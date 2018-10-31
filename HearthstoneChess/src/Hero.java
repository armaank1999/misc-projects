import java.util.ArrayList;

public class Hero {
    public ArrayList<Card> hand = new ArrayList<>();
    public ArrayList<Card> board = new ArrayList<>();
    public String name;
    private Deck myDeck = new Deck();
    private int health = 15;
    private int fatigue = 1;
    private int currentMana = 0;
    private int totalMana = 0;
    private boolean usedHP;

    public Hero(String nick) {
        name = nick;
    }

    private static void printHand(ArrayList<Card> hand) {
        int mySize = hand.size();
        int indents = 8 - mySize;
        for (int i = 0; i < indents; i++) System.out.print("    ");
        for (int i = 0; i < mySize; i++) System.out.print("_________ ");
        System.out.println();
        for (int i = 0; i < indents; i++) System.out.print("    ");
        for (Card curr : hand) System.out.printf("|%-2d   %2d| ", curr.mana, curr.attack);
        System.out.println();
        for (int i = 0; i < indents; i++) System.out.print("    ");
        for (Card curr : hand) System.out.printf("|%6s | ", curr.name);
        System.out.println();
        for (int i = 0; i < indents; i++) System.out.print("    ");
        for (Card curr : hand) System.out.print("|      " + curr.health + "| ");
        System.out.println();
        for (int i = 0; i < indents; i++) System.out.print("    ");
        for (int i = 0; i < mySize; i++) System.out.print("--------- ");
        System.out.println();
    }

    public void printHand() {
        int mySize = hand.size();
        int indents = 10 - mySize;
        for (int i = 0; i < indents; i++) System.out.print("    ");
        for (int i = 0; i < mySize; i++) System.out.print("_________ ");
        System.out.println();
        for (int i = 0; i < indents; i++) System.out.print("    ");
        for (Card curr : hand) System.out.printf("|%-2d   %2d| ", curr.mana, curr.attack);
        System.out.println();
        for (int i = 0; i < indents; i++) System.out.print("    ");
        for (Card curr : hand) System.out.printf("|%6s | ", curr.name);
        System.out.println();
        for (int i = 0; i < indents; i++) System.out.print("    ");
        for (Card curr : hand) System.out.print("|      " + curr.health + "| ");
        System.out.println();
        for (int i = 0; i < indents; i++) System.out.print("    ");
        for (int i = 0; i < mySize; i++) System.out.print("--------- ");
        System.out.println();
    }

    // When hero takes damage, reduce their health. Can't go lower than 0
    public void reduceHealth(int x) {
        health -= x;
        if (health < 0) health = 0;
    }

    public void heroPower() {
        if (this.currentMana < 2) {
            System.out.println("Error, insufficient mana");
        } else if (usedHP) {
            System.out.println("Error, you can only use the hero power once per turn");
        } else if (hand.size() == 10) {
            System.out.println("Your hand is too full! Play a card, then hero power.");
        } else {
            currentMana -= 2;
            usedHP = true;
            // A pool of 3 of the 5 card types is created and shuffled, with two types left out randomly
            int n = Hearthstone.globalRand.nextInt(4);
            int m = Hearthstone.globalRand.nextInt(5);
            ArrayList<Card> choices = new ArrayList<>();
            choices.add(Card.pawn());
            choices.add(Card.bishop());
            choices.add(Card.rook());
            choices.add(Card.queen());
            choices.add(Card.knight());
            choices.remove(m);
            choices.remove(n);
            Deck.shuffle(choices);
            printHand(choices);
            System.out.println("Choose a card from the options presented (0 indexed).");
            int command = Hearthstone.console.nextInt();
            while (command > 2 || command < 0) {
                System.out.println("Error, number inputted is not in the range of 0-2.");
                command = Hearthstone.console.nextInt();
            }
            hand.add(choices.get(command));
        }
    }

    // At the start of each turn, the person's total mana increases, and current mana matches total mana, <= 10.
    public void refreshMana() {
        totalMana++;
        if (totalMana == 11) totalMana = 10;
        usedHP = false;
        currentMana = totalMana;
    }

    // Find out if the hero is dead
    public boolean isDead() {
        return health == 0;
    }

