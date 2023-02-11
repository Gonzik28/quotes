package com.gonzik.quotes.dto;

public class ResponseAuthenticationDto {
    private String login;
    private ResponseUserDto user;

    public void setLogin(String login) {
        this.login = login;
    }

    public ResponseUserDto getUser() {
        return user;
    }

    public void setUser(ResponseUserDto user) {
        this.user = user;
    }
}
