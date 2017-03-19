import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {

    private static AtomicInteger nextGameId = new AtomicInteger();
    private AdminUser addedBy;
    private int gameID;
    private ESRBRating esrbRating;
    private String name;
    private List<Release> releases;

    private Statement stmt;

    public Game(AdminUser addedBy, ESRBRating esrbRating, String name, Region region, Platform platform, Date releaseDate) throws SQLException {
        gameID = nextGameId.incrementAndGet();
        this.addedBy = addedBy;
        this.esrbRating = esrbRating;
        this.name = name;
        releases = new ArrayList<>();
        releases.add(new Release(gameID, region, platform, addedBy, releaseDate));

        stmt = ConnectionManager.getStatement();

    }

    public void addGameToDatabase() throws SQLException{
        if (releases.size() > 0) {
            String sql = "INSERT INTO Game (gameID, addedBy, ESRBRating, name) VALUES (" +
                    gameID + ", '" +
                    addedBy.getUsername() + "', '" +
                    esrbRating.getShortName() + "', '" +
                    name + "')";
            stmt.executeUpdate(sql);
            for (Release release : releases) {
                sql = "INSERT INTO Release (gameID, region, platform, addedBy, releaseDate) VALUES (" +
                        gameID + ", '" +
                        release.getRegion().name() + "', '" +
                        release.getPlatform().getName() + "', '" +
                        release.getAddedBy().getUsername() + "', TO_DATE('" +
                        release.getReleaseDate().toString() + "','yyyy-mm-dd'))";
                stmt.executeUpdate(sql);
            }

        }

    }


    public AdminUser getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(AdminUser addedBy) throws SQLException {
        this.addedBy = addedBy;
        stmt.executeUpdate("UPDATE GAME " +
                "SET addedby = " + addedBy.getUsername() +
                " WHERE gameID = " + gameID
        );
    }

    public int getGameID() throws SQLException {
        return gameID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws SQLException {
        this.name = name;
        stmt.executeUpdate("UPDATE GAME " +
                "SET name = " + name +
                " WHERE gameID = " + gameID
        );
    }

    public ESRBRating getEsrbRating() {
        return esrbRating;
    }

    public void setESRBRating(ESRBRating esrbRating) throws SQLException {
        this.esrbRating = esrbRating;
        stmt.executeUpdate("UPDATE GAME " +
                "SET ESRBRating = " + esrbRating.getShortName() +
                " WHERE gameID = " + gameID
        );
    }

    // Note, not currently working.
    // ToDo: create specialized exception for ESRBRating? Maybe find better built-in?
    public void setESRBRating(String rating) throws SQLException {
        try {
            this.esrbRating = ESRBRating.getRatingFromString(rating);
            stmt.executeUpdate("UPDATE GAME " +
                    "SET ESRBRating = " + rating +
                    " WHERE gameID = " + gameID
            );
        } catch (Exception err) {
            throw new SQLException("Unable to set ESRB Rating: Invalid rating.");
        }
    }

    public void addRelease(Release release) throws SQLException {
        releases.add(release);
        stmt.executeUpdate("INSERT INTO Release " +
                "(gameID, region, platform, addedBy, releaseDate) VALUES (" +
                gameID + ", '" +
                release.getRegion() + "', '" +
                release.getPlatform().getName() + "', '" +
                release.getAddedBy().getUsername() + "', TO_DATE('" +
                release.getReleaseDate().toString() + "','yyyy-mm-dd'))"
        );
    }

    public void deleteRelease(Release release) throws SQLException {
        releases.remove(release);
        stmt.executeUpdate("DELETE FROM Release WHERE " +
                "gameID=" + gameID + " AND " +
                "region='" + release.getRegion() + "' AND " +
                "platform='" + release.getPlatform().name() + "'"
        );
    }

    public List<Release> getReleases() {
        return releases;
    }
}
