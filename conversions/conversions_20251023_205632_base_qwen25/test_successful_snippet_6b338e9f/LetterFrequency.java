import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LetterFrequency {
    public static void main(String[] args) {
        String fileName = "File.txt";
        int[] letterCnt = new int[26];
        String recordName;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((recordName = br.readLine()) != null) {
                recordName = recordName.toUpperCase();
                for (char c : recordName.toCharArray()) {
                    if (c >= 'A' && c <= 'Z') {
                        letterCnt[c - 'A']++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Letter Frequency Distribution");
        System.out.println("-----------------------------");
        System.out.printf("A : %5d          N : %5d%n", letterCnt[0], letterCnt[13]);
        System.out.printf("B : %5d          O : %5d%n", letterCnt[1], letterCnt[14]);
        System.out.printf("C : %5d          P : %5d%n", letterCnt[2], letterCnt[15]);
        System.out.printf("D : %5d          Q : %5d%n", letterCnt[3], letterCnt[16]);
        System.out.printf("E : %5d          R : %5d%n", letterCnt[4], letterCnt[17]);
        System.out.printf("F : %5d          S : %5d%n", letterCnt[5], letterCnt[18]);
        System.out.printf("G : %5d          T : %5d%n", letterCnt[6], letterCnt[19]);
        System.out.printf("H : %5d          U : %5d%n", letterCnt[7], letterCnt[20]);
        System.out.printf("I : %5d          V : %5d%n", letterCnt[8], letterCnt[21]);
        System.out.printf("J : %5d          W : %5d%n", letterCnt[9], letterCnt[22]);
        System.out.printf("K : %5d          X : %5d%n", letterCnt[10], letterCnt[23]);
        System.out.printf("L : %5d          Y : %5d%n", letterCnt[11], letterCnt[24]);
        System.out.printf("M : %5d          Z : %5d%n", letterCnt[12], letterCnt[25]);
    }
}