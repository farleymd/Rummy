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
        return cards.get(index);
    }

    public int getPoints() {
        int points = 0;
        for (Card card : cards) {
            points += card.pointValue();
        }
        return points;
    }

    // add a card to the hand
    public void addCard (Card newCard){
        cards.add(newCard);
        Collections.sort(cards);
    }

    // remove a card from the hand
    public void removeCard (int position){
        cards.remove(position);
        Collections.sort(cards);
    }

    // remove a group of cards
    public void removeCards(ArrayList<Card> cards) {
        cards.removeAll(cards);
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
            if (i != cards.size() - 1) {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }
}
