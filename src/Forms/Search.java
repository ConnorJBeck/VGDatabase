package Forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Skyline on 2017-03-28.
 */
public class Search {
    private JTextField textField1;
    private JPanel panel1;
    private JComboBox searchBy;
    private JButton submitSearchButton;

    public Search() {
        submitSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
