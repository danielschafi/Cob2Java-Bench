import java.io.*;
import java.util.*;

public class FXSORT {
    
    static class Record implements Comparable<Record> {
        String name;
        double price;
        
        public Record(String name, double price) {
            this.name = name;
            this.price = price;
        }
        
        @Override
        public int compareTo(Record other) {
            return Double.compare(this.price, other.price);
        }
    }
    
    static class FileHandler {
        private List<Record> records;
        private PrintWriter writer;
        private BufferedReader reader;
        private String filename;
        
        public FileHandler(String filename) {
            this.filename = filename;
            this.records = new ArrayList<>();
        }
        
        public void openForReading() throws IOException {
            reader = new BufferedReader(new FileReader(filename));
        }
        
        public void openForWriting() throws IOException {
            writer = new PrintWriter(new FileWriter(filename));
        }
        
        public void close() throws IOException {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
        }
        
        public boolean readRecord(Record record) throws IOException {
            String line = reader.readLine();
            if (line == null) return false;
            
            record.name = line.substring(0, Math.min(50, line.length())).trim();
            String priceStr = line.substring(50, Math.min(67, line.length())).trim();
            try {
                record.price = Double.parseDouble(priceStr.replace(",", "").replace("$", ""));
            } catch (NumberFormatException e) {
                record.price = 0.0;
            }
            return true;
        }
        
        public void writeRecord(Record record) throws IOException {
            String formattedPrice = String.format("%1$,.2f", record.price).replace(",", " ");
            String outputLine = String.format("%-50s%-17s", record.name, formattedPrice);
            writer.println(outputLine);
        }
        
        public void addRecord(Record record) {
            records.add(record);
        }
        
        public List<Record> getRecords() {
            return records;
        }
        
        public void sort() {
            Collections.sort(records);
        }
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("MERGING FILES");
            
            // Create file handlers for input files
            FileHandler bossFile = new FileHandler("FXBOSS");
            FileHandler mxrFile = new FileHandler("FXMXR");
            FileHandler jhsFile = new FileHandler("FXJHS");
            FileHandler workFile = new FileHandler("WRK");
            FileHandler mergeFile = new FileHandler("FXLSTMRG");
            FileHandler sortedFile = new FileHandler("FXSORTED");
            
            // Read all input files and merge them into workFile
            bossFile.openForReading();
            mxrFile.openForReading();
            jhsFile.openForReading();
            
            Record tempRecord = new Record("", 0.0);
            
            // Read from boss file
            while (bossFile.readRecord(tempRecord)) {
                workFile.addRecord(new Record(tempRecord.name, tempRecord.price));
            }
            
            // Read from mxr file
            while (mxrFile.readRecord(tempRecord)) {
                workFile.addRecord(new Record(tempRecord.name, tempRecord.price));
            }
            
            // Read from jhs file
            while (jhsFile.readRecord(tempRecord)) {
                workFile.addRecord(new Record(tempRecord.name, tempRecord.price));
            }
            
            bossFile.close();
            mxrFile.close();
            jhsFile.close();
            
            // Sort workFile by name (not implemented as no direct name sorting was specified)
            // For now we'll just write the records as they were read
            
            // Write merged records to mergeFile
            mergeFile.openForWriting();
            for (Record r : workFile.getRecords()) {
                mergeFile.writeRecord(r);
            }
            mergeFile.close();
            
            System.out.println("SORTING RECORDS");
            
            // Read from merge file and sort by price
            mergeFile.openForReading();
            List<Record> sortedRecords = new ArrayList<>();
            
            while (mergeFile.readRecord(tempRecord)) {
                sortedRecords.add(new Record(tempRecord.name, tempRecord.price));
            }
            
            mergeFile.close();
            
            // Sort by price
            Collections.sort(sortedRecords);
            
            // Write sorted records to sorted file
            sortedFile.openForWriting();
            for (Record r : sortedRecords) {
                sortedFile.writeRecord(r);
            }
            sortedFile.close();
            
            System.out.println("NOW I'M STOPPING");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}