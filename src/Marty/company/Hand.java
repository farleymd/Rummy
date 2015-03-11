package Marty.company;

import java.util.*;

/**
 * Represents a hand of cards
 */
public class Hand {
    private ArrayList<Card> cards;

    // creates a new empty hand
    public Hand() {
        this.cards = new ArrayList<Card>(10);
    }

    // returns the card at the position in the hand
    public Card getCard (int index){
        Card returnCard = cards.get(index);
        return returnCard;
    }

    // add a card to the hand
    public void addCard (Card newCard){
        cards.add(newCard);
    }

    // remove a card from the hand
    public void removeCard (Card newCard){
        cards.remove(newCard);
    }

    // create a new empty hand
    public void newHand() {
        cards = new ArrayList<Card>(10);
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < cards.size(); i++) {
            stringBuilder.append(cards.get(i));

            // don't add a space after the last card
            if (i != cards.size()) {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }
}
