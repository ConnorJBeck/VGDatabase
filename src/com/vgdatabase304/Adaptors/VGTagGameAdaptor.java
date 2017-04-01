package com.vgdatabase304.Adaptors;

import com.vgdatabase304.Exceptions.InstanceNotFoundException;
import com.vgdatabase304.Structures.*;
import com.vgdatabase304.Utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VGTagGameAdaptor {

    private static Statement stmt;
    private static ResultSet rs;

    public static VGTagGame addTagGameToDatabase(VGTag tag, Game game, RegisteredUser addedBy) throws SQLException {
        stmt = ConnectionManager.getStatement();

        String sql = "INSERT INTO TAGGAME VALUES ('" +
                tag.getTagName() + "', " +
                game.getGameID() + ", '" +
                addedBy.getUsername() + "')";
        stmt.executeUpdate(sql);
        return new VGTagGame(tag, game, addedBy);
    }

    public static void removeTagGameFromDatabase(VGTagGame tagGame) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "DELETE FROM TAGGAME WHERE " +
                "TAGNAME='" + tagGame.getTag().getTagName() +
                "' AND GAMEID=" + tagGame.getGame().getGameID() +
                " AND USERNAME='" + tagGame.getUser().getUsername() + "'";
        stmt.executeUpdate(sql);
    }

    public static void setTag(VGTagGame tagGame, VGTag tag) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE TAGGAME " +
                "SET TAGNAME='" + tag.getTagName() +
                "' WHERE TAGNAME='" + tagGame.getTag().getTagName() +
                "' AND GAMEID=" + tagGame.getGame().getGameID() +
                " AND USERNAME='" + tagGame.getUser().getUsername() + "'"
        );
        tagGame.setTag(tag);
    }

    public static void setGame(VGTagGame tagGame, Game game) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE TAGGAME " +
                "SET GAMEID='" + game.getGameID() +
                "' WHERE TAGNAME='" + tagGame.getTag().getTagName() +
                "' AND GAMEID=" + tagGame.getGame().getGameID() +
                " AND USERNAME='" + tagGame.getUser().getUsername() + "'"
        );
        tagGame.setGame(game);
    }

    public static void setUser(VGTagGame tagGame, RegisteredUser user) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE TAGGAME " +
                "SET USERNAME='" + user.getUsername() +
                "' WHERE TAGNAME='" + tagGame.getTag().getTagName() +
                "' AND GAMEID=" + tagGame.getGame().getGameID() +
                " AND USERNAME='" + tagGame.getUser().getUsername() + "'"
        );
        tagGame.setUser(user);
    }

    public static List<VGTag> getAttachedTagsToGame(Game game) throws SQLException {
        stmt = ConnectionManager.getStatement();
        List<VGTag> listOfAttachedTags = new ArrayList<>();
        rs = stmt.executeQuery("SELECT TAGNAME FROM TAGGAME WHERE GAMEID='" + game.getGameID() + "'");
        while (rs.next()) {
            listOfAttachedTags.add(new VGTag(rs.getString("TAGNAME")));
        }
        if (listOfAttachedTags.size() > 0) {
            return listOfAttachedTags;
        } else {
            throw new InstanceNotFoundException("No tags found for GAMEID: " + game.getGameID());
        }
    }
}
