package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.RegisteredUserAdaptor;
import com.vgdatabase304.Structures.RegisteredUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by jessyang90 on 2017-03-28.
 */
public class AdminUserProfile {
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JButton searchButton;
    private JLabel reviewLabel;
    private JLabel listPanel;
    private JList myList;
    private JList listofReviews;
    private JTextArea username;
    private JTextArea email;
    private JButton deleteUser;
    private JPanel ReviewPanel;
    private JPanel ListPanel;
    private JPanel mainPanel;
    private JFrame f;

    public AdminUserProfile(final RegisteredUser user, RegisteredUser currentUser) {
        f = new JFrame("Search");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        username.setText(currentUser.getUsername());
        try {
            email.append(RegisteredUserAdaptor.getEmail(currentUser));
        } catch (SQLException e) {
            System.out.println("Could not get user's email");
        }

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Search(user);
            }
        });

        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        f.setContentPane(mainPanel);
        f.pack();
        f.setVisible(true);
    }
}
