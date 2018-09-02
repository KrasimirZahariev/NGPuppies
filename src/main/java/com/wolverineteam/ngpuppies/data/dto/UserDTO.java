package com.wolverineteam.ngpuppies.data.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotNull(message = "Cannot be null!")
    private String username;

    @NotNull(message = "Cannot be null!")
    private String password;

    @NotNull(message = "Cannot be null!")
    @Digits(integer = 20,fraction = 0,message = "EIK number can contain only digits!")
    private String EIK;

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

    public String getEIK() {
        return EIK;
    }

    public void setEIK(String EIK) {
        this.EIK = EIK;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
