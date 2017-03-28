import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class VGListEntry {

    private VGList list;
    private Game game;

    public VGListEntry(VGList list, Game game) throws SQLException {
        this.list = list;
        this.game = game;
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
}
