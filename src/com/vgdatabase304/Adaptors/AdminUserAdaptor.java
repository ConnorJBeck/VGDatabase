package com.vgdatabase304.Adaptors;

import com.vgdatabase304.Structures.AdminUser;
import com.vgdatabase304.Structures.RegisteredUser;

import java.sql.SQLException;

public class AdminUserAdaptor extends RegisteredUserAdaptor {


    public static AdminUser addAdminToDatabase(String username, String email, String password) throws SQLException {
        addUserToDatabase(username, email, password);
        stmt.executeUpdate("INSERT INTO Admin (UserName, Email, Password) VALUES ('" +
                username + "', '" +
                email + "', '" +
                password + "')"
        );
        return new AdminUser("username");
    }

    public static AdminUser giveAdminPrivileges(RegisteredUser user) throws SQLException {
        stmt.executeUpdate("INSERT INTO Admin (UserName, Email, Password) VALUES ('" +
                user.getUsername() + "', '" +
                RegisteredUserAdaptor.getEmail(user) + "', '" +
                RegisteredUserAdaptor.getPassword(user) + "')"
        );
        return (AdminUser) user;
    }

    public void revokeAdminPriviliges(AdminUser admin) throws SQLException {
        stmt.executeUpdate("DELETE FROM Admin WHERE USERNAME='" + admin.getUsername() + "'");
    }
}
