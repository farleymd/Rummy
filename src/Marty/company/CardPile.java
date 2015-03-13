package Marty.company;

import java.util.LinkedList;

/**
 * A superclass that represents a pile of cards
 */
public abstract class CardPile {
    protected LinkedList<Card> cards;

    // creates an empty card pile
    public CardPile() {
        cards = new LinkedList<Card>();
    }

    // deal the top card from the pile
    public Card deal() {
        return cards.pop();
    }

    // add a card to the top of the pile
    public void addCard(Card card) {
        cards.push(card);
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
