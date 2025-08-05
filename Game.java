public class Game {
    public Deck deck;
    public Deck discardDeck;
    public Player[] players;
    public Integer currentPlayerIndex;
    public Phase gamePhase;
    public Boolean gringoCalled;

    public enum Phase {
        SETUP, PLAYING, ENDING, FINISHED
    }

    public Game () {
        Deck deck = new Deck();

        deck.fillDeck();
    }



    public static void main (String[] args) {
        Game g = new Game();
    }
}
