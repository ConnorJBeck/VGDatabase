package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.GameAdaptor;
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
public class CreateGame {
    private JPanel mainPanel;
    private JTextField gameTitleField;
    private JTextField esrbField;
    private JTextField regionFIeld;
    private JTextField dateField;
    private JTextField platformField;
    private JButton addGameButton;
    private JFrame f;

    public CreateGame(RegisteredUser adminUser) {
        f = new JFrame("CreateGame");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        addGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ESRBRating esrb = ESRBRating.getRatingFromString(esrbField.getText());
                    Region region = Region.getRegionFromString(regionFIeld.getText());
                    Platform platform = Platform.valueOf(platformField.getText().replaceAll("\\s+",""));
                    String gameTitle = gameTitleField.getText();
                    String[] splitDate = dateField.getText().split("-");
                    Date date = new Date(Integer.parseInt(splitDate[0]),
                            Integer.parseInt(splitDate[1]),
                            Integer.parseInt(splitDate[2]));
                    try {
                        Game gameAdded = GameAdaptor.addGameToDatabase((AdminUser) adminUser, esrb, gameTitle, region, platform, date);
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
