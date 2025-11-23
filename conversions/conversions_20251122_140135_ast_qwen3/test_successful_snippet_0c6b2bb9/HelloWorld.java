import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HelloWorld {
    private static int x;
    private static int y;
    private static int z;
    private static String aDatum;
    private static String aNamn;
    private static int aAlder;
    private static String datumRed;
    private static String namn;
    private static String resultat;
    private static boolean bilNej;
    private static boolean bilJa;
    private static boolean sysNej;
    private static boolean sysJa;

    public static void main(String[] args) {
        // Initialize variables
        x = 0;
        y = 0;
        z = 0;
        aDatum = "";
        aNamn = "";
        aAlder = 0;
        datumRed = "";
        namn = "";
        resultat = "";
        bilNej = false;
        bilJa = false;
        sysNej = false;
        sysJa = false;

        // First calculation block
        x = 10;
        x += 1;
        x -= 1;
        x *= 6;
        y = x / 3;
        z = x % 3;
        System.out.println("X=" + x + " Y=" + y + " Z=" + z);

        // Second calculation block
        x = 10;
        y = (((x + 1) - 1) * 6) / 3;
        System.out.println("Y=" + y);

        // Get current date
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        aDatum = currentDate.format(formatter);
        System.out.println("Dagens datum: " + aDatum);

        // Accept user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        aNamn = scanner.nextLine();
        System.out.print("Enter age: ");
        aAlder = scanner.nextInt();

        // Set initial condition
        sysNej = true;

        // If statement logic
        if (aAlder >= 18) {
            bilJa = true;
            if (aAlder >= 20) {
                sysJa = true;
            }
        } else {
            bilNej = true;
        }

        // Evaluate statement based on age
        switch (aAlder) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                bilNej = true;
                sysNej = true;
                break;
            case 18:
            case 19:
                bilJa = true;
                sysNej = true;
                break;
            default:
                bilJa = true;
                sysJa = true;
                break;
        }

        // Evaluate statement with conditions
        if (aNamn.equals("Pink Panther")) {
            resultat = "Du är ju Rosa Pantern! Du står över lagen!";
        } else if (aAlder < 18) {
            bilNej = true;
            sysNej = true;
        } else if (aAlder >= 18 && aAlder < 20) {
            bilJa = true;
            sysNej = true;
        } else {
            bilJa = true;
            sysJa = true;
        }

        // Copy date and name to report fields
        datumRed = aDatum;
        namn = aNamn;

        // Display report header
        System.out.println("RAPPORT " + datumRed + " " + namn);

        // Display result
        System.out.println(resultat);

        // Display length of name
        System.out.println(namn.length());

        // Display each character of name until space
        for (int i = 0; i < namn.length(); i++) {
            char c = namn.charAt(i);
            if (c == ' ') {
                break;
            }
            System.out.println(c);
        }

        scanner.close();
    }
}