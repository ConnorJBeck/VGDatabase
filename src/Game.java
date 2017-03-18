import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Game {

    private String addedBy;
    private int gameID;
    private ESRBRating esrbRating;
    private String name;

    private Statement stmt;

    Game() throws SQLException {
        stmt = ConnectionManager.getStatement();
    }


    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) throws SQLException {
        this.addedBy = addedBy;
        stmt.executeUpdate("UPDATE GAME " +
                "SET addedby = " + addedBy +
                " WHERE gameID = " + gameID
        );
    }

    public int getGameID() {
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
}
