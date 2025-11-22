import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AOC1801 {
    private static final int MAX_FREQ_HIST = 150000;
    
    private List<Integer> puzzleHist = new ArrayList<>();
    private int puzzleCount = 0;
    private int freqLast = 0;
    private Set<Integer> freqHistSet = new HashSet<>();
    private boolean freqMatch = false;
    
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
            while ((line = reader.readLine()) != null) {
                processRecord(line.trim());
            }
            puzzleCount = puzzleHist.size();
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private void processRecord(String puzzleClue) {
        try {
            int value = Integer.parseInt(puzzleClue);
            puzzleHist.add(value);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + puzzleClue);
        }
    }
    
    private void part1() {
        freqLast = 0;
        for (int p = 0; p < puzzleCount; p++) {
            frequencyShiftPart1(p);
        }
        displayResult(1);
    }
    
    private void frequencyShiftPart1(int p) {
        int freqShft = puzzleHist.get(p);
        freqLast += freqShft;
    }
    
    private void part2() {
        freqLast = 0;
        freqMatch = false;
        freqHistSet.clear();
        freqHistSet.add(0);
        
        int p = 0;
        while (!freqMatch) {
            if (p >= puzzleCount) {
                p = 0;
            }
            frequencyShiftPart2(p);
            p++;
        }
        displayResult(2);
    }
    
    private void frequencyShiftPart2(int p) {
        int freqShft = puzzleHist.get(p);
        freqLast += freqShft;
        
        if (freqHistSet.contains(freqLast)) {
            freqMatch = true;
        } else {
            freqHistSet.add(freqLast);
        }
    }
    
    private void displayResult(int partNum) {
        System.out.println("Part " + partNum + ": " + freqLast);
    }
}