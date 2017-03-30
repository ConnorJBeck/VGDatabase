package com.vgdatabase304.Adaptors;

import com.vgdatabase304.Structures.AdminUser;
import com.vgdatabase304.Structures.RegisteredUser;

import java.sql.SQLException;

public class AdminUserAdaptor extends RegisteredUserAdaptor {


    public static AdminUser addAdminUserToDatabase(String username, String email, String password) throws SQLException {
        RegisteredUser user = addRegisteredUserToDatabase(username, email, password);
        return giveAdminPrivileges(user);
    }

    public static AdminUser giveAdminPrivileges(RegisteredUser user) throws SQLException {
        try {
            stmt.executeUpdate("INSERT INTO Admin (UserName, Email, Password) VALUES ('" +
                    user.getUsername() + "', '" +
                    RegisteredUserAdaptor.getEmail(user) + "', '" +
                    RegisteredUserAdaptor.getPassword(user) + "')"
            );
        } catch (SQLException e) {

        }

        return new AdminUser(user.getUsername());
    }

    public static void revokeAdminPriviliges(AdminUser admin) throws SQLException {
        stmt.executeUpdate("DELETE FROM Admin WHERE USERNAME='" + admin.getUsername() + "'");
    }

    public static boolean isAdmin(RegisteredUser user) throws SQLException {
        rs = stmt.executeQuery("SELECT * FROM ADMIN WHERE USERNAME='" + user.getUsername() + "'");
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }
}
