import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202103_2 {
    private static final int N = 12;
    private static final int M = 1000;
    
    private List<String> wsArray = new ArrayList<>();
    private List<Integer> wsOxyFlag = new ArrayList<>();
    private List<Integer> wsCo2Flag = new ArrayList<>();
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
    
    public static void main(String[] args) throws IOException {
        AOC202103_2 program = new AOC202103_2();
        program.run();
    }
    
    private void run() throws IOException {
        read();
        filterNumbers();
        computeDecimals();
        long result = wsOxyDec * wsCo2Dec;
        System.out.println(result);
    }
    
    private void read() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("d03.input"));
        for (String line : lines) {
            processRecord(line);
        }
    }
    
    private void processRecord(String inputRecord) {
        wsArray.add(inputRecord);
        wsOxyFlag.add(1);
        wsCo2Flag.add(1);
        
        for (int i = 0; i < N; i++) {
            if (inputRecord.charAt(i) == '1') {
                wsCountsOxy[i]++;
                wsCountsCo2[i]++;
            }
        }
    }
    
    private void filterNumbers() {
        wsOxyRows = wsArray.size();
        wsCo2Rows = wsArray.size();
        
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
        
        for (int j = 0; j < wsArray.size(); j++) {
            if (wsOxyFlag.get(j) == 1 && wsOxyRows > 1) {
                if (wsArray.get(j).charAt(i) == wsOxyBadBit) {
                    wsOxyFlag.set(j, 0);
                    wsOxyRows--;
                    for (int k = i; k < N; k++) {
                        if (wsArray.get(j).charAt(k) == '1') {
                            wsCountsOxy[k]--;
                        }
                    }
                }
            }
            
            if (wsCo2Flag.get(j) == 1 && wsCo2Rows > 1) {
                if (wsArray.get(j).charAt(i) == wsCo2BadBit) {
                    wsCo2Flag.set(j, 0);
                    wsCo2Rows--;
                    for (int k = i; k < N; k++) {
                        if (wsArray.get(j).charAt(k) == '1') {
                            wsCountsCo2[k]--;
                        }
                    }
                }
            }
            
            if (wsOxyFlag.get(j) == 1 && wsOxyRows == 1) {
                wsOxyIdx = j;
            }
            
            if (wsCo2Flag.get(j) == 1 && wsCo2Rows == 1) {
                wsCo2Idx = j;
            }
        }
    }
    
    private void computeDecimals() {
        for (int i = 0; i < N; i++) {
            wsOxyDec = wsOxyDec * 2;
            wsCo2Dec = wsCo2Dec * 2;
            
            if (wsArray.get(wsOxyIdx).charAt(i) == '1') {
                wsOxyDec = wsOxyDec + 1;
            }
            
            if (wsArray.get(wsCo2Idx).charAt(i) == '1') {
                wsCo2Dec = wsCo2Dec + 1;
            }
        }
    }
}