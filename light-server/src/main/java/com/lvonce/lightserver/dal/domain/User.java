package com.lvonce.lightserver.dal.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "\"user\"")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "createTime",
        "updateTime"
})
public class User {

    @Id
    @JsonProperty("id")
    private Long id;
    @JsonProperty("createTime")
    private Date createTime;
    @JsonProperty("updateTime")
    private Date updateTime;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("createTime")
    public Date getCreateTime() {
        return createTime;
    }

    @JsonProperty("createTime")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonProperty("updateTime")
    public Date getUpdateTime() {
        return updateTime;
    }

    @JsonProperty("updateTime")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("createTime");
        sb.append('=');
        sb.append(((this.createTime == null) ? "<null>" : this.createTime));
        sb.append(',');
        sb.append("updateTime");
        sb.append('=');
        sb.append(((this.updateTime == null) ? "<null>" : this.updateTime));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.updateTime == null) ? 0 : this.updateTime.hashCode()));
        result = ((result * 31) + ((this.id == null) ? 0 : this.id.hashCode()));
        result = ((result * 31) + ((this.createTime == null) ? 0 : this.createTime.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof User) == false) {
            return false;
        }
        User rhs = ((User) other);
        return ((((this.updateTime == rhs.updateTime) || ((this.updateTime != null) && this.updateTime.equals(rhs.updateTime))) && ((this.id == rhs.id) || ((this.id != null) && this.id.equals(rhs.id)))) && ((this.createTime == rhs.createTime) || ((this.createTime != null) && this.createTime.equals(rhs.createTime))));
    }

}
