import java.util.Scanner;

public class cs03a {
    private static final String MYNAME = "cs03a";
    private static int wsRecCount = 0;
    private static int bitExponent = 0;
    private static int nbBits = 1;
    private static int bitToCount = 0;
    private static long currProduct = 0;
    private static long gammaRate = 0;
    private static long epsilonRate = 0;
    private static String gammaRateX = "                ";
    private static String processType = "    ";
    private static String epsilonRateX = "                ";
    private static String wsInpt = "                        ";
    private static boolean inptDataEof = false;
    private static boolean processTest = false;
    private static final int[][] bitCountTable = new int[16][2];

    public static void main(String[] args) {
        if (args.length > 0) {
            processType = args[0].toUpperCase();
            processTest = processType.equals("TEST");
        }

        if (processTest) {
            System.out.println(MYNAME + " TRACE READY");
        }

        Scanner scanner = new Scanner(System.in);

        readInptData(scanner);

        while (!inptDataEof) {
            for (bitToCount = 1; bitToCount <= nbBits; bitToCount++) {
                char bit = wsInpt.charAt(bitToCount - 1);
                switch (bit) {
                    case '0':
                        bitCountTable[bitToCount - 1][0]++;
                        break;
                    case '1':
                        bitCountTable[bitToCount - 1][1]++;
                        break;
                    default:
                        System.out.println(MYNAME + " bad bit " + bitToCount + " in record " + wsRecCount + " " + wsInpt);
                        break;
                }
            }
            readInptData(scanner);
        }

        scanner.close();

        if (processTest) {
            System.out.println(MYNAME + " TRACE RESET");
            for (bitToCount = 1; bitToCount <= nbBits; bitToCount++) {
                System.out.println(MYNAME + " COUNT-0(" + bitToCount + ") " + bitCountTable[bitToCount - 1][0]);
                System.out.println(MYNAME + " COUNT-1(" + bitToCount + ") " + bitCountTable[bitToCount - 1][1]);
            }
            System.out.println(MYNAME + " TRACE READY");
        }

        for (bitToCount = 1; bitToCount <= nbBits; bitToCount++) {
            if (bitCountTable[bitToCount - 1][0] > bitCountTable[bitToCount - 1][1]) {
                gammaRateX = gammaRateX.substring(0, bitToCount - 1) + '0' + gammaRateX.substring(bitToCount);
                epsilonRateX = epsilonRateX.substring(0, bitToCount - 1) + '1' + epsilonRateX.substring(bitToCount);
            } else {
                gammaRateX = gammaRateX.substring(0, bitToCount - 1) + '1' + gammaRateX.substring(bitToCount);
                epsilonRateX = epsilonRateX.substring(0, bitToCount - 1) + '0' + epsilonRateX.substring(bitToCount);
            }
        }

        System.out.println(MYNAME + " gamma rate   " + gammaRateX.trim());
        System.out.println(MYNAME + " epsilon rate " + epsilonRateX.trim());

        for (bitToCount = 1; bitToCount <= nbBits; bitToCount++) {
            bitExponent = nbBits - bitToCount;
            if (gammaRateX.charAt(bitToCount - 1) == '1') {
                gammaRate += Math.pow(2, bitExponent);
            }
            if (epsilonRateX.charAt(bitToCount - 1) == '1') {
                epsilonRate += Math.pow(2, bitExponent);
            }
        }

        System.out.println(MYNAME + " gamma rate   " + gammaRate);
        System.out.println(MYNAME + " epsilon rate " + epsilonRate);

        currProduct = gammaRate * epsilonRate;
        System.out.println(MYNAME + " product of gamma and epsilon " + currProduct);

        System.out.println(MYNAME + " records read " + wsRecCount);
    }

    private static void readInptData(Scanner scanner) {
        wsInpt = "                        ";
        if (scanner.hasNextLine()) {
            wsInpt = scanner.nextLine();
            wsRecCount++;
            if (wsRecCount == 1) {
                while (Character.isDigit(wsInpt.charAt(nbBits - 1))) {
                    nbBits++;
                }
                nbBits--;
                System.out.println(MYNAME + " number of bits " + nbBits);
            }
        } else {
            inptDataEof = true;
        }
    }
}