import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC1801 {
    private static final int MAX_RECORDS = 1004;
    private static final int MAX_HISTORY = 150000;
    
    private static String[] puzzleHist = new String[MAX_RECORDS];
    private static int[] freqHist = new int[MAX_HISTORY];
    private static int puzzleCount = 0;
    private static boolean eof = false;
    private static int freqShift = 0;
    private static int freqLast = 0;
    private static String freqDisp = "";
    private static char freqMatch = 'N';
    private static int partNum = 0;
    private static int freqHistIdx = 0;

    public static void main(String[] args) {
        System.out.println("Advent of Code 2018, Day 1");
        prepareRecords();
        part1();
        part2();
    }

    private static void prepareRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("input"))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < MAX_RECORDS) {
                puzzleHist[i] = line;
                i++;
            }
            puzzleCount = i;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void part1() {
        partNum = 1;
        for (int p = 0; p < puzzleCount; p++) {
            frequencyShift(p);
        }
        displayResult();
    }

    private static void part2() {
        partNum = 2;
        freqMatch = 'N';
        while (freqMatch == 'N') {
            for (int p = 0; p < puzzleCount; p++) {
                frequencyShift(p);
                if (freqMatch == 'Y') {
                    break;
                }
            }
        }
        displayResult();
    }

    private static void frequencyShift(int p) {
        if (p >= puzzleCount) {
            p = 0;
        }
        freqShift = Integer.parseInt(puzzleHist[p]);
        freqLast += freqShift;
        
        freqHistIdx = 0;
        for (int f = 0; f < MAX_HISTORY; f++) {
            if (freqHist[f] == freqLast) {
                freqMatch = 'Y';
                break;
            }
        }
        
        freqHist[freqHistIdx] = freqLast;
        freqHistIdx++;
    }

    private static void displayResult() {
        freqDisp = String.format("%d", freqLast);
        System.out.println("Part " + partNum + ": " + freqDisp);
    }
}