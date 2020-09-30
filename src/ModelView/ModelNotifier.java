package ModelView;

import Model.tictactoe;

import javax.swing.*;

import static ModelView.CardCollection.GameBoard;

public class ModelNotifier {
    private Model.tictactoe GameLogic;
    private ViewUpdater UIUpdater;
    //transports View data to the Model
    //updates CardCollection using View data

    public ModelNotifier(String gametype, JLabel[] labelCollection, ViewUpdater UIUpdater) {
        GameBoard = new CardCollection(gametype, labelCollection);
        GameLogic = new tictactoe();
        this.UIUpdater = UIUpdater;
    }

    public void startNewGame() {
        GameLogic = new tictactoe();
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
