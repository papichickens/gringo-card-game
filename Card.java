import java.util.HashMap;

public class Card {
    private String rank;
    private String suit;
    private Integer value;

    public enum Powers {
        SEENSWAP,
        SEE,
        SWAP,
        O
    }

    private static HashMap<String, Integer> valueOfCard = new HashMap<>();

    static {
        valueOfCard.put("JOKER", 0);

        valueOfCard.put("A", 1);

        valueOfCard.put("K-Hearts", -2);
        valueOfCard.put("K-Diamonds", -2);
        valueOfCard.put("K-Spades", 10);
        valueOfCard.put("K-Clubs", 10);

        valueOfCard.put("Q-Hearts", 10);
        valueOfCard.put("Q-Diamonds", 10);
        valueOfCard.put("Q-Spades", 10);
        valueOfCard.put("Q-Clubs", 10);

        valueOfCard.put("J-Hearts", 10);
        valueOfCard.put("J-Diamonds", 10);
        valueOfCard.put("J-Spades", 10);
        valueOfCard.put("J-Clubs", 10);

        valueOfCard.put("10", 10);
        valueOfCard.put("9", 9);
        valueOfCard.put("8", 8);
        valueOfCard.put("7", 7);
        valueOfCard.put("6", 6);
        valueOfCard.put("5", 5);
        valueOfCard.put("4", 4);
        valueOfCard.put("3", 3);
        valueOfCard.put("2", 2);
    }

    public String getRank (){return rank;}
    public String getSuit (){return suit;}

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;



        this.value = valueOfCard.get(rank + "-" + suit);
        if (this.value == null)
            this.value = valueOfCard.get(rank);

        System.out.println("value: " + this.value);
    }
}
