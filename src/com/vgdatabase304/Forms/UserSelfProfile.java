package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.*;
import com.vgdatabase304.Structures.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private JList listofReviews;
    private JTextArea userName;
    private JTextArea eMail;
    private JPanel ReivewPanel;
    private JPanel ListPanel;
    private JTextField newListName;
    private JButton createList;
    public JPanel mainPanel;
    private JScrollPane listsScrollPane;
    private JScrollPane reviewsScrollPane;

    public UserSelfProfile(JFrame parent, RegisteredUser user) {
        setAccount(user);
        parent.setContentPane(mainPanel);
        parent.setVisible(true);
        parent.pack();
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                    //open new list interface
                }
            });
            List<VGList> VGListList = VGListAdaptor.getAllListsByUser(user);
            DefaultListModel vgList = new DefaultListModel();
            listOfVGLists.setModel(vgList);
            listsScrollPane.setViewportView(listOfVGLists);
            for (VGList listObject : VGListList) {
                System.out.println(listObject.getListID());
                vgList.addElement(VGListAdaptor.getListName(listObject));
            }
            listOfVGLists.setCellRenderer(new CellRenderer());

            listofReviews.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            listofReviews.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    //open new list interface
                }
            });
            List<Review> reviewList = ReviewAdaptor.getAllReviewsByUser(user);
            DefaultListModel reviewListModel = new DefaultListModel();
            listofReviews.setModel(reviewListModel);
            listofReviews.setCellRenderer(new CellRenderer());
            reviewsScrollPane.setViewportView(listofReviews);
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

    }
}
