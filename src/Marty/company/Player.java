package Marty.company;

public abstract class Player {
    private String name;
    private int score = 0;
    protected Hand hand;

    public Player(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void addToScore(int score) {
        this.score += score;
    }

    public Hand getHand() {
        return hand;
    }

    public void newHand() {
        this.hand = new Hand();
    }

    @Override
    public String toString() {
        return name;
    }
}
