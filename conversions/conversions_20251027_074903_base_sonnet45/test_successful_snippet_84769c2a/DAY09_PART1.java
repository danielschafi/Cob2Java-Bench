import java.util.Scanner;
import java.math.BigInteger;

public class DAY09_PART1 {
    private static final int LS_BUFFER_SIZE = 25;
    private static final int LS_TOTAL_NUMBER_COUNT = 1000;
    
    private static int lsTotalNumbersRead = 0;
    private static BigInteger lsNumber = BigInteger.ZERO;
    private static int lsIndex = 1;
    private static int lsIndexSearch1;
    private static int lsIndexSearch2;
    private static BigInteger lsSum = BigInteger.ZERO;
    private static BigInteger[] lsElement = new BigInteger[LS_BUFFER_SIZE + 1];
    
    private static BigInteger lsTarget = BigInteger.ZERO;
    private static BigInteger lsMin = BigInteger.ZERO;
    private static BigInteger lsMax = BigInteger.ZERO;
    private static BigInteger[] lsNumbers = new BigInteger[LS_TOTAL_NUMBER_COUNT];
    
    private static Scanner scanner = new Scanner(System.in);
    private static boolean inputAvailable = true;
    
    public static void main(String[] args) {
        for (int i = 0; i <= LS_BUFFER_SIZE; i++) {
            lsElement[i] = BigInteger.ZERO;
        }
        for (int i = 0; i < LS_TOTAL_NUMBER_COUNT; i++) {
            lsNumbers[i] = BigInteger.ZERO;
        }
        
        for (int i = 0; i < LS_BUFFER_SIZE; i++) {
            readNumber();
            if (!inputAvailable) break;
            moveToBuffers();
        }
        
        while (lsTotalNumbersRead < LS_TOTAL_NUMBER_COUNT) {
            part1();
            if (!inputAvailable) break;
        }
        
        part2();
    }
    
    private static void readNumber() {
        try {
            if (scanner.hasNextBigInteger()) {
                lsNumber = scanner.nextBigInteger();
            } else {
                inputAvailable = false;
            }
        } catch (Exception e) {
            inputAvailable = false;
        }
    }
    
    private static void moveToBuffers() {
        lsElement[lsIndex] = lsNumber;
        lsNumbers[lsTotalNumbersRead] = lsNumber;
        lsIndex++;
        lsTotalNumbersRead++;
        if (lsIndex > LS_BUFFER_SIZE) {
            lsIndex = 1;
        }
    }
    
    private static void part1() {
        readNumber();
        if (!inputAvailable) return;
        findMatch();
        moveToBuffers();
    }
    
    private static void findMatch() {
        boolean found = false;
        
        outerLoop:
        for (lsIndexSearch1 = 1; lsIndexSearch1 <= LS_BUFFER_SIZE; lsIndexSearch1++) {
            for (lsIndexSearch2 = 1; lsIndexSearch2 <= LS_BUFFER_SIZE; lsIndexSearch2++) {
                if (!lsElement[lsIndexSearch1].equals(lsElement[lsIndexSearch2])) {
                    lsSum = lsElement[lsIndexSearch1].add(lsElement[lsIndexSearch2]);
                    
                    if (lsSum.equals(lsNumber)) {
                        found = true;
                        break outerLoop;
                    }
                }
            }
        }
        
        if (!lsSum.equals(lsNumber)) {
            System.out.println("NOT FOUND " + lsNumber);
            lsTarget = lsNumber;
        }
    }
    
    private static void part2() {
        boolean targetFound = false;
        
        outerLoop:
        for (lsIndexSearch1 = 0; lsIndexSearch1 < LS_TOTAL_NUMBER_COUNT; lsIndexSearch1++) {
            lsSum = BigInteger.ZERO;
            lsMax = BigInteger.ZERO;
            lsMin = new BigInteger("99999999999999999999");
            
            for (lsIndexSearch2 = lsIndexSearch1; lsIndexSearch2 < LS_TOTAL_NUMBER_COUNT; lsIndexSearch2++) {
                lsSum = lsSum.add(lsNumbers[lsIndexSearch2]);
                lsMax = lsMax.max(lsNumbers[lsIndexSearch2]);
                lsMin = lsMin.min(lsNumbers[lsIndexSearch2]);
                
                if (lsSum.compareTo(lsTarget) >= 0) {
                    break;
                }
            }
            
            if (lsSum.equals(lsTarget)) {
                targetFound = true;
                break;
            }
        }
        
        if (lsSum.equals(lsTarget)) {
            lsSum = lsMin.add(lsMax);
            System.out.println("MIN " + lsMin + " MAX " + lsMax + " SUM " + lsSum);
        }
    }
}