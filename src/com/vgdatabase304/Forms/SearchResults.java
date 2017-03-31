package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.GameAdaptor;
import com.vgdatabase304.Structures.CellRenderer;
import com.vgdatabase304.Structures.Game;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public SearchResults(List<Game> resultGameList) {
        f = new JFrame("SearchResults");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        resultList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        DefaultListModel gameList = new DefaultListModel();
        resultList.setModel(gameList);
        for (Game listObject : resultGameList){
            String gameName = null;
            try {
                gameName = GameAdaptor.getName(listObject);
            } catch (SQLException e) {
                System.out.println("Game name could not be retrieved");
            }
            System.out.println(gameName);
            System.out.println(listObject.getGameID());
            gameList.addElement(listObject.getGameID());
        }
        resultList.setCellRenderer(new CellRenderer());

        f.setVisible(true);
        f.pack();
        f.setContentPane(panel1);
    }
}
