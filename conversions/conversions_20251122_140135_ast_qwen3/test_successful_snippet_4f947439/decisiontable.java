import java.util.Scanner;

public class decisiontable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String notprinting;
        String flashing;
        String notrecognized;
        
        System.out.println();
        
        System.out.print("The printer does not print (Y or N) ");
        notprinting = scanner.nextLine().toUpperCase();
        
        System.out.print("A red light is flashing (Y or N) ");
        flashing = scanner.nextLine().toUpperCase();
        
        System.out.print("The printer is unrecognized (Y or N) ");
        notrecognized = scanner.nextLine().toUpperCase();
        
        System.out.println();
        
        if ("Y".equals(notprinting) && "Y".equals(flashing) && "Y".equals(notrecognized)) {
            System.out.println("Check the printer-computer cable");
            System.out.println("Ensure printer software is installed");
            System.out.println("Check/replace ink");
        } else if ("Y".equals(notprinting) && "Y".equals(flashing) && !"Y".equals(notrecognized)) {
            System.out.println("Check/replace ink");
            System.out.println("Check for paper jam");
        } else if ("Y".equals(notprinting) && !"Y".equals(flashing) && "Y".equals(notrecognized)) {
            System.out.println("Check the power cable");
            System.out.println("Check the printer-computer cable");
            System.out.println("Ensure printer software is installed");
        } else if ("Y".equals(notprinting) && !"Y".equals(flashing) && !"Y".equals(notrecognized)) {
            System.out.println("Check for paper jam");
        } else if ("N".equals(notprinting) && "Y".equals(flashing) && "Y".equals(notrecognized)) {
            System.out.println("Ensure printer software is installed");
            System.out.println("Check/replace ink");
        } else if ("N".equals(notprinting) && "Y".equals(flashing) && !"Y".equals(notrecognized)) {
            System.out.println("Check/replace ink");
        } else if ("N".equals(notprinting) && !"Y".equals(flashing) && "Y".equals(notrecognized)) {
            System.out.println("Ensure printer software is installed");
        } else if ("N".equals(notprinting) && !"Y".equals(flashing) && !"Y".equals(notrecognized)) {
            System.out.println("no action found");
        } else {
            System.out.println("invalid input: " + notprinting + " " + flashing + " " + notrecognized);
        }
        
        System.out.println();
    }
}