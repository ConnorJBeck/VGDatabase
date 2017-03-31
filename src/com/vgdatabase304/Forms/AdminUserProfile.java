package com.vgdatabase304.Forms;

import javax.swing.*;

/**
 * Created by jessyang90 on 2017-03-28.
 */
public class AdminUserProfile {
    private JLabel username;
    private JLabel email;
    private JButton searchButton;
    private JLabel reviewLabel;
    private JLabel listPanel;
    private JList myList;
    private JList listofReviews;
    private JTextArea userName;
    private JTextArea eMail;
    private JButton deleteUser;
    private JPanel ReivewPanel;
    private JPanel ListPanel;
    private JPanel mainPanel;
    private JFrame f;

    public AdminUserProfile() {
        f = new JFrame("Search");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setContentPane(mainPanel);
        f.pack();
    }
}
