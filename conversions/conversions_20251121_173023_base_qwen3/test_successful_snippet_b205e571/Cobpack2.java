import java.io.*;
import java.nio.file.*;

public class Cobpack2 {
    private static final String OUTFILE_NAME = "OUTFILE";
    private static final int RECORD_SIZE = 150;
    
    // File structure fields
    private static long outfileText;
    private static long outfileUnpacked;
    private static long outfileUnpackedS;
    private static int outfileComp04;
    private static int outfileComp04S;
    private static int outfileComp09;
    private static int outfileComp09S;
    private static long outfileComp18;
    private static long outfileComp18S;
    private static byte[] outfileComp304 = new byte[2];
    private static byte[] outfileComp304S = new byte[2];
    private static byte[] outfileComp309 = new byte[5];
    private static byte[] outfileComp309S = new byte[5];
    private static byte[] outfileComp318 = new byte[10];
    private static byte[] outfileComp318S = new byte[10];
    private static byte[] text1 = new byte[2];
    private static byte[] text2 = new byte[2];
    
    // Working storage
    private static String wsFsOutfile = "  ";
    private static long wsIx = -100000000L;
    
    public static void main(String[] args) {
        System.out.println("COBPACK START...");
        
        try {
            // Open output file
            FileOutputStream fos = new FileOutputStream(OUTFILE_NAME);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            
            // Main processing loop
            while (wsIx <= 100000000) {
                // Move WS-IX to all fields
                outfileText = wsIx;
                outfileUnpacked = wsIx;
                outfileUnpackedS = wsIx;
                outfileComp04 = (int) wsIx;
                outfileComp04S = (int) wsIx;
                outfileComp09 = (int) wsIx;
                outfileComp09S = (int) wsIx;
                outfileComp18 = wsIx;
                outfileComp18S = wsIx;
                
                // Pack decimal fields
                packDecimal(wsIx, outfileComp304);
                packDecimal(wsIx, outfileComp304S);
                packDecimal(wsIx, outfileComp309);
                packDecimal(wsIx, outfileComp309S);
                packDecimal(wsIx, outfileComp318);
                packDecimal(wsIx, outfileComp318S);
                
                // Set text fields
                text1[0] = (byte) 'A';
                text1[1] = (byte) 'A';
                text2[0] = (byte) 'A';
                text2[1] = (byte) 'A';
                
                // Write record
                writeRecord(bos);
                
                // Check for write error
                if (!wsFsOutfile.equals("00")) {
                    System.out.println("WRITE OUTFILE FS:  " + wsFsOutfile);
                    return;
                }
                
                wsIx += 2001;
            }
            
            // Close file
            bos.close();
            
            if (!wsFsOutfile.equals("00")) {
                System.out.println("CLOSE OUTFILE FS: " + wsFsOutfile);
                return;
            }
            
            System.out.println("COBPACK FINISH..");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void writeRecord(BufferedOutputStream bos) throws IOException {
        byte[] record = new byte[RECORD_SIZE];
        
        // Fill with spaces initially
        for (int i = 0; i < RECORD_SIZE; i++) {
            record[i] = (byte) ' ';
        }
        
        // Copy values to record
        putLong(record, 0, outfileText);
        putLong(record, 18, outfileUnpacked);
        putLong(record, 36, outfileUnpackedS);
        putInt(record, 54, outfileComp04);
        putInt(record, 58, outfileComp04S);
        putInt(record, 62, outfileComp09);
        putInt(record, 66, outfileComp09S);
        putLong(record, 70, outfileComp18);
        putLong(record, 88, outfileComp18S);
        
        // Copy packed decimals
        System.arraycopy(outfileComp304, 0, record, 106, 2);
        System.arraycopy(outfileComp304S, 0, record, 108, 2);
        System.arraycopy(outfileComp309, 0, record, 110, 5);
        System.arraycopy(outfileComp309S, 0, record, 115, 5);
        System.arraycopy(outfileComp318, 0, record, 120, 10);
        System.arraycopy(outfileComp318S, 0, record, 130, 10);
        
        // Copy text fields
        record[140] = text1[0];
        record[141] = text1[1];
        record[142] = text2[0];
        record[143] = text2[1];
        
        bos.write(record);
    }
    
    private static void packDecimal(long value, byte[] packed) {
        // Simple implementation for demonstration
        // In real COBOL, this would properly pack the decimal
        if (value >= 0) {
            packed[0] = (byte) ((value / 100000000000000000L) & 0x0F);
            packed[1] = (byte) ((value % 100000000000000000L) & 0xFF);
        } else {
            // Handle negative numbers
            packed[0] = (byte) ((value / 100000000000000000L) & 0x0F | 0x0F);
            packed[1] = (byte) ((value % 100000000000000000L) & 0xFF);
        }
    }
    
    private static void putLong(byte[] array, int offset, long value) {
        array[offset] = (byte) ((value >> 56) & 0xFF);
        array[offset + 1] = (byte) ((value >> 48) & 0xFF);
        array[offset + 2] = (byte) ((value >> 40) & 0xFF);
        array[offset + 3] = (byte) ((value >> 32) & 0xFF);
        array[offset + 4] = (byte) ((value >> 24) & 0xFF);
        array[offset + 5] = (byte) ((value >> 16) & 0xFF);
        array[offset + 6] = (byte) ((value >> 8) & 0xFF);
        array[offset + 7] = (byte) (value & 0xFF);
    }
    
    private static void putInt(byte[] array, int offset, int value) {
        array[offset] = (byte) ((value >> 24) & 0xFF);
        array[offset + 1] = (byte) ((value >> 16) & 0xFF);
        array[offset + 2] = (byte) ((value >> 8) & 0xFF);
        array[offset + 3] = (byte) (value & 0xFF);
    }
}