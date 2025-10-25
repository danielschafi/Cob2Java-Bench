import java.util.Scanner;

public class cs02b {
    private static final String MYNAME = "cs02b";
    private static int wsRecCount = 0;
    private static int currDepth = 0;
    private static int currHposn = 0;
    private static int currAim = 0;
    private static long currProduct = 0;
    private static int operationArg = 0;
    private static String processType = "";
    private static String operation = "";
    private static String operationArgX = "";
    private static String wsInpt = "";
    private static boolean inptDataEof = false;
    private static boolean processTest = false;
    private static boolean operationForward = false;
    private static boolean operationDown = false;
    private static boolean operationUp = false;

    public static void main(String[] args) {
        if (args.length > 0) {
            processType = args[0].toUpperCase();
        }

        if (processType.equals("TEST")) {
            processTest = true;
        }

        Scanner scanner = new Scanner(System.in);

        readInptData(scanner);

        while (!inptDataEof) {
            if (operationForward) {
                currHposn += operationArg;
                currDepth += operationArg * currAim;
            } else if (operationDown) {
                currAim += operationArg;
            } else if (operationUp) {
                currAim -= operationArg;
            } else {
                System.out.println(MYNAME + " invalid operation " + operation);
            }

            readInptData(scanner);
        }

        scanner.close();

        System.out.println(MYNAME + " current horizontal position " + currHposn);
        System.out.println(MYNAME + " current depth " + currDepth);
        currProduct = (long) currHposn * currDepth;
        System.out.println(MYNAME + " product of position and depth " + currProduct);
        System.out.println(MYNAME + " records read " + wsRecCount);
    }

    private static void readInptData(Scanner scanner) {
        wsInpt = "";
        operation = "";
        operationArgX = "";
        operationArg = 0;
        operationForward = false;
        operationDown = false;
        operationUp = false;

        if (scanner.hasNextLine()) {
            wsInpt = scanner.nextLine();
            wsRecCount++;

            String[] parts = wsInpt.trim().split("\\s+");
            if (parts.length >= 2) {
                operation = parts[0].toUpperCase();
                operationArgX = parts[1];
                operationArg = Integer.parseInt(operationArgX);
            }

            if (operation.equals("FORWARD")) {
                operationForward = true;
            } else if (operation.equals("DOWN")) {
                operationDown = true;
            } else if (operation.equals("UP")) {
                operationUp = true;
            }
        } else {
            inptDataEof = true;
        }
    }
}