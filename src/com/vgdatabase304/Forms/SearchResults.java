package com.vgdatabase304.Forms;

import com.vgdatabase304.Adaptors.AdminUserAdaptor;
import com.vgdatabase304.Structures.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Skyline on 2017-03-29.
 */
public class SearchResults {
    private JPanel panel1;
    private JLabel results;
    private JButton backButton;
    private JList resultList;
    private JFrame f;
    boolean isGame;

    public SearchResults(List<SearchResult> inputList, final RegisteredUser currentUser) {
        f = new JFrame("SearchResults");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        if (inputList.size() > 0 && inputList.get(0).getClass() == Game.class){
            isGame = true;
        }

        resultList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });


        resultList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        resultList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    System.out.println("list clicked");
                    // Game List
                    if(isGame){
                        Game game = (Game) resultList.getSelectedValue();
                        new GameGUI(game, currentUser);
                    }
                    // User List
                    else{
                        try {
                            RegisteredUser selected = (RegisteredUser) resultList.getSelectedValue();

                            if (AdminUserAdaptor.isAdmin(currentUser)) {
                                new AdminUserProfile(selected,currentUser);
                            }else{
                                new UserUserProfile(selected, currentUser);
                            }

                        }catch (SQLException err){
                            System.out.println("Error: " + err.getMessage());
                        }
                        // click currentUser
                    }

                }
            }
        });
        SearchResultRenderer renderer = new SearchResultRenderer();
        if (isGame) {
            renderer.setGameFlagOn();
        }
        DefaultListModel list = new DefaultListModel();
        resultList.setModel(list);
//        if (inputList.getClass().getComponentType() == Game.class) {
//            List<Game> inputGameList = inputList;
            for (SearchResult listObject : inputList) {
//                    String gameName = GameAdaptor.getName((Game) listObject);
//                    System.out.println(gameName);
//                    System.out.println(listObject.getGameID());
//            list.addElement(listObject.getGameID());
                list.addElement(listObject);

            }
//        }
//        resultList.setCellRenderer(new CellRenderer());

//        resultList.setCellRenderer(new GameRenderer());
        resultList.setCellRenderer(renderer);




        f.setVisible(true);
        f.setContentPane(panel1);
        f.pack();
    }
}
