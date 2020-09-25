package ModelView;

import javax.swing.*;

import static ModelView.CardCollection.GameBoard;

public class ModelNotifier {
    //transports View data to the Model
    //updates CardCollection using View data

    public ModelNotifier(String gametype, JLabel[] labelCollection) {
        GameBoard = new CardCollection(gametype, labelCollection);
    }

    public void commitTransfer(JLabel affectedLabel, String gameStep) {
        toModel(GameBoard, affectedLabel, gameStep);
        toCardCollection(affectedLabel, gameStep);
    }

    private void toModel(CardCollection GameBoard, JLabel affectedLabel, String gameStep) {
    }

    private void toCardCollection(JLabel affectedLabel, String gameStep) {
    }
}
