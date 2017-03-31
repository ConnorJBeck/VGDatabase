package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.*;
import com.vgdatabase304.Structures.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jessyang90 on 2017-03-28.
 */
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
    private JPanel ReivewPanel;
    private JPanel ListPanel;
    private JTextField newListName;
    private JButton createList;
    public JPanel mainPanel;
    private JScrollPane listsScrollPane;
    private JScrollPane reviewsScrollPane;
    private JFrame parent;

    public UserSelfProfile(RegisteredUser user) {
        setAccount(user);
        parent = new JFrame("Profile");
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Search();
            }
        });

        parent.setVisible(true);
        parent.setContentPane(mainPanel);
        parent.pack();
    }

    public void setAccount(RegisteredUser user) {
        this.userName.setText(user.getUsername());
        try {
            eMail.setText(RegisteredUserAdaptor.getEmail(user));
        } catch (SQLException e) {
            eMail.setText("Unable to retrieve email from database");
        }

        try {
            listOfVGLists.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            listOfVGLists.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    System.out.println("value changed");
                    try {
                        VGList vgList = (VGList) listOfVGLists.getSelectedValue();
                        JFrame frame = new JFrame((VGListAdaptor.getListName(vgList)));
                        new ListGUI(frame, vgList);
                    } catch (SQLException err) {
                        System.out.println(err.getMessage());
                    }
                }
            });
            List<VGList> VGListList = VGListAdaptor.getAllListsByUser(user);
            DefaultListModel vgList = new DefaultListModel();
            listOfVGLists.setModel(vgList);
            listsScrollPane.setViewportView(listOfVGLists);
            for (VGList listObject : VGListList) {
                System.out.println(listObject.getListID());
                vgList.addElement(listObject);
            }
            listOfVGLists.setCellRenderer(new ListRenderer());

            listOfReviews.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            listOfReviews.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                }
            });
            List<Review> reviewList = ReviewAdaptor.getAllReviewsByUser(user);
            DefaultListModel reviewListModel = new DefaultListModel();
            listOfReviews.setModel(reviewListModel);
            listOfReviews.setCellRenderer(new CellRenderer());
            reviewsScrollPane.setViewportView(listOfReviews);
            for (Review reviewObject : reviewList) {
                System.out.println(reviewObject.getReviewID());
                vgList.addElement(ReviewAdaptor.getGame(reviewObject));
            }


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
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
