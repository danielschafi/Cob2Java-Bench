import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day8 {
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        int stringCodeLength = 0;
        int stringMemoryLength = 0;
        int reencodedLength = 0;
        int lineMemoryAccumulator = 0;
        int lineReencodedMemoryAcc = 6;
        String escapeStatus = "none";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                int lineLength = line.length();
                stringCodeLength += lineLength;

                for (int i = 1; i < lineLength; i++) {
                    char currentChar = line.charAt(i);
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
        } catch (IOException e) {
            System.err.println("error: input.txt open failed with " + e.getMessage());
            System.exit(1);
        }

        int result = stringCodeLength - stringMemoryLength;
        int result2 = reencodedLength - stringCodeLength;

        System.out.println("Total string code length: " + stringCodeLength);
        System.out.println("Total string mem length : " + stringMemoryLength);
        System.out.println("Total reencoded length  : " + reencodedLength);
        System.out.println("Difference repr-mem: " + result);
        System.out.println("Difference repr-enc: " + result2);
    }
}