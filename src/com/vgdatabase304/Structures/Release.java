package com.vgdatabase304.Structures;

import java.sql.SQLException;
import com.vgdatabase304.Exceptions.*;
import com.vgdatabase304.Utils.*;

public class Release {

    private Game game;
    private Region region;
    private Platform platform;

    public Release(Game game, Region region, Platform platform) throws SQLException {
        this.game = game;
        this.region = region;
        this.platform = platform;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
