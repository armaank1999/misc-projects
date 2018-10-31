import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<>(); // An arrayList of Cards, representing the deck.
    private int cardsUsed = 0; // How many cards have been dealt from the deck.

    public Deck() {
        deck.add(Card.pawn());
        deck.add(Card.pawn());
        deck.add(Card.pawn());
        deck.add(Card.pawn());
        deck.add(Card.pawn());
        deck.add(Card.pawn());
        deck.add(Card.pawn());
        deck.add(Card.pawn());
        deck.add(Card.bishop());
        deck.add(Card.rook());
        deck.add(Card.knight());
        deck.add(Card.bishop());
        deck.add(Card.rook());
        deck.add(Card.knight());
        deck.add(Card.queen());
        shuffle(deck);
    }

    // Remove random cards at each step, then add all back to randomize order.
    public static void shuffle(ArrayList<Card> cards) {
        ArrayList<Card> tempDeck = new ArrayList<>();
        while (cards.size() > 1) tempDeck.add(cards.remove(Hearthstone.globalRand.nextInt(cards.size())));
        cards.addAll(tempDeck);
    }

    public int cardsLeft() {
        return deck.size() - cardsUsed;
    }

    public Card dealCard() {
        return deck.get(cardsUsed++);
    }
}