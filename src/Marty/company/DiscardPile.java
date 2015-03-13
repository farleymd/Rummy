package Marty.company;

import java.util.Collections;

/**
 * Holds the discards
 */
public class DiscardPile extends CardPile {

    // returns the top card
    public Card topCard() {
        return cards.peek();
    }

    // reverses the order of the cards
    // used for when we need to "flip" this pile before moving the cards to the deck
    public void flipOver() {
        Collections.reverse(cards);
    }
}
