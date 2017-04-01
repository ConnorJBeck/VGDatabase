package com.vgdatabase304.Adaptors;

import com.vgdatabase304.Structures.AdminUser;
import com.vgdatabase304.Structures.RegisteredUser;
import com.vgdatabase304.Utils.ConnectionManager;

import java.sql.SQLException;

public class AdminUserAdaptor extends RegisteredUserAdaptor {


    public static AdminUser addAdminUserToDatabase(String username, String email, String password) throws SQLException {
        stmt = ConnectionManager.getStatement();
        RegisteredUser user = addRegisteredUserToDatabase(username, email, password);
        return giveAdminPrivileges(user);
    }

    public static AdminUser giveAdminPrivileges(RegisteredUser user) throws SQLException {
        stmt = ConnectionManager.getStatement();
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
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("DELETE FROM Admin WHERE USERNAME='" + admin.getUsername() + "'");
    }

    public static boolean isAdmin(String username) throws SQLException {
        stmt = ConnectionManager.getStatement();
        rs = stmt.executeQuery("SELECT * FROM ADMIN WHERE USERNAME='" + username + "'");
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }
}
