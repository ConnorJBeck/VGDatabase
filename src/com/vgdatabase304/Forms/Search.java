package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.GameAdaptor;
import com.vgdatabase304.Adaptors.RegisteredUserAdaptor;
import com.vgdatabase304.Structures.*;
import com.vgdatabase304.Structures.Game;
import com.vgdatabase304.Structures.RegisteredUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Search {
    private JTextField searchBar;
    private JPanel panel1;
    private JComboBox searchBy;
    private JButton submitSearchButton;
    private JButton backButton;
    private JFrame f;

    public Search(final RegisteredUser currentUser) {
        f = new JFrame("Search");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        submitSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean searchAll = false;
                boolean userFlag = false;
                String query = searchBar.getText();
                String searchFilter = (String) searchBy.getSelectedItem();
                List<Game> resultGameList = new ArrayList<Game>();
                List<RegisteredUser> registeredUserList = new ArrayList<RegisteredUser>();
                List<SearchResult> resultList = new ArrayList<SearchResult>();

                if (query.trim().isEmpty()){
                    searchAll = true;
                }



                if (searchFilter == "User") {
                    try {
                        if(searchAll){
                            registeredUserList = RegisteredUserAdaptor.searchAllUsers();
                        }
                        else {
                            registeredUserList = RegisteredUserAdaptor.searchUserByUserName(query);
                        }
                        userFlag = true;
                    } catch (SQLException err){
                        System.out.println("Error: " + err.getMessage());
                    }
                }
                else if (searchFilter == "Game Title") {
                    try {
                        if (searchAll){
                            resultGameList = GameAdaptor.searchAllGames();
                        }
                        else {
                            resultGameList = GameAdaptor.searchGameByTitle(query);
                        }
                    } catch (SQLException err){
                        System.out.println("Error: " + err.getMessage());
                    }
                }
                else if (searchFilter == "Game Platform"){
                    try {
                        if (searchAll){
                            resultGameList = GameAdaptor.searchAllGames();
                        }
                        else {
                            Platform platform = Platform.valueOf(query.replaceAll("\\s+", ""));
                            resultGameList = GameAdaptor.searchGameByPlatform(platform);
                        }
                    } catch (SQLException err){
                        System.out.println("Error: " + err.getMessage());
                    }
                }
                else if (searchFilter == "Game Year"){
                    // int
                    try {
                        if (searchAll){
                            resultGameList = GameAdaptor.searchAllGames();
                        }
                        else {
                            int year = Integer.parseInt(query);
                            resultGameList = GameAdaptor.searchGameByYear(year);
                        }
                    } catch (SQLException err){
                        System.out.println("Error: " + err.getMessage());
                    }
                }
                else if (searchFilter == "Tag"){
                    try {
                        if (searchAll){
                            resultGameList = GameAdaptor.searchAllGames();
                        }
                        else {
                            VGTag tag = new VGTag(query);
                            resultGameList = GameAdaptor.searchGameByTag(tag);
                        }
                    } catch (SQLException err){
                        System.out.println("Error: " + err.getMessage());
                    }
                }
                else if (searchFilter == "Rating"){
                    // double
                    try {
                        if (searchAll){
                            resultGameList = GameAdaptor.searchAllGames();
                        }
                        else {
                            String[] parts = query.split("_");
                            String comparator = parts[0];
                            Double rating = Double.parseDouble(parts[1]);
                            resultGameList = GameAdaptor.searchGameByRating(comparator, rating);
                        }
                    } catch (SQLException err){
                        System.out.println("Error: " + err.getMessage());
                    }
                }

                    if (userFlag){
                        for (RegisteredUser u : registeredUserList){
                            resultList.add(u);
                        }
                    }
                    else {
                        for (Game game : resultGameList){
                            resultList.add(game);
                        }
                    }
                    new SearchResults(resultList, currentUser);


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
        f.setContentPane(panel1);
        f.pack();
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
