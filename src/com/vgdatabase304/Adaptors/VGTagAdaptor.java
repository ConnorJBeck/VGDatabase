package com.vgdatabase304.Adaptors;

import com.vgdatabase304.Exceptions.InstanceNotFoundException;
import com.vgdatabase304.Structures.AdminUser;
import com.vgdatabase304.Structures.VGTag;
import com.vgdatabase304.Utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VGTagAdaptor {

    private static Statement stmt;
    private static ResultSet rs;


    public static VGTag addTagToDatabase(String tagName, AdminUser admin) throws SQLException {
        stmt = ConnectionManager.getStatement();

        String sql = "INSERT INTO TAG VALUES ('" +
                tagName + "', '" +
                admin.getUsername() + "')";
        stmt.executeUpdate(sql);
        return new VGTag(tagName);
    }

    public static void removeTagFromDatabase(String tag) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "DELETE FROM TAG WHERE " +
                "NAME='" + tag + "'";
        stmt.executeUpdate(sql);
    }

    public static void setTagName(VGTag tag, String tagName) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE TAG " +
                "SET NAME='" + tagName +
                "' WHERE NAME='" + tag.getTagName() + "'"
        );
        tag.setTagName(tagName);
    }

    public static AdminUser getAddedBy(VGTag tag) throws SQLException {
        stmt = ConnectionManager.getStatement();
        String sql = "SELECT ADDEDBY FROM TAG WHERE NAME='" + tag.getTagName() + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return new AdminUser(rs.getString(1));
        } else {
            throw new InstanceNotFoundException("No record found in TAG for " + tag.getTagName());
        }
    }

    public static void setAddedBy(VGTag tag, AdminUser admin) throws SQLException {
        stmt = ConnectionManager.getStatement();
        stmt.executeUpdate("UPDATE TAG " +
                "SET ADDEDBY='" + admin.getUsername() +
                "' WHERE NAME='" + tag.getTagName() + "'"
        );
    }

    public static List<VGTag> getAllTags() throws SQLException {
      stmt = ConnectionManager.getStatement();
      List<VGTag> listOfVGTags = new ArrayList<>();
      String sql = "SELECT NAME FROM TAG";
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
          listOfVGTags.add(new VGTag(rs.getString("NAME")));
      }
      return listOfVGTags;
    }
}
