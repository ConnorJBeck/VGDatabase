public class Release {

    private int gameID;
    private String region;
    private Platform platform;
    private AdminUser addedBy;
    private String releaseDate;

    public Release(int gameID, String region, Platform platform, AdminUser addedBy, String releaseDate) {
        this.gameID = gameID;
        this.region = region;
        this.platform = platform;
        this.addedBy = addedBy;
        this.releaseDate = releaseDate;
    }

    public Release(int gameID, String region, Platform platform, AdminUser addedBy) {
        this.gameID = gameID;
        this.region = region;
        this.platform = platform;
        this.addedBy = addedBy;
        this.releaseDate = null;
    }

    public Release(int gameID, String region, Platform platform, String releaseDate) {
        this.gameID = gameID;
        this.region = region;
        this.platform = platform;
        this.addedBy = null;
        this.releaseDate = releaseDate;
    }

    public Release(int gameID, String region, Platform platform) {
        this.gameID = gameID;
        this.region = region;
        this.platform = platform;
        this.addedBy = null;
        this.releaseDate = null;
    }
}
