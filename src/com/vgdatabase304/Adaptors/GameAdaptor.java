package com.vgdatabase304.Adaptors;

import com.vgdatabase304.Exceptions.*;
import com.vgdatabase304.Structures.*;
import com.vgdatabase304.Utils.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameAdaptor {

    private static Statement stmt;
    private static ResultSet rs;

    public static Game addGameToDatabase(AdminUser addedBy, ESRBRating esrbRating, String name, Region region, Platform platform, Date releaseDate) throws SQLException{
        stmt = ConnectionManager.getStatement();
        ResultSet rs = stmt.executeQuery("SELECT Max(GAMEID) FROM GAME");
        int gameID = 0;
        if (rs.next()) {
            gameID = rs.getInt(1);
        }
        gameID++;

        String sql = "INSERT INTO Game (gameID, addedBy, ESRBRating, name) VALUES (" +
                gameID + ", '" +
                addedBy.getUsername() + "', '" +
                esrbRating.getShortName() + "', '" +
                name + "')";
        stmt.executeUpdate(sql);
        Game newGame = new Game(gameID);
        ReleaseAdaptor.addReleaseToDatabase(newGame, region, platform, addedBy, releaseDate);
        return newGame;
    }

    public static void removeGameFromDatabase(Game game) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "DELETE FROM RELEASE WHERE " +
                "GAMEID=" + game.getGameID();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM GAME WHERE " +
                "GAMEID=" + game.getGameID();
        stmt.executeUpdate(sql);
    }

    public static void setGameID(Game game, int gameID) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE GAME " +
                "SET GAMEID=" + gameID +
                " WHERE gameID=" + game.getGameID()
        );
        game.setGameID(gameID);
    }


    public static String getAddedBy(Game game) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT AddedBy FROM GAME WHERE GAMEID=" + game.getGameID();
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getString(1);
        } else {
            throw new InstanceNotFoundException("No record found in GAME for " + game.getGameID());
        }
    }

    public static void setAddedBy(Game game, AdminUser addedBy) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE GAME " +
                "SET addedby = " + addedBy.getUsername() +
                " WHERE gameID = " + game.getGameID()
        );
    }

    public static String getName(Game game) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT Name FROM GAME WHERE GAMEID=" + game.getGameID();
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getString(1);
        } else {
            throw new InstanceNotFoundException("No record found in GAME for " + game.getGameID());
        }
    }

    public static void setName(Game game, String name) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE GAME " +
                "SET name = " + name +
                " WHERE gameID = " + game.getGameID()
        );
    }

    public static ESRBRating getESRBRating(Game game) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT ESRBRATING FROM GAME WHERE GAMEID=" + game.getGameID();
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return ESRBRating.getRatingFromString(rs.getString(1));
        } else {
            throw new InstanceNotFoundException("No record found in GAME for " + game.getGameID());
        }
    }

    public static void setESRBRating(Game gameID, ESRBRating esrbRating) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE GAME " +
                "SET ESRBRating = " + esrbRating.getShortName() +
                " WHERE gameID = " + gameID.getGameID()
        );
    }

    public static List<Release> getReleases(Game game) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT GAMEID, REGION, PLATFORM FROM RELEASE WHERE GAMEID=" + game.getGameID();
        rs = stmt.executeQuery(sql);
        List<Release> releases = new ArrayList<>();
        Region region;
        Platform platform;
        while (rs.next()) {
            region = Region.valueOf(rs.getString("REGION"));
            platform = Platform.valueOf(rs.getString("PLATFORM"));
            releases.add(new Release(game, region, platform));
        }
        if (releases.size() > 0) {
            return releases;
        } else {
            throw new InstanceNotFoundException("No releases found for GameID " + game.getGameID());
        }
    }

    public static List<Game> getGameByTitle(String gameTitle) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT GAMEID FROM GAME WHERE NAME LIKE '%" + gameTitle + "%'";
        rs = stmt.executeQuery(sql);
        List<Game> games = new ArrayList<>();
        while (rs.next()) {
            games.add(new Game(rs.getInt(1)));
        }
        return games;
    }
}
