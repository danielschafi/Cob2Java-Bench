import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RPCGAMRB {
    private String wsTodaysDate;
    private int wsTimeHH;
    private int wsTimeMM;
    private int wsTimeSS;
    private int wsTimeMS1;
    private int wsTimeMS2;
    private String wsChoice;
    private String anyKey;
    private Scanner scanner;

    public RPCGAMRB() {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        RPCGAMRB game = new RPCGAMRB();
        game.mainline();
    }

    private void mainline() {
        System.out.println("START RPSGAMRB BY RYAN BROOKS");
        
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        wsTodaysDate = today.format(dateFormatter);
        System.out.println("PROGRAM EXECUTION DATE      : " + wsTodaysDate);
        
        LocalTime now = LocalTime.now();
        wsTimeHH = now.getHour();
        wsTimeMM = now.getMinute();
        wsTimeSS = now.getSecond();
        wsTimeMS1 = (now.getNano() / 100000000) % 10;
        wsTimeMS2 = (now.getNano() / 10000000) % 10;
        System.out.println("PROGRAM EXECUTION START TIME: " + String.format("%02d%02d%02d%d%d", wsTimeHH, wsTimeMM, wsTimeSS, wsTimeMS1, wsTimeMS2));
        
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("R FOR ROCK, P FOR PAPER, S FOR SCISSORS");
            wsChoice = scanner.nextLine().trim();
            if (wsChoice.length() > 0) {
                char choice = wsChoice.charAt(0);
                if (choice == 'R' || choice == 'r' || choice == 'P' || choice == 'p' || choice == 'S' || choice == 's') {
                    validChoice = true;
                }
            }
        }
        
        switch (wsChoice.toUpperCase()) {
            case "R":
                System.out.println("YOU CHOSE ROCK, SOLID CHOICE.");
                break;
            case "P":
                System.out.println("YOU CHOSE PAPER, YOU HAVE THIS COVERED.");
                break;
            case "S":
                System.out.println("YOU CHOSE SCISSORS, HOPE IT'S NOT A ROCK.");
                break;
        }
        
        wsTimeMS2 = 9;
        boolean validRandomDigit = false;
        while (!validRandomDigit) {
            LocalTime timeNow = LocalTime.now();
            wsTimeMS2 = (timeNow.getNano() / 10000000) % 10;
            if (wsTimeMS2 >= 0 && wsTimeMS2 <= 8) {
                validRandomDigit = true;
            }
        }
        
        System.out.println("--------------------------");
        System.out.println("TIME DIGIT: " + wsTimeMS2);
        
        boolean isRandomRock = (wsTimeMS2 >= 0 && wsTimeMS2 <= 2);
        boolean isRandomPaper = (wsTimeMS2 >= 3 && wsTimeMS2 <= 5);
        boolean isRandomScissors = (wsTimeMS2 >= 6 && wsTimeMS2 <= 8);
        
        boolean isRockChosen = wsChoice.equalsIgnoreCase("R");
        boolean isPaperChosen = wsChoice.equalsIgnoreCase("P");
        boolean isScissorsChosen = wsChoice.equalsIgnoreCase("S");
        
        if (isRandomRock && isRockChosen) {
            System.out.println("The computer chooses rock.");
            System.out.println("--------------------------");
            System.out.println("Tie!");
        } else if (isRandomRock && isPaperChosen) {
            System.out.println("The computer chooses rock.");
            System.out.println("--------------------------");
            System.out.println("Paper wins equals you win.");
        } else if (isRandomRock && isScissorsChosen) {
            System.out.println("The computer chooses rock.");
            System.out.println("--------------------------");
            System.out.println("Rock crushes scissors, computer wins.");
        } else if (isRandomPaper && isRockChosen) {
            System.out.println("The computer chooses paper.");
            System.out.println("--------------------------");
            System.out.println("Paper beats rock, computer wins.");
        } else if (isRandomPaper && isPaperChosen) {
            System.out.println("The computer chooses paper.");
            System.out.println("--------------------------");
            System.out.println("Tie!");
        } else if (isRandomPaper && isScissorsChosen) {
            System.out.println("The computer chooses paper.");
            System.out.println("--------------------------");
            System.out.println("Scissors win, paper loses; player wins.");
        } else if (isRandomScissors && isRockChosen) {
            System.out.println("The computer chooses scissors.");
            System.out.println("--------------------------");
            System.out.println("You win, you crushed the scissors.");
        } else if (isRandomScissors && isPaperChosen) {
            System.out.println("The computer chooses scissors.");
            System.out.println("--------------------------");
            System.out.println("Scissors cuts paper, computer wins.");
        } else if (isRandomScissors && isScissorsChosen) {
            System.out.println("The computer chooses scissors.");
            System.out.println("--------------------------");
            System.out.println("Tie!");
        }
        
        System.out.println("Press any key to exit.");
        anyKey = scanner.nextLine();
    }
}