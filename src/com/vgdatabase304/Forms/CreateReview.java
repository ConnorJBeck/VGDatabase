package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.ReviewAdaptor;
import com.vgdatabase304.Structures.Game;
import com.vgdatabase304.Structures.RegisteredUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Skyline on 2017-03-31.
 */
public class CreateReview {
    private JButton backButton;
    private JPanel mainPanel;
    private JTextField promptField;
    private JTextArea reviewText;
    private JButton submitButton;
    private JTextField ratingField;
    private JFrame f;

    public CreateReview(final Game game, final RegisteredUser currentUser) {
        f = new JFrame("CreateReview");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String review = reviewText.getText();
                Double rating = Double.valueOf(ratingField.getText());
                try {
                    ReviewAdaptor.addReviewToDatabase(currentUser, game, review, rating);
                    f.dispose();
                } catch (SQLException e1) {
                    System.out.println("Unable to submit review");
                }
            }
        });

        f.setVisible(true);
        f.setContentPane(mainPanel);
        f.pack();
    }
}
