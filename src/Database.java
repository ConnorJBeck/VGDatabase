import java.sql.SQLException;

public class Database {

    Database () {

    }

    public void initDatabase() {
        try {
            DatabaseFacade.stmt.executeUpdate("CREATE TABLE ESRBRating (" +
                    "shortName  CHAR(2)," +
                    "ratingName VARCHAR(20) NOT NULL UNIQUE, " +
                    "PRIMARY KEY (shortName))");
            DatabaseFacade.stmt.executeUpdate("CREATE TABLE RegisteredUser" +
                    "(userName  VARCHAR(20)," +
                    " email     VARCHAR(50) NOT NULL UNIQUE," +
                    " password  VARCHAR(12) NOT NULL," +
                    "PRIMARY KEY (userName))");
            DatabaseFacade.stmt.executeUpdate("CREATE TABLE Admin " +
                    "(userName  VARCHAR(20)," +
                    " email     VARCHAR(50) NOT NULL UNIQUE," +
                    " password  VARCHAR(20) NOT NULL," +
                    "PRIMARY KEY (userName)," +
                    "FOREIGN KEY (userName) REFERENCES RegisteredUser" +
                    " ON DELETE CASCADE)");
            DatabaseFacade.stmt.executeUpdate("CREATE TABLE Game" +
                    "(gameID        INTEGER," +
                    " addedBy       VARCHAR(20) NOT NULL," +
                    " ESRBrating    CHAR(2) NOT NULL," +
                    " name          VARCHAR(100) NOT NULL," +
                    "PRIMARY KEY (gameID)," +
                    "FOREIGN KEY (addedBy) REFERENCES Admin(userName)" +
                    " ON DELETE SET DEFAULT" +
                    " ON UPDATE CASCADE," +
                    "FOREIGN KEY (ESRBrating) REFERENCES ESRBRating(shortName)" +
                    " ON DELETE CASCADE" +
                    " ON UPDATE CASCADE)");
            DatabaseFacade.stmt.executeUpdate("CREATE TABLE Release " +
                    "(gameID        INTEGER," +
                    " region        VARCHAR(20)," +
                    " platform      VARCHAR(20)," +
                    " addedBy       VARCHAR(20)," +
                    " releaseDate   DATE)" +
                    "PRIMARY KEY (gameID, region, platform)," +
                    "FOREIGN KEY (gameID) REFERENCES Game," +
                    " ON DELETE CASCADE" +
                    " ON UPDATE CASCADE" +
                    "FOREIGN KEY (addedBy) REFERENCES Admin(userName)" +
                    " ON DELETE SET DEFAULT" +
                    " ON UPDATE CASCADE)");
            DatabaseFacade.stmt.executeUpdate("CREATE TABLE CreateReview" +
                    "(reviewID      INTEGER," +
                    " userName      VARCHAR(20) NOT NULL," +
                    " gameID        INTEGER NOT NULL," +
                    " platform      VARCHAR(20)NOT NULL, " +
                    " reviewText    VARCHAR(1000)" +
                    " postDateTime  DATETIME," +
                    " rating        FLOAT(2,1)," +
                    "PRIMARY KEY (reviewID, platform, releaseDate)," +
                    "FOREIGN KEY (userName) REFERENCES RegisteredUser," +
                    " ON DELETE CASCADE" +
                    "FOREIGN KEY (gameID) REFERENCES Game" +
                    " ON DELETE CASCADE" +
                    " ON UPDATE CASCADE)");
            DatabaseFacade.stmt.executeUpdate("CREATE TABLE Tag" +
                    "(name      VARCHAR(20)," +
                    " addedBy   VARCHAR(20)NOT NULL," +
                    "PRIMARY KEY (name)," +
                    "FOREIGN KEY (addedBy) REFERENCES Admin(userName)" +
                    " ON DELETE SET DEFAULT" +
                    " ON UPDATE CASCADE)");
            DatabaseFacade.stmt.executeUpdate("CREATE TABLE TagGame" +
                    "(tagName   VARCHAR(50)," +
                    " gameID    INTEGER," +
                    " userName  VARCHAR(20)," +
                    "PRIMARY KEY (tagName, userName, gameID)," +
                    "FOREIGN KEY (tagName) REFERENCES Tag," +
                    " ON DELETE CASCADE" +
                    " ON UPDATE CASCADE" +
                    "FOREIGN KEY (gameID) REFERENCES Game," +
                    " ON DELETE CASCADE" +
                    " ON UPDATE CASCADE" +
                    "FOREIGN KEY (userName) REFERENCES RegisteredUser" +
                    " ON DELETE SET DEFAULT)");
            DatabaseFacade.stmt.executeUpdate("CREATE TABLE List " +
                    "(listID    INTEGER," +
                    " name      VARCHAR(50) NOT NULL," +
                    " userName  VARCHAR(20) NOT NULL," +
                    "PRIMARY KEY (listID)" +
                    "FOREIGN KEY (userName) REFERENCES RegisteredUser" +
                    " ON DELETE CASCADE)");
            DatabaseFacade.stmt.executeUpdate("CREATE TABLE ListEntries" +
                    "(listID    INTEGER," +
                    " gameID    INTEGER," +
                    " dateAdded DATE, " +
                    "PRIMARY KEY (listID, gameID)," +
                    "FOREIGN KEY (userName) REFERENCES RegisteredUser," +
                    " ON DELETE CASCADE" +
                    " FOREIGN KEY (gameID) REFERENCES Game" +
                    " ON DELETE CASCADE" +
                    " ON UPDATE CASCADE)");
        } catch (SQLException err) {
            System.out.println("Error: " + err.getMessage());
            System.out.println("Error: " + err.toString());
            for (int i = 0; i < err.getStackTrace().length; i++) {
                System.out.println("Error: " + err.getStackTrace()[i]);
            }
            System.out.println("Error: " + err.getStackTrace().length);
        }
    }
}
