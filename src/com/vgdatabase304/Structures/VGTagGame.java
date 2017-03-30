package com.vgdatabase304.Structures;
import java.sql.SQLException;

public class VGTagGame {

    private VGTag tag;
    private Game game;
    private RegisteredUser user;

    public VGTagGame(VGTag tagName, Game gameID, RegisteredUser user) {
        this.tag = tagName;
        this.game = gameID;
        this.user = user;
    }

    public VGTag getTag() {
        return tag;
    }

    public void setTag(VGTag tag) {
        this.tag = tag;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public RegisteredUser getUser() {
        return user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }
}
