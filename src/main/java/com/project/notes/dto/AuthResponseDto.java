package com.project.notes.dto;

public class AuthResponseDto {

    private String jwtToken;

    private UserDto user = new UserDto();


    //CONSTRUCTORS

    public AuthResponseDto() {}

    public AuthResponseDto(String jwtToken, UserDto user) {
        this.jwtToken = jwtToken;
        this.user = user;
    }

    //GETTERS && SETTERS

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

}
