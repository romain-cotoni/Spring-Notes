package com.project.notes.dto;

import com.project.notes.enumeration.Right;

import java.util.List;

public class SharedNoteUserDto {

    private Integer id;

    private Right right;

    private String username;

    private Integer userId;

    private List<SharedUserDto> sharedUsers;

    public SharedNoteUserDto() {}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Right getRight() {
        return right;
    }
    public void setRight(Right right) {
        this.right = right;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<SharedUserDto> getSharedUsers() {
        return sharedUsers;
    }
    public void setSharedUsers(List<SharedUserDto> sharedUsers) {
        this.sharedUsers = sharedUsers;
    }
}
