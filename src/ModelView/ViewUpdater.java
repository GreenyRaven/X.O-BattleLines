package ModelView;

import View.Main_Board;

public class ViewUpdater {
    Main_Board View;
    //transports Model results to the View
    //updates CardCollection using accumulated Model results
    public ViewUpdater(Main_Board View) {
        this.View = View;
    }

    public void toNextStep() {
        View.nextStep();
    }

    public void endThisGame(int[] winningCards, String withStatus) {
        for (int index : winningCards) {
            CardCollection.GameBoard.getCardCollection()[index].setIconType(withStatus);
        }
        View.endGame();
    }
}
