```java
import java.util.Scanner;

public class ExpandRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rangeStr = scanner.nextLine();
        scanner.close();
        
        int commaPos = 0;
        boolean lastPart = false;
        
        while (!lastPart) {
            int nextCommaPos = rangeStr.indexOf(',', commaPos);
            String part;
            
            if (nextCommaPos == -1) {
                part = rangeStr.substring(commaPos).trim();
                lastPart = true;
            } else {
                part = rangeStr.substring(commaPos, nextCommaPos).trim();
                commaPos = nextCommaPos + 1;
            }
            
            if (part.isEmpty()) {
                continue;
            }
            
            int dashPos = findRangeDash(part);
            
            if (dashPos > part.length() - 1) {
                displayNum(part);
            } else {
                displayRange(part, dashPos);
            }
        }
        
        System.out.println();
    }
    
    private static int findRangeDash(String part) {
        int startPos = 0;
        if (part.length() > 0 && part.charAt(0) == '-') {
            startPos = 1;
        }
        
        int dashPos = part.indexOf('-', startPos);
        if (dashPos == -1) {
            return part.length();
        }
        return dashPos;
    }
    
    private static void displayNum(String part) {
        displayEditedNum(part, "Y");
    }
    
    private static void displayRange(String part, int dashPos) {
        String startStr = part.substring(0, dashPos);
        String endStr = part.substring(dashPos + 1);
        
        int startNum = Integer.parseInt(startStr.trim());
        int endNum = Integer.parseInt(endStr.trim());
        
        for (int num = startNum; num < endNum; num++) {
            displayEditedNum(String.valueOf(num), "N");
        }
        
        displayEditedNum(String.valueOf(endNum), "Y");
    }
    
    private static void displayEditedNum(String numStr, String hideCommaFlag) {
        String trimmed = numStr.trim();
        System.out.print(trimmed);
        
        if (!hideCommaFlag.equals("Y")) {
            System.out.print(", ");
        }
    }
}