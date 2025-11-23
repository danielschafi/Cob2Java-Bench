import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class XFN {
    private static final int[] BIT_TABLE_KEY = new int[256];
    private static final String[] BIT_TABLE_VAL = new String[256];
    private static final Map<String, Short> BYTE_TABLE_KEY = new HashMap<>();
    private static final Map<Short, String> BYTE_TABLE_VAL = new HashMap<>();
    
    private static byte[] byte_array = new byte[8];
    private static char[] display_array = new char[8];
    private static short short_int = 0;
    private static String bit_string = "";
    private static byte a_byte = 0;
    private static String routine = "";
    private static String time1 = "";
    private static String time2 = "";
    private static int cnt = 0;
    private static int i_max = 0;
    private static int j_max = 0;

    public static void main(String[] args) {
        fillBitTable();
        fillByteTable();

        System.out.println("Testing the XF5 procedure:");
        short_int = 42;
        xf5();
        System.out.println("  byte: " + short_int);
        System.out.println("  bits: " + bit_string);

        System.out.println("Testing CALL X\"F5\":");
        a_byte = 42;
        callXF5(a_byte, byte_array);
        for (int i = 0; i < 8; i++) {
            display_array[i] = (char) byte_array[i];
        }
        System.out.println("  byte: " + a_byte);
        System.out.print("  bits: ");
        for (int i = 0; i < 8; i++) {
            System.out.print(display_array[i]);
        }
        System.out.println();

        System.out.println("Testing the XF4 procedure:");
        short_int = 0;
        xf4();
        System.out.println("  bits: " + bit_string);
        System.out.println("  byte: " + short_int);

        System.out.println("Testing CALL X\"F4\":");
        a_byte = 0;
        callXF4(a_byte, byte_array);
        System.out.print("  bits: ");
        for (int i = 0; i < 8; i++) {
            System.out.print(display_array[i]);
        }
        System.out.println();
        System.out.println("  byte: " + a_byte);

        routine = "XFN";
        bigLoop();

        routine = "CALL X\"FN\"";
        bigLoop();

        System.out.println("Press ENTER to exit ...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void xf4() {
        String key = bit_string;
        if (BYTE_TABLE_KEY.containsKey(key)) {
            short_int = BYTE_TABLE_KEY.get(key);
        }
    }

    private static void xf5() {
        Short key = short_int;
        if (BYTE_TABLE_VAL.containsKey(key)) {
            bit_string = BYTE_TABLE_VAL.get(key);
        }
    }

    private static void bigLoop() {
        i_max = 4096;
        j_max = 256;
        time1 = getTime();
        
        if ("XFN".equals(routine)) {
            for (int i = 1; i <= i_max; i++) {
                for (int j = 1; j <= j_max; j++) {
                    short_int = (short) j;
                    xf5();
                    xf4();
                }
            }
        } else if ("CALL X\"FN\"".equals(routine)) {
            for (int i = 1; i <= i_max; i++) {
                for (int j = 1; j <= j_max; j++) {
                    a_byte = (byte) j;
                    callXF5(a_byte, byte_array);
                    callXF4(a_byte, byte_array);
                }
            }
        } else {
            System.out.println("Invalid routine name: " + routine);
        }

        time2 = getTime();
        cnt = i_max * j_max;
        System.out.println("Start/end times for " + cnt + " round trips through the " + routine + " routines:");
        System.out.println(" " + time1.substring(0, 2) + ":" + time1.substring(2, 4) + ":" + time1.substring(4, 6) + "." + time1.substring(6, 8));
        System.out.println(" " + time2.substring(0, 2) + ":" + time2.substring(2, 4) + ":" + time2.substring(4, 6) + "." + time2.substring(6, 8));
    }

    private static void fillBitTable() {
        for (int i = 0; i < 256; i++) {
            BIT_TABLE_KEY[i] = i - 1;
            switch (i) {
                case 0x00: BIT_TABLE_VAL[i] = "00000000"; break;
                case 0x01: BIT_TABLE_VAL[i] = "00000001"; break;
                case 0x02: BIT_TABLE_VAL[i] = "00000010"; break;
                case 0x03: BIT_TABLE_VAL[i] = "00000011"; break;
                case 0x04: BIT_TABLE_VAL[i] = "00000100"; break;
                case 0x05: BIT_TABLE_VAL[i] = "00000101"; break;
                case 0x06: BIT_TABLE_VAL[i] = "00000110"; break;
                case 0x07: BIT_TABLE_VAL[i] = "00000111"; break;
                case 0x08: BIT_TABLE_VAL[i] = "00001000"; break;
                case 0x09: BIT_TABLE_VAL[i] = "00001001"; break;
                case 0x0A: BIT_TABLE_VAL[i] = "00001010"; break;
                case 0x0B: BIT_TABLE_VAL[i] = "00001011"; break;
                case 0x0C: BIT_TABLE_VAL[i] = "00001100"; break;
                case 0x0D: BIT_TABLE_VAL[i] = "00001101"; break;
                case 0x0E: BIT_TABLE_VAL[i] = "00001110"; break;
                case 0x0F: BIT_TABLE_VAL[i] = "00001111"; break;
                case 0x10: BIT_TABLE_VAL[i] = "00010000"; break;
                case 0x11: BIT_TABLE_VAL[i] = "00010001"; break;
                case 0x12: BIT_TABLE_VAL[i] = "00010010"; break;
                case 0x13: BIT_TABLE_VAL[i] = "00010011"; break;
                case 0x14: BIT_TABLE_VAL[i] = "00010100"; break;
                case 0x15: BIT_TABLE_VAL[i] = "00010101"; break;
                case 0x16: BIT_TABLE_VAL[i] = "00010110"; break;
                case 0x17: BIT_TABLE_VAL[i] = "00010111"; break;
                case 0x18: BIT_TABLE_VAL[i] = "00011000"; break;
                case 0x19: BIT_TABLE_VAL[i] = "00011001"; break;
                case 0x1A: BIT_TABLE_VAL[i] = "00011010"; break;
                case 0x1B: BIT_TABLE_VAL[i] = "00011011"; break;
                case 0x1C: BIT_TABLE_VAL[i] = "00011100"; break;
                case 0x1D: BIT_TABLE_VAL[i] = "00011101"; break;
                case 0x1E: BIT_TABLE_VAL[i] = "00011110"; break;
                case 0x1F: BIT_TABLE_VAL[i] = "00011111"; break;
                case 0x20: BIT_TABLE_VAL[i] = "00100000"; break;
                case 0x21: BIT_TABLE_VAL[i] = "00100001"; break;
                case 0x22: BIT_TABLE_VAL[i] = "00100010"; break;
                case 0x23: BIT_TABLE_VAL[i] = "00100011"; break;
                case 0x24: BIT_TABLE_VAL[i] = "00100100"; break;
                case 0x25: BIT_TABLE_VAL[i] = "00100101"; break;
                case 0x26: BIT_TABLE_VAL[i] = "0