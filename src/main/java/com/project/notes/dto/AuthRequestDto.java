package com.project.notes.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.notes.enumeration.Role;

public class AuthRequestDto {

    private String username;
    private String password;
    private Role role;


    @JsonCreator
    public AuthRequestDto(@JsonProperty("username") String username,
                          @JsonProperty("password") String password,
                          @JsonProperty("role") Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }


}
