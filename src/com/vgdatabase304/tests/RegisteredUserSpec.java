
import com.vgdatabase304.Adaptors.RegisteredUserAdaptor;
import com.vgdatabase304.Structures.RegisteredUser;
import com.vgdatabase304.Utils.ConnectionManager;
import com.vgdatabase304.Utils.TestUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;


public class RegisteredUserSpec {

    private static Statement stmt;
    private static RegisteredUser user;

    @BeforeClass
    public static void runBefore() throws SQLException {
        TestUtils.initDBForTests();
        try {
            user = TestUtils.addTestRegisteredUser();
        } catch (SQLException e) {

        }
        stmt = ConnectionManager.getStatement();
    }

    @AfterClass
    public static void runAfter() throws SQLException {
        TestUtils.endTesting();
    }

    @Test
    public void testGetRegisteredUserPassword() throws SQLException {
        assertEquals("hunter2",RegisteredUserAdaptor.getPassword(user));
    }
}
