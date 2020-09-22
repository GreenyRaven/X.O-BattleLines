package View;
import ModelView.CardCollection;

import javax.swing.*;
import java.util.Objects;

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
    private JComboBox gamediffBox;
    private JComboBox gametypeBox;
    private JButton launchButton;
    private JPanel controlPanel;
    private JPanel baseGamePanel;
    private JPanel northGamePanel;
    private JPanel westGamePanel;
    private JPanel eastGamePanel;


    public Main_Board () {
        getContentPane().add(main_panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMenuItems();
        setLabelVisibility("6x6", false, false);
        launchButton.addActionListener(e -> setGameBoard((String) Objects.requireNonNull(gametypeBox.getSelectedItem())));
        pack();
        setVisible(true);
    }

    public void updateViewObject (CardCollection.Card gameCard) {
    }
    private void sendData () {
    }

    private void setMenuItems () {
        JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Настройки игры");
        JMenuItem txtFileItem = new JMenuItem("Text file");
        mainMenu.add(txtFileItem);
        mainMenu.addSeparator();
        menuBar.add(mainMenu);
        setJMenuBar(menuBar);
    }
    private void setGameBoard (String gametype) {
        controlPanel.setVisible(false);
        if (gametype.equals("3x3")) {
            setLabelVisibility("3x3",true,true);
            ModelView.CardCollection gameBoard = new CardCollection("3x3");
        }
        if (gametype.equals("6x6")) {
            setLabelVisibility("6x6",true,true);
            ModelView.CardCollection gameBoard = new CardCollection("6x6");
        }
        pack();
    }
    private void setLabelVisibility (String fieldSize, boolean unlocked, boolean visible) {
        if (unlocked && visible) {
            switch (fieldSize) {
                case "3x3" -> {
                    baseGamePanel.setVisible(true);
                    baseGamePanel.setEnabled(true);
                    northGamePanel.setVisible(false);
                    westGamePanel.setVisible(false);
                    eastGamePanel.setVisible(false);
                }
                case "6x6" -> {
                    baseGamePanel.setVisible(true);
                    baseGamePanel.setEnabled(true);
                    northGamePanel.setVisible(true);
                    northGamePanel.setEnabled(true);
                    westGamePanel.setVisible(true);
                    westGamePanel.setEnabled(true);
                    eastGamePanel.setVisible(true);
                    eastGamePanel.setEnabled(true);
                }
            }
        }
        if (!unlocked && visible) {
            switch (fieldSize) {
                case "3x3" -> {
                    baseGamePanel.setVisible(true);
                    baseGamePanel.setEnabled(false);
                    northGamePanel.setVisible(false);
                    westGamePanel.setVisible(false);
                    eastGamePanel.setVisible(false);
                }
                case "6x6" -> {
                    baseGamePanel.setVisible(true);
                    baseGamePanel.setEnabled(false);
                    northGamePanel.setVisible(true);
                    northGamePanel.setEnabled(false);
                    westGamePanel.setVisible(true);
                    westGamePanel.setEnabled(false);
                    eastGamePanel.setVisible(true);
                    eastGamePanel.setEnabled(false);
                }
            }
        }
        if (!visible) {
            baseGamePanel.setVisible(false);
            northGamePanel.setVisible(false);
            westGamePanel.setVisible(false);
            eastGamePanel.setVisible(false);
        }
        pack();
    }
}


