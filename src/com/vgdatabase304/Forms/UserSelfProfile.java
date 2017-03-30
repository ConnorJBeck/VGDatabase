package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.RegisteredUserAdaptor;
import com.vgdatabase304.Adaptors.VGListAdaptor;
import com.vgdatabase304.Structures.RegisteredUser;
import com.vgdatabase304.Structures.VGList;

import javax.swing.*;
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
    public JPanel mainPanel;
    private JScrollPane listsScrollPane;

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
            List<VGList> VGListList = VGListAdaptor.getAllListsByUser(user);
            DefaultListModel vglist = new DefaultListModel();
            listsScrollPane.setViewportView(myList);
            for (VGList listObject : VGListList) {
                vglist.addElement(listObject);
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
