package Marty.company;

import java.util.Collection;

public abstract class Player {
    private String name;
    private int score;
    protected Hand hand;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    // deals the the starting hand
    public void dealHand(Deck deck, int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            hand.addCard(deck.deal());
        }
    }

    // draws a card from the deck or discard pile
    public void drawCard(CardPile cardPile) {
        hand.addCard(cardPile.deal());
    }

    public int getScore() {
        return score;
    }

    public void addToScore(int score) {
        this.score += score;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return name;
    }
}
