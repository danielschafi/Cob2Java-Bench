import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MD5 {
    public static void main(String[] args) {
        String preCmd = "echo -n ";
        String str1Part1 = "The quick brown fox jumps";
        String str1Part2 = " over the lazy dog";
        String postCmd = " | md5sum > MD5";
        String str1Complete = str1Part1 + str1Part2;
        String str1 = preCmd + "\"" + str1Complete + "\"" + postCmd;
        String str1MD5 = "";

        System.out.println(str1Complete);
        getMD5(str1);
        try (BufferedReader br = new BufferedReader(new FileReader("MD5"))) {
            str1MD5 = br.readLine().split(" ")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(str1MD5);
        new File("MD5").delete();
    }

    private static void getMD5(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}