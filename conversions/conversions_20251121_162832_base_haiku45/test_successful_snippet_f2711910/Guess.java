import java.util.Scanner;
import java.time.LocalTime;

public class Guess {
    private int answer;
    private int theGuess;
    private int delta;
    private int tries;
    private boolean programFinished;
    private Scanner scanner;

    public Guess() {
        this.answer = 0;
        this.theGuess = -1;
        this.delta = 0;
        this.tries = 0;
        this.programFinished = false;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Guess game = new Guess();
        game.programBegin();
    }

    private void programBegin() {
        System.out.println("Welcome! Let's play a game.");
        seedRandom();
        selectNumber();
        while (!programFinished) {
            mainLoop();
        }
        programDone();
    }

    private void programDone() {
        System.exit(0);
    }

    private void mainLoop() {
        while (!isValidGuess()) {
            promptUser();
        }
        checkGuess();
        theGuess = -1;
    }

    private void seedRandom() {
        long seed = System.currentTimeMillis();
    }

    private void selectNumber() {
        tries = 0;
        answer = (int) (Math.random() * 99) + 1;
    }

    private void promptUser() {
        System.out.println("Guess what number I'm thinking of between 1 and 99. (Enter 0 to give up.)");
        theGuess = scanner.nextInt();
    }

    private boolean isValidGuess() {
        return theGuess >= 0 && theGuess <= 99;
    }

    private boolean userGivesUp() {
        return theGuess == 0;
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
        programFinished = true;
    }

    private void showHint() {
        tries++;
        delta = theGuess - answer;

        if (delta == 0) {
            System.out.println("Correct! You guessed it in " + tries + " tries!");
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
            programFinished = true;
        } else {
            System.out.println("You've guessed " + tries + " times.");
        }
    }
}