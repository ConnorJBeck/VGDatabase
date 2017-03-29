import org.junit.*;
import com.vgdatabase304.Structures.*;
import com.vgdatabase304.Utils.*;
import com.vgdatabase304.Adaptors.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ReleaseSpec {

    private static Statement stmt;
    private static Release release;
    private static Game game;
    private static AdminUser admin;

    @BeforeClass
    public static void runBeforeAll() throws SQLException {
        TestUtils.initDBForTests();
        admin = TestUtils.addTestAdmin();
        game = TestUtils.addTestGame(admin);
        release = TestUtils.addTestRelease(game, admin);
        stmt = ConnectionManager.getStatement();
    }

    @AfterClass
    public static void runAfterAll() throws SQLException {
        TestUtils.endTesting();
    }

    @Test
    public void testAddRelease() throws SQLException {
        String sql = "SELECT G.GameID, G.ESRBRating, G.AddedBy, G.Name, R.Region, " +
                "R.Platform, R.ReleaseDate, R.AddedBy " +
                "FROM GAME G INNER JOIN RELEASE R ON G.GAMEID = R.GAMEID " +
                "WHERE G.gameID=" + release.getGame().getGameID() +
                " ORDER BY R.Region ASC";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        assertEquals(release.getGame().getGameID(), rs.getInt(1));
        assertEquals("E",rs.getString(2));
        assertEquals("testAdmin",rs.getString(3));
        assertEquals("Test Game", rs.getString(4));
        assertEquals("NTSC", rs.getString(5));
        assertEquals("Atari 2600", rs.getString(6));
        assertEquals("testAdmin", rs.getString(8));
    }

    @Test
    public void testAddSecondRelease() throws SQLException {
        Date dateNew = new Date(1);
        AdminUser admin = AdminUserAdaptor.addAdminUserToDatabase("testAdmin2", "testAdmin2@gmail.com", "hunter3");
        Release newRelease = ReleaseAdaptor.addReleaseToDatabase(release.getGame(), Region.PAL, Platform.Nintendo64, admin, dateNew);
        String sql = "SELECT G.GameID, G.ESRBRating, G.AddedBy, G.Name, R.Region, " +
                "R.Platform, R.ReleaseDate, R.AddedBy " +
                "FROM GAME G INNER JOIN RELEASE R ON G.GAMEID = R.GAMEID " +
                "WHERE G.gameID=" + release.getGame().getGameID() +
                " ORDER BY R.Region ASC";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        assertEquals(release.getGame().getGameID(), rs.getInt(1));
        assertEquals("E",rs.getString(2));
        assertEquals("testAdmin",rs.getString(3));
        assertEquals("Test Game", rs.getString(4));
        assertEquals("NTSC", rs.getString(5));
        assertEquals("Atari 2600", rs.getString(6));
        assertEquals("testAdmin", rs.getString(8));
        rs.next();
        assertEquals(release.getGame().getGameID(), rs.getInt(1));
        assertEquals("E",rs.getString(2));
        assertEquals("testAdmin",rs.getString(3));
        assertEquals("Test Game", rs.getString(4));
        assertEquals("PAL", rs.getString(5));
        assertEquals("Nintendo 64", rs.getString(6));
        assertEquals("testAdmin2", rs.getString(8));
        ReleaseAdaptor.removeReleaseFromDB(newRelease);
    }

    @Test
    public void testRemoveRelease() throws SQLException {
        ReleaseAdaptor.removeReleaseFromDB(release);

        String sql = "SELECT G.GameID, G.ESRBRating, G.AddedBy, G.Name, R.Region, " +
                "R.Platform, R.ReleaseDate, R.AddedBy " +
                "FROM GAME G INNER JOIN RELEASE R ON G.GAMEID = R.GAMEID " +
                "WHERE G.gameID=" + release.getGame().getGameID() +
                " ORDER BY R.Region ASC";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        assertEquals(release.getGame().getGameID(), rs.getInt(1));
        assertEquals("E",rs.getString(2));
        assertEquals("testAdmin",rs.getString(3));
        assertEquals("Test Game", rs.getString(4));
        assertEquals("NTSC", rs.getString(5));
        assertEquals("Atari 2600", rs.getString(6));
        assertEquals("testAdmin", rs.getString(8));
        assertFalse(rs.next());
        TestUtils.addTestRelease(game, admin);
    }


}
