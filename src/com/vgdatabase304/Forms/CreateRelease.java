package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.ReleaseAdaptor;
import com.vgdatabase304.Exceptions.InstanceNotFoundException;
import com.vgdatabase304.Structures.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by Skyline on 2017-03-31.
 */
public class CreateRelease {
    private JPanel mainPanel;
    private JTextArea regionField;
    private JTextArea platformField;
    private JTextArea dateField;
    private JButton submitButton;
    private JFrame f;

    public CreateRelease(RegisteredUser user, Game game) {
        f = new JFrame("CreateRelease");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Region region = Region.getRegionFromString(regionField.getText());
                    Platform platform = Platform.valueOf(platformField.getText().replaceAll("\\s+",""));
                    String[] splitDate = dateField.getText().split("-");
                    Date date = new Date(Integer.parseInt(splitDate[0]),
                            Integer.parseInt(splitDate[1]),
                            Integer.parseInt(splitDate[2]));
                    try {
                        ReleaseAdaptor.addReleaseToDatabase(game, region, platform, (AdminUser) user, date);
                        f.dispose();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } catch (InstanceNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        f.setVisible(true);
        f.setContentPane(mainPanel);
        f.pack();
    }
}
