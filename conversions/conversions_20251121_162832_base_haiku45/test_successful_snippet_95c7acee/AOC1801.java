import java.io.*;
import java.util.*;

public class AOC1801 {
    private static final int MAX_PUZZLE_COUNT = 1004;
    private static final int MAX_FREQ_HIST = 150000;
    
    private int[] puzzleHist = new int[MAX_PUZZLE_COUNT];
    private int puzzleCount = 0;
    private long[] freqHist = new long[MAX_FREQ_HIST];
    private long freqShft = 0;
    private long freqLast = 0;
    private char freqMatch = 'N';
    private int freqHistIdx = 0;
    private char partNum = '1';
    
    public static void main(String[] args) {
        AOC1801 program = new AOC1801();
        System.out.println("Advent of Code 2018, Day 1");
        program.prepareRecords();
        program.part1();
        program.part2();
    }
    
    private void prepareRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("input"))) {
            String line;
            int p = 0;
            while ((line = reader.readLine()) != null && p < MAX_PUZZLE_COUNT) {
                puzzleHist[p] = Integer.parseInt(line.trim());
                p++;
            }
            puzzleCount = p;
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
        freqShft = puzzleHist[p];
        freqLast += freqShft;
        
        int saveFreqHistIdx = freqHistIdx;
        
        boolean found = false;
        for (int f = 0; f < freqHistIdx; f++) {
            if (freqHist[f] == freqLast) {
                freqMatch = 'Y';
                found = true;
                break;
            }
        }
        
        if (!found) {
            freqHist[freqHistIdx] = freqLast;
            freqHistIdx++;
        }
    }
    
    private void displayResult() {
        System.out.println("Part " + partNum + ": " + freqLast);
    }
}