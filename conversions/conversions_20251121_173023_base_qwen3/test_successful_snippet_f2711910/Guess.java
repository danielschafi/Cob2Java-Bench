import java.util.Random;
import java.util.Scanner;

public class Guess {
    private static int answer = 0;
    private static int theGuess = -1;
    private static int delta = 0;
    private static int tries = 0;
    private static boolean programFinished = false;
    private static final char YES = 'Y';
    private static char done = ' ';
    private static long seedTime = 0;
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome! Let's play a game.");
        seedRandom();
        selectNumber();
        mainLoop();
    }

    private static void mainLoop() {
        while (!programFinished) {
            promptUser();
            if (isValidGuess()) {
                checkGuess();
                theGuess = -1;
            }
        }
    }

    private static void seedRandom() {
        seedTime = System.currentTimeMillis();
        random.setSeed(seedTime);
        answer = (int) (random.nextDouble() * 100);
    }

    private static void selectNumber() {
        tries = 0;
        answer = random.nextInt(99) + 1;
    }

    private static void promptUser() {
        System.out.println("Guess what number I'm thinking of between 1 and 99" +
                           " (Enter 0 to give up).");
        try {
            theGuess = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // consume invalid input
            theGuess = -1;
        }
    }

    private static boolean isValidGuess() {
        return theGuess >= 0 && theGuess <= 99;
    }

    private static void checkGuess() {
        if (isUserGivesUp()) {
            giveUp();
        } else {
            showHint();
        }
    }

    private static boolean isUserGivesUp() {
        return theGuess == 0;
    }

    private static void giveUp() {
        System.out.println("It was " + answer + "!");
        done = YES;
        programFinished = true;
    }

    private static void showHint() {
        tries++;
        delta = theGuess - answer;

        if (delta == 0) {
            System.out.println("Correct! You guessed it in " + tries + " tries!");
            done = YES;
            programFinished = true;
        }

        if (delta < 0) {
            System.out.println("Too low, guess again!");
        }

        if (delta > 0) {
            System.out.println("Too high, guess again!");
        }

        if (tries == 99) {
            System.out.println("You've guessed too many times!");
            System.out.println("The answer was " + answer + ".");
            done = YES;
            programFinished = true;
        } else {
            System.out.println("You've guessed " + tries + " times.");
        }
    }
}