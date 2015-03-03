package Marty.company;

/**
 * Created by marty.farley on 3/1/2015.
 */
public class Card implements Comparable<Card> {

    private int rank, suit;

    private static String[] suits = {"hearts", "spades", "diamonds", "clubs"};
    private static String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

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

    public static String[] getSuits() {
        return suits;
    }

    public static void setSuits(String[] suits) {
        Card.suits = suits;
    }

    public
    @Override
    String toString() {
        return ranks[rank] + " of " + suits[suit];
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



