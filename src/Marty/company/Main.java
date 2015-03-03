package Marty.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Player humanPlayer = new Player();
        Player computerPlayer = new Player();
        Deck newDeck = new Deck();
        DiscardPile newDiscard = new DiscardPile();

//        Hand testHand = new Hand();
//        testHand.testBuild(newDeck);
//        testHand.displayHand();
//
//        testHand.checkForGroup();

        Hand humanHand = humanPlayer.getPlayerHand();
        humanHand.buildHand(newDeck);
        humanHand.displayHand();
        runTurns(humanHand, newDeck, newDiscard);

        Hand computerHand = computerPlayer.getPlayerHand();
        computerHand.buildHand(newDeck);
        computerHand.displayHand();
    }

    public static void runTurns(Object humanHand, Deck newDeck, DiscardPile newDiscard){
        //Player draw
        newDiscard.displayDiscard(newDeck);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to draw from the deck or the discard pile?");
        String userDraw = scanner.next();

        if (userDraw.equalsIgnoreCase("Deck")){
            Card newCard = newDeck.drawFromDeck();
        } else if (userDraw.equalsIgnoreCase("Discard")){
            //Card newCard = newDiscard.drawFromDiscard();

        }

    }

}



