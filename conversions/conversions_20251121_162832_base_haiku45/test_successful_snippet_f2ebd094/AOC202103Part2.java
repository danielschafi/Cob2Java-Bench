import java.io.*;
import java.util.*;

public class AOC202103Part2 {
    private static final int N = 12;
    private static final int M = 1000;
    
    private String[] wsArray = new String[M];
    private int[] wsOxyFlag = new int[M];
    private int[] wsCo2Flag = new int[M];
    private int[] wsCountsOxy = new int[N + 1];
    private int[] wsCountsCo2 = new int[N + 1];
    
    private long wsOxyDec = 0;
    private long wsCo2Dec = 0;
    private int wsOxyIdx = 0;
    private int wsCo2Idx = 0;
    private int wsOxyRows = 0;
    private int wsCo2Rows = 0;
    private char wsOxyBadBit = '0';
    private char wsCo2BadBit = '0';
    private int recordCount = 0;
    
    public static void main(String[] args) {
        AOC202103Part2 program = new AOC202103Part2();
        program.run();
    }
    
    private void run() {
        readInput();
        filterNumbers();
        computeDecimals();
        long result = wsOxyDec * wsCo2Dec;
        System.out.println(result);
    }
    
    private void readInput() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d03.input"))) {
            String line;
            int j = 0;
            while ((line = reader.readLine()) != null && j < M) {
                wsArray[j] = line;
                wsOxyFlag[j] = 1;
                wsCo2Flag[j] = 1;
                
                for (int i = 0; i < N; i++) {
                    if (line.charAt(i) == '1') {
                        wsCountsOxy[i]++;
                        wsCountsCo2[i]++;
                    }
                }
                j++;
                recordCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void filterNumbers() {
        wsOxyRows = recordCount;
        wsCo2Rows = recordCount;
        
        for (int i = 0; i < N; i++) {
            filterByBit(i);
        }
    }
    
    private void filterByBit(int i) {
        if (wsCountsOxy[i] < wsOxyRows / 2.0) {
            wsOxyBadBit = '1';
        } else {
            wsOxyBadBit = '0';
        }
        
        if (wsCountsCo2[i] >= wsCo2Rows / 2.0) {
            wsCo2BadBit = '1';
        } else {
            wsCo2BadBit = '0';
        }
        
        for (int j = 0; j < recordCount; j++) {
            if (wsOxyFlag[j] == 1 && wsOxyRows > 1) {
                if (wsArray[j].charAt(i) == wsOxyBadBit) {
                    wsOxyFlag[j] = 0;
                    wsOxyRows--;
                    for (int k = i; k < N; k++) {
                        if (wsArray[j].charAt(k) == '1') {
                            wsCountsOxy[k]--;
                        }
                    }
                }
            }
            
            if (wsCo2Flag[j] == 1 && wsCo2Rows > 1) {
                if (wsArray[j].charAt(i) == wsCo2BadBit) {
                    wsCo2Flag[j] = 0;
                    wsCo2Rows--;
                    for (int k = i; k < N; k++) {
                        if (wsArray[j].charAt(k) == '1') {
                            wsCountsCo2[k]--;
                        }
                    }
                }
            }
            
            if (wsOxyFlag[j] == 1 && wsOxyRows == 1) {
                wsOxyIdx = j;
            }
            
            if (wsCo2Flag[j] == 1 && wsCo2Rows == 1) {
                wsCo2Idx = j;
            }
        }
    }
    
    private void computeDecimals() {
        for (int i = 0; i < N; i++) {
            wsOxyDec = wsOxyDec * 2;
            wsCo2Dec = wsCo2Dec * 2;
            
            if (wsArray[wsOxyIdx].charAt(i) == '1') {
                wsOxyDec = wsOxyDec + 1;
            }
            
            if (wsArray[wsCo2Idx].charAt(i) == '1') {
                wsCo2Dec = wsCo2Dec + 1;
            }
        }
    }
}