package com.project.notes.dto;

import com.project.notes.enumeration.Right;

public class SharedUserDto {

    private Integer id;

    private String username;

    private Right right;

    public SharedUserDto() {}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Right getRight() {
        return right;
    }

    public void setRight(Right right) {
        this.right = right;
    }

}
