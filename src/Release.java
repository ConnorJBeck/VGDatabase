import java.sql.SQLException;
import java.sql.Statement;

public class Release {

    private int gameID;
    private Region region;
    private Platform platform;
    private static Statement stmt;

    public Release(int gameID, Region region, Platform platform) throws SQLException {
        this.gameID = gameID;
        this.region = region;
        this.platform = platform;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
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
}
