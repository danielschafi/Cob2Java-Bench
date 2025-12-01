import java.util.Scanner;

public class ExpandRange {
    private static int commaPos = 1;
    private static int dashPos;
    private static int endNum;
    private static final int MAX_PART_LEN = 10;
    private static int num;
    private static String editedNum;
    private static String part;
    private static boolean lastPart = false;
    private static String rangeStr;
    private static final int RANGE_STR_LEN = 80;
    private static int startPos;
    private static int startNum;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        rangeStr = scanner.nextLine();
        scanner.close();

        while (true) {
            unstring(rangeStr);
            checkIfLast();

            if (lastPart) {
                break;
            }

            findRangeDash();

            if (dashPos > MAX_PART_LEN) {
                displayNum();
            } else {
                displayRange();
            }
        }

        System.out.println();
    }

    private static void unstring(String str) {
        int index = commaPos - 1;
        int endIndex = str.indexOf(',', index);

        if (endIndex == -1) {
            part = str.substring(index).trim();
            commaPos = RANGE_STR_LEN + 1;
        } else {
            part = str.substring(index, endIndex).trim();
            commaPos = endIndex + 2;
        }
    }

    private static void checkIfLast() {
        if (commaPos > RANGE_STR_LEN) {
            lastPart = true;
        }
    }

    private static void findRangeDash() {
        if (part.length() > 0 && part.charAt(0) != '-') {
            startPos = 1;
        } else {
            startPos = 2;
        }

        dashPos = 1;
        String substring = part.substring(startPos - 1);
        int dashIndex = substring.indexOf('-');

        if (dashIndex == -1) {
            dashPos = substring.length() + startPos;
        } else {
            dashPos = dashIndex + startPos;
        }
    }

    private static void displayNum() {
        try {
            int value = Integer.parseInt(part.trim());
            displayEditedNum(part, true);
        } catch (NumberFormatException e) {
            displayEditedNum(part, true);
        }
    }

    private static void displayRange() {
        String startStr = part.substring(0, dashPos - 1).trim();
        String endStr = part.substring(dashPos).trim();

        startNum = Integer.parseInt(startStr);
        endNum = Integer.parseInt(endStr);

        for (num = startNum; num < endNum; num++) {
            displayEditedNum(String.valueOf(num), false);
        }

        displayEditedNum(String.valueOf(endNum), true);
    }

    private static void displayEditedNum(String value, boolean hideComma) {
        System.out.print(value.trim());
        if (!hideComma) {
            System.out.print(", ");
        }
    }
}