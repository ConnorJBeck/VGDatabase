package com.vgdatabase304.Forms;

import javax.swing.*;

/**
 * Created by Skyline on 2017-03-29.
 */
public class ReviewGUI {
    private JPanel panel1;
    private JTextField reviewName;
    private JButton backButton;
    private JTextArea reviewText;
    private JFrame f;

    public ReviewGUI() {
        f = new JFrame("Search");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setContentPane(panel1);
        f.pack();
    }
}

