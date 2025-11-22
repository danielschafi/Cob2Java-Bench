import java.util.Scanner;
import java.util.Arrays;
import java.time.LocalDate;

public class cs06b {
    private static final String MYNAME = "cs06b";
    private static int wsRecCount = 0;
    private static int dayCount = 0;
    private static int nbDays = 0;
    private static int unstringPtr = 1;
    private static int fishSub = 0;
    private static long totalFish = 0;
    private static long fishSwap = 0;
    private static String cliArgs = new String(new char[80]).replace('\0', '\0');
    private static char fishX = '\0';
    private static String processType = new String(new char[4]).replace('\0', '\0');
    private static String nbDaysX = new String(new char[4]).replace('\0', '\0');
    private static int fishSubOut = 0;
    private static String wsInpt = new String(new char[1024]).replace('\0', ' ');
    private static boolean inptDataEof = false;
    private static String processSw = new String(new char[4]).replace('\0', '\0');
    private static long[] fishTbl = new long[10];

    public static void main(String[] args) {
        System.out.println(MYNAME + " " + LocalDate.now());

        if (args.length > 0) {
            cliArgs = args[0];
        }

        String[] parts = cliArgs.split(" ");
        if (parts.length > 0) {
            processType = parts[0].toUpperCase();
        }
        if (parts.length > 1) {
            nbDaysX = parts[1];
        }

        processSw = processType.toUpperCase();

        try {
            nbDays = Integer.parseInt(nbDaysX);
        } catch (NumberFormatException e) {
            nbDays = 0;
        }

        System.out.println(MYNAME + " number of days " + nbDays);

        if (processSw.equals("TEST")) {
            // Ready trace
        }

        Arrays.fill(fishTbl, 0);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            wsInpt = scanner.nextLine();
            wsRecCount++;
            loadInput();
        }

        scanner.close();

        for (fishSub = 2; fishSub <= 9; fishSub++) {
            totalFish += fishTbl[fishSub];
        }
        System.out.println(MYNAME + " initial number of fish " + totalFish);

        dumpFishTable();

        for (dayCount = 1; dayCount <= nbDays; dayCount++) {
            processOneDay();
        }

        totalFish = 0;
        for (fishSub = 1; fishSub <= 9; fishSub++) {
            totalFish += fishTbl[fishSub];
        }
        dayCount--;
        System.out.println(MYNAME + " number of fish " + totalFish + " after " + dayCount + " days");
        System.out.println(MYNAME + " records read " + wsRecCount);

        System.out.println(MYNAME + " " + LocalDate.now());
    }

    private static void loadInput() {
        fishX = '\0';
        String[] parts = wsInpt.split(",");
        if (parts.length > 0 && !parts[0].trim().isEmpty()) {
            fishX = parts[0].charAt(0);
        }

        if (fishX != '\0') {
            fishSub = Integer.parseInt(String.valueOf(fishX)) + 1;
            fishTbl[fishSub]++;
        }
    }

    private static void processOneDay() {
        if (processSw.equals("TEST")) {
            // Reset trace
        }

        fishSwap = fishTbl[1];
        for (fishSub = 2; fishSub <= 9; fishSub++) {
            fishTbl[fishSub - 1] = fishTbl[fishSub];
        }
        fishTbl[9] = 0;
        fishTbl[7] += fishSwap;
        fishTbl[9] += fishSwap;

        if (processSw.equals("TEST")) {
            dumpFishTable();
            // Ready trace
        }
    }

    private static void dumpFishTable() {
        System.out.print(MYNAME + " day " + dayCount + " ");
        for (fishSub = 1; fishSub <= 9; fishSub++) {
            fishSubOut = fishSub - 1;
            System.out.print(fishSubOut + " " + fishTbl[fishSub] + ",");
        }
        System.out.println();
    }
}