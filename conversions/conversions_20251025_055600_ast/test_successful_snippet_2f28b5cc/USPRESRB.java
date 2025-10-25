import java.io.*;
import java.util.Scanner;

public class USPRESRB {
    private static final int MAX_PRESIDENTS = 45;
    private static final String FILE_NAME = "USPRES.DAT";
    private String[][] wsPresTable = new String[MAX_PRESIDENTS][2];
    private int wsPresNumber = 0;
    private int wsPresSub = 0;
    private String usrInput = "";
    private int listIndex = 0;

    public static void main(String[] args) {
        USPRESRB program = new USPRESRB();
        program.run();
    }

    public void run() {
        System.out.println("USPRESRB BY RYAN BROOKS");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null && wsPresSub < MAX_PRESIDENTS) {
                wsPresTable[wsPresSub][0] = line.substring(0, 2).trim();
                wsPresTable[wsPresSub][1] = line.substring(2, 27).trim();
                wsPresSub++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (!usrInput.equalsIgnoreCase("X")) {
            selectPresident(scanner);
            System.out.println();
            System.out.println("You picked " + wsPresTable[wsPresNumber - 1][0] + ", President " + wsPresTable[wsPresNumber - 1][1]);
            System.out.println();
            wsPresNumber = 0;
        }
        scanner.close();
    }

    private void selectPresident(Scanner scanner) {
        while (wsPresNumber < 1 || wsPresNumber > MAX_PRESIDENTS) {
            System.out.println();
            System.out.println("Which president (01-45)?  X exits, A lists all. ");
            usrInput = scanner.nextLine().trim();

            if (usrInput.equalsIgnoreCase("A")) {
                displayAll();
            } else if (usrInput.equalsIgnoreCase("X")) {
                System.exit(0);
            } else if (usrInput.matches("\\d+")) {
                wsPresNumber = Integer.parseInt(usrInput);
                if (wsPresNumber < 1 || wsPresNumber > MAX_PRESIDENTS) {
                    System.out.println();
                    System.out.println("Please enter a valid input");
                }
            } else {
                System.out.println();
                System.out.println("Please enter a valid input");
            }
        }
    }

    private void displayAll() {
        listIndex = 0;
        while (listIndex < MAX_PRESIDENTS) {
            System.out.println("President " + wsPresTable[listIndex][1]);
            listIndex++;
        }
    }
}