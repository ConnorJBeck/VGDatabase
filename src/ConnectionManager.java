import java.sql.*;

public class ConnectionManager {

    private static Connection connection;
    private static ConnectionManager instance;
    private static Statement stmt;

    private ConnectionManager() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1522:ug", "ora_s8h0b", "a57723158");
    }

    public static void initConnection() throws SQLException {
        if(instance == null) {
            instance = new ConnectionManager();
        }
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    public static Statement getStatement() throws SQLException {
        if (connection == null) {
            initConnection();
        }
        if (stmt == null) {
            connection.createStatement();
        }

        return stmt;
    }

    /*
    public static void executeUpdate(String sqlStatement) throws SQLException {
        stmt.executeUpdate(sqlStatement);
    }

    public static ResultSet executeQuery(String sqlStatement) throws SQLException {
        return stmt.executeQuery(sqlStatement);
    }
    */
}
