package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.GameAdaptor;
import com.vgdatabase304.Structures.Game;
import com.vgdatabase304.Structures.ListRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Skyline on 2017-03-29.
 */
public class SearchResults {
    private JPanel panel1;
    private JLabel results;
    private JButton backButton;
    private JList resultList;
    private JFrame f;

    public SearchResults(JFrame parent, List<Game> resultGameList) throws SQLException{


        resultList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        DefaultListModel gameList = new DefaultListModel();
        resultList.setModel(gameList);
        for (Game listObject : resultGameList){
            String gameName = GameAdaptor.getName(listObject);
            System.out.println(gameName);
            System.out.println(listObject.getGameID());
            gameList.addElement(listObject.getGameID());
        }
        resultList.setCellRenderer(new ListRenderer());

        f = parent;
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.pack();
        f.setContentPane(panel1);
        f.setSize(600,600);

    }
}
