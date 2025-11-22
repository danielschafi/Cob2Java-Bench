import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day8 {
    public static void main(String[] args) {
        int stringCodeLength = 0;
        int stringMemoryLength = 0;
        int reencodedLength = 0;
        int lineMemoryAccumulator = 0;
        int lineReencodedMemoryAcc = 6;
        String escapeStatus = "none";

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String inputLine;
            
            while ((inputLine = reader.readLine()) != null) {
                int lineLength = inputLine.length();
                stringCodeLength += lineLength;

                for (int i = 1; i < lineLength; i++) {
                    char currentChar = inputLine.charAt(i);
                    
                    if (currentChar == '\\' || currentChar == '"') {
                        lineReencodedMemoryAcc += 2;
                    } else {
                        lineReencodedMemoryAcc += 1;
                    }

                    if (escapeStatus.equals("none")) {
                        if (currentChar == '\\') {
                            escapeStatus = "in escape";
                        } else {
                            lineMemoryAccumulator += 1;
                        }
                    } else if (escapeStatus.equals("in escape")) {
                        if (currentChar == 'x') {
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

            int result = stringCodeLength - stringMemoryLength;
            int result2 = reencodedLength - stringCodeLength;

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