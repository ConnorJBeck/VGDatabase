package com.vgdatabase304.Structures;

import java.sql.SQLException;

public class RegisteredUser {

    private String username;

    public RegisteredUser(String username) throws SQLException {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
