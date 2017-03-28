package com.vgdatabase304.Adaptors;

import com.vgdatabase304.Exceptions.*;
import com.vgdatabase304.Structures.*;
import com.vgdatabase304.Utils.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReleaseAdaptor {

    private static Statement stmt;
    private static ResultSet rs;

    public static Release addReleaseToDatabase(Game game, Region region, Platform platform, AdminUser addedBy, Date releaseDate) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("INSERT INTO Release " +
                "(gameID, region, platform, addedBy, releaseDate) VALUES (" +
                game.getGameID() + ", '" +
                region.name() + "', '" +
                platform.getName() + "', '" +
                addedBy.getUsername() + "', TO_DATE('" +
                releaseDate.toString() + "','yyyy-mm-dd'))"
        );
        return new Release(game, region, platform);
    }

    public static void removeReleaseFromDB(Release release) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("DELETE FROM Release WHERE " +
                "gameID=" + release.getGame() + " AND " +
                "region='" + release.getRegion().name() + "' AND " +
                "platform='" + release.getPlatform().getName() + "'"
        );
    }

    public static void setGameID(Release release, Game game) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE Release " +
                "SET GAMEID=" + game.getGameID() +
                " WHERE gameID=" + release.getGame() +
                " AND region='" + release.getRegion() +
                "' AND platform='" + release.getPlatform().getName() + "'"
        );
        release.setGame(game);
    }

    public static void setRegion(Release release, Region region) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE Release " +
                "SET region='" + region.name() +
                "' WHERE gameID=" + release.getGame() +
                " AND region='" + release.getRegion() +
                "' AND platform='" + release.getPlatform().getName() + "'"
        );
        release.setRegion(region);
    }

    public static void setPlatform(Release release, Platform platform) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE Release " +
                "SET platform='" + platform.getName() +
                "' WHERE gameID=" + release.getGame() +
                " AND region='" + release.getRegion().name() +
                "' AND platform='" + release.getPlatform().getName() + "'"
        );
        release.setPlatform(platform);
    }


    public static AdminUser getAddedBy(Release release) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT AddedBy FROM RELEASE " +
                "WHERE GAMEID=" + release.getGame() +
                " REGION='" + release.getRegion() +
                "' PLATFORM='" + release.getPlatform() + "'";
        rs = stmt.executeQuery(sql);
        if (rs.first()) {
            return new AdminUser(rs.getString("ADDEDBY"));
        } else {
            throw new InstanceNotFoundException("No record found in RELEASE for " + release.getGame());
        }
    }

    public static void setAddedBy(Release release, AdminUser addedBy) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE Release " +
                "SET ADDEDBY='" + addedBy.getUsername() +
                "' WHERE gameID=" + release.getGame() +
                " AND region='" + release.getRegion().name() +
                "' AND platform='" + release.getPlatform().getName() + "'"
        );
    }

    public static Date getReleaseDate(Release release) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT DATE FROM GAME WHERE GAMEID=" + release.getGame();
        rs = stmt.executeQuery(sql);
        if (rs.first()) {
            return rs.getDate("Date");
        } else {
            throw new InstanceNotFoundException("No record found in RELEASE for " + release.getGame());
        }
    }

    public static void setReleaseDate(Release release, Date releaseDate) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE Release " +
                "SET RELEASEDATE=TO_DATE('" + releaseDate.toString() + "','yyyy-mm-dd')" +
                " WHERE gameID=" + release.getGame() +
                " AND region='" + release.getRegion().name() +
                "' AND platform='" + release.getPlatform().getName() + "'"
        );
    }


}
