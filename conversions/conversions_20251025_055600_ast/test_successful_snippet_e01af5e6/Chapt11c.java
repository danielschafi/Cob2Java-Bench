import java.util.Scanner;

public class Chapt11c {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] keyboardStatus = new char[3];
        char[] tempField = new char[20];
        char[] formattedNumber = "(XXX) XXX-XXXX".toCharArray();
        char[] formattedAlternate = "XXX-XXXX".toCharArray();
        int nameLength = 0;
        int counter = 0;
        char[] lastName = new char[20];
        char[] firstName = new char[20];
        long phoneNumber = 0;
        char[] theEditedNumber = new char[14];
        char[] combinedName = new char[40];

        while (keyboardStatus[1] != 0x01) {
            System.out.print(" Enter Phone Number: ");
            phoneNumber = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            System.out.print("    Enter Last Name: ");
            scanner.nextLine(lastName);

            System.out.print("   Enter First Name: ");
            scanner.nextLine(firstName);

            if (phoneNumber > 9999999) {
                formattedNumber[1] = (char) ((phoneNumber / 1000000) % 10 + '0');
                formattedNumber[2] = (char) ((phoneNumber / 100000) % 10 + '0');
                formattedNumber[3] = (char) ((phoneNumber / 10000) % 10 + '0');
                formattedNumber[7] = (char) ((phoneNumber / 1000) % 10 + '0');
                formattedNumber[8] = (char) ((phoneNumber / 100) % 10 + '0');
                formattedNumber[9] = (char) ((phoneNumber / 10) % 10 + '0');
                formattedNumber[10] = (char) (phoneNumber % 10 + '0');
                System.arraycopy(formattedNumber, 0, theEditedNumber, 0, 14);
            } else {
                formattedAlternate[0] = (char) ((phoneNumber / 1000) % 10 + '0');
                formattedAlternate[1] = (char) ((phoneNumber / 100) % 10 + '0');
                formattedAlternate[2] = (char) ((phoneNumber / 10) % 10 + '0');
                formattedAlternate[3] = (char) (phoneNumber % 10 + '0');
                System.arraycopy(formattedAlternate, 0, theEditedNumber, 0, 8);
            }

            for (counter = 0; counter < 20 && firstName[counter] == ' '; counter++);
            if (counter < 20) {
                System.arraycopy(firstName, counter, tempField, 0, 20 - counter);
                System.arraycopy(tempField, 0, firstName, 0, 20);
            }

            for (counter = 0; counter < 20 && lastName[counter] == ' '; counter++);
            if (counter < 20) {
                System.arraycopy(lastName, counter, tempField, 0, 20 - counter);
                System.arraycopy(tempField, 0, lastName, 0, 20);
            }

            for (nameLength = 19; nameLength >= 0 && firstName[nameLength] == ' '; nameLength--);
            if (nameLength == -1) {
                System.arraycopy(lastName, 0, combinedName, 0, 20);
            } else {
                System.arraycopy(firstName, 0, combinedName, 0, nameLength + 1);
                combinedName[nameLength + 1] = ' ';
                System.arraycopy(lastName, 0, combinedName, nameLength + 2, 20);
            }

            System.out.println("          Full Name: " + new String(combinedName).trim());
            System.out.println("Edited Phone Number: " + new String(theEditedNumber).trim());
            System.out.println("Press F1 to Exit");

            keyboardStatus[1] = scanner.nextLine().charAt(0);
        }
        scanner.close();
    }
}