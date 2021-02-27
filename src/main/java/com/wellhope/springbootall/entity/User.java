package com.wellhope.springbootall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author GaoJ
 * @create 2021-02-26 22:35
 */
public class User {
    @JsonIgnore//字段不返回
    Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)//空字段不返回
    @NotNull(message = "用户名不能为空")//被注释的元素必须不为 null
    String name;
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//设置响应的时间格式(可在application.properties配置文件统一配置)
    Date entryDate;

    public User(Long id, String name, Date entryDate) {
        this.id = id;
        this.name = name;
        this.entryDate = entryDate;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", entryDate=" + entryDate +
                '}';
    }
}
