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

        Card card7 = new Card(1,2);
        handCard.add(card7);

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
        System.out.println(handCard);
    }

    public ArrayList addCard (Card newCard){
        handCard.add(newCard);
        return handCard;
    }

    public void checkForRun() {

    }

    public void checkForGroup() {
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
        while (iter.hasNext()) {
            Card yp = iter.next();
            int rank = yp.getRank();
            switch (rank) {
                case 0:
                    rankAce.add(yp);
                    break;
                case 1:
                    rank2.add(yp);
                    break;
                case 2:
                    rank3.add(yp);
                    break;
                case 3:
                    rank4.add(yp);
                    break;
                case 4:
                    rank5.add(yp);
                    break;
                case 5:
                    rank6.add(yp);
                    break;
                case 6:
                    rank7.add(yp);
                    break;
                case 7:
                    rank8.add(yp);
                    break;
                case 8:
                    rank9.add(yp);
                    break;
                case 9:
                    rank10.add(yp);
                    break;
                case 10:
                    rankJack.add(yp);
                    break;
                case 11:
                    rankQueen.add(yp);
                    break;
                case 12:
                    rankKing.add(yp);
                    break;
            }
            if (rankAce.size() == 3 || rankAce.size() == 4) {
                runTheGroup(yp);
            } else if (rank2.size() == 3 ||rank2.size() == 4) {
                runTheGroup(yp);
            } else if (rank3.size() == 3 ||rank3.size() == 4) {
                runTheGroup(yp);
            } else if (rank4.size() == 3 ||rank4.size() == 4) {
                runTheGroup(yp);
            } else if (rank5.size() == 3 ||rank5.size() == 4) {
                runTheGroup(yp);
            } else if (rank6.size() == 3 ||rank6.size() == 4) {
                runTheGroup(yp);
            } else if (rank7.size() == 3 ||rank7.size() == 4) {
                runTheGroup(yp);
            } else if (rank8.size() == 3 ||rank8.size() == 4) {
                runTheGroup(yp);
            } else if (rank9.size() == 3 ||rank9.size() == 4) {
                runTheGroup(yp);
            } else if (rank10.size() == 3 ||rank10.size() == 4) {
                runTheGroup(yp);
            } else if (rankJack.size() == 3 ||rankJack.size() == 4) {
                runTheGroup(yp);
            } else if (rankQueen.size() == 3 ||rankQueen.size() == 4) {
                runTheGroup(yp);
            } else if (rankKing.size() == 3 ||rankKing.size() == 4) {
                runTheGroup(yp);
            }
        }
    }

    public void runTheGroup(Card yp){
        Scanner scanner = new Scanner(System.in);
        String[] ranks = yp.getRanks();
        int rank = yp.getRank();
        String rankString = ranks[rank];
        System.out.println("You have a group of " + rankString + "s! Do you want to run the group? Y or N");
        String playerAnswer = scanner.next();
        if (playerAnswer.equalsIgnoreCase("Y")){

        }
    }
}
