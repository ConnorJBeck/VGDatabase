
import org.junit.*;
import com.vgdatabase304.Structures.*;
import com.vgdatabase304.Utils.*;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GameSpec {

    private static Statement stmt;
    private static Game game;

    @BeforeClass
    public static void runBefore() throws SQLException {
        TestUtils.initDBForTests();
        try {
            game = TestUtils.addTestGame();
        } catch (SQLException e) {

        }
        stmt = ConnectionManager.getStatement();
    }

    @AfterClass
    public static void runAfter() throws SQLException {
        TestUtils.endTesting();
    }

    @Test
    public void testAddGameToDatabase() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT G.GameID, G.ESRBRating, G.AddedBy, G.Name, R.Region, " +
                "R.Platform, R.ReleaseDate, R.AddedBy " +
                "FROM Game G INNER JOIN Release R ON G.GAMEID = R.GAMEID " +
                "WHERE G.gameID=" + game.getGameID() +
                " ORDER BY G.GameID ASC");
        rs.next();
        assertEquals(game.getGameID(), rs.getInt(1));
        assertEquals("E",rs.getString(2));
        assertEquals("testAdmin",rs.getString(3));
        assertEquals("Test Game", rs.getString(4));
        assertEquals("NTSC", rs.getString(5));
        assertEquals("Atari 2600", rs.getString(6));
        assertEquals("testAdmin", rs.getString(8));
    }
}
