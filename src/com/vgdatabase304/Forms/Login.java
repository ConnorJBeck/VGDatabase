package com.vgdatabase304.Forms;
import com.vgdatabase304.Adaptors.RegisteredUserAdaptor;
import com.vgdatabase304.Structures.Platform;
import com.vgdatabase304.Structures.RegisteredUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Raymond on 2017-03-23.
 */
public class Login {
    private JButton loginButton;
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton createAccount;

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username, password;
                username = textField1.getText();
                password = passwordField1.getText();
                try {
                    RegisteredUser user = new RegisteredUser(username);
                    if (RegisteredUserAdaptor.getPassword(user).equals(password)) {
                        //TODO: Log in the user
                    }
                    else {
                        //TODO: Reject user
                    }
                } catch (SQLException err) {

                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

