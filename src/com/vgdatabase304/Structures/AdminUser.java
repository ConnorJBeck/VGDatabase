package com.vgdatabase304.Structures;

import java.sql.SQLException;

public class AdminUser extends RegisteredUser {

    public AdminUser(String username) throws SQLException {
        super(username);
    }
}
