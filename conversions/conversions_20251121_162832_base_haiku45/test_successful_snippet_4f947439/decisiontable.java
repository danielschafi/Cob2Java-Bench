import java.util.Scanner;

public class decisiontable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String notprinting;
        String flashing;
        String notrecognized;
        
        System.out.println();
        
        System.out.print("The printer does not print (Y or N) ");
        notprinting = scanner.nextLine().trim().toUpperCase();
        
        System.out.print("A red light is flashing (Y or N) ");
        flashing = scanner.nextLine().trim().toUpperCase();
        
        System.out.print("The printer is unrecognized (Y or N) ");
        notrecognized = scanner.nextLine().trim().toUpperCase();
        
        System.out.println();
        
        if (notprinting.equals("Y") && flashing.equals("Y") && notrecognized.equals("Y")) {
            System.out.println("Check the printer-computer cable");
            System.out.println("Ensure printer software is installed");
            System.out.println("Check/replace ink");
        } else if (notprinting.equals("Y") && flashing.equals("Y") && notrecognized.equals("N")) {
            System.out.println("Check/replace ink");
            System.out.println("Check for paper jam");
        } else if (notprinting.equals("Y") && flashing.equals("N") && notrecognized.equals("Y")) {
            System.out.println("Check the power cable");
            System.out.println("Check the printer-computer cable");
            System.out.println("Ensure printer software is installed");
        } else if (notprinting.equals("Y") && flashing.equals("N") && notrecognized.equals("N")) {
            System.out.println("Check for paper jam");
        } else if (notprinting.equals("N") && flashing.equals("Y") && notrecognized.equals("Y")) {
            System.out.println("Ensure printer software is installed");
            System.out.println("Check/replace ink");
        } else if (notprinting.equals("N") && flashing.equals("Y") && notrecognized.equals("N")) {
            System.out.println("Check/replace ink");
        } else if (notprinting.equals("N") && flashing.equals("N") && notrecognized.equals("Y")) {
            System.out.println("Ensure printer software is installed");
        } else if (notprinting.equals("N") && flashing.equals("N") && notrecognized.equals("N")) {
            System.out.println("no action found");
        } else {
            System.out.println("invalid input: " + notprinting + " " + flashing + " " + notrecognized);
        }
        
        System.out.println();
        
        scanner.close();
    }
}