package View;

import ModelView.ModelNotifier;
import ModelView.ViewUpdater;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class Main_Board extends JFrame {
    public JLabel s_11;
    public JLabel s_12;
    public JLabel s_13;
    public JLabel s_14;
    public JLabel s_15;
    public JLabel s_16;
    public JLabel s_21;
    public JLabel s_22;
    public JLabel s_23;
    public JLabel s_24;
    public JLabel s_25;
    public JLabel s_26;
    public JLabel s_31;
    public JLabel s_32;
    public JLabel s_33;
    public JLabel s_34;
    public JLabel s_35;
    public JLabel s_36;
    public JLabel s_41;
    public JLabel s_42;
    public JLabel s_43;
    public JLabel s_44;
    public JLabel s_45;
    public JLabel s_46;
    public JLabel s_51;
    public JLabel s_52;
    public JLabel s_53;
    public JLabel s_54;
    public JLabel s_55;
    public JLabel s_56;
    public JLabel s_61;
    public JLabel s_62;
    public JLabel s_63;
    public JLabel s_64;
    public JLabel s_65;
    public JLabel s_66;
    private JPanel main_panel;
    private JComboBox gamediffBox;
    private JComboBox gametypeBox;
    private JButton launchButton;
    private JPanel controlPanel;
    private JPanel baseGamePanel;
    private JPanel northGamePanel;
    private JPanel westGamePanel;
    private JPanel eastGamePanel;
    private JLabel gameStep;
    private JButton newGameButton;
    private JLabel winnerLabel;
    private JPanel endGamePanel;
    private JLabel step;
    private JLabel nullWinCountLabel;
    private JLabel crossWinCountLabel;

    ModelNotifier ModelNotifier;
    ViewUpdater UIUpdater;
    private boolean locked = false;
    private int crossWinCount = 0;
    private int nullWinCount = 0;
    private String gametype;
    private String gameDifficulty;

    public Main_Board() {
        getContentPane().add(main_panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLabelVisibility("6x6", false, false);
        setLabelListeners();
        step.setVisible(false);
        gameStep.setVisible(false);
        endGamePanel.setVisible(false);
        launchButton.addActionListener(e -> setGameBoard((String) Objects.requireNonNull(gametypeBox.getSelectedItem())));
        newGameButton.addActionListener(e -> newGame());
        pack();
        setVisible(true);
    }

    public void nextStep() {
        locked = false;
    }

    public void endGame() {
        if (gameStep.getText().equals("Нолики")) {
            winnerLabel.setText("Победа за нулями!");
            nullWinCount++;
            nullWinCountLabel.setText(String.valueOf(nullWinCount));
        } else {
            winnerLabel.setText("Победа за крестами!");
            crossWinCount++;
            crossWinCountLabel.setText(String.valueOf(crossWinCount));
        }
        pack();
        endGamePanel.setVisible(true);
    }

    private void newGame() {
        //операции по старту новой игры
        //
        ModelNotifier.startNewGame();
        //
        //со стороны Main_Board:
        endGamePanel.setVisible(false);
        setLabelVisibility(gametype, true, true);
        controlPanel.setVisible(false);
        locked = false;
        pack();
    }

    private void sendData(JLabel affectedLabel) {
        if (!locked) {
            if (gameStep.getText().equals("Нолики")) {
                ModelNotifier.commitTransfer(affectedLabel, gameStep.getText());
                gameStep.setText("Крестики");
                locked = true;
            } else {
                ModelNotifier.commitTransfer(affectedLabel, gameStep.getText());
                gameStep.setText("Нолики");
                locked = true;
            }
        }
    }


    private JLabel[] returnFullLabelCollection() {
        JLabel[] labelCollection = new JLabel[36];
        labelCollection[0] = s_11;
        labelCollection[1] = s_12;
        labelCollection[2] = s_13;
        labelCollection[3] = s_14;
        labelCollection[4] = s_15;
        labelCollection[5] = s_16;
        labelCollection[6] = s_21;
        labelCollection[7] = s_22;
        labelCollection[8] = s_23;
        labelCollection[9] = s_24;
        labelCollection[10] = s_25;
        labelCollection[11] = s_26;
        labelCollection[12] = s_31;
        labelCollection[13] = s_32;
        labelCollection[14] = s_33;
        labelCollection[15] = s_34;
        labelCollection[16] = s_35;
        labelCollection[17] = s_36;
        labelCollection[18] = s_41;
        labelCollection[19] = s_42;
        labelCollection[20] = s_43;
        labelCollection[21] = s_44;
        labelCollection[22] = s_45;
        labelCollection[23] = s_46;
        labelCollection[24] = s_51;
        labelCollection[25] = s_52;
        labelCollection[26] = s_53;
        labelCollection[27] = s_54;
        labelCollection[28] = s_55;
        labelCollection[29] = s_56;
        labelCollection[30] = s_61;
        labelCollection[31] = s_62;
        labelCollection[32] = s_63;
        labelCollection[33] = s_64;
        labelCollection[34] = s_65;
        labelCollection[35] = s_66;
        return labelCollection;
    }

    private JLabel[] returnBasicLabelCollection() {
        JLabel[] labelCollection = new JLabel[9];
        labelCollection[0] = s_11;
        labelCollection[1] = s_12;
        labelCollection[2] = s_13;
        labelCollection[3] = s_21;
        labelCollection[4] = s_22;
        labelCollection[5] = s_23;
        labelCollection[6] = s_31;
        labelCollection[7] = s_32;
        labelCollection[8] = s_33;
        return labelCollection;
    }

    private void setLabelListeners() {
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sendData((JLabel) e.getSource());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        JLabel[] labelCollection = returnFullLabelCollection();
        for (JLabel jLabel : labelCollection) {
            jLabel.addMouseListener(listener);
        }
    }

    private void setGameBoard(String gametype) {
        this.gametype = gametype;
        gameDifficulty = (String) gamediffBox.getSelectedItem();
        controlPanel.setVisible(false);
        endGamePanel.setVisible(false);
        crossWinCountLabel.setText("0");
        nullWinCountLabel.setText("0");
        step.setVisible(true);
        gameStep.setVisible(true);
        switch (gametype) {
            case "3x3" -> {
                setLabelVisibility("3x3", true, true);
                UIUpdater = new ViewUpdater(this);
                ModelNotifier = new ModelNotifier("3x3", returnBasicLabelCollection(), UIUpdater);
            }
            case "6x6" -> {
                setLabelVisibility("6x6", true, true);
                UIUpdater = new ViewUpdater(this);
                ModelNotifier = new ModelNotifier("6x6", returnFullLabelCollection(), UIUpdater);
            }
        }
        pack();
    }

    private void setLabelVisibility(String fieldSize, boolean unlocked, boolean visible) {
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


