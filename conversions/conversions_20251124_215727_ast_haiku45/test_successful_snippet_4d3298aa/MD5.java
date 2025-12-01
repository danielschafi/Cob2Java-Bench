import java.io.*;
import java.nio.file.*;

public class MD5 {
    private String preCMD = "echo -n ";
    private String str1Part1 = "The quick brown fox jumps";
    private String str1Part2 = " over the lazy dog";
    private String postCMD = " | md5sum > MD5";
    private String str1Complete;
    private String str1MD5 = "";

    public MD5() {
        str1Complete = str1Part1 + str1Part2;
    }

    public static void main(String[] args) {
        MD5 program = new MD5();
        program.mainProgram();
    }

    private void mainProgram() {
        System.out.println(str1Complete);
        getMD5();
        System.out.println(str1MD5);
    }

    private void getMD5() {
        try {
            String command = preCMD + str1Complete + postCMD;
            ProcessBuilder pb = new ProcessBuilder("sh", "-c", command);
            pb.start().waitFor();

            str1MD5 = new String(Files.readAllBytes(Paths.get("MD5"))).trim();

            Files.deleteIfExists(Paths.get("MD5"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}