package Marty.company;

import java.util.Random;
import java.util.ArrayList;

/**
 * Created by marty.farley on 3/1/2015.
 */
public class Deck {

    private ArrayList<Card> cards;

    Deck()
    {
        this.cards = new ArrayList<Card>();
        for (int suit= 0; suit <= 3; suit++)
        {
            for (int rank = 0; rank <= 12; rank++)
            {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Card drawFromDeck()
    {
        Random generator = new Random();
        int index = generator.nextInt(cards.size());
        return cards.remove(index);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int getSize(){
        int size = cards.size();

        return size;
    }
}
