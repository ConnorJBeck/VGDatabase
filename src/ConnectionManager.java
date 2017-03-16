import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private Connection connection;
    private static ConnectionManager instance;

    private ConnectionManager() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1522:ug", "ora_s8h0b", "a57723158");
    }

    public static ConnectionManager getInstance() throws SQLException {
        if(instance == null) {
            instance = new ConnectionManager();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
