import java.util.*;

public class UnstringExample {
    static class WorkingStorage {
        String wsSourceStr = "";
        String wsDestStr = "";
        String wsPart1 = "";
        String wsPart2 = "";
        char wsDelimiter = '|';
        int wsSingleFieldsFilled = 0;
        String wsSingleDestStr = "";
        char wsSingleDelimiter = ' ';
        int wsSingleCharCount = 0;
        int wsMultiFieldsFilled = 0;
        String[] wsMultiDestStr = new String[6];
        char[] wsMultiDelimiter = new char[6];
        int[] wsMultiCharCount = new int[6];
        int wsPointer = 0;
        String wsSourceNum = "";
        int[] wsDestNum = new int[3];
        int wsMultiIdx = 0;

        WorkingStorage() {
            for (int i = 0; i < 6; i++) {
                wsMultiDestStr[i] = "";
                wsMultiDelimiter[i] = ' ';
                wsMultiCharCount[i] = 0;
            }
            for (int i = 0; i < 3; i++) {
                wsDestNum[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        WorkingStorage ws = new WorkingStorage();
        
        ws.wsSourceStr = "Hello World";

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 1 : SIMPLE UNSTRING");
        System.out.println();
        System.out.println("SOURCE STRING: " + ws.wsSourceStr);

        String[] parts = ws.wsSourceStr.split(" ", 2);
        ws.wsPart1 = parts.length > 0 ? padRight(parts[0], 15) : padRight("", 15);
        ws.wsPart2 = parts.length > 1 ? padRight(parts[1], 15) : padRight("", 15);

        System.out.println("PART1: " + ws.wsPart1);
        System.out.println("PART2: " + ws.wsPart2);

        ws.wsPointer = 1;

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 2 : UNSTRING MULTIPLE TIMES INTO SAME DEST.");
        System.out.println();
        System.out.println("SOURCE STRING: " + ws.wsSourceStr);

        String[] tokens = ws.wsSourceStr.split(" +");
        for (int i = 0; i < 2 && i < tokens.length; i++) {
            ws.wsPart1 = padRight(tokens[i], 15);
            ws.wsPointer = ws.wsSourceStr.indexOf(tokens[i]) + tokens[i].length() + 1;
            System.out.println("Successfully unstrung.");
            System.out.println("PART VALUE: " + ws.wsPart1);
            System.out.println("POINTER: " + ws.wsPointer);
        }

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 3 : UNSTRING INTO EXPLICIT FIELDS");

        ws.wsPointer = 1;

        System.out.println();
        System.out.println("SOURCE STRING: " + ws.wsSourceStr);

        parts = ws.wsSourceStr.split(" ", 2);
        ws.wsPart1 = parts.length > 0 ? padRight(parts[0], 15) : padRight("", 15);
        ws.wsPart2 = parts.length > 1 ? padRight(parts[1], 15) : padRight("", 15);
        ws.wsPointer = ws.wsSourceStr.length() + 1;

        System.out.println("Successfully unstrung.");
        System.out.println("PART1: " + ws.wsPart1);
        System.out.println("PART2: " + ws.wsPart2);
        System.out.println("POINTER: " + ws.wsPointer);

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 4 : UNSTRING WITH MULTIPLE DELIMITERS ");

        ws.wsPointer = 1;
        ws.wsSourceStr = "A<B<CD>E%FG!HIJ|KL!MN>OP#QR!ST";

        System.out.println();
        System.out.println("SOURCE STRING: " + ws.wsSourceStr);

        String[] delimiters = {"<", ">", "!", "|"};
        String remaining = ws.wsSourceStr;
        ws.wsSingleFieldsFilled = 0;

        while (!remaining.isEmpty()) {
            int minIdx = Integer.MAX_VALUE;
            char foundDelim = ' ';

            for (String delim : delimiters) {
                int idx = remaining.indexOf(delim);
                if (idx >= 0 && idx < minIdx) {
                    minIdx = idx;
                    foundDelim = delim.charAt(0);
                }
            }

            if (minIdx == Integer.MAX_VALUE) {
                ws.wsSingleDestStr = padRight(remaining, 5);
                ws.wsSingleDelimiter = ' ';
                ws.wsSingleCharCount = remaining.length();
                remaining = "";
            } else {
                ws.wsSingleDestStr = padRight(remaining.substring(0, minIdx), 5);
                ws.wsSingleDelimiter = foundDelim;
                ws.wsSingleCharCount = minIdx;
                remaining = remaining.substring(minIdx + 1);
            }

            ws.wsSingleFieldsFilled++;
            ws.wsPointer = ws.wsSourceStr.length() - remaining.length();

            System.out.println();
            System.out.println("VALUE: " + ws.wsSingleDestStr);
            System.out.println("DELIMITER: " + ws.wsSingleDelimiter);
            System.out.println("CHAR COUNT:" + ws.wsSingleCharCount);
            System.out.println("CURRENT POINTER: " + ws.wsPointer);
            System.out.println("TOTAL FIELDS FILLED: " + ws.wsSingleFieldsFilled);
            System.out.println("-------------------------------------------");
        }

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 5 : UNSTRING WITH MULTIPLE DELIMITERS INTO MULTIPLE DESTINATIONS");

        ws.wsSourceStr = "A<B<CD>EFG!HIJ|KLMN>O";

        System.out.println();
        System.out.println("SOURCE STRING: " + ws.wsSourceStr);

        remaining = ws.wsSourceStr;
        ws.wsMultiFieldsFilled = 0;
        int idx = 0;

        while (!remaining.isEmpty() && idx < 6) {
            int minIdx = Integer.MAX_VALUE;
            char foundDelim = ' ';

            for (String delim : delimiters) {
                int pos = remaining.indexOf(delim);
                if (pos >= 0 && pos < minIdx) {
                    minIdx = pos;
                    foundDelim = delim.charAt(0);
                }
            }

            if (minIdx == Integer.MAX_VALUE) {
                ws.wsMultiDestStr[idx] = padRight(remaining, 5);
                ws.wsMultiDelimiter[idx] = ' ';
                ws.wsMultiCharCount[idx] = remaining.length();
                remaining = "";
            } else {
                ws.wsMultiDestStr[idx] = padRight(remaining.substring(0, minIdx), 5);
                ws.wsMultiDelimiter[idx] = foundDelim;
                ws.wsMultiCharCount[idx] = minIdx;
                remaining = remaining.substring(minIdx + 1);
            }

            ws.wsMultiFieldsFilled++;
            idx++;
        }

        for (int i