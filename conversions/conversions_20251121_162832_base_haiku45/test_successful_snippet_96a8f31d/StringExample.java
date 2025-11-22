import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringExample {
    
    static class Identite {
        String nom = "";
        String prenom = "";
    }
    
    static class TodayDate {
        String tYear = "";
        String tMonth = "";
        String tDay = "";
    }
    
    public static void main(String[] args) {
        Identite identite = new Identite();
        TodayDate todayDate = new TodayDate();
        
        double nonInteger = 3.14159;
        String piEdit = "";
        
        int signedInteger = 42;
        
        int beginString = 13;
        String entete = "";
        
        String structure = "n";
        boolean accepted = false;
        
        // Move values
        identite.nom = "Doe";
        identite.prenom = "John";
        
        // Set accepted to true
        accepted = true;
        
        if (accepted) {
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String dateStr = now.format(formatter);
            todayDate.tYear = dateStr.substring(0, 4);
            todayDate.tMonth = dateStr.substring(4, 6);
            todayDate.tDay = dateStr.substring(6, 8);
        } else {
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String dateStr = now.format(formatter);
            todayDate.tYear = dateStr.substring(0, 4);
            todayDate.tMonth = dateStr.substring(4, 6);
            todayDate.tDay = dateStr.substring(6, 8);
        }
        
        // String concatenation with pointer
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < beginString - 1; i++) {
            sb.append(" ");
        }
        
        String prenom = identite.prenom.trim();
        String nom = identite.nom.trim();
        
        sb.append(prenom);
        sb.append(" ");
        sb.append(nom);
        sb.append(" on ");
        sb.append(todayDate.tDay);
        sb.append("/");
        sb.append(todayDate.tMonth);
        sb.append("/");
        sb.append(todayDate.tYear);
        
        entete = sb.toString();
        
        System.out.println();
        System.out.println(entete);
        
        // String with non-integer value
        String nonIntegerStr = String.format("%08d", (int)(nonInteger * 100000));
        piEdit = "PI is : " + nonIntegerStr;
        System.out.println(piEdit);
        
        // String with signed integer value
        piEdit = "Answer to life is : " + signedInteger;
        System.out.println(piEdit);
    }
}