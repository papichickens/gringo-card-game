import java.util.ArrayList;

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
            c.toString();
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

        System.out.println(this.deck);
        System.out.println(this.deck.size());
    }

    // public void shuffleDeck () {
    //     deck.
    // }

}
