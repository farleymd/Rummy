//package Marty.company;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.ListIterator;
//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Player humanPlayer = new Player();
//        Player computerPlayer = new Player();
//        Deck newDeck = new Deck();
//        DiscardPile newDiscard = new DiscardPile();
//
////        Hand testHand = new Hand();
////        testHand.testBuild(newDeck);
////        testHand.displayHand();
////
////        testHand.checkForGroup();
//
//        Hand humanHand = humanPlayer.getPlayerHand();
//        humanHand.buildHand(newDeck);
//
//        Hand computerHand = computerPlayer.getPlayerHand();
//        computerHand.buildHand(newDeck);
//
//        newDiscard.displayDiscardFirstTime(newDeck);
//
//        boolean humanHandEmpty = humanHand.notEmpty();
//        boolean computerHandEmpty = computerHand.notEmpty();
//
//        while (humanHandEmpty == false && computerHandEmpty == false){
//            runHumanTurn(humanHand, newDeck, newDiscard);
//            runComputerTurn(computerHand, newDeck, newDiscard);
//        }
//    }
//
//    public static void runHumanTurn(Hand playerHand, Deck newDeck, DiscardPile newDiscard){
//        Scanner scanner = new Scanner(System.in);
//        playerHand.displayHand();
//        newDiscard.displayDiscard(newDeck);
//        //Player draw
//        System.out.println("Would you like to draw from the deck or the discard pile?");
//        String userDraw = scanner.next();
//
//        if (userDraw.equalsIgnoreCase("Deck")){
//            Card newCard = newDeck.drawFromDeck();
//            playerHand.addCard(newCard);
//            playerHand.displayHand();
//
//        } else if (userDraw.equalsIgnoreCase("Discard")){
//            Card newCard = newDiscard.drawFromDiscard();
//            playerHand.addCard(newCard);
//            playerHand.displayHand();
//        }
//
//        playerHand.checkForGroup();
//        playerHand.checkForRun();
//
//        System.out.println("Which card would you like to discard? Type the number beside the card.");
//        int playerDiscardChoice = scanner.nextInt();
//        Card playerDiscardCard = playerHand.getCard(playerDiscardChoice-1);
//        playerHand.removeCard(playerDiscardCard);
//
//        newDiscard.addToDiscardPile(playerDiscardCard);
//    }
//
//    public static void runComputerTurn(Hand computerHand, Deck newDeck, DiscardPile newDiscard){
//        computerHand.checkForGroup();
//        computerHand.checkForRun();
//
//        Card playerDiscardCard = computerHand.getCard(3);
//        computerHand.removeCard(playerDiscardCard);
//
//        newDiscard.addToDiscardPile(playerDiscardCard);
//
//    }
//
//}
//
//
//
