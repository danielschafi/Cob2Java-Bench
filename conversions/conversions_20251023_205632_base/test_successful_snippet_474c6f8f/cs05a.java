import java.util.Scanner;

public class cs05a {
    private static final String MYNAME = "cs05a";
    private static int wsRecCount = 0;
    private static int x1 = 0;
    private static int y1 = 0;
    private static int x2 = 0;
    private static int y2 = 0;
    private static int overlapCount = 0;
    private static int currAim = 0;
    private static long currProduct = 0;
    private static int operationArg = 0;
    private static String processType = "";
    private static String coord1 = "";
    private static String coord2 = "";
    private static String coordX1 = "";
    private static String coordY1 = "";
    private static String coordX2 = "";
    private static String coordY2 = "";
    private static String wsInpt = "";
    private static boolean inptDataEof = false;
    private static boolean processTest = false;
    private static int[][] theOceanFloor = new int[1001][1001];

    public static void main(String[] args) {
        if (args.length > 0) {
            processType = args[0].toUpperCase();
            processTest = processType.equals("TEST");
        }

        if (processTest) {
            System.out.println("READY TRACE");
        }

        for (int i = 0; i <= 1000; i++) {
            for (int j = 0; j <= 1000; j++) {
                theOceanFloor[i][j] = 0;
            }
        }

        Scanner scanner = new Scanner(System.in);

        readInptData(scanner);

        while (!inptDataEof) {
            loadInput();
        }

        scanner.close();

        dumpTheOceanFloor();

        countIntersections();

        System.out.println(MYNAME + " overlap count " + overlapCount);
        System.out.println(MYNAME + " records read " + wsRecCount);
    }

    private static void loadInput() {
        String[] parts = wsInpt.split("->");
        if (parts.length == 2) {
            coord1 = parts[0].trim();
            coord2 = parts[1].trim();
        }

        String[] coord1Parts = coord1.split(",");
        if (coord1Parts.length == 2) {
            coordX1 = coord1Parts[0].trim();
            coordY1 = coord1Parts[1].trim();
        }

        String[] coord2Parts = coord2.split(",");
        if (coord2Parts.length == 2) {
            coordX2 = coord2Parts[0].trim();
            coordY2 = coord2Parts[1].trim();
        }

        x1 = Integer.parseInt(coordX1) + 1;
        y1 = Integer.parseInt(coordY1) + 1;
        x2 = Integer.parseInt(coordX2) + 1;
        y2 = Integer.parseInt(coordY2) + 1;

        if (x1 == x2 && y1 < y2) {
            for (int yIndx1 = y1; yIndx1 <= y2; yIndx1++) {
                plotTheLine(x1, yIndx1);
            }
        } else if (x1 == x2 && y1 > y2) {
            for (int yIndx1 = y2; yIndx1 <= y1; yIndx1++) {
                plotTheLine(x1, yIndx1);
            }
        } else if (y1 == y2 && x1 < x2) {
            for (int xIndx1 = x1; xIndx1 <= x2; xIndx1++) {
                plotTheLine(xIndx1, y1);
            }
        } else if (y1 == y2 && x1 > x2) {
            for (int xIndx1 = x2; xIndx1 <= x1; xIndx1++) {
                plotTheLine(xIndx1, y1);
            }
        } else {
            System.out.println(MYNAME + " ignoring " + wsInpt);
        }

        readInptData(scanner);
    }

    private static void plotTheLine(int xIndx1, int yIndx1) {
        theOceanFloor[yIndx1][xIndx1]++;
    }

    private static void countIntersections() {
        if (processTest) {
            System.out.println("RESET TRACE");
        }

        for (int yIndx1 = 1; yIndx1 <= 1000; yIndx1++) {
            for (int xIndx1 = 1; xIndx1 <= 1000; xIndx1++) {
                if (theOceanFloor[yIndx1][xIndx1] > 1) {
                    overlapCount++;
                }
            }
        }

        if (processTest) {
            System.out.println("READY TRACE");
        }
    }

    private static void readInptData(Scanner scanner) {
        if (scanner.hasNextLine()) {
            wsInpt = scanner.nextLine();
            wsRecCount++;
        } else {
            inptDataEof = true;
        }
    }

    private static void dumpTheOceanFloor() {
        if (processTest) {
            System.out.println("RESET TRACE");
            for (int yIndx1 = 1; yIndx1 <= 10; yIndx1++) {
                System.out.print(MYNAME + " ");
                for (int xIndx1 = 1; xIndx1 <= 10; xIndx1++) {
                    System.out.print(theOceanFloor[yIndx1][xIndx1] + " ");
                }
                System.out.println();
            }
            System.out.println("READY TRACE");
        }
    }
}