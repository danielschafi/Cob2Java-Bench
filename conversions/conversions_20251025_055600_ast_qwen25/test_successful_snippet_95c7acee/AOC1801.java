import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC1801 {
    private static final int MAX_PUZZLE_HIST = 1004;
    private static final int MAX_FREQ_HIST = 150000;

    private int recordSize;
    private int partNum;
    private int freqShift;
    private int freqLast;
    private String freqDisp;
    private char freqMatch;
    private int puzzleCount;
    private String[] puzzleHist = new String[MAX_PUZZLE_HIST];
    private int freqHistIdx;
    private int[] freqHist = new int[MAX_FREQ_HIST];
    private char eof;

    public static void main(String[] args) {
        AOC1801 aoc = new AOC1801();
        aoc.main();
    }

    public void main() {
        System.out.println("Advent of Code 2018, Day 1");
        prepareRecords();
        part1();
        part2();
    }

    public void prepareRecords() {
        try (BufferedReader br = new BufferedReader(new FileReader("input"))) {
            String line;
            int p = 0;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    puzzleHist[p] = line;
                    p++;
                }
            }
            puzzleCount = p;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void part1() {
        partNum = 1;
        for (int p = 0; p < puzzleCount; p++) {
            frequencyShift(p);
        }
        displayResult();
    }

    public void part2() {
        partNum = 2;
        freqMatch = 'N';
        int p = 0;
        while (freqMatch != 'Y') {
            frequencyShift(p);
            p = (p + 1) % puzzleCount;
        }
        displayResult();
    }

    public void displayResult() {
        freqDisp = String.format("%07d", freqLast);
        System.out.println("Part " + partNum + ": " + freqDisp);
    }

    public void frequencyShift(int p) {
        freqShift = Integer.parseInt(puzzleHist[p]);
        freqLast += freqShift;
        freqHistIdx = 0;
        while (freqHistIdx < freqHist.length && freqHist[freqHistIdx] != freqLast) {
            freqHistIdx++;
        }
        if (freqHistIdx < freqHist.length && freqHist[freqHistIdx] == freqLast) {
            freqMatch = 'Y';
        } else {
            freqHist[freqHistIdx] = freqLast;
        }
    }
}