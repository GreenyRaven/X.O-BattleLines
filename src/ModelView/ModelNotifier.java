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
        GameLogic = new tictactoe(UIUpdater, AIDifficulty);
        this.UIUpdater = UIUpdater;
    }

    public ModelNotifier(String gameType, JLabel[] labelCollection, ViewUpdater UIUpdater) {
        GameBoard = new CardCollection(gameType, labelCollection);
        GameLogic = new tictactoe(UIUpdater);
        this.UIUpdater = UIUpdater;
    }

    public void startNewGame() {
        GameLogic = new tictactoe(UIUpdater);
        GameBoard.updateCards(GameBoard.getCardCollection(), "blank");
    }

    public void startNewGame(String AIGameDifficulty) {
        GameLogic = new tictactoe(UIUpdater, AIGameDifficulty);
        GameBoard.updateCards(GameBoard.getCardCollection(), "blank");
    }

    public void commitTransfer(JLabel affectedLabel, String gameStep) {
        int indexOfCard = findCardIndexByLabel(affectedLabel);
        try {
            if (!GameBoard.getCardCollection()[indexOfCard].isPushed()) {
                toModel(indexOfCard, gameStep, UIUpdater);
                toCardCollection(indexOfCard, gameStep);
            }
        } catch (Exception ignored) {
        }
    }

    private void toModel(int index, String gameStep, ViewUpdater UIUpdater) {
        GameLogic.test(index, gameStep, UIUpdater);
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
