package Marty.company;

import java.util.Scanner;

public class Game {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printBanner();
        new Game().run();
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
        Rummy rummy = new Rummy();

//        System.out.print("Enter your name: ");
//        String playerName = scanner.nextLine();
        String playerName = "mason";

        rummy.addPlayer(new HumanPlayer(playerName));
        rummy.addPlayer(new ComputerPlayer());

        while (!rummy.gameWon()) {
            rummy.dealFirstHand();

            while (true) {
                if (rummy.getCurrentPlayer() instanceof HumanPlayer) {
                    runHumanTurn();
                } else {
                    runComputerTurn();
                }

                if (rummy.playerHandEmpty())

                rummy.nextPlayer();
            }
        }
    }

    private void runHumanTurn() {

    }

    private void runComputerTurn() {

    }
}
