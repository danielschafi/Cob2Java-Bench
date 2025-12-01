import java.io.*;
import java.nio.file.*;

public class day8 {
    private static int lineLength = 512;
    private static int inputFileStatus = 0;
    private static int stringCodeLength = 0;
    private static int stringMemoryLength = 0;
    private static int reencodedLength = 0;
    private static int lineMemoryAccumulator = 0;
    private static int lineReencodedMemoryAcc = 6;
    private static int result = 0;
    private static int result2 = 0;
    private static int i = 0;
    private static String escapeStatus = "none";

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            
            String line;
            while ((line = reader.readLine()) != null) {
                lineLength = line.length();
                
                if (lineLength == 0) {
                    continue;
                }
                
                stringCodeLength += lineLength;
                
                for (i = 1; i < lineLength; i++) {
                    char ch = line.charAt(i);
                    
                    if (ch == '\\' || ch == '"') {
                        lineReencodedMemoryAcc += 2;
                    } else {
                        lineReencodedMemoryAcc += 1;
                    }
                    
                    if (escapeStatus.equals("none")) {
                        if (ch == '\\') {
                            escapeStatus = "in escape";
                        } else {
                            lineMemoryAccumulator += 1;
                        }
                    } else if (escapeStatus.equals("in escape")) {
                        if (ch == 'x') {
                            escapeStatus = "hex escape";
                        } else {
                            lineMemoryAccumulator += 1;
                            escapeStatus = "none";
                        }
                    } else if (escapeStatus.equals("hex escape")) {
                        escapeStatus = "hexescape2";
                    } else if (escapeStatus.equals("hexescape2")) {
                        escapeStatus = "none";
                        lineMemoryAccumulator += 1;
                    }
                }
                
                escapeStatus = "none";
                stringMemoryLength += lineMemoryAccumulator;
                lineMemoryAccumulator = 0;
                
                reencodedLength += lineReencodedMemoryAcc;
                lineReencodedMemoryAcc = 6;
            }
            
            reader.close();
            
            result = stringCodeLength - stringMemoryLength;
            result2 = reencodedLength - stringCodeLength;
            
            System.out.println("Total string code length: " + stringCodeLength);
            System.out.println("Total string mem length : " + stringMemoryLength);
            System.out.println("Total reencoded length  : " + reencodedLength);
            System.out.println("Difference repr-mem: " + result);
            System.out.println("Difference repr-enc: " + result2);
            
        } catch (IOException e) {
            System.err.println("error: input.txt open failed with " + e.getMessage());
            System.exit(1);
        }
    }
}