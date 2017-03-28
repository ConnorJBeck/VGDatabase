package com.vgdatabase304.Structures;
import com.vgdatabase304.Utils.*;
import com.vgdatabase304.Exceptions.*;

public class Game {

    private int gameID;

    public Game(int gameID) {
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
