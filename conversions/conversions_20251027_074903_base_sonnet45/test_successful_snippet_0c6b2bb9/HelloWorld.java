import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HelloWorld {
    
    private static class AArbetstareor {
        String aDatum = "        ";
        String aNamn = "                                        ";
        int aAlder = 0;
    }
    
    private static class Utvardering {
        String rubrik = "";
        String resultat = "";
        
        String getDatumRed() {
            return rubrik.substring(8, 18);
        }
        
        void setDatumRed(String datum) {
            StringBuilder sb = new StringBuilder(rubrik);
            String formatted = datum.substring(0, 4) + "/" + datum.substring(4, 6) + "/" + datum.substring(6, 8);
            sb.replace(8, 18, formatted);
            rubrik = sb.toString();
        }
        
        String getNamn() {
            return rubrik.substring(22, 62);
        }
        
        void setNamn(String namn) {
            StringBuilder sb = new StringBuilder(rubrik);
            sb.replace(22, 62, String.format("%-40s", namn));
            rubrik = sb.toString();
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int x = 0;
        int y = 0;
        int z = 0;
        
        AArbetstareor a = new AArbetstareor();
        Utvardering utvardering = new Utvardering();
        
        utvardering.rubrik = "RAPPORT " + "          " + "    " + String.format("%-40s", "");
        utvardering.resultat = String.format("%-30s%-30s", "", "");
        
        x = 10;
        x = x + 1;
        x = x - 1;
        x = x * 6;
        y = x / 3;
        z = x % 3;
        System.out.println("X=" + String.format("%02d", x) + " Y=" + String.format("%02d", y) + " Z=" + String.format("%02d", z));
        
        x = 10;
        y = (((x + 1) - 1) * 6) / 3;
        System.out.println("Y=" + String.format("%02d", y));
        
        LocalDate today = LocalDate.now();
        a.aDatum = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("Dagens datum: " + a.aDatum);
        
        a.aNamn = scanner.nextLine();
        if (a.aNamn.length() < 40) {
            a.aNamn = String.format("%-40s", a.aNamn);
        } else if (a.aNamn.length() > 40) {
            a.aNamn = a.aNamn.substring(0, 40);
        }
        
        a.aAlder = scanner.nextInt();
        
        utvardering.resultat = String.format("%-30s%-30s", "Du får INTE gå på bolaget.", "");
        
        if (a.aAlder >= 18) {
            utvardering.resultat = String.format("%-30s%-30s", "Du får köra bil.", utvardering.resultat.substring(30));
            if (a.aAlder >= 20) {
                utvardering.resultat = String.format("%-30s%-30s", utvardering.resultat.substring(0, 30), "Du får gå på bolaget");
            }
        } else {
            utvardering.resultat = String.format("%-30s%-30s", "Du får INTE köra bil.", utvardering.resultat.substring(30));
        }
        
        if (a.aAlder >= 0 && a.aAlder <= 17) {
            utvardering.resultat = String.format("%-30s%-30s", "Du får INTE köra bil.", "Du får INTE gå på bolaget.");
        } else if (a.aAlder == 18 || a.aAlder == 19) {
            utvardering.resultat = String.format("%-30s%-30s", "Du får köra bil.", "Du får INTE gå på bolaget.");
        } else {
            utvardering.resultat = String.format("%-30s%-30s", "Du får köra bil.", "Du får gå på bolaget");
        }
        
        if (a.aNamn.trim().equals("Pink Panther")) {
            utvardering.resultat = String.format("%-60s", "Du är ju Rosa Pantern! Du står över lagen!");
        } else if (a.aAlder < 18) {
            utvardering.resultat = String.format("%-30s%-30s", "Du får INTE köra bil.", "Du får INTE gå på bolaget.");
        } else if (a.aAlder >= 18 && a.aAlder < 20) {
            utvardering.resultat = String.format("%-30s%-30s", "Du får köra bil.", "Du får INTE gå på bolaget.");
        } else {
            utvardering.resultat = String.format("%-30s%-30s", "Du får köra bil.", "Du får gå på bolaget");
        }
        
        utvardering.setDatumRed(a.aDatum);
        utvardering.setNamn(a.aNamn);
        System.out.println(utvardering.rubrik);
        System.out.println(utvardering.resultat);
        
        System.out.println(40);
        
        for (x = 1; x <= 40; x++) {
            if (a.aNamn.substring(x - 1).trim().isEmpty()) {
                break;
            }
            System.out.println(a.aNamn.substring(x - 1, x));
        }
        
        scanner.close();
    }
}