import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringExample {
    private static String nom = "";
    private static String prenom = "";
    private static String tYear = "";
    private static String tMonth = "";
    private static String tDay = "";
    private static double nonInteger = 3.14159;
    private static String piEdit = "";
    private static int signedInteger = 42;
    private static int beginString = 13;
    private static String entete = "";
    private static boolean accepted = false;

    public static void main(String[] args) {
        nom = "Doe";
        prenom = "John";
        accepted = true;

        if (accepted) {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDate = currentDate.format(formatter);
            tYear = formattedDate.substring(0, 4);
            tMonth = formattedDate.substring(4, 6);
            tDay = formattedDate.substring(6, 8);
        } else {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDate = currentDate.format(formatter);
            tYear = formattedDate.substring(0, 4);
            tMonth = formattedDate.substring(4, 6);
            tDay = formattedDate.substring(6, 8);
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prenom).append(" ").append(nom).append(" on ").append(tDay).append("/").append(tMonth).append("/").append(tYear);
        entete = stringBuilder.toString();

        System.out.println();
        System.out.println(entete);

        piEdit = "PI is : " + String.format("%09d", (int)(nonInteger * 100000));
        System.out.println(piEdit);

        piEdit = "Answer to life is : " + String.format("%03d", signedInteger);
        System.out.println(piEdit);
    }
}