package Marty.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by marty.farley on 3/1/2015.
 */
public class DiscardPile {
    private ArrayList<Card> discardPile;

    public DiscardPile() {
        discardPile = new ArrayList<Card>();
    }

    public void setDiscardPile(ArrayList<Card> discardPile) {
        this.discardPile = discardPile;
    }

    public Card drawFromDiscard()
    {
        int index = discardPile.size();
        return discardPile.remove(discardPile.size() -1);
    }

    public void displayDiscard(Deck deck){
        Card discardCard = deck.drawFromDeck();
        System.out.println("Discard Card: " + discardCard);
        discardPile.add(discardCard);
        return;
    }
}
