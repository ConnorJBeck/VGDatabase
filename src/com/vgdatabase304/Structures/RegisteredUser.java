package com.vgdatabase304.Structures;

public class RegisteredUser extends SearchResult{

    private String username;

    public RegisteredUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
