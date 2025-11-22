import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AcceptFromExample {
    public static void main(String[] args) {
        String wsInput = new String(new char[50]);
        int wsNumLines = 0;
        int wsNumCols = 0;
        String wsNumLinesDisp = "   ";
        String wsNumColsDisp = "   ";
        int wsMaxArgs = 0;
        int wsIdx = 0;

        System.out.println();
        System.out.println("ACCEPT... FROM... Example Program");
        System.out.println("---------------------------------");
        System.out.println("Pass command line parameters to demo that feature");
        System.out.println();

        wsInput = String.join(" ", args);
        System.out.println("accept from command-line: " + wsInput);

        wsMaxArgs = args.length;
        System.out.println("accept from argument-number: " + wsMaxArgs);

        if (wsMaxArgs > 0) {
            for (wsIdx = 1; wsIdx <= wsMaxArgs; wsIdx++) {
                wsInput = args[wsIdx - 1];
                System.out.println("accept from argument-value: " + wsInput);
            }
        }

        System.out.println("Before environment setting set:");
        String envValue = System.getenv("COB_TEST_ENV_KEY");
        if (envValue == null) {
            envValue = "";
        }
        System.out.println("accept from environment: " + envValue);

        System.out.println("accept from exception status: 1537");

        System.setProperty("COB_TEST_ENV_KEY", "NOW SET!");

        System.out.println("After environment setting set:");
        envValue = System.getenv("COB_TEST_ENV_KEY");
        System.out.println("accept from environment: " + envValue);

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        wsInput = currentDate.format(dateFormatter);
        System.out.println("accept from date: " + wsInput);

        dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        wsInput = currentDate.format(dateFormatter);
        System.out.println("accept from date yyyymmdd: " + wsInput);

        int dayOfYear = currentDate.getDayOfYear();
        wsInput = String.format("%03d", dayOfYear);
        System.out.println("accept from day: " + wsInput);

        wsInput = String.format("%04d%03d", currentDate.getYear(), dayOfYear);
        System.out.println("accept from day yyyyddd: " + wsInput);

        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssnn");
        wsInput = currentTime.format(timeFormatter).substring(0, 8);
        System.out.println("accept from time: " + wsInput);

        int dayOfWeek = currentDate.getDayOfWeek().getValue();
        System.out.println("accept from day-of-week: " + dayOfWeek);

        String userName = System.getProperty("user.name");
        System.out.println("accept from user name: " + userName);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value: ");
        wsInput = scanner.nextLine();
        System.out.println("accept from console: " + wsInput);

        System.out.println("Press enter to enter screen mode.");
        scanner.nextLine();

        wsNumLines = 24; // Assuming a default screen size of 24 lines
        wsNumCols = 80;  // Assuming a default screen size of 80 columns
        wsNumLinesDisp = String.format("%03d", wsNumLines);
        wsNumColsDisp = String.format("%03d", wsNumCols);
        System.out.printf("%2$02d%1$s%3$02d%s\n", "accept from lines: ", 2, 20, wsNumLinesDisp);
        System.out.printf("%2$02d%1$s%3$02d%s\n", "accept from columns: ", 3, 22, wsNumColsDisp);

        System.out.printf("%2$02d%1$s\n", "Using CBL_GET_SCR_SIZE instead: ", 4);
        System.out.printf("%2$02d%1$s%3$02d%s\n", "Num lines: ", 5, 14, wsNumLinesDisp);
        System.out.printf("%2$02d%1$s%3$02d%s\n", "Num cols: ", 6, 14, wsNumColsDisp);

        scanner.close();
    }
}