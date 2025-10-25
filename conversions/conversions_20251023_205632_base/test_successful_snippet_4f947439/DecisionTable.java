import java.util.Scanner;

public class DecisionTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String notprinting, flashing, notrecognized;

        System.out.print("The printer does not print (Y or N) ");
        notprinting = scanner.nextLine().toUpperCase();

        System.out.print("A red light is flashing (Y or N) ");
        flashing = scanner.nextLine().toUpperCase();

        System.out.print("The printer is unrecognized (Y or N) ");
        notrecognized = scanner.nextLine().toUpperCase();

        switch (notprinting + flashing + notrecognized) {
            case "YYY":
                System.out.println("Check the printer-computer cable");
                System.out.println("Ensure printer software is installed");
                System.out.println("Check/replace ink");
                break;
            case "YYN":
                System.out.println("Check/replace ink");
                System.out.println("Check for paper jam");
                break;
            case "YNY":
                System.out.println("Check the power cable");
                System.out.println("Check the printer-computer cable");
                System.out.println("Ensure printer software is installed");
                break;
            case "YNN":
                System.out.println("Check for paper jam");
                break;
            case "NYY":
                System.out.println("Ensure printer software is installed");
                System.out.println("Check/replace ink");
                break;
            case "NYN":
                System.out.println("Check/replace ink");
                break;
            case "NNY":
                System.out.println("Ensure printer software is installed");
                break;
            case "NNN":
                System.out.println("no action found");
                break;
            default:
                System.out.println("invalid input: " + notprinting + " " + flashing + " " + notrecognized);
                break;
        }

        System.out.println();
        scanner.close();
    }
}