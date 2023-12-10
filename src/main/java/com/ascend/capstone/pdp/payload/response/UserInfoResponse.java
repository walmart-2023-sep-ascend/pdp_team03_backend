package com.ascend.capstone.pdp.payload.response;

public class UserInfoResponse {
    private Integer userId;
    private String username;
    private String email;
    private String jwtcookie;

    public UserInfoResponse(Integer userId, String username, String email,String jwtcookie) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.jwtcookie = jwtcookie;
    }

    public String getJwtcookie() {
        return jwtcookie;
    }

    public void setJwtcookie(String jwtcookie) {
        this.jwtcookie = jwtcookie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
