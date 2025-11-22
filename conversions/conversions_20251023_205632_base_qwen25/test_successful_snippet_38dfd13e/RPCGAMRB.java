import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;

public class RPCGAMRB {
    public static void main(String[] args) {
        String wsTodaysDate;
        String wsTime;
        int wsTimeMs2;
        char wsChoice;
        char anyKey;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmssSS");

        wsTodaysDate = dateFormat.format(new Date());
        System.out.println("START RPSGAMRB BY RYAN BROOKS");
        System.out.println("PROGRAM EXECUTION DATE      : " + wsTodaysDate);
        wsTime = timeFormat.format(new Date());
        System.out.println("PROGRAM EXECUTION START TIME: " + wsTime);

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("R FOR ROCK, P FOR PAPER, S FOR SCISSORS");
            wsChoice = scanner.next().charAt(0);
        } while (!isValidChoice(wsChoice));

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

        Random random = new Random();
        wsTimeMs2 = random.nextInt(9);

        System.out.println("--------------------------");
        System.out.println("TIME DIGIT: " + wsTimeMs2);

        switch (wsTimeMs2) {
            case 0:
            case 1:
            case 2:
                System.out.println("The computer chooses rock.");
                if (Character.toUpperCase(wsChoice) == 'R') {
                    System.out.println("--------------------------");
                    System.out.println("Tie!");
                } else if (Character.toUpperCase(wsChoice) == 'P') {
                    System.out.println("--------------------------");
                    System.out.println("Paper wins equals you win.");
                } else if (Character.toUpperCase(wsChoice) == 'S') {
                    System.out.println("--------------------------");
                    System.out.println("Rock crushes scissors, computer wins.");
                }
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("The computer chooses paper.");
                if (Character.toUpperCase(wsChoice) == 'R') {
                    System.out.println("--------------------------");
                    System.out.println("Paper beats rock, computer wins.");
                } else if (Character.toUpperCase(wsChoice) == 'P') {
                    System.out.println("--------------------------");
                    System.out.println("Tie!");
                } else if (Character.toUpperCase(wsChoice) == 'S') {
                    System.out.println("--------------------------");
                    System.out.println("Scissors win, paper loses; player wins.");
                }
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("The computer chooses scissors.");
                if (Character.toUpperCase(wsChoice) == 'R') {
                    System.out.println("--------------------------");
                    System.out.println("You win, you crushed the scissors.");
                } else if (Character.toUpperCase(wsChoice) == 'P') {
                    System.out.println("--------------------------");
                    System.out.println("Scissors cuts paper, computer wins.");
                } else if (Character.toUpperCase(wsChoice) == 'S') {
                    System.out.println("--------------------------");
                    System.out.println("Tie!");
                }
                break;
        }

        System.out.println("Press any key to exit.");
        anyKey = scanner.next().charAt(0);
    }

    private static boolean isValidChoice(char choice) {
        return choice == 'R' || choice == 'r' || choice == 'P' || choice == 'p' || choice == 'S' || choice == 's';
    }
}