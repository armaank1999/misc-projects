public class Card {
    // Card's name, mana cost, health, maxHealth(health changes when taking damage, max health so they cannot be healed over)
    // Negative attack means it heals friendly minions, 0 health means it dies immediately, so a spell.
    public String name;
    public int mana;
    public int attack;
    public int health;
    private int maxHealth;

    private Card(String label, int cost, int atk, int hp) {
        name = label;
        mana = cost;
        attack = atk;
        health = maxHealth = hp;
    }

    public static Card queen() {
        return new Card("Queen", 7, 4, 6);
    }

    public static Card rook() {
        return new Card("Rook", 3, 2, 6);
    }

    public static Card bishop() {
        return new Card("Bishop", 3, -2, 6);
    }

    public static Card knight() {
        return new Card("Knight", 4, 3, 4);
    }

    public static Card pawn() {
        return new Card("Pawn", 1, 1, 6);
    }

    public static Card coin() {
        return new Card("Coin", -1, 0, 0);
    }

    public void loseHealth(int x) {
        health = Math.min(health - x, maxHealth);
    }
}