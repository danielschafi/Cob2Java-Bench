import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AOC1801 {
    public static void main(String[] args) {
        System.out.println("Advent of Code 2018, Day 1");
        List<String> puzzleRecords = new ArrayList<>();
        prepareRecords(puzzleRecords);
        part1(puzzleRecords);
        part2(puzzleRecords);
    }

    private static void prepareRecords(List<String> puzzleRecords) {
        try (BufferedReader br = new BufferedReader(new FileReader("input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                puzzleRecords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void part1(List<String> puzzleRecords) {
        int freqLast = 0;
        for (String record : puzzleRecords) {
            freqLast += Integer.parseInt(record);
        }
        displayResult(1, freqLast);
    }

    private static void part2(List<String> puzzleRecords) {
        int freqLast = 0;
        Set<Integer> freqHist = new HashSet<>();
        freqHist.add(freqLast);
        boolean freqMatch = false;
        int index = 0;
        while (!freqMatch) {
            freqLast += Integer.parseInt(puzzleRecords.get(index));
            if (freqHist.contains(freqLast)) {
                freqMatch = true;
            } else {
                freqHist.add(freqLast);
            }
            index = (index + 1) % puzzleRecords.size();
        }
        displayResult(2, freqLast);
    }

    private static void displayResult(int partNum, int freqDisp) {
        System.out.println("Part " + partNum + ": " + freqDisp);
    }
}