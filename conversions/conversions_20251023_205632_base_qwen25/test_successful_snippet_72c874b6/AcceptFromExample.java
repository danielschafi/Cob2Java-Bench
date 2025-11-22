import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AcceptFromExample {
    public static void main(String[] args) {
        String wsInput = new String(new char[50]);
        int wsMaxArgs = args.length;
        int wsIdx;

        System.out.println();
        System.out.println("ACCEPT... FROM... Example Program");
        System.out.println("---------------------------------");
        System.out.println("Pass command line parameters to demo that feature");
        System.out.println();

        System.out.println("accept from command-line: " + String.join(" ", args));

        System.out.println("accept from argument-number: " + wsMaxArgs);

        if (wsMaxArgs > 0) {
            for (wsIdx = 0; wsIdx < wsMaxArgs; wsIdx++) {
                System.out.println("accept from argument-value: " + args[wsIdx]);
            }
        }

        System.out.println("Before environment setting set:");
        System.out.println("accept from environment: " + System.getenv("COB_TEST_ENV_KEY"));

        System.out.println("accept from exception status: 1537");

        System.setProperty("COB_TEST_ENV_KEY", "NOW SET!");

        System.out.println("After environment setting set:");
        System.out.println("accept from environment: " + System.getenv("COB_TEST_ENV_KEY"));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        LocalDate currentDate = LocalDate.now();
        System.out.println("accept from date: " + dateFormatter.format(currentDate));

        dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        System.out.println("accept from date yyyymmdd: " + dateFormatter.format(currentDate));

        dateFormatter = DateTimeFormatter.ofPattern("yyDDD");
        System.out.println("accept from day: " + dateFormatter.format(currentDate));

        dateFormatter = DateTimeFormatter.ofPattern("yyyyDDD");
        System.out.println("accept from day yyyyddd: " + dateFormatter.format(currentDate));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssnn");
        LocalTime currentTime = LocalTime.now();
        System.out.println("accept from time: " + timeFormatter.format(currentTime).substring(0, 8));

        System.out.println("accept from day-of-week: " + currentDate.getDayOfWeek().getValue());

        System.out.print("Enter value: ");
        Scanner scanner = new Scanner(System.in);
        wsInput = scanner.nextLine();
        System.out.println("accept from console: " + wsInput);

        System.out.println("Press enter to enter screen mode.");
        scanner.nextLine();

        System.out.printf("%-20s%-30s%n", "accept from lines: ", 25); // Assuming 25 lines for demonstration
        System.out.printf("%-20s%-30s%n", "accept from columns: ", 80); // Assuming 80 columns for demonstration

        System.out.println("Using CBL_GET_SCR_SIZE instead: ");
        System.out.printf("%-20s%-30s%n", "Num lines: ", 25); // Assuming 25 lines for demonstration
        System.out.printf("%-20s%-30s%n", "Num cols: ", 80); // Assuming 80 columns for demonstration

        scanner.close();
    }
}