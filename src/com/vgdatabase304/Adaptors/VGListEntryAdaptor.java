package com.vgdatabase304.Adaptors;

import com.vgdatabase304.Exceptions.*;
import com.vgdatabase304.Structures.*;
import com.vgdatabase304.Utils.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VGListEntryAdaptor {

    private static Statement stmt;
    private static ResultSet rs;

    public static VGListEntry addListEntryToDatabase(VGList list, Game game, Date dateAdded) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "INSERT INTO LISTENTRIES (LISTID, GAMEID, DATEADDED) VALUES (" +
                list.getListID() + ", '" +
                game.getGameID() + "', TO_DATE('" +
                dateAdded.toString() + "'))";
        stmt.executeUpdate(sql);
        return new VGListEntry(list, game);
    }

    public static void removeListEntryFromDatabase(VGListEntry listEntry) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "DELETE FROM LISTENTRIES WHERE " +
                "LISTID=" + listEntry.getList().getListID() +
                " GAMEID=" + listEntry.getGame().getGameID();
        stmt.executeUpdate(sql);
    }

    public static void setListID(VGListEntry listEntry, VGList list) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE LISTENTRIES " +
                "SET LISTID=" + list.getListID() +
                " WHERE LISTID=" + listEntry.getList().getListID() +
                " AND GAMEID=" + listEntry.getGame().getGameID()
        );
        listEntry.setList(list);
    }

    public static void setGameID(VGListEntry listEntry, Game game) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE LISTENTRIES " +
                "SET GAMEID=" + game.getGameID() +
                " WHERE LISTID=" + listEntry.getList().getListID() +
                " AND GAMEID=" + listEntry.getGame().getGameID()
        );
        listEntry.setGame(game);
    }

    public static Date getDateAdded(VGListEntry listEntry) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT DATEADDED FROM LISTENTRIES WHERE " +
                "LISTID=" + listEntry.getList().getListID() +
                " GAMEID=" + listEntry.getGame().getGameID();
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getDate(1);
        } else {
            throw new InstanceNotFoundException("No record found in LISTENTRY for ListID: " +
                    listEntry.getList().getListID() + " GameID: " + listEntry.getGame().getGameID());
        }
    }

    public static void setDateAdded(VGListEntry listEntry, Date dateAdded) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE LISTENTRIES " +
                "SET DATEADDED=TO_DATE('" + dateAdded.toString() +
                "' WHERE listID=" + listEntry.getList().getListID() +
                " AND gameID=" + listEntry.getGame().getGameID()
        );
    }

    public static List<Game> getAllGamesInList(VGList list) throws SQLException {
        stmt = ConnectionManager.getStatement();
        List<Game> listOfGames = new ArrayList<>();
        rs = stmt.executeQuery("SELECT GAMEID FROM LISTENTRIES WHERE LISTID='" + list.getListID() + "'");
        while (rs.next()) {
            listOfGames.add(new Game(rs.getInt("GAMEID")));
        }
        if (listOfGames.size() > 0) {
            return listOfGames;
        } else {
            throw new InstanceNotFoundException("No games found for list " + list.getListID());
        }
    }

    public static List<VGList> getAllListsByGame(Game game) throws SQLException {
        stmt = ConnectionManager.getStatement();
        List<VGList> listOfVGLists = new ArrayList<>();
        rs = stmt.executeQuery("SELECT LISTID FROM LISTENTRIES WHERE GAMEID='" + game.getGameID() + "'");
        while (rs.next()) {
            listOfVGLists.add(new VGList(rs.getInt("LISTID")));
        }
        if (listOfVGLists.size() > 0) {
            return listOfVGLists;
        } else {
            throw new InstanceNotFoundException("No lists found for game " + game.getGameID());
        }
    }

    public static Game getHighestRatedGame(VGList list) throws SQLException {
        stmt = ConnectionManager.getStatement();

        rs = stmt.executeQuery("SELECT GAMEID, MAX(AVERAGERATING) AS MAXRATING FROM " +
                "(SELECT L.GAMEID, AVERAGERATING " +
                "FROM LISTENTRIES L INNER JOIN " +
                "(SELECT G.GAMEID, AVG(C.RATING) AS AVERAGERATING FROM CREATEREVIEW C INNER JOIN GAME G ON C.GAMEID=G.GAMEID GROUP BY G.GAMEID) " +
                "ON L.GAMEID=GAME.GAMEID " +
                "WHERE LISTID=" + list.getListID() + ")");
        if (!rs.next()) {
            throw new InstanceNotFoundException("No Games or reviews in database");
        }

        return new Game(rs.getInt(1));
    }

    public static Game getLowestRatedGame(VGList list) throws SQLException {
        stmt = ConnectionManager.getStatement();

        rs = stmt.executeQuery("SELECT GAMEID, MIN(AVERAGERATING) AS MAXRATING FROM " +
                "(SELECT L.GAMEID, AVERAGERATING " +
                "FROM LISTENTRIES L INNER JOIN " +
                "(SELECT G.GAMEID, AVG(C.RATING) AS AVERAGERATING FROM CREATEREVIEW C INNER JOIN GAME G ON C.GAMEID=G.GAMEID GROUP BY G.GAMEID) " +
                "ON L.GAMEID=GAME.GAMEID " +
                "WHERE LISTID=" + list.getListID() + ")");
        if (!rs.next()) {
            throw new InstanceNotFoundException("No Games or reviews in database");
        }

        return new Game(rs.getInt(1));
    }
}
