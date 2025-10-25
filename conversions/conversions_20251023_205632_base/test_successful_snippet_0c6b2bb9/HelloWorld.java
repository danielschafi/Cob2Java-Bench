import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        int z = 0;

        String aDatum = "";
        String aNamn = "";
        int aAlder = 0;

        String rubrik = "RAPPORT " + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + " ";
        String resultat = "";

        x = 10;
        x = x + 1;
        x = x - 1;
        y = 6 * x;
        y = y / 3;
        z = y % 3;
        System.out.println("X=" + x + " Y=" + y + " Z=" + z);

        x = 10;
        y = (((x + 1) - 1) * 6) / 3;
        System.out.println("Y=" + y);

        aDatum = new SimpleDateFormat("yyyyMMdd").format(new Date());
        System.out.println("Dagens datum: " + aDatum);

        Scanner scanner = new Scanner(System.in);
        aNamn = scanner.nextLine();
        aAlder = scanner.nextInt();

        boolean bilNej = false;
        boolean bilJa = false;
        boolean sysNej = true;
        boolean sysJa = false;

        if (aAlder >= 18) {
            bilJa = true;
            if (aAlder >= 20) {
                sysJa = true;
            }
        } else {
            bilNej = true;
        }

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

        rubrik += aNamn;
        System.out.println(rubrik);
        System.out.println(resultat);

        System.out.println(aNamn.length());

        for (x = 0; x < aNamn.length(); x++) {
            if (aNamn.charAt(x) == ' ') {
                break;
            }
            System.out.println(aNamn.charAt(x));
        }

        scanner.close();
    }
}