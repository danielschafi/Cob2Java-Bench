import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RPCGAMRB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("START RPSGAMRB BY RYAN BROOKS");
        
        LocalDate today = LocalDate.now();
        String todaysDate = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("PROGRAM EXECUTION DATE      : " + todaysDate);
        
        LocalTime currentTime = LocalTime.now();
        String timeString = currentTime.format(DateTimeFormatter.ofPattern("HHmmssSSS"));
        System.out.println("PROGRAM EXECUTION START TIME: " + timeString);
        
        String wsChoice = "";
        boolean validChoice = false;
        
        while (!validChoice) {
            System.out.println("R FOR ROCK, P FOR PAPER, S FOR SCISSORS");
            wsChoice = scanner.nextLine();
            if (wsChoice.length() > 0) {
                char choice = wsChoice.charAt(0);
                if (choice == 'R' || choice == 'r' || choice == 'P' || choice == 'p' || choice == 'S' || choice == 's') {
                    validChoice = true;
                }
            }
        }
        
        char userChoice = wsChoice.charAt(0);
        
        switch (userChoice) {
            case 'R':
                System.out.println("YOU CHOSE ROCK, SOLID CHOICE.");
                break;
            case 'P':
                System.out.println("YOU CHOSE PAPER, YOU HAVE THIS COVERED.");
                break;
            case 'S':
                System.out.println("YOU CHOSE SCISSORS, HOPE IT'S NOT A ROCK.");
                break;
            case 'r':
                System.out.println("YOU CHOSE ROCK, SOLID CHOICE.");
                break;
            case 'p':
                System.out.println("YOU CHOSE PAPER, YOU HAVE THIS COVERED.");
                break;
            case 's':
                System.out.println("YOU CHOSE SCISSORS, HOPE IT'S NOT A ROCK.");
                break;
        }
        
        int randomDigit = 9;
        boolean validRandomDigit = false;
        
        while (!validRandomDigit) {
            LocalTime time = LocalTime.now();
            int milliseconds = time.getNano() / 1000000;
            randomDigit = milliseconds % 10;
            if (randomDigit >= 0 && randomDigit <= 8) {
                validRandomDigit = true;
            }
        }
        
        System.out.println("--------------------------");
        System.out.println("TIME DIGIT: " + randomDigit);
        
        boolean rockChosen = (userChoice == 'R' || userChoice == 'r');
        boolean paperChosen = (userChoice == 'P' || userChoice == 'p');
        boolean scissorsChosen = (userChoice == 'S' || userChoice == 's');
        
        boolean randomRock = (randomDigit >= 0 && randomDigit <= 2);
        boolean randomPaper = (randomDigit >= 3 && randomDigit <= 5);
        boolean randomScissors = (randomDigit >= 6 && randomDigit <= 8);
        
        if (randomRock && rockChosen) {
            System.out.println("The computer chooses rock.");
            System.out.println("--------------------------");
            System.out.println("Tie!");
        } else if (randomRock && paperChosen) {
            System.out.println("The computer chooses rock.");
            System.out.println("--------------------------");
            System.out.println("Paper wins equals you win.");
        } else if (randomRock && scissorsChosen) {
            System.out.println("The computer chooses rock.");
            System.out.println("--------------------------");
            System.out.println("Rock crushes scissors, computer wins.");
        } else if (randomPaper && rockChosen) {
            System.out.println("The computer chooses paper.");
            System.out.println("--------------------------");
            System.out.println("Paper beats rock, computer wins.");
        } else if (randomPaper && paperChosen) {
            System.out.println("The computer chooses paper.");
            System.out.println("--------------------------");
            System.out.println("Tie!");
        } else if (randomPaper && scissorsChosen) {
            System.out.println("The computer chooses paper.");
            System.out.println("--------------------------");
            System.out.println("Scissors win, paper loses; player wins.");
        } else if (randomScissors && rockChosen) {
            System.out.println("The computer chooses scissors.");
            System.out.println("--------------------------");
            System.out.println("You win, you crushed the scissors.");
        } else if (randomScissors && paperChosen) {
            System.out.println("The computer chooses scissors.");
            System.out.println("--------------------------");
            System.out.println("Scissors cuts paper, computer wins.");
        } else if (randomScissors && scissorsChosen) {
            System.out.println("The computer chooses scissors.");
            System.out.println("--------------------------");
            System.out.println("Tie!");
        }
        
        System.out.println("Press any key to exit.");
        scanner.nextLine();
        scanner.close();
    }
}