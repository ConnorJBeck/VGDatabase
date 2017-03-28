package com.vgdatabase304.Structures;
import com.vgdatabase304.Utils.*;
import com.vgdatabase304.Exceptions.*;
import java.sql.SQLException;
import java.sql.Statement;

public class VGTag {

    private String tagName;
    private AdminUser addedBy;
    private Statement stmt;

    public VGTag(String tagName, AdminUser addedBy) throws SQLException {
        this.tagName = tagName;
        this.addedBy = addedBy;
        this.stmt = ConnectionManager.getStatement();
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public AdminUser getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(AdminUser addedBy) {
        this.addedBy = addedBy;
    }


}
