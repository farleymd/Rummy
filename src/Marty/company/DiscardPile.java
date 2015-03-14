package Marty.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by marty.farley on 3/1/2015.
 */
public class DiscardPile {
    private ArrayList<Card> discardPile;
    private int size;

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
        System.out.println("Discard Pile: " + discardPile);
        return;
    }

    public void displayDiscardFirstTime(Deck deck){
        Card discardCard = deck.drawFromDeck();
        discardPile.add(discardCard);
        return;
    }

    public void addToDiscardPile(Card playerDiscardCard){
        discardPile.add(playerDiscardCard);
        return;
    }

    public Card lastDiscardCard(){
        Card lastDiscard = discardPile.get(size);
        return lastDiscard;
    }

    public boolean isEmpty() {

        return discardPile.isEmpty();
    }

    public int getSize(){
        this.size = discardPile.size();
        return size;
    }
}
