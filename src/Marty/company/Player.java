package Marty.company;

/**
 * Created by marty.farley on 3/1/2015.
 */
public class Player {
    private int Score;
    private Hand playerHand;

    public int getScore() {
        return Score;
    }

    public void setScore(int points) {

        Score = Score + points;
    }

    public Hand getPlayerHand() {
        playerHand = new Hand();

        return playerHand;
    }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }

//    private boolean gameWon(Player player) {
//        int score = this.getScore();
//        boolean gameWon = false;
//
//        if (score != 500){
//            gameWon = false;
//        } else {
//            gameWon = true;
//        }
//
//        return gameWon;
//    }

}
