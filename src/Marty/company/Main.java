package Marty.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        printBanner();
        Player humanPlayer = new Player();
        Player computerPlayer = new Player();
        int humanScore = humanPlayer.getScore();
        int computerScore = computerPlayer.getScore();

        while (humanScore != 100 || computerScore != 100){
            newGame(humanPlayer, computerPlayer);
        }

//        Hand testHand = new Hand();
//        testHand.testBuild(newDeck);
//        testHand.displayHand();
//
//        testHand.checkForGroup();

    }

        public static void newGame(Player humanPlayer, Player computerPlayer){
            Scanner scanner = new Scanner(System.in);
            Deck newDeck = new Deck();
            DiscardPile newDiscard = new DiscardPile();

            Hand humanHand = humanPlayer.getPlayerHand();
            humanHand.buildHand(newDeck);

            Hand computerHand = computerPlayer.getPlayerHand();
            computerHand.buildHand(newDeck);

            newDiscard.displayDiscardFirstTime(newDeck);

            boolean humanHandEmpty = humanHand.notEmpty();
            boolean computerHandEmpty = computerHand.notEmpty();

            while (humanHandEmpty == false && computerHandEmpty == false){
            runHumanTurn(humanHand, newDeck, newDiscard, humanPlayer, computerPlayer);
            runComputerTurn(computerHand, newDeck, newDiscard, computerPlayer);
        }
    }

    public static void runHumanTurn(Hand playerHand, Deck newDeck, DiscardPile newDiscard, Player humanPlayer,
                                    Player computerPlayer){
        Scanner scanner = new Scanner(System.in);
        printBoard(newDeck, newDiscard, humanPlayer, computerPlayer);
        playerHand.displayHand();
        //Player draw
        System.out.println("Would you like to draw from the deck or the discard pile?");
        String userDraw = scanner.next();

        if (userDraw.equalsIgnoreCase("Deck")){
            Card newCard = newDeck.drawFromDeck();
            playerHand.addCard(newCard);
            playerHand.displayHand();

        } else if (userDraw.equalsIgnoreCase("Discard")){
            Card newCard = newDiscard.drawFromDiscard();
            playerHand.addCard(newCard);
            playerHand.displayHand();
        }

        playerHand.checkForGroup("humanPlayer");
        playerHand.checkForRun();

        System.out.println("Which card would you like to discard? Type the number beside the card.");
        int playerDiscardChoice = scanner.nextInt();
        Card playerDiscardCard = playerHand.getCard(playerDiscardChoice-1);
        playerHand.removeCard(playerDiscardCard);

        newDiscard.addToDiscardPile(playerDiscardCard);
    }

    public static void runComputerTurn(Hand computerHand, Deck newDeck, DiscardPile newDiscard,
                                       Player computerPlayer){
        computerHand.checkForGroup("computerPlayer");
        computerHand.checkForRun();

        Card playerDiscardCard = computerHand.getCard(3);
        computerHand.removeCard(playerDiscardCard);

        newDiscard.addToDiscardPile(playerDiscardCard);

    }

    public static void printBanner(){
        System.out.println(
                        " ______    __   __  __   __  __   __  __   __ \n" +
                        "|    _ |  |  | |  ||  |_|  ||  |_|  ||  | |  |\n" +
                        "|   | ||  |  | |  ||       ||       ||  |_|  |\n" +
                        "|   |_||_ |  |_|  ||       ||       ||       |\n" +
                        "|    __  ||       ||       ||       ||_     _|\n" +
                        "|   |  | ||       || ||_|| || ||_|| |  |   |  \n" +
                        "|___|  |_||_______||_|   |_||_|   |_|  |___|  \n" +
                        "\n" +
                        "written by       Marty Farley and Mason Elmore\n"
        );
        System.out.println("The first player to hit 100 points wins the game." + "\n");
    }

    private static void printBoard(Deck newDeck, DiscardPile newDiscard,
                                   Player humanPlayer, Player computerPlayer) {
        // make sure we aren't trying to get the size of an empty deck or discard pile
        int deckSize = 0;
        int discardSize = 0;

        if (!newDeck.isEmpty()) {
            deckSize = newDeck.getSize();
        }
        if (!newDiscard.isEmpty()) {
            discardSize = newDiscard.getSize();
        }

        System.out.println("\n#########################################");
        System.out.printf("# SCORE         YOU: %-3d  COMPUTER: %-3d\n",
                humanPlayer.getScore(), computerPlayer.getScore()
        );
        System.out.printf("# Stock: (%2d cards)  Discard: (%d)\n",
                deckSize, discardSize
        );
        newDiscard.displayDiscard(newDeck);
        System.out.println("#########################################\n");
        //printMelds();
        System.out.println();
        //printHand();

    }




}



