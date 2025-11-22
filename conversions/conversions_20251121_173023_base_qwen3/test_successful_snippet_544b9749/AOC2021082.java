import java.io.*;
import java.util.*;

public class AOC2021082 {
    static final int NUM_LETTERS = 7;
    static final int NUM_DIGITS = 10;
    static final int NUM_SEGMENTS = 7;
    static final int MAX_RECORD_LENGTH = 99;
    
    static int[][] digitsAsBin = new int[NUM_DIGITS][NUM_SEGMENTS];
    static int[][] bufferAsBinArr = new int[14][NUM_SEGMENTS];
    static int[][] zeroSixNine = new int[3][NUM_SEGMENTS];
    static int[][] twoThreeFive = new int[3][NUM_SEGMENTS];
    static int idx0 = 0;
    static int idx9 = 0;
    static int idx3 = 0;
    static int idx5 = 0;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d08.input"));
        String line;
        
        while ((line = reader.readLine()) != null) {
            processRecord(line);
        }
        
        reader.close();
        System.out.println(result);
    }
    
    static void processRecord(String inputRecord) {
        // Reset variables for each record
        Arrays.fill(digitsAsBin[0], 0);
        Arrays.fill(digitsAsBin[1], 0);
        Arrays.fill(digitsAsBin[2], 0);
        Arrays.fill(digitsAsBin[3], 0);
        Arrays.fill(digitsAsBin[4], 0);
        Arrays.fill(digitsAsBin[5], 0);
        Arrays.fill(digitsAsBin[6], 0);
        Arrays.fill(digitsAsBin[7], 0);
        Arrays.fill(digitsAsBin[8], 0);
        Arrays.fill(digitsAsBin[9], 0);
        Arrays.fill(digitsAsBin[10], 0);
        
        for (int i = 0; i < 14; i++) {
            Arrays.fill(bufferAsBinArr[i], 0);
        }
        
        for (int i = 0; i < 3; i++) {
            Arrays.fill(zeroSixNine[i], 0);
            Arrays.fill(twoThreeFive[i], 0);
        }
        
        idx0 = 0;
        idx9 = 0;
        idx3 = 0;
        idx5 = 0;
        
        // Split the record by " | "
        String[] parts = inputRecord.split("\\|");
        String[] buffer = new String[14];
        
        // Process left side (patterns)
        String leftPart = parts[0].trim();
        int stringPtr = 0;
        int i = 0;
        
        while (i < 14 && stringPtr < leftPart.length()) {
            int spaceIndex = leftPart.indexOf(' ', stringPtr);
            if (spaceIndex == -1) {
                spaceIndex = leftPart.length();
            }
            
            buffer[i] = leftPart.substring(stringPtr, spaceIndex).trim();
            stringPtr = spaceIndex + 1;
            i++;
        }
        
        // Process all 14 patterns
        for (int j = 0; j < 14; j++) {
            if (j >= i) break;
            
            String currentBuffer = buffer[j];
            int len = currentBuffer.length();
            
            // Convert characters to binary representation
            for (int k = 0; k < NUM_SEGMENTS; k++) {
                char c = (char) ('a' + k);
                int count = 0;
                for (int l = 0; l < currentBuffer.length(); l++) {
                    if (currentBuffer.charAt(l) == c) {
                        count++;
                    }
                }
                bufferAsBinArr[j][k] = count;
            }
            
            // Determine digit based on length
            switch (len) {
                case 2: // Digit 1
                    for (int k = 0; k < NUM_SEGMENTS; k++) {
                        digitsAsBin[1][k] = bufferAsBinArr[j][k];
                    }
                    break;
                case 3: // Digit 7
                    for (int k = 0; k < NUM_SEGMENTS; k++) {
                        digitsAsBin[7][k] = bufferAsBinArr[j][k];
                    }
                    break;
                case 4: // Digit 4
                    for (int k = 0; k < NUM_SEGMENTS; k++) {
                        digitsAsBin[4][k] = bufferAsBinArr[j][k];
                    }
                    break;
                case 7: // Digit 8
                    for (int k = 0; k < NUM_SEGMENTS; k++) {
                        digitsAsBin[8][k] = bufferAsBinArr[j][k];
                    }
                    break;
                case 5: // Digits 2, 3, 5
                    if (j < 10) { // First 3 5-segment patterns
                        for (int k = 0; k < NUM_SEGMENTS; k++) {
                            twoThreeFive[j - 1][k] = bufferAsBinArr[j][k];
                        }
                    }
                    break;
                case 6: // Digits 0, 6, 9
                    if (j < 10) { // First 3 6-segment patterns
                        for (int k = 0; k < NUM_SEGMENTS; k++) {
                            zeroSixNine[j - 1][k] = bufferAsBinArr[j][k];
                        }
                    }
                    break;
            }
        }
        
        // Identify 0, 6, 9
        for (int i1 = 0; i1 < 3; i1++) {
            int l = 1;
            for (int j = 0; j < NUM_SEGMENTS; j++) {
                if (digitsAsBin[4][j] == 1) {
                    l *= zeroSixNine[i1][j];
                }
            }
            if (l == 1) {
                idx9 = i1;
                for (int j = 0; j < NUM_SEGMENTS; j++) {
                    digitsAsBin[9][j] = zeroSixNine[i1][j];
                }
            }
        }
        
        // Identify 0
        for (int i1 = 0; i1 < 3; i1++) {
            int l = 1;
            for (int j = 0; j < NUM_SEGMENTS; j++) {
                if (digitsAsBin[1][j] == 1) {
                    l *= zeroSixNine[i1][j];
                }
            }
            if (i1 != idx9 && l == 1) {
                idx0 = i1;
                for (int j = 0; j < NUM_SEGMENTS; j++) {
                    digitsAsBin[0][j] = zeroSixNine[i1][j];
                }
            }
        }
        
        // Identify 6
        for (int i1 = 0; i1 < 3; i1++) {
            if (i1 != idx9 && i1 != idx0) {
                for (int j = 0; j < NUM_SEGMENTS; j++) {
                    digitsAsBin[6][j] = zeroSixNine[i1][j];
                }
            }
        }
        
        // Identify 2, 3, 5
        for (int i1 = 0; i1 < 3; i1++) {
            int l = 1;
            for (int j = 0; j < NUM_SEGMENTS; j++) {
                if (digitsAsBin[1][j] == 1) {
                    l *= twoThreeFive[i1][j];
                }
            }
            if (l == 1) {
                idx3 = i1;
                for (int j = 0; j < NUM_SEGMENTS; j++) {
                    digitsAsBin[3][j] = twoThreeFive[i1][j];
                }
            }
        }
        
        // Identify 5
        for (int i1 = 0; i1 < 3; i1++) {
            int l = 1;
            for (int j = 0; j < NUM_SEGMENTS; j++) {
                if (twoThreeFive[i1][j] == 1) {
                    l *= digitsAsBin[6][j];
                }
            }
            if (l == 1) {
                idx5 = i1;
                for (int j = 0; j < NUM_SEGMENTS; j++) {
                    digitsAsBin[5][j] = twoThreeFive[i1][j];
                }
            }
        }
        
        // Identify 2
        for (int i1 = 0; i1 < 3; i1++) {
            if (i1 != idx3 && i1 != idx5) {
                for (int j = 0; j < NUM_SEGMENTS; j++) {
                    digitsAsBin[2][j] = twoThreeFive[i1][j];
                }
            }
        }
        
        // Decode the output values
        int valDec = 0;
        for (int i1 = 10; i1 < 14; i1++) {
            for (int k = 1; k <= 10; k++) {
                int l = 1;
                for (int j = 0; j < NUM_SEGMENTS; j++) {
                    if (digitsAsBin[k - 1][j] != bufferAsBinArr[i1][j