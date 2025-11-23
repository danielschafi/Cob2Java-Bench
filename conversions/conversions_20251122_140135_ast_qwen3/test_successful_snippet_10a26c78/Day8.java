import java.io.*;
import java.nio.file.*;

public class Day8 {
    private static final String INPUT_FILE = "input.txt";
    
    public static void main(String[] args) {
        try {
            byte[] content = Files.readAllBytes(Paths.get(INPUT_FILE));
            String[] lines = new String(content).split("\n");
            
            int stringCodeLength = 0;
            int stringMemoryLength = 0;
            int reencodedLength = 0;
            
            for (String line : lines) {
                if (line.isEmpty()) continue;
                
                int lineLength = line.length();
                stringCodeLength += lineLength;
                
                // Calculate memory length
                int lineMemoryAccumulator = 0;
                String escapeStatus = "none";
                
                for (int i = 0; i < lineLength; i++) {
                    char c = line.charAt(i);
                    
                    if (escapeStatus.equals("none")) {
                        if (c == '\\') {
                            escapeStatus = "in escape";
                        } else {
                            lineMemoryAccumulator++;
                        }
                    } else if (escapeStatus.equals("in escape")) {
                        if (c == 'x') {
                            escapeStatus = "hex escape";
                        } else {
                            lineMemoryAccumulator++;
                            escapeStatus = "none";
                        }
                    } else if (escapeStatus.equals("hex escape")) {
                        escapeStatus = "hexescape2";
                    } else if (escapeStatus.equals("hexescape2")) {
                        escapeStatus = "none";
                        lineMemoryAccumulator++;
                    }
                }
                
                stringMemoryLength += lineMemoryAccumulator;
                
                // Calculate reencoded length
                int lineReencodedMemoryAcc = 6;
                for (int i = 0; i < lineLength; i++) {
                    char c = line.charAt(i);
                    if (c == '\\' || c == '"') {
                        lineReencodedMemoryAcc += 2;
                    } else {
                        lineReencodedMemoryAcc += 1;
                    }
                }
                
                reencodedLength += lineReencodedMemoryAcc;
            }
            
            int result = stringCodeLength - stringMemoryLength;
            int result2 = reencodedLength - stringCodeLength;
            
            System.out.println("Total string code length: " + stringCodeLength);
            System.out.println("Total string mem length : " + stringMemoryLength);
            System.out.println("Total reencoded length  : " + reencodedLength);
            System.out.println("Difference repr-mem: " + result);
            System.out.println("Difference repr-enc: " + result2);
            
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            System.exit(1);
        }
    }
}