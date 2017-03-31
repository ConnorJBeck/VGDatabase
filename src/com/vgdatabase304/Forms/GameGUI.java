package com.vgdatabase304.Forms;

import com.vgdatabase304.Structures.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Skyline on 2017-03-28.
 */
public class GameGUI {
    private JButton backButton;
    private JPanel mainPanel;
    private JTextField gameName;
    private JButton gameReviews;
    private JComboBox addTag;
    private JTextField textField1;
    private JTextField textField2;
    private JList platformList;
    private JLabel releaseList;
    private JFrame f;

    public GameGUI(JFrame frame, Game game) {
        f = new JFrame("GameGUI");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setVisible(true);
        f.setContentPane(mainPanel);
        f.pack();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
