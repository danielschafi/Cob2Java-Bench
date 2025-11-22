import java.util.Scanner;

public class cs05a {
    private static final int MAX_SIZE = 1000;
    private static final String MYNAME = "cs05a";
    private static int wsRecCount = 0;
    private static int x1, y1, x2, y2;
    private static int overlapCount = 0;
    private static int currAim = 0;
    private static long currProduct = 0;
    private static int operationArg = 0;
    private static String processType = "    ";
    private static String coord1 = "        ";
    private static String coord2 = "        ";
    private static String coordX1 = "    ";
    private static String coordY1 = "    ";
    private static String coordX2 = "    ";
    private static String coordY2 = "    ";
    private static String wsInpt = "                        ";
    private static boolean inptDataEof = false;
    private static boolean processTest = false;
    private static int[][] theOceanFloor = new int[MAX_SIZE][MAX_SIZE];

    public static void main(String[] args) {
        if (args.length > 0) {
            processType = args[0].toUpperCase();
        }

        if (processType.equals("TEST")) {
            // Ready trace
        }

        initializeTheOceanFloor();

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

    private static void initializeTheOceanFloor() {
        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                theOceanFloor[i][j] = 0;
            }
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

    private static void loadInput() {
        String[] parts = wsInpt.split("->");
        if (parts.length == 2) {
            coord1 = parts[0].trim();
            coord2 = parts[1].trim();
        } else {
            coord1 = "";
            coord2 = "";
        }

        String[] coord1Parts = coord1.split(",");
        if (coord1Parts.length == 2) {
            coordX1 = coord1Parts[0].trim();
            coordY1 = coord1Parts[1].trim();
        } else {
            coordX1 = "";
            coordY1 = "";
        }

        String[] coord2Parts = coord2.split(",");
        if (coord2Parts.length == 2) {
            coordX2 = coord2Parts[0].trim();
            coordY2 = coord2Parts[1].trim();
        } else {
            coordX2 = "";
            coordY2 = "";
        }

        x1 = Integer.parseInt(coordX1) + 1;
        y1 = Integer.parseInt(coordY1) + 1;
        x2 = Integer.parseInt(coordX2) + 1;
        y2 = Integer.parseInt(coordY2) + 1;

        if (x1 == x2 && y1 < y2) {
            for (int y = y1; y <= y2; y++) {
                plotTheLine(y, x1);
            }
        } else if (x1 == x2 && y1 > y2) {
            for (int y = y2; y <= y1; y++) {
                plotTheLine(y, x1);
            }
        } else if (y1 == y2 && x1 < x2) {
            for (int x = x1; x <= x2; x++) {
                plotTheLine(y1, x);
            }
        } else if (y1 == y2 && x1 > x2) {
            for (int x = x2; x <= x1; x++) {
                plotTheLine(y1, x);
            }
        } else {
            System.out.println(MYNAME + " ignoring " + wsInpt);
        }

        readInptData(new Scanner(System.in));
    }

    private static void plotTheLine(int y, int x) {
        theOceanFloor[y - 1][x - 1]++;
    }

    private static void countIntersections() {
        if (processTest) {
            // Reset trace
        }

        for (int y = 0; y < MAX_SIZE; y++) {
            for (int x = 0; x < MAX_SIZE; x++) {
                if (theOceanFloor[y][x] > 1) {
                    overlapCount++;
                }
            }
        }

        if (processTest) {
            // Ready trace
        }
    }

    private static void dumpTheOceanFloor() {
        if (processTest) {
            // Reset trace
            for (int y = 0; y < 10; y++) {
                System.out.print(MYNAME + " ");
                for (int x = 0; x < 10; x++) {
                    System.out.print(theOceanFloor[y][x] + " ");
                }
                System.out.println();
            }
            // Ready trace
        }
    }
}