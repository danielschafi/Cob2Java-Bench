import java.io.*;
import java.nio.file.*;

public class AOC2021161 {
    private static final String INPUT_FILE = "d16.input";
    private static int[] wsBits = new int[5272];
    private static int result = 0;
    
    public static void main(String[] args) throws IOException {
        String inputLine = Files.readString(Path.of(INPUT_FILE));
        int n = inputLine.length();
        
        // Process each character in the input line
        for (int j = 0; j < n; j++) {
            char x = inputLine.charAt(j);
            int y;
            
            if (x >= 'A' && x <= 'F') {
                y = (int)x - 55; // Convert A-F to 10-15
            } else {
                y = (int)x - 48; // Convert 0-9 to 0-9
            }
            
            int valDec = y;
            String valBin = decToBin(valDec, 4);
            
            // Copy each bit from valBin to wsBits array
            for (int k = 0; k < 4; k++) {
                wsBits[4 * j + k] = Character.getNumericValue(valBin.charAt(k));
            }
        }
        
        int j = 0;
        while (j < n * 4 - 11) {
            processPacket(j);
        }
        
        System.out.println(result);
    }
    
    private static void processPacket(int j) {
        // Extract version (first 3 bits)
        int version = 0;
        for (int i = 0; i < 3; i++) {
            version = version * 2 + wsBits[j + i];
        }
        result += version;
        j += 3;
        
        // Extract type ID (next 3 bits)
        int typeId = 0;
        for (int i = 0; i < 3; i++) {
            typeId = typeId * 2 + wsBits[j + i];
        }
        j += 3;
        
        if (typeId == 4) {
            processLiteralPacket(j);
        } else {
            processOperatorPacket(j);
        }
    }
    
    private static void processLiteralPacket(int j) {
        int y = 1;
        while (y != 0) {
            y = wsBits[j];
            j += 5;
        }
    }
    
    private static void processOperatorPacket(int j) {
        int t = wsBits[j];
        j++;
        
        int len = (t == 0) ? 15 : 11;
        j += len;
    }
    
    private static String decToBin(int valDec, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            sb.append((valDec >> i) & 1);
        }
        return sb.toString();
    }
}