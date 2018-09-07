package com.wolverineteam.ngpuppies.data.dto;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class UserDTO {

    private String userId;

    private String username;

    private String password;

    private String eik;

    private String role;

    public UserDTO(){

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

    public String getEik() {
        return eik;
    }

    public void setEik(String eik) {
        this.eik = eik;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
