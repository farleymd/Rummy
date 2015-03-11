package Marty.company;

import java.util.LinkedList;
import java.util.Random;

public class Rummy {
    private LinkedList<Player> players;
    private Player currentPlayer;
    private Deck deck;
    private DiscardPile discardPile;

    public Rummy() {
        players = new LinkedList<Player>();
        deck = new Deck();
        deck.shuffle();
        discardPile = new DiscardPile();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean gameWon() {
        // TODO set score or deals? or let user input score or deals to stop the game
        return false;
    }

    // deal the starting hand of 10 cards to each player
    public void dealFirstHand() {
        for (Player player : players) {
            player.newHand();
            for (int i = 0; i < 10; i++) {
                player.hand.addCard(deck.draw());
            }
        }

        // TODO it doesn't feel like this belongs here
        discardPile.addCard(deck.draw());
    }

    public void drawFromDeck() {
        if (deck.isEmpty()) {
            discardPile.moveToDeck(deck);
        }

        currentPlayer.hand.addCard(deck.draw());
    }

    public void drawFromDiscard() {
        currentPlayer.hand.addCard(discardPile.draw());
    }

    public void chooseRandomDealer() {
        Random random = new Random();
        currentPlayer = players.get(random.nextInt(players.size()));
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    // set the current player to the next player in the list
    public void nextPlayer() {
        // get the position of the current player
        int playerPos = players.indexOf(currentPlayer);

        // go back to the first player if we are on the last
        if (playerPos == players.size() - 1) {
            currentPlayer = players.getFirst();
        } else {
            currentPlayer = players.get(playerPos + 1);
        }
}
