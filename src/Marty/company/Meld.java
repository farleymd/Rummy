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

    public Meld() {
        this.meldDesktop = new ArrayList<ArrayList<Card>>();
    }

    public void addMeldGroup(ArrayList groupToAdd){
        meldDesktop.add(groupToAdd);
        return;
    }

    public void addMeldRun(){

    }

    public void printMelds(){
        System.out.println("# Melds: ");
        for (int i = 0; i < meldDesktop.size(); i++){
            int identifier = i+1;
            System.out.print( identifier + meldDesktop.get(i).toString() + "\n");
        }
    }

    //ask the user if they want to attach the group to the meld
    public void runTheGroup(Card yp, String playerName, ArrayList groupToAdd,
                            Meld meldDesktop, Hand playerHand, Player player){
        Scanner scanner = new Scanner(System.in);
        String[] ranks = yp.getRanks();
        int rank = yp.getRank();
        String rankString = ranks[rank];

        if (playerName.equalsIgnoreCase("humanPlayer")){
            System.out.println("You have a group of " + rankString + "s! Do you want to run the group? Y or N");
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

}
