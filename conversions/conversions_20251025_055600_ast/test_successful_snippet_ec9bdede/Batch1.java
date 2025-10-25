import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Batch1 {
    public static void main(String[] args) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("! BATCH 1   :                                    !");
        System.out.println("! TRAITEMENT BACH STATISTIQUES VISITES           !");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        String inputFileName = "data.txt";
        String outputFileName = "transactions.txt";
        String inputRecord = new String(new char[50]);
        String outputRecord = new String(new char[50]);
        int endOfFile = 0;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String currentDate = dateFormat.format(new Date());

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            System.out.println("PARAGRAPHE TRAITEMENT 1");
            System.out.println("CURRENT DATE " + currentDate);

            if (reader.readLine() == null) {
                endOfFile = 1;
            }

            if (endOfFile == 1) {
                reader.close();
            }

            endOfFile = 0;

            while ((inputRecord = reader.readLine()) != null) {
                outputRecord = String.format("%-50s", inputRecord).substring(0, 50);
                writer.write(outputRecord);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}