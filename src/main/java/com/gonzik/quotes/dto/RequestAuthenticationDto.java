package com.gonzik.quotes.dto;

public class RequestAuthenticationDto {
    private String login;
    private String password;
    private String userId;

    public String getUserId() {
        return userId;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

}
