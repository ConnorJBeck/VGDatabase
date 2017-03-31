package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.GameAdaptor;
import com.vgdatabase304.Adaptors.VGListAdaptor;
import com.vgdatabase304.Adaptors.VGListEntryAdaptor;
import com.vgdatabase304.Structures.*;
import jdk.nashorn.internal.runtime.ListAdapter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skyline on 2017-03-30.
 */
public class ListGUI {
    private JButton backButton;
    private JPanel panel1;
    private JTextField listName;
    private JList listGames;
    private JTextField highestRatedGameField;
    private JTextField lowestRatedGameField;
    private JScrollPane gameScrollPane;
    private JFrame frame;
    private DefaultListModel vgList;

    public ListGUI(VGList list, RegisteredUser user) {
        try {
            frame = new JFrame(VGListAdaptor.getListName(list));
        } catch (SQLException err) {
            frame = new JFrame("Could not retrieve list");
        }
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.pack();
        setupListGUI(list, user);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    private void setupListGUI (VGList list, RegisteredUser user) {
        try {
            listName.setText(VGListAdaptor.getListName(list));
        }catch (SQLException err) {
            listName.setText("Unknown List");
        }

        try {
            List<Game> listOfGames = VGListEntryAdaptor.getAllGamesInList(list);
            listGames.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            listGames.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    System.out.println("value changed");
                    Game game = new Game((int) listGames.getSelectedValue());
                    new GameGUI(game, user);
                    frame.dispose();
                }
            });
            vgList = new DefaultListModel();
            listGames.setModel(vgList);
            gameScrollPane.setViewportView(listGames);
            for (Game gameObject : listOfGames) {
                System.out.println(gameObject.getGameID());
                vgList.addElement(gameObject);
            }
            listGames.setCellRenderer(new GameRenderer());


        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

        try {
            highestRatedGameField.setText(GameAdaptor.getName(VGListEntryAdaptor.getHighestRatedGame(list)));
        } catch (SQLException err) {
            highestRatedGameField.setText("Could not find highest Rated game");
        }

        try {
            lowestRatedGameField.setText(GameAdaptor.getName(VGListEntryAdaptor.getLowestRatedGame(list)));
        } catch (SQLException err) {
            lowestRatedGameField.setText("Could not find lowest rated game");
        }


    }
}
