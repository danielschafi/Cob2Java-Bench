import java.io.*;
import java.util.*;

public class AOC202016 {
    private int wsResult = 0;
    private int recLen = 0;
    private int fileStatus = 0;
    private int wsSection = 1;
    
    private int[] wsMin1 = new int[20];
    private int[] wsMax1 = new int[20];
    private int[] wsMin2 = new int[20];
    private int[] wsMax2 = new int[20];
    
    private String wsTmp1 = "";
    private String wsTmp2 = "";
    private String wsTmp3 = "";
    private String wsTmp4 = "";
    private String wsTmp5 = "";
    private String wsTmp6 = "";
    
    private int[] wsRow = new int[20];
    private int wsVal = 0;
    private int wsValCorrect = 0;
    
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int n = 0;
    private int stringPtr = 1;
    
    public static void main(String[] args) {
        AOC202016 program = new AOC202016();
        program.run();
    }
    
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("d16.input"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                processRecord(line);
            }
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println(wsResult);
    }
    
    private void processRecord(String inputRecord) {
        if (recLen == 0) {
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
    
    private void processRecordType1(String inputRecord) {
        String[] parts = inputRecord.split("-|:|\\s+or\\s+");
        
        n++;
        wsMin1[n - 1] = Integer.parseInt(parts[1].trim());
        wsMax1[n - 1] = Integer.parseInt(parts[2].trim());
        wsMin2[n - 1] = Integer.parseInt(parts[3].trim());
        wsMax2[n - 1] = Integer.parseInt(parts[4].trim());
    }
    
    private void processRecordType2(String inputRecord) {
    }
    
    private void processRecordType3(String inputRecord) {
        i++;
        if (i == 1) {
            return;
        }
        
        String[] values = inputRecord.split(",");
        for (j = 0; j < n; j++) {
            wsRow[j] = Integer.parseInt(values[j].trim());
        }
        
        for (j = 0; j < n; j++) {
            wsVal = wsRow[j];
            checkVal();
        }
    }
    
    private void checkVal() {
        wsValCorrect = 0;
        for (k = 0; k < n; k++) {
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