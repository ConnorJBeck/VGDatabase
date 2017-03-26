import java.sql.SQLException;

public class Release {

    private Game game;
    private Region region;
    private Platform platform;

    public Release(Game game, Region region, Platform platform) throws SQLException {
        this.game = game;
        this.region = region;
        this.platform = platform;
    }

    public Game getGameID() {
        return game;
    }

    public void setGameID(Game game) {
        this.game = game;
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
