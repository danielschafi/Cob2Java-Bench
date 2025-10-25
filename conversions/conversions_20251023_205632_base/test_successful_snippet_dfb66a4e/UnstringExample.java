import java.text.DecimalFormat;

public class UnstringExample {
    public static void main(String[] args) {
        String wsSourceStr = new String(new char[30]);
        String wsPart1 = new String(new char[15]);
        String wsPart2 = new String(new char[15]);
        char wsDelimiter = '|';
        String wsSingleDestStr = new String(new char[5]);
        char wsSingleDelimiter;
        int wsSingleCharCount;
        int wsSingleFieldsFilled;
        String[] wsMultiDestStr = new String[6];
        char[] wsMultiDelimiter = new char[6];
        int[] wsMultiCharCount = new int[6];
        int wsMultiFieldsFilled;
        int wsPointer;
        double wsSourceNum = 0.0;
        String[] wsDestNum = new String[3];

        for (int i = 0; i < wsMultiDestStr.length; i++) {
            wsMultiDestStr[i] = new String(new char[5]);
        }

        wsSourceStr = "Hello World";

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 1 : SIMPLE UNSTRING");
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        String[] parts = wsSourceStr.split(" ", 2);
        wsPart1 = parts[0];
        wsPart2 = parts.length > 1 ? parts[1] : "";

        System.out.println("PART1: " + wsPart1);
        System.out.println("PART2: " + wsPart2);

        wsPointer = 1;

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 2 : UNSTRING MULTIPLE TIMES INTO SAME DEST.");
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        for (int i = 0; i < 2; i++) {
            if (wsPointer <= wsSourceStr.length()) {
                parts = wsSourceStr.substring(wsPointer - 1).split(" ", 2);
                wsPart1 = parts[0];
                wsPointer += wsPart1.length() + 1;
                System.out.println("Successfully unstrung.");
            } else {
                System.out.println("ERROR: OVERFLOW");
            }

            System.out.println("PART VALUE: " + wsPart1);
            System.out.println("POINTER: " + wsPointer);
        }

        wsPointer = 1;

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 3 : UNSTRING INTO EXPLICIT FIELDS");
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        parts = wsSourceStr.split(" ", 2);
        wsPart1 = parts[0];
        wsPart2 = parts.length > 1 ? parts[1] : "";
        wsPointer += wsPart1.length() + wsPart2.length() + 1;

        System.out.println("Successfully unstrung.");
        System.out.println("PART1: " + wsPart1);
        System.out.println("PART2: " + wsPart2);
        System.out.println("POINTER: " + wsPointer);

        wsPointer = 1;
        wsSourceStr = "A<B<CD>E%FG!HIJ|KL!MN>OP#QR!ST";

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 4 : UNSTRING WITH MULTIPLE DELIMITERS ");
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        while (wsPointer <= wsSourceStr.length()) {
            int start = wsPointer - 1;
            int end = wsSourceStr.indexOfAny(new char[] {'<', '>', '!', wsDelimiter}, start);
            if (end == -1) {
                end = wsSourceStr.length();
            }
            wsSingleDestStr = wsSourceStr.substring(start, end).trim();
            wsSingleDelimiter = end < wsSourceStr.length() ? wsSourceStr.charAt(end) : '\0';
            wsSingleCharCount = wsSingleDestStr.length();
            wsSingleFieldsFilled++;
            wsPointer = end + 1;

            System.out.println();
            System.out.println("VALUE: " + wsSingleDestStr);
            System.out.println("DELIMITER: " + wsSingleDelimiter);
            System.out.println("CHAR COUNT: " + wsSingleCharCount);
            System.out.println("CURRENT POINTER: " + wsPointer);
            System.out.println("TOTAL FIELDS FILLED: " + wsSingleFieldsFilled);
            System.out.println("-------------------------------------------");
        }

        wsSourceStr = "A<B<CD>EFG!HIJ|KLMN>O";
        wsMultiFieldsFilled = 0;

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 5 : UNSTRING WITH MULTIPLE DELIMITERS INTO MULTIPLE DESTINATIONS");
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        String[] delimiters = new String[] {"<", ">", "!", String.valueOf(wsDelimiter)};
        String[] tokens = wsSourceStr.split(String.join("|", delimiters), 7);

        for (int i = 0; i < tokens.length && i < 6; i++) {
            wsMultiDestStr[i] = tokens[i].trim();
            wsMultiDelimiter[i] = i < tokens.length - 1 ? wsSourceStr.charAt(wsSourceStr.indexOf(tokens[i]) + tokens[i].length()) : '\0';
            wsMultiCharCount[i] = wsMultiDestStr[i].length();
            wsMultiFieldsFilled++;
        }

        for (int i = 0; i < wsMultiFieldsFilled; i++) {
            System.out.println();
            System.out.println("STRING NUMBER: " + (i + 1));
            System.out.println("VALUE: " + wsMultiDestStr[i]);
            System.out.println("DELIMITER: " + wsMultiDelimiter[i]);
            System.out.println("CHAR COUNT: " + wsMultiCharCount[i]);
            System.out.println("-------------------------------------------");
        }

        System.out.println("TOTALS: ");
        System.out.println("FIELDS FILLED: " + wsMultiFieldsFilled);

        wsSourceNum = 123456.12;
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        String formattedNumber = df.format(wsSourceNum).substring(1);

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 6 : UNSTRING FORMATTED NUMBER");
        System.out.println();
        System.out.println("SOURCE VALUE: " + formattedNumber);

        parts = formattedNumber.substring(1).split("[,.]", 4);
        wsDestNum[0] = parts[0];
        wsDestNum[1] = parts.length > 1 ? parts[1] : "";
        wsDestNum[2] = parts.length > 2 ? parts[2] : "";

        System.out.println("PART 1: " + wsDestNum[0]);
        System.out.println("PART 2: " + wsDestNum[1]);
        System.out.println("PART 3: " + wsDestNum[2]);
        System.out.println();
    }
}

class String {
    public static int indexOfAny(char[] chars, int start) {
        for (int i = start; i < length(); i++) {
            for (char c : chars) {
                if (charAt(i) == c) {
                    return i;
                }
            }
        }
        return -1;
    }
}