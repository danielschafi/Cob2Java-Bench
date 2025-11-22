import java.util.Arrays;
import java.util.Scanner;

public class XFN {
    private static final int[] BIT_TABLE_KEY = new int[256];
    private static final String[] BIT_TABLE_VAL = new String[256];
    private static final String[] BYTE_TABLE_KEY = new String[256];
    private static final int[] BYTE_TABLE_VAL = new int[256];
    private static final char[] BYTE_ARRAY = new char[8];
    private static final int[] DISPLAY_ARRAY = new int[8];
    private static final String BIT_STRING = new String(new char[8]);
    private static int SHORT_INT;
    private static long I, J, I_MAX, J_MAX;
    private static String ROUTINE;
    private static String TIME1, TIME2;
    private static long CNT;

    public static void main(String[] args) {
        fillBitTable();
        fillByteTable();

        System.out.println("Testing the XF5 procedure:");
        SHORT_INT = 42;
        xf5();
        System.out.println("  byte: " + SHORT_INT);
        System.out.println("  bits: " + BIT_STRING);

        System.out.println("Testing CALL X\"F5\":");
        byte aByte = (byte) 42;
        callXF5(aByte, BYTE_ARRAY);
        for (int i = 0; i < 8; i++) {
            DISPLAY_ARRAY[i] = BYTE_ARRAY[i];
        }
        System.out.println("  byte: " + aByte);
        System.out.println("  bits: " + Arrays.toString(DISPLAY_ARRAY));

        System.out.println("Testing the XF4 procedure:");
        SHORT_INT = 0;
        xf4();
        System.out.println("  bits: " + BIT_STRING);
        System.out.println("  byte: " + SHORT_INT);

        System.out.println("Testing CALL X\"F4\":");
        aByte = 0;
        callXF4(aByte, BYTE_ARRAY);
        System.out.println("  bits: " + Arrays.toString(BYTE_ARRAY));
        System.out.println("  byte: " + aByte);

        ROUTINE = "XFN";
        bigLoop();

        ROUTINE = "CALL X\"FN\"";
        bigLoop();

        System.out.println("Press ENTER to exit ...");
        new Scanner(System.in).nextLine();
    }

    private static void xf4() {
        for (int i = 0; i < 256; i++) {
            if (BIT_TABLE_VAL[i].equals(BIT_STRING)) {
                SHORT_INT = BIT_TABLE_KEY[i];
                break;
            }
        }
    }

    private static void xf5() {
        BIT_STRING.getChars(0, 8, BYTE_ARRAY, 0);
        SHORT_INT = (int) Long.parseLong(new String(BYTE_ARRAY), 2);
        BIT_TABLE_VAL[SHORT_INT].getChars(0, 8, BYTE_ARRAY, 0);
    }

    private static void callXF5(byte aByte, char[] byteArray) {
        String bitString = String.format("%8s", Integer.toBinaryString(aByte & 0xFF)).replace(' ', '0');
        bitString.getChars(0, 8, byteArray, 0);
    }

    private static void callXF4(byte aByte, char[] byteArray) {
        String bitString = new String(byteArray);
        int value = (int) Long.parseLong(bitString, 2);
        String result = String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
        result.getChars(0, 8, byteArray, 0);
    }

    private static void bigLoop() {
        I_MAX = 4096;
        J_MAX = 256;
        TIME1 = Long.toString(System.currentTimeMillis());

        switch (ROUTINE) {
            case "XFN":
                for (I = 1; I <= I_MAX; I++) {
                    for (J = 1; J <= J_MAX; J++) {
                        SHORT_INT = (int) J;
                        xf5();
                        xf4();
                    }
                }
                break;
            case "CALL X\"FN\"":
                for (I = 1; I <= I_MAX; I++) {
                    for (J = 1; J <= J_MAX; J++) {
                        byte aByte = (byte) J;
                        callXF5(aByte, BYTE_ARRAY);
                        callXF4(aByte, BYTE_ARRAY);
                    }
                }
                break;
            default:
                System.out.println("Invalid routine name: " + ROUTINE);
                return;
        }

        TIME2 = Long.toString(System.currentTimeMillis());

        CNT = I_MAX * J_MAX;
        System.out.println("Start/end times for " + CNT + " round trips through the " + ROUTINE + " routines:");
        System.out.println(" " + TIME1.substring(0, 2) + ":" + TIME1.substring(2, 4) + ":" + TIME1.substring(4, 6) + "." + TIME1.substring(6, 8));
        System.out.println(" " + TIME2.substring(0, 2) + ":" + TIME2.substring(2, 4) + ":" + TIME2.substring(4, 6) + "." + TIME2.substring(6, 8));
    }

    private static void fillBitTable() {
        for (int i = 0; i < 256; i++) {
            BIT_TABLE_KEY[i] = i;
            BIT_TABLE_VAL[i] = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
        }
    }

    private static void fillByteTable() {
        for (int i = 0; i < 256; i++) {
            BYTE_TABLE_KEY[i] = BIT_TABLE_VAL[i];
            BYTE_TABLE_VAL[i] = BIT_TABLE_KEY[i];
        }
    }
}