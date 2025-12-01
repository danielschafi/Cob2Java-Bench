import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HelloWorld {
    private static int X = 0;
    private static int Y = 0;
    private static int Z = 0;
    
    private static String aDatum = "";
    private static String aNamn = "";
    private static int aAlder = 0;
    
    private static String datumRed = "";
    private static String namn = "";
    private static String resultat = "";
    
    private static boolean bilNej = false;
    private static boolean bilJa = false;
    private static boolean sysNej = false;
    private static boolean sysJa = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        X = 10;
        X = X + 1;
        X = X - 1;
        X = X * 6;
        Y = X / 3;
        Z = X % 3;
        System.out.println("X=" + X + " Y=" + Y + " Z=" + Z);
        
        X = 10;
        Y = (((X + 1) - 1) * 6) / 3;
        System.out.println("Y=" + Y);
        
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        aDatum = today.format(formatter);
        System.out.println("Dagens datum: " + aDatum);
        
        System.out.print("");
        aNamn = scanner.nextLine();
        System.out.print("");
        aAlder = scanner.nextInt();
        scanner.nextLine();
        
        sysNej = true;
        if (aAlder >= 18) {
            bilJa = true;
            if (aAlder >= 20) {
                sysJa = true;
            }
        } else {
            bilNej = true;
        }
        
        if (aAlder >= 0 && aAlder <= 17) {
            bilNej = true;
            sysNej = true;
        } else if (aAlder == 18 || aAlder == 19) {
            bilJa = true;
            sysNej = true;
        } else {
            bilJa = true;
            sysJa = true;
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
        
        datumRed = formatDatum(aDatum);
        namn = aNamn;
        
        String rubrik = "RAPPORT " + datumRed + "    " + namn;
        System.out.println(rubrik);
        System.out.println(resultat);
        
        System.out.println(namn.length());
        
        for (int i = 0; i < namn.length(); i++) {
            if (namn.charAt(i) == ' ') {
                break;
            }
            System.out.println(namn.charAt(i));
        }
    }
    
    private static String formatDatum(String datum) {
        if (datum.length() == 8) {
            return datum.substring(0, 4) + "/" + datum.substring(4, 6) + "/" + datum.substring(6, 8);
        }
        return datum;
    }
}