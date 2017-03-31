package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.GameAdaptor;
import com.vgdatabase304.Structures.Game;
import com.vgdatabase304.Structures.RegisteredUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    public Search(RegisteredUser user) {
        f = new JFrame("Search");
        f.setContentPane(panel1);
        f.pack();

        submitSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String query = searchBar.getText();
                String searchFilter = (String) searchBy.getSelectedItem();
                List<Game> resultGameList = new ArrayList<Game>();

                if (searchFilter == "User") {

                }
                else if (searchFilter == "Game Title") {
                    try {
                        resultGameList = GameAdaptor.searchGameByTitle(query);
                    } catch (SQLException err){
                        System.out.println("Error: " + err.getMessage());
                    }
                }
                else if (searchFilter == "Game Platform"){

                }
                else if (searchFilter == "Game Year"){
                    // int
                }
                else if (searchFilter == "Tag"){

                }
                else if (searchFilter == "Rating"){
                    // double
                }

                try {
                    new SearchResults(new JFrame("SearchResults"), resultGameList);
                }catch (SQLException err){
                    System.out.println("Error: " + err.getMessage());
                }

                f.dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
