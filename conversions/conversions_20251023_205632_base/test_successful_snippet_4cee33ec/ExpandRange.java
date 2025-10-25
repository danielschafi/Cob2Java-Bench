import java.util.Scanner;

public class ExpandRange {
    private static final int MAX_PART_LEN = 10;
    private static final int RANGE_STR_LEN = 80;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rangeStr = scanner.nextLine();
        scanner.close();

        int commaPos = 1;
        boolean lastPart = false;

        while (!lastPart) {
            int partLength = rangeStr.indexOf(',', commaPos - 1);
            if (partLength == -1) {
                partLength = rangeStr.length();
                lastPart = true;
            }
            String part = rangeStr.substring(commaPos - 1, partLength);

            int dashPos = findRangeDash(part);

            if (dashPos > MAX_PART_LEN) {
                displayNum(part, lastPart);
            } else {
                displayRange(part, lastPart);
            }

            commaPos = partLength + 2;
        }

        System.out.println();
    }

    private static int findRangeDash(String part) {
        int startPos = part.charAt(0) == '-' ? 2 : 1;
        int dashPos = part.indexOf('-', startPos - 1);
        return dashPos + startPos - 1;
    }

    private static void displayNum(String part, boolean lastPart) {
        int num = Integer.parseInt(part);
        displayEditedNum(lastPart ? "Y" : "N", num);
    }

    private static void displayRange(String part, boolean lastPart) {
        int startNum = Integer.parseInt(part.substring(0, findRangeDash(part) - 1));
        int endNum = Integer.parseInt(part.substring(findRangeDash(part) + 1));

        for (int num = startNum; num < endNum; num++) {
            displayEditedNum("N", num);
        }
        displayEditedNum(lastPart ? "Y" : "N", endNum);
    }

    private static void displayEditedNum(String hideCommaFlag, int editedNum) {
        System.out.print(String.format("%3d", editedNum).replace(' ', '0').trim());
        if (!hideCommaFlag.equals("Y")) {
            System.out.print(", ");
        }
    }
}