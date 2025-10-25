import java.util.Scanner;

public class cs10a {
    private static final String MYNAME = "cs10a";
    private static int wsRecCount = 0;
    private static int stackPtr = 0;
    private static int charPtr = 0;
    private static int fileScore = 0;
    private static String processType = "    ";
    private static char theChar = ' ';
    private static boolean theCharIsOpen;
    private static boolean theCharIsClose;
    private static boolean inptDataEof = false;
    private static boolean processTest = false;
    private static boolean badRecord = false;
    private static char[] stack = new char[256];
    private static char[] wsInpt = new char[4096];

    public static void main(String[] args) {
        if (args.length > 0) {
            processType = args[0].toUpperCase();
            processTest = processType.equals("TEST");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println(MYNAME + " " + java.time.LocalDate.now());

        readInptData(scanner);

        while (!inptDataEof) {
            processInput();
        }

        scanner.close();
        System.out.println(MYNAME + " file score      " + fileScore);
        System.out.println(MYNAME + " records read    " + wsRecCount);
    }

    private static void processInput() {
        badRecord = false;
        for (charPtr = 0; charPtr < wsInpt.length && wsInpt[charPtr] != ' ' && !badRecord; charPtr++) {
            theChar = wsInpt[charPtr];
            theCharIsOpen = theChar == '(' || theChar == '[' || theChar == '{' || theChar == '<';
            theCharIsClose = theChar == ')' || theChar == ']' || theChar == '}' || theChar == '>';

            if (theCharIsOpen) {
                stack[stackPtr++] = theChar;
            } else if (stackPtr == 0) {
                System.out.println(MYNAME + " stack pointer 0");
                badRecord = true;
            } else {
                if (processTest) {
                    System.out.println(MYNAME + " " + theChar + " " + stack[stackPtr - 1]);
                }
                if ((stack[stackPtr - 1] == '(' && theChar == ')') ||
                    (stack[stackPtr - 1] == '[' && theChar == ']') ||
                    (stack[stackPtr - 1] == '{' && theChar == '}') ||
                    (stack[stackPtr - 1] == '<' && theChar == '>')) {
                    stackPtr--;
                } else {
                    badRecord = true;
                }
            }
        }

        if (badRecord) {
            if (processTest) {
                System.out.println(MYNAME + " expected close for " + stack[stackPtr - 1] + " but found " + theChar + " instead");
            }
            switch (theChar) {
                case ')':
                    fileScore += 3;
                    break;
                case ']':
                    fileScore += 57;
                    break;
                case '}':
                    fileScore += 1197;
                    break;
                case '>':
                    fileScore += 25137;
                    break;
            }
        }

        readInptData(scanner);
    }

    private static void readInptData(Scanner scanner) {
        for (int i = 0; i < wsInpt.length; i++) {
            wsInpt[i] = ' ';
        }
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int i = 0; i < line.length() && i < wsInpt.length; i++) {
                wsInpt[i] = line.charAt(i);
            }
            wsRecCount++;
        } else {
            inptDataEof = true;
        }
    }
}