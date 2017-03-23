import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class VGListEntries {

    private VGList list;
    private Game game;
    private Date dateAdded;
    private Statement stmt;

    public VGListEntries(VGList list, Game game, Date dateAdded) throws SQLException {
        this.list = list;
        this.game = game;
        this.dateAdded = dateAdded;
        this.stmt = ConnectionManager.getStatement();
    }

    public VGList getList() {
        return list;
    }

    public void setList(VGList list) {
        this.list = list;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
