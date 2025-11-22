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
            wsChoice = scanner.nextLine().trim();
            if (wsChoice.length() > 0) {
                char choice = wsChoice.charAt(0);
                if (choice == 'R' || choice == 'r' || choice == 'P' || choice == 'p' || 
                    choice == 'S' || choice == 's') {
                    validChoice = true;
                }
            }
        }
        
        char userChoice = wsChoice.charAt(0);
        boolean rockChosen = (userChoice == 'R' || userChoice == 'r');
        boolean paperChosen = (userChoice == 'P' || userChoice == 'p');
        boolean scissorsChosen = (userChoice == 'S' || userChoice == 's');
        
        if (rockChosen) {
            System.out.println("YOU CHOSE ROCK, SOLID CHOICE.");
        } else if (paperChosen) {
            System.out.println("YOU CHOSE PAPER, YOU HAVE THIS COVERED.");
        } else if (scissorsChosen) {
            System.out.println("YOU CHOSE SCISSORS, HOPE IT'S NOT A ROCK.");
        }
        
        int wsTimeMs2 = 9;
        boolean validRandomDigit = false;
        
        while (!validRandomDigit) {
            LocalTime time = LocalTime.now();
            int milliseconds = time.getNano() / 1000000;
            wsTimeMs2 = milliseconds % 10;
            System.out.println("TIME DIGIT: " + wsTimeMs2);
            if (wsTimeMs2 >= 0 && wsTimeMs2 <= 8) {
                validRandomDigit = true;
            }
        }
        
        boolean randomRock = (wsTimeMs2 >= 0 && wsTimeMs2 <= 2);
        boolean randomPaper = (wsTimeMs2 >= 3 && wsTimeMs2 <= 5);
        boolean randomScissors = (wsTimeMs2 >= 6 && wsTimeMs2 <= 8);
        
        if (randomPaper) {
            System.out.println("The computer chooses paper.");
            if (rockChosen) {
                System.out.println("Paper beats rock, computer wins.");
            } else if (scissorsChosen) {
                System.out.println("Scissors win, paper loses; player wins.");
            } else if (paperChosen) {
                System.out.println("Tie!");
            }
        } else if (randomRock) {
            System.out.println("The computer chooses rock.");
            if (paperChosen) {
                System.out.println("Paper wins equals you win.");
            } else if (scissorsChosen) {
                System.out.println("Rock crushes scissors, computer wins.");
            } else if (rockChosen) {
                System.out.println("Tie!");
            }
        } else if (randomScissors) {
            System.out.println("The computer chooses scissors.");
            if (rockChosen) {
                System.out.println("You win, you crushed the scissors.");
            } else if (paperChosen) {
                System.out.println("Scissors cuts paper, computer wins.");
            } else if (scissorsChosen) {
                System.out.println("Tie!");
            }
        }
        
        System.out.println("Program exiting");
        scanner.close();
    }
}