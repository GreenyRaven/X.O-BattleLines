package ModelView;

import javax.swing.*;
import java.awt.*;

public final class CardCollection {
    static CardCollection GameBoard;
    private Card[] CardCollection;

    CardCollection (String gametype, JLabel[] labelCollection) {
        switch (gametype) {
            case "3x3" -> {
                CardCollection = new Card[9];
                for (int i = 0; i < 9; i++) {
                    CardCollection[i] = new Card(null, labelCollection[i]);
                }
            }
            case "6x6" -> {
                CardCollection = new Card[36];
                for (int i = 0; i < 36; i++) {
                    CardCollection[i] = new Card(null, labelCollection[i]);
                }
            }
        }
    }

    Card[] getCardCollection() {
        return CardCollection;
    }

    public final class Card {
        private boolean pushed;
        private Image cardIcon;
        private String iconType;
        private JLabel capsule;

        Card (Image baseImage, JLabel capsule) {
            setCardIcon(baseImage);
            setPushed(false);
            setIconType("blank");
            setCapsule(capsule);
        }

        public boolean isPushed() {
            return pushed;
        }

        void setPushed(boolean pushed) {
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

        void setCardIcon(Image cardIcon) {
            this.cardIcon = cardIcon;
        }

        public String getIconType() {
            return iconType;
        }

        void setIconType(String iconType) {
            this.iconType = iconType;
        }

        public JLabel getCapsule() {
            return capsule;
        }

        private void setCapsule(JLabel capsule) {
            this.capsule = capsule;
        }
    }
}
