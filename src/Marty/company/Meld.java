package Marty.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by marty.farley on 3/12/2015.
 */
public class Meld {

    //collection of the group and run arrays as if displayed on a desktop
    private ArrayList<ArrayList<Card>> meldDesktop;
    private String ANSI_black = "\u001B[30m";

    public Meld() {

        this.meldDesktop = new ArrayList<ArrayList<Card>>();
    }

    public void addMeldGroup(ArrayList groupToAdd){
        meldDesktop.add(groupToAdd);
        return;
    }

    public void addMeldRun(ArrayList runToAdd){
        meldDesktop.add(runToAdd);
    }

    //ask the user if they want to attach the group to the meld
    public void runTheGroup(Card yp, String playerName, ArrayList groupToAdd,
                            Meld meldDesktop, Hand playerHand, Player player){
        Scanner scanner = new Scanner(System.in);
        String[] ranks = yp.getRanks();
        int rank = yp.getRank();
        String rankString = ranks[rank];

        if (playerName.equalsIgnoreCase("humanPlayer")){
            System.out.println(ANSI_black + "You have a group of " + rankString + "s! Do you want to meld the group? Y or N");
            String playerAnswer = scanner.next();
            if (playerAnswer.equalsIgnoreCase("Y")){
                meldDesktop.addMeldGroup(groupToAdd);

                //remove the cards from the player's hand
                Iterator<Card> testRoll = groupToAdd.iterator();
                while (testRoll.hasNext()){
                    int points = 0;
                    Card removeCard = testRoll.next();
                    playerHand.removeCard(removeCard);
                    points = removeCard.pointValue() + points;
                    player.setScore(points);
                }
                //display the card desktop
                meldDesktop.printMelds();
                playerHand.displayHand();
            }
        } else {
            meldDesktop.addMeldGroup(groupToAdd);

            Iterator<Card> testRoll = groupToAdd.iterator();
            while (testRoll.hasNext()){
                int points = 0;
                Card removeCard = testRoll.next();
                playerHand.removeCard(removeCard);
                points = removeCard.pointValue() + points;
                player.setScore(points);
            }
        }
    }

    public void addIndividualGroupCard(Card meldCard, int arrayIndex, Hand playerHand, Player player) {

        int points = 0;
        ArrayList<Card> groupCheck = new ArrayList<Card>();
        groupCheck = meldDesktop.get(arrayIndex);


        //check if card belongs to the selected group
        Iterator<Card> groupChecker =groupCheck.iterator();
        boolean found = true;    //way to break out of iteration
        while (groupChecker.hasNext() && found == true) {
            Card groupCheckCard = groupChecker.next();
            int cardRank = meldCard.getRank();
            int groupRank = groupCheckCard.getRank();

            if (cardRank == groupRank) {  //rank of the card matches rank within the selected group
                //add the card to the meld
                meldDesktop.get(arrayIndex).add(meldCard);
                //remove individual card from player's hand, get points for it
                playerHand.removeCard(meldCard);
                points = meldCard.pointValue() + points;
                player.setScore(points);
                System.out.println("That card was added to that group.");

                found = false;
            }else {
                System.out.println(ANSI_black + "That card cannot be melded to that group.");
                found = false;
            }
        }
    }

    public void addIndividualRunCard(Card meldCard, int arrayIndex, Hand playerHand, Player player) {

        int points = 0;
        ArrayList<Card> groupCheck = new ArrayList<Card>();
        groupCheck = meldDesktop.get(arrayIndex);

        Iterator<Card> groupChecker = groupCheck.iterator();
        boolean found = true;    //way to break out of iteration
        //while (groupChecker.hasNext() && found == true) {
            //Card groupCheckCard = groupChecker.next();

           Card groupCheckCard = groupCheck.get(groupCheck.size()-1);

            int cardRank = meldCard.getRank();
            int groupRank = groupCheckCard.getRank();

            int cardSuit = meldCard.getSuit();
            int groupSuit = groupCheckCard.getSuit();

            if (cardSuit == groupSuit){ //belongs to the same suit
                if (Math.abs(groupRank - cardRank) == 1){ //ranks are within 1 of each other
                    //add the card to the meld
                    meldDesktop.get(arrayIndex).add(meldCard);
                    //remove individual card from player's hand, get points for it
                    playerHand.removeCard(meldCard);
                    points = meldCard.pointValue() + points;
                    player.setScore(points);
                    System.out.println("That card was added to that run.");
                    found = false;
                } else {
                    System.out.println("That card doesn't belong to this run.");
                    found = false;
                }
            } else {
                System.out.println("That card doesn't have the same suit.");
                found = false;
            }

        }
    //}

    public void addComputerGroup(Hand computerHand, Player computerPlayer){
        //TODO ADD RUN/GROUP FROM COMPUTER HAND

        int points = 0;
        ArrayList<Card> groupCheck = new ArrayList<Card>();


        for (int i = 0; i < this.meldDesktop.size(); i++){
            groupCheck = meldDesktop.get(i);

            Iterator<Card> groupChecker =groupCheck.iterator();
            boolean found = true;
            while (groupChecker.hasNext() && found == true){
                Card groupCheckCard = groupChecker.next();

                for (int x = 0; x < computerHand.getSize(computerHand); x++){
                    Card meldCard = computerHand.getCard(x);

                    int groupRank = groupCheckCard.getRank();
                    int cardRank = meldCard.getRank();

                    if (cardRank == groupRank) {
                        meldDesktop.get(i).add(meldCard);
                        //remove individual card from player's hand, get points for it
                        computerHand.removeCard(meldCard);
                        points = meldCard.pointValue() + points;
                        computerPlayer.setScore(points);

                        found = false;
                    }
                }
            }
        }
    }

    public void addComputerRun(Hand computerHand, Player computerPlayer){

        int points = 0;
        ArrayList<Card> runCheck = new ArrayList<Card>();


        for (int i = 0; i < this.meldDesktop.size(); i++) {
            runCheck = meldDesktop.get(i);

            Iterator<Card> groupChecker = runCheck.iterator();
            boolean found = true;
            while (groupChecker.hasNext() && found == true) {
                Card groupCheckCard = groupChecker.next();
                for (int x = 0; x < computerHand.getSize(computerHand); x++) {

                    Card meldCard = computerHand.getCard(x);
                    int cardRank = meldCard.getRank();
                    int cardSuit = meldCard.getSuit();

                    Card runCheckCard = runCheck.get(runCheck.size() - 1);
                    int runRank = runCheckCard.getRank();
                    int runSuit = runCheckCard.getSuit();

                    if (cardSuit == runSuit) { //belongs to the same suit
                        if (Math.abs(runRank - cardRank) == 1) { //ranks are within 1 of each other
                            //add the card to the meld
                            meldDesktop.get(i).add(meldCard);
                            //remove individual card from player's hand, get points for it
                            computerHand.removeCard(meldCard);
                            points = meldCard.pointValue() + points;
                            computerPlayer.setScore(points);
                        }
                    }
                }
            }
        }
    }


    public boolean meldDesktopIsEmpty(){
        boolean isEmpty = false;

        if (meldDesktop.size() == 0){
            return true;
        } else {
            return false;
        }
    }

    public void printMelds(){
        System.out.println("# Melds: ");
        for (int i = 0; i < meldDesktop.size(); i++){
            int identifier = i+1;
            System.out.print(ANSI_black + identifier + ". " + meldDesktop.get(i).toString() + "\n");
        }
    }

}
