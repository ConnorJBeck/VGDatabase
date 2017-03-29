package com.vgdatabase304.Adaptors;

import com.vgdatabase304.Exceptions.InstanceNotFoundException;
import com.vgdatabase304.Structures.RegisteredUser;
import com.vgdatabase304.Utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RegisteredUserAdaptor {

    protected static ResultSet rs;
    protected static Statement stmt;

    public static RegisteredUser addRegisteredUserToDatabase(String username, String email, String password) throws SQLException {
        System.out.println("before stmt");
        stmt = ConnectionManager.getStatement();
        System.out.println("after statement");
        try {
            System.out.println("before update");
            stmt.executeUpdate("INSERT INTO RegisteredUser (UserName, Email, Password) VALUES ('" +
                    username + "', '" +
                    email + "', '" +
                    password + "')"
            );
            System.out.println("after update");
        } catch (SQLException e) {
            System.out.println("exception thrown: " + e);
        }
        System.out.println("before return");
        return new RegisteredUser(username);
    }



    public static String getPassword(RegisteredUser user) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT PASSWORD FROM REGISTEREDUSER WHERE USERNAME='" + user.getUsername() + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getString("PASSWORD");
        } else {
            throw new InstanceNotFoundException("No record found in REGISTEREDUSER for " + user.getUsername());
        }
    }

    public static void setPassword(RegisteredUser user, String password) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE REGISTEREDUSER " +
                "SET PASSWORD = '" + password +
                "' WHERE USERNAME = '" + user.getUsername() + "'"
        );
    }

    public static String getEmail(RegisteredUser user) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT EMAIL FROM REGISTEREDUSER WHERE USERNAME='" + user.getUsername() + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getString("EMAIL");
        } else {
            throw new InstanceNotFoundException("No record found in REGISTEREDUSER for " + user.getUsername());
        }
    }

    public static void setEmail(RegisteredUser user, String email) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE REGISTEREDUSER " +
                "SET EMAIL = '" + email +
                "' WHERE USERNAME = '" + user.getUsername() + "'"
        );
    }
}
