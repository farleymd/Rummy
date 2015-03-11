package Marty.company;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class RummyGame {

    private static Scanner scanner = new Scanner(System.in);
    private LinkedList<Player> players;
    private Player currentPlayer;
    private Deck deck;
    private DiscardPile discard;


    public static void main(String[] args) {
        printBanner();
        new RummyGame().run();
        scanner.close();
    }

    private static void printBanner() {
        System.out.println(
                " ______    __   __  __   __  __   __  __   __ \n" +
                "|    _ |  |  | |  ||  |_|  ||  |_|  ||  | |  |\n" +
                "|   | ||  |  | |  ||       ||       ||  |_|  |\n" +
                "|   |_||_ |  |_|  ||       ||       ||       |\n" +
                "|    __  ||       ||       ||       ||_     _|\n" +
                "|   |  | ||       || ||_|| || ||_|| |  |   |  \n" +
                "|___|  |_||_______||_|   |_||_|   |_|  |___|  \n" +
                "\n" +
                "written by       Marty Farley and Mason Elmore\n" +
                "\n"
        );
    }

    private void run() {
        // TODO let user enter points or deals to win

        players = new LinkedList<Player>();
        players.push(new HumanPlayer("Player"));
        players.push(new ComputerPlayer());
        currentPlayer = players.getFirst();

        int handSize = 10;  // for possible multiplayer mode

        System.out.println("The first player to hit 100 points wins the game.");
        System.out.println("TIP: When selecting cards, enter the position they are at in your hand.");
        System.out.println("For example: ♦J ♥7 ♥8 ♣3 ♣7 ♦A ♣J ♥5 ♣8 ♦3");
        System.out.println("If you want to select ♣3, you would enter 4.");
        System.out.println("If you want to select ♥8 ♦A ♥5 ♦J, enter them like this: 3 6 8 1");
        System.out.println("Sorry!");

        // deal new hands until the game is won
        while (!gameWon()) {
            // initialize the deck and discard pile
            deck = new Deck();
            deck.shuffle();
            discard = new DiscardPile();

            // deal starting hand to each player
            for (Player player : players) {
                player.newHand();
                for (int i = 0; i < handSize; i++) {
                    player.hand.addCard(deck.draw());
                }
            }

            // start the discard pile
            discard.addCard(deck.draw());

            // players take turns until one runs out of cards
            while (true) {
                if (currentPlayer instanceof HumanPlayer) {
                    humanTurn();
                } else {
                    computerTurn();
                }

                if (currentPlayer.hand.isEmpty()) {
                    currentPlayer.addToScore(calculatePoints());
                }

                nextPlayer();
            }
        }
    }

    private void humanTurn() {
        printBoard();

        System.out.print(
                "Draw a card from\n" +
                "1. Deck\n" +
                "2. Discard\n"
        );
        int userChoice = getIntRangeInput(1, 2);
        switch (userChoice) {
            case 1:
                drawFromDeck();
                break;
            case 2:
                drawFromDiscard();
                break;
            default:
                // this shouldn't happen
                System.out.println("Something went wrong.");
        }

        System.out.println(currentPlayer.hand);

        System.out.println(
                "1. Meld\n" +
                "2. Lay Off\n" +
                "3. Discard\n"
        );
        userChoice = getIntRangeInput(1, 3);
        switch (userChoice) {
            case 1:
                meldMenu();
                break;
            case 2:
                layOffMenu();
                break;
            case 3:
                discardMenu();
        }
    }

    private void computerTurn() {

    }
    private void drawFromDiscard() {
        currentPlayer.hand.addCard(discard.draw());
    }

    private void drawFromDeck() {
        // refill the deck from the discard if it is empty
        if (deck.isEmpty()){
            discard.moveToDeck(deck);
        }
        currentPlayer.hand.addCard(deck.draw());
    }

    private void layOffMenu() {

    }

    private void meldMenu() {

    }

    private void discardMenu() {
        int handSize = currentPlayer.hand.size();
        System.out.printf("Enter the card you want to discard, 1 - %d\n", handSize);
        int cardPosition = getIntRangeInput(1, handSize);
        cardPosition -= 1;
        discard.addCard(currentPlayer.hand.getCard(cardPosition));
        currentPlayer.hand.removeCard(cardPosition);
    }

    private int getIntRangeInput(int firstInt, int lastInt) {
        while (true) {
            String input = scanner.nextLine();
            try {
                int inputInt = Integer.parseInt(input);
                if (inputInt < firstInt || inputInt > lastInt) {
                    System.out.println("Please enter a valid selection.");
                    continue;
                }
                return inputInt;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a number.");
                continue;
            }
        }
    }


    private void printBoard() {
        System.out.println("\n#########################################");

        System.out.printf("# SCORE         YOU: %-3d  COMPUTER: %-3d\n",
                players.get(0).getScore(), players.get(1).getScore()
        );
        System.out.printf("# Stock: (%2d cards)  Discard: %s (%d)\n",
                deck.size(), discard.peek().toString(), discard.size());
        System.out.println("#########################################\n");

        System.out.println("Hand:\n" + currentPlayer.getHand());
    }

    // set the current player to the next player in the list
    private void nextPlayer() {
        // get the position of the current player
        int playerPos = players.indexOf(currentPlayer);

        // go back to the first player if we are on the last
        if (playerPos == players.size() - 1) {
            currentPlayer = players.getFirst();
        } else {
            currentPlayer = players.get(playerPos + 1);
        }
    }

    // add up the point values of every non-empty hand
    private int calculatePoints() {
        int points = 0;
        for (Player player : players) {
            if (!player.hand.isEmpty()){
                points += player.hand.getPoints();
            }
        }
        return points;
    }

    private boolean gameWon() {
        for (Player player : players) {
            // TODO hard coded max score should be moved outside so user can set it
            if (player.getScore() >= 100) {
                return true;
            }
        }
        return false;
    }
}
