import java.io.*;
import java.util.*;

public class RunStddev {
    static class TableData {
        int size;
        double[] table;
        
        TableData() {
            this.size = 0;
            this.table = new double[100];
        }
    }
    
    public static void main(String[] args) {
        TableData tableData = new TableData();
        double[] stddev = new double[1];
        
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tableData.size++;
                int inputValue = Integer.parseInt(line.trim());
                tableData.table[tableData.size - 1] = inputValue;
                
                stddev[0] = calculateStddev(tableData);
                System.out.println("inp=" + inputValue + " stddev=" + String.format("%.4f", stddev[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static double calculateStddev(TableData tableData) {
        double sum = 0;
        
        for (int i = 0; i < tableData.size; i++) {
            sum += tableData.table[i];
        }
        
        double avg = sum / tableData.size;
        
        double sumsq = 0;
        for (int i = 0; i < tableData.size; i++) {
            double diff = tableData.table[i] - avg;
            sumsq += diff * diff;
        }
        
        double variance = sumsq / tableData.size;
        double stddev = Math.sqrt(variance);
        
        return stddev;
    }
}