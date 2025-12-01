import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202010_1 {
    private static final String INPUT_FILE = "d10.input";
    private static final int MAX_ARRAY_SIZE = 99;
    
    private int[] wsArray;
    private int wsArrLen;
    private int fileStatus;
    private int result;
    private int i;
    private int diff1;
    private int diff3;
    private int diff;
    
    public AOC202010_1() {
        wsArray = new int[MAX_ARRAY_SIZE + 1];
        wsArrLen = 95;
        fileStatus = 0;
        result = 0;
        i = 1;
        diff1 = 0;
        diff3 = 0;
        diff = 0;
    }
    
    public static void main(String[] args) {
        AOC202010_1 program = new AOC202010_1();
        program.run();
    }
    
    private void run() {
        readInputFile();
        sortArray();
        shiftArray();
        wsArray[wsArrLen] = wsArray[wsArrLen - 1] + 3;
        useAdapters();
        result = diff1 * diff3;
        System.out.println(result);
    }
    
    private void readInputFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(INPUT_FILE));
            wsArray[0] = 0;
            i = 1;
            
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    wsArray[i] = Integer.parseInt(line.trim());
                    i++;
                    result++;
                }
            }
            wsArrLen = result;
        } catch (IOException e) {
            e.printStackTrace();
            fileStatus = 1;
        }
    }
    
    private void sortArray() {
        int[] temp = new int[wsArrLen + 1];
        for (int j = 0; j <= wsArrLen; j++) {
            temp[j] = wsArray[j];
        }
        Arrays.sort(temp, 0, wsArrLen + 1);
        for (int j = 0; j <= wsArrLen; j++) {
            wsArray[j] = temp[j];
        }
    }
    
    private void shiftArray() {
        for (i = 1; i <= wsArrLen - 1; i++) {
            wsArray[i] = wsArray[i + 1];
        }
    }
    
    private void useAdapters() {
        diff1 = 0;
        diff3 = 0;
        
        for (i = 1; i <= wsArrLen - 1; i++) {
            diff = wsArray[i + 1] - wsArray[i];
            if (diff == 1) {
                diff1++;
            }
            if (diff == 3) {
                diff3++;
            }
        }
    }
}