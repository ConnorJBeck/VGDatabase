import java.sql.SQLException;
import java.sql.Statement;

public class RegisteredUser {

    protected String username;
    protected String email;
    protected String password;
    protected Statement stmt;

    public RegisteredUser(String username, String email, String password) throws SQLException {
        this.username = username;
        this.email = email;
        this.password = password;
        stmt = ConnectionManager.getStatement();
    }

    public void addUserToDatabase() throws SQLException {
        stmt.executeUpdate("INSERT INTO RegisteredUser (UserName, Email, Password) VALUES ('" +
                username + "', '" +
                email + "', '" +
                password + "')"
        );
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
