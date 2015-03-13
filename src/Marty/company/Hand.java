package Marty.company;

import java.util.*;

/**
 * Created by marty.farley on 3/1/2015.
 */
public class Hand {
    private ArrayList<Card> handCard = new ArrayList<Card>(10);

//    public Hand(ArrayList<Card> handCard) {
//        this.handCard = handCard;
//    }

    public Card getCard (int index){
        Card returnCard = handCard.get(index);
        return returnCard;
    }

    public ArrayList addCard (Card newCard){
        handCard.add(newCard);
        Collections.sort(handCard);
        return handCard;
    }

    public ArrayList removeCard (Card newCard){
        handCard.remove(newCard);
        Collections.sort(handCard);
        return handCard;
    }

    public ArrayList testBuild(Deck deck){
        Card card1 = new Card(0,0);
        handCard.add(card1);

        Card card2 = new Card(0,1);
        handCard.add(card2);

        Card card3 = new Card(0,2);
        handCard.add(card3);

        Card card4 = new Card(0,3);
        handCard.add(card4);

        Card card5 = new Card(1,0);
        handCard.add(card5);

        Card card6 = new Card(1,1);
        handCard.add(card6);

        Card card7 = new Card(4,3);
        handCard.add(card7);

        Card card8 = new Card(10,3);
        handCard.add(card8);

        Card card9 = new Card(8,3);
        handCard.add(card9);

        return handCard;
    }

    public ArrayList buildHand(Deck deck){

        for (int i = 0; i < 10; i++)
        {
            Card newCard = deck.drawFromDeck();
            handCard.add(newCard);
        }
        return handCard;
    }

    public void displayHand(){
        Collections.sort(handCard);
        for (int i = 0; i < handCard.size(); i++){
            int identifier = i+1;
            System.out.print(identifier + ". " + handCard.get(i).toString() + " ");
        }
        System.out.println("\n");
    }

    public boolean notEmpty(){
        boolean empty = false;
        int handSize = handCard.size();
        if (handSize != 0){
            empty = false;
        } else {
            empty = true;
        }
        return empty;
    }

    public void checkForRun() {
        boolean isRun = false;
        int numberOfCards = handCard.size();
        if (numberOfCards < 3 || numberOfCards > 13) {
            isRun = false;
        }

        // make sure cards are sorted
        Collections.sort(handCard);

        // compare all suits to the first card, and ranks starting with the first
        int suitCompare = handCard.get(0).getSuit();
        int previousRank = handCard.get(0).getRank();
        for (int cardNum = 1; cardNum < numberOfCards; cardNum++) {
            int thisSuit = handCard.get(cardNum).getSuit();
            int thisRank = handCard.get(cardNum).getRank();

            if (thisSuit != suitCompare) {
                isRun = false;
            }

            if (Math.abs(thisRank - previousRank) != 1) {
                isRun = false;
            } else {
                previousRank = thisRank;
            }
        }

        // all tests passed
        isRun = true;

        if (isRun == true){

        }

    }

    //tests if three or four cards are the same group
    public void checkForGroup(String playerName, Meld meldDesktop, Hand playerHand) {
        ArrayList<Card> rankAce = new ArrayList<Card>();
        ArrayList<Card> rank2 = new ArrayList<Card>();
        ArrayList<Card> rank3 = new ArrayList<Card>();
        ArrayList<Card> rank4 = new ArrayList<Card>();
        ArrayList<Card> rank5 = new ArrayList<Card>();
        ArrayList<Card> rank6 = new ArrayList<Card>();
        ArrayList<Card> rank7 = new ArrayList<Card>();
        ArrayList<Card> rank8 = new ArrayList<Card>();
        ArrayList<Card> rank9 = new ArrayList<Card>();
        ArrayList<Card> rank10 = new ArrayList<Card>();
        ArrayList<Card> rankJack = new ArrayList<Card>();
        ArrayList<Card> rankQueen = new ArrayList<Card>();
        ArrayList<Card> rankKing = new ArrayList<Card>();

        Iterator<Card> iter = handCard.iterator();
        boolean found = false;    //way to break out of iteration
        while (iter.hasNext() && found == false) {
            Card yp = iter.next();
            int rank = yp.getRank();
            switch (rank) {
                case 0:
                    rankAce.add(yp);
                    if (rankAce.size() == 4 || rankAce.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rankAce, meldDesktop, playerHand);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 1:
                    rank2.add(yp);
                    if (rank2.size() == 4 || rank2.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank2, meldDesktop, playerHand);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 2:
                    rank3.add(yp);
                    if (rank3.size() == 4 || rank3.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank3, meldDesktop, playerHand);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 3:
                    rank4.add(yp);
                    if (rank4.size() == 4 || rank4.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank4, meldDesktop, playerHand);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 4:
                    rank5.add(yp);
                    if (rank5.size() == 4 || rank5.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank5, meldDesktop, playerHand);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 5:
                    rank6.add(yp);
                    if (rank6.size() == 4 || rank6.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank6, meldDesktop, playerHand);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 6:
                    rank7.add(yp);
                    if (rank7.size() == 4 || rank7.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank7, meldDesktop, playerHand);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 7:
                    rank8.add(yp);
                    if (rank8.size() == 4 || rank8.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank8, meldDesktop, playerHand);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 8:
                    rank9.add(yp);
                    if (rank9.size() == 4 || rank9.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank9, meldDesktop, playerHand);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 9:
                    rank10.add(yp);
                    if (rank10.size() == 4 || rank10.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank10, meldDesktop, playerHand);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 10:
                    rankJack.add(yp);
                    if (rankJack.size() == 4 || rankJack.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rankJack, meldDesktop, playerHand);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 11:
                    rankQueen.add(yp);
                    if (rankQueen.size() == 4 || rankQueen.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rankQueen, meldDesktop, playerHand);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 12:
                    rankKing.add(yp);
                    if (rankKing.size() == 4 || rankKing.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rankKing, meldDesktop, playerHand);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
            }
        }
    }

}