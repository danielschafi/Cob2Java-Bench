import java.text.SimpleDateFormat;
import java.util.Date;

public class XFN {
    private static final int[] BIT_REC_KEY = new int[256];
    private static final char[][] BIT_REC_VAL = new char[256][8];
    private static final char[][] BYTE_REC_KEY = new char[256][8];
    private static final int[] BYTE_REC_VAL = new int[256];
    
    private static String BIT_STRING = "";
    private static int SHORT_INT = 0;
    private static byte A_BYTE = 0;
    private static byte[] BYTE_ARRAY = new byte[8];
    private static int[] DISPLAY_ARRAY = new int[8];
    
    public static void main(String[] args) {
        fillBitTable();
        fillByteTable();
        
        System.out.println("Testing the XF5 procedure:");
        SHORT_INT = 42;
        xf5();
        System.out.println("  byte: " + SHORT_INT);
        System.out.println("  bits: " + BIT_STRING);
        
        System.out.println("Testing CALL X\"F5\":");
        A_BYTE = 42;
        callXF5();
        System.out.print("  byte: " + A_BYTE);
        System.out.print("  bits: ");
        for (int i = 0; i < 8; i++) {
            System.out.print(DISPLAY_ARRAY[i]);
        }
        System.out.println();
        
        System.out.println("Testing the XF4 procedure:");
        SHORT_INT = 0;
        xf4();
        System.out.println("  bits: " + BIT_STRING);
        System.out.println("  byte: " + SHORT_INT);
        
        System.out.println("Testing CALL X\"F4\":");
        A_BYTE = 0;
        callXF4();
        System.out.print("  bits: ");
        for (int i = 0; i < 8; i++) {
            System.out.print(DISPLAY_ARRAY[i]);
        }
        System.out.println();
        System.out.println("  byte: " + A_BYTE);
        
        String routine = "XFN";
        bigLoop(routine);
        
        routine = "CALL X\"FN\"";
        bigLoop(routine);
        
        System.out.println("Press ENTER to exit ...");
        try {
            System.in.read();
        } catch (Exception e) {}
    }
    
    private static void xf4() {
        for (int i = 0; i < 256; i++) {
            if (BIT_REC_KEY[i] == SHORT_INT) {
                BIT_STRING = new String(BIT_REC_VAL[i]);
                break;
            }
        }
    }
    
    private static void xf5() {
        for (int i = 0; i < 256; i++) {
            if (BIT_REC_KEY[i] == SHORT_INT) {
                BIT_STRING = new String(BIT_REC_VAL[i]);
                break;
            }
        }
    }
    
    private static void bigLoop(String routine) {
        int I_MAX = 4096;
        int J_MAX = 256;
        
        long start = System.currentTimeMillis();
        String time1 = formatTime(start);
        
        if ("XFN".equals(routine)) {
            for (int i = 1; i <= I_MAX; i++) {
                for (int j = 1; j <= J_MAX; j++) {
                    SHORT_INT = j;
                    xf5();
                    xf4();
                }
            }
        } else if ("CALL X\"FN\"".equals(routine)) {
            for (int i = 1; i <= I_MAX; i++) {
                for (int j = 1; j <= J_MAX; j++) {
                    A_BYTE = (byte) j;
                    callXF5();
                    callXF4();
                }
            }
        } else {
            System.out.println("Invalid routine name: " + routine);
            return;
        }
        
        long end = System.currentTimeMillis();
        String time2 = formatTime(end);
        
        int cnt = I_MAX * J_MAX;
        System.out.println("Start/end times for " + cnt + " round trips through the " + routine + " routines:");
        System.out.println(" " + time1.substring(0, 2) + ":" + time1.substring(2, 4) + ":" + 
                          time1.substring(4, 6) + "." + time1.substring(6, 8));
        System.out.println(" " + time2.substring(0, 2) + ":" + time2.substring(2, 4) + ":" + 
                          time2.substring(4, 6) + "." + time2.substring(6, 8));
    }
    
    private static String formatTime(long millis) {
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSS");
        return sdf.format(date);
    }
    
    private static void callXF5() {
        for (int i = 0; i < 8; i++) {
            BYTE_ARRAY[i] = 0;
        }
        
        int val = A_BYTE & 0xFF;
        for (int i = 7; i >= 0; i--) {
            if ((val & (1 << i)) != 0) {
                BYTE_ARRAY[7 - i] = 1;
            }
        }
        
        for (int i = 0; i < 8; i++) {
            DISPLAY_ARRAY[i] = BYTE_ARRAY[i];
        }
    }
    
    private static void callXF4() {
        int result = 0;
        for (int i = 0; i < 8; i++) {
            result = (result << 1) | BYTE_ARRAY[i];
        }
        A_BYTE = (byte) result;
        
        for (int i = 0; i < 8; i++) {
            DISPLAY_ARRAY[i] = BYTE_ARRAY[i];
        }
    }
    
    private static void fillBitTable() {
        for (int i = 1; i <= 256; i++) {
            BIT_REC_KEY[i-1] = i - 1;
            
            String binaryString = Integer.toBinaryString(i - 1);
            while (binaryString.length() < 8) {
                binaryString = "0" + binaryString;
            }
            
            if (binaryString.length() > 8) {
                binaryString = binaryString.substring(binaryString.length() - 8);
            }
            
            for (int j = 0; j < 8; j++) {
                BIT_REC_VAL[i-1][j] = binaryString.charAt(j);
            }
        }
    }
    
    private static void fillByteTable() {
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 8; j++) {
                BYTE_REC_KEY[i][j] = BIT_REC_VAL[i][j];
            }
            BYTE_REC_VAL[i] = BIT_REC_KEY[i];
        }
    }
}