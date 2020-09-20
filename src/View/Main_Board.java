package View;
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
    private JLabel gametypeLabel;
    private JLabel gamediffLabel;


    public Main_Board () {
        getContentPane().add(main_panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMenuItems();
        setLabelVisibility("6x6", false, false);
        launchButton.addActionListener(e -> setGameBoard((String) Objects.requireNonNull(gametypeBox.getSelectedItem())));
        pack();
        setVisible(true);
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
        gametypeLabel.setEnabled(false);
        gametypeLabel.setVisible(false);
        gamediffLabel.setEnabled(false);
        gamediffLabel.setVisible(false);
        gamediffBox.setEnabled(false);
        gamediffBox.setVisible(false);
        gametypeBox.setEnabled(false);
        gametypeBox.setVisible(false);
        launchButton.setEnabled(false);
        launchButton.setVisible(false);
        if (gametype.equals("3x3")) {
            setLabelVisibility("3x3",true,true);
        }
        if (gametype.equals("6x6")) {
            setLabelVisibility("6x6",true,true);
        }
        pack();
    }
    private void setLabelVisibility (String fieldSize, boolean unlocked, boolean visible) {
        if (unlocked && visible) {
            switch (fieldSize) {
                case "3x3" -> {
                    oneOne.setEnabled(true);
                    oneOne.setVisible(true);
                    oneTwo.setEnabled(true);
                    oneTwo.setVisible(true);
                    oneThree.setEnabled(true);
                    oneThree.setVisible(true);
                    twoOne.setEnabled(true);
                    twoOne.setVisible(true);
                    twoTwo.setEnabled(true);
                    twoTwo.setVisible(true);
                    twoThree.setEnabled(true);
                    twoThree.setVisible(true);
                    threeOne.setEnabled(true);
                    threeOne.setVisible(true);
                    threeTwo.setEnabled(true);
                    threeTwo.setVisible(true);
                    threeThree.setEnabled(true);
                    threeThree.setVisible(true);
                }
                case "6x6" -> {
                    oneOne.setEnabled(true);
                    oneOne.setVisible(true);
                    oneTwo.setEnabled(true);
                    oneTwo.setVisible(true);
                    oneThree.setEnabled(true);
                    oneThree.setVisible(true);
                    oneFour.setEnabled(true);
                    oneFour.setVisible(true);
                    oneFive.setEnabled(true);
                    oneFive.setVisible(true);
                    oneSix.setEnabled(true);
                    oneSix.setVisible(true);
                    twoOne.setEnabled(true);
                    twoOne.setVisible(true);
                    twoTwo.setEnabled(true);
                    twoTwo.setVisible(true);
                    twoThree.setEnabled(true);
                    twoThree.setVisible(true);
                    twoFour.setEnabled(true);
                    twoFour.setVisible(true);
                    twoFive.setEnabled(true);
                    twoFive.setVisible(true);
                    twoSix.setEnabled(true);
                    twoSix.setVisible(true);
                    threeOne.setEnabled(true);
                    threeOne.setVisible(true);
                    threeTwo.setEnabled(true);
                    threeTwo.setVisible(true);
                    threeThree.setEnabled(true);
                    threeThree.setVisible(true);
                    threeFour.setEnabled(true);
                    threeFour.setVisible(true);
                    threeFive.setEnabled(true);
                    threeFive.setVisible(true);
                    threeSix.setEnabled(true);
                    threeSix.setVisible(true);
                    fourOne.setEnabled(true);
                    fourOne.setVisible(true);
                    fourTwo.setEnabled(true);
                    fourTwo.setVisible(true);
                    fourThree.setEnabled(true);
                    fourThree.setVisible(true);
                    fourFour.setEnabled(true);
                    fourFour.setVisible(true);
                    fourFive.setEnabled(true);
                    fourFive.setVisible(true);
                    fourSix.setEnabled(true);
                    fourSix.setVisible(true);
                    fiveOne.setEnabled(true);
                    fiveOne.setVisible(true);
                    fiveTwo.setEnabled(true);
                    fiveTwo.setVisible(true);
                    fiveThree.setEnabled(true);
                    fiveThree.setVisible(true);
                    fiveFour.setEnabled(true);
                    fiveFour.setVisible(true);
                    fiveFive.setEnabled(true);
                    fiveFive.setVisible(true);
                    fiveSix.setEnabled(true);
                    fiveSix.setVisible(true);
                    sixOne.setEnabled(true);
                    sixOne.setVisible(true);
                    sixTwo.setEnabled(true);
                    sixTwo.setVisible(true);
                    sixThree.setEnabled(true);
                    sixThree.setVisible(true);
                    sixFour.setEnabled(true);
                    sixFour.setVisible(true);
                    sixFive.setEnabled(true);
                    sixFive.setVisible(true);
                    sixSix.setEnabled(true);
                    sixSix.setVisible(true);
                }
            }
        }
        if (!unlocked && visible) {
            switch (fieldSize) {
                case "3x3" -> {
                    oneOne.setEnabled(false);
                    oneOne.setVisible(true);
                    oneTwo.setEnabled(false);
                    oneTwo.setVisible(true);
                    oneThree.setEnabled(false);
                    oneThree.setVisible(true);
                    twoOne.setEnabled(false);
                    twoOne.setVisible(true);
                    twoTwo.setEnabled(false);
                    twoTwo.setVisible(true);
                    twoThree.setEnabled(false);
                    twoThree.setVisible(true);
                    threeOne.setEnabled(false);
                    threeOne.setVisible(true);
                    threeTwo.setEnabled(false);
                    threeTwo.setVisible(true);
                    threeThree.setEnabled(false);
                    threeThree.setVisible(true);
                }
                case "6x6" -> {
                    oneOne.setEnabled(false);
                    oneOne.setVisible(true);
                    oneTwo.setEnabled(false);
                    oneTwo.setVisible(true);
                    oneThree.setEnabled(false);
                    oneThree.setVisible(true);
                    oneFour.setEnabled(false);
                    oneFour.setVisible(true);
                    oneFive.setEnabled(false);
                    oneFive.setVisible(true);
                    oneSix.setEnabled(false);
                    oneSix.setVisible(true);
                    twoOne.setEnabled(false);
                    twoOne.setVisible(true);
                    twoTwo.setEnabled(false);
                    twoTwo.setVisible(true);
                    twoThree.setEnabled(false);
                    twoThree.setVisible(true);
                    twoFour.setEnabled(false);
                    twoFour.setVisible(true);
                    twoFive.setEnabled(false);
                    twoFive.setVisible(true);
                    twoSix.setEnabled(false);
                    twoSix.setVisible(true);
                    threeOne.setEnabled(false);
                    threeOne.setVisible(true);
                    threeTwo.setEnabled(false);
                    threeTwo.setVisible(true);
                    threeThree.setEnabled(false);
                    threeThree.setVisible(true);
                    threeFour.setEnabled(false);
                    threeFour.setVisible(true);
                    threeFive.setEnabled(false);
                    threeFive.setVisible(true);
                    threeSix.setEnabled(false);
                    threeSix.setVisible(true);
                    fourOne.setEnabled(false);
                    fourOne.setVisible(true);
                    fourTwo.setEnabled(false);
                    fourTwo.setVisible(true);
                    fourThree.setEnabled(false);
                    fourThree.setVisible(true);
                    fourFour.setEnabled(false);
                    fourFour.setVisible(true);
                    fourFive.setEnabled(false);
                    fourFive.setVisible(true);
                    fourSix.setEnabled(false);
                    fourSix.setVisible(true);
                    fiveOne.setEnabled(false);
                    fiveOne.setVisible(true);
                    fiveTwo.setEnabled(false);
                    fiveTwo.setVisible(true);
                    fiveThree.setEnabled(false);
                    fiveThree.setVisible(true);
                    fiveFour.setEnabled(false);
                    fiveFour.setVisible(true);
                    fiveFive.setEnabled(false);
                    fiveFive.setVisible(true);
                    fiveSix.setEnabled(false);
                    fiveSix.setVisible(true);
                    sixOne.setEnabled(false);
                    sixOne.setVisible(true);
                    sixTwo.setEnabled(false);
                    sixTwo.setVisible(true);
                    sixThree.setEnabled(false);
                    sixThree.setVisible(true);
                    sixFour.setEnabled(false);
                    sixFour.setVisible(true);
                    sixFive.setEnabled(false);
                    sixFive.setVisible(true);
                    sixSix.setEnabled(false);
                    sixSix.setVisible(true);
                }
            }
        }
        if (!visible) {
            switch (fieldSize) {
                case "3x3" -> {
                    oneOne.setEnabled(false);
                    oneOne.setVisible(false);
                    oneTwo.setEnabled(false);
                    oneTwo.setVisible(false);
                    oneThree.setEnabled(false);
                    oneThree.setVisible(false);
                    twoOne.setEnabled(false);
                    twoOne.setVisible(false);
                    twoTwo.setEnabled(false);
                    twoTwo.setVisible(false);
                    twoThree.setEnabled(false);
                    twoThree.setVisible(false);
                    threeOne.setEnabled(false);
                    threeOne.setVisible(false);
                    threeTwo.setEnabled(false);
                    threeTwo.setVisible(false);
                    threeThree.setEnabled(false);
                    threeThree.setVisible(false);
                }
                case "6x6" -> {
                    oneOne.setEnabled(false);
                    oneOne.setVisible(false);
                    oneTwo.setEnabled(false);
                    oneTwo.setVisible(false);
                    oneThree.setEnabled(false);
                    oneThree.setVisible(false);
                    oneFour.setEnabled(false);
                    oneFour.setVisible(false);
                    oneFive.setEnabled(false);
                    oneFive.setVisible(false);
                    oneSix.setEnabled(false);
                    oneSix.setVisible(false);
                    twoOne.setEnabled(false);
                    twoOne.setVisible(false);
                    twoTwo.setEnabled(false);
                    twoTwo.setVisible(false);
                    twoThree.setEnabled(false);
                    twoThree.setVisible(false);
                    twoFour.setEnabled(false);
                    twoFour.setVisible(false);
                    twoFive.setEnabled(false);
                    twoFive.setVisible(false);
                    twoSix.setEnabled(false);
                    twoSix.setVisible(false);
                    threeOne.setEnabled(false);
                    threeOne.setVisible(false);
                    threeTwo.setEnabled(false);
                    threeTwo.setVisible(false);
                    threeThree.setEnabled(false);
                    threeThree.setVisible(false);
                    threeFour.setEnabled(false);
                    threeFour.setVisible(false);
                    threeFive.setEnabled(false);
                    threeFive.setVisible(false);
                    threeSix.setEnabled(false);
                    threeSix.setVisible(false);
                    fourOne.setEnabled(false);
                    fourOne.setVisible(false);
                    fourTwo.setEnabled(false);
                    fourTwo.setVisible(false);
                    fourThree.setEnabled(false);
                    fourThree.setVisible(false);
                    fourFour.setEnabled(false);
                    fourFour.setVisible(false);
                    fourFive.setEnabled(false);
                    fourFive.setVisible(false);
                    fourSix.setEnabled(false);
                    fourSix.setVisible(false);
                    fiveOne.setEnabled(false);
                    fiveOne.setVisible(false);
                    fiveTwo.setEnabled(false);
                    fiveTwo.setVisible(false);
                    fiveThree.setEnabled(false);
                    fiveThree.setVisible(false);
                    fiveFour.setEnabled(false);
                    fiveFour.setVisible(false);
                    fiveFive.setEnabled(false);
                    fiveFive.setVisible(false);
                    fiveSix.setEnabled(false);
                    fiveSix.setVisible(false);
                    sixOne.setEnabled(false);
                    sixOne.setVisible(false);
                    sixTwo.setEnabled(false);
                    sixTwo.setVisible(false);
                    sixThree.setEnabled(false);
                    sixThree.setVisible(false);
                    sixFour.setEnabled(false);
                    sixFour.setVisible(false);
                    sixFive.setEnabled(false);
                    sixFive.setVisible(false);
                    sixSix.setEnabled(false);
                    sixSix.setVisible(false);
                }
            }
        }
        pack();
    }
}


