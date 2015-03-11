package Marty.company;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Holds the discards
 */
public class DiscardPile {
    private LinkedList<Card> cards;

    // creates a new empty discard pile
    public DiscardPile() {
        cards = new LinkedList<Card>();
    }

    // adds a card to the discard pile
    public void addCard(Card card){
        cards.add(card);
    }

    // returns a card from the discard pile
    public Card draw() {
        return cards.pop();
    }

    // reverses the card order and moves them to the deck
    public void moveToDeck(Deck deck) {
        Collections.reverse(cards);
        deck.addCards(cards);
        cards = new LinkedList<Card>();
    }
}
