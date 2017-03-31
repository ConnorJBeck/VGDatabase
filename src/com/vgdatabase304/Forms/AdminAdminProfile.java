package com.vgdatabase304.Forms;

import com.vgdatabase304.Structures.AdminUser;
import com.vgdatabase304.Structures.RegisteredUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jessyang90 on 2017-03-28.
 */
public class AdminAdminProfile {
    private JLabel username;
    private JLabel email;
    private JButton searchButton;
    private JLabel reviewLabel;
    private JLabel listPanel;
    private JList myList;
    private JList listofReviews;
    private JTextArea userName;
    private JTextArea eMail;
    private JPanel ReviewPanel;
    private JPanel ListPanel;
    private JPanel TagPanel;
    private JComboBox listofTags;
    private JButton DeleteTag;
    private JTextField newTagName;
    private JButton CreateTag;
    private JPanel mainPanel;
    private JFrame f;

    public AdminAdminProfile(RegisteredUser user) {
        f = new JFrame("Admin");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Search();
            }
        });

        f.setVisible(true);
        f.setContentPane(mainPanel);
        f.pack();
    }
}
