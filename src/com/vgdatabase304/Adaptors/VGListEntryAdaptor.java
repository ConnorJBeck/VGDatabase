package com.vgdatabase304.Adaptors;

import com.vgdatabase304.Exceptions.*;
import com.vgdatabase304.Structures.*;
import com.vgdatabase304.Utils.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
