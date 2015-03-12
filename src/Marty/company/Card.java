package Marty.company;

/**
 * Created by marty.farley on 3/1/2015.
 */
public class Card implements Comparable<Card> {

    private int rank, suit;

    private static final char SPADE     = '\u2660';
    private static final char CLUB      = '\u2663';
    private static final char HEART     = '\u2665';
    private static final char DIAMOND   = '\u2666';

    private static char[] suits = {HEART, SPADE, DIAMOND, CLUB};
    private static String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static String[] getRanks() {
        return ranks;
    }

    public static void setRanks(String[] ranks) {
        Card.ranks = ranks;
    }

    public static char[] getSuits() {
        return suits;
    }

    public static void setSuits(char[] suits) {
        Card.suits = suits;
    }

    public
    @Override
    String toString() {
        return ranks[rank] + suits[suit];
    }

    public int getRank() {

        return rank;
    }

    public int getSuit() {
        return suit;
    }

    public int compareTo(Card anotherCard) {

        if (this.rank < anotherCard.getRank()) {
            return 1;
        } else if (this.rank > anotherCard.getRank()) {
            return -1;
        } else {
            return 0;
        }
    }
}



