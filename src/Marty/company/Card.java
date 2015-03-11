package Marty.company;

/**
 * Represents a card in a standard 52 card deck
 */
public class Card implements Comparable<Card> {

    private int rank, suit;

    // unicode values for suit symbols
    private static final char SPADE     = '\u2660';
    private static final char CLUB      = '\u2663';
    private static final char HEART     = '\u2665';
    private static final char DIAMOND   = '\u2666';

    // representations for the card when we need to print the card
    private static final char[] suits = {HEART, SPADE, DIAMOND, CLUB};
    private static final String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    // creates a new card with the rank a suit
    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

    // sort by suit then rank in ascending order
    public int compareTo(Card anotherCard) {
        int suitComp = this.suit - anotherCard.suit;
        return suitComp != 0 ? suitComp : this.rank - anotherCard.rank;
    }

    // example: ♠J
    @Override
    public String toString() {
        return suits[suit] + ranks[rank];
    }
}



