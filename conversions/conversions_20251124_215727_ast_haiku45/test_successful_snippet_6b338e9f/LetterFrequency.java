import java.io.*;
import java.nio.file.*;

public class LetterFrequency {
    private int[] letterCounts = new int[26];
    private BufferedReader reader;

    public LetterFrequency() {
        for (int i = 0; i < 26; i++) {
            letterCounts[i] = 0;
        }
    }

    public void processFile(String filename) {
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String upperLine = line.toUpperCase();
                for (char c : upperLine.toCharArray()) {
                    if (c >= 'A' && c <= 'Z') {
                        letterCounts[c - 'A']++;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void displayResults() {
        System.out.println("Letter Frequency Distribution");
        System.out.println("-----------------------------");
        System.out.println("A : " + formatNumber(letterCounts[0]) + "          " + "N : " + formatNumber(letterCounts[13]));
        System.out.println("B : " + formatNumber(letterCounts[1]) + "          " + "O : " + formatNumber(letterCounts[14]));
        System.out.println("C : " + formatNumber(letterCounts[2]) + "          " + "P : " + formatNumber(letterCounts[15]));
        System.out.println("D : " + formatNumber(letterCounts[3]) + "          " + "Q : " + formatNumber(letterCounts[16]));
        System.out.println("E : " + formatNumber(letterCounts[4]) + "          " + "R : " + formatNumber(letterCounts[17]));
        System.out.println("F : " + formatNumber(letterCounts[5]) + "          " + "S : " + formatNumber(letterCounts[18]));
        System.out.println("G : " + formatNumber(letterCounts[6]) + "          " + "T : " + formatNumber(letterCounts[19]));
        System.out.println("H : " + formatNumber(letterCounts[7]) + "          " + "U : " + formatNumber(letterCounts[20]));
        System.out.println("I : " + formatNumber(letterCounts[8]) + "          " + "V : " + formatNumber(letterCounts[21]));
        System.out.println("J : " + formatNumber(letterCounts[9]) + "          " + "W : " + formatNumber(letterCounts[22]));
        System.out.println("K : " + formatNumber(letterCounts[10]) + "          " + "X : " + formatNumber(letterCounts[23]));
        System.out.println("L : " + formatNumber(letterCounts[11]) + "          " + "Y : " + formatNumber(letterCounts[24]));
        System.out.println("M : " + formatNumber(letterCounts[12]) + "          " + "Z : " + formatNumber(letterCounts[25]));
    }

    private String formatNumber(int num) {
        return String.format("%5d", num);
    }

    public static void main(String[] args) {
        LetterFrequency lf = new LetterFrequency();
        lf.processFile("File.txt");
        lf.displayResults();
    }
}