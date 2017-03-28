import java.sql.*;

public class ConnectionManager {

    private static Connection connection;
    private static ConnectionManager instance;
    private static Statement stmt;

    private ConnectionManager(String user, String password) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1522:ug", user, password);
    }

    public static void initConnection(String user, String password) throws SQLException {
        if(instance == null) {
            instance = new ConnectionManager(user, password);
        }
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    public static Statement getStatement() throws SQLException {
        if (connection == null) {
            throw new SQLException("Not really an SQL exception, but you need to initialize the connection first");
        }
        if (stmt == null) {
            stmt = connection.createStatement();
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
