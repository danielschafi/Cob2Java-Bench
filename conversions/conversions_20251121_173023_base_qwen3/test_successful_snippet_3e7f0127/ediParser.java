import java.io.*;
import java.nio.file.*;

public class ediParser {
    private static final String INPUT_FILE = "Team3_EDI_Data.txt";
    private static final String OUTPUT_FILE = "Team3_EDI_FINAL.txt";

    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(INPUT_FILE));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(OUTPUT_FILE))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) continue;

                int endIndex = line.length();
                while (endIndex > 0 && line.charAt(endIndex - 1) == ' ') {
                    endIndex--;
                }

                String trimmedLine = line.substring(0, endIndex);
                writer.write(trimmedLine);
                writer.newLine();
                writer.write("");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}