import java.util.Scanner;

public class cs01a {
    private static final String MYNAME = "cs01a";
    private static long wsCount = 0;
    private static long wsRecCount = 0;
    private static long depthLen = 0;
    private static long holdDepth = 0;
    private static long currDepth = 0;
    private static String wsInptDepth = "        ";
    private static boolean inptDataEof = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        readInptData(scanner);
        holdDepth = currDepth;

        while (!inptDataEof) {
            if (currDepth > holdDepth) {
                wsCount++;
            }
            holdDepth = currDepth;
            readInptData(scanner);
        }

        scanner.close();

        System.out.println(MYNAME + " measurements larger than the previous measurement " + wsCount);
        System.out.println(MYNAME + " records read " + wsRecCount);
    }

    private static void readInptData(Scanner scanner) {
        wsInptDepth = "        ";
        
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            wsInptDepth = String.format("%-8s", line).substring(0, 8);
            
            wsRecCount++;
            
            String byte4 = wsInptDepth.substring(3, 4);
            if (isNumeric(byte4)) {
                depthLen = 4;
            } else {
                depthLen = 3;
            }
            
            String depthStr = wsInptDepth.substring(0, (int)depthLen).trim();
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