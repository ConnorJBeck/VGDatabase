package com.vgdatabase304.Forms;

import com.vgdatabase304.Utils.ConnectionManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Skyline on 2017-03-28.
 */
public class Search {
    private JTextField searchBar;
    private JPanel panel1;
    private JComboBox searchBy;
    private JButton submitSearchButton;
    private JButton backButton;
    private JFrame f;

    public Search() {
        f = new JFrame("Search");
        f.setContentPane(panel1);
        f.pack();

        submitSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new SearchResults(new JFrame("SearchResults"));

                String query = searchBar.getText();
                String searchFilter = (String) searchBy.getSelectedItem();
                if (searchFilter == "User") {

                }
                else if (searchFilter == "Game Title") {

                }
                else if (searchFilter == "Game Platform"){

                }
                else if (searchFilter == "Game Year"){

                }
                else if (searchFilter == "Tag"){

                }
                else if (searchFilter == "Rating"){

                }
                else {

                }

                f.dispose();
            }
        });
        f.setVisible(true);
    }

/*    public static void main(String[] args){
        try {
            ConnectionManager.initConnection("ora_s8h0b", "a57723158");
        } catch (SQLException e) {
            System.out.println("Unable to make connection to database (" + e.getMessage() + ")");
        }

        frame = new JFrame("Search");
        frame.setContentPane(new Search().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }*/

    private void createUIComponents() {
        searchBy.addItem("User");
        searchBy.addItem("Game");
    }
}
