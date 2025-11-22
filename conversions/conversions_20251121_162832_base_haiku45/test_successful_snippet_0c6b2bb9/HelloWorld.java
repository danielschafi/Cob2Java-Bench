import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HelloWorld {
    static class AArbetstareor {
        String aDatum;
        String aNamn;
        int aAlder;
    }

    static class Utvardering {
        String rubrik;
        String resultat;
        String datumRed;
        String namn;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int x = 0, y = 0, z = 0;
        
        x = 10;
        x = x + 1;
        x = x - 1;
        x = x * 6;
        y = x / 3;
        z = x % 3;
        System.out.println("X=" + x + " Y=" + y + " Z=" + z);
        
        x = 10;
        y = (int)(((x + 1) - 1) * 6) / 3;
        System.out.println("Y=" + y);
        
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String aDatum = today.format(formatter);
        System.out.println("Dagens datum: " + aDatum);
        
        System.out.print("");
        String aNamn = scanner.nextLine();
        System.out.print("");
        String aAlderStr = scanner.nextLine();
        int aAlder = Integer.parseInt(aAlderStr.trim());
        
        boolean bilNej = false;
        boolean bilJa = false;
        boolean sysNej = true;
        boolean sysJa = false;
        
        if (aAlder >= 18) {
            bilJa = true;
            bilNej = false;
            if (aAlder >= 20) {
                sysJa = true;
                sysNej = false;
            }
        } else {
            bilNej = true;
            bilJa = false;
        }
        
        if (aAlder >= 0 && aAlder <= 17) {
            bilNej = true;
            bilJa = false;
            sysNej = true;
            sysJa = false;
        } else if (aAlder == 18 || aAlder == 19) {
            bilJa = true;
            bilNej = false;
            sysNej = true;
            sysJa = false;
        } else {
            bilJa = true;
            bilNej = false;
            sysJa = true;
            sysNej = false;
        }
        
        String resultat = "";
        if (aNamn.equals("Pink Panther")) {
            resultat = "Du är ju Rosa Pantern! Du står över lagen!";
        } else if (aAlder < 18) {
            bilNej = true;
            bilJa = false;
            sysNej = true;
            sysJa = false;
            resultat = "Du får INTE köra bil.";
        } else if (aAlder >= 18 && aAlder < 20) {
            bilJa = true;
            bilNej = false;
            sysNej = true;
            sysJa = false;
            resultat = "Du får köra bil.";
        } else {
            bilJa = true;
            bilNej = false;
            sysJa = true;
            sysNej = false;
            resultat = "Du får köra bil.";
        }
        
        String datumRed = formatDatum(aDatum);
        String rubrik = "RAPPORT " + datumRed + "    " + aNamn;
        
        System.out.println(rubrik);
        System.out.println(resultat);
        
        System.out.println(aNamn.length());
        
        for (int i = 0; i < aNamn.length(); i++) {
            if (aNamn.charAt(i) == ' ') {
                break;
            }
            System.out.println(aNamn.charAt(i));
        }
        
        scanner.close();
    }
    
    static String formatDatum(String datum) {
        if (datum.length() >= 8) {
            return datum.substring(0, 4) + "/" + datum.substring(4, 6) + "/" + datum.substring(6, 8);
        }
        return datum;
    }
}