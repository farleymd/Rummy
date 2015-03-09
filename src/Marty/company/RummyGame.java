package Marty.company;

import java.util.LinkedList;

public class RummyGame {
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

    public static void main(String[] args){
        printBanner();

        // create the deck and discard pile
        Deck deck = new Deck();
        DiscardPile discard = new DiscardPile();

        // add the players to the game
        LinkedList<Player> players = new LinkedList<Player>();
        players.push(new Player());
        players.push(new Player());     // TODO make this a computer player

        // loop until a player wins. either number of deals or total points

            // deal 10 cards to each player
            // start discard by adding 1 card to it

            // loop through players until one "goes out"

                // player needs to draw from the deck or discard
                    // if player draws from deck and it is empty, reverse discard pile and move to deck

                // player options
                    // meld (only one)
                    // lay off (add to a meld)
                    // discard
                        // can not be a card picked up from discard this turn

                // if player goes out, calculate score
                    // if score >= total to win -> player wins
                    // else deal again
    }
}
