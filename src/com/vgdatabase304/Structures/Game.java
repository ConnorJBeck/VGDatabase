package com.vgdatabase304.Structures;

public class Game extends SearchResult {

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
