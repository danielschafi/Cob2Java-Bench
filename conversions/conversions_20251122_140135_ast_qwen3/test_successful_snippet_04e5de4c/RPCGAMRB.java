import java.util.Scanner;
import java.util.Date;

public class RPCGAMRB {
    private static String wsTodaysDate;
    private static int wsTimeHH;
    private static int wsTimeMM;
    private static int wsTimeSS;
    private static int wsTimeMS1;
    private static int wsTimeMS2;
    private static char wsChoice;
    
    public static void main(String[] args) {
        System.out.println("START RPSGAMRB BY RYAN BROOKS");
        
        // Get today's date
        Date date = new Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
        wsTodaysDate = sdf.format(date);
        System.out.println("PROGRAM EXECUTION DATE      : " + wsTodaysDate);
        
        // Get current time
        java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("HHmmssSS");
        String currentTime = timeFormat.format(date);
        wsTimeHH = Integer.parseInt(currentTime.substring(0, 2));
        wsTimeMM = Integer.parseInt(currentTime.substring(2, 4));
        wsTimeSS = Integer.parseInt(currentTime.substring(4, 6));
        wsTimeMS1 = Integer.parseInt(currentTime.substring(6, 7));
        wsTimeMS2 = Integer.parseInt(currentTime.substring(7, 8));
        System.out.println("PROGRAM EXECUTION START TIME: " + wsTimeHH + ":" + wsTimeMM + ":" + wsTimeSS);
        
        // Prompt user for game choice until valid choice is made
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("R FOR ROCK, P FOR PAPER, S FOR SCISSORS");
            Scanner scanner = new Scanner(System.in);
            wsChoice = scanner.next().charAt(0);
            
            if (isRockChosen() || isPaperChosen() || isScissorsChosen()) {
                validChoice = true;
            }
        }
        
        // Display the user's choice
        if (isRockChosen()) {
            System.out.println("YOU CHOSE ROCK, SOLID CHOICE.");
        } else if (isPaperChosen()) {
            System.out.println("YOU CHOSE PAPER, YOU HAVE THIS COVERED.");
        } else if (isScissorsChosen()) {
            System.out.println("YOU CHOSE SCISSORS, HOPE IT'S NOT A ROCK.");
        }
        
        // Quasi-random number generator for game
        wsTimeMS2 = 9;
        boolean validRandomDigit = false;
        while (!validRandomDigit) {
            Date timeDate = new Date();
            java.text.SimpleDateFormat timeFormat2 = new java.text.SimpleDateFormat("HHmmssSS");
            String currentTime2 = timeFormat2.format(timeDate);
            wsTimeHH = Integer.parseInt(currentTime2.substring(0, 2));
            wsTimeMM = Integer.parseInt(currentTime2.substring(2, 4));
            wsTimeSS = Integer.parseInt(currentTime2.substring(4, 6));
            wsTimeMS1 = Integer.parseInt(currentTime2.substring(6, 7));
            wsTimeMS2 = Integer.parseInt(currentTime2.substring(7, 8));
            System.out.println("TIME DIGIT: " + wsTimeMS2);
            
            if (isValidRandomDigit()) {
                validRandomDigit = true;
            }
        }
        
        // Display computer choice and determine winner
        int randomValue = wsTimeMS2;
        if (isRandomPaper(randomValue)) {
            System.out.println("The computer chooses paper.");
            if (isRockChosen()) {
                System.out.println("Paper beats rock, computer wins.");
            } else if (isScissorsChosen()) {
                System.out.println("Scissors win, paper loses; player wins.");
            } else if (isPaperChosen()) {
                System.out.println("Tie!");
            }
        } else if (isRandomRock(randomValue)) {
            System.out.println("The computer chooses rock.");
            if (isPaperChosen()) {
                System.out.println("Paper wins equals you win.");
            } else if (isScissorsChosen()) {
                System.out.println("Rock crushes scissors, computer wins.");
            } else if (isRockChosen()) {
                System.out.println("Tie!");
            }
        } else if (isRandomScissors(randomValue)) {
            System.out.println("The computer chooses scissors.");
            if (isRockChosen()) {
                System.out.println("You win, you crushed the scissors.");
            } else if (isPaperChosen()) {
                System.out.println("Scissors cuts paper, computer wins.");
            } else if (isScissorsChosen()) {
                System.out.println("Tie!");
            }
        }
        
        System.out.println("Program exiting");
    }
    
    private static boolean isRockChosen() {
        return wsChoice == 'R' || wsChoice == 'r';
    }
    
    private static boolean isPaperChosen() {
        return wsChoice == 'P' || wsChoice == 'p';
    }
    
    private static boolean isScissorsChosen() {
        return wsChoice == 'S' || wsChoice == 's';
    }
    
    private static boolean isValidRandomDigit() {
        return wsTimeMS2 >= 0 && wsTimeMS2 <= 8;
    }
    
    private static boolean isRandomPaper(int value) {
        return value >= 3 && value <= 5;
    }
    
    private static boolean isRandomRock(int value) {
        return value >= 0 && value <= 2;
    }
    
    private static boolean isRandomScissors(int value) {
        return value >= 6 && value <= 8;
    }
}