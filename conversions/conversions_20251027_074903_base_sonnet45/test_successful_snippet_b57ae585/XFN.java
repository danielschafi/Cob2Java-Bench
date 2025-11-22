import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class XFN {
    private static class BitRec {
        short key;
        String value;
        
        BitRec(short key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private static class ByteRec {
        String key;
        short value;
        
        ByteRec(String key, short value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private static BitRec[] bitTable = new BitRec[256];
    private static ByteRec[] byteTable = new ByteRec[256];
    private static String bitString;
    private static short shortInt;
    private static byte aByte;
    private static byte[] byteArray = new byte[8];
    
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
        callXF5();
        StringBuilder displayArray = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            displayArray.append(byteArray[i]);
        }
        System.out.println("  byte: " + (aByte & 0xFF));
        System.out.println("  bits: " + displayArray);
        
        System.out.println("Testing the XF4 procedure:");
        shortInt = 0;
        xf4();
        System.out.println("  bits: " + bitString);
        System.out.println("  byte: " + shortInt);
        
        System.out.println("Testing CALL X\"F4\":");
        aByte = 0;
        callXF4();
        displayArray = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            displayArray.append(byteArray[i]);
        }
        System.out.println("  bits: " + displayArray);
        System.out.println("  byte: " + (aByte & 0xFF));
        
        bigLoop("XFN");
        bigLoop("CALL X\"FN\"");
        
        System.out.println("Press ENTER to exit ...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    
    private static void xf4() {
        for (int i = 0; i < 256; i++) {
            if (byteTable[i].key.equals(bitString)) {
                shortInt = byteTable[i].value;
                break;
            }
        }
    }
    
    private static void xf5() {
        for (int i = 0; i < 256; i++) {
            if (bitTable[i].key == shortInt) {
                bitString = bitTable[i].value;
                break;
            }
        }
    }
    
    private static void callXF4() {
        StringBuilder bits = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            bits.append(byteArray[i]);
        }
        for (int i = 0; i < 256; i++) {
            if (byteTable[i].key.equals(bits.toString())) {
                aByte = (byte) byteTable[i].value;
                break;
            }
        }
    }
    
    private static void callXF5() {
        int byteVal = aByte & 0xFF;
        for (int i = 0; i < 256; i++) {
            if (bitTable[i].key == byteVal) {
                String bits = bitTable[i].value;
                for (int j = 0; j < 8; j++) {
                    byteArray[j] = (byte) (bits.charAt(j) - '0');
                }
                break;
            }
        }
    }
    
    private static void bigLoop(String routine) {
        int iMax = 4096;
        int jMax = 256;
        
        LocalTime time1 = LocalTime.now();
        
        if (routine.equals("XFN")) {
            for (int i = 1; i <= iMax; i++) {
                for (int j = 1; j <= jMax; j++) {
                    shortInt = (short) j;
                    xf5();
                    xf4();
                }
            }
        } else if (routine.equals("CALL X\"FN\"")) {
            for (int i = 1; i <= iMax; i++) {
                for (int j = 1; j <= jMax; j++) {
                    aByte = (byte) j;
                    callXF5();
                    callXF4();
                }
            }
        } else {
            System.out.println("Invalid routine name: " + routine);
            return;
        }
        
        LocalTime time2 = LocalTime.now();
        
        int cnt = iMax * jMax;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SS");
        System.out.println("Start/end times for " + cnt + " round trips through the " + routine + " routines:");
        System.out.println(" " + time1.format(formatter));
        System.out.println(" " + time2.format(formatter));
    }
    
    private static void fillBitTable() {
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
            "01101000", "01101001", "01101010", "01101011", "01101100", "01101101",