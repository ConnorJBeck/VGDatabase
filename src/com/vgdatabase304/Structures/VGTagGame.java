package com.vgdatabase304.Structures;
import com.vgdatabase304.Utils.*;
import com.vgdatabase304.Exceptions.*;
import java.sql.SQLException;
import java.sql.Statement;

public class VGTagGame {

    private VGTag tagName;
    private Game gameID;
    private RegisteredUser userName;
    private Statement stmt;

    public VGTagGame(VGTag tagName, Game gameID, RegisteredUser userName) throws SQLException {
        this.tagName = tagName;
        this.gameID = gameID;
        this.userName = userName;
        this.stmt = ConnectionManager.getStatement();
    }

    public VGTag getTagName() {
        return tagName;
    }

    public void setTagName(VGTag tagName) {
        this.tagName = tagName;
    }

    public Game getGameID() {
        return gameID;
    }

    public void setGameID(Game gameID) {
        this.gameID = gameID;
    }

    public RegisteredUser getUserName() {
        return userName;
    }

    public void setUserName(RegisteredUser userName) {
        this.userName = userName;
    }
}
