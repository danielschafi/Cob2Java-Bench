import java.io.*;
import java.util.*;
import java.math.BigDecimal;

public class FXSORT {
    
    static class FXRecord {
        String name;
        BigDecimal price;
        
        FXRecord(String name, BigDecimal price) {
            this.name = name;
            this.price = price;
        }
        
        @Override
        public String toString() {
            return String.format("%-50s %12.2f", name, price);
        }
    }
    
    static List<FXRecord> readFile(String filename) throws IOException {
        List<FXRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 67) {
                    String name = line.substring(0, 50).trim();
                    String priceStr = line.substring(50, 67).trim();
                    try {
                        BigDecimal price = new BigDecimal(priceStr.replaceAll("[^0-9.]", ""));
                        records.add(new FXRecord(name, price));
                    } catch (NumberFormatException e) {
                    }
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
    
    static List<FXRecord> mergeFiles(List<String> inputFiles) throws IOException {
        List<FXRecord> merged = new ArrayList<>();
        for (String file : inputFiles) {
            merged.addAll(readFile(file));
        }
        merged.sort((a, b) -> a.name.compareTo(b.name));
        return merged;
    }
    
    static List<FXRecord> sortByPrice(List<FXRecord> records) {
        List<FXRecord> sorted = new ArrayList<>(records);
        sorted.sort((a, b) -> a.price.compareTo(b.price));
        return sorted;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("MERGING FILES");
            
            List<String> inputFiles = Arrays.asList("FXBOSS", "FXMXR", "FXJHS");
            List<FXRecord> merged = mergeFiles(inputFiles);
            writeFile("FXLSTMRG", merged);
            
            System.out.println("SORTING RECORDS");
            List<FXRecord> sorted = sortByPrice(merged);
            writeFile("FXSORTED", sorted);
            
            System.out.println("NOW I'M STOPPING");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}