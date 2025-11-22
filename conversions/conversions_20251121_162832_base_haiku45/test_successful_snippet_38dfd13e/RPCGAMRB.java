```java
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RPCGAMRB {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        String wsChoice;
        String wsTimeMs2;
        
        System.out.println("START RPSGAMRB BY RYAN BROOKS");
        
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String wsTodaysDate = today.format(dateFormatter);
        System.out.println("PROGRAM EXECUTION DATE      : " + wsTodaysDate);
        
        LocalTime now = LocalTime.now();
        String timeStr = String.format("%02d%02d%02d%02d%02d", 
            now.getHour(), now.getMinute(), now.getSecond(), 
            now.getNano() / 100000000, (now.getNano() / 10000000) % 10);
        System.out.println("PROGRAM EXECUTION START TIME: " + timeStr);
        
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("R FOR ROCK, P FOR PAPER, S FOR SCISSORS");
            wsChoice = scanner.nextLine().trim();
            if (wsChoice.length() > 0) {
                char choice = wsChoice.charAt(0);
                if (choice == 'R' || choice == 'r' || choice == 'P' || choice == 'p' || 
                    choice == 'S' || choice == 's') {
                    validChoice = true;
                    wsChoice = String.valueOf(choice);
                    
                    switch (choice) {
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
                }
            }
        }
        
        LocalTime timeForRandom = LocalTime.now();
        int randomDigit = (timeForRandom.getNano() / 10000000) % 10;
        
        System.out.println("--------------------------");
        System.out.println("TIME DIGIT: " + randomDigit);
        
        char computerChoice;
        if (randomDigit >= 0 && randomDigit <= 2) {
            computerChoice = 'R';
        } else if (randomDigit >= 3 && randomDigit <= 5) {
            computerChoice = 'P';
        } else {
            computerChoice = 'S';
        }
        
        char userChoice = wsChoice.charAt(0);
        if (userChoice == 'r') userChoice = 'R';
        if (userChoice == 'p') userChoice = 'P';
        if (userChoice == 's') userChoice = 'S';
        
        if (computerChoice == 'R') {
            System.out.println("The computer chooses rock.");
            System.out.println("--------------------------");
            if (userChoice == 'R') {
                System.out.println("Tie!");
            } else if (userChoice == 'P') {
                System.out.println("Paper wins equals you win.");
            } else {
                System.out.println("Rock crushes scissors, computer wins.");
            }
        } else if (computerChoice == 'P') {
            System.out.println("The computer chooses paper.");
            System.out.println("--------------------------");
            if (userChoice == 'R') {
                System.out.println("Paper beats rock, computer wins.");
            } else if (userChoice == 'P') {
                System.out.println("Tie!");
            } else {
                System.out.println("Scissors win, paper loses; player wins.");
            }
        } else {
            System.out.println("The computer chooses scissors.");
            System.out.println("--------------------------");
            if (userChoice == 'R') {
                System.out.println("You win, you crushed the scissors.");
            } else if (userChoice == 'P') {
                System.out.println("Scissors cuts paper, computer wins.");
            } else {
                System.out.println("Tie!");
            }
        }
        
        System.out.println("Press any key to exit.");
        scanner.nextLine();
        scanner.close();
    }
}