public class UnstringExample {
    private static String wsSourceStr = new String(new char[30]);
    private static String wsPart1 = new String(new char[15]);
    private static String wsPart2 = new String(new char[15]);
    private static char wsDelimiter = '|';
    private static int wsSingleFieldsFilled;
    private static String wsSingleDestStr = new String(new char[5]);
    private static char wsSingleDelimiter;
    private static int wsSingleCharCount;
    private static int wsMultiFieldsFilled;
    private static String[] wsMultiDestStr = new String[6];
    private static char[] wsMultiDelimiter = new char[6];
    private static int[] wsMultiCharCount = new int[6];
    private static int wsPointer;
    private static double wsSourceNum;
    private static int[] wsDestNum = new int[3];
    private static int wsMultiIdx;

    public static void main(String[] args) {
        // Initialize arrays
        for (int i = 0; i < 6; i++) {
            wsMultiDestStr[i] = new String(new char[5]);
        }

        // Set initial values
        wsSourceStr = "Hello World";
        
        // EXAMPLE 1: SIMPLE UNSTRING
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 1 : SIMPLE UNSTRING");
        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        String[] parts = wsSourceStr.trim().split("\\s+", 2);
        if (parts.length >= 1) {
            wsPart1 = parts[0];
        }
        if (parts.length >= 2) {
            wsPart2 = parts[1];
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
            String result = unstringWithPointer(wsSourceStr, " ", wsPart1, wsPointer);
            wsPart1 = result.substring(0, Math.min(result.length(), 15));
            wsPointer = Integer.parseInt(result.substring(Math.min(result.length(), 15)));
            
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

        String[] parts3 = wsSourceStr.trim().split("\\s+", 2);
        if (parts3.length >= 1) {
            wsPart1 = parts3[0];
        }
        if (parts3.length >= 2) {
            wsPart2 = parts3[1];
        }

        wsPointer = wsSourceStr.length() + 1;

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
            String[] result = unstringMultipleDelimiters(wsSourceStr, "<>!|", wsSingleDestStr, wsPointer);
            wsSingleDestStr = result[0];
            wsSingleDelimiter = result[1].charAt(0);
            wsSingleCharCount = Integer.parseInt(result[2]);
            wsPointer = Integer.parseInt(result[3]);
            wsSingleFieldsFilled++;

            System.out.println();
            System.out.println("VALUE: " + wsSingleDestStr);
            System.out.println("DELIMITER: " + wsSingleDelimiter);
            System.out.println("CHAR COUNT:" + wsSingleCharCount);
            System.out.println("CURRENT POINTER: " + wsPointer);
            System.out.println("TOTAL FIELDS FILLED: " + wsSingleFieldsFilled);
            System.out.println("-------------------------------------------");
        }

        // EXAMPLE 5: UNSTRING WITH MULTIPLE DELIMITERS INTO MULTIPLE DESTINATIONS
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 5 : UNSTRING WITH MULTIPLE DELIMITERS INTO MULTIPLE DESTINATIONS");

        wsSourceStr = "A<B<CD>EFG!HIJ|KLMN>O";

        System.out.println();
        System.out.println("SOURCE STRING: " + wsSourceStr);

        String[] result5 = unstringMultipleDestinations(wsSourceStr, "<>!|");
        wsMultiFieldsFilled = Integer.parseInt(result5[0]);

        for (int i = 1; i <= 6; i++) {
            if (i <= wsMultiFieldsFilled) {
                System.out.println();
                System.out.println("STRING NUMBER: " + i);
                System.out.println("VALUE: " + wsMultiDestStr[i-1]);
                System.out.println("DELIMITER: " + wsMultiDelimiter[i-1]);
                System.out.println("CHAR COUNT:" + wsMultiCharCount[i-1]);
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

        String numStr = String.valueOf(wsSourceNum).substring(1); // Remove '$' character
        String[] numParts = numStr.split("[,.]", 3);
        if (numParts.length >= 1) {
            wsDestNum[0] = Integer.parseInt(numParts[0]);
        }
        if (numParts.length >= 2) {
            wsDestNum[1] = Integer.parseInt(numParts[1]);
        }
        if (numParts.length >= 3) {
            wsDestNum[2] = Integer.parseInt(numParts[2]);
        }

        System.out.println("PART 1: " + wsDestNum[0]);
        System.out.println("PART 2: " + wsDestNum[1]);
        System.out.println("PART 3: " + wsDestNum[2]);
        System.out.println();
    }

    private static String unstringWithPointer(String source, String delimiter, String dest, int pointer) {
        // Simplified implementation
        String[] tokens = source.substring(pointer - 1).trim().split("\\s+", 2);
        if (tokens.length >= 1) {
            return tokens[0] + " " + (pointer + tokens[0].length() + 1);
        } else {
            return " " + pointer;
        }
    }

    private static String[] unstringMultipleDelimiters(String source, String delimiters, String dest, int pointer) {
        // Simplified implementation
        String[] result = new String[4];
        result[0] = "";
        result[1] = "";
        result[2] = "0";
        result[3] = "" + pointer;
        return result;
    }

    private static String[] unstringMultipleDestinations(String source, String delimiters) {
        // Simplified implementation
        String[] result = new String[7];
        result[0] = "0";
        for (int i = 1; i <= 6; i++) {
            result[i] = "";
        }
        return result;
    }
}