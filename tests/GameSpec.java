import org.junit.*;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GameSpec {

    private static AdminUser admin;
    private static Statement stmt;
    private static Database db;
    private static Date date;
    private static RegisteredUser adminRU;
    private static Game game;

    @BeforeClass
    public static void runBefore() throws SQLException {
        ConnectionManager.initConnection("ora_z3n0b", "a53123155");
        stmt = ConnectionManager.getStatement();

        db = new Database();
        db.deleteDatabase();
        db.initDatabase();

        adminRU = RegisteredUser.addRegisteredUserToDatabase("testAdmin", "testAdmin@gmail.com", "hunter2")
        admin = admin.addAdminUserToDatabase(adminRU);

        ResultSet rs = stmt.executeQuery("SELECT userName, email, password " +
                "FROM Admin " +
                "WHERE userName='testAdmin'");
        rs.next();
        assertEquals("testAdmin", rs.getString(1));
        assertEquals("testAdmin@gmail.com",rs.getString(2));
        assertEquals("hunter2",rs.getString(3));

        date = new Date(0);

        game = GameAdaptor.addGameToDatabase(admin, ESRBRating.E, "Test Game", Region.NTSC, Platform.Atari2600, date);

    }

    @AfterClass
    public static void runAfter() throws SQLException {
        db.deleteDatabase();
        ConnectionManager.closeConnection();
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

    @Test
    public void testAddRelease() throws SQLException {
        Date dateNew = new Date(1);
        ReleaseAdaptor.addReleaseToDatabase(game, Region.PAL, Platform.Nintendo64, admin, dateNew);
        String sql = "SELECT G.GameID, G.ESRBRating, G.AddedBy, G.Name, R.Region, " +
                "R.Platform, R.ReleaseDate, R.AddedBy " +
                "FROM GAME G INNER JOIN RELEASE R ON G.GAMEID = R.GAMEID " +
                "WHERE G.gameID=" + game.getGameID() +
                " ORDER BY R.Region ASC";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        assertEquals(game.getGameID(), rs.getInt(1));
        assertEquals("E",rs.getString(2));
        assertEquals("testAdmin",rs.getString(3));
        assertEquals("Test Game", rs.getString(4));
        assertEquals("NTSC", rs.getString(5));
        assertEquals("Atari 2600", rs.getString(6));
        assertEquals("testAdmin", rs.getString(8));
        rs.next();
        assertEquals(game.getGameID(), rs.getInt(1));
        assertEquals("E",rs.getString(2));
        assertEquals("testAdmin",rs.getString(3));
        assertEquals("Test Game", rs.getString(4));
        assertEquals("PAL", rs.getString(5));
        assertEquals("Nintendo 64", rs.getString(6));
        assertEquals("testAdmin", rs.getString(8));
    }


}
