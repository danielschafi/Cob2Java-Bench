import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC1801 {
    private static final int PUZZLE_SIZE = 1004;
    private static final int FREQ_HIST_SIZE = 150000;
    
    private List<String> puzzleHist = new ArrayList<>();
    private int puzzleCount = 0;
    private long[] freqHist = new long[FREQ_HIST_SIZE];
    private int freqHistIdx = 0;
    private long freqLast = 0;
    private char freqMatch = 'N';
    private char partNum = '0';
    
    public static void main(String[] args) {
        AOC1801 program = new AOC1801();
        program.run();
    }
    
    private void run() {
        System.out.println("Advent of Code 2018, Day 1");
        prepareRecords();
        part1();
        part2();
    }
    
    private void prepareRecords() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("input"));
            for (String line : lines) {
                if (!line.isEmpty()) {
                    puzzleHist.add(line);
                }
            }
            puzzleCount = puzzleHist.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void part1() {
        partNum = '1';
        freqLast = 0;
        freqMatch = 'N';
        freqHistIdx = 0;
        Arrays.fill(freqHist, 0);
        
        for (int p = 0; p < puzzleCount; p++) {
            frequencyShift(p);
        }
        
        displayResult();
    }
    
    private void part2() {
        partNum = '2';
        freqLast = 0;
        freqMatch = 'N';
        freqHistIdx = 0;
        Arrays.fill(freqHist, 0);
        
        int p = 0;
        while (freqMatch != 'Y') {
            if (p >= puzzleCount) {
                p = 0;
            }
            frequencyShift(p);
            p++;
        }
        
        displayResult();
    }
    
    private void frequencyShift(int p) {
        if (p >= puzzleCount) {
            p = 0;
        }
        
        String puzzleStr = puzzleHist.get(p);
        long freqShft = Long.parseLong(puzzleStr);
        freqLast += freqShft;
        
        int savedIdx = freqHistIdx;
        freqHistIdx = 0;
        
        boolean found = false;
        for (int f = 0; f < freqHistIdx + 1; f++) {
            if (freqHist[f] == freqLast) {
                freqMatch = 'Y';
                found = true;
                break;
            }
        }
        
        freqHistIdx = savedIdx;
        freqHistIdx++;
        if (freqHistIdx < FREQ_HIST_SIZE) {
            freqHist[freqHistIdx] = freqLast;
        }
    }
    
    private void displayResult() {
        System.out.println("Part " + partNum + ": " + freqLast);
    }
}