import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class COBPACK2 {
    public static void main(String[] args) {
        String outFile = "OUTFILE";
        String wsFsOutFile = "  ";
        long wsIx = -100000000;

        System.out.println("COBPACK START...");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            while (wsIx <= 100000000) {
                StringBuilder recOutfile = new StringBuilder(150);

                recOutfile.append(String.format("%-18s", wsIx)); // OUTFILE-TEXT
                recOutfile.append(String.format("%018d", wsIx)); // OUTFILE-UNPACKED
                recOutfile.append(String.format("%+018d", wsIx)); // OUTFILE-UNPACKED-S

                recOutfile.append(intToBytes((int) wsIx, 2)); // OUTFILE-COMP-04
                recOutfile.append(intToBytes((int) wsIx, 2)); // OUTFILE-COMP-04-S
                recOutfile.append(longToBytes(wsIx, 4)); // OUTFILE-COMP-09
                recOutfile.append(longToBytes(wsIx, 4)); // OUTFILE-COMP-09-S
                recOutfile.append(longToBytes(wsIx, 8)); // OUTFILE-COMP-18
                recOutfile.append(longToBytes(wsIx, 8)); // OUTFILE-COMP-18-S

                recOutfile.append(intToComp3((int) wsIx, 2)); // OUTFILE-COMP3-04
                recOutfile.append(intToComp3((int) wsIx, 2)); // OUTFILE-COMP3-04-S
                recOutfile.append(longToComp3(wsIx, 4)); // OUTFILE-COMP3-09
                recOutfile.append(longToComp3(wsIx, 4)); // OUTFILE-COMP3-09-S
                recOutfile.append(longToComp3(wsIx, 8)); // OUTFILE-COMP3-18
                recOutfile.append(longToComp3(wsIx, 8)); // OUTFILE-COMP3-18-S

                recOutfile.append("AA"); // TEXT1 OF GROUP1-1 (1) and (2)

                writer.write(recOutfile.toString());
                writer.newLine();

                wsIx += 2001;
            }
        } catch (IOException e) {
            wsFsOutFile = "99";
            System.out.println("WRITE OUTFILE FS:  " + wsFsOutFile);
            System.exit(1);
        }

        System.out.println("COBPACK FINISH..");
    }

    private static String intToBytes(int value, int length) {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= 8;
        }
        return new String(bytes);
    }

    private static String longToBytes(long value, int length) {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= 8;
        }
        return new String(bytes);
    }

    private static String intToComp3(int value, int length) {
        String sign = value < 0 ? "D" : "C";
        value = Math.abs(value);
        StringBuilder comp3 = new StringBuilder(String.format("%0" + (length * 2 - 1) + "d", value));
        comp3.append(sign);
        return comp3.toString();
    }

    private static String longToComp3(long value, int length) {
        String sign = value < 0 ? "D" : "C";
        value = Math.abs(value);
        StringBuilder comp3 = new StringBuilder(String.format("%0" + (length * 2 - 1) + "d", value));
        comp3.append(sign);
        return comp3.toString();
    }
}