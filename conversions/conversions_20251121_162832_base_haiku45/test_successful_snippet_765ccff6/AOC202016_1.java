import java.io.*;
import java.util.*;

public class AOC202016_1 {
    private static int wsResult = 0;
    private static int wsSection = 1;
    
    private static int[] wsMin1 = new int[20];
    private static int[] wsMax1 = new int[20];
    private static int[] wsMin2 = new int[20];
    private static int[] wsMax2 = new int[20];
    private static int[] wsRow = new int[20];
    private static int wsVal = 0;
    private static int wsValCorrect = 0;
    private static int n = 0;
    private static int i = 0;
    
    public static void main(String[] args) {
        try {
            processFile("d16.input");
            System.out.println(wsResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void processFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        
        while ((line = reader.readLine()) != null) {
            processRecord(line);
        }
        
        reader.close();
    }
    
    private static void processRecord(String inputRecord) {
        if (inputRecord.length() == 0) {
            wsSection++;
        } else {
            if (wsSection == 1) {
                processRecordType1(inputRecord);
            } else if (wsSection == 2) {
                processRecordType2(inputRecord);
            } else {
                processRecordType3(inputRecord);
            }
        }
    }
    
    private static void processRecordType1(String inputRecord) {
        String[] parts = inputRecord.split("-|:|\\s+or\\s+");
        
        n++;
        wsMin1[n - 1] = Integer.parseInt(parts[1].trim());
        wsMax1[n - 1] = Integer.parseInt(parts[2].trim());
        wsMin2[n - 1] = Integer.parseInt(parts[3].trim());
        wsMax2[n - 1] = Integer.parseInt(parts[4].trim());
    }
    
    private static void processRecordType2(String inputRecord) {
    }
    
    private static void processRecordType3(String inputRecord) {
        i++;
        if (i == 1) {
            return;
        }
        
        String[] values = inputRecord.split(",");
        for (int j = 0; j < values.length && j < n; j++) {
            wsRow[j] = Integer.parseInt(values[j].trim());
        }
        
        for (int j = 0; j < n; j++) {
            wsVal = wsRow[j];
            checkVal();
        }
    }
    
    private static void checkVal() {
        wsValCorrect = 0;
        for (int k = 0; k < n; k++) {
            if ((wsVal >= wsMin1[k] && wsVal <= wsMax1[k]) ||
                (wsVal >= wsMin2[k] && wsVal <= wsMax2[k])) {
                wsValCorrect = 1;
            }
        }
        if (wsValCorrect == 0) {
            wsResult += wsVal;
        }
    }
}