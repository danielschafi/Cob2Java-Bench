import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.math.BigDecimal;

public class COBPACK2 {
    private static final int RECORD_SIZE = 150;
    private String wsfsOutfile = "00";
    private long wsIx;

    public static void main(String[] args) {
        COBPACK2 program = new COBPACK2();
        program.run();
    }

    public void run() {
        System.out.println("COBPACK START...");
        
        String outfileName = System.getenv("OUTFILE");
        if (outfileName == null || outfileName.isEmpty()) {
            outfileName = "OUTFILE";
        }

        try (FileOutputStream outfile = new FileOutputStream(outfileName)) {
            wsfsOutfile = "00";
            
            wsIx = -100000000L;

            while (wsIx <= 100000000L) {
                byte[] record = new byte[RECORD_SIZE];
                int offset = 0;

                String outfileText = String.format("%019d", wsIx);
                System.arraycopy(outfileText.getBytes(), 0, record, offset, 19);
                offset += 19;

                String outfileUnpacked = String.format("%018d", Math.abs(wsIx));
                System.arraycopy(outfileUnpacked.getBytes(), 0, record, offset, 18);
                offset += 18;

                String outfileUnpackedS = String.format("%018d", wsIx);
                if (wsIx < 0) {
                    outfileUnpackedS = String.format("%018d", wsIx).replace("-", "");
                    byte[] temp = outfileUnpackedS.getBytes();
                    temp[temp.length - 1] = (byte) (temp[temp.length - 1] | 0x70);
                    System.arraycopy(temp, 0, record, offset, 18);
                } else {
                    System.arraycopy(outfileUnpackedS.getBytes(), 0, record, offset, 18);
                }
                offset += 18;

                ByteBuffer bb = ByteBuffer.wrap(record, offset, 36);
                bb.order(ByteOrder.BIG_ENDIAN);
                
                bb.putShort((short) wsIx);
                bb.putShort((short) wsIx);
                bb.putInt((int) wsIx);
                bb.putInt((int) wsIx);
                bb.putLong(wsIx);
                bb.putLong(wsIx);
                offset += 36;

                offset = writePackedDecimal(record, offset, wsIx, 4);
                offset = writePackedDecimal(record, offset, wsIx, 4);
                offset = writePackedDecimal(record, offset, wsIx, 9);
                offset = writePackedDecimal(record, offset, wsIx, 9);
                offset = writePackedDecimal(record, offset, wsIx, 18);
                offset = writePackedDecimal(record, offset, wsIx, 18);

                record[offset] = 'A';
                offset++;
                record[offset] = 'A';
                offset++;

                outfile.write(record);

                wsIx += 2001;
            }

            wsfsOutfile = "00";
            
        } catch (IOException e) {
            System.out.println("WRITE OUTFILE FS:  " + wsfsOutfile);
            System.exit(1);
        }

        System.out.println("COBPACK FINISH..");
    }

    private int writePackedDecimal(byte[] record, int offset, long value, int digits) {
        int bytes = (digits + 2) / 2;
        long absValue = Math.abs(value);
        String numStr = String.format("%0" + digits + "d", absValue);
        
        for (int i = 0; i < bytes; i++) {
            record[offset + i] = 0;
        }
        
        int strPos = 0;
        for (int i = 0; i < bytes - 1; i++) {
            int highNibble = numStr.charAt(strPos) - '0';
            int lowNibble = numStr.charAt(strPos + 1) - '0';
            record[offset + i] = (byte) ((highNibble << 4) | lowNibble);
            strPos += 2;
        }
        
        int lastByte;
        if (digits % 2 == 0) {
            int highNibble = numStr.charAt(strPos) - '0';
            int sign = (value < 0) ? 0x0D : 0x0C;
            lastByte = (highNibble << 4) | sign;
        } else {
            int digit = numStr.charAt(strPos) - '0';
            int sign = (value < 0) ? 0x0D : 0x0C;
            lastByte = (digit << 4) | sign;
        }
        record[offset + bytes - 1] = (byte) lastByte;
        
        return offset + bytes;
    }
}