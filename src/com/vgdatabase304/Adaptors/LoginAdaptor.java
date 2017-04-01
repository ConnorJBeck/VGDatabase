package com.vgdatabase304.Adaptors;

import com.vgdatabase304.Structures.RegisteredUser;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.vgdatabase304.Adaptors.RegisteredUserAdaptor.stmt;

/**
 * Created by yoshi on 3/30/2017.
 */
public class LoginAdaptor {

    boolean runLogin(String username, String password)
    {
        return runLoginHelper(new RegisteredUser(username), password);
    }

    // Helper to determine if loginUser is an admin or not
    boolean runLoginHelper(RegisteredUser loginUser, String password)
    {

        boolean isAdmin = false;
        try
        {
            AdminUserAdaptor adminUserAdaptor = new AdminUserAdaptor();
            isAdmin = adminUserAdaptor.isAdmin(loginUser.getUsername());
        }

        catch (SQLException e){System.out.println(e);}

        return submitLoginToServer(loginUser, password, isAdmin);
    }

    boolean submitLoginToServer(RegisteredUser loginUser, String password, boolean isAdmin)
    {

        String sql = "SELECT username level FROM " + (isAdmin? "ADMIN" : "USER") +
                "WHERE username LIKE '" + loginUser.getUsername() + "'" +
                "AND password LIKE '" + password + "'";

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);

            if (rs != null && rs.next())
            {
                return true;
            }
        }
        catch (SQLException e){System.out.println(e);}

        return false;
    }
}
