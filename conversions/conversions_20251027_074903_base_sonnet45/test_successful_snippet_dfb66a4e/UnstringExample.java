import java.util.ArrayList;
import java.util.List;

public class UnstringExample {
    
    private String wsSourceStr;
    private String wsPart1;
    private String wsPart2;
    private String wsDelimiter;
    private int wsSingleFieldsFilled;
    private String wsSingleDestStr;
    private String wsSingleDelimiter;
    private int wsSingleCharCount;
    private int wsMultiFieldsFilled;
    private List<MultiDestInfo> wsMultiDestInfo;
    private int wsPointer;
    private String wsSourceNum;
    private int[] wsDestNum;
    
    private static class MultiDestInfo {
        String wsMultiDestStr;
        String wsMultiDelimiter;
        int wsMultiCharCount;
        
        MultiDestInfo() {
            wsMultiDestStr = "";
            wsMultiDelimiter = "";
            wsMultiCharCount = 0;
        }
    }
    
    public UnstringExample() {
        wsSourceStr = "";
        wsPart1 = "";
        wsPart2 = "";
        wsDelimiter = "|";
        wsSingleFieldsFilled = 0;
        wsSingleDestStr = "";
        wsSingleDelimiter = "";
        wsSingleCharCount = 0;
        wsMultiFieldsFilled = 0;
        wsMultiDestInfo = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            wsMultiDestInfo.add(new MultiDestInfo());
        }
        wsPointer = 0;
        wsSourceNum = "";
        wsDestNum = new int[3];
    }
    
    private String padRight(String str, int length) {
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        return String.format("%-" + length + "s", str);
    }
    
    private String padLeft(String str, int length) {
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        return String.format("%" + length + "s", str);
    }
    
    private UnstringResult unstringSingle(String source, int pointer, String[] delimiters, boolean allSpaces) {
        UnstringResult result = new UnstringResult();
        result.pointer = pointer;
        result.overflow = false;
        result.value = "";
        result.delimiter = "";
        result.count = 0;
        
        if (pointer > source.length()) {
            result.overflow = false;
            return result;
        }
        
        int startPos = pointer - 1;
        int currentPos = startPos;
        
        if (allSpaces) {
            while (currentPos < source.length() && source.charAt(currentPos) == ' ') {
                currentPos++;
            }
            startPos = currentPos;
        }
        
        while (currentPos < source.length()) {
            boolean foundDelimiter = false;
            String matchedDelimiter = "";
            
            for (String delim : delimiters) {
                if (currentPos + delim.length() <= source.length()) {
                    String substr = source.substring(currentPos, currentPos + delim.length());
                    if (substr.equals(delim)) {
                        foundDelimiter = true;
                        matchedDelimiter = delim;
                        break;
                    }
                }
            }
            
            if (foundDelimiter) {
                result.value = source.substring(startPos, currentPos);
                result.delimiter = matchedDelimiter;
                result.count = result.value.length();
                result.pointer = currentPos + matchedDelimiter.length() + 1;
                
                if (allSpaces) {
                    while (result.pointer <= source.length() && 
                           result.pointer - 1 < source.length() && 
                           source.charAt(result.pointer - 1) == ' ') {
                        result.pointer++;
                    }
                }
                
                return result;
            }
            
            currentPos++;
        }
        
        result.value = source.substring(startPos);
        result.count = result.value.length();
        result.pointer = source.length() + 1;
        
        return result;
    }
    
    private static class UnstringResult {
        String value;
        String delimiter;
        int count;
        int pointer;
        boolean overflow;
    }
    
    public void run() {
        wsSourceStr = padRight("Hello World", 30);
        
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 1 : SIMPLE UNSTRING");
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);
        
        String[] parts = wsSourceStr.trim().split(" ", 2);
        wsPart1 = padRight(parts.length > 0 ? parts[0] : "", 15);
        wsPart2 = padRight(parts.length > 1 ? parts[1] : "", 15);
        
        System.out.println("PART1: " + wsPart1);
        System.out.println("PART2: " + wsPart2);
        
        wsPointer = 1;
        
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 2 : UNSTRING MULTIPLE TIMES INTO SAME DEST.");
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);
        
        for (int i = 0; i < 2; i++) {
            UnstringResult result = unstringSingle(wsSourceStr, wsPointer, new String[]{" "}, true);
            
            if (result.pointer <= wsSourceStr.length() && !result.value.isEmpty()) {
                System.out.println("ERROR: OVERFLOW");
            } else {
                System.out.println("Successfully unstrung.");
            }
            
            wsPart1 = padRight(result.value, 15);
            wsPointer = result.pointer;
            
            System.out.println("PART VALUE: " + wsPart1);
            System.out.println("POINTER: " + wsPointer);
        }
        
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 3 : UNSTRING INTO EXPLICIT FIELDS");
        
        wsPointer = 1;
        
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);
        
        parts = wsSourceStr.trim().split("\\s+", 2);
        wsPart1 = padRight(parts.length > 0 ? parts[0] : "", 15);
        wsPart2 = padRight(parts.length > 1 ? parts[1] : "", 15);
        wsPointer = wsSourceStr.trim().length() + 1;
        
        System.out.println("Successfully unstrung.");
        System.out.println("PART1: " + wsPart1);
        System.out.println("PART2: " + wsPart2);
        System.out.println("POINTER: " + wsPointer);
        
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 4 : UNSTRING WITH MULTIPLE DELIMITERS ");
        
        wsPointer = 1;
        wsSourceStr = padRight("A<B<CD>E%FG!HIJ|KL!MN>OP#QR!ST", 30);
        
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);
        
        wsSingleFieldsFilled = 0;
        
        while (wsPointer <= wsSourceStr.trim().length()) {
            UnstringResult result = unstringSingle(wsSourceStr.trim(), wsPointer, 
                new String[]{"<", ">", "!", wsDelimiter}, false);
            
            w