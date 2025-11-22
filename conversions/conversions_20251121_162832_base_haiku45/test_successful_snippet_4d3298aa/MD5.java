import java.io.*;
import java.nio.file.*;

public class MD5 {
    public static void main(String[] args) {
        String str1Complete = "The quick brown fox jumps over the lazy dog";
        String str1MD5 = "";
        
        System.out.println(str1Complete);
        str1MD5 = getMD5(str1Complete);
        System.out.println(str1MD5);
    }
    
    static String getMD5(String input) {
        String md5Hash = "";
        String tempFile = "MD5";
        
        try {
            String command = "echo -n '" + input + "' | md5sum > " + tempFile;
            Process process = Runtime.getRuntime().exec(new String[]{"sh", "-c", command});
            process.waitFor();
            
            String md5Content = new String(Files.readAllBytes(Paths.get(tempFile))).trim();
            md5Hash = md5Content.substring(0, 32);
            
            Files.delete(Paths.get(tempFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return md5Hash;
    }
}