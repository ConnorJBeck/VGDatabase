package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.*;
import com.vgdatabase304.Structures.*;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class UserSelfProfile extends JFrame {
    private JLabel username;
    private JLabel email;
    private JButton searchButton;
    private JLabel reviewLabel;
    private JLabel listPanel;
    private JList listOfVGLists;
    private JList listOfReviews;
    private JTextArea userName;
    private JTextArea eMail;
    private JPanel ReviewPanel;
    private JPanel ListPanel;
    private JTextField newListName;
    private JButton createList;
    public JPanel mainPanel;
    private JScrollPane listsScrollPane;
    private JScrollPane reviewsScrollPane;
    private JButton deleteListButton;
    private JButton deleteReviewButton;
    private JFrame parent;
    private DefaultListModel vgList;
    private DefaultListModel reviewListModel;
    private JTextArea reviewRankingTextArea;
    private JButton changeEmailButton;
    private JTextField newEmail;
    private JLabel newEmailLabel;

    public UserSelfProfile(final RegisteredUser user) {
        setAccount(user);
        parent = new JFrame("Profile");
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Search(user);
            }
        });

        changeEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RegisteredUserAdaptor.setEmail(user, newEmail.getText());
                    eMail.setText(RegisteredUserAdaptor.getEmail(user));
                    newEmail.setText(null);
                    System.out.println("Changed email for " + user.getUsername() + " to " + newEmail.getText());
                } catch (SQLException e1) {
                    System.out.println("Could not chage email for: " + user.getUsername());
                }
            }
        });

        parent.setVisible(true);
        parent.setContentPane(mainPanel);
        parent.pack();
    }

    public void setAccount(final RegisteredUser user) {
        this.userName.setText(user.getUsername());
        try {
            eMail.setText(RegisteredUserAdaptor.getEmail(user));
        } catch (SQLException e) {
            eMail.setText("Unable to retrieve email from database");
        }

        try {
            listOfVGLists.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            listOfVGLists.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        System.out.println("list clicked");
                        VGList vgList = (VGList) listOfVGLists.getSelectedValue();
                        new ListGUI(vgList, user);
                    }
                }
            });
            List<VGList> VGListList = VGListAdaptor.getAllListsByUser(user);
            vgList = new DefaultListModel();
            listOfVGLists.setModel(vgList);
            listsScrollPane.setViewportView(listOfVGLists);
            for (VGList listObject : VGListList) {
                System.out.println(listObject.getListID());
                vgList.addElement(listObject);
            }
            listOfVGLists.setCellRenderer(new ListRenderer());
        } catch (SQLException err) {
            System.out.println("List Of Lists Error: No Lists Found");
        }

        try {
            listOfReviews.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            listOfReviews.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        System.out.println("review clicked");
                        Review review = (Review) listOfReviews.getSelectedValue();
                        new ReviewGUI(review, user);
                    }
                }
            });
            List<Review> reviewList = ReviewAdaptor.getAllReviewsByUser(user);
            reviewListModel = new DefaultListModel();
            listOfReviews.setModel(reviewListModel);
            listOfReviews.setCellRenderer(new ReviewRenderer());
            reviewsScrollPane.setViewportView(listOfReviews);
            for (Review reviewObject : reviewList) {
                System.out.println(reviewObject.getReviewID());
                reviewListModel.addElement(reviewObject);
            }
        } catch (SQLException err) {
            System.out.println("List Of Reviews Error: No Reviews Found");
        }

        createList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newListStr = newListName.getText();
                if (newListStr.length() == 0) {
                    JOptionPane.showMessageDialog(null, "You must enter a name for your new list.");
                } else {
                    try {
                        VGList newVGList = VGListAdaptor.addListToDatabase(newListStr, user);
                        vgList.addElement(newVGList);
                        newListName.setText("");
                    } catch (SQLException err) {
                        System.out.println(err.getMessage());
                    }
                }
            }
        });

        deleteListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object badVGList = listOfVGLists.getSelectedValue();
                vgList.removeElement(badVGList);
            }
        });

        deleteReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object review = listOfReviews.getSelectedValue();
                reviewListModel.removeElement(review);
            }
        });

        try {
            Ranking ranking = RegisteredUserAdaptor.getUsersReviewRanking(user);
            reviewRankingTextArea.setText("Number of Reviews You've Posted: " + ranking.getPersonalTotal() + "\n" +
                    "Total Number of Review Posted: " + ranking.getOverallTotal() + "\n" +
                    "Your Ranking: " + ranking.getRank());
        } catch (SQLException err) {
            System.out.println("Could not get users review ranking: " + err.getMessage());
        }


        /*
        try {
            password.setTest(RegisteredUserAdaptor.getPassword(user));
        } catch (SQLException e) {
            password.setText("Unable to retrieve password from database");
        }
        */
    }


    private void createUIComponents() {
        //search user by name;
        //search game by platform;
        //search game by release year;
        //search game by rating;
        //search game by VGtag
    }
}
