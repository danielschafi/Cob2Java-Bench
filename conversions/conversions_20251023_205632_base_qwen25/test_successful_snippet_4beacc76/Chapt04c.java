import java.util.Scanner;

public class Chapt04c {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String lastName = "                         ";
        String firstName = "               ";
        String middleName = "          ";
        String addressLine1 = "                                                 ";
        String addressLine2 = "                                                 ";
        String city = "                                        ";
        String stateOrCountry = "                    ";
        String postalCode = "               ";
        String homePhone = "                   ";
        String workPhone = "                   ";
        String otherPhone = "                   ";
        String startDate = "00000000";
        String lastRentPaidDat = "00000000";
        String nextRentDueDate = "00000000";
        String rentAmount = "0000.00";
        String consignmentPercent = "000";

        System.out.println("\033[H\033[2J");
        System.out.flush();

        System.out.println("\u001B[34;41m" + "Darlene's Treasures" + "\u001B[0m");
        System.out.println("Tenant Entry Program");
        System.out.println("Name, Last: " + "\u001B[7m" + lastName + "\u001B[0m" + " First: " + "\u001B[7m" + firstName + "\u001B[0m" + " Middle: " + "\u001B[7m" + middleName + "\u001B[0m");
        System.out.println("Address 1: " + "\u001B[7m" + addressLine1 + "\u001B[0m");
        System.out.println("Address 2: " + "\u001B[7m" + addressLine2 + "\u001B[0m");
        System.out.println("City: " + "\u001B[7m" + city + "\u001B[0m");
        System.out.println("Country/State: " + "\u001B[7m" + stateOrCountry + "\u001B[0m" + " Postal Code: " + "\u001B[7m" + postalCode + "\u001B[0m");
        System.out.println("Phone/Home: " + "\u001B[7m" + homePhone + "\u001B[0m" + " Work: " + "\u001B[7m" + workPhone + "\u001B[0m");
        System.out.println("Other: " + "\u001B[7m" + otherPhone + "\u001B[0m");
        System.out.println("Start Date: " + "\u001B[7m" + startDate.substring(0, 2) + "/" + startDate.substring(2, 4) + "/" + startDate.substring(4) + "\u001B[0m" + " Last Paid Date: " + "\u001B[7m" + lastRentPaidDat.substring(0, 2) + "/" + lastRentPaidDat.substring(2, 4) + "/" + lastRentPaidDat.substring(4) + "\u001B[0m" + " Next Rent Due on: " + "\u001B[7m" + nextRentDueDate.substring(0, 2) + "/" + nextRentDueDate.substring(2, 4) + "/" + nextRentDueDate.substring(4) + "\u001B[0m");
        System.out.println("Consignment Percent: " + "\u001B[7m" + consignmentPercent + "\u001B[0m");

        System.out.print("Enter data: ");
        String input = scanner.nextLine();

        scanner.close();
    }
}