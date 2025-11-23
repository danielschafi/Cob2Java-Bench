public class UnstringExample {
    private static String wsSourceStr = "";
    private static String wsPart1 = "";
    private static String wsPart2 = "";
    private static char wsDelimiter = '|';
    private static int wsSingleFieldsFilled = 0;
    private static String wsSingleDestStr = "";
    private static char wsSingleDelimiter = '\0';
    private static int wsSingleCharCount = 0;
    private static int wsMultiFieldsFilled = 0;
    private static String[] wsMultiDestStr = new String[6];
    private static char[] wsMultiDelimiter = new char[6];
    private static int[] wsMultiCharCount = new int[6];
    private static int wsPointer = 1;
    private static double wsSourceNum = 0.0;
    private static int[] wsDestNum = new int[3];

    public static void main(String[] args) {
        wsSourceStr = "Hello World";

        // EXAMPLE 1: SIMPLE UNSTRING
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 1 : SIMPLE UNSTRING");
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        int spaceIndex = wsSourceStr.indexOf(' ');
        if (spaceIndex != -1) {
            wsPart1 = wsSourceStr.substring(0, spaceIndex);
            wsPart2 = wsSourceStr.substring(spaceIndex + 1);
        } else {
            wsPart1 = wsSourceStr;
            wsPart2 = "";
        }

        System.out.println("PART1: " + wsPart1);
        System.out.println("PART2: " + wsPart2);

        // EXAMPLE 2: UNSTRING MULTIPLE TIMES INTO SAME DEST.
        wsPointer = 1;

        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 2 : UNSTRING MULTIPLE TIMES INTO SAME DEST.");

        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        for (int i = 0; i < 2; i++) {
            int startIndex = wsPointer - 1;
            int endIndex = wsSourceStr.indexOf(' ', startIndex);
            if (endIndex == -1) {
                endIndex = wsSourceStr.length();
            }
            
            if (startIndex < wsSourceStr.length()) {
                wsPart1 = wsSourceStr.substring(startIndex, endIndex);
                wsPointer = endIndex + 1;
                System.out.println("Successfully unstrung.");
            } else {
                System.out.println("ERROR: OVERFLOW");
            }

            System.out.println("PART VALUE: " + wsPart1);
            System.out.println("POINTER: " + wsPointer);
        }

        // EXAMPLE 3: UNSTRING INTO EXPLICIT FIELDS
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 3 : UNSTRING INTO EXPLICIT FIELDS");

        wsPointer = 1;

        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        startIndex = wsPointer - 1;
        endIndex = wsSourceStr.indexOf(' ', startIndex);
        if (endIndex == -1) {
            endIndex = wsSourceStr.length();
        }
        
        if (startIndex < wsSourceStr.length()) {
            wsPart1 = wsSourceStr.substring(startIndex, endIndex);
            wsPointer = endIndex + 1;
            endIndex = wsSourceStr.indexOf(' ', wsPointer - 1);
            if (endIndex == -1) {
                endIndex = wsSourceStr.length();
            }
            if (wsPointer - 1 < wsSourceStr.length()) {
                wsPart2 = wsSourceStr.substring(wsPointer - 1, endIndex);
                wsPointer = endIndex + 1;
                System.out.println("Successfully unstrung.");
            } else {
                wsPart2 = "";
                System.out.println("ERROR: OVERFLOW");
            }
        } else {
            wsPart1 = "";
            wsPart2 = "";
            System.out.println("ERROR: OVERFLOW");
        }

        System.out.println("PART1: " + wsPart1);
        System.out.println("PART2: " + wsPart2);
        System.out.println("POINTER: " + wsPointer);

        // EXAMPLE 4: UNSTRING WITH MULTIPLE DELIMITERS
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 4 : UNSTRING WITH MULTIPLE DELIMITERS ");

        wsPointer = 1;
        wsSourceStr = "A<B<CD>E%FG!HIJ|KL!MN>OP#QR!ST";

        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        while (wsPointer <= wsSourceStr.length()) {
            int nextIndex = findNextDelimiter(wsSourceStr, wsPointer - 1);
            if (nextIndex == -1) {
                wsSingleDestStr = wsSourceStr.substring(wsPointer - 1);
                wsSingleCharCount = wsSingleDestStr.length();
                wsSingleDelimiter = '\0';
                wsPointer = wsSourceStr.length() + 1;
            } else {
                wsSingleDestStr = wsSourceStr.substring(wsPointer - 1, nextIndex);
                wsSingleCharCount = wsSingleDestStr.length();
                wsSingleDelimiter = wsSourceStr.charAt(nextIndex);
                wsPointer = nextIndex + 1;
            }

            System.out.println();
            System.out.println("VALUE: " + wsSingleDestStr);
            System.out.println("DELIMITER: " + wsSingleDelimiter);
            System.out.println("CHAR COUNT:" + wsSingleCharCount);
            System.out.println("CURRENT POINTER: " + wsPointer);
            System.out.println("TOTAL FIELDS FILLED: " + ++wsSingleFieldsFilled);
            System.out.println("-------------------------------------------");
        }

        // EXAMPLE 5: UNSTRING WITH MULTIPLE DELIMITERS INTO MULTIPLE DESTINATIONS
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 5 : UNSTRING WITH MULTIPLE DELIMITERS INTO MULTIPLE DESTINATIONS");

        wsSourceStr = "A<B<CD>EFG!HIJ|KLMN>O";

        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        String[] delimiters = {"<", ">", "!", "|"};
        int index = 0;
        int destIndex = 0;
        int pointer = 1;
        int fieldsFilled = 0;

        while (pointer <= wsSourceStr.length() && destIndex < 6) {
            int nextIndex = wsSourceStr.indexOf(delimiters[index], pointer - 1);
            if (nextIndex == -1) {
                wsMultiDestStr[destIndex] = wsSourceStr.substring(pointer - 1);
                wsMultiCharCount[destIndex] = wsMultiDestStr[destIndex].length();
                wsMultiDelimiter[destIndex] = '\0';
                pointer = wsSourceStr.length() + 1;
            } else {
                wsMultiDestStr[destIndex] = wsSourceStr.substring(pointer - 1, nextIndex);
                wsMultiCharCount[destIndex] = wsMultiDestStr[destIndex].length();
                wsMultiDelimiter[destIndex] = wsSourceStr.charAt(nextIndex);
                pointer = nextIndex + 1;
            }
            fieldsFilled++;
            destIndex++;
            index = (index + 1) % delimiters.length;
        }

        wsMultiFieldsFilled = fieldsFilled;

        for (int i = 0; i < 6; i++) {
            if (wsMultiDestStr[i] != null) {
                System.out.println();
                System.out.println("STRING NUMBER: " + (i + 1));
                System.out.println("VALUE: " + wsMultiDestStr[i]);
                System.out.println("DELIMITER: " + wsMultiDelimiter[i]);
                System.out.println("CHAR COUNT:" + wsMultiCharCount[i]);
                System.out.println("-------------------------------------------");
            }
        }

        System.out.println("TOTALS: ");
        System.out.println("FIELDS FILLED: " + wsMultiFieldsFilled);

        // EXAMPLE 6: UNSTRING FORMATTED NUMBER
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 6 : UNSTRING FORMATTED NUMBER");
        System.out.println();

        wsSourceNum = 123456.12;
        System.out.println("SOURCE VALUE: " + wsSourceNum);

        String numStr = String.valueOf(wsSourceNum);
        int commaIndex = numStr.indexOf(',');
        int dotIndex = numStr.indexOf('.');
        
        if (commaIndex != -1) {
            wsDestNum[0] = Integer.parseInt(numStr.substring(2, commaIndex));
            if (dotIndex != -1) {
                wsDestNum[1] = Integer.parseInt(numStr.substring(commaIndex + 1, dotIndex));
                wsDestNum[2] = Integer.parseInt(numStr.substring(dotIndex + 1));
            } else {
                wsDestNum[1] = Integer.parseInt(numStr.substring(commaIndex + 1));
                wsDestNum[2] = 0;
            }
        } else if (dotIndex != -1) {
            wsDestNum[0] = Integer.parseInt(numStr.substring(2, dotIndex));
            wsDestNum[1] = 0;
            wsDestNum[2] = Integer.parseInt(numStr.substring(dotIndex + 1));
        } else {
            wsDestNum[0] = Integer.parseInt(numStr.substring(2));
            wsDestNum[1] = 0;
            wsDestNum[2] = 0;