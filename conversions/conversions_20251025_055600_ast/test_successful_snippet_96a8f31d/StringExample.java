import java.text.SimpleDateFormat;
import java.util.Date;

public class StringExample {
    public static void main(String[] args) {
        String nom = "Doe";
        String prenom = "John";

        boolean accepted = true;

        String tYear = "";
        String tMonth = "";
        String tDay = "";

        if (accepted) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String currentDate = dateFormat.format(new Date());
            tYear = currentDate.substring(0, 4);
            tMonth = currentDate.substring(4, 6);
            tDay = currentDate.substring(6, 8);
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            tYear = dateFormat.format(new Date());
            dateFormat = new SimpleDateFormat("MM");
            tMonth = dateFormat.format(new Date());
            dateFormat = new SimpleDateFormat("dd");
            tDay = dateFormat.format(new Date());
        }

        String entete = String.format("%-25s %-25s on %s/%s/%s", prenom, nom, tDay, tMonth, tYear);
        System.out.println(entete);

        double nonInteger = 3.14159;
        String piEdit = String.format("PI is : %09.0f", nonInteger);
        System.out.println(piEdit);

        int signedInteger = 42;
        piEdit = String.format("Answer to life is : %02d", signedInteger);
        System.out.println(piEdit);
    }
}