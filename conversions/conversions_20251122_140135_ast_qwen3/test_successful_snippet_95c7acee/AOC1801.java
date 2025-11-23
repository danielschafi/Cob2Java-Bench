import java.io.*;
import java.util.*;

public class AOC1801 {
    private static final String INPUT_FILE = "input";
    
    // Working storage variables
    private static int ws_record_size;
    private static char ws_part_num;
    private static int ws_freq_shft;
    private static int ws_freq_last;
    private static String ws_freq_disp;
    private static char ws_freq_match = 'N';
    private static int ws_puzzle_count;
    private static String[] ws_puzzle_hist = new String[1004];
    private static int[] ws_freq_hist = new int[150000];
    private static int ws_freq_hist_idx;
    private static char ws_eof = 'N';
    
    public static void main(String[] args) {
        System.out.println("Advent of Code 2018, Day 1");
        prepareRecords();
        part1();
        part2();
    }
    
    private static void prepareRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            int p = 0;
            while ((line = reader.readLine()) != null && p < 1004) {
                ws_puzzle_hist[p] = line;
                p++;
            }
            ws_puzzle_count = p;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void part1() {
        ws_part_num = '1';
        for (int p = 0; p < ws_puzzle_count; p++) {
            frequencyShift(p);
        }
        displayResult();
    }
    
    private static void part2() {
        ws_part_num = '2';
        int p = 0;
        while (ws_freq_match == 'N') {
            frequencyShift(p);
            p = (p + 1) % ws_puzzle_count;
        }
        displayResult();
    }
    
    private static void frequencyShift(int p) {
        if (p >= ws_puzzle_count) {
            p = 0;
        }
        
        ws_freq_shft = Integer.parseInt(ws_puzzle_hist[p]);
        ws_freq_last += ws_freq_shft;
        
        ws_freq_hist_idx = 0;
        ws_freq_hist[0] = 0;
        
        boolean found = false;
        for (int f = 0; f < ws_freq_hist.length; f++) {
            if (ws_freq_hist[f] == ws_freq_last) {
                ws_freq_match = 'Y';
                found = true;
                break;
            }
        }
        
        if (!found) {
            ws_freq_hist[ws_freq_hist_idx] = ws_freq_last;
            ws_freq_hist_idx++;
        }
    }
    
    private static void displayResult() {
        ws_freq_disp = String.format("%d", ws_freq_last);
        System.out.println("Part " + ws_part_num + ": " + ws_freq_disp);
    }
}