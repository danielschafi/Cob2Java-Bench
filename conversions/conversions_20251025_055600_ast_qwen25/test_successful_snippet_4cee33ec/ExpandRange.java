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
            int commaIndex = rangeStr.indexOf(',', commaPos - 1);
            if (commaIndex == -1) {
                commaIndex = rangeStr.length();
                lastPart = true;
            }
            String part = rangeStr.substring(commaPos - 1, commaIndex).trim();
            commaPos = commaIndex + 2;

            int dashPos = findRangeDash(part);

            if (dashPos > MAX_PART_LEN) {
                displayNum(part, "Y");
            } else {
                displayRange(part, dashPos);
            }
        }

        System.out.println();
    }

    private static int findRangeDash(String part) {
        int startPos = part.startsWith("-") ? 2 : 1;
        int dashPos = part.indexOf('-', startPos - 1);
        return dashPos + startPos - 1;
    }

    private static void displayNum(String part, String partFlag) {
        int num = Integer.parseInt(part);
        displayEditedNum(partFlag, num);
    }

    private static void displayRange(String part, int dashPos) {
        int startNum = Integer.parseInt(part.substring(0, dashPos - 1));
        int endNum = Integer.parseInt(part.substring(dashPos));

        for (int num = startNum; num < endNum; num++) {
            displayEditedNum("N", num);
        }
        displayEditedNum("Y", endNum);
    }

    private static void displayEditedNum(String hideCommaFlag, int editedNum) {
        String numStr = String.format("%03d", editedNum).replaceFirst("^0+(?!$)", "");
        System.out.print(numStr);
        if (!"Y".equals(hideCommaFlag)) {
            System.out.print(", ");
        }
    }
}