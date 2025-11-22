import java.io.*;
import java.nio.file.*;

public class day8 {
    static class FileStatus {
        int status = 0;
    }

    public static void main(String[] args) {
        FileStatus inputFileStatus = new FileStatus();
        int lineLength = 512;
        int stringCodeLength = 0;
        int stringMemoryLength = 0;
        int reencodedLength = 0;
        int lineMemoryAccumulator = 0;
        int lineReencodedMemoryAcc = 6;
        int result = 0;
        int result2 = 0;
        int i = 0;
        String escapeStatus = "none";

        try {
            String content = new String(Files.readAllBytes(Paths.get("input.txt")));
            String[] lines = content.split("\n", -1);

            for (String inputLine : lines) {
                if (inputLine.isEmpty() && content.endsWith("\n")) {
                    continue;
                }

                lineLength = inputLine.length();
                stringCodeLength += lineLength;

                escapeStatus = "none";
                lineMemoryAccumulator = 0;
                lineReencodedMemoryAcc = 6;

                for (i = 1; i < lineLength; i++) {
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

            result = stringCodeLength - stringMemoryLength;
            result2 = reencodedLength - stringCodeLength;

            System.out.println("Total string code length: " + stringCodeLength);
            System.out.println("Total string mem length : " + stringMemoryLength);
            System.out.println("Total reencoded length  : " + reencodedLength);
            System.out.println("Difference repr-mem: " + result);
            System.out.println("Difference repr-enc: " + result2);

        } catch (IOException e) {
            System.err.println("error: input.txt open failed");
            System.exit(1);
        }
    }
}