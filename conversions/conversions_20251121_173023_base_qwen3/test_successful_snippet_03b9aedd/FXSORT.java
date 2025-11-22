import java.io.*;
import java.util.*;

public class FXSORT {
    static class FXRecord implements Comparable<FXRecord> {
        String name;
        double price;
        
        public FXRecord(String name, double price) {
            this.name = name;
            this.price = price;
        }
        
        @Override
        public int compareTo(FXRecord other) {
            return Double.compare(this.price, other.price);
        }
    }
    
    static class FXRecordComparator implements Comparator<FXRecord> {
        @Override
        public int compare(FXRecord r1, FXRecord r2) {
            return r1.name.compareTo(r2.name);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("MERGING FILES");
        
        List<FXRecord> workRecords = new ArrayList<>();
        List<FXRecord> bossRecords = new ArrayList<>();
        List<FXRecord> mxrRecords = new ArrayList<>();
        List<FXRecord> jhsRecords = new ArrayList<>();
        List<FXRecord> mergeRecords = new ArrayList<>();
        
        try {
            readRecords("FXBOSS", bossRecords);
            readRecords("FXMXR", mxrRecords);
            readRecords("FXJHS", jhsRecords);
            
            // Merge files
            workRecords.addAll(bossRecords);
            workRecords.addAll(mxrRecords);
            workRecords.addAll(jhsRecords);
            
            Collections.sort(workRecords, new FXRecordComparator());
            
            writeRecords("WRK", workRecords);
            
            // Read merged records
            readRecords("WRK", mergeRecords);
            
            // Sort by price
            Collections.sort(mergeRecords);
            
            writeRecords("FXSORTED", mergeRecords);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("NOW I'M STOPPING");
    }
    
    private static void readRecords(String filename, List<FXRecord> records) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 50) {
                    String name = line.substring(0, 50).trim();
                    double price = 0.0;
                    if (line.length() > 50) {
                        try {
                            String priceStr = line.substring(50, Math.min(65, line.length())).trim();
                            price = Double.parseDouble(priceStr);
                        } catch (NumberFormatException e) {
                            // Handle invalid price format
                        }
                    }
                    records.add(new FXRecord(name, price));
                }
            }
        }
    }
    
    private static void writeRecords(String filename, List<FXRecord> records) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (FXRecord record : records) {
                String name = String.format("%-50s", record.name);
                String price = String.format("%15.2f", record.price);
                writer.println(name + price + "                 ");
            }
        }
    }
}