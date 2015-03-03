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
        cards = new ArrayList<Card>();
        for (int a= 0; a <= 3; a++)
        {
            for (int b = 0; b <= 12; b++)
            {
                cards.add(new Card(b, a));
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
}
