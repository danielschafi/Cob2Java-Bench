import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RPCGAMRB {
    private static Scanner scanner = new Scanner(System.in);
    private static String wsChoice;
    private static int wsTimeMs2;
    private static String wsTodaysDate;
    private static LocalTime wsTime;

    public static void main(String[] args) {
        mainline();
    }

    private static void mainline() {
        System.out.println("START RPSGAMRB BY RYAN BROOKS");
        
        wsTodaysDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("PROGRAM EXECUTION DATE      : " + wsTodaysDate);
        
        wsTime = LocalTime.now();
        System.out.println("PROGRAM EXECUTION START TIME: " + wsTime);

        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("R FOR ROCK, P FOR PAPER, S FOR SCISSORS");
            wsChoice = scanner.nextLine().trim();
            if (isValidChoice(wsChoice)) {
                validChoice = true;
            }
        }

        if (isRockChosen(wsChoice)) {
            System.out.println("YOU CHOSE ROCK, SOLID CHOICE.");
        } else if (isPaperChosen(wsChoice)) {
            System.out.println("YOU CHOSE PAPER, YOU HAVE THIS COVERED.");
        } else if (isScissorsChosen(wsChoice)) {
            System.out.println("YOU CHOSE SCISSORS, HOPE IT'S NOT A ROCK.");
        }

        wsTimeMs2 = 9;
        boolean validRandomDigit = false;
        while (!validRandomDigit) {
            wsTime = LocalTime.now();
            int ms = wsTime.getNano() / 1000000;
            wsTimeMs2 = ms % 10;
            System.out.println("TIME DIGIT: " + wsTimeMs2);
            if (isValidRandomDigit(wsTimeMs2)) {
                validRandomDigit = true;
            }
        }

        if (isRandomPaper(wsTimeMs2)) {
            System.out.println("The computer chooses paper.");
            if (isRockChosen(wsChoice)) {
                System.out.println("Paper beats rock, computer wins.");
            } else if (isScissorsChosen(wsChoice)) {
                System.out.println("Scissors win, paper loses; player wins.");
            } else if (isPaperChosen(wsChoice)) {
                System.out.println("Tie!");
            }
        } else if (isRandomRock(wsTimeMs2)) {
            System.out.println("The computer chooses rock.");
            if (isPaperChosen(wsChoice)) {
                System.out.println("Paper wins equals you win.");
            } else if (isScissorsChosen(wsChoice)) {
                System.out.println("Rock crushes scissors, computer wins.");
            } else if (isRockChosen(wsChoice)) {
                System.out.println("Tie!");
            }
        } else if (isRandomScissors(wsTimeMs2)) {
            System.out.println("The computer chooses scissors.");
            if (isRockChosen(wsChoice)) {
                System.out.println("You win, you crushed the scissors.");
            } else if (isPaperChosen(wsChoice)) {
                System.out.println("Scissors cuts paper, computer wins.");
            } else if (isScissorsChosen(wsChoice)) {
                System.out.println("Tie!");
            }
        }

        System.out.println("Program exiting");
    }

    private static boolean isRockChosen(String choice) {
        return choice.equals("R") || choice.equals("r");
    }

    private static boolean isPaperChosen(String choice) {
        return choice.equals("P") || choice.equals("p");
    }

    private static boolean isScissorsChosen(String choice) {
        return choice.equals("S") || choice.equals("s");
    }

    private static boolean isValidChoice(String choice) {
        return isRockChosen(choice) || isPaperChosen(choice) || isScissorsChosen(choice);
    }

    private static boolean isRandomRock(int digit) {
        return digit == 0 || digit == 1 || digit == 2;
    }

    private static boolean isRandomPaper(int digit) {
        return digit == 3 || digit == 4 || digit == 5;
    }

    private static boolean isRandomScissors(int digit) {
        return digit == 6 || digit == 7 || digit == 8;
    }

    private static boolean isValidRandomDigit(int digit) {
        return digit >= 0 && digit <= 8;
    }
}