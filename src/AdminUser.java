import java.sql.SQLException;

public class AdminUser extends RegisteredUser {

    public AdminUser(String username, String email, String password) throws SQLException{
        super(username, email, password);
    }

    public void addAdminToDatabase() throws SQLException {
        stmt.executeUpdate("INSERT INTO RegisteredUser (UserName, Email, Password) VALUES ('" +
                username + "', '" +
                email + "', '" +
                password + "')"
        );
        stmt.executeUpdate("INSERT INTO Admin (UserName, Email, Password) VALUES ('" +
                username + "', '" +
                email + "', '" +
                password + "')"
        );
    }
}
