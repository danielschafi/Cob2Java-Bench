import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloWorld {
    public static void main(String[] args) {
        int X = 0;
        int Y = 0;
        int Z = 0;
        String A_DATUM = "";
        String A_NAMN = "";
        int A_ALDER = 0;
        String RUBRIK = "RAPPORT       ";
        String DATUM_RED = "";
        String NAMN = "";
        String RESULTAT = "";

        X = 10;
        X = X + 1;
        X = X - 1;
        Y = 6 * X;
        Y = X / 3;
        Z = X % 3;
        System.out.println("X=" + X + " Y=" + Y + " Z=" + Z);

        X = 10;
        Y = (((X + 1) - 1) * 6) / 3;
        System.out.println("Y=" + Y);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        A_DATUM = dateFormat.format(new Date());
        System.out.println("Dagens datum: " + A_DATUM);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        A_NAMN = scanner.nextLine();
        System.out.print("Enter age: ");
        A_ALDER = scanner.nextInt();

        boolean SYS_NEJ = true;
        if (A_ALDER >= 18) {
            boolean BIL_JA = true;
            if (A_ALDER >= 20) {
                SYS_NEJ = false;
            }
        } else {
            boolean BIL_NEJ = true;
        }

        switch (A_ALDER) {
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
                SYS_NEJ = true;
                break;
            case 18:
            case 19:
                SYS_NEJ = true;
                break;
            default:
                SYS_NEJ = false;
                break;
        }

        if (A_NAMN.equals("Pink Panther")) {
            RESULTAT = "Du är ju Rosa Pantern! Du står över lagen!";
        } else if (A_ALDER < 18) {
            SYS_NEJ = true;
        } else if (A_ALDER >= 18 && A_ALDER < 20) {
            SYS_NEJ = true;
        } else {
            SYS_NEJ = false;
        }

        DATUM_RED = A_DATUM;
        NAMN = A_NAMN;
        System.out.println(RUBRIK);
        System.out.println(RESULTAT);

        System.out.println(NAMN.length());

        for (X = 1; X <= NAMN.length(); X++) {
            if (NAMN.charAt(X - 1) == ' ') {
                break;
            }
            System.out.println(NAMN.charAt(X - 1));
        }

        scanner.close();
    }
}