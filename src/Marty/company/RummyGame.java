package Marty.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class RummyGame {

    private static Scanner scanner = new Scanner(System.in);
    private LinkedList<Player> players;
    private Player currentPlayer;
    private Deck deck;
    private DiscardPile discard;
    private ArrayList<Meld> melds;  // TODO associate melds with players


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
        players = new LinkedList<Player>();
        players.push(new HumanPlayer("Player"));
        players.push(new ComputerPlayer());
        currentPlayer = players.getFirst();

        int handSize = 10;  // for possible multiplayer mode

        System.out.println("The first player to hit 100 points wins the game.");
        System.out.println();
        System.out.println("TIP: When selecting cards, enter the position they are at in your hand.");
        System.out.println("For example: ♦J ♥7 ♥8 ♣3 ♣7 ♦A ♣J ♥5 ♣8 ♦3");
        System.out.println("If you want to select ♣3, you would enter 4.");
        System.out.println("If you want to select ♥8 ♦A ♥5 ♦J, enter them like this: 3 6 8 1");
        System.out.println("Sorry!");

        // deal new hands until the game is won
        while (!gameWon()) {
            // initialize the deck and discard pile
            deck = new Deck();
            discard = new DiscardPile();
            melds = new ArrayList<Meld>();

            // deal starting hand to each player
            for (Player player : players) {
                player.dealHand(deck, handSize);
            }

            // start the discard pile
            while (!deck.isEmpty()) {
                discard.addCard(deck.deal());
            }

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

        // track the card from the discard pile so they player cant put it back
        Card cardFromDiscard = null;

        switch (userChoice) {
            case 1:
                // refill the deck from the discard if it is empty
                if (deck.isEmpty()){
                    discard.flipOver();
                    deck = new Deck(discard);
                    discard = new DiscardPile();
                }
                currentPlayer.drawCard(deck);
                break;
            case 2:
                cardFromDiscard = discard.topCard();
                currentPlayer.drawCard(discard);
                break;
        }

        // player can only meld once and must discard before their turn is over
        boolean hasMelded = false;
        boolean hasDiscarded = false;

        while (!hasDiscarded){
            printBoard();

            System.out.println(
                    "1. Meld\n" +
                    "2. Lay Off\n" +
                    "3. Discard\n"
            );
            userChoice = getIntRangeInput(1, 3);

            switch (userChoice) {
                case 1:
                    if (hasMelded) {
                        System.out.println("You can only meld once per turn.");
                        break;
                    }
                    hasMelded = meldMenu();
                    break;
                case 2:
                    if (melds.isEmpty()) {
                        System.out.println("No melds to lay off cards to.");
                        break;
                    }
                    layOffMenu();
                    break;
                case 3:
                    hasDiscarded = discardMenu(cardFromDiscard);
            }
        }
    }

    private void computerTurn() {

    }

    private boolean meldMenu() {
        int handSize = currentPlayer.hand.size();
        System.out.printf("Enter cards to meld (1 - %d)\n", handSize);
        // get the card positions from the user
        ArrayList<Integer> cardPositions = getManyIntsRange(1, handSize);

        // build a temporary meld and test if it is valid
        ArrayList<Card> testForMeld = new ArrayList<Card>();
        for (Integer cardPos : cardPositions) {
            testForMeld.add(currentPlayer.hand.getCard(cardPos));
        }

        if (Meld.isValidMeld(testForMeld)) {
            // valid meld, add it to the list
            melds.add(new Meld(testForMeld));
            // remove cards from hand
            currentPlayer.hand.removeCards(testForMeld);
            return true;
        } else {
            System.out.println("That is not a valid meld.");
            return false;
        }
    }

    private void layOffMenu() {
        int meldCount = melds.size();
        System.out.printf("Enter the meld to lay off onto (1 - %d)\n", meldCount);
        int meldNumber = getIntRangeInput(1, meldCount);

        int handSize = currentPlayer.hand.size();
        System.out.printf("Enter cards to lay off (1 - %d)\n", handSize);
        // get the card positions from the user
        ArrayList<Integer> cardPositions = getManyIntsRange(1, handSize);

        // add the cards to a copy of the current meld and see if it's valid
        ArrayList<Card> testForMeld = melds.get(meldNumber).getCards();
        for (Integer cardPos : cardPositions) {
            testForMeld.add(currentPlayer.hand.getCard(cardPos));
        }

        if (Meld.isValidMeld(testForMeld)) {
            // valid meld, add it to the list
            melds.add(new Meld(testForMeld));
            // remove old meld
            melds.remove(meldNumber);
        } else {
            System.out.println("That is not a valid meld.");
        }
    }

    private boolean discardMenu(Card fromDiscard) {

        if (fromDiscard != null) {
            System.out.println("Unable to discard this turn: " + fromDiscard);
        }

        int handSize = currentPlayer.hand.size();
        System.out.printf("Enter the card you want to discard (1 - %d)\n", handSize);
        int cardPosition = getIntRangeInput(1, handSize);
        cardPosition -= 1;  // -1 to match hand index

        Card cardToDiscard = currentPlayer.hand.getCard(cardPosition);
        if (cardToDiscard == fromDiscard) {
            System.out.println("That card was from the discard pile, you can't discard it this turn.");
            return false;
        }

        discard.addCard(cardToDiscard);
        // TODO add removeCard method that takes card object as an argument
        currentPlayer.hand.removeCard(cardPosition);
        return true;
    }

    // get an int from the user within the given range
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

    // get a number of ints from the user within the given range
    private ArrayList<Integer> getManyIntsRange(int firstInt, int lastInt) {
        ArrayList<Integer> cardPositions = new ArrayList<Integer>();
        while (true) {
            // get space separated (we hope the user read the tip!) ints from user
            String input = scanner.nextLine();
            try {
                String[] inputParts = input.split(" ");

                for (String part : inputParts) {
                    // try to parse it
                    int partInt = Integer.parseInt(part);
                    // see if it's within the given range
                    if (partInt < firstInt || partInt > lastInt) {
                        System.out.println(partInt + " is not a valid card position.");
                        System.out.printf("Only number from %d to %d are allowed.\n", firstInt, lastInt);
                        continue;
                    }

                    // add valid int to list
                    cardPositions.add(partInt - 1);
                }

                return cardPositions;
            } catch (NumberFormatException nme) {
                // hopefully they didn't have a bad typo on a long list of numbers
                System.out.println("Please enter numbers.");
            }
        }
    }

    private void printBoard() {
        // make sure we aren't trying to get the size of an empty deck or discard pile
        int deckSize = 0;
        int discardSize = 0;
        String discardStr = "X";

        if (!deck.isEmpty()) {
            deckSize = deck.size();
        }
        if (!discard.isEmpty()) {
            discardStr = discard.topCard().toString();
            discardSize = discard.size();
        }

        System.out.println("\n#########################################");
        System.out.printf("# SCORE         YOU: %-3d  COMPUTER: %-3d\n",
                players.get(0).getScore(), players.get(1).getScore()
        );
        System.out.printf("# Stock: (%2d cards)  Discard: %s (%d)\n",
                deckSize, discardStr, discardSize
        );
        System.out.println("#########################################\n");
        printMelds();
        System.out.println();
        printHand();

    }

    private void printMelds() {
        System.out.println("Melds: ");
        if (melds.isEmpty()) {
            System.out.println("NO MELDS");
        } else {
            for (int i = 0; i < melds.size(); i++) {
                System.out.printf("%d: %s\n", (i + 1), melds);
            }
        }
    }

    private void printHand() {
        System.out.println("Hand: " + currentPlayer.hand);
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
        // TODO let user choose the rules for winning the game
        for (Player player : players) {
            if (player.getScore() >= 100) {
                return true;
            }
        }
        return false;
    }
}
