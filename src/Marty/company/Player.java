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

    // creates a new hand and deals a number of cards from a deck
    public void dealHand(Deck deck, int numberOfCards) {
        hand = new Hand();

        for (int i = 0; i < numberOfCards; i++) {
            hand.addCard(deck.deal());
        }
    }

    // draws a card from the deck or discard pile into this hand
    public void drawCard(CardPile cardPile) {
        hand.addCard(cardPile.deal());
    }

    // total score this player has earned
    public int getScore() {
        return score;
    }

    // add points to this players score
    public void addToScore(int score) {
        this.score += score;
    }

    @Override
    public String toString() {
        return name;
    }
}
