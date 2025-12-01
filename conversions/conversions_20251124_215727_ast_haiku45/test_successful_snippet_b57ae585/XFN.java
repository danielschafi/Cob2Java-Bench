import java.util.*;
import java.time.*;
import java.time.format.*;

public class XFN {
    static class BitRecord {
        short key;
        String value;
    }
    
    static class ByteRecord {
        String key;
        short value;
    }
    
    static BitRecord[] bitTable = new BitRecord[256];
    static ByteRecord[] byteTable = new ByteRecord[256];
    
    static byte aByte;
    static byte[] byteArray = new byte[8];
    static int displayByte;
    static int[] displayArray = new int[8];
    
    static String bitString = "";
    static short shortInt;
    
    static long i;
    static long j;
    static long iMax;
    static long jMax;
    
    static String routine = "";
    static String time1 = "";
    static String time2 = "";
    static long cnt;
    
    public static void main(String[] args) {
        fillBitTable();
        fillByteTable();
        
        System.out.println("Testing the XF5 procedure:");
        shortInt = 42;
        xf5();
        System.out.println("  byte: " + shortInt);
        System.out.println("  bits: " + bitString);
        
        System.out.println("Testing CALL X\"F5\":");
        aByte = 42;
        callF5(aByte, byteArray);
        for (int k = 0; k < 8; k++) {
            displayArray[k] = byteArray[k];
        }
        System.out.println("  byte: " + aByte);
        System.out.print("  bits: ");
        for (int k = 0; k < 8; k++) {
            System.out.print(displayArray[k]);
        }
        System.out.println();
        
        System.out.println("Testing the XF4 procedure:");
        shortInt = 0;
        xf4();
        System.out.println("  bits: " + bitString);
        System.out.println("  byte: " + shortInt);
        
        System.out.println("Testing CALL X\"F4\":");
        aByte = 0;
        callF4(aByte, byteArray);
        System.out.print("  bits: ");
        for (int k = 0; k < 8; k++) {
            System.out.print(byteArray[k]);
        }
        System.out.println();
        System.out.println("  byte: " + aByte);
        
        routine = "XFN";
        bigLoop();
        
        routine = "CALL X\"FN\"";
        bigLoop();
        
        System.out.println("Press ENTER to exit ...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
    
    static void xf4() {
        for (int idx = 0; idx < 256; idx++) {
            if (byteTable[idx].key.equals(bitString)) {
                shortInt = byteTable[idx].value;
                break;
            }
        }
    }
    
    static void xf5() {
        for (int idx = 0; idx < 256; idx++) {
            if (bitTable[idx].key == shortInt) {
                bitString = bitTable[idx].value;
                break;
            }
        }
    }
    
    static void bigLoop() {
        iMax = 4096;
        jMax = 256;
        time1 = getCurrentTime();
        
        if (routine.equals("XFN")) {
            for (i = 1; i <= iMax; i++) {
                for (j = 1; j <= jMax; j++) {
                    shortInt = (short) j;
                    xf5();
                    xf4();
                }
            }
        } else if (routine.equals("CALL X\"FN\"")) {
            for (i = 1; i <= iMax; i++) {
                for (j = 1; j <= jMax; j++) {
                    aByte = (byte) j;
                    callF5(aByte, byteArray);
                    callF4(aByte, byteArray);
                }
            }
        } else {
            System.out.println("Invalid routine name: " + routine);
            return;
        }
        
        time2 = getCurrentTime();
        
        cnt = iMax * jMax;
        System.out.println("Start/end times for " + cnt + " round trips through the " + routine + " routines:");
        System.out.println(" " + time1.substring(0, 2) + ":" + time1.substring(2, 4) + ":" + time1.substring(4, 6) + "." + time1.substring(6, 8));
        System.out.println(" " + time2.substring(0, 2) + ":" + time2.substring(2, 4) + ":" + time2.substring(4, 6) + "." + time2.substring(6, 8));
    }
    
    static void fillBitTable() {
        String[] bitPatterns = {
            "00000000", "00000001", "00000010", "00000011", "00000100", "00000101", "00000110", "00000111",
            "00001000", "00001001", "00001010", "00001011", "00001100", "00001101", "00001110", "00001111",
            "00010000", "00010001", "00010010", "00010011", "00010100", "00010101", "00010110", "00010111",
            "00011000", "00011001", "00011010", "00011011", "00011100", "00011101", "00011110", "00011111",
            "00100000", "00100001", "00100010", "00100011", "00100100", "00100101", "00100110", "00100111",
            "00101000", "00101001", "00101010", "00101011", "00101100", "00101101", "00101110", "00101111",
            "00110000", "00110001", "00110010", "00110011", "00110100", "00110101", "00110110", "00110111",
            "00111000", "00111001", "00111010", "00111011", "00111100", "00111101", "00111110", "00111111",
            "01000000", "01000001", "01000010", "01000011", "01000100", "01000101", "01000110", "01000111",
            "01001000", "01001001", "01001010", "01001011", "01001100", "01001101", "01001110", "01001111",
            "01010000", "01010001", "01010010", "01010011", "01010100", "01010101", "01010110", "01010111",
            "01011000", "01011001", "01011010", "01011011", "01011100", "01011101", "01011110", "01011111",
            "01100000", "01100001", "01100010", "01100011", "01100100", "01100101", "01100110", "01100111",
            "01101000", "01101001", "01101010", "01101011", "01101100", "01101101", "01101110", "01101111",
            "01110000", "01110001", "01110010", "01110011", "01110100", "01110101", "01110110", "01110111",
            "01111000", "01111001", "01111010", "01111011", "01111100", "01111101", "01111110", "01111111",
            "10000000", "10000001", "10000010", "10000011", "10000100", "10000101", "10000110", "10000111",
            "10001000", "10001001", "10001010", "10001011