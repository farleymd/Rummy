package Marty.company;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Represents a standard 52 card deck
 */
public class Deck {
    private LinkedList<Card> cards;

    // creates a new deck and populates it with 52 cards
    public Deck() {
        this.cards = new LinkedList<Card>();
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 0; rank <= 12; rank++) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    // adds a collection of cards to the deck
    public void addCards(Collection<Card> cards) {
        this.cards.addAll(cards);
    }

    // shuffles the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // draw a card from the deck
    public Card draw() {
        return cards.pop();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }
}
