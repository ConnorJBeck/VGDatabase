package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.RegisteredUserAdaptor;
import com.vgdatabase304.Adaptors.VGTagAdaptor;
import com.vgdatabase304.Structures.AdminUser;
import com.vgdatabase304.Structures.VGTag;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JList myList;
    private JList listofReviews;
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
    private JFrame f;
    private List<VGTag> tags;

    public AdminAdminProfile(AdminUser user) {
        f = new JFrame("Admin");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

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

        f.setVisible(true);
        f.setContentPane(mainPanel);
        f.pack();
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
