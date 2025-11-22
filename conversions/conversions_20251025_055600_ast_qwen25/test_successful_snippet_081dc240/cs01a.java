import java.util.Scanner;

public class cs01a {
    private static final String MYNAME = "cs01a";
    private static int wsCount = 0;
    private static int wsRecCount = 0;
    private static int depthLen = 0;
    private static int holdDepth = 0;
    private static int currDepth = 0;
    private static String wsInptDepth = "        ";
    private static char wsInptByte4;
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
            wsInptDepth = scanner.nextLine();
            wsRecCount++;
            if (Character.isDigit(wsInptDepth.charAt(3))) {
                depthLen = 4;
            } else {
                depthLen = 3;
            }
            currDepth = Integer.parseInt(wsInptDepth.substring(0, depthLen));
        } else {
            inptDataEof = true;
        }
    }
}