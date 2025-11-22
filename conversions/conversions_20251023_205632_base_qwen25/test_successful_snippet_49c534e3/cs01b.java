import java.util.Scanner;

public class cs01b {
    private static final String MYNAME = "cs01b";
    private static int wsCount = 0;
    private static int wsRecCount = 0;
    private static int depthLen = 0;
    private static int depthIndxMax = 0;
    private static int slidingCount = 0;
    private static int whichIndx = 0;
    private static int whichIndxR = 0;
    private static int holdDepth = 0;
    private static int currDepth = 0;
    private static String processType = "";
    private static String wsInptDepth = "        ";
    private static char inptDataEofSw = 'N';
    private static boolean processTest = false;
    private static final int[] slidingDepth = new int[2000];
    private static int depthIndx1 = 1;
    private static int depthIndx2 = 2;
    private static int depthIndx3 = 3;

    public static void main(String[] args) {
        if (args.length > 0) {
            processType = args[0].toUpperCase();
            processTest = processType.equals("TEST");
        }

        if (processTest) {
            System.out.println("TRACE READY");
        }

        Scanner scanner = new Scanner(System.in);

        readInptData(scanner);
        depthIndx1 = 1;
        depthIndx2 = 2;
        depthIndx3 = 3;

        while (inptDataEofSw != 'Y') {
            slidingCount++;
            switch (slidingCount) {
                case 1:
                    incrementGroup1();
                    break;
                case 2:
                    incrementGroup2();
                    break;
                case 3:
                    incrementGroup3();
                    depthIndxMax++;
                    break;
                default:
                    depthIndxMax++;
                    whichIndx = slidingCount / 3;
                    whichIndxR = slidingCount % 3;
                    switch (whichIndxR) {
                        case 1:
                            depthIndx1 += 3;
                            break;
                        case 2:
                            depthIndx2 += 3;
                            break;
                        case 0:
                            depthIndx3 += 3;
                            break;
                    }
                    if (depthIndx1 > 2000) {
                        System.out.println(MYNAME + " internal table overflow");
                        scanner.close();
                        System.exit(8);
                    }
                    incrementGroup3();
                    break;
            }
            readInptData(scanner);
        }

        scanner.close();

        holdDepth = slidingDepth[0];

        for (int depthIndx = 0; depthIndx < depthIndxMax; depthIndx++) {
            if (slidingDepth[depthIndx] > holdDepth) {
                wsCount++;
            }
            holdDepth = slidingDepth[depthIndx];
        }

        System.out.println(MYNAME + " measurements larger than the previous measurement " + wsCount);
        System.out.println(MYNAME + " records read " + wsRecCount);
        System.out.println(MYNAME + " sliding window groups " + depthIndxMax);

        if (processTest) {
            System.out.println("TRACE RESET");
            for (int depthIndx = 0; depthIndx < depthIndxMax; depthIndx++) {
                System.out.println((depthIndx + 1) + " " + slidingDepth[depthIndx]);
            }
        }
    }

    private static void incrementGroup1() {
        slidingDepth[depthIndx1 - 1] += currDepth;
    }

    private static void incrementGroup2() {
        incrementGroup1();
        slidingDepth[depthIndx2 - 1] += currDepth;
    }

    private static void incrementGroup3() {
        incrementGroup2();
        slidingDepth[depthIndx3 - 1] += currDepth;
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
            inptDataEofSw = 'Y';
        }
    }
}