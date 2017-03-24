import Exceptions.InstanceNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private static Statement stmt;
    private static ResultSet rs;

    public static void addGameToDatabase(Release release, AdminUser addedBy, ESRBRating esrbRating, String name) throws SQLException{
        stmt = ConnectionManager.getStatement();
        ResultSet rs = stmt.executeQuery("SELECT Max(GAMEID) FROM GAME");
        int gameID = 0;
        if (rs.first()) {
            gameID = rs.getInt(1);
        }
        gameID++;

        String sql = "INSERT INTO Game (gameID, addedBy, ESRBRating, name) VALUES (" +
                gameID + ", '" +
                addedBy.getUsername() + "', '" +
                esrbRating.getShortName() + "', '" +
                name + "')";
        stmt.executeUpdate(sql);
        ReleaseAdaptor.AddReleaseToDatabase(gameID);
    }


    public static String getAddedBy(int gameID) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT AddedBy FROM GAME WHERE GAMEID=" + gameID;
        rs = stmt.executeQuery(sql);
        if (rs.first()) {
            return rs.getString(1);
        } else {
            throw new InstanceNotFoundException("No record found in GAME for " + gameID);
        }
    }

    public static void setAddedBy(int gameID, String addedBy) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE GAME " +
                "SET addedby = " + addedBy +
                " WHERE gameID = " + gameID
        );
    }

    public static String getName(int gameID) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT Name FROM GAME WHERE GAMEID=" + gameID;
        rs = stmt.executeQuery(sql);
        if (rs.first()) {
            return rs.getString(1);
        } else {
            throw new InstanceNotFoundException("No record found in GAME for " + gameID);
        }
    }

    public static void setName(int gameID, String name) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE GAME " +
                "SET name = " + name +
                " WHERE gameID = " + gameID
        );
    }

    public static ESRBRating getESRBRating(int gameID) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT ESRBRATING FROM GAME WHERE GAMEID=" + gameID;
        rs = stmt.executeQuery(sql);
        if (rs.first()) {
            return ESRBRating.getRatingFromString(rs.getString(1));
        } else {
            throw new InstanceNotFoundException("No record found in GAME for " + gameID);
        }
    }

    public static void setESRBRating(int gameID, ESRBRating esrbRating) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE GAME " +
                "SET ESRBRating = " + esrbRating.getShortName() +
                " WHERE gameID = " + gameID
        );
    }

    public static List<Release> getReleases(int gameID) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT GAMEID, REGION, PLATFORM FROM RELEASE WHERE GAMEID=" + gameID;
        rs = stmt.executeQuery(sql);
        List<Release> releases = new ArrayList<>();
        Region region;
        Platform platform;
        while (rs.next()) {
            region = Region.valueOf(rs.getString("REGION"));
            platform = Platform.valueOf(rs.getString("PLATFORM"));
            releases.add(new Release(gameID, region, platform));
        }
        if (releases.size() > 0) {
            return releases;
        } else {
            throw new InstanceNotFoundException("No releases found for GameID " + gameID);
        }

    }
}
