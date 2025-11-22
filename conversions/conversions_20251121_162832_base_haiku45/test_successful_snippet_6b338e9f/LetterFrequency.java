import java.io.*;
import java.nio.file.*;

public class LetterFrequency {
    private int[] letterCounts = new int[26];
    private String recordName;

    public static void main(String[] args) {
        LetterFrequency program = new LetterFrequency();
        program.run();
    }

    public void run() {
        try {
            readFile();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        displayResults();
    }

    private void readFile() throws IOException {
        File file = new File("File.txt");
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recordName = line;
                if (recordName.length() < 80) {
                    recordName = String.format("%-80s", recordName);
                }
                countLetters(recordName);
            }
        }
    }

    private void countLetters(String text) {
        String upperText = text.toUpperCase();
        for (char c : upperText.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                letterCounts[c - 'A']++;
            }
        }
    }

    private void displayResults() {
        System.out.println("Letter Frequency Distribution");
        System.out.println("-----------------------------");
        System.out.printf("A : %5d          N : %5d%n", letterCounts[0], letterCounts[13]);
        System.out.printf("B : %5d          O : %5d%n", letterCounts[1], letterCounts[14]);
        System.out.printf("C : %5d          P : %5d%n", letterCounts[2], letterCounts[15]);
        System.out.printf("D : %5d          Q : %5d%n", letterCounts[3], letterCounts[16]);
        System.out.printf("E : %5d          R : %5d%n", letterCounts[4], letterCounts[17]);
        System.out.printf("F : %5d          S : %5d%n", letterCounts[5], letterCounts[18]);
        System.out.printf("G : %5d          T : %5d%n", letterCounts[6], letterCounts[19]);
        System.out.printf("H : %5d          U : %5d%n", letterCounts[7], letterCounts[20]);
        System.out.printf("I : %5d          V : %5d%n", letterCounts[8], letterCounts[21]);
        System.out.printf("J : %5d          W : %5d%n", letterCounts[9], letterCounts[22]);
        System.out.printf("K : %5d          X : %5d%n", letterCounts[10], letterCounts[23]);
        System.out.printf("L : %5d          Y : %5d%n", letterCounts[11], letterCounts[24]);
        System.out.printf("M : %5d          Z : %5d%n", letterCounts[12], letterCounts[25]);
    }
}