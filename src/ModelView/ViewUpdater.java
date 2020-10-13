package ModelView;

import View.Main_Board;

//transports Model results to the View
//updates CardCollection using accumulated Model results
public class ViewUpdater {
    Main_Board View;

    public ViewUpdater(Main_Board View) {
        this.View = View;
    }

    public void toNextStep() {
        View.nextStep();
    }

    public void endThisGameWithWinner(int[] winningCards, String withStatus) {
        for (CardCollection.Card card : CardCollection.GameBoard.getCardCollection()) {
            for (int index : winningCards) {
                if (card.getIndex() == index) {
                    CardCollection.GameBoard.updateCards(new CardCollection.Card[]{card}, withStatus);
                }
            }
        }
        String winner = "";
        switch (withStatus) {
            case "Cross_Diagonal_LD", "Cross_Diagonal_RD", "Cross_Horizontal", "Cross_Vertical" -> winner = "Crosses";
            case "Null_Diagonal_LD", "Null_Diagonal_RD", "Null_Horizontal", "Null_Vertical" -> winner = "Nulls";
        }
        View.endGameWithWinner(winner);
    }

    public void endThisGameWithoutWinner() {
        View.endGameWithoutWinner();
    }

    public void makeAIStep(int toIndex, String withStatus) {
        for (CardCollection.Card card : CardCollection.GameBoard.getCardCollection()) {
            if (card.getIndex() == toIndex) {
                CardCollection.GameBoard.updateCards(new CardCollection.Card[]{card}, withStatus);
            }
        }
        View.nextAIStep();
    }
}
