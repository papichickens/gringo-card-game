import java.util.ArrayList;
import java.util.Random;

public class Deck {
    //Globals
    private static final String[] rankOrder = {
        "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "J", "Q", "K", "A"
    };

    private static final String[] suitOrder = {
        "Hearts", "Diamonds", "Spades", "Clubs"
    };
    //
    public ArrayList<Card> deck;

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < deck.size(); i++) {
            Card c = deck.get(i);
            // c.toString();
            System.out.print(c + ", ");
        }
        return s;
    }

    public Deck(){
        this.deck = new ArrayList<Card>();
    }

    public void fillDeck () {
        for (int rank = 0; rank < rankOrder.length; rank++){
            for (int suit = 0; suit < suitOrder.length; suit++) {
                this.deck.add(new Card(rankOrder[rank], suitOrder[suit]));
            }
        }

        // add jokers
        this.deck.add(new Card("JOKER", "JOKER"));
        this.deck.add(new Card("JOKER", "JOKER"));

        // System.out.println(this.deck);
        // System.out.println(this.deck.size());
    }

    

    public void shuffleDeck () {
        Random rnd = new Random();
        for (int i = deck.size() - 1; i > 0; i-- ) {
            // Swap
            int swap_i = rnd.nextInt(i + 1);
            Card c1 = this.deck.get(i);
            Card c2 = this.deck.get(swap_i);
            deck.set(swap_i, c1);
            deck.set(i, c2);
        }

        // System.out.println(this.deck);
        // System.out.println(this.deck.size());
    }
}
