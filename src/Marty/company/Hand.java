package Marty.company;

import java.util.*;

/**
 * Created by marty.farley on 3/1/2015.
 */
public class Hand {
    private ArrayList<Card> handCard = new ArrayList<Card>();
    private String ANSI_red = "\u001B[35m";
    private String ANSI_black = "\u001B[30m";

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
        //Collections.sort(handCard);
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
            System.out.print(ANSI_black + identifier + ". " + ANSI_red + handCard.get(i).toString() + " ");
        }
        System.out.println("\n");
    }

    public int getSize(Hand playerHand){
        int size = handCard.size();
        return size;
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

    public void handPoints(Hand playerHand, Player player){

        int handSize = playerHand.getSize(playerHand);
        int points = 0;

        for (int i = 0; i < handSize; i++) {
            Card cardForPoints = playerHand.getCard(i);
            points = cardForPoints.pointValue() + points;
            player.setScore(points);
        }
    }

    public boolean checkForRun(String playerName, Meld meldDesktop, Hand playerHand, Player player) {
        Scanner scanner = new Scanner(System.in);
        boolean isRun = false;
        int numberOfCards = playerHand.getSize(playerHand);
        if (numberOfCards < 3 || numberOfCards > 13) {
            isRun = false;
        }
        ArrayList<Card> cardRun = new ArrayList<Card>();
        int beginnerCardCounter = 0;

        // beginning with first card, compare all cards in the player's hand
        for (int cardNum = 1; cardNum < numberOfCards; cardNum++) {
            Card beginnerCard = playerHand.getCard(beginnerCardCounter);
            int suitCompare = beginnerCard.getSuit();
            int previousRank = beginnerCard.getRank();
            Card compareCard = playerHand.getCard(cardNum);
            int thisSuit = compareCard.getSuit();
            int thisRank = compareCard.getRank();

            if (thisRank != previousRank){  //if cards have the same rank, skip
                if (thisSuit != suitCompare) {
                    isRun = false;
                    beginnerCardCounter = beginnerCardCounter + 1;
                } else {
                    if (Math.abs(thisRank - previousRank) != 1) {
                        isRun = false;
                        beginnerCardCounter = beginnerCardCounter + 1;
                    } else {
                        if (cardRun.size() == 0) {
                            cardRun.add(beginnerCard);
                        }
                        cardRun.add(compareCard);

                        //compare the card after the last one checked in the player's hand to the last card in the array
                        for (int cardNum2 = cardNum + 1; cardNum2 < numberOfCards; cardNum2++) {
                            Card lastRunCard = cardRun.get(1);
                            int lastSuitCompare = lastRunCard.getSuit();
                            int lastRankCompare = lastRunCard.getRank();
                            Card newCompare = playerHand.getCard(cardNum2);
                            int newSuit = newCompare.getSuit();
                            int newRank = newCompare.getRank();

                            if (newSuit != lastSuitCompare) {
                                break;
                            } else {
                                if (Math.abs(newRank - lastRankCompare) != 1) {
                                    break;
                                } else {
                                    cardRun.add(newCompare);

                                    if (cardRun.size() >= 3) {
                                        if (playerName.equalsIgnoreCase("humanPlayer")) {
                                            System.out.println(ANSI_black + "You have a run! Would you like to meld the run? Y or N");
                                            String userAnswer = scanner.next();

                                            if (userAnswer.equalsIgnoreCase("Y")) {
                                                meldDesktop.addMeldRun(cardRun);
                                                isRun = true;

                                                //after adding the run to the meld desktop, remove them from the player's
                                                //hand and get points
                                                Iterator<Card> testRoll = cardRun.iterator();
                                                while (testRoll.hasNext()) {
                                                    int points = 0;
                                                    Card removeCard = testRoll.next();
                                                    playerHand.removeCard(removeCard);
                                                    points = removeCard.pointValue() + points;
                                                    player.setScore(points);
                                                }

                                                //display the card desktop
                                                meldDesktop.printMelds();
                                                playerHand.displayHand();
                                                numberOfCards = playerHand.getSize(playerHand);
                                            }
                                        } else {
                                            meldDesktop.addMeldRun(cardRun);
                                            isRun = true;

                                            Iterator<Card> testRoll = cardRun.iterator();
                                            while (testRoll.hasNext()) {
                                                int points = 0;
                                                Card removeCard = testRoll.next();
                                                playerHand.removeCard(removeCard);
                                                points = removeCard.pointValue() + points;
                                                player.setScore(points);
                                            }

                                            //don't display desktop during computer's turn
                                            numberOfCards = playerHand.getSize(playerHand);

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return isRun;
    }

    //tests if three or four cards are the same group
    public boolean checkForGroup(String playerName, Meld meldDesktop, Hand playerHand, Player player) {
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
                        meldDesktop.runTheGroup(yp, playerName, rankAce, meldDesktop, playerHand, player);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 1:
                    rank2.add(yp);
                    if (rank2.size() == 4 || rank2.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank2, meldDesktop, playerHand, player);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 2:
                    rank3.add(yp);
                    if (rank3.size() == 4 || rank3.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank3, meldDesktop, playerHand, player);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 3:
                    rank4.add(yp);
                    if (rank4.size() == 4 || rank4.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank4, meldDesktop, playerHand, player);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 4:
                    rank5.add(yp);
                    if (rank5.size() == 4 || rank5.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank5, meldDesktop, playerHand, player);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 5:
                    rank6.add(yp);
                    if (rank6.size() == 4 || rank6.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank6, meldDesktop, playerHand, player);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 6:
                    rank7.add(yp);
                    if (rank7.size() == 4 || rank7.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank7, meldDesktop, playerHand, player);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 7:
                    rank8.add(yp);
                    if (rank8.size() == 4 || rank8.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank8, meldDesktop, playerHand, player);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 8:
                    rank9.add(yp);
                    if (rank9.size() == 4 || rank9.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank9, meldDesktop, playerHand, player);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 9:
                    rank10.add(yp);
                    if (rank10.size() == 4 || rank10.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rank10, meldDesktop, playerHand, player);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 10:
                    rankJack.add(yp);
                    if (rankJack.size() == 4 || rankJack.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rankJack, meldDesktop, playerHand, player);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 11:
                    rankQueen.add(yp);
                    if (rankQueen.size() == 4 || rankQueen.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rankQueen, meldDesktop, playerHand, player);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
                case 12:
                    rankKing.add(yp);
                    if (rankKing.size() == 4 || rankKing.size() == 3) {
                        meldDesktop.runTheGroup(yp, playerName, rankKing, meldDesktop, playerHand, player);
                        found = true;
                    } else {
                        continue;
                    }
                    break;
            }
        }

        return found;
    }

    public void computerHandSort(){
        Collections.sort(handCard);
    }

    public int computerDraw(DiscardPile newDiscard, Hand computerHand){
        //get last card of discard pile
        int computerChoice = 0;

        Card lastDiscard = newDiscard.lastDiscardCard();
        int discardSuit = lastDiscard.getSuit();
        int discardRank = lastDiscard.getRank();
        int handSize = computerHand.getSize(computerHand);

        //compare lastDiscard to hand
        for (int i = 0; i < handSize; i++){
            Card compareCard = computerHand.getCard(i);
            int suit = compareCard.getSuit();
            int rank = compareCard.getRank();

            //if rank is the same or there's a difference of 1 between the card ranks
            if (discardRank == rank || (discardSuit == suit && Math.abs(discardRank - rank) == 1)){
                computerChoice = 1;
                break;
            } else {
                computerChoice = 0;
            }
        }

        return computerChoice;
    }

    public Card computerDiscard(Hand computerHand){

        int handSize = computerHand.getSize(computerHand);
        handSize = handSize - 1;
        Card discardCard = computerHand.getCard(handSize);
        boolean notGroup = false;
        boolean notRun = false;

        for (int i = 0; i< handSize; i++){
            Card originalCard = computerHand.getCard(i);
            int originalSuit = originalCard.getSuit();
            int originalRank = originalCard.getRank();

            Card compareCard = computerHand.getCard(i+1);
            int compareSuit = compareCard.getSuit();
            int compareRank = compareCard.getRank();

            //can't be group
            if (originalRank != compareRank){
                notGroup = true;
            } else {
                notGroup = false;
            }

            if (originalSuit != compareSuit){
                notRun = true;
            } else {
                notRun = false;
            }

           if ((notGroup == true) && (notRun == true)){
               discardCard = compareCard;
           }
        }
        return discardCard;
    }

    //TEST DECK BUILDS
    public ArrayList testBuild(Deck deck){
        Card card1 = new Card(8,2);
        handCard.add(card1);

        Card card2 = new Card(4,2);
        handCard.add(card2);

        Card card3 = new Card(0,2);
        handCard.add(card3);

        Card card4 = new Card(6,2);
        handCard.add(card4);

        Card card5 = new Card(7,2);
        handCard.add(card5);

        Card card6 = new Card(5,2);
        handCard.add(card6);

        Card card7 = new Card(12,3);
        handCard.add(card7);

        Card card8 = new Card(11,2);
        handCard.add(card8);

        Card card9 = new Card(8,3);
        handCard.add(card9);

        return handCard;
    }

    public ArrayList testBuild2(Deck deck) {
        Card card1 = new Card(2, 1);
        handCard.add(card1);

        Card card2 = new Card(4, 2);
        handCard.add(card2);

        Card card3 = new Card(0, 2);
        handCard.add(card3);

        Card card4 = new Card(9, 2);
        handCard.add(card4);

        return handCard;
    }

    public ArrayList testBuildEmpty (Deck deck){
        return handCard;
    }


}