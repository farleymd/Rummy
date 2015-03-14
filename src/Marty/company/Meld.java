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

    public void addIndividualCard(Card meldCard, int arrayIndex, Hand playerHand, Player player) {
        //TODO add individual run checker

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

                found = false;
            }else {
                System.out.println(ANSI_black + "That card cannot be melded to that group.");
                found = false;
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
