package ModelView;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;

public final class CardCollection {
    static CardCollection GameBoard;
    private Card[] CardCollection;

    CardCollection(String gametype, JLabel[] labelCollection) {
        switch (gametype) {
            case "3x3" -> {
                CardCollection = new Card[9];
                for (int i = 0; i < 9; i++) {
                    CardCollection[i] = new Card(labelCollection[i], i);
                }
            }
            case "6x6" -> {
                CardCollection = new Card[36];
                for (int i = 0; i < 36; i++) {
                    CardCollection[i] = new Card(labelCollection[i], i);
                }
            }
        }
    }

    Card[] getCardCollection() {
        return CardCollection;
    }

    void updateCards(Card[] thoseCards, String toStatus) {
        for (Card card : thoseCards) {
            switch (toStatus) {
                case "Нолики", "Null_Clear" -> card.setIconType("null");
                case "Крестики", "Cross_Clear" -> card.setIconType("cross");
                case "Blank" -> card.setIconType("blank");
                case "Cross_Diagonal_LD" -> card.setIconType("winCross1");
                case "Cross_Diagonal_RD" -> card.setIconType("winCross2");
                case "Cross_Horizontal" -> card.setIconType("winCross3");
                case "Cross_Vertical" -> card.setIconType("winCross4");
                case "Null_Diagonal_LD" -> card.setIconType("winNull1");
                case "Null_Diagonal_RD" -> card.setIconType("winNull2");
                case "Null_Horizontal" -> card.setIconType("winNull3");
                case "Null_Vertical" -> card.setIconType("winNull4");
            }
        }
    }

    class Card {
        private boolean pushed;
        private ImageIcon cardIcon;
        private String iconType;
        private JLabel capsule;
        private int index;

        Card(JLabel capsule, int index) {
            setCapsule(capsule);
            setIconType("blank");
            setIndex(index);
        }

        boolean isPushed() {
            return pushed;
        }

        private void setPushed(boolean pushed) {
            this.pushed = pushed;
        }

        ImageIcon getCardIcon() {
            return cardIcon;
        }

        private void setCardIcon(ImageIcon cardIcon) {
            this.cardIcon = cardIcon;
            capsule.setIcon(cardIcon);
        }

        String getIconType() {
            return iconType;
        }

        void setIconType(String iconType) {
            this.iconType = iconType;
            try {
                setCardIcon(new ImageIcon(ImageIO.read(new File(iconType + ".png"))));
                setPushed(!iconType.equals("blank"));
            } catch (IOException ex) {
                System.out.println("Image not found");
            }
        }

        JLabel getCapsule() {
            return capsule;
        }

        private void setCapsule(JLabel capsule) {
            this.capsule = capsule;
        }

        int getIndex() {
            return index;
        }

        private void setIndex(int index) {
            this.index = index;
        }
    }
}
