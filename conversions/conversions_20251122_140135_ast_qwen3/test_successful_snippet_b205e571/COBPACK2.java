import java.io.*;
import java.nio.file.*;

public class COBPACK2 {
    private static final String OUTFILE_NAME = "OUTFILE";
    
    // File structure
    private static class RecOutfile {
        private long outfileText;
        private long outfileUnpacked;
        private long outfileUnpackedS;
        private int outfileComp04;
        private short outfileComp04S;
        private int outfileComp09;
        private int outfileComp09S;
        private long outfileComp18;
        private long outfileComp18S;
        private byte[] outfileComp304 = new byte[3];
        private byte[] outfileComp304S = new byte[3];
        private byte[] outfileComp309 = new byte[5];
        private byte[] outfileComp309S = new byte[5];
        private byte[] outfileComp318 = new byte[10];
        private byte[] outfileComp318S = new byte[10];
        private char[] group1_1 = new char[2];
        private char[] text2 = new char[2];
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("COBPACK START...");
            
            // Open output file
            FileOutputStream fos = new FileOutputStream(OUTFILE_NAME);
            DataOutputStream dos = new DataOutputStream(fos);
            
            RecOutfile record = new RecOutfile();
            long wsIx = -100000000L;
            
            while (wsIx <= 100000000L) {
                // Move wsIx to all fields
                record.outfileText = wsIx;
                record.outfileUnpacked = wsIx;
                record.outfileUnpackedS = wsIx;
                
                // Binary fields (COMP)
                record.outfileComp04 = (int)(wsIx % 10000);
                record.outfileComp04S = (short)(wsIx % 10000);
                record.outfileComp09 = (int)(wsIx % 1000000000);
                record.outfileComp09S = (int)(wsIx % 1000000000);
                record.outfileComp18 = wsIx;
                record.outfileComp18S = wsIx;
                
                // Packed decimal fields (COMP-3)
                packValue(wsIx, record.outfileComp304);
                packValue(wsIx, record.outfileComp304S);
                packValue(wsIx, record.outfileComp309);
                packValue(wsIx, record.outfileComp309S);
                packValue(wsIx, record.outfileComp318);
                packValue(wsIx, record.outfileComp318S);
                
                // Group fields
                record.group1_1[0] = 'A';
                record.group1_1[1] = 'A';
                record.text2[0] = 'A';
                record.text2[1] = 'A';
                
                // Write record
                writeRecord(dos, record);
                
                wsIx += 2001;
            }
            
            dos.close();
            fos.close();
            
            System.out.println("COBPACK FINISH..");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void writeRecord(DataOutputStream dos, RecOutfile record) throws IOException {
        // Write outfileText as signed decimal
        String textStr = String.format("%018d", Math.abs(record.outfileText));
        if (record.outfileText < 0) {
            textStr = "-" + textStr.substring(1);
        }
        dos.writeBytes(textStr);
        
        // Write outfileUnpacked as unsigned decimal
        dos.writeBytes(String.format("%018d", record.outfileUnpacked));
        
        // Write outfileUnpackedS as signed decimal
        String signedStr = String.format("%018d", Math.abs(record.outfileUnpackedS));
        if (record.outfileUnpackedS < 0) {
            signedStr = "-" + signedStr.substring(1);
        }
        dos.writeBytes(signedStr);
        
        // Write binary fields (COMP)
        writeBinaryField(dos, record.outfileComp04, 2); // 2 bytes for 4-digit number
        writeBinaryField(dos, record.outfileComp04S, 2); // 2 bytes for 4-digit number
        writeBinaryField(dos, record.outfileComp09, 4); // 4 bytes for 9-digit number
        writeBinaryField(dos, record.outfileComp09S, 4); // 4 bytes for 9-digit number
        writeBinaryField(dos, record.outfileComp18, 8); // 8 bytes for 18-digit number
        writeBinaryField(dos, record.outfileComp18S, 8); // 8 bytes for 18-digit number
        
        // Write packed decimal fields (COMP-3)
        dos.write(record.outfileComp304);
        dos.write(record.outfileComp304S);
        dos.write(record.outfileComp309);
        dos.write(record.outfileComp309S);
        dos.write(record.outfileComp318);
        dos.write(record.outfileComp318S);
        
        // Write group fields
        dos.writeBytes(new String(record.group1_1));
        dos.writeBytes(new String(record.text2));
        
        // Pad to 150 characters
        for (int i = 0; i < 150 - 112; i++) {
            dos.writeByte(0x20); // space character
        }
    }
    
    private static void writeBinaryField(DataOutputStream dos, int value, int bytes) throws IOException {
        byte[] data = new byte[bytes];
        for (int i = bytes - 1; i >= 0; i--) {
            data[i] = (byte)(value & 0xFF);
            value >>= 8;
        }
        dos.write(data);
    }
    
    private static void writeBinaryField(DataOutputStream dos, long value, int bytes) throws IOException {
        byte[] data = new byte[bytes];
        for (int i = bytes - 1; i >= 0; i--) {
            data[i] = (byte)(value & 0xFF);
            value >>= 8;
        }
        dos.write(data);
    }
    
    private static void packValue(long value, byte[] packed) {
        // Convert to string and reverse for processing
        String strValue = String.valueOf(Math.abs(value));
        int len = strValue.length();
        
        // Fill with leading zeros to make even length
        if (len % 2 == 1) {
            strValue = "0" + strValue;
        }
        
        // Pack two digits per byte
        for (int i = 0; i < strValue.length(); i += 2) {
            int highDigit = Character.digit(strValue.charAt(i), 10);
            int lowDigit = Character.digit(strValue.charAt(i+1), 10);
            int packedByte = (highDigit << 4) | lowDigit;
            packed[i/2] = (byte)packedByte;
        }
        
        // Add sign nibble if negative
        if (value < 0) {
            packed[packed.length-1] |= 0x0F; // Set last nibble to 'F' for negative
        }
    }
}