package com.lvonce.lightserver.api;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.matcher.RestAssuredMockMvcMatchers.*;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexTest {

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    public void testHelloWorld() {
        given()
                .get("/")
                .then()
                .statusCode(200)
                .contentType("text/plain")
                .content(equalTo("hello world"));
    }

    @Test
    public void testGetUserExample() {
        Map params = new LinkedHashMap();
        params.put("id", 1);
        given().get("/user-example", params)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", equalTo(23));
    }

    @Test
    public void testPostUserExample() {
        given().header("Content-Type", "application/json")
                .body("{\"id\":24,\"createTime\":\"2018-07-27T09:19:28.519+0000\",\"updateTime\":\"2018-07-27T09:19:28.519+0000\"}")
                .post("/user-example")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .content(equalTo("24"));
    }

}
