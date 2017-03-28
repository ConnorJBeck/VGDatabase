package com.vgdatabase304.Structures;
import com.vgdatabase304.Utils.*;
import com.vgdatabase304.Exceptions.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

public class VGList {

    private int listID;

    public VGList(int listID) throws SQLException {
        this.listID = listID;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }
}
