import java.util.Scanner;

public class cs01a {
    public static void main(String[] args) {
        String MYNAME = "cs01a";
        int WS_COUNT = 0;
        int WS_REC_COUNT = 0;
        int DEPTH_LEN = 0;
        int HOLD_DEPTH = 0;
        int CURR_DEPTH = 0;
        String WS_INPT_DEPTH = "        ";
        char WS_INPT_BYTE_4;
        boolean INPT_DATA_EOF = false;

        Scanner scanner = new Scanner(System.in);

        readInputData(scanner, WS_INPT_DEPTH);
        CURR_DEPTH = Integer.parseInt(WS_INPT_DEPTH.trim());
        HOLD_DEPTH = CURR_DEPTH;

        while (!INPT_DATA_EOF) {
            if (CURR_DEPTH > HOLD_DEPTH) {
                WS_COUNT++;
            }
            HOLD_DEPTH = CURR_DEPTH;
            readInputData(scanner, WS_INPT_DEPTH);
            if (!INPT_DATA_EOF) {
                CURR_DEPTH = Integer.parseInt(WS_INPT_DEPTH.trim());
            }
        }

        scanner.close();

        System.out.println(MYNAME + " measurements larger than the previous measurement " + WS_COUNT);
        System.out.println(MYNAME + " records read " + WS_REC_COUNT);
    }

    private static void readInputData(Scanner scanner, String WS_INPT_DEPTH) {
        if (scanner.hasNext()) {
            WS_INPT_DEPTH = scanner.next();
            if (WS_INPT_DEPTH.length() > 4) {
                WS_INPT_DEPTH = WS_INPT_DEPTH.substring(0, 4);
            } else {
                WS_INPT_DEPTH = String.format("%-4s", WS_INPT_DEPTH);
            }
            WS_REC_COUNT++;
        } else {
            INPT_DATA_EOF = true;
        }
    }
}