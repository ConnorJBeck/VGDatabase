package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.GameAdaptor;
import com.vgdatabase304.Adaptors.VGListAdaptor;
import com.vgdatabase304.Adaptors.VGListEntryAdaptor;
import com.vgdatabase304.Structures.CellRenderer;
import com.vgdatabase304.Structures.Game;
import com.vgdatabase304.Structures.VGList;

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
    private JTextField textField1;
    private JTextField textField2;
    private JScrollPane gameScrollPane;
    private JFrame parent;

    public ListGUI(JFrame parent, VGList list) {
        this.parent = parent;
        parent.setContentPane(panel1);
        parent.setVisible(true);
        parent.pack();
        setupListGUI(list);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.dispose();
            }
        });
    }

    private void setupListGUI (VGList list) {
        try {
            List<Game> listOfGames = VGListEntryAdaptor.getAllGamesInList(list);
            listGames.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            listGames.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    System.out.println("value changed");
                    try {
                        Game game = new Game((int) listGames.getSelectedValue());
                        JFrame frame = new JFrame((GameAdaptor.getName(game)));
                        new GameGUI(frame, game);
                        parent.dispose();
                    } catch (SQLException err) {
                        System.out.println(err.getMessage());
                    }
                }
            });
            DefaultListModel vgList = new DefaultListModel();
            listGames.setModel(vgList);
            gameScrollPane.setViewportView(listGames);
            for (Game gameObject : listOfGames) {
                System.out.println(gameObject.getGameID());
                vgList.addElement(gameObject.getGameID());
            }
            listGames.setCellRenderer(new CellRenderer());
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
}
