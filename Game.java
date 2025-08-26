

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
        // List<Player> players = new List<Player>();

        deck.fillDeck();
        deck.shuffleDeck();
        System.out.println(deck.toString());
    }

    private void setupPlayerDecks(Player[] players) {

    }

    public static void main (String[] args) {
        Game g = new Game();
    }
}
