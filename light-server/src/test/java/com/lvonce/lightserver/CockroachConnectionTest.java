package com.lvonce.lightserver;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class CockroachConnectionTest {

    private String dbIp = "115.29.17.166";
    private int dbPort = 26257;
    private String dbType = "postgresql";
    private String dbName = "school";
    private String driver = "org.postgresql.Driver";
    private String userName = "testuser";
    private String password = "";
    private boolean sslMode = false;

    private Connection getConnection() throws Exception {
        String ssl = sslMode ? "" : "?sslmode=disable";
        String url = String.format("jdbc:%s://%s:%d/%s%s", dbType, dbIp, dbPort, dbName, ssl);
        Class.forName(driver).newInstance();
        return DriverManager.getConnection(url, userName, password);
    }

    @Test
    public void testConnection() throws Exception {
        Assert.assertNotNull(getConnection());
    }

    public void createTable() throws Exception {
        getConnection().createStatement().execute("CREATE TABLE IF NOT EXISTS student (id INT PRIMARY KEY, name varchar(30))");
    }

    private void insertStudent() throws Exception {
        getConnection().createStatement().execute("INSERT INTO school.student (id, name) VALUES (11, 'Deepak')");
    }

    public void updateStudent() throws Exception {
        getConnection().createStatement().execute("update student set id=12 where id=11");
    }

    public void selectStudent(int id, boolean expectExists) throws Exception {
        String sql = String.format("SELECT id, name FROM student WHERE id=%d", id);
        ResultSet res = getConnection().createStatement().executeQuery(sql);
        if (expectExists) {
            res.next();
            int findId = res.getInt("id");
            Assert.assertEquals(id, findId);
        } else {
            Assert.assertFalse(res.next());
        }
    }

    public void deleteStudent() throws Exception {
        getConnection().createStatement().execute("delete from student where id=11");
        getConnection().createStatement().execute("delete from student where id=12");
    }

    @Test
    public void testBasic() throws Exception {
        createTable();
        deleteStudent();
        insertStudent();
        selectStudent(11, true);
        deleteStudent();
        selectStudent(11, false);
        insertStudent();
        updateStudent();
        selectStudent(11, false);
        selectStudent(12, true);
    }
}
