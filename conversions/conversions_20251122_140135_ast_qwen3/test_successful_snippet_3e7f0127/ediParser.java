import java.io.*;
import java.nio.file.*;

public class ediParser {
    public static void main(String[] args) {
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get("Team3_EDI_Data.txt"));
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("Team3_EDI_FINAL.txt"));
            
            String line;
            while ((line = reader.readLine()) != null) {
                int stringEnd = 70;
                while (stringEnd > 0 && line.charAt(stringEnd - 1) == ' ') {
                    stringEnd--;
                }
                
                String ediSeg = line.substring(0, stringEnd);
                
                writer.write(ediSeg);
                writer.newLine();
                writer.write("");
                writer.newLine();
            }
            
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}