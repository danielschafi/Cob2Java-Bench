import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.text.DecimalFormat;

public class FXSORT {
    
    private static final int RECORD_LENGTH = 81;
    private static final int NAME_LENGTH = 50;
    private static final int PRICE_LENGTH = 14;
    private static final int FILLER_LENGTH = 17;
    
    static class FXRecord {
        String name;
        String priceFormatted;
        double priceValue;
        String filler;
        
        FXRecord(String name, String priceFormatted, String filler) {
            this.name = name;
            this.priceFormatted = priceFormatted;
            this.filler = filler;
            this.priceValue = parsePrice(priceFormatted);
        }
        
        private double parsePrice(String priceStr) {
            String cleaned = priceStr.replace("$", "").replace(",", "").trim();
            try {
                return Double.parseDouble(cleaned);
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
        
        String toRecordString() {
            return String.format("%-50s%-14s%-17s", name, priceFormatted, filler);
        }
    }
    
    public static void main(String[] args) {
        try {
            processRecords();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static void processRecords() throws IOException {
        System.out.println("MERGING FILES");
        
        String fxboss = System.getenv("FXBOSS");
        String fxmxr = System.getenv("FXMXR");
        String fxjhs = System.getenv("FXJHS");
        String fxlstmrg = System.getenv("FXLSTMRG");
        String fxsorted = System.getenv("FXSORTED");
        
        if (fxboss == null) fxboss = "FXBOSS";
        if (fxmxr == null) fxmxr = "FXMXR";
        if (fxjhs == null) fxjhs = "FXJHS";
        if (fxlstmrg == null) fxlstmrg = "FXLSTMRG";
        if (fxsorted == null) fxsorted = "FXSORTED";
        
        List<FXRecord> mergedRecords = new ArrayList<>();
        
        mergedRecords.addAll(readRecords(fxboss));
        mergedRecords.addAll(readRecords(fxmxr));
        mergedRecords.addAll(readRecords(fxjhs));
        
        mergedRecords.sort(Comparator.comparing(r -> r.name));
        
        writeRecords(fxlstmrg, mergedRecords);
        
        System.out.println("SORTING RECORDS");
        
        List<FXRecord> sortedRecords = readRecords(fxlstmrg);
        
        sortedRecords.sort(Comparator.comparingDouble(r -> r.priceValue));
        
        writeRecords(fxsorted, sortedRecords);
        
        closeStop();
    }
    
    private static List<FXRecord> readRecords(String filename) throws IOException {
        List<FXRecord> records = new ArrayList<>();
        File file = new File(filename);
        
        if (!file.exists()) {
            return records;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= NAME_LENGTH + PRICE_LENGTH) {
                    String name = line.substring(0, NAME_LENGTH);
                    String price = line.substring(NAME_LENGTH, NAME_LENGTH + PRICE_LENGTH);
                    String filler = "";
                    if (line.length() >= NAME_LENGTH + PRICE_LENGTH + FILLER_LENGTH) {
                        filler = line.substring(NAME_LENGTH + PRICE_LENGTH, NAME_LENGTH + PRICE_LENGTH + FILLER_LENGTH);
                    } else if (line.length() > NAME_LENGTH + PRICE_LENGTH) {
                        filler = line.substring(NAME_LENGTH + PRICE_LENGTH);
                    }
                    records.add(new FXRecord(name, price, filler));
                }
            }
        }
        
        return records;
    }
    
    private static void writeRecords(String filename, List<FXRecord> records) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (FXRecord record : records) {
                writer.write(record.toRecordString());
                writer.newLine();
            }
        }
    }
    
    private static void closeStop() {
        System.out.println("NOW I'M STOPPING");
        System.exit(0);
    }
}