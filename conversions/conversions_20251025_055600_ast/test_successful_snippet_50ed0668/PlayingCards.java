import java.util.Random;

public class PlayingCards {
    private static final int[] SUIT_NAME = new int[5];
    private static final int BASE_S = 4036985504;
    private static final int BASE_H = 4036985520;
    private static final int BASE_D = 4036985728;
    private static final int BASE_C = 4036985744;
    private static final int[] SYM = new int[4];
    private static final int[] SYMX = SYM;
    private static int S;
    private static int R;
    private static int C;
    private static int HIT;
    private static int LIMITER;
    private static final int SPADES = 1;
    private static final int HEARTS = 2;
    private static final int DIAMONDS = 3;
    private static final int CLUBS = 4;
    private static final int PLAYERS = 3;
    private static final int CARDS_PER = 5;
    private static int DEAL;
    private static int PLAYER;
    private static int SHOW_TALLY;
    private static int SHOW_RANK;
    private static int ARG;
    private static final Card[] DECK = new Card[52];
    private static int TALLY;

    static {
        for (int i = 0; i < DECK.length; i++) {
            DECK[i] = new Card();
        }
        SUIT_NAME[SPADES] = "spades".hashCode();
        SUIT_NAME[HEARTS] = "hearts".hashCode();
        SUIT_NAME[DIAMONDS] = "diamonds".hashCode();
        SUIT_NAME[CLUBS] = "clubs".hashCode();
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            ARG = Integer.parseInt(args[0]);
        }
        seed();
        initializeDeck();
        shuffleDeck();
        dealDeck();
        displayHands();
    }

    private static void seed() {
        if (ARG != 0) {
            C = new Random(ARG).nextInt();
        }
    }

    private static void initializeDeck() {
        for (S = 1; S <= 4; S++) {
            for (R = 1; R <= 13; R++) {
                C = (S - 1) * 13 + R;
                switch (S) {
                    case SPADES:
                        SYM[0] = BASE_S + R;
                        break;
                    case HEARTS:
                        SYM[0] = BASE_H + R;
                        break;
                    case DIAMONDS:
                        SYM[0] = BASE_D + R;
                        break;
                    case CLUBS:
                        SYM[0] = BASE_C + R;
                        break;
                }
                if (R > 11) {
                    SYM[0]++;
                }
                DECK[C - 1].suit = S;
                DECK[C - 1].rank = R;
                DECK[C - 1].symbol = SYMX[0];
                DECK[C - 1].slot = 0;
                DECK[C - 1].hand = 0;
            }
        }
    }

    private static void shuffleDeck() {
        LIMITER = 0;
        while (true) {
            C = new Random().nextInt(52) + 1;
            HIT = 0;
            for (TALLY = 1; TALLY <= 52; TALLY++) {
                if (DECK[TALLY - 1].slot == C) {
                    HIT = 1;
                    break;
                }
                if (DECK[TALLY - 1].slot == 0) {
                    if (TALLY < 52) {
                        HIT = 1;
                    }
                    DECK[TALLY - 1].slot = C;
                    break;
                }
            }
            if (HIT == 0) {
                break;
            }
            if (LIMITER > 999999) {
                System.err.println("too many shuffles, deck invalid");
                break;
            }
            LIMITER++;
        }
        java.util.Arrays.sort(DECK, (a, b) -> Integer.compare(a.slot, b.slot));
    }

    private static void displayCard() {
        SHOW_RANK = DECK[TALLY - 1].rank;
        switch (SHOW_RANK) {
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
                System.out.print(SHOW_RANK);
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
        System.out.print(" of " + getSuitName(DECK[TALLY - 1].suit));
    }

    private static void displayDeck() {
        for (TALLY = 1; TALLY <= 52; TALLY++) {
            SHOW_TALLY = TALLY;
            System.out.print("Card: " + SHOW_TALLY + " currently in hand " + DECK[TALLY - 1].hand + " is ");
            displayCard();
            System.out.println();
        }
    }

    private static void displayHands() {
        for (PLAYER = 1; PLAYER <= PLAYERS; PLAYER++) {
            TALLY = PLAYER;
            System.out.print("Player " + PLAYER + ": ");
            for (DEAL = 1; DEAL <= CARDS_PER; DEAL++) {
                displayCard();
                TALLY += PLAYERS;
            }
            System.out.println();
        }
        System.out.print("Stock: ");
        TALLY -= PLAYERS;
        TALLY++;
        for (; TALLY <= 52; TALLY++) {
            displayCard();
        }
        System.out.println();
    }

    private static void dealDeck() {
        System.out.println("Dealing " + CARDS_PER + " cards to " + PLAYERS + " players");
        TALLY = 1;
        for (DEAL = 1; DEAL <= CARDS_PER; DEAL++) {
            for (PLAYER = 1; PLAYER <= PLAYERS; PLAYER++) {
                DECK[TALLY - 1].hand = PLAYER;
                TALLY++;
            }
        }
    }

    private static String getSuitName(int suit) {
        switch (suit) {
            case SPADES:
                return "spades";
            case HEARTS:
                return "hearts";
            case DIAMONDS:
                return "diamonds";
            case CLUBS:
                return "clubs";
            default:
                return "";
        }
    }

    private static class Card {
        int slot;
        int hand;
        int suit;
        int symbol;
        int rank;
    }
}