import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RPCGAMRB {
    private static String wsTodaysDate;
    private static int wsTimeHH;
    private static int wsTimeMM;
    private static int wsTimeSS;
    private static int wsTimeMS1;
    private static int wsTimeMS2;
    private static char wsChoice;
    private static String anyKey;

    public static void main(String[] args) {
        System.out.println("START RPSGAMRB BY RYAN BROOKS");
        
        // Get current date
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        wsTodaysDate = now.format(dateFormatter);
        System.out.println("PROGRAM EXECUTION DATE      : " + wsTodaysDate);
        
        // Get current time
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssSS");
        String timeStr = now.format(timeFormatter);
        wsTimeHH = Integer.parseInt(timeStr.substring(0, 2));
        wsTimeMM = Integer.parseInt(timeStr.substring(2, 4));
        wsTimeSS = Integer.parseInt(timeStr.substring(4, 6));
        wsTimeMS1 = Integer.parseInt(timeStr.substring(6, 7));
        wsTimeMS2 = Integer.parseInt(timeStr.substring(7, 8));
        System.out.println("PROGRAM EXECUTION START TIME: " + wsTimeHH + ":" + wsTimeMM + ":" + wsTimeSS + "." + wsTimeMS1 + wsTimeMS2);
        
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user for game choice until valid
        do {
            System.out.println("R FOR ROCK, P FOR PAPER, S FOR SCISSORS");
            wsChoice = scanner.nextLine().charAt(0);
        } while (!isValidChoice());
        
        // Display user's choice
        switch (Character.toUpperCase(wsChoice)) {
            case 'R':
                System.out.println("YOU CHOSE ROCK, SOLID CHOICE.");
                break;
            case 'P':
                System.out.println("YOU CHOSE PAPER, YOU HAVE THIS COVERED.");
                break;
            case 'S':
                System.out.println("YOU CHOSE SCISSORS, HOPE IT'S NOT A ROCK.");
                break;
        }
        
        // Quasi-random number generator
        wsTimeMS2 = 9;
        do {
            now = LocalDateTime.now();
            String timeStr2 = now.format(timeFormatter);
            wsTimeMS2 = Integer.parseInt(timeStr2.substring(7, 8));
        } while (!isValidRandomDigit());
        
        System.out.println("--------------------------");
        System.out.println("TIME DIGIT: " + wsTimeMS2);
        
        // Determine computer's choice based on time digit
        boolean randomRock = (wsTimeMS2 == 0 || wsTimeMS2 == 1 || wsTimeMS2 == 2);
        boolean randomPaper = (wsTimeMS2 == 3 || wsTimeMS2 == 4 || wsTimeMS2 == 5);
        boolean randomScissors = (wsTimeMS2 == 6 || wsTimeMS2 == 7 || wsTimeMS2 == 8);
        
        if (randomRock && (wsChoice == 'R' || wsChoice == 'r')) {
            System.out.println("The computer chooses rock.");
            System.out.println("--------------------------");
            System.out.println("Tie!");
        } else if (randomRock && (wsChoice == 'P' || wsChoice == 'p')) {
            System.out.println("The computer chooses rock.");
            System.out.println("--------------------------");
            System.out.println("Paper wins equals you win.");
        } else if (randomRock && (wsChoice == 'S' || wsChoice == 's')) {
            System.out.println("The computer chooses rock.");
            System.out.println("--------------------------");
            System.out.println("Rock crushes scissors, computer wins.");
        } else if (randomPaper && (wsChoice == 'R' || wsChoice == 'r')) {
            System.out.println("The computer chooses paper.");
            System.out.println("--------------------------");
            System.out.println("Paper beats rock, computer wins.");
        } else if (randomPaper && (wsChoice == 'P' || wsChoice == 'p')) {
            System.out.println("The computer chooses paper.");
            System.out.println("--------------------------");
            System.out.println("Tie!");
        } else if (randomPaper && (wsChoice == 'S' || wsChoice == 's')) {
            System.out.println("The computer chooses paper.");
            System.out.println("--------------------------");
            System.out.println("Scissors win, paper loses; player wins.");
        } else if (randomScissors && (wsChoice == 'R' || wsChoice == 'r')) {
            System.out.println("The computer chooses scissors.");
            System.out.println("--------------------------");
            System.out.println("You win, you crushed the scissors.");
        } else if (randomScissors && (wsChoice == 'P' || wsChoice == 'p')) {
            System.out.println("The computer chooses scissors.");
            System.out.println("--------------------------");
            System.out.println("Scissors cuts paper, computer wins.");
        } else if (randomScissors && (wsChoice == 'S' || wsChoice == 's')) {
            System.out.println("The computer chooses scissors.");
            System.out.println("--------------------------");
            System.out.println("Tie!");
        }
        
        System.out.println("Press any key to exit.");
        anyKey = scanner.nextLine();
    }
    
    private static boolean isValidChoice() {
        return (wsChoice == 'R' || wsChoice == 'r' || wsChoice == 'P' || wsChoice == 'p' || wsChoice == 'S' || wsChoice == 's');
    }
    
    private static boolean isValidRandomDigit() {
        return (wsTimeMS2 >= 0 && wsTimeMS2 <= 8);
    }
}