package Marty.company;

public abstract class Player {
    private String name;
    private int score;
    protected Hand hand;

    public Player(String name) {
        this.name = name;
    }

    public abstract void drawCard();

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
