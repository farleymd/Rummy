package Marty.company;

import java.util.ArrayList;
import java.util.Collections;

public class Meld {
    // TODO make card collection interface or superclass
    private ArrayList<Card> cards;

    public Meld(ArrayList<Card> cards) {
        if (!isValidMeld(cards)) throw new AssertionError();
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    // tests if a group of cards is a valid meld
    public static boolean isValidMeld(ArrayList<Card> cards) {
        if (isSequence(cards) || isGroup(cards)) {
            return true;
        }
        return false;
    }

    // a group is three or four cards of the same rank
    private static boolean isGroup(ArrayList<Card> cards) {
        int numberOfCards = cards.size();
        if (numberOfCards < 3 || numberOfCards > 4) {
            return false;
        }

        // compare ranks of all cards to the first card
        int firstCardRank = cards.get(0).getRank();
        // we compare the first card to itself here, but this looks cleaner than the alternative
        for (Card card : cards) {
            if (card.getRank() != firstCardRank) {
                return false;
            }
        }

        // all tests passed
        return true;
    }

    // a sequence is three or more cards of the same suit in consecutive order
    private static boolean isSequence(ArrayList<Card> cards) {
        int numberOfCards = cards.size();
        if (numberOfCards < 3 || numberOfCards > 13) {
            return false;
        }

        // make sure cards are sorted
        Collections.sort(cards);

        // compare all suits to the first card, and ranks starting with the first
        int suitCompare = cards.get(0).getSuit();
        int previousRank = cards.get(0).getRank();
        for (int cardNum = 1; cardNum < numberOfCards; cardNum++) {
            int thisSuit = cards.get(cardNum).getSuit();
            int thisRank = cards.get(cardNum).getRank();

            if (thisSuit != suitCompare) {
                return false;
            }

            if (Math.abs(thisRank - previousRank) != 1) {
                return false;
            } else {
                previousRank = thisRank;
            }
        }

        // all tests passed
        return true;
    }
}
