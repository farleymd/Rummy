package Marty.company;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class RummyGame {

    private static Scanner scanner = new Scanner(System.in);
    private LinkedList<Player> players;
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
        players.push(new Player());
        players.push(new Player());     // TODO make this a computer player

        // TODO let use choose either the number of deals or points to win the game
        int maxScore = 500;
        System.out.printf("The first player to hit %d points wins the game.", maxScore);

        while (!gameWon()) {
            deck = new Deck();
            discard = new DiscardPile();

            // deal the starting hands and start the discard pile
            deal(players);
            discard.addToDiscardPile(deck.drawFromDeck());

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

    private void deal(LinkedList<Player> players) {
        for (Player player : players) {
            Hand playerHand = player.getPlayerHand();
            playerHand.buildHand(deck);
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
