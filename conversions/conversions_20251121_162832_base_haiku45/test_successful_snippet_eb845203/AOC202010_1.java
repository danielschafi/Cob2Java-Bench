import java.io.*;
import java.util.*;

public class AOC202010_1 {
    private static final String INPUT_FILE = "d10.input";
    private static final int MAX_ARRAY_SIZE = 99;
    
    private int[] wsArray;
    private int wsArrLen;
    private int result;
    private int diff1;
    private int diff3;
    
    public AOC202010_1() {
        wsArray = new int[MAX_ARRAY_SIZE + 1];
        wsArrLen = 0;
        result = 0;
        diff1 = 0;
        diff3 = 0;
    }
    
    public void run() {
        readInput();
        Arrays.sort(wsArray, 0, wsArrLen);
        shiftArray();
        wsArray[wsArrLen - 1] = wsArray[wsArrLen - 2] + 3;
        useAdapters();
        result = diff1 * diff3;
        System.out.println(result);
    }
    
    private void readInput() {
        wsArray[0] = 0;
        int index = 1;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    wsArray[index] = Integer.parseInt(line.trim());
                    index++;
                }
            }
            wsArrLen = index;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void shiftArray() {
        for (int i = 0; i < wsArrLen - 1; i++) {
            wsArray[i] = wsArray[i + 1];
        }
    }
    
    private void useAdapters() {
        for (int i = 0; i < wsArrLen - 1; i++) {
            int diff = wsArray[i + 1] - wsArray[i];
            if (diff == 1) {
                diff1++;
            }
            if (diff == 3) {
                diff3++;
            }
        }
    }
    
    public static void main(String[] args) {
        AOC202010_1 program = new AOC202010_1();
        program.run();
    }
}