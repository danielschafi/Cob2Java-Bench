import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BATCH_1 {
    private static final String VISIT_FILE_PATH = "data.txt";
    private static final String TRANSACTIONS_PATH = "transactions.txt";

    public static void main(String[] args) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("! BATCH 1   :                                    !");
        System.out.println("! TRAITEMENT BACH STATISTIQUES VISITES           !");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        traitementBatch();
    }

    private static void traitementBatch() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssSS");

        System.out.println("PARAGRAPHE TRAITEMENT 1");
        System.out.println("CURRENT DATE " + now.format(dateFormatter) + " " + now.format(timeFormatter));

        try (BufferedReader reader = new BufferedReader(new FileReader(VISIT_FILE_PATH));
             BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTIONS_PATH))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}