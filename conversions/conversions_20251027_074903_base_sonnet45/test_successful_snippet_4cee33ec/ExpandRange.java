import java.util.Scanner;

public class ExpandRange {
    private static final int MAX_PART_LEN = 10;
    private static final int RANGE_STR_LEN = 80;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rangeStr = scanner.nextLine();
        scanner.close();

        int commaPos = 0;
        boolean lastPart = false;

        do {
            String part = "";
            int nextComma = rangeStr.indexOf(',', commaPos);
            
            if (nextComma == -1) {
                part = rangeStr.substring(commaPos).trim();
                lastPart = true;
            } else {
                part = rangeStr.substring(commaPos, nextComma).trim();
                commaPos = nextComma + 1;
            }

            if (commaPos > RANGE_STR_LEN) {
                lastPart = true;
            }

            int dashPos = findRangeDash(part);

            String partFlag = lastPart ? "Y" : "N";

            if (dashPos > MAX_PART_LEN || dashPos == -1) {
                displayNum(part, partFlag);
            } else {
                displayRange(part, dashPos, partFlag);
            }

        } while (!lastPart);

        System.out.println();
    }

    private static int findRangeDash(String part) {
        int startPos;
        if (part.length() > 0 && part.charAt(0) == '-') {
            startPos = 1;
        } else {
            startPos = 0;
        }

        int dashPos = part.indexOf('-', startPos);
        
        if (dashPos == -1) {
            return MAX_PART_LEN + 1;
        }
        
        return dashPos;
    }

    private static void displayNum(String part, String partFlag) {
        int num = Integer.parseInt(part.trim());
        displayEditedNum(partFlag, num);
    }

    private static void displayRange(String part, int dashPos, String partFlag) {
        String startStr = part.substring(0, dashPos).trim();
        String endStr = part.substring(dashPos + 1).trim();
        
        int startNum = Integer.parseInt(startStr);
        int endNum = Integer.parseInt(endStr);

        for (int num = startNum; num < endNum; num++) {
            displayEditedNum("N", num);
        }

        displayEditedNum(partFlag, endNum);
    }

    private static void displayEditedNum(String hideCommaFlag, int editedNum) {
        System.out.print(editedNum);
        if (!hideCommaFlag.equals("Y")) {
            System.out.print(", ");
        }
    }
}