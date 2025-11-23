import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BATCH_1 {
    private static final String TRANSACTIONS_FILE = "transactions.txt";
    private static final String VISIT_FILE = "data.txt";
    
    public static void main(String[] args) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("! BATCH 1   :                                    !");
        System.out.println("! TRAITEMENT BACH STATISTIQUES VISITES           !");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        
        traitement1();
    }
    
    private static void traitement1() {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("PARAGRAPHE TRAITEMENT 1");
        System.out.println("CURRENT DATE " + currentDate.format(formatter));
        
        try (BufferedReader visitReader = new BufferedReader(new FileReader(VISIT_FILE));
             BufferedWriter transactionsWriter = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE))) {
            
            String line;
            boolean endOfFile = false;
            
            // Read first record from visit file
            line = visitReader.readLine();
            if (line == null) {
                endOfFile = true;
            }
            
            if (!endOfFile) {
                // Write the first record
                transactionsWriter.write(line);
                transactionsWriter.newLine();
                
                // Process remaining records
                while ((line = visitReader.readLine()) != null) {
                    transactionsWriter.write(line);
                    transactionsWriter.newLine();
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}