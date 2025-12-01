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

    static Identite identite = new Identite();
    static TodayDate todayDate = new TodayDate();
    static double nonInteger = 3.14159;
    static String piEdit = "";
    static int signedInteger = 42;
    static int beginString = 13;
    static String entete = "";
    static String structure = "n";
    static boolean accepted = false;

    public static void main(String[] args) {
        identite.nom = "Doe";
        identite.prenom = "John";

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

        entete = "";
        int pointer = beginString - 1;

        String prenomPart = identite.prenom.trim();
        String nomPart = identite.nom.trim();

        StringBuilder sb = new StringBuilder(132);
        for (int i = 0; i < 132; i++) {
            sb.append(" ");
        }
        entete = sb.toString();

        String stringContent = prenomPart + " " + nomPart + " on " + todayDate.tDay + "/" + todayDate.tMonth + "/" + todayDate.tYear;

        if (pointer + stringContent.length() <= 132) {
            char[] enteteArray = entete.toCharArray();
            for (int i = 0; i < stringContent.length() && pointer + i < 132; i++) {
                enteteArray[pointer + i] = stringContent.charAt(i);
            }
            entete = new String(enteteArray);
        } else {
            System.out.println("Error in string operation");
        }

        System.out.println();
        System.out.println(entete);

        piEdit = "";
        String piContent = "PI is : " + String.format("%.5f", nonInteger);
        if (piContent.length() <= 50) {
            piEdit = piContent;
        } else {
            System.out.println("Error in string operation");
        }

        System.out.println(piEdit);

        piEdit = "";
        String answerContent = "Answer to life is : " + signedInteger;
        if (answerContent.length() <= 50) {
            piEdit = answerContent;
        } else {
            System.out.println("Error in string operation");
        }

        System.out.println(piEdit);
    }
}