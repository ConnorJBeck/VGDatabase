package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.RegisteredUserAdaptor;
import com.vgdatabase304.Adaptors.VGListAdaptor;
import com.vgdatabase304.Structures.RegisteredUser;
import com.vgdatabase304.Structures.VGList;

import javax.swing.*;
import java.awt.*;
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
    private JList listOfVGLists;
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
    private JScrollPane reviewsScrollPane;

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

        class MyCellRenderer extends JLabel implements ListCellRenderer<Object> {

            // This is the only method defined by ListCellRenderer.
            // We just reconfigure the JLabel each time we're called.

            public Component getListCellRendererComponent(
                    JList<?> list,           // the list
                    Object value,            // value to display
                    int index,               // cell index
                    boolean isSelected,      // is the cell selected
                    boolean cellHasFocus)    // does the cell have focus
            {
                String s = value.toString();
                setText(s);
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setEnabled(list.isEnabled());
                setFont(list.getFont());
                setOpaque(true);
                return this;
            }
        }

        try {
            listOfVGLists.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            List<VGList> VGListList = VGListAdaptor.getAllListsByUser(user);
            DefaultListModel vgList = new DefaultListModel();
            listOfVGLists.setModel(vgList);
            listsScrollPane.setViewportView(listOfVGLists);
            for (VGList listObject : VGListList) {
                System.out.println(listObject.getListID());
                vgList.addElement(VGListAdaptor.getListName(listObject));
            }
            listOfVGLists.setCellRenderer(new MyCellRenderer());
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
