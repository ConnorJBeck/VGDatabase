//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasicGUI {

    private static Statement stmt;
    //Note: Typically the main method will be in a
    //separate class. As this is a simple one class
    //example it's all in the one class.
    public static void main(String[] args) {
        try {
            ConnectionManager.initConnection("ora_s8h0b", "a57723158");
            stmt = ConnectionManager.getStatement();
            Database fd = new Database();
            fd.initDatabase();

            new BasicGUI();
        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
            System.out.println("Error: " + err.toString());
            for (int i = 0; i < err.getStackTrace().length; i++) {
                System.out.println("Error: " + err.getStackTrace()[i]);
            }
            System.out.println("Error: " + err.getStackTrace().length);
        }

    }

    public BasicGUI() throws SQLException
    {
        JFrame guiFrame = new JFrame();

        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("VG App");
        guiFrame.setSize(800,700);

        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        //Options for the JComboBox
        ResultSet rs = stmt.executeQuery("SELECT * FROM GAME");
        String[] fruitOptions = {"Apple", "Apricot", "Banana"
                ,"Cherry", "Date", "Kiwi", "Orange", "Pear", "Strawberry"};

        //Options for the JList
        String[] vegOptions = {"Asparagus", "Beans", "Broccoli", "Cabbage"
                , "Carrot", "Celery", "Cucumber", "Leek", "Mushroom"
                , "Pepper", "Radish", "Shallot", "Spinach", "Swede"
                , "Turnip"};

        //The first JPanel contains a JLabel and JCombobox
        JPanel homeScroll = new JPanel();
        JLabel homeScrolltxt = new JLabel("Recently Added Games:");
        JScrollPane scrPane = new JScrollPane(homeScroll);
        JScrollBar vertScrollBar = scrPane.createVerticalScrollBar();

        scrPane.add(homeScrolltxt);

        //Create the second JPanel. Add a JLabel and JList and
        //make use the JPanel is not visible.
        final JPanel listPanel = new JPanel();
        listPanel.setVisible(false);
        JLabel listLbl = new JLabel("Vegetables:");
        JList vegs = new JList(vegOptions);
        vegs.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        listPanel.add(listLbl);
        listPanel.add(vegs);

        JButton vegFruitBut = new JButton( "Fruit or Veg");

        //The ActionListener class is used to handle the
        //event that happens when the user clicks the button.
        //As there is not a lot that needs to happen we can 
        //define an anonymous inner class to make the code simpler.
        vegFruitBut.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                //When the fruit of veg button is pressed
                //the setVisible value of the listPanel and
                //comboPanel is switched from true to
                //value or vice versa.
                listPanel.setVisible(!listPanel.isVisible());
                homeScroll.setVisible(!homeScroll.isVisible());

            }
        });

        //The JFrame uses the BorderLayout layout manager.
        //Put the two JPanels and JButton in different areas.
        guiFrame.add(homeScroll, BorderLayout.NORTH);
        guiFrame.add(listPanel, BorderLayout.CENTER);
        guiFrame.add(vegFruitBut,BorderLayout.SOUTH);

        //make sure the JFrame is visible
        guiFrame.setVisible(true);
    }

}