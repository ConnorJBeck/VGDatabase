
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

public class VGList {

    private static AtomicInteger nextListId = new AtomicInteger();
    private int listID;
    private String listName;
    private RegisteredUser userName;
    private Statement stmt;

    public VGList(int listID, String listName, RegisteredUser userName, Statement stmt) throws SQLException {
        this.listID = nextListId.incrementAndGet();
        this.listName = listName;
        this.userName = userName;
        this.stmt = ConnectionManager.getStatement();
    }

    public int getListID() {
        return listID;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public RegisteredUser getUserName() {
        return userName;
    }

    public void setUserName(RegisteredUser userName) {
        this.userName = userName;
    }
}
