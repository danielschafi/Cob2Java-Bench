import java.util.Scanner;

public class Day09Part1 {
    private static final int LS_BUFFER_SIZE = 25;
    private static final int LS_TOTAL_NUMBER_COUNT = 1000;
    
    private static long lsTotalNumbersRead = 0;
    private static long lsNumber = 0;
    private static int lsIndex = 1;
    private static long lsIndexSearch1 = 0;
    private static long lsIndexSearch2 = 0;
    private static long lsSum = 0;
    private static long[] lsBuffer = new long[LS_BUFFER_SIZE];
    
    private static long lsTarget = 0;
    private static long lsMin = 0;
    private static long lsMax = 0;
    private static long[] lsNumbers = new long[LS_TOTAL_NUMBER_COUNT];
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        for (int i = 0; i < LS_BUFFER_SIZE; i++) {
            readNumber();
            moveToBuffers();
        }
        
        while (lsTotalNumbersRead < LS_TOTAL_NUMBER_COUNT) {
            part1();
        }
        
        part2();
    }
    
    private static void readNumber() {
        try {
            if (scanner.hasNextLong()) {
                lsNumber = scanner.nextLong();
            }
        } catch (Exception e) {
            return;
        }
    }
    
    private static void moveToBuffers() {
        lsBuffer[lsIndex - 1] = lsNumber;
        lsNumbers[(int)lsTotalNumbersRead] = lsNumber;
        lsIndex++;
        lsTotalNumbersRead++;
        if (lsIndex > LS_BUFFER_SIZE) {
            lsIndex = 1;
        }
    }
    
    private static void part1() {
        readNumber();
        findMatch();
        moveToBuffers();
    }
    
    private static void findMatch() {
        lsSum = 0;
        boolean found = false;
        
        for (lsIndexSearch1 = 1; lsIndexSearch1 <= LS_BUFFER_SIZE; lsIndexSearch1++) {
            for (lsIndexSearch2 = 1; lsIndexSearch2 <= LS_BUFFER_SIZE; lsIndexSearch2++) {
                if (lsBuffer[(int)lsIndexSearch1 - 1] != lsBuffer[(int)lsIndexSearch2 - 1]) {
                    lsSum = lsBuffer[(int)lsIndexSearch1 - 1] + lsBuffer[(int)lsIndexSearch2 - 1];
                    if (lsSum == lsNumber) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                break;
            }
        }
        
        if (lsSum != lsNumber) {
            System.out.println("NOT FOUND " + lsNumber);
            lsTarget = lsNumber;
        }
    }
    
    private static void part2() {
        for (lsIndexSearch1 = 1; lsIndexSearch1 <= LS_TOTAL_NUMBER_COUNT; lsIndexSearch1++) {
            lsSum = 0;
            lsMax = 0;
            lsMin = 99999999999999999999L;
            
            for (lsIndexSearch2 = lsIndexSearch1; lsIndexSearch2 <= LS_TOTAL_NUMBER_COUNT; lsIndexSearch2++) {
                lsSum += lsNumbers[(int)lsIndexSearch2 - 1];
                lsMax = Math.max(lsNumbers[(int)lsIndexSearch2 - 1], lsMax);
                lsMin = Math.min(lsNumbers[(int)lsIndexSearch2 - 1], lsMin);
                
                if (lsSum >= lsTarget) {
                    break;
                }
            }
            
            if (lsSum == lsTarget) {
                break;
            }
        }
        
        if (lsSum == lsTarget) {
            lsSum = lsMin + lsMax;
            System.out.println("MIN " + lsMin + " MAX " + lsMax + " SUM " + lsSum);
        }
    }
}