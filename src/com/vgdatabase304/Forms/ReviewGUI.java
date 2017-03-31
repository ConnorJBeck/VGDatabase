package com.vgdatabase304.Forms;

import com.vgdatabase304.Structures.RegisteredUser;
import com.vgdatabase304.Structures.Review;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Skyline on 2017-03-29.
 */
public class ReviewGUI {
    private JPanel mainPanel;
    private JTextField reviewName;
    private JButton backButton;
    private JTextArea reviewText;
    private JFrame f;

    public ReviewGUI(Review review, RegisteredUser user) {
        f = new JFrame("Review");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setContentPane(mainPanel);
        f.pack();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        f.setVisible(true);
    }
}

