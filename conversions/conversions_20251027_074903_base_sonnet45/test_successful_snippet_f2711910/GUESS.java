import java.util.Scanner;
import java.util.Random;

public class GUESS {
    private int answer = 0;
    private int theGuess = -1;
    private int delta = 0;
    private int tries = 0;
    private String done = " ";
    private String yes = "Y";
    private Random random;
    private Scanner scanner;

    public GUESS() {
        random = new Random();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        GUESS program = new GUESS();
        program.programBegin();
    }

    private void programBegin() {
        System.out.println("Welcome! Let's play a game.");
        seedRandom();
        selectNumber();
        while (!programFinished()) {
            mainLoop();
        }
        programDone();
    }

    private void programDone() {
        scanner.close();
        System.exit(0);
    }

    private void mainLoop() {
        do {
            promptUser();
        } while (!validGuess());
        checkGuess();
        theGuess = -1;
    }

    private void seedRandom() {
        long seed = System.currentTimeMillis();
        random.setSeed(seed);
        answer = random.nextInt(100);
    }

    private void selectNumber() {
        tries = 0;
        answer = random.nextInt(99) + 1;
    }

    private void promptUser() {
        System.out.println("Guess what number I'm thinking of between 1 and 99. (Enter 0 to give up.)");
        try {
            String input = scanner.nextLine().trim();
            theGuess = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            theGuess = -1;
        }
    }

    private void checkGuess() {
        if (userGivesUp()) {
            giveUp();
        } else {
            showHint();
        }
    }

    private void giveUp() {
        System.out.println("It was " + answer + "!");
        done = yes;
    }

    private void showHint() {
        tries++;
        delta = theGuess - answer;

        if (delta == 0) {
            System.out.println("Correct! You guessed it in " + tries + " tries!");
            done = yes;
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
            done = yes;
        } else {
            System.out.println("You've guessed " + tries + " times.");
        }
    }

    private boolean validGuess() {
        return theGuess >= 0 && theGuess <= 99;
    }

    private boolean userGivesUp() {
        return theGuess == 0;
    }

    private boolean programFinished() {
        return done.equals(yes);
    }
}