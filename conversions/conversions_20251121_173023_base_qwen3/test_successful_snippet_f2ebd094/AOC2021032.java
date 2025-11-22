import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2021032 {
    private static final int N = 12;
    private static final int M = 1000;
    
    private static String[] wsArray = new String[M];
    private static int[] wsOxyFlag = new int[M];
    private static int[] wsCo2Flag = new int[M];
    private static int[] wsCountsOxy = new int[N];
    private static int[] wsCountsCo2 = new int[N];
    private static int wsOxyRows = M;
    private static int wsCo2Rows = M;
    private static int wsOxyIdx = 0;
    private static int wsCo2Idx = 0;
    
    public static void main(String[] args) {
        try {
            readInput();
            filterNumbers();
            computeDecimals();
            long result = wsOxyDec * wsCo2Dec;
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d03.input"));
        String line;
        int j = 1;
        
        while ((line = reader.readLine()) != null) {
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
        }
        reader.close();
    }
    
    private static void filterNumbers() {
        for (int i = 0; i < N; i++) {
            filterByBit(i);
        }
    }
    
    private static void filterByBit(int i) {
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
        
        for (int j = 1; j <= M; j++) {
            if (wsOxyFlag[j] == 1 && wsOxyRows > 1) {
                if (wsArray[j].charAt(i) == oxyBadBit) {
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
                if (wsArray[j].charAt(i) == co2BadBit) {
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
    
    private static long wsOxyDec = 0;
    private static long wsCo2Dec = 0;
    
    private static void computeDecimals() {
        for (int i = 0; i < N; i++) {
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