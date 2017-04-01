package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.RegisteredUserAdaptor;
import com.vgdatabase304.Adaptors.ReviewAdaptor;
import com.vgdatabase304.Adaptors.VGListAdaptor;
import com.vgdatabase304.Adaptors.VGTagAdaptor;
import com.vgdatabase304.Structures.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jessyang90 on 2017-03-28.
 */
public class AdminAdminProfile {
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JButton searchButton;
    private JLabel reviewLabel;
    private JLabel listPanel;
    private JList userLists;
    private JList userReviews;
    private JTextArea username;
    private JTextArea email;
    private JPanel ReviewPanel;
    private JPanel ListPanel;
    private JPanel TagPanel;
    private JComboBox listofTags;
    private JButton DeleteTag;
    private JTextField newTagName;
    private JButton CreateTag;
    private JPanel mainPanel;
    private JButton changeEmailButton;
    private JTextField newEmail;
    private JTextField newListName;
    private JButton createList;
    private JButton deleteListButton;
    private JTextArea reviewRankingTextArea;
    private JButton deleteReviewButton;
    private JLabel createListLabel;
    private JScrollPane listsScrollPane;
    private JScrollPane reviewsScrollPane;
    private DefaultListModel vgList;
    private DefaultListModel reviewListModel;
    private JButton addAGameButton;
    private JFrame f;
    private List<VGTag> tags;

    public AdminAdminProfile(final RegisteredUser user) {
        f = new JFrame("Admin");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display username and email
        username.setText(user.getUsername());
        try {
            email.append(RegisteredUserAdaptor.getEmail(user));
        } catch (SQLException e) {
            System.out.println("Could not get admin user email");
        }

        //Get all tags for dropdown
        populateTags();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Search(user);
            }
        });

        CreateTag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tag = newTagName.getText();
//                try {
//                    VGTagAdaptor.addTagToDatabase(tag, user);
//                } catch (SQLException e1) {
//                    System.out.println("Admin user could not add tag");
//                }
                newTagName.setText(null);
                deleteAllTags();
                populateTags();
                System.out.println("New Tag: " + tag);
            }
        });

        DeleteTag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTag = (String) listofTags.getSelectedItem();
                System.out.println("Selected Tag: " + selectedTag);
                try {
                    VGTagAdaptor.removeTagFromDatabase(selectedTag);
                    System.out.println("Successful removal of tag: " + selectedTag);
                } catch (SQLException e1) {
                    System.out.println("Unsuccessful removal of tag: " + selectedTag);
                }
                deleteAllTags();
                populateTags();
            }
        });

        changeEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RegisteredUserAdaptor.setEmail(user, newEmail.getText());
                    email.setText(RegisteredUserAdaptor.getEmail(user));
                    newEmail.setText(null);
                    System.out.println("Changed email for " + user.getUsername() + " to " + newEmail.getText());
                } catch (SQLException e1) {
                    System.out.println("Could not chage email for: " + user.getUsername());
                }
            }
        });

        addAGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateGame(user);
            }
        });

        f.setVisible(true);
        f.setContentPane(mainPanel);
        f.pack();

        try {
            vgList = new DefaultListModel();
            userLists.setCellRenderer(new ListRenderer());
            userLists.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            userLists.setModel(vgList);
            listsScrollPane.setViewportView(userLists);
            userLists.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        System.out.println("list clicked");
                        VGList vgList = (VGList) userLists.getSelectedValue();
                        new ListGUI(vgList, user);
                    }
                }
            });

            List<VGList> VGListList = VGListAdaptor.getAllListsByUser(user);
            for (VGList listObject : VGListList) {
                System.out.println(listObject.getListID());
                vgList.addElement(listObject);
            }
        } catch (SQLException err) {
            System.out.println("List Of Lists Error: No Lists Found");
        }

        try {
            reviewListModel = new DefaultListModel();
            userReviews.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            userReviews.setModel(reviewListModel);
            userReviews.setCellRenderer(new ReviewRenderer());
            reviewsScrollPane.setViewportView(userReviews);
            userReviews.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        System.out.println("review clicked");
                        Review review = (Review) userReviews.getSelectedValue();
                        new ReviewGUI(review, user);
                    }
                }
            });

            List<Review> reviewList = ReviewAdaptor.getAllReviewsByUser(user);
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
                Object badVGList = userLists.getSelectedValue();

                try {
                    VGListAdaptor.removeListFromDatabase((VGList) badVGList);
                    vgList.removeElement(badVGList);
                } catch (SQLException err) {
                    System.out.println("Could not remove from database: " + err.getMessage());
                }
            }
        });

        deleteReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object review = userReviews.getSelectedValue();

                try {
                    reviewListModel.removeElement(review);
                    ReviewAdaptor.removeReviewFromDatabase((Review) review);
                } catch (SQLException err) {
                    System.out.println("Could not remove from database: " + err.getMessage());
                }
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
    }

    private void populateTags() {
        try {
            tags = VGTagAdaptor.getAllTags();
            for (VGTag tag: tags) {
                listofTags.addItem(tag.getTagName());
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving tags");
        }
    }

    private void deleteAllTags() {
        listofTags.removeAllItems();
    }
}
