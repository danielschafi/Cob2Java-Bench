import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MD5 {
    private static final String TMP_MD5_FILE = "MD5";
    
    public static void main(String[] args) {
        String str1Complete = "The quick brown fox jumps over the lazy dog";
        String str1Md5 = "";
        
        System.out.println(str1Complete);
        
        try {
            getMD5(str1Complete, TMP_MD5_FILE);
            
            // Read the MD5 hash from the temporary file
            Path filePath = Paths.get(TMP_MD5_FILE);
            List<String> lines = Files.readAllLines(filePath);
            if (!lines.isEmpty()) {
                str1Md5 = lines.get(0).substring(0, Math.min(32, lines.get(0).length()));
            }
            
            System.out.println(str1Md5);
            
            // Delete the temporary file
            Files.deleteIfExists(filePath);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void getMD5(String input, String tempFileName) throws Exception {
        // Build the command string
        StringBuilder cmdBuilder = new StringBuilder();
        cmdBuilder.append("echo -n '")
                  .append(input)
                  .append("' | md5sum > ")
                  .append(tempFileName);
        
        // Execute the system command
        Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", cmdBuilder.toString()});
        process.waitFor();
    }
}