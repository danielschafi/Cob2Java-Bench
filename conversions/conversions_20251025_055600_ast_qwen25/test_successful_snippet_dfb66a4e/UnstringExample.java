import java.util.Arrays;

public class UnstringExample {
    public static void main(String[] args) {
        String wsSourceStr = "Hello World";
        String wsPart1 = new String(new char[15]).replace('\0', ' ');
        String wsPart2 = new String(new char[15]).replace('\0', ' ');

        // Example 1
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 1 : SIMPLE UNSTRING");
        System.out.println();

        System.out.println("SOURCE STRING: " + wsSourceStr);

        String[] parts = wsSourceStr.split(" ", 2);
        wsPart1 = String.format("%-15s", parts[0]);
        wsPart2 = String.format("%-15s", parts.length > 1 ? parts[1] : "");

        System.out.println("PART1: " + wsPart1.trim());
        System.out.println("PART2: " + wsPart2.trim());

        // Example 2
        int wsPointer = 1;
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 2 : UNSTRING MULTIPLE TIMES INTO SAME DEST.");
        System.out.println();

        System.out.println("SOURCE STRING: " + wsSourceStr);

        for (int i = 0; i < 2; i++) {
            if (wsPointer <= wsSourceStr.length()) {
                int nextSpace = wsSourceStr.indexOf(' ', wsPointer - 1);
                if (nextSpace == -1) {
                    nextSpace = wsSourceStr.length();
                }
                wsPart1 = String.format("%-15s", wsSourceStr.substring(wsPointer - 1, nextSpace));
                wsPointer = nextSpace + 1;
                System.out.println("Successfully unstrung.");
            } else {
                System.out.println("ERROR: OVERFLOW");
            }

            System.out.println("PART VALUE: " + wsPart1.trim());
            System.out.println("POINTER: " + wsPointer);
        }

        // Example 3
        wsPointer = 1;
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 3 : UNSTRING INTO EXPLICIT FIELDS");
        System.out.println();

        System.out.println("SOURCE STRING: " + wsSourceStr);

        parts = wsSourceStr.split(" ", 2);
        wsPart1 = String.format("%-15s", parts[0]);
        wsPart2 = String.format("%-15s", parts.length > 1 ? parts[1] : "");
        wsPointer = wsSourceStr.length() + 1;

        System.out.println("PART1: " + wsPart1.trim());
        System.out.println("PART2: " + wsPart2.trim());
        System.out.println("POINTER: " + wsPointer);

        // Example 4
        wsPointer = 1;
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 4 : UNSTRING WITH MULTIPLE DELIMITERS ");
        System.out.println();

        wsSourceStr = "A<B<CD>E%FG!HIJ|KL!MN>OP#QR!ST";
        System.out.println("SOURCE STRING: " + wsSourceStr);

        String[] delimiters = {"<", ">", "!", "|"};
        int wsSingleFieldsFilled = 0;
        String wsSingleDestStr = new String(new char[5]).replace('\0', ' ');
        char wsSingleDelimiter = ' ';
        int wsSingleCharCount = 0;

        while (wsPointer <= wsSourceStr.length()) {
            int nextDelimiterIndex = wsSourceStr.length();
            for (String delimiter : delimiters) {
                int index = wsSourceStr.indexOf(delimiter, wsPointer - 1);
                if (index != -1 && index < nextDelimiterIndex) {
                    nextDelimiterIndex = index;
                    wsSingleDelimiter = delimiter.charAt(0);
                }
            }

            wsSingleDestStr = String.format("%-5s", wsSourceStr.substring(wsPointer - 1, nextDelimiterIndex));
            wsSingleCharCount = nextDelimiterIndex - wsPointer + 1;
            wsPointer = nextDelimiterIndex + 1;
            wsSingleFieldsFilled++;

            System.out.println();
            System.out.println("VALUE: " + wsSingleDestStr.trim());
            System.out.println("DELIMITER: " + wsSingleDelimiter);
            System.out.println("CHAR COUNT: " + wsSingleCharCount);
            System.out.println("CURRENT POINTER: " + wsPointer);
            System.out.println("TOTAL FIELDS FILLED: " + wsSingleFieldsFilled);
            System.out.println("-------------------------------------------");
        }

        // Example 5
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 5 : UNSTRING WITH MULTIPLE DELIMITERS INTO MULTIPLE DESTINATIONS");
        System.out.println();

        wsSourceStr = "A<B<CD>EFG!HIJ|KLMN>O";
        System.out.println("SOURCE STRING: " + wsSourceStr);

        String[] wsMultiDestStr = new String[6];
        char[] wsMultiDelimiter = new char[6];
        int[] wsMultiCharCount = new int[6];
        Arrays.fill(wsMultiDestStr, new String(new char[5]).replace('\0', ' '));
        Arrays.fill(wsMultiDelimiter, ' ');
        Arrays.fill(wsMultiCharCount, 0);

        wsPointer = 1;
        int wsMultiFieldsFilled = 0;

        while (wsPointer <= wsSourceStr.length()) {
            int nextDelimiterIndex = wsSourceStr.length();
            for (String delimiter : delimiters) {
                int index = wsSourceStr.indexOf(delimiter, wsPointer - 1);
                if (index != -1 && index < nextDelimiterIndex) {
                    nextDelimiterIndex = index;
                    wsMultiDelimiter[wsMultiFieldsFilled] = delimiter.charAt(0);
                }
            }

            wsMultiDestStr[wsMultiFieldsFilled] = String.format("%-5s", wsSourceStr.substring(wsPointer - 1, nextDelimiterIndex));
            wsMultiCharCount[wsMultiFieldsFilled] = nextDelimiterIndex - wsPointer + 1;
            wsPointer = nextDelimiterIndex + 1;
            wsMultiFieldsFilled++;
        }

        for (int i = 0; i < wsMultiFieldsFilled; i++) {
            System.out.println();
            System.out.println("STRING NUMBER: " + (i + 1));
            System.out.println("VALUE: " + wsMultiDestStr[i].trim());
            System.out.println("DELIMITER: " + wsMultiDelimiter[i]);
            System.out.println("CHAR COUNT: " + wsMultiCharCount[i]);
            System.out.println("-------------------------------------------");
        }

        System.out.println("TOTALS: ");
        System.out.println("FIELDS FILLED: " + wsMultiFieldsFilled);

        // Example 6
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 6 : UNSTRING FORMATTED NUMBER");
        System.out.println();

        double wsSourceNum = 123456.12;
        System.out.println("SOURCE VALUE: " + wsSourceNum);

        String sourceNumStr = String.format("%.2f", wsSourceNum).substring(1);
        String[] wsDestNum = sourceNumStr.split("[,.]");

        System.out.println("PART 1: " + wsDestNum[0]);
        System.out.println("PART 2: " + wsDestNum[1]);
        System.out.println("PART 3: " + wsDestNum[2]);
    }
}