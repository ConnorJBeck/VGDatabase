package com.vgdatabase304.Structures;

import com.vgdatabase304.Adaptors.GameAdaptor;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class SearchResultRenderer extends CellRenderer implements ListCellRenderer<Object> {
        boolean isGame = false;

    public void setGameFlagOn (){
        this.isGame = true;
    }
    // This is the only method defined by ListCellRenderer.
    // We just reconfigure the JLabel each time we're called.
    @Override
    public Component getListCellRendererComponent(
            JList<?> list,           // the list
            Object value,            // value to display
            int index,               // cell index
            boolean isSelected,      // is the cell selected
            boolean cellHasFocus)   // does the cell have focus
    {
        String s;
        try {
            if(isGame) {
                s = GameAdaptor.getName((Game) value);
            }else{
                RegisteredUser newValue = (RegisteredUser) value;
                s = newValue.getUsername();
            }
        } catch (SQLException err) {
            s = "Game could not be found";
        }

        return super.setVariables(list, s, index, isSelected, cellHasFocus);
    }
}
