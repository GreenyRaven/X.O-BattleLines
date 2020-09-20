package View;

import com.sun.tools.javac.Main;

import javax.swing.*;

public class Main_Board extends JFrame{
    private JLabel oneOne;
    private JLabel oneTwo;
    private JLabel oneThree;
    private JLabel oneFour;
    private JLabel oneFive;
    private JLabel oneSix;
    private JLabel twoOne;
    private JLabel twoTwo;
    private JLabel twoThree;
    private JLabel twoFour;
    private JLabel twoFive;
    private JLabel twoSix;
    private JLabel threeOne;
    private JLabel threeTwo;
    private JLabel threeThree;
    private JLabel threeFour;
    private JLabel threeFive;
    private JLabel threeSix;
    private JLabel fourOne;
    private JLabel fourTwo;
    private JLabel fourThree;
    private JLabel fourFour;
    private JLabel fourFive;
    private JLabel fourSix;
    private JLabel fiveOne;
    private JLabel fiveTwo;
    private JLabel fiveThree;
    private JLabel fiveFour;
    private JLabel fiveFive;
    private JLabel fiveSix;
    private JLabel sixOne;
    private JLabel sixTwo;
    private JLabel sixThree;
    private JLabel sixFour;
    private JLabel sixFive;
    private JLabel sixSix;
    private JPanel main_panel;


    public Main_Board () {
        getContentPane().add(main_panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMenuItems();
        pack();
        setVisible(true);
    }

    private void setMenuItems () {
        JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("File");
        JMenu fileMenu = new JMenu("New");
        mainMenu.add(fileMenu);
        JMenuItem txtFileItem = new JMenuItem("Text file");
        fileMenu.add(txtFileItem);
        fileMenu.addSeparator();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }
}


