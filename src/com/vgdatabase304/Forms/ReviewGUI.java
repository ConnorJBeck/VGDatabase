package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.ReviewAdaptor;
import com.vgdatabase304.Structures.Platform;
import com.vgdatabase304.Structures.RegisteredUser;
import com.vgdatabase304.Structures.Review;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Skyline on 2017-03-29.
 */
public class ReviewGUI {
    private JPanel mainPanel;
    private JTextField reviewerName;
    private JButton backButton;
    private JTextArea reviewText;
    private JFrame f;

    public ReviewGUI(Review review, RegisteredUser currentUser) {
        f = new JFrame("Review");

        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        f.setVisible(true);
        f.setContentPane(mainPanel);
        f.pack();

        try {
            reviewText.setText(ReviewAdaptor.getReviewText(review));
        } catch (SQLException err) {
            reviewText.setText("Review not found.");
            System.out.println(err.getMessage());
        }


        try {
            reviewerName.setText(ReviewAdaptor.getUsername(review).getUsername());
        } catch (SQLException err) {
            reviewerName.setText("Review not found.");
            System.out.println(err.getMessage());
        }


    }
}

