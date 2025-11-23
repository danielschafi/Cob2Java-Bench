import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2021032 {
    private static final int MAX_ROWS = 1000;
    private static final int MAX_COLS = 12;
    
    private static String[] wsArray = new String[MAX_ROWS];
    private static int[] wsOxyFlag = new int[MAX_ROWS];
    private static int[] wsCo2Flag = new int[MAX_ROWS];
    private static int[] wsCountsOxy = new int[MAX_COLS];
    private static int[] wsCountsCo2 = new int[MAX_COLS];
    private static int wsOxyRows = MAX_ROWS;
    private static int wsCo2Rows = MAX_ROWS;
    private static int wsOxyIdx;
    private static int wsCo2Idx;
    private static int wsOxyDec = 0;
    private static int wsCo2Dec = 0;
    private static int j = 1;
    private static int m = 1000;
    private static int n = 12;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d03.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (j <= MAX_ROWS) {
                    wsArray[j - 1] = line;
                    wsOxyFlag[j - 1] = 1;
                    wsCo2Flag[j - 1] = 1;
                    
                    for (int i = 0; i < n; i++) {
                        if (line.charAt(i) == '1') {
                            wsCountsOxy[i]++;
                            wsCountsCo2[i]++;
                        }
                    }
                    j++;
                }
            }
            
            filterNumbers();
            computeDecimals();
            
            long result = (long) wsOxyDec * wsCo2Dec;
            System.out.println(result);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void filterNumbers() {
        for (int i = 0; i < n; i++) {
            char oxyBadBit;
            char co2BadBit;
            
            if (wsCountsOxy[i] < wsOxyRows / 2.0) {
                oxyBadBit = '1';
            } else {
                oxyBadBit = '0';
            }
            
            if (wsCountsCo2[i] >= wsCo2Rows / 2.0) {
                co2BadBit = '1';
            } else {
                co2BadBit = '0';
            }
            
            for (int j = 0; j < m; j++) {
                if (wsOxyFlag[j] == 1 && wsOxyRows > 1) {
                    if (wsArray[j].charAt(i) == oxyBadBit) {
                        wsOxyFlag[j] = 0;
                        wsOxyRows--;
                        
                        for (int k = i; k < n; k++) {
                            if (wsArray[j].charAt(k) == '1') {
                                wsCountsOxy[k]--;
                            }
                        }
                    }
                }
                
                if (wsCo2Flag[j] == 1 && wsCo2Rows > 1) {
                    if (wsArray[j].charAt(i) == co2BadBit) {
                        wsCo2Flag[j] = 0;
                        wsCo2Rows--;
                        
                        for (int k = i; k < n; k++) {
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
    }
    
    private static void computeDecimals() {
        for (int i = 0; i < n; i++) {
            wsOxyDec = wsOxyDec * 2;
            wsCo2Dec = wsCo2Dec * 2;
            
            if (wsArray[wsOxyIdx].charAt(i) == '1') {
                wsOxyDec++;
            }
            
            if (wsArray[wsCo2Idx].charAt(i) == '1') {
                wsCo2Dec++;
            }
        }
    }
}