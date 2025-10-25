import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MD5 {
    public static void main(String[] args) {
        String str1 = "echo -n 'The quick brown fox jumps over the lazy dog' | md5sum > MD5";
        String str1MD5 = "";

        try {
            Process process = Runtime.getRuntime().exec(str1);
            process.waitFor();

            File file = new File("MD5");
            BufferedReader br = new BufferedReader(new FileReader(file));
            str1MD5 = br.readLine().substring(0, 32);
            br.close();
            file.delete();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The quick brown fox jumps over the lazy dog");
        System.out.println(str1MD5);
    }
}