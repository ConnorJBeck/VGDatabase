package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.RegisteredUserAdaptor;
import com.vgdatabase304.Structures.RegisteredUser;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by jessyang90 on 2017-03-28.
 */
public class UserSelfProfile extends JFrame {
    private JLabel username;
    private JLabel email;
    private JButton searchButton;
    private JLabel reviewLabel;
    private JLabel listPanel;
    private JList myList;
    private JList listofReviews;
    private JTextArea userName;
    private JTextArea eMail;
    private JPanel ReivewPanel;
    private JPanel ListPanel;
    private JComboBox selectedGame;
    private JTextField newListName;
    private JTextArea newReview;
    private JButton createList;
    private JButton createReview;

    public UserSelfProfile() {

    }

    public void setAccount(RegisteredUser user) {
        this.username.setText(user.getUsername());
        try {
            email.setText(RegisteredUserAdaptor.getEmail(user));
        } catch (SQLException e) {
            email.setText("Unable to retrieve email from database");
        }

        /*
        try {
            password.setTest(RegisteredUserAdaptor.getPassword(user));
        } catch (SQLException e) {
            password.setText("Unable to retrieve password from database");
        }
        */
    }
}
