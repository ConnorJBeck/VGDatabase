import java.sql.Date;

public class Release {

    private int gameID;
    private Region region;
    private Platform platform;
    private AdminUser addedBy;
    private Date releaseDate;

    public Release(int gameID, Region region, Platform platform, AdminUser addedBy, Date releaseDate) {
        this.gameID = gameID;
        this.region = region;
        this.platform = platform;
        this.addedBy = addedBy;
        this.releaseDate = releaseDate;
    }

    public Release(int gameID, Region region, Platform platform, AdminUser addedBy) {
        this.gameID = gameID;
        this.region = region;
        this.platform = platform;
        this.addedBy = addedBy;
        this.releaseDate = null;
    }

    public Release(int gameID, Region region, Platform platform, Date releaseDate) {
        this.gameID = gameID;
        this.region = region;
        this.platform = platform;
        this.addedBy = null;
        this.releaseDate = releaseDate;
    }

    public Release(int gameID, Region region, Platform platform) {
        this.gameID = gameID;
        this.region = region;
        this.platform = platform;
        this.addedBy = null;
        this.releaseDate = null;
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
}
