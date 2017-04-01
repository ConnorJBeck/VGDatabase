package com.vgdatabase304.Forms;

import com.vgdatabase304.Structures.Game;
import com.vgdatabase304.Structures.GameRenderer;
import com.vgdatabase304.Structures.SearchResult;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public SearchResults(List<SearchResult> inputList) {
        f = new JFrame("SearchResults");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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


        DefaultListModel list = new DefaultListModel();
        resultList.setModel(list);
//        if (inputList.getClass().getComponentType() == Game.class) {
//            List<Game> inputGameList = inputList;
            for (SearchResult listObject : inputList) {
//                    String gameName = GameAdaptor.getName((Game) listObject);
//                    System.out.println(gameName);
                    System.out.println(listObject.getGameID());
//            list.addElement(listObject.getGameID());
                list.addElement(listObject);

            }
//        }
//        resultList.setCellRenderer(new CellRenderer());
        GameRenderer renderer = new GameRenderer();
        if (list.getElementAt(0).getClass() == Game.class) {
            renderer.setGameFlagOn();
        }
//        resultList.setCellRenderer(new GameRenderer());
        resultList.setCellRenderer(renderer);


        f.setVisible(true);
        f.setContentPane(panel1);
        f.pack();
    }
}
