import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by Connor on 3/27/2017.
 */
public class TestUtils {

    public static void initDBForTests() throws SQLException {
        ConnectionManager.initConnection("ora_z3n0b", "a53123155");

        Database.deleteDatabase();
        Database.initDatabase();
    }

    public static void endTesting() throws SQLException {
        ConnectionManager.closeConnection();
    }

    public static RegisteredUser addTestRegisteredUser() throws SQLException {
        return RegisteredUser.addRegisteredUserToDatabase("testAdmin", "testAdmin@gmail.com", "hunter2");
    }

    public static AdminUser addTestAdmin() throws SQLException {
        RegisteredUser adminRU = addTestRegisteredUser();
        return AdminUser.addAdminUserToDatabase(adminRU);
    }

    public static Game addTestGame() throws SQLException {
        AdminUser admin = addTestAdmin();
        return GameAdaptor.addGameToDatabase(admin, ESRBRating.E, "Test Game", Region.NTSC, Platform.Atari2600, new Date(0));
    }

    public static Release addTestRelease() throws SQLException {
        AdminUser admin = addTestAdmin();
        Game game = addTestGame();
        return ReleaseAdaptor.addReleaseToDatabase(game, Region.PAL, Platform.Xbox, admin, new Date(0));
    }

    public static VGList addTestList() throws SQLException {
        RegisteredUser registeredUser = addTestRegisteredUser();
        return VGListAdaptor.addListToDatabase("test list", registeredUser);
    }


    public static VGListEntry addTestListEntry() throws SQLException {
        VGList vgList = addTestList();
        Game game = addTestGame();
        return VGListEntryAdaptor.addListEntryToDatabase();
    }
}
