import java.util.Random;
import java.util.Scanner;

public class Guess {
    private static int answer = 0;
    private static int theGuess = -1;
    private static int delta = 0;
    private static int tries = 0;
    private static boolean programFinished = false;
    private static final char yes = 'Y';
    
    public static void main(String[] args) {
        System.out.println("Welcome! Let's play a game.");
        seedRandom();
        selectNumber();
        mainLoop();
        System.exit(0);
    }
    
    private static void mainLoop() {
        while (!isValidGuess()) {
            promptUser();
        }
        checkGuess();
        theGuess = -1;
    }
    
    private static void seedRandom() {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        answer = (int) (random.nextDouble() * Integer.MAX_VALUE);
    }
    
    private static void selectNumber() {
        tries = 0;
        Random random = new Random();
        answer = random.nextInt(99) + 1;
    }
    
    private static void promptUser() {
        System.out.println("Guess what number I'm thinking of between 1 and 99- (Enter 0 to give up.)");
        Scanner scanner = new Scanner(System.in);
        try {
            theGuess = scanner.nextInt();
        } catch (Exception e) {
            theGuess = -1;
        }
    }
    
    private static void checkGuess() {
        if (isUserGivesUp()) {
            giveUp();
        } else {
            showHint();
        }
    }
    
    private static void giveUp() {
        System.out.println("It was " + answer + "!");
        programFinished = true;
    }
    
    private static void showHint() {
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
    
    private static boolean isValidGuess() {
        return theGuess >= 0 && theGuess <= 99;
    }
    
    private static boolean isUserGivesUp() {
        return theGuess == 0;
    }
}