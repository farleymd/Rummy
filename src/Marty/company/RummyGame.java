package Marty.company;

import java.util.Collections;
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
        new RummyGame().playGame();
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

    private void playGame() {
        players = new LinkedList<Player>();
        players.push(new Player("Player"));
        players.push(new Player("Computer"));     // TODO make this a computer player
        currentPlayer = players.getFirst();

        // TODO let use choose either the number of deals or points to win the game
        int maxScore = 500;
        System.out.printf("The first player to hit %d points wins the game.\n", maxScore);

        while (!gameWon()) {
            deck = new Deck();
            deck.shuffle();
            discard = new DiscardPile();

            deal();
            playerTurn();

            // player needs to draw from the deck or discard
                // if player draws from deck and it is empty, reverse discard pile and move to deck

            // player options
                // meld (only one)
                // lay off (add to a meld)
                // discard
                    // can not be a card picked up from discard this turn

            // if player goes out, calculate scores
                // if score >= total to win -> player wins
                // else deal again
        }
    }


    private void playerTurn() {
        printBoard();
    }

    private void printBoard() {
        System.out.println("#########################################");
        System.out.print("Hand: " + currentPlayer.getPlayerHand());
        System.out.println("");
        scanner.next();
    }

    // deal the starting hand to each player
    private void deal() {
        for (Player player : players) {
            Hand playerHand = player.getPlayerHand();
            for (int i = 0; i < 10; i++) {
                playerHand.addCard(deck.draw());
            }
        }
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

    private boolean gameWon() {
        // TODO check players scores to see if one is more than max
        // if more than one is above the winning score, pick one with highest score
        return false;
    }

    private int getPositiveIntFromUser() {
        int userChoice;

        while (true) {
            try {
                System.out.print("=> ");
                userChoice = scanner.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid menu item");
                scanner.next();
                continue;
            }

            if (userChoice <= 0) {
                System.out.println("Please enter a valid menu item");
                continue;
            }

            return userChoice;
        }
    }
}
