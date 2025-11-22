import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class RPCGAMRB {
    public static void main(String[] args) {
        String wsTodaysDate;
        String wsTime;
        int wsTimeMS2;
        char wsChoice;
        char anyKey;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmssSSS");

        wsTodaysDate = dateFormat.format(new Date());
        System.out.println("START RPSGAMRB BY RYAN BROOKS");
        System.out.println("PROGRAM EXECUTION DATE      : " + wsTodaysDate);
        wsTime = timeFormat.format(new Date());
        System.out.println("PROGRAM EXECUTION START TIME: " + wsTime.substring(0, 8));

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

        wsTimeMS2 = Integer.parseInt(timeFormat.format(new Date()).substring(8, 9));
        while (wsTimeMS2 < 0 || wsTimeMS2 > 8) {
            wsTimeMS2 = Integer.parseInt(timeFormat.format(new Date()).substring(8, 9));
        }

        System.out.println("--------------------------");
        System.out.println("TIME DIGIT: " + wsTimeMS2);

        boolean randomRock = wsTimeMS2 >= 0 && wsTimeMS2 <= 2;
        boolean randomPaper = wsTimeMS2 >= 3 && wsTimeMS2 <= 5;
        boolean randomScissors = wsTimeMS2 >= 6 && wsTimeMS2 <= 8;

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
        anyKey = scanner.next().charAt(0);
    }

    private static boolean isValidChoice(char choice) {
        return choice == 'R' || choice == 'r' || choice == 'P' || choice == 'p' || choice == 'S' || choice == 's';
    }
}