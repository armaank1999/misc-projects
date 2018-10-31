import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Hearthstone {
    public static Scanner console = new Scanner(System.in);
    public static Random globalRand = new Random();

    public static void main(String args[]) {
        System.out.println("Enter names 1 and 2. Press enter after each name.");
        String name1 = console.nextLine();
        Hero white = new Hero(name1);
        String name2 = console.nextLine();
        Hero black = new Hero(name2);
        setUp(white, black);
        Hero active = white, other = black, temp;
        while (!white.isDead() && !black.isDead()) {
            boolean playing = true;
            while (playing) {
                System.out.println(other);
                other.printBoard(active);
                active.printBoard(other);
                active.printHand();
                System.out.println(active);
                System.out.println("If you wish to play a card, enter card number, then board spot.");
                System.out.println("If you wish to end turn, enter -1. If you wish to use hero power, enter -2.");
                int input = console.nextInt();
                if (input == -1) {
                    playing = false;
                    autoAttack(active.board, other.board, other);
                    intermission();
                } else if (input == -2) active.heroPower();
                else {
                    int spot = console.nextInt();
                    if ((input >= 0 && input < active.hand.size()) && (spot >= 0 && spot <= active.board.size())) {
                        active.playCard(input, spot);
                    } else System.out.println("Make sure card number is in bounds and board spot is in bounds");
                }
            }
            temp = active;
            active = other;
            other = temp;
            active.drawCard();
            active.refreshMana();
        }
        if (white.isDead()) {
            System.out.println(white.name + " blew a 6-2 lead!");
            System.out.println(black.name + " is the champion!");
        }
        if (black.isDead()) {
            System.out.println(black.name + " blew a 3-1 lead!");
            System.out.println(white.name + " is the champion!");
        }
        System.out.println("Thank you for playing Hearthstone.java");
    }

    //After the end of each turn, each card on the board will attack or heal according to this method.
    private static void autoAttack(ArrayList<Card> board1, ArrayList<Card> board2, Hero opponent) {
        int oppSize = board2.size(), mySize = board1.size();
        if (oppSize == mySize) {
            for (int i = 0; i < mySize; i++) {
                int atk = board1.get(i).attack;
                if (atk < 0) {
                    if (i > 0) board1.get(i - 1).loseHealth(atk);
                    if (i < (mySize - 1)) board1.get(i + 1).loseHealth(atk);
                } else board2.get(i).loseHealth(atk);
            }
        } else if (oppSize > mySize) {
            int difference = oppSize - mySize;
            if (difference % 2 == 1) {
                for (int i = 0; i < mySize; i++) {
                    int atk = board1.get(i).attack;
                    if (atk < 0) {
                        if (i > 0) board1.get(i - 1).loseHealth(atk);
                        if (i < (mySize - 1)) board1.get(i + 1).loseHealth(atk);
                    } else {
                        board2.get((difference - 1) / 2 + i).loseHealth(atk);
                        board2.get((difference + 1) / 2 + i).loseHealth(atk);
                    }
                }
            } else {
                for (int i = 0; i < mySize; i++) {
                    int atk = board1.get(i).attack;
                    if (atk < 0) {
                        if (i > 0) board1.get(i - 1).loseHealth(atk);
                        if (i < (mySize - 1)) board1.get(i + 1).loseHealth(atk);
                    } else board2.get(difference / 2 + i).loseHealth(atk);
                }
            }
        } else {
            int difference = mySize - oppSize;
            for (int i = 0; i < mySize; i++) {
                int atk = board1.get(i).attack;
                if (atk < 0) {
                    if (i > 0) board1.get(i - 1).loseHealth(atk);
                    if (i < (mySize - 1)) board1.get(i + 1).loseHealth(atk);
                } else {
                    if (difference % 2 == 1) {
                        if (i > (difference - 1) / 2 && i < (difference - 1) / 2 + oppSize) {
                            board2.get(i - (difference + 1) / 2).loseHealth(atk);
                            board2.get(i - (difference - 1) / 2).loseHealth(atk);
                        } else if (i == (difference - 1) / 2 && oppSize != 0) {
                            board2.get(0).loseHealth(atk);
                        } else if (i == (difference - 1) / 2 + oppSize && oppSize != 0) {
                            board2.get(oppSize - 1).loseHealth(atk);
                        } else opponent.reduceHealth(atk);
                    } else if (i >= difference / 2 && i < difference / 2 + oppSize) {
                        board2.get(i - difference / 2).loseHealth(atk);
                    } else opponent.reduceHealth(atk);
                }
            }
        }
        removeDead(board2);
    }

    // Creates the starting hands and gives a coin to black
    public static void setUp(Hero white, Hero black) {
        white.drawCard();
        white.drawCard();
        white.drawCard();
        white.drawCard();
        black.drawCard();
        black.drawCard();
        black.drawCard();
        black.drawCard();
        black.addCoin();
        white.refreshMana();
    }

    // This method checks if a card on board is dead each turn and removes it from the board.
    private static void removeDead(ArrayList<Card> board) {
        for (int i = 0; i < board.size(); i++) if (board.get(i).health < 1) board.remove(i--);
    }

    // Creates a blank space so each player could hand the computer off to other between turns.
    private static void intermission() {
        for (int i = 0; i < 50; i++) System.out.println();
        System.out.println("Please pass computer to your opponent. Enter anything when done.");
        console.nextLine();
    }
}