import java.io.*;
import java.util.*;

public class AOC202014_1 {
    private static final String INPUT_FILE = "d14.input";
    private static final int MEMORY_SIZE = 65536;
    
    private String wsMask = "";
    private long wsAddr = 0;
    private long wsVal = 0;
    private long wsValDec = 0;
    private String wsValBin = "";
    private long[] wsMem = new long[MEMORY_SIZE];
    private long result = 0;
    private int wsD = 0;
    
    public static void main(String[] args) {
        AOC202014_1 program = new AOC202014_1();
        program.run();
    }
    
    private void run() {
        try {
            readInput();
            sumMemory();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
        String line;
        
        while ((line = reader.readLine()) != null) {
            processRecord(line);
        }
        
        reader.close();
    }
    
    private void processRecord(String record) {
        if (record.startsWith("mask")) {
            wsMask = record.substring(7, 43);
        } else {
            String[] parts = record.split(" = ");
            wsAddr = Long.parseLong(parts[0].substring(4, parts[0].length() - 1));
            wsVal = Long.parseLong(parts[1]);
            wsValDec = wsVal;
            
            decToBin();
            applyMask();
            binToDec();
            
            wsMem[(int)wsAddr] = wsValDec;
        }
    }
    
    private void applyMask() {
        StringBuilder sb = new StringBuilder(wsValBin);
        for (int i = 0; i < 36; i++) {
            if (wsMask.charAt(i) != 'X') {
                sb.setCharAt(i, wsMask.charAt(i));
            }
        }
        wsValBin = sb.toString();
    }
    
    private void decToBin() {
        StringBuilder sb = new StringBuilder();
        long value = wsValDec;
        
        for (int i = 0; i < 36; i++) {
            sb.append(value % 2);
            value = value / 2;
        }
        
        wsValBin = sb.reverse().toString();
    }
    
    private void binToDec() {
        wsValDec = 0;
        for (int i = 0; i < 36; i++) {
            wsValDec = wsValDec * 2;
            if (wsValBin.charAt(i) == '1') {
                wsValDec = wsValDec + 1;
            }
        }
    }
    
    private void sumMemory() {
        result = 0;
        for (int i = 0; i < MEMORY_SIZE; i++) {
            result += wsMem[i];
        }
    }
}