import java.io.*;
import java.nio.file.*;
import java.util.*;

public class day8 {
    private static final String INPUT_FILE = "input.txt";
    
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(INPUT_FILE));
            
            int stringCodeLength = 0;
            int stringMemoryLength = 0;
            int reencodedLength = 0;
            
            for (String line : lines) {
                // Add line length to total code length
                stringCodeLength += line.length();
                
                // Calculate memory length
                int lineMemoryAccumulator = 0;
                boolean inEscape = false;
                boolean hexEscape = false;
                
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    
                    if (!inEscape && c == '\\') {
                        inEscape = true;
                    } else if (inEscape && !hexEscape && c == 'x') {
                        hexEscape = true;
                    } else if (inEscape && hexEscape) {
                        // Skip next two characters (the hex digits)
                        hexEscape = false;
                        inEscape = false;
                        lineMemoryAccumulator++;
                    } else if (inEscape) {
                        // Regular escape sequence
                        inEscape = false;
                        lineMemoryAccumulator++;
                    } else {
                        // Regular character
                        lineMemoryAccumulator++;
                    }
                }
                
                stringMemoryLength += lineMemoryAccumulator;
                
                // Calculate reencoded length
                int lineReencodedMemoryAcc = 2; // Start with quotes
                
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c == '\\' || c == '"') {
                        lineReencodedMemoryAcc += 2; // Add backslash and character
                    } else {
                        lineReencodedMemoryAcc++; // Regular character
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