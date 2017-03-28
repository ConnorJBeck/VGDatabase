package com.vgdatabase304.Structures;

import com.vgdatabase304.Exceptions.*;
import com.vgdatabase304.Utils.*;

import java.sql.SQLException;

public class AdminUser extends RegisteredUser {

    public AdminUser(String username, String email, String password) throws SQLException {
        super(username, email, password);
    }

    public void addAdminToDatabase() throws SQLException {
        addUserToDatabase();
        stmt.executeUpdate("INSERT INTO Admin (UserName, Email, Password) VALUES ('" +
                username + "', '" +
                email + "', '" +
                password + "')"
        );
    }

    public AdminUser giveAdminPrivileges(RegisteredUser user) throws SQLException {
        stmt.executeUpdate("INSERT INTO Admin (UserName, Email, Password) VALUES ('" +
                username + "', '" +
                email + "', '" +
                password + "')"
        );
        return (AdminUser) user;
    }

    public void revokeAdminPriviliges() throws SQLException {
        stmt.executeUpdate("DELETE FROM Admin WHERE USERNAME='" + username + "'");
    }
}
