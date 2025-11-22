import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringExample {
    
    static class Identite {
        String nom;
        String prenom;
        
        Identite() {
            this.nom = padRight("", 25);
            this.prenom = padRight("", 25);
        }
    }
    
    static class TodayDate {
        String tYear;
        String tMonth;
        String tDay;
        
        TodayDate() {
            this.tYear = "    ";
            this.tMonth = "  ";
            this.tDay = "  ";
        }
    }
    
    private static String padRight(String s, int n) {
        if (s.length() >= n) {
            return s.substring(0, n);
        }
        return String.format("%-" + n + "s", s);
    }
    
    private static String padLeft(String s, int n) {
        if (s.length() >= n) {
            return s.substring(0, n);
        }
        return String.format("%" + n + "s", s);
    }
    
    public static void main(String[] args) {
        Identite identite = new Identite();
        TodayDate todayDate = new TodayDate();
        
        double nonInteger = 3.14159;
        String piEdit = padRight("", 50);
        
        int signedInteger = 42;
        
        int beginString = 13;
        String entete = padRight("", 132);
        
        boolean accepted = false;
        
        identite.nom = padRight("Doe", 25);
        identite.prenom = padRight("John", 25);
        
        accepted = true;
        
        if (accepted) {
            LocalDate now = LocalDate.now();
            todayDate.tYear = String.format("%04d", now.getYear());
            todayDate.tMonth = String.format("%02d", now.getMonthValue());
            todayDate.tDay = String.format("%02d", now.getDayOfMonth());
        } else {
            LocalDate now = LocalDate.now();
            todayDate.tYear = String.format("%04d", now.getYear());
            todayDate.tMonth = String.format("%02d", now.getMonthValue());
            todayDate.tDay = String.format("%02d", now.getDayOfMonth());
        }
        
        StringBuilder sb = new StringBuilder();
        int pointer = beginString - 1;
        
        String prenomTrimmed = identite.prenom.trim();
        sb.append(prenomTrimmed);
        sb.append(" ");
        String nomTrimmed = identite.nom.trim();
        sb.append(nomTrimmed);
        sb.append(" on ");
        sb.append(todayDate.tDay);
        sb.append("/");
        sb.append(todayDate.tMonth);
        sb.append("/");
        sb.append(todayDate.tYear);
        
        String result = sb.toString();
        
        if (pointer + result.length() > 132) {
            System.out.println("Error in string operation");
        } else {
            entete = padRight("", pointer) + result + padRight("", 132 - pointer - result.length());
            if (entete.length() > 132) {
                entete = entete.substring(0, 132);
            }
        }
        
        System.out.println();
        System.out.println(entete);
        
        sb = new StringBuilder();
        sb.append("PI is : ");
        
        int intPart = (int) nonInteger;
        int fracPart = (int) Math.round((nonInteger - intPart) * 100000);
        String nonIntegerStr = String.format("%03d%05d", Math.abs(intPart), Math.abs(fracPart));
        sb.append(nonIntegerStr);
        
        piEdit = padRight(sb.toString(), 50);
        
        System.out.println(piEdit);
        
        sb = new StringBuilder();
        sb.append("Answer to life is : ");
        sb.append(signedInteger);
        
        piEdit = padRight(sb.toString(), 50);
        
        System.out.println(piEdit);
    }
}