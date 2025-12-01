import java.util.Scanner;

public class cs01a {
    private static final String MYNAME = "cs01a";
    private static long wsCount = 0;
    private static long wsRecCount = 0;
    private static long depthLen = 0;
    private static long holdDepth = 0;
    private static long currDepth = 0;
    private static String wsInptDepth = "        ";
    private static String wsInptByte4 = " ";
    private static boolean inptDataEof = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        read8010InptData(scanner);
        holdDepth = currDepth;

        while (!inptDataEof) {
            if (currDepth > holdDepth) {
                wsCount++;
            }
            holdDepth = currDepth;
            read8010InptData(scanner);
        }

        scanner.close();

        System.out.println(MYNAME + " measurements larger than the previous measurement " + wsCount);
        System.out.println(MYNAME + " records read " + wsRecCount);
    }

    private static void read8010InptData(Scanner scanner) {
        wsInptDepth = "        ";
        wsInptByte4 = " ";

        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() >= 8) {
                wsInptDepth = line.substring(0, 8);
            } else {
                wsInptDepth = String.format("%-8s", line);
            }

            if (wsInptDepth.length() >= 4) {
                wsInptByte4 = wsInptDepth.substring(3, 4);
            }

            wsRecCount++;

            if (isNumeric(wsInptByte4)) {
                depthLen = 4;
            } else {
                depthLen = 3;
            }

            String depthStr = wsInptDepth.substring(0, (int) depthLen).trim();
            try {
                currDepth = Long.parseLong(depthStr);
            } catch (NumberFormatException e) {
                currDepth = 0;
            }
        } else {
            inptDataEof = true;
        }
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}