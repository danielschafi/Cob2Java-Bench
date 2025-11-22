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
            int nextComma = rangeStr.indexOf(',', commaPos - 1);
            if (nextComma == -1 || nextComma >= RANGE_STR_LEN) {
                lastPart = true;
                nextComma = rangeStr.length();
            }
            
            String part = rangeStr.substring(commaPos - 1, nextComma);
            commaPos = nextComma + 2;
            
            if (part.isEmpty()) continue;
            
            int dashPos = findDashPosition(part);
            
            if (dashPos >= MAX_PART_LEN || dashPos <= 0) {
                displayNumber(part, lastPart ? "Y" : "N");
            } else {
                displayRange(part, dashPos, lastPart ? "Y" : "N");
            }
        }
        
        System.out.println();
    }
    
    private static int findDashPosition(String part) {
        int startPos = 0;
        if (part.startsWith("-")) {
            startPos = 1;
        }
        
        int dashPos = part.indexOf('-', startPos);
        return dashPos == -1 ? part.length() + 1 : dashPos + 1;
    }
    
    private static void displayNumber(String part, String flag) {
        part = part.trim();
        displayEditedNum(flag, part);
    }
    
    private static void displayRange(String part, int dashPos, String flag) {
        String startStr = part.substring(0, dashPos - 1).trim();
        String endStr = part.substring(dashPos).trim();
        
        int startNum = Integer.parseInt(startStr);
        int endNum = Integer.parseInt(endStr);
        
        for (int i = startNum; i < endNum; i++) {
            displayEditedNum("N", String.valueOf(i));
        }
        
        displayEditedNum(flag, String.valueOf(endNum));
    }
    
    private static void displayEditedNum(String hideCommaFlag, String editedNum) {
        editedNum = editedNum.trim();
        System.out.print(editedNum);
        if (!"Y".equals(hideCommaFlag)) {
            System.out.print(", ");
        }
    }
}