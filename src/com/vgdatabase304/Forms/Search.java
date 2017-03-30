package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.RegisteredUserAdaptor;
import com.vgdatabase304.Structures.RegisteredUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Skyline on 2017-03-28.
 */
public class Search {
    private JTextField searchBar;
    private JPanel panel1;
    private JComboBox searchBy;
    private JButton submitSearchButton;
    private JButton backButton;

    public Search() {
        submitSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchBar.getText();
                String searchFilter = (String) searchBy.getSelectedItem();
                if (searchFilter == "User") {

                } else if (searchFilter == "Game") {

                }
            }
        });
    }

    private void createUIComponents() {
        searchBy.addItem("User");
        searchBy.addItem("Game");
    }
}
