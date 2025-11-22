import java.util.Random;
import java.util.Scanner;

public class Guess {
    private int answer;
    private int theGuess;
    private int delta;
    private int tries;
    private boolean done;
    private static final int MAX_TRIES = 99;

    public static void main(String[] args) {
        Guess game = new Guess();
        game.programBegin();
    }

    public void programBegin() {
        System.out.println("Welcome! Let's play a game.");
        seedRandom();
        selectNumber();
        while (!done) {
            mainLoop();
        }
    }

    public void mainLoop() {
        do {
            promptUser();
        } while (!validGuess());
        checkGuess();
        theGuess = -1;
    }

    public void seedRandom() {
        Random random = new Random();
        answer = random.nextInt(100);
    }

    public void selectNumber() {
        tries = 0;
        answer = (new Random().nextInt(100)) + 1;
    }

    public void promptUser() {
        System.out.println("Guess what number I'm thinking of between 1 and 99 - (Enter 0 to give up.)");
        Scanner scanner = new Scanner(System.in);
        theGuess = scanner.nextInt();
    }

    public void checkGuess() {
        if (userGivesUp()) {
            giveUp();
        } else {
            showHint();
        }
    }

    public void giveUp() {
        System.out.println("It was " + answer + "!");
        done = true;
    }

    public void showHint() {
        tries++;
        delta = theGuess - answer;

        if (delta == 0) {
            System.out.println("Correct! You guessed it in " + tries + " tries!");
            done = true;
        }

        if (delta < 0) {
            System.out.println("Too low, guess again!");
        }

        if (delta > 0) {
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

    public boolean validGuess() {
        return theGuess >= 0 && theGuess <= 99;
    }

    public boolean userGivesUp() {
        return theGuess == 0;
    }
}