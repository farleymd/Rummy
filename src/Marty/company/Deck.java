package Marty.company;

import java.util.Collections;

/**
 * Represents a standard 52 card deck
 */
public class Deck extends CardPile {

    // creates a new deck of cards and shuffles the deck
    public Deck() {
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 0; rank <= 12; rank++) {
                addCard(new Card(rank, suit));
            }
        }

        Collections.shuffle(cards);
    }

    // add all the cards from the discard pile to the deck
    public Deck(DiscardPile discardPile) {
        if (!this.cards.isEmpty()) {
            throw new IllegalStateException("Deck(DiscardPile discardPile) called when the Deck is not empty.");
        }

        this.cards.addAll(discardPile.cards);
    }
}
