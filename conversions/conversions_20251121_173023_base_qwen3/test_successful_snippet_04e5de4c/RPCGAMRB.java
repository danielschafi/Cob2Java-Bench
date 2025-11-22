import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RPCGAMRB {
    private static String wsTodaysDate;
    private static int wsTimeHH;
    private static int wsTimeMM;
    private static int wsTimeSS;
    private static int wsTimeMS1;
    private static int wsTimeMS2;
    private static char wsChoice;
    private static int randomDigit;

    public static void main(String[] args) {
        System.out.println("START RPSGAMRB BY RYAN BROOKS");
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        wsTodaysDate = now.format(dateFormatter);
        System.out.println("PROGRAM EXECUTION DATE      : " + wsTodaysDate);
        
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssSS");
        String timeString = now.format(timeFormatter);
        wsTimeHH = Integer.parseInt(timeString.substring(0, 2));
        wsTimeMM = Integer.parseInt(timeString.substring(2, 4));
        wsTimeSS = Integer.parseInt(timeString.substring(4, 6));
        wsTimeMS1 = Integer.parseInt(timeString.substring(6, 7));
        wsTimeMS2 = Integer.parseInt(timeString.substring(7, 8));
        System.out.println("PROGRAM EXECUTION START TIME: " + wsTimeHH + ":" + wsTimeMM + ":" + wsTimeSS + "." + wsTimeMS1 + wsTimeMS2);
        
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("R FOR ROCK, P FOR PAPER, S FOR SCISSORS");
            wsChoice = scanner.nextLine().charAt(0);
            if (isChoiceValid()) {
                validChoice = true;
            }
        }
        
        if (isRockChosen()) {
            System.out.println("YOU CHOSE ROCK, SOLID CHOICE.");
        } else if (isPaperChosen()) {
            System.out.println("YOU CHOSE PAPER, YOU HAVE THIS COVERED.");
        } else if (isScissorsChosen()) {
            System.out.println("YOU CHOSE SCISSORS, HOPE IT'S NOT A ROCK.");
        }
        
        wsTimeMS2 = 9;
        boolean validRandomDigit = false;
        while (!validRandomDigit) {
            now = LocalDateTime.now();
            timeString = now.format(timeFormatter);
            wsTimeHH = Integer.parseInt(timeString.substring(0, 2));
            wsTimeMM = Integer.parseInt(timeString.substring(2, 4));
            wsTimeSS = Integer.parseInt(timeString.substring(4, 6));
            wsTimeMS1 = Integer.parseInt(timeString.substring(6, 7));
            wsTimeMS2 = Integer.parseInt(timeString.substring(7, 8));
            randomDigit = wsTimeMS2;
            if (isValidRandomDigit()) {
                validRandomDigit = true;
            }
            System.out.println("TIME DIGIT: " + wsTimeMS2);
        }
        
        if (isRandomPaper()) {
            System.out.println("The computer chooses paper.");
            if (isRockChosen()) {
                System.out.println("Paper beats rock, computer wins.");
            } else if (isScissorsChosen()) {
                System.out.println("Scissors win, paper loses; player wins.");
            } else if (isPaperChosen()) {
                System.out.println("Tie!");
            }
        } else if (isRandomRock()) {
            System.out.println("The computer chooses rock.");
            if (isPaperChosen()) {
                System.out.println("Paper wins equals you win.");
            } else if (isScissorsChosen()) {
                System.out.println("Rock crushes scissors, computer wins.");
            } else if (isRockChosen()) {
                System.out.println("Tie!");
            }
        } else if (isRandomScissors()) {
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
    
    private static boolean isChoiceValid() {
        return (wsChoice == 'R' || wsChoice == 'r' || 
                wsChoice == 'P' || wsChoice == 'p' || 
                wsChoice == 'S' || wsChoice == 's');
    }
    
    private static boolean isRockChosen() {
        return (wsChoice == 'R' || wsChoice == 'r');
    }
    
    private static boolean isPaperChosen() {
        return (wsChoice == 'P' || wsChoice == 'p');
    }
    
    private static boolean isScissorsChosen() {
        return (wsChoice == 'S' || wsChoice == 's');
    }
    
    private static boolean isValidRandomDigit() {
        return (randomDigit >= 0 && randomDigit <= 8);
    }
    
    private static boolean isRandomPaper() {
        return (randomDigit == 3 || randomDigit == 4 || randomDigit == 5);
    }
    
    private static boolean isRandomRock() {
        return (randomDigit == 0 || randomDigit == 1 || randomDigit == 2);
    }
    
    private static boolean isRandomScissors() {
        return (randomDigit == 6 || randomDigit == 7 || randomDigit == 8);
    }
}