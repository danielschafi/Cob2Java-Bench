import java.util.Random;
import java.util.Scanner;

public class PlayingCards {
    private static final int BASE_S = 4036985504;
    private static final int BASE_H = 4036985520;
    private static final int BASE_D = 4036985728;
    private static final int BASE_C = 4036985744;
    private static final int SPADES = 1;
    private static final int HEARTS = 2;
    private static final int DIAMONDS = 3;
    private static final int CLUBS = 4;
    private static final int PLAYERS = 3;
    private static final int CARDS_PER = 5;
    private static final int DECK_SIZE = 52;

    private static class Card {
        int slot;
        int hand;
        int suit;
        String symbol;
        int rank;
    }

    private static Card[] deck = new Card[DECK_SIZE];
    private static String[] suitName = new String[5];
    private static Random random;
    private static int c;
    private static int s;
    private static int r;
    private static int hit;
    private static int limiter;
    private static int tally;
    private static int player;
    private static int deal;
    private static String showRank;
    private static int arg;

    public static void main(String[] args) {
        seed(args);
        initializeDeck();
        shuffleDeck();
        dealDeck();
        displayHands();
    }

    private static void seed(String[] args) {
        if (args.length > 0) {
            arg = Integer.parseInt(args[0]);
            random = new Random(arg);
        } else {
            random = new Random();
        }
    }

    private static void initializeDeck() {
        suitName[SPADES] = "spades";
        suitName[HEARTS] = "hearts";
        suitName[DIAMONDS] = "diamonds";
        suitName[CLUBS] = "clubs";

        for (s = 1; s <= 4; s++) {
            for (r = 1; r <= 13; r++) {
                c = (s - 1) * 13 + r;
                deck[c - 1] = new Card();
                switch (s) {
                    case SPADES:
                        sym = BASE_S + r;
                        break;
                    case HEARTS:
                        sym = BASE_H + r;
                        break;
                    case DIAMONDS:
                        sym = BASE_D + r;
                        break;
                    case CLUBS:
                        sym = BASE_C + r;
                        break;
                }
                if (r > 11) {
                    sym += 1;
                }
                deck[c - 1].suit = s;
                deck[c - 1].rank = r;
                deck[c - 1].symbol = String.format("%04x", sym);
                deck[c - 1].slot = 0;
                deck[c - 1].hand = 0;
            }
        }
    }

    private static void shuffleDeck() {
        limiter = 0;
        while (true) {
            c = random.nextInt(52) + 1;
            hit = 0;
            for (tally = 1; tally <= 52; tally++) {
                if (deck[tally - 1].slot == c) {
                    hit = 1;
                    break;
                }
                if (deck[tally - 1].slot == 0) {
                    if (tally < 52) {
                        hit = 1;
                    }
                    deck[tally - 1].slot = c;
                    break;
                }
            }
            if (hit == 0) {
                break;
            }
            if (limiter > 999999) {
                System.err.println("too many shuffles, deck invalid");
                break;
            }
            limiter++;
        }
        java.util.Arrays.sort(deck, (a, b) -> Integer.compare(a.slot, b.slot));
    }

    private static void displayCard() {
        showRank = String.format("%02d", deck[tally - 1].rank);
        switch (deck[tally - 1].rank) {
            case 1:
                System.out.print("  ace");
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                System.out.print(showRank);
                break;
            case 11:
                System.out.print(" jack");
                break;
            case 12:
                System.out.print("queen");
                break;
            case 13:
                System.out.print(" king");
                break;
        }
        System.out.print(" of " + suitName[deck[tally - 1].suit]);
    }

    private static void displayHands() {
        System.out.println("Dealing " + CARDS_PER + " cards to " + PLAYERS + " players");
        for (player = 1; player <= PLAYERS; player++) {
            tally = player;
            System.out.print("Player " + player + ": ");
            for (deal = 1; deal <= CARDS_PER; deal++) {
                displayCard();
                tally += PLAYERS;
            }
            System.out.println();
        }
        System.out.print("Stock: ");
        tally -= PLAYERS;
        tally++;
        for (; tally <= DECK_SIZE; tally++) {
            displayCard();
            System.out.println();
        }
        System.out.println();
    }

    private static void dealDeck() {
        tally = 1;
        for (deal = 1; deal <= CARDS_PER; deal++) {
            for (player = 1; player <= PLAYERS; player++) {
                deck[tally - 1].hand = player;
                tally++;
            }
        }
    }
}