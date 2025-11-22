import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MD5 {
    public static void main(String[] args) {
        String str1Complete = "The quick brown fox jumps over the lazy dog";
        System.out.println(str1Complete);
        
        String md5Hash = getMD5(str1Complete);
        System.out.println(md5Hash);
    }
    
    public static String getMD5(String input) {
        try {
            // Create the command string
            String command = "echo -n '" + input + "' | md5sum";
            
            // Execute the command
            Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});
            process.waitFor();
            
            // Read the output from the process
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );
            
            String line = reader.readLine();
            if (line != null) {
                // Extract just the hash part (first 32 characters)
                return line.substring(0, Math.min(32, line.length()));
            }
            
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "";
    }
}