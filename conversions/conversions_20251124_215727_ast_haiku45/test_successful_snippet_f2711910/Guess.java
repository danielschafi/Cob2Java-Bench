import java.util.Scanner;

public class Guess {
    private int answer = 0;
    private int theGuess = -1;
    private int delta = 0;
    private int tries = 0;
    private char done = ' ';
    private char yes = 'Y';
    private long seed = 0;

    public static void main(String[] args) {
        Guess game = new Guess();
        game.programBegin();
    }

    private void programBegin() {
        System.out.println("Welcome! Let's play a game.");
        seedRandom();
        selectNumber();
        while (done != 'Y') {
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
        seed = System.currentTimeMillis();
    }

    private void selectNumber() {
        tries = 0;
        answer = (int) ((Math.random() * 99) + 1);
    }

    private void promptUser() {
        System.out.println("Guess what number I'm thinking of between 1 and 99. (Enter 0 to give up.)");
        Scanner scanner = new Scanner(System.in);
        theGuess = scanner.nextInt();
    }

    private void checkGuess() {
        if (isUserGivesUp()) {
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

    private boolean isValidGuess() {
        return theGuess >= 0 && theGuess <= 99;
    }

    private boolean isUserGivesUp() {
        return theGuess == 0;
    }
}