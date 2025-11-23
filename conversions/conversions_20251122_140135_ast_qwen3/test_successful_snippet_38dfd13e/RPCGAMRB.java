import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RPCGAMRB {
    private static String wsTodaysDate;
    private static String wsTime;
    private static int wsTimeHh;
    private static int wsTimeMm;
    private static int wsTimeSs;
    private static int wsTimeMs1;
    private static int wsTimeMs2;
    private static char wsChoice;
    private static String anyKey;

    public static void main(String[] args) throws IOException {
        System.out.println("START RPSGAMRB BY RYAN BROOKS");
        
        // Get today's date
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        wsTodaysDate = now.format(dateFormatter);
        System.out.println("PROGRAM EXECUTION DATE      : " + wsTodaysDate);
        
        // Get current time
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssSS");
        wsTime = now.format(timeFormatter);
        System.out.println("PROGRAM EXECUTION START TIME: " + wsTime);
        
        // Prompt user for game choice until valid choice is made
        do {
            System.out.println("R FOR ROCK, P FOR PAPER, S FOR SCISSORS");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            wsChoice = (char) reader.read();
        } while (!isValidChoice());
        
        // Display user's choice
        switch (wsChoice) {
            case 'R':
            case 'r':
                System.out.println("YOU CHOSE ROCK, SOLID CHOICE.");
                break;
            case 'P':
            case 'p':
                System.out.println("YOU CHOSE PAPER, YOU HAVE THIS COVERED.");
                break;
            case 'S':
            case 's':
                System.out.println("YOU CHOSE SCISSORS, HOPE IT'S NOT A ROCK.");
                break;
        }
        
        // Quasi-random number generator for game
        wsTimeMs2 = 9;
        do {
            wsTime = now.format(timeFormatter);
            wsTimeMs2 = Character.getNumericValue(wsTime.charAt(6));
        } while (!isValidRandomDigit());
        
        System.out.println("--------------------------");
        System.out.println("TIME DIGIT: " + wsTimeMs2);
        
        // Determine computer choice based on time digit
        int computerChoice;
        if (wsTimeMs2 >= 0 && wsTimeMs2 <= 2) {
            computerChoice = 0; // Rock
        } else if (wsTimeMs2 >= 3 && wsTimeMs2 <= 5) {
            computerChoice = 1; // Paper
        } else {
            computerChoice = 2; // Scissors
        }
        
        // Display computer choice
        switch (computerChoice) {
            case 0:
                System.out.println("The computer chooses rock.");
                break;
            case 1:
                System.out.println("The computer chooses paper.");
                break;
            case 2:
                System.out.println("The computer chooses scissors.");
                break;
        }
        
        System.out.println("--------------------------");
        
        // Determine winner
        if (computerChoice == 0) { // Rock
            if (wsChoice == 'R' || wsChoice == 'r') {
                System.out.println("Tie!");
            } else if (wsChoice == 'P' || wsChoice == 'p') {
                System.out.println("Paper wins equals you win.");
            } else if (wsChoice == 'S' || wsChoice == 's') {
                System.out.println("Rock crushes scissors, computer wins.");
            }
        } else if (computerChoice == 1) { // Paper
            if (wsChoice == 'R' || wsChoice == 'r') {
                System.out.println("Paper beats rock, computer wins.");
            } else if (wsChoice == 'P' || wsChoice == 'p') {
                System.out.println("Tie!");
            } else if (wsChoice == 'S' || wsChoice == 's') {
                System.out.println("Scissors win, paper loses; player wins.");
            }
        } else if (computerChoice == 2) { // Scissors
            if (wsChoice == 'R' || wsChoice == 'r') {
                System.out.println("You win, you crushed the scissors.");
            } else if (wsChoice == 'P' || wsChoice == 'p') {
                System.out.println("Scissors cuts paper, computer wins.");
            } else if (wsChoice == 'S' || wsChoice == 's') {
                System.out.println("Tie!");
            }
        }
        
        System.out.println("Press any key to exit.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        anyKey = reader.readLine();
    }
    
    private static boolean isValidChoice() {
        return (wsChoice == 'R' || wsChoice == 'r' || wsChoice == 'P' || wsChoice == 'p' || wsChoice == 'S' || wsChoice == 's');
    }
    
    private static boolean isValidRandomDigit() {
        return (wsTimeMs2 >= 0 && wsTimeMs2 <= 8);
    }
}