package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.*;
import com.vgdatabase304.Structures.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;


public class GameGUI {
    private JButton backButton;
    private JPanel mainPanel;
    private JTextField gameName;
    private JComboBox listOfTags;
    private JList releaseList;
    private JLabel releaseListLabel;
    private JButton attachTagToGame;
    private JButton addReview;
    private JLabel ratingLabel;
    private JScrollPane releaseScrollPane;
    private JLabel reviewsLabel;
    private JList reviewsList;
    private JScrollPane reviewScrollPane;
    private JComboBox listOfAttachedTags;
    private JLabel currentTagsLabel;
    private JButton deleteThisReleaseButton;
    private JTextArea ratingTextArea;
    private JFrame f;
    private List<VGTag> tags;
    private List<VGTag> attachedTags;
    private DefaultListModel releaseListModel;
    private DefaultListModel reviewListModel;

    public GameGUI(final Game game, final RegisteredUser currentUser) {
        f = new JFrame("GameGUI");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Set name
        setName(game);

        //Populate list of tags that can be added
        populateListOfTags();

        populateListAttachedTags(game);

        try {
            if (AdminUserAdaptor.isAdmin(currentUser)) {
                deleteThisReleaseButton.setEnabled(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        attachTagToGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    VGTag tagToBeAdded = new VGTag((String) listOfTags.getSelectedItem());
                    VGTagGameAdaptor.addTagGameToDatabase(tagToBeAdded, game, currentUser);
                    deleteAllAttachedTags();
                    populateListAttachedTags(game);
                } catch (SQLException err) {
                    System.out.println("Tag not attached");
                }

            }
        });

        addReview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateReview(game, currentUser);
            }
        });

        deleteThisReleaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Release selected = (Release) releaseList.getSelectedValue();
                try {
                    ReleaseAdaptor.removeReleaseFromDB(selected);
                    releaseListModel.removeElement(releaseList.getSelectedValue());
                } catch (SQLException e1) {
                    System.out.println("Could not remove release");
                }
            }
        });

        try {
            ratingTextArea.setText(Double.toString(GameAdaptor.getGameRating(game)));
        } catch (SQLException err) {
            ratingTextArea.setText("Could not retrieve game");
            System.out.println(err.getMessage());
        }

        try {
            releaseList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            List<Release> listOfReleases = GameAdaptor.getReleases(game);
            releaseListModel = new DefaultListModel();
            releaseList.setModel(releaseListModel);
            releaseList.setCellRenderer(new ReleaseRenderer());
            releaseScrollPane.setViewportView(releaseList);
            for (Release releaseObject : listOfReleases) {
                releaseListModel.addElement(releaseObject);
            }
        } catch (SQLException err) {
            System.out.println("List Of Reviews Error: No Reviews Found");
        }

        try {
            reviewsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            reviewsList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        System.out.println("review clicked");
                        Review review = (Review) reviewsList.getSelectedValue();
                        new ReviewGUI(review, currentUser);
                    }
                }
            });
            List<Review> reviewList = ReviewAdaptor.getAllReviewsByUser(currentUser);
            reviewListModel = new DefaultListModel();
            reviewsList.setModel(reviewListModel);
            reviewsList.setCellRenderer(new ReviewRenderer());
            reviewScrollPane.setViewportView(reviewsList);
            for (Review reviewObject : reviewList) {
                System.out.println(reviewObject.getReviewID());
                reviewListModel.addElement(reviewObject);
            }
        } catch (SQLException err) {
            System.out.println("List Of Reviews Error: No Reviews Found");
        }

        f.setVisible(true);
        f.setContentPane(mainPanel);
        f.pack();
    }

    private void populateListAttachedTags(Game game) {
        try {
            attachedTags = VGTagGameAdaptor.getAttachedTagsToGame(game);
            for (VGTag tag: attachedTags) {
                listOfAttachedTags.addItem(tag.getTagName());
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving attached tags");
        }
    }

    private void setName(Game game) {
        try {
            gameName.setText(GameAdaptor.getName(game));
        } catch (SQLException e) {
            System.out.println("Game name cannot be set");
        }
    }

    private void populateListOfTags() {
        try {
            tags = VGTagAdaptor.getAllTags();
            for (VGTag tag: tags) {
                listOfTags.addItem(tag.getTagName());
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving tags");
        }
    }

    private void deleteAllAttachedTags() {
        listOfAttachedTags.removeAllItems();
    }
}
