import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BATCH_1 {
    public static void main(String[] args) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("! BATCH 1   :                                    !");
        System.out.println("! TRAITEMENT BACH STATISTIQUES VISITES           !");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        String inputFileName = "data.txt";
        String outputFileName = "transactions.txt";
        BufferedReader reader = null;
        BufferedWriter writer = null;
        int endOfFile = 0;

        try {
            reader = new BufferedReader(new FileReader(inputFileName));
            writer = new BufferedWriter(new FileWriter(outputFileName));

            System.out.println("PARAGRAPHE TRAITEMENT 1");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
            String currentDate = dateFormat.format(new Date());
            System.out.println("CURRENT DATE " + currentDate);

            String inputRecord = reader.readLine();
            if (inputRecord == null) {
                endOfFile = 1;
            }

            if (endOfFile == 1) {
                reader.close();
            }

            endOfFile = 0;

            while (endOfFile == 0) {
                writer.write(inputRecord);
                writer.newLine();
                inputRecord = reader.readLine();
                if (inputRecord == null) {
                    endOfFile = 1;
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}