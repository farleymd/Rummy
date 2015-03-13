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

    // creates a deck with cards from another CardPile
    // mainly used for refilling the deck from the discard pile
    public Deck(CardPile discardPile) {
        this.cards = discardPile.cards;
    }
}
