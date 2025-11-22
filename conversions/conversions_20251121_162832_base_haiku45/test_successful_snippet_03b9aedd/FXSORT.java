import java.io.*;
import java.util.*;

public class FXSORT {
    
    static class FXRecord {
        String name;
        String price;
        
        FXRecord(String name, String price) {
            this.name = name;
            this.price = price;
        }
        
        @Override
        public String toString() {
            return String.format("%-50s%-15s%17s", name, price, "");
        }
    }
    
    public static void main(String[] args) {
        try {
            processRecords();
            closeStop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void processRecords() throws IOException {
        System.out.println("MERGING FILES");
        
        List<FXRecord> mergedRecords = new ArrayList<>();
        
        mergedRecords.addAll(readFile("FXBOSS"));
        mergedRecords.addAll(readFile("FXMXR"));
        mergedRecords.addAll(readFile("FXJHS"));
        
        Collections.sort(mergedRecords, (a, b) -> a.name.compareTo(b.name));
        
        writeFile("FXLSTMRG", mergedRecords);
        
        System.out.println("SORTING RECORDS");
        
        List<FXRecord> sortedRecords = new ArrayList<>(mergedRecords);
        Collections.sort(sortedRecords, (a, b) -> {
            String priceA = a.price.replaceAll("[^0-9.]", "");
            String priceB = b.price.replaceAll("[^0-9.]", "");
            double numA = Double.parseDouble(priceA.isEmpty() ? "0" : priceA);
            double numB = Double.parseDouble(priceB.isEmpty() ? "0" : priceB);
            return Double.compare(numA, numB);
        });
        
        writeFile("FXSORTED", sortedRecords);
    }
    
    static List<FXRecord> readFile(String filename) throws IOException {
        List<FXRecord> records = new ArrayList<>();
        File file = new File(filename);
        
        if (!file.exists()) {
            return records;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 65) {
                    String name = line.substring(0, 50).trim();
                    String price = line.substring(50, 65).trim();
                    records.add(new FXRecord(name, price));
                }
            }
        }
        
        return records;
    }
    
    static void writeFile(String filename, List<FXRecord> records) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (FXRecord record : records) {
                writer.write(record.toString());
                writer.newLine();
            }
        }
    }
    
    static void closeStop() {
        System.out.println("NOW I'M STOPPING");
    }
}