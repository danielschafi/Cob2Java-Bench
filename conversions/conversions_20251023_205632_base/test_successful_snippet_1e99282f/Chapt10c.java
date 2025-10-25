import java.util.Scanner;

public class Chapt10c {
    private static String nameEntry = new String(new char[40]).replace('\0', ' ');
    private static String eMail = new String(new char[30]).replace('\0', ' ');
    private static String lastName = new String(new char[30]).replace('\0', ' ');
    private static String firstName = new String(new char[30]).replace('\0', ' ');
    private static String errorMessage = new String(new char[60]).replace('\0', ' ');
    private static int workNumber;
    private static int workNumber1;
    private static int workNumber2;
    private static boolean f1Pressed;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (!f1Pressed) {
            displayAndAcceptScreen(scanner);
        }
        scanner.close();
    }

    private static void displayAndAcceptScreen(Scanner scanner) {
        System.out.println("Name and E-mail Entry");
        System.out.println("  Name: " + nameEntry.trim());
        System.out.println("E-mail: " + eMail.trim());
        System.out.println("  Last: " + lastName.trim());
        System.out.println(" First: " + firstName.trim());
        System.out.println("Press F1 to Exit");
        System.out.println(errorMessage.trim());

        System.out.print("  Name: ");
        nameEntry = scanner.nextLine();
        System.out.print("E-mail: ");
        eMail = scanner.nextLine();

        lastName = new String(new char[30]).replace('\0', ' ');
        firstName = new String(new char[30]).replace('\0', ' ');
        errorMessage = new String(new char[60]).replace('\0', ' ');
        workNumber = 0;
        workNumber1 = 0;
        workNumber2 = 0;

        workNumber2 = countOccurrences(nameEntry, ",");
        if (workNumber2 > 0) {
            processTheData();
        } else {
            errorMessage = "Name must contain a comma";
        }
    }

    private static void processTheData() {
        workNumber = nameEntry.indexOf(",");
        lastName = nameEntry.substring(0, workNumber).trim();
        workNumber += 2;
        workNumber1 = countLeadingSpaces(nameEntry.substring(workNumber));
        firstName = nameEntry.substring(workNumber + workNumber1).trim();
        eMail = eMail.toLowerCase();
    }

    private static int countOccurrences(String str, String toCount) {
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(toCount, index)) != -1) {
            count++;
            index += toCount.length();
        }
        return count;
    }

    private static int countLeadingSpaces(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}