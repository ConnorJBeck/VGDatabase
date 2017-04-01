package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.RegisteredUserAdaptor;
import com.vgdatabase304.Adaptors.ReviewAdaptor;
import com.vgdatabase304.Adaptors.VGListAdaptor;
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
public class UserUserProfile {
    private JLabel username;
    private JLabel reviewLabel;
    private JLabel listPanel;
    private JList userLists;
    private JList userReviews;
    private JTextArea userName;
    private JPanel ReivewPanel;
    private JPanel ListPanel;
    private JTextArea reviewRankingTextArea;
    private JPanel mainPanel;
    private JScrollPane listsScrollPane;
    private JScrollPane reviewsScrollPane;
    private JFrame f;
    private DefaultListModel reviewListModel;
    private DefaultListModel vgList;

    public UserUserProfile(RegisteredUser user, final RegisteredUser currentUser) {
        setAccount(user);
        f = new JFrame("Profile");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        f.setVisible(true);
        f.setContentPane(mainPanel);
        f.pack();
    }

    public void setAccount(final RegisteredUser user) {
        this.userName.setText(user.getUsername());

        try {
            vgList = new DefaultListModel();
            userLists.setModel(vgList);
            listsScrollPane.setViewportView(userLists);
            userLists.setCellRenderer(new ListRenderer());
            userLists.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
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
            userReviews.setModel(reviewListModel);
            userReviews.setCellRenderer(new ReviewRenderer());
            reviewsScrollPane.setViewportView(userReviews);
            userReviews.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
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

        try {
            Ranking ranking = RegisteredUserAdaptor.getUsersReviewRanking(user);
            reviewRankingTextArea.setText("Number of Reviews They've Posted: " + ranking.getPersonalTotal() + "\n" +
                    "Total Number of Reviews Posted: " + ranking.getOverallTotal() + "\n" +
                    "Your Ranking: " + ranking.getRank());
        } catch (SQLException err) {
            System.out.println("Could not get users review ranking: " + err.getMessage());
        }

    }
}
