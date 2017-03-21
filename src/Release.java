import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class Release {

    private int gameID;
    private Region region;
    private Platform platform;
    private AdminUser addedBy;
    private Date releaseDate;
    private Statement stmt;

    public Release(int gameID, Region region, Platform platform, AdminUser addedBy, Date releaseDate) throws SQLException {
        this.gameID = gameID;
        this.region = region;
        this.platform = platform;
        this.addedBy = addedBy;
        this.releaseDate = releaseDate;
        stmt = ConnectionManager.getStatement();
    }

    public Release(int gameID, Region region, Platform platform, AdminUser addedBy) throws SQLException {
        this.gameID = gameID;
        this.region = region;
        this.platform = platform;
        this.addedBy = addedBy;
        this.releaseDate = null;
        stmt = ConnectionManager.getStatement();
    }

    public Release(int gameID, Region region, Platform platform, Date releaseDate) throws SQLException {
        this.gameID = gameID;
        this.region = region;
        this.platform = platform;
        this.addedBy = null;
        this.releaseDate = releaseDate;
        stmt = ConnectionManager.getStatement();
    }

    public Release(int gameID, Region region, Platform platform) throws SQLException {
        this.gameID = gameID;
        this.region = region;
        this.platform = platform;
        this.addedBy = null;
        this.releaseDate = null;
        stmt = ConnectionManager.getStatement();
    }

    public int getGameID() {
        return gameID;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;

    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public AdminUser getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(AdminUser addedBy) {
        this.addedBy = addedBy;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void AddReleaseToDatabase(int gameID) throws SQLException {
        stmt.executeUpdate("INSERT INTO Release " +
                "(gameID, region, platform, addedBy, releaseDate) VALUES (" +
                gameID + ", '" +
                region + "', '" +
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
