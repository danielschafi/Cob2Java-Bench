import java.util.*;

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
            // Extract part using comma delimiter
            String part;
            int nextComma = rangeStr.indexOf(',', commaPos - 1);
            if (nextComma == -1 || nextComma >= RANGE_STR_LEN) {
                part = rangeStr.substring(commaPos - 1);
                lastPart = true;
            } else {
                part = rangeStr.substring(commaPos - 1, nextComma);
                commaPos = nextComma + 2; // Move past comma and space
            }
            
            // Check if part contains a dash
            int dashPos = part.indexOf('-');
            if (dashPos == -1) {
                // No dash, just display number
                displayEditedNum("Y", part);
            } else {
                // Has dash, process range
                int startNum = Integer.parseInt(part.substring(0, dashPos));
                int endNum = Integer.parseInt(part.substring(dashPos + 1));
                
                for (int i = startNum; i <= endNum; i++) {
                    displayEditedNum("N", String.valueOf(i));
                }
            }
        }
        
        System.out.println();
    }
    
    private static void displayEditedNum(String hideCommaFlag, String editedNum) {
        System.out.print(editedNum.trim());
        if (!"Y".equals(hideCommaFlag)) {
            System.out.print(", ");
        }
    }
}