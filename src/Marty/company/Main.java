package Marty.company;

import java.util.*;

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

    }

        public static void newGame(Player humanPlayer, Player computerPlayer){
            Scanner scanner = new Scanner(System.in);
            Deck newDeck = new Deck();
            DiscardPile newDiscard = new DiscardPile();
            Meld meldDesktop = new Meld();

            Hand humanHand = humanPlayer.getPlayerHand();
            //humanHand.buildHand(newDeck);
            humanHand.testBuild(newDeck);

            Hand computerHand = computerPlayer.getPlayerHand();
            computerHand.buildHand(newDeck);
            //computerHand.testBuild(newDeck);

            newDiscard.displayDiscardFirstTime(newDeck);

            boolean humanHandEmpty = humanHand.notEmpty();
            boolean computerHandEmpty = computerHand.notEmpty();

            while (humanHandEmpty == false && computerHandEmpty == false){
                runHumanTurn(humanHand, newDeck, newDiscard, humanPlayer, computerPlayer, meldDesktop);
                runComputerTurn(computerHand, newDeck, newDiscard, computerPlayer, meldDesktop);

                humanHandEmpty = humanHand.notEmpty();
                computerHandEmpty = computerHand.notEmpty();
        }

            if (humanHandEmpty == true){
                System.out.println("You won this hand! Let's total up the points and start a new game.");
                computerHand.handPoints(computerHand, humanPlayer);
                boolean humanWon = humanPlayer.gameWon(humanPlayer);
                boolean computerWon = computerPlayer.gameWon(computerPlayer);
                while (humanWon == false || computerWon == false){
                    newGame(humanPlayer, computerPlayer);
                }
                if (humanWon == true){
                    System.out.println("Hurray! You've won the game!");
                }
            }

            if (computerHandEmpty == true){
                System.out.println("The computer won this hand. Let's total up the points and start a new game.");
                humanHand.handPoints(humanHand, computerPlayer);
                boolean humanWon = humanPlayer.gameWon(humanPlayer);
                boolean computerWon = computerPlayer.gameWon(computerPlayer);
                while (humanWon == false || computerWon == false){
                    newGame(humanPlayer, computerPlayer);
                }
                if (computerWon == true){
                    System.out.println("Sorry, looks like the computer beat you this time around!");
                }
            }
    }

    public static void runHumanTurn(Hand playerHand, Deck newDeck, DiscardPile newDiscard, Player humanPlayer,
                                    Player computerPlayer, Meld meldDesktop){
        Scanner scanner = new Scanner(System.in);
        String ANSI_black = "\u001B[30m";
        printBoard(newDeck, newDiscard, humanPlayer, computerPlayer, meldDesktop);
        playerHand.displayHand();
        //Player draw
        System.out.println(ANSI_black + "Would you like to draw from the deck or the discard pile?");
        String userDraw = scanner.next();
        boolean isGroup = false;
        boolean isRun = false;

        if (userDraw.equalsIgnoreCase("Deck")){
            Card newCard = newDeck.drawFromDeck();
            playerHand.addCard(newCard);
            playerHand.displayHand();

        } else if (userDraw.equalsIgnoreCase("Discard")){
            Card newCard = newDiscard.drawFromDiscard();
            playerHand.addCard(newCard);
            playerHand.displayHand();
        }

        //can meld only once per turn

        isGroup = playerHand.checkForGroup("humanPlayer", meldDesktop, playerHand, humanPlayer);

        if (isGroup == false) {
            isRun = playerHand.checkForRun("humanPlayer", meldDesktop, playerHand, humanPlayer);

            if (isRun == false) {
                boolean noMelds = meldDesktop.meldDesktopIsEmpty(); //are there any melds on the board?
                if (noMelds == false) {
                    System.out.println(ANSI_black + "Would you like to meld an individual card? Y or N");

                String userAnswer = scanner.next();

                if (userAnswer.equalsIgnoreCase("Y")) {
                    System.out.println(ANSI_black + "What card would you like to meld?");
                    int meldCardChoice = scanner.nextInt();
                    meldCardChoice = meldCardChoice - 1;

                    System.out.println(ANSI_black + "Which meld would you like to add the card to?");
                    int meldChoice = scanner.nextInt();
                    meldChoice = meldChoice - 1;


                    Card meldCard = playerHand.getCard(meldCardChoice);

                    meldDesktop.addIndividualGroupCard(meldCard, meldChoice, playerHand, humanPlayer);
                    meldDesktop.addIndividualRunCard(meldCard, meldChoice, playerHand, humanPlayer);

                    meldDesktop.printMelds();
                }

                }
            }
        }
        System.out.println(ANSI_black + "Which card would you like to discard? Type the number beside the card.");
        int playerDiscardChoice = scanner.nextInt();
        Card playerDiscardCard = playerHand.getCard(playerDiscardChoice-1);
        playerHand.removeCard(playerDiscardCard);

        newDiscard.addToDiscardPile(playerDiscardCard);
    }

    public static void runComputerTurn(Hand computerHand, Deck newDeck, DiscardPile newDiscard,
                                       Player computerPlayer, Meld meldDesktop){
        computerHand.computerHandSort();

        boolean isGroup = false;
        boolean isRun = false;

        //computer decides deck or discard draw
        int computerChoice = computerHand.computerDraw(newDiscard, computerHand);

        if (computerChoice == 0){  ///draw from deck choice
            Card newCard = newDeck.drawFromDeck();
            computerHand.addCard(newCard);
        } else if (computerChoice == 1){ //draw from discard
            Card newCard = newDiscard.drawFromDiscard();
            computerHand.addCard(newCard);
        }

        //can only meld once. check group first. if no group, check run. if no run, ask to meld a card

        isGroup = computerHand.checkForGroup("computerPlayer", meldDesktop, computerHand, computerPlayer);

        if (isGroup == false){
            isRun = computerHand.checkForRun("computerPlayer",meldDesktop, computerHand, computerPlayer);

            if (isRun == false){
                meldDesktop.addComputerCard();
            }
        }

        //TODO make computer less stupid about discard
        Card computerDiscardCard = computerHand.computerDiscard(computerHand);
        ;
        computerHand.removeCard(computerDiscardCard);

        newDiscard.addToDiscardPile(computerDiscardCard);

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
                                   Player humanPlayer, Player computerPlayer,
                                   Meld meldDesktop) {
        // make sure we aren't trying to get the size of an empty deck or discard pile
        String ANSI_black = "\u001B[30m";
        int deckSize = 0;
        int discardSize = 0;

        if (!newDeck.isEmpty()) {
            deckSize = newDeck.getSize();
        }
        if (!newDiscard.isEmpty()) {
            discardSize = newDiscard.getSize();
        }

        System.out.println(ANSI_black + "\n#########################################");
        System.out.printf("# SCORE         YOU: %-3d  COMPUTER: %-3d\n",
                humanPlayer.getScore(), computerPlayer.getScore()
        );
        System.out.printf("# Stock: (%2d cards)  Discard: (%d)\n",
                deckSize, discardSize
        );
        newDiscard.displayDiscard(newDeck);
        meldDesktop.printMelds();
        System.out.println("#########################################\n");
        System.out.println();
        //printHand();

    }




}



