import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private static class WorkingStorage {
        String eof = "F";
        String preCmd = "echo -n ";
        String str1Part1 = "The quick brown fox jumps";
        String str1Part2 = " over the lazy dog";
        String postCmd = " | md5sum > MD5";
        String str1Complete = str1Part1 + str1Part2;
        String str1 = preCmd + str1Complete + postCmd;
        String str1MD5 = "";
    }

    private static WorkingStorage ws = new WorkingStorage();

    public static void main(String[] args) {
        mainProgram();
    }

    private static void mainProgram() {
        System.out.println(ws.str1Complete);
        getMD5();
        System.out.println(ws.str1MD5);
    }

    private static void getMD5() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(ws.str1Complete.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            ws.str1MD5 = hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println("MD5 algorithm not available");
            System.exit(1);
        }
    }
}