public class Hamming {
    private static long curPower3 = 1;
    private static int curEvilNum = 0;
    private static int curOdiousNum = 0;
    private static int lineIndex = 1;

    public static void main(String[] args) {
        System.out.println("     3^   EVIL   ODD");
        for (int i = 0; i < 30; i++) {
            makeLine();
        }
    }

    private static void makeLine() {
        int popcount = findPopcount(curPower3);
        String line = String.format("%2d. %2d     %2d     %2d", lineIndex, popcount, curEvilNum, curOdiousNum);
        System.out.println(line);
        
        curPower3 *= 3;
        curEvilNum++;
        curOdiousNum++;
        lineIndex++;
    }

    private static int findPopcount(long num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num >>= 1;
        }
        return count;
    }

    private static boolean isEvil(int num) {
        int popcount = findPopcount(num);
        return popcount % 2 == 0;
    }

    private static boolean isOdious(int num) {
        int popcount = findPopcount(num);
        return popcount % 2 == 1;
    }

    private static int findNextEvil(int start) {
        int num = start;
        while (!isEvil(num)) {
            num++;
        }
        return num;
    }

    private static int findNextOdious(int start) {
        int num = start;
        while (!isOdious(num)) {
            num++;
        }
        return num;
    }
}
```

Wait, I need to reconsider the logic. Looking at the COBOL code more carefully:

```java
public class Hamming {
    private static long curPower3 = 1;
    private static int curEvilNum = 0;
    private static int curOdiousNum = 0;
    private static int lineIndex = 1;

    public static void main(String[] args) {
        System.out.println("     3^   EVIL   ODD");
        for (int i = 0; i < 30; i++) {
            makeLine();
        }
    }

    private static void makeLine() {
        int popcount = findPopcount(curPower3);
        System.out.printf("%2d. %2d     %2d     %2d%n", lineIndex, popcount, curEvilNum, curOdiousNum);
        
        curPower3 *= 3;
        curEvilNum = findNextEvil(curEvilNum + 1);
        curOdiousNum = findNextOdious(curOdiousNum + 1);
        lineIndex++;
    }

    private static int findPopcount(long num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num >>= 1;
        }
        return count;
    }

    private static boolean isEvil(int num) {
        int popcount = findPopcount(num);
        return popcount % 2 == 0;
    }

    private static boolean isOdious(int num) {
        int popcount = findPopcount(num);
        return popcount % 2 == 1;
    }

    private static int findNextEvil(int start) {
        int num = start;
        while (!isEvil(num)) {
            num++;
        }
        return num;
    }

    private static int findNextOdious(int start) {
        int num = start;
        while (!isOdious(num)) {
            num++;
        }
        return num;
    }
}