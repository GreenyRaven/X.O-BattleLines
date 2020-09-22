package ModelView;

import java.awt.*;

public class CardCollection {
    private Card[] cardCollection;

    public CardCollection (String gametype) {
        switch (gametype) {
            case "3x3" -> {
                cardCollection = new Card[9];
                for (int i = 0; i < 9; i++) {
                    cardCollection[i] = new Card(i, null);
                }
            }
            case "6x6" -> {
                cardCollection = new Card[36];
                for (int i = 0; i < 36; i++) {
                    cardCollection[i] = new Card(i, null);
                }
            }
        }
    }

    protected Card[] getCardCollection() {
        return cardCollection;
    }

    public class Card {
        private final int index;
        private boolean pushed;
        private Image cardIcon;
        private String iconType;

        protected Card (int index, Image baseImage) {
            this.index = index;
            setCardIcon(baseImage);
            setPushed(false);
            setIconType("blank");
        }

        public int getIndex() {
            return index;
        }

        protected boolean isPushed() {
            return pushed;
        }

        protected void setPushed(boolean pushed) {
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

        protected void setCardIcon(Image cardIcon) {
            this.cardIcon = cardIcon;
        }

        protected String getIconType() {
            return iconType;
        }

        protected void setIconType(String iconType) {
            this.iconType = iconType;
        }
    }
}
