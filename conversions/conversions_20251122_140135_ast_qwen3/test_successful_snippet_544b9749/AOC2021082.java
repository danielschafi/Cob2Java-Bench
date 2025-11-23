import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AOC2021082 {
    private static final int[][] wsBufferAsBin = new int[14][7];
    private static final int[][] wsDigitsAsBin = new int[10][7];
    private static final int[][] ws069 = new int[3][7];
    private static final int[][] ws235 = new int[3][7];
    private static int idx0 = 0;
    private static int idx9 = 0;
    private static int idx3 = 0;
    private static int idx5 = 0;
    private static int i5 = 1;
    private static int i6 = 1;
    private static int wsValDec = 0;
    private static int wsResult = 0;
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d08.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(wsResult);
    }
    
    private static void processRecord(String inputRecord) {
        // Reset variables for each record
        idx0 = 0;
        idx9 = 0;
        idx3 = 0;
        idx5 = 0;
        i5 = 1;
        i6 = 1;
        
        // Split input record by delimiter
        String[] parts = inputRecord.split("\\s+\\|\\s+");
        String[] buffer = new String[14];
        int ptr = 0;
        
        // Parse first part (input patterns)
        String[] inputParts = parts[0].split("\\s+");
        for (int i = 0; i < Math.min(inputParts.length, 14); i++) {
            buffer[i] = inputParts[i];
        }
        
        // Parse second part (output digits)
        String[] outputParts = parts[1].split("\\s+");
        for (int i = 0; i < Math.min(outputParts.length, 4); i++) {
            buffer[inputParts.length + i] = outputParts[i];
        }
        
        // Process each pattern
        for (int i = 0; i < 14; i++) {
            if (i >= buffer.length) break;
            
            String currentPattern = buffer[i];
            int m = 0;
            // Count trailing spaces
            while (m < currentPattern.length() && currentPattern.charAt(currentPattern.length() - 1 - m) == ' ') {
                m++;
            }
            int len = 8 - m;
            
            // For each letter from 'a' to 'g'
            for (int j = 0; j < 7; j++) {
                char c = (char)('a' + j);
                int count = 0;
                for (int k = 0; k < currentPattern.length(); k++) {
                    if (currentPattern.charAt(k) == c) {
                        count++;
                    }
                }
                wsBufferAsBin[i][j] = count;
            }
            
            // Evaluate based on length
            switch (len) {
                case 2:
                    for (int j = 0; j < 7; j++) {
                        wsDigitsAsBin[2][j] = wsBufferAsBin[i][j];
                    }
                    break;
                case 3:
                    for (int j = 0; j < 7; j++) {
                        wsDigitsAsBin[8][j] = wsBufferAsBin[i][j];
                    }
                    break;
                case 4:
                    for (int j = 0; j < 7; j++) {
                        wsDigitsAsBin[5][j] = wsBufferAsBin[i][j];
                    }
                    break;
                case 7:
                    for (int j = 0; j < 7; j++) {
                        wsDigitsAsBin[9][j] = wsBufferAsBin[i][j];
                    }
                    break;
                case 5:
                    if (i < 11) {
                        for (int j = 0; j < 7; j++) {
                            ws235[i5 - 1][j] = wsBufferAsBin[i][j];
                        }
                        i5++;
                    }
                    break;
                case 6:
                    if (i < 11) {
                        for (int j = 0; j < 7; j++) {
                            ws069[i6 - 1][j] = wsBufferAsBin[i][j];
                        }
                        i6++;
                    }
                    break;
            }
        }
        
        // Identify 0, 6, 9
        for (int i = 0; i < 3; i++) {
            int l = 1;
            for (int j = 0; j < 7; j++) {
                if (wsDigitsAsBin[5][j] == 1) {
                    l *= ws069[i][j];
                }
            }
            if (l == 1) {
                idx9 = i;
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBin[10][j] = ws069[i][j];
                }
            }
        }
        
        for (int i = 0; i < 3; i++) {
            int l = 1;
            for (int j = 0; j < 7; j++) {
                if (wsDigitsAsBin[2][j] == 1) {
                    l *= ws069[i][j];
                }
            }
            if (i != idx9 && l == 1) {
                idx0 = i;
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBin[1][j] = ws069[i][j];
                }
            }
        }
        
        for (int i = 0; i < 3; i++) {
            if (i != idx9 && i != idx0) {
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBin[7][j] = ws069[i][j];
                }
            }
        }
        
        // Identify 2, 3, 5
        for (int i = 0; i < 3; i++) {
            int l = 1;
            for (int j = 0; j < 7; j++) {
                if (wsDigitsAsBin[2][j] == 1) {
                    l *= ws235[i][j];
                }
            }
            if (l == 1) {
                idx3 = i;
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBin[4][j] = ws235[i][j];
                }
            }
        }
        
        for (int i = 0; i < 3; i++) {
            int l = 1;
            for (int j = 0; j < 7; j++) {
                if (ws235[i][j] == 1) {
                    l *= wsDigitsAsBin[7][j];
                }
            }
            if (l == 1) {
                idx5 = i;
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBin[6][j] = ws235[i][j];
                }
            }
        }
        
        for (int i = 0; i < 3; i++) {
            if (i != idx3 && i != idx5) {
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBin[3][j] = ws235[i][j];
                }
            }
        }
        
        // Identify the last numbers
        wsValDec = 0;
        for (int i = 11; i <= 14; i++) {
            for (int k = 1; k <= 10; k++) {
                int l = 1;
                for (int j = 0; j < 7; j++) {
                    if (wsDigitsAsBin[k][j] != wsBufferAsBin[i][j]) {
                        l = 0;
                        break;
                    }
                }
                if (l == 1) {
                    wsValDec = wsValDec * 10 + k - 1;
                    break;
                }
            }
        }
        wsResult += wsValDec;
    }
}