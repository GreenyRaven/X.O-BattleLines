package ModelView;

import Model.Tictactoe;

import javax.swing.*;

import static ModelView.CardCollection.GameBoard;

//transports View data to the Model
//updates CardCollection using View data
public class ModelNotifier {
    private Model.Tictactoe GameLogic;
    private final ViewUpdater UIUpdater;

    public ModelNotifier(String AIDifficulty, String gameType, JLabel[] labelCollection, ViewUpdater UIUpdater) {
        GameBoard = new CardCollection(gameType, labelCollection);
        GameLogic = new Tictactoe(UIUpdater, AIDifficulty, gameType);
        this.UIUpdater = UIUpdater;
    }

    public ModelNotifier(String gameType, JLabel[] labelCollection, ViewUpdater UIUpdater) {
        GameBoard = new CardCollection(gameType, labelCollection);
        GameLogic = new Tictactoe(UIUpdater, gameType);
        this.UIUpdater = UIUpdater;
    }

    public void startNewGame(String gameType) {
        GameLogic = new Tictactoe(UIUpdater, gameType);
        GameBoard.updateCards(GameBoard.getCardCollection(), "Blank");
    }

    public void startNewGame(String AIGameDifficulty, String gameType) {
        GameLogic = new Tictactoe(UIUpdater, AIGameDifficulty, gameType);
        GameBoard.updateCards(GameBoard.getCardCollection(), "Blank");
    }

    public void commitTransfer(JLabel affectedLabel, String gameStep) {
        int indexOfCard = findCardIndexByLabel(affectedLabel);
        try {
            if (!GameBoard.getCardCollection()[indexOfCard].isPushed()) {
                toCardCollection(indexOfCard, gameStep);
                toModel(indexOfCard, gameStep);
            }
        } catch (Exception ignored) {
        }
    }

    private void toModel(int index, String gameStep) {
        GameLogic.play(index, gameStep, UIUpdater);
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
