import java.io.*;
import java.util.Scanner;

public class USPRESRB {
    private static final int MAX_PRESIDENTS = 45;
    private static String[] wsPresTable = new String[MAX_PRESIDENTS];
    private static int wsPresNumber = 0;
    private static boolean endOfPres = false;
    private static String usrInput = "";
    private static int listIndex = 0;

    public static void main(String[] args) {
        System.out.println("USPRESRB BY RYAN BROOKS");
        try (BufferedReader reader = new BufferedReader(new FileReader("USPRES.DAT"))) {
            String line;
            int wsPresSub = 0;
            while ((line = reader.readLine()) != null && wsPresSub < MAX_PRESIDENTS) {
                wsPresTable[wsPresSub] = line;
                wsPresSub++;
            }
            endOfPres = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        while (!usrInput.equalsIgnoreCase("X")) {
            selectPresident(scanner);
            System.out.println();
            System.out.println("You picked " + String.format("%02d", wsPresNumber) + ", President " + wsPresTable[wsPresNumber - 1]);
            System.out.println();
            wsPresNumber = 0;
        }
        scanner.close();
    }

    private static void selectPresident(Scanner scanner) {
        while (!isValidPres()) {
            System.out.println();
            System.out.println("Which president (01-45)?  X exits, A lists all. ");
            usrInput = scanner.nextLine().trim();
            if (usrInput.equalsIgnoreCase("A")) {
                displayAll();
            } else if (usrInput.equalsIgnoreCase("X")) {
                System.exit(0);
            } else if (usrInput.matches("\\d+")) {
                wsPresNumber = Integer.parseInt(usrInput);
                if (!isValidPres()) {
                    System.out.println();
                    System.out.println("Please enter a valid input");
                }
            } else {
                System.out.println();
                System.out.println("Please enter a valid input");
            }
        }
    }

    private static boolean isValidPres() {
        return wsPresNumber >= 1 && wsPresNumber <= MAX_PRESIDENTS;
    }

    private static void displayAll() {
        listIndex = 0;
        while (listIndex < MAX_PRESIDENTS) {
            System.out.println("President " + wsPresTable[listIndex]);
            listIndex++;
        }
    }
}