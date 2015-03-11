package Marty.company;

/**
 * Created by marty.farley on 3/1/2015.
 */
public class Player {
    private String name;
    private int Score;
    private Hand playerHand;

    public Player(String name) {
        this.name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public Hand getPlayerHand() {
        playerHand = new Hand();

        return playerHand;
    }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }

    @Override
    public String toString() {
        return name;
    }

}
