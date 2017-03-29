import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.vgdatabase304.Structures.*;
import com.vgdatabase304.Utils.*;
import com.vgdatabase304.Adaptors.*;
import com.vgdatabase304.Exceptions.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;


public class ReleaseSpec {

    private static Statement stmt;
    private static Release release;

    @BeforeClass
    public static void runBefore() throws SQLException {
        TestUtils.initDBForTests();
        release = TestUtils.addTestRelease();
        stmt = ConnectionManager.getStatement();
    }

    @AfterClass
    public static void runAfter() throws SQLException {
        TestUtils.endTesting();
    }

    @Test
    public void testAddRelease() throws SQLException {
        String sql = "SELECT G.GameID, G.ESRBRating, G.AddedBy, G.Name, R.Region, " +
                "R.Platform, R.ReleaseDate, R.AddedBy " +
                "FROM GAME G INNER JOIN RELEASE R ON G.GAMEID = R.GAMEID " +
                "WHERE G.gameID=" + release.getGame() +
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
        ReleaseAdaptor.addReleaseToDatabase(release.getGame(), Region.PAL, Platform.Nintendo64, admin, dateNew);
        String sql = "SELECT G.GameID, G.ESRBRating, G.AddedBy, G.Name, R.Region, " +
                "R.Platform, R.ReleaseDate, R.AddedBy " +
                "FROM GAME G INNER JOIN RELEASE R ON G.GAMEID = R.GAMEID " +
                "WHERE G.gameID=" + release.getGame() +
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
        assertEquals("testAdmin", rs.getString(8));
    }


}
