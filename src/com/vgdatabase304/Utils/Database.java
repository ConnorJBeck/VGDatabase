package com.vgdatabase304.Utils;
import com.vgdatabase304.Structures.ESRBRating;

import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Statement stmt;

    public static void initDatabase() throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("CREATE TABLE ESRBRating (" +
                "shortName  VARCHAR(2)," +
                "ratingName VARCHAR(20) NOT NULL UNIQUE, " +
                "PRIMARY KEY (shortName))");
        stmt.executeUpdate("CREATE TABLE RegisteredUser" +
                "(userName  VARCHAR(20)," +
                " email     VARCHAR(50) NOT NULL UNIQUE," +
                " password  VARCHAR(12) NOT NULL," +
                "PRIMARY KEY (userName))");
        stmt.executeUpdate("CREATE TABLE Admin " +
                "(userName  VARCHAR(20)," +
                " email     VARCHAR(50) NOT NULL UNIQUE," +
                " password  VARCHAR(20) NOT NULL," +
                "PRIMARY KEY (userName)," +
                "FOREIGN KEY (userName) REFERENCES RegisteredUser" +
                " ON DELETE CASCADE)");
        stmt.executeUpdate("CREATE TABLE Game" +
                "(gameID        INTEGER," +
                " addedBy       VARCHAR(20)," +
                " ESRBrating    VARCHAR(2) NOT NULL," +
                " name          VARCHAR(100) NOT NULL," +
                "PRIMARY KEY (gameID)," +
                "FOREIGN KEY (addedBy) REFERENCES Admin(userName)" +
                " ON DELETE SET NULL," +
                "FOREIGN KEY (ESRBrating) REFERENCES ESRBRating(shortName)" +
                " ON DELETE CASCADE)");
        stmt.executeUpdate("CREATE TABLE Release " +
                "(gameID        INTEGER," +
                " region        VARCHAR(20)," +
                " platform      VARCHAR(20)," +
                " addedBy       VARCHAR(20)," +
                " releaseDate   DATE, " +
                "PRIMARY KEY (gameID, region, platform)," +
                "FOREIGN KEY (gameID) REFERENCES Game" +
                " ON DELETE CASCADE, " +
                "FOREIGN KEY (addedBy) REFERENCES Admin(userName)" +
                " ON DELETE SET NULL)");
        stmt.executeUpdate("CREATE TABLE CreateReview" +
                "(reviewID      INTEGER," +
                " userName      VARCHAR(20) NOT NULL," +
                " gameID        INTEGER NOT NULL," +
                " region        VARCHAR(20)," +
                " platform      VARCHAR(20)NOT NULL, " +
                " reviewText    VARCHAR(1000), " +
                " postDateTime  TIMESTAMP," +
                " rating        FLOAT(1)," +
                "PRIMARY KEY (reviewID)," +
                "FOREIGN KEY (userName) REFERENCES RegisteredUser" +
                " ON DELETE CASCADE," +
                "FOREIGN KEY (gameID) REFERENCES Game" +
                " ON DELETE CASCADE)");
        stmt.executeUpdate("CREATE TABLE Tag" +
                "(name      VARCHAR(20)," +
                " addedBy   VARCHAR(20)," +
                "PRIMARY KEY (name)," +
                "FOREIGN KEY (addedBy) REFERENCES Admin(userName)" +
                " ON DELETE SET NULL)");
        stmt.executeUpdate("CREATE TABLE TagGame" +
                "(tagName   VARCHAR(50)," +
                " gameID    INTEGER," +
                " userName  VARCHAR(20)," +
                "PRIMARY KEY (tagName, userName, gameID)," +
                "FOREIGN KEY (tagName) REFERENCES Tag" +
                " ON DELETE CASCADE," +
                "FOREIGN KEY (gameID) REFERENCES Game" +
                " ON DELETE CASCADE," +
                "FOREIGN KEY (userName) REFERENCES RegisteredUser" +
                " ON DELETE SET NULL)");
        stmt.executeUpdate("CREATE TABLE List " +
                "(listID    INTEGER," +
                " name      VARCHAR(50) NOT NULL," +
                " userName  VARCHAR(20) NOT NULL," +
                "PRIMARY KEY (listID), " +
                "FOREIGN KEY (userName) REFERENCES RegisteredUser" +
                " ON DELETE CASCADE)");
        stmt.executeUpdate("CREATE TABLE ListEntries" +
                "(listID    INTEGER," +
                " gameID    INTEGER," +
                " dateAdded DATE, " +
                "PRIMARY KEY (listID, gameID)," +
                "FOREIGN KEY (listID) REFERENCES List" +
                " ON DELETE CASCADE," +
                "FOREIGN KEY (gameID) REFERENCES Game" +
                " ON DELETE CASCADE)");

        stmt.executeUpdate("INSERT INTO ESRBRATING (SHORTNAME, RATINGNAME) VALUES ('" +
                ESRBRating.E.getShortName() + "', '" +
                ESRBRating.E.getRatingName() + "')");
    }

    public static void deleteDatabase() throws SQLException {
        stmt = ConnectionManager.getStatement();
        try {
            stmt.executeUpdate("DROP TABLE ListEntries CASCADE CONSTRAINTS");
            System.out.println("ListEntries table successfully dropped.");
        } catch (SQLException err) {
            System.out.println("ListEntries table not found.");
        }

        try {
        stmt.executeUpdate("DROP TABLE TagGame CASCADE CONSTRAINTS");
            System.out.println("TagGame table successfully dropped.");
        } catch (SQLException err) {
            System.out.println("TagGame table not found.");
        }

        try {
            stmt.executeUpdate("DROP TABLE Tag CASCADE CONSTRAINTS");
            System.out.println("Tag table successfully dropped.");
        } catch (SQLException err) {
            System.out.println("Tag table not found.");
        }

        try {
            stmt.executeUpdate("DROP TABLE CreateReview CASCADE CONSTRAINTS");
            System.out.println("CreateReview table successfully dropped.");
        } catch (SQLException err) {
            System.out.println("CreateReview table not found.");
        }

        try {
            stmt.executeUpdate("DROP TABLE List CASCADE CONSTRAINTS");
            System.out.println("List table successfully dropped.");
        } catch (SQLException err) {
            System.out.println("List table not found.");
        }

        try {
            stmt.executeUpdate("DROP TABLE Release CASCADE CONSTRAINTS");
            System.out.println("Release table successfully dropped.");
        } catch (SQLException err) {
            System.out.println("Release table not found.");
        }

        try {
            stmt.executeUpdate("DROP TABLE Game CASCADE CONSTRAINTS");
            System.out.println("Game table successfully dropped.");
        } catch (SQLException err) {
            System.out.println("Game table not found.");
        }

        try {
            stmt.executeUpdate("DROP TABLE ESRBRating CASCADE CONSTRAINTS");
            System.out.println("ESRBRating table successfully dropped.");
        } catch (SQLException err) {
            System.out.println("ESRBRating table not found.");
        }

        try {
            stmt.executeUpdate("DROP TABLE Admin CASCADE CONSTRAINTS");
            System.out.println("Admin table successfully dropped.");
        } catch (SQLException err) {
            System.out.println("Admin table not found.");
        }

        try {
            stmt.executeUpdate("DROP TABLE RegisteredUser CASCADE CONSTRAINTS");
            System.out.println("RegisteredUser table successfully dropped.");
        } catch (SQLException err) {
            System.out.println("RegisteredUser table not found.");
        }

    }
}
