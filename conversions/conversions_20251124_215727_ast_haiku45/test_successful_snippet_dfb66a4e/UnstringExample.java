import java.util.*;

public class UnstringExample {
    private String wsSourceStr = "";
    private String wsPart1 = "";
    private String wsPart2 = "";
    private String wsDelimiter = "|";
    private int wsSingleFieldsFilled = 0;
    private String wsSingleDestStr = "";
    private String wsSingleDelimiter = "";
    private int wsSingleCharCount = 0;
    private int wsMultiFieldsFilled = 0;
    private String[] wsMultiDestStr = new String[6];
    private String[] wsMultiDelimiter = new String[6];
    private int[] wsMultiCharCount = new int[6];
    private int wsPointer = 0;
    private String wsSourceNum = "";
    private int[] wsDestNum = new int[3];

    public UnstringExample() {
        for (int i = 0; i < 6; i++) {
            wsMultiDestStr[i] = "";
            wsMultiDelimiter[i] = "";
        }
    }

    public static void main(String[] args) {
        UnstringExample program = new UnstringExample();
        program.mainProcedure();
    }

    private void mainProcedure() {
        wsSourceStr = "Hello World";

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 1 : SIMPLE UNSTRING");
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        unstring1();

        System.out.println("PART1: " + wsPart1);
        System.out.println("PART2: " + wsPart2);

        wsPointer = 1;

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 2 : UNSTRING MULTIPLE TIMES INTO SAME DEST.");
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        for (int i = 0; i < 2; i++) {
            unstring2();
            System.out.println("PART VALUE: " + wsPart1);
            System.out.println("POINTER: " + wsPointer);
        }

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 3 : UNSTRING INTO EXPLICIT FIELDS");

        wsPointer = 1;
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        unstring3();

        System.out.println("PART1: " + wsPart1);
        System.out.println("PART2: " + wsPart2);
        System.out.println("POINTER: " + wsPointer);

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 4 : UNSTRING WITH MULTIPLE DELIMITERS ");

        wsPointer = 1;
        wsSourceStr = "A<B<CD>E%FG!HIJ|KL!MN>OP#QR!ST";

        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        while (wsPointer <= wsSourceStr.length()) {
            unstring4();
            System.out.println();
            System.out.println("VALUE: " + wsSingleDestStr);
            System.out.println("DELIMITER: " + wsSingleDelimiter);
            System.out.println("CHAR COUNT:" + wsSingleCharCount);
            System.out.println("CURRENT POINTER: " + wsPointer);
            System.out.println("TOTAL FIELDS FILLED: " + wsSingleFieldsFilled);
            System.out.println("-------------------------------------------");
        }

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 5 : UNSTRING WITH MULTIPLE DELIMITERS INTO MULTIPLE DESTINATIONS");

        wsSourceStr = "A<B<CD>EFG!HIJ|KLMN>O";

        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        unstring5();

        for (int i = 0; i < 6; i++) {
            System.out.println();
            System.out.println("STRING NUMBER: " + (i + 1));
            System.out.println("VALUE: " + wsMultiDestStr[i]);
            System.out.println("DELIMITER: " + wsMultiDelimiter[i]);
            System.out.println("CHAR COUNT:" + wsMultiCharCount[i]);
            System.out.println("-------------------------------------------");
        }

        System.out.println("TOTALS: ");
        System.out.println("FIELDS FILLED: " + wsMultiFieldsFilled);

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 6 : UNSTRING FORMATTED NUMBER");
        System.out.println();

        wsSourceNum = "$123,456.12";
        System.out.println("SOURCE VALUE: " + wsSourceNum);

        unstring6();

        System.out.println("PART 1: " + wsDestNum[0]);
        System.out.println("PART 2: " + wsDestNum[1]);
        System.out.println("PART 3: " + wsDestNum[2]);
        System.out.println();
    }

    private void unstring1() {
        String[] parts = wsSourceStr.split(" ");
        wsPart1 = parts.length > 0 ? parts[0] : "";
        wsPart2 = parts.length > 1 ? parts[1] : "";
    }

    private void unstring2() {
        String remaining = wsSourceStr.substring(wsPointer - 1);
        String[] parts = remaining.split("\\s+");
        if (parts.length > 0) {
            wsPart1 = parts[0];
            wsPointer += parts[0].length();
            while (wsPointer <= wsSourceStr.length() && Character.isWhitespace(wsSourceStr.charAt(wsPointer - 1))) {
                wsPointer++;
            }
        }
    }

    private void unstring3() {
        String[] parts = wsSourceStr.split("\\s+");
        wsPart1 = parts.length > 0 ? parts[0] : "";
        wsPart2 = parts.length > 1 ? parts[1] : "";
        wsPointer = wsSourceStr.length() + 1;
    }

    private void unstring4() {
        String remaining = wsSourceStr.substring(wsPointer - 1);
        int minIndex = remaining.length();
        char foundDelim = ' ';

        int idx = remaining.indexOf('<');
        if (idx >= 0 && idx < minIndex) {
            minIndex = idx;
            foundDelim = '<';
        }
        idx = remaining.indexOf('>');
        if (idx >= 0 && idx < minIndex) {
            minIndex = idx;
            foundDelim = '>';
        }
        idx = remaining.indexOf('!');
        if (idx >= 0 && idx < minIndex) {
            minIndex = idx;
            foundDelim = '!';
        }
        idx = remaining.indexOf('|');
        if (idx >= 0 && idx < minIndex) {
            minIndex = idx;
            foundDelim = '|';
        }

        if (minIndex < remaining.length()) {
            wsSingleDestStr = remaining.substring(0, minIndex);
            wsSingleDelimiter = String.valueOf(foundDelim);
            wsSingleCharCount = wsSingleDestStr.length();
            wsPointer += minIndex + 1;
        } else {
            wsSingleDestStr = remaining;
            wsSingleDelimiter = "";
            wsSingleCharCount = wsSingleDestStr.length();
            wsPointer = wsSourceStr.length() + 1;
        }
        wsSingleFieldsFilled++;
    }

    private void un