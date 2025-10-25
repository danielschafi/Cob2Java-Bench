import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class StringExample {
    public static void main(String[] args) {
        String nom = "Doe";
        String prenom = "John";
        boolean accepted = true;
        String tYear = "";
        String tMonth = "";
        String tDay = "";
        double nonInteger = 3.14159;
        String piEdit = new String(new char[50]);
        int signedInteger = 42;
        int beginString = 13;
        String entete = new String(new char[132]);

        if (accepted) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String currentDate = dateFormat.format(new Date());
            tYear = currentDate.substring(0, 4);
            tMonth = currentDate.substring(4, 6);
            tDay = currentDate.substring(6, 8);
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String currentDate = dateFormat.format(new Date());
            tYear = currentDate.substring(0, 4);
            tMonth = currentDate.substring(4, 6);
            tDay = currentDate.substring(6, 8);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(prenom).append(" ").append(nom).append(" on ").append(tDay).append("/").append(tMonth).append("/").append(tYear);
        if (sb.length() > entete.length()) {
            System.out.println("Error in string operation");
        } else {
            entete = String.format("%" + beginString + "s", sb.toString());
        }

        System.out.println();

        System.out.println(entete);

        sb = new StringBuilder();
        sb.append("PI is : ").append(String.format("%09.0f", nonInteger));
        if (sb.length() > piEdit.length()) {
            System.out.println("Error in string operation");
        } else {
            piEdit = sb.toString();
        }

        System.out.println(piEdit);

        sb = new StringBuilder();
        sb.append("Answer to life is : ").append(signedInteger);
        if (sb.length() > piEdit.length()) {
            System.out.println("Error in string operation");
        } else {
            piEdit = sb.toString();
        }

        System.out.println(piEdit);
    }
}