    // Draws a card from the deck, adds it to hand, and says how many cards are remaining
    public void drawCard() {
        if (myDeck.cardsLeft() == 0) {
            System.out.println("You took " + fatigue + " fatigue damage!");
            reduceHealth(fatigue++);
            return;
        }
        Card topDeck = myDeck.dealCard();
        if (hand.size() == 10) {
            System.out.println("Your hand is too full!");
            System.out.println("You burned a " + topDeck.name + "!");
            return;
        }
        System.out.println("Your deck now has " + myDeck.cardsLeft() + " cards remaining.");
        hand.add(topDeck);
    }

    // Outputs all useful information about the hero: cards left, mana, health
    public String toString() {
        StringBuilder message = new StringBuilder("Name: ");
        message.append(name).append(", Health: ").append(health).append(", ");
        message.append(currentMana).append("/").append(totalMana).append(" Mana, ").append(myDeck.cardsLeft());
        message.append(" cards left, ").append(hand.size()).append(" cards in hand.");
        if (myDeck.cardsLeft() == 0) message.append(" Next fatigue damage: ").append(fatigue);
        return message.toString();
    }

    // Plays a card from the hand to the board if possible
    public void playCard(int number, int destination) {
        Card chosen = hand.get(number);
        if (board.size() == 7) {
            System.out.println("Your board is full!");
            return;
        }
        if (currentMana < chosen.mana) {
            System.out.println("Insufficient mana.");
            return;
        }
        hand.remove(number);
        if (chosen.health != 0) board.add(destination, chosen);
        currentMana -= chosen.mana;
    }

    //Adds a coin to the users hand. Done if they are going second.
    public void addCoin() {
        hand.add(Card.coin());
    }

    //prints opponents board, then users board in a formatted format
    public void printBoard(Hero opp) {
        ArrayList<Card> oppBoard = opp.board;
        int oppSize = oppBoard.size();
        int mySize = board.size();
        if (oppSize <= mySize) {
            double indents = 8 - mySize;
            for (int i = 0; i < indents; i++) System.out.print("    ");
            for (int i = 0; i < mySize; i++) System.out.print("_________   ");
            System.out.println();
            for (int i = 0; i < indents; i++) System.out.print("    ");
            for (Card curr : board) System.out.printf("|%d    %2d|   ", curr.mana, curr.attack);
            System.out.println();
            for (int i = 0; i < indents; i++) System.out.print("    ");
            for (Card curr : board) System.out.printf("|%6s |   ", curr.name);
            System.out.println();
            for (int i = 0; i < indents; i++) System.out.print("    ");
            for (Card curr : board) System.out.print("|      " + curr.health + "|   ");
            System.out.println();
            for (int i = 0; i < indents; i++) System.out.print("    ");
            for (int i = 0; i < mySize; i++) System.out.print("---------   ");
            System.out.println();
        } else {
            int indents = 8 - oppSize;
            int starts = oppSize - mySize;
            for (int i = 0; i < indents; i++) System.out.print("    ");
            for (int i = 0; i < starts; i++) System.out.print("      ");
            for (int i = 0; i < mySize; i++) System.out.print("_________   ");
            System.out.println();
            for (int i = 0; i < starts; i++) System.out.print("      ");
            for (int i = 0; i < indents; i++) System.out.print("    ");
            for (Card curr : board) System.out.printf("|%d    %2d|   ", curr.mana, curr.attack);
            System.out.println();
            for (int i = 0; i < starts; i++) System.out.print("      ");
            for (int i = 0; i < indents; i++) System.out.print("    ");
            for (Card curr : board) System.out.printf("|%6s |   ", curr.name);
            System.out.println();
            for (int i = 0; i < starts; i++) System.out.print("      ");
            for (int i = 0; i < indents; i++) System.out.print("    ");
            for (Card curr : board) System.out.print("|      " + curr.health + "|   ");
            System.out.println();
            for (int i = 0; i < starts; i++) System.out.print("      ");
            for (int i = 0; i < indents; i++) System.out.print("    ");
            for (int i = 0; i < mySize; i++) System.out.print("---------   ");
            System.out.println();
        }
    }
}