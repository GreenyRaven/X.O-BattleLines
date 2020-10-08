package ModelView;

import Model.tictactoe;

import javax.swing.*;

import static ModelView.CardCollection.GameBoard;

//transports View data to the Model
//updates CardCollection using View data
public class ModelNotifier {
    private Model.tictactoe GameLogic;
    private final ViewUpdater UIUpdater;

    public ModelNotifier(String AIDifficulty, String gameType, JLabel[] labelCollection, ViewUpdater UIUpdater) {
        GameBoard = new CardCollection(gameType, labelCollection);
        GameLogic = new tictactoe(UIUpdater, AIDifficulty, gameType);
        this.UIUpdater = UIUpdater;
    }

    public ModelNotifier(String gameType, JLabel[] labelCollection, ViewUpdater UIUpdater) {
        GameBoard = new CardCollection(gameType, labelCollection);
        GameLogic = new tictactoe(UIUpdater, gameType);
        this.UIUpdater = UIUpdater;
    }

    public void startNewGame(String gameType) {
        GameLogic = new tictactoe(UIUpdater, gameType);
        GameBoard.updateCards(GameBoard.getCardCollection(), "blank");
    }

    public void startNewGame(String AIGameDifficulty, String gameType) {
        GameLogic = new tictactoe(UIUpdater, AIGameDifficulty, gameType);
        GameBoard.updateCards(GameBoard.getCardCollection(), "blank");
    }

    public void commitTransfer(JLabel affectedLabel, String gameStep) {
        int indexOfCard = findCardIndexByLabel(affectedLabel);
        try {
            if (!GameBoard.getCardCollection()[indexOfCard].isPushed()) {
                toModel(indexOfCard, gameStep);
                toCardCollection(indexOfCard, gameStep);
            }
        } catch (Exception ignored) {
        }
    }

    private void toModel(int index, String gameStep) {
        GameLogic.test(index, gameStep);
    }

    private void toCardCollection(int desiredIndex, String gameStep) {
        GameBoard.updateCards(new CardCollection.Card[]{GameBoard.getCardCollection()[desiredIndex]}, gameStep);
    }

    private int findCardIndexByLabel(JLabel affectedLabel) {
        for (CardCollection.Card card : GameBoard.getCardCollection()) {
            if (card.getCapsule().equals(affectedLabel)) {
                return card.getIndex();
            }
        }
        return 0;
    }
}
