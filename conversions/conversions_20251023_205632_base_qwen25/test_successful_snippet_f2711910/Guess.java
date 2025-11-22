import java.util.Random;
import java.util.Scanner;

public class Guess {
    private static int answer;
    private static int theGuess;
    private static int delta;
    private static int tries;
    private static boolean done;
    private static final int MAX_TRIES = 99;
    private static final int GIVE_UP = 0;

    public static void main(String[] args) {
        System.out.println("Welcome! Let's play a game.");
        seedRandom();
        selectNumber();
        mainLoop();
    }

    private static void mainLoop() {
        while (!done) {
            promptUser();
            checkGuess();
            theGuess = -1;
        }
    }

    private static void seedRandom() {
        Random random = new Random(System.currentTimeMillis());
        answer = random.nextInt(100) + 1;
    }

    private static void selectNumber() {
        tries = 0;
        answer = new Random().nextInt(99) + 1;
    }

    private static void promptUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess what number I'm thinking of between 1 and 99\n" +
                           "- (Enter 0 to give up.)");
        theGuess = scanner.nextInt();
    }

    private static void checkGuess() {
        if (theGuess == GIVE_UP) {
            giveUp();
        } else {
            showHint();
        }
    }

    private static void giveUp() {
        System.out.println("It was " + answer + "!");
        done = true;
    }

    private static void showHint() {
        tries++;
        delta = theGuess - answer;

        if (delta == 0) {
            System.out.println("Correct! You guessed it in " + tries + " tries!");
            done = true;
        } else if (delta < 0) {
            System.out.println("Too low, guess again!");
        } else if (delta > 0) {
            System.out.println("Too high, guess again!");
        }

        if (tries == MAX_TRIES) {
            System.out.println("You've guessed too many times!");
            System.out.println("The answer was " + answer + ".");
            done = true;
        } else {
            System.out.println("You've guessed " + tries + " times.");
        }
    }
}