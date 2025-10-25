import java.io.*;
import java.util.*;

public class server {
    private static final int MAX_MESSAGES = 100;
    private static final int MESSAGE_TITLE_LENGTH = 60;
    private static final int MESSAGE_BODY_LENGTH = 500;

    private String[] wsMsg = new String[MAX_MESSAGES];
    private int counter;
    private int offset;
    private char menuChoice;
    private char messageChoice;
    private String userName;

    public static void main(String[] args) {
        server serverInstance = new server();
        serverInstance.run();
    }

    public void run() {
        generateTable();
        displayLogin();
    }

    private void generateTable() {
        counter = 0;
        int wsFileIsEnded = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("messages.dat"))) {
            String line;
            while ((line = reader.readLine()) != null && wsFileIsEnded == 0) {
                if (line.length() >= MESSAGE_TITLE_LENGTH) {
                    wsMsg[counter] = line.substring(0, MESSAGE_TITLE_LENGTH);
                    counter++;
                } else {
                    wsFileIsEnded = 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        offset = counter;
    }

    private void displayLogin() {
        userName = "";
        System.out.println("Makers BBS");
        System.out.println("What's your name?");
        Scanner scanner = new Scanner(System.in);
        userName = scanner.nextLine().substring(0, Math.min(userName.length(), 10));
        displayMenu();
    }

    private void displayMenu() {
        menuChoice = ' ';
        System.out.println("Makers BBS");
        System.out.println("Welcome, " + userName);
        System.out.println("(n) Nothing");
        System.out.println("(m) Message board");
        System.out.println("(l) Logout");
        System.out.println("(q) Quit");
        System.out.print("Pick: ");
        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextLine().charAt(0);
        if (menuChoice == 'q') {
            System.exit(0);
        } else if (menuChoice == 'l') {
            displayLogin();
        } else if (menuChoice == 'n') {
            displayMenu();
        } else if (menuChoice == 'm') {
            displayMessageBoard();
        }
    }

    private void displayMessageBoard() {
        messageChoice = ' ';
        System.out.println("Makers BBS");
        System.out.println("Here are the last 10 messages:");
        for (int i = 0; i < 10 && offset - i >= 0; i++) {
            System.out.println((i + 1) + ". " + wsMsg[offset - i]);
        }
        System.out.println("( ) Read the full message by number");
        System.out.println("(m) Post a message of your own");
        System.out.println("(n) Next page");
        System.out.println("(p) Previous page");
        System.out.println("(q) Go back");
        System.out.print("Pick: ");
        Scanner scanner = new Scanner(System.in);
        messageChoice = scanner.nextLine().charAt(0);
        if (messageChoice == 'q') {
            displayMenu();
        } else if (messageChoice == 'n') {
            if (offset > 10) {
                offset -= 10;
            } else {
                offset = 10;
            }
            displayMessageBoard();
        } else if (messageChoice == 'p') {
            offset += 10;
            displayMessageBoard();
        }
    }
}