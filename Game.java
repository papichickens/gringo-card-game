

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
        this.deck = new Deck();
        this.deck.fillDeck();
        this.deck.shuffleDeck();
        System.out.println(deck.toString());
        this.discardDeck = new Deck();

        this.currentPlayerIndex = 0;
        this.gamePhase = Phase.SETUP;
        this.gringoCalled = false;
    }

    private void setupPlayerDecks(Player[] players) {
        //
    }

    public static void main (String[] args) {
        Game g = new Game();
    }
}
