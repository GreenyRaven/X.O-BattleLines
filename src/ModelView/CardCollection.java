package ModelView;

import java.awt.*;

public class CardCollection {
    public Card[] gameboard;

    public CardCollection (String gametype) {
        switch (gametype) {
            case "3x3" -> {
                gameboard = new Card[9];
                for (int i=0;i<9;i++) {
                    gameboard[i] = new Card(i,null);
                }
            }
            case "6x6" -> {
                gameboard = new Card[36];
                for (int i=0;i<36;i++) {
                    gameboard[i] = new Card(i,null);
                }
            }
        }
    }
    class Card {
        private final int index;
        private boolean pushed;
        private Image cardIcon;
        private String iconType;

        public Card (int index, Image baseImage) {
            this.index = index;
            setCardIcon(baseImage);
            setPushed(false);
            setIconType("blank");
        }

        public int getIndex() {
            return index;
        }

        public boolean isPushed() {
            return pushed;
        }

        public void setPushed(boolean pushed) {
            this.pushed = pushed;
            if (pushed) {
                //set new image, lock this Card object
                //setIconType
                //setCardIcon
            }
        }

        public Image getCardIcon() {
            return cardIcon;
        }

        public void setCardIcon(Image cardIcon) {
            this.cardIcon = cardIcon;
        }

        public String getIconType() {
            return iconType;
        }

        public void setIconType(String iconType) {
            this.iconType = iconType;
        }
    }
}
