import Exceptions.InstanceNotFoundException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReleaseAdaptor {

    private static Statement stmt;
    private static ResultSet rs;

    public static void setRegion(Release release, Region region) throws SQLException {
        stmt.executeUpdate("UPDATE Release " +
                "SET region='" + region.name() +
                "' WHERE gameID=" + release.getGameID() +
                " AND region='" + release.getRegion() +
                "' AND platform='" + release.getPlatform().getName() + "'"
        );
        release.setRegion(region);
    }

    public void setPlatform(Release release, Platform platform) throws SQLException {
        stmt.executeUpdate("UPDATE Release " +
                "SET platform='" + platform.getName() +
                "' WHERE gameID=" + release.getGameID() +
                " AND region='" + release.getRegion().name() +
                "' AND platform='" + release.getPlatform().getName() + "'"
        );
        release.setPlatform(platform);
    }


    public AdminUser getAddedBy() throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT AddedBy FROM GAME WHERE GAMEID=" + gameID;
        rs = stmt.executeQuery(sql);
        if (rs.first()) {
            return rs.getString(1);
        } else {
            throw new InstanceNotFoundException("No record found in GAME for " + gameID);
        }
    }

    public void setAddedBy(AdminUser addedBy) throws SQLException {
        stmt.executeUpdate("UPDATE Release " +
                "SET ADDEDBY='" + addedBy.getUsername() +
                "' WHERE gameID=" + gameID +
                " AND region='" + region.name() +
                "' AND platform='" + platform.getName() + "'"
        );
        this.addedBy = addedBy;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) throws SQLException {
        stmt.executeUpdate("UPDATE Release " +
                "SET RELEASEDATE=TO_DATE('" + releaseDate.toString() + "','yyyy-mm-dd')" +
                " WHERE gameID=" + gameID +
                " AND region='" + region.name() +
                "' AND platform='" + platform.getName() + "'"
        );
        this.releaseDate = releaseDate;
    }

    public static void AddReleaseToDatabase(Release release, String addedBy, Date releaseDate) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("INSERT INTO Release " +
                "(gameID, region, platform, addedBy, releaseDate) VALUES (" +
                gameID + ", '" +
                region.name() + "', '" +
                platform.getName() + "', '" +
                addedBy.getUsername() + "', TO_DATE('" +
                releaseDate.toString() + "','yyyy-mm-dd'))"
        );
    }

    public void removeReleaseFromDB() throws SQLException {
        stmt.executeUpdate("DELETE FROM Release WHERE " +
                "gameID=" + gameID + " AND " +
                "region='" + region + "' AND " +
                "platform='" + platform.getName() + "'"
        );
    }
}
