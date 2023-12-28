package com.project.notes.model;

public class JwtResponse {
    private Integer userId;
    private String username;
    private String jwtToken;

    public JwtResponse(String username, String jwtToken) {
        this.username = username;
        this.jwtToken = jwtToken;
    }
//    public JwtResponse(Integer userId, String username, String jwtToken) {
//        this.userId = userId;
//        this.username = username;
//        this.jwtToken = jwtToken;
//    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwtToken() {
        return jwtToken;
    }
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
