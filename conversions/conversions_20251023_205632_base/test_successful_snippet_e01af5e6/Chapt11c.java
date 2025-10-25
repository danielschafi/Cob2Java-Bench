import java.util.Scanner;

public class Chapt11c {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String keyboardStatus = "0 ";
        char functionKey;
        boolean f1Pressed = false;
        String tempField = "                    ";
        String formattedNumber = "(XXX) XXX-XXXX";
        String formattedAlternate = "XXX-XXXX";
        int nameLength = 0;
        int counter = 0;
        String lastName = "                    ";
        String firstName = "                    ";
        long phoneNumber = 0;
        String theEditedNumber = "              ";
        String combinedName = "                                        ";

        while (!f1Pressed) {
            System.out.println(" Enter Phone Number: " + String.format("%010d", phoneNumber));
            System.out.println("    Enter Last Name: " + lastName);
            System.out.println("   Enter First Name: " + firstName);
            System.out.println("          Full Name: " + combinedName);
            System.out.println("Edited Phone Number: " + theEditedNumber);
            System.out.println("Press F1 to Exit");

            phoneNumber = scanner.nextLong();
            scanner.nextLine(); // Consume newline
            lastName = scanner.nextLine();
            firstName = scanner.nextLine();
            keyboardStatus = scanner.nextLine();
            functionKey = keyboardStatus.charAt(1);
            f1Pressed = functionKey == 0x01;

            formattedNumber = "(XXX) XXX-XXXX";
            formattedAlternate = "XXX-XXXX";

            if (phoneNumber > 9999999) {
                formattedNumber = formattedNumber.replaceFirst("XXX", String.format("%03d", phoneNumber / 10000000 % 1000));
                formattedNumber = formattedNumber.replaceFirst("XXX", String.format("%03d", phoneNumber / 10000 % 1000));
                formattedNumber = formattedNumber.replaceFirst("XXXX", String.format("%04d", phoneNumber % 10000));
                theEditedNumber = formattedNumber;
            } else {
                formattedAlternate = formattedAlternate.replaceFirst("XXX", String.format("%03d", phoneNumber / 10000 % 1000));
                formattedAlternate = formattedAlternate.replaceFirst("XXXX", String.format("%04d", phoneNumber % 10000));
                theEditedNumber = formattedAlternate;
            }

            if (!firstName.trim().isEmpty()) {
                for (counter = 0; counter < firstName.length(); counter++) {
                    if (firstName.charAt(counter) > ' ') {
                        break;
                    }
                }
                tempField = firstName.substring(counter);
                firstName = tempField;
            }

            if (!lastName.trim().isEmpty()) {
                for (counter = 0; counter < lastName.length(); counter++) {
                    if (lastName.charAt(counter) > ' ') {
                        break;
                    }
                }
                tempField = lastName.substring(counter);
                lastName = tempField;
            }

            for (nameLength = 20; nameLength > 0; nameLength--) {
                if (firstName.charAt(nameLength - 1) > ' ' || nameLength == 0) {
                    break;
                }
            }

            if (nameLength == 0) {
                combinedName = lastName;
            } else {
                combinedName = firstName.substring(0, nameLength) + " " + lastName;
            }
        }
        scanner.close();
    }
}