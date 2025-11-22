import java.io.*;
import java.util.*;

public class Server {
    private static final int MAX_MESSAGES = 100;
    private static final int PAGE_SIZE = 10;
    private static String[] messages = new String[MAX_MESSAGES];
    private static int counter = 0;
    private static int offset = 0;
    private static String userName = "";
    private static String menuChoice = "";
    private static String messageChoice = "";

    public static void main(String[] args) {
        generateTable();
        displayLogin();
    }

    private static void generateTable() {
        try (BufferedReader reader = new BufferedReader(new FileReader("messages.dat"))) {
            String line;
            while ((line = reader.readLine()) != null && counter < MAX_MESSAGES) {
                messages[counter++] = line;
            }
            offset = counter;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Makers BBS");
        System.out.println("What's your name?");
        userName = scanner.nextLine().trim();
        displayMenu();
    }

    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Makers BBS");
        System.out.println("Welcome, " + userName);
        System.out.println("(n) Nothing");
        System.out.println("(m) Message board");
        System.out.println("(l) Logout");
        System.out.println("(q) Quit");
        System.out.print("Pick: ");
        menuChoice = scanner.nextLine().trim();

        if (menuChoice.equals("q")) {
            System.exit(0);
        } else if (menuChoice.equals("l")) {
            displayLogin();
        } else if (menuChoice.equals("n")) {
            displayMenu();
        } else if (menuChoice.equals("m")) {
            displayMessageBoard();
        }
    }

    private static void displayMessageBoard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Makers BBS");
        System.out.println("Here are the last 10 messages:");
        for (int i = 0; i < PAGE_SIZE; i++) {
            int index = offset - 1 - i;
            if (index >= 0 && index < counter) {
                System.out.println((i + 1) + ". " + messages[index]);
            }
        }
        System.out.println("( ) Read the full message by number");
        System.out.println("(m) Post a message of your own");
        System.out.println("(n) Next page");
        System.out.println("(p) Previous page");
        System.out.println("(q) Go back");
        System.out.print("Pick: ");
        messageChoice = scanner.nextLine().trim();

        if (messageChoice.equals("q")) {
            displayMenu();
        } else if (messageChoice.equals("n")) {
            if (offset > PAGE_SIZE) {
                offset -= PAGE_SIZE;
            } else {
                offset = PAGE_SIZE;
            }
            displayMessageBoard();
        } else if (messageChoice.equals("p")) {
            offset += PAGE_SIZE;
            displayMessageBoard();
        }
    }
}