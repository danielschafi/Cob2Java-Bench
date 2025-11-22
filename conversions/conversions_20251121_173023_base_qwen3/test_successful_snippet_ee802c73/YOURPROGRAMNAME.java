import java.io.*;
import java.nio.file.*;

public class YOURPROGRAMNAME {
    private static final String FILE_NAME = "your-file.txt";
    private static int returnCode = 0;

    public static void main(String[] args) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(FILE_NAME))) {
            // Initialize data
            int count = 4;
            long[] myVar = {123456789L, 123456789L, 0L, -123456789L};

            // Open file and check status
            if (returnCode != 0) {
                System.out.println("OPEN");
                System.out.println(returnCode);
                System.out.println("-");
            }

            // Write string records
            writeStringRecord(out, "-NXT-");

            // Write FILEO-REC records
            for (int i = 0; i < count; i++) {
                writeRecord(out, myVar[i]);
            }

            writeStringRecord(out, "-NXT-");

            // Write FILEO-REC1 records
            for (int i = 0; i < count; i++) {
                writeRecord(out, myVar[i]);
            }

            writeStringRecord(out, "-NXT-");

            // Write FILEO-REC2 records
            for (int i = 0; i < count; i++) {
                writeRecord(out, myVar[i]);
            }

            writeStringRecord(out, "-NXT-");

            // Write FILEO-REC3 records
            for (int i = 0; i < count; i++) {
                writeRecord(out, myVar[i]);
            }

            // Close file and check status
            if (returnCode != 0) {
                System.out.println("CLOSE");
                System.out.println(returnCode);
                System.out.println("-");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void writeStringRecord(DataOutputStream out, String str) throws IOException {
        byte[] bytes = str.getBytes();
        out.write(bytes);
    }

    private static void writeRecord(DataOutputStream out, long value) throws IOException {
        // Convert signed integer to packed decimal format (simplified)
        // In actual implementation, this would require proper BCD conversion
        int intValue = (int) value;
        byte[] bytes = new byte[5]; // 5 bytes for packed decimal
        bytes[0] = (byte) ((intValue >> 24) & 0xFF);
        bytes[1] = (byte) ((intValue >> 16) & 0xFF);
        bytes[2] = (byte) ((intValue >> 8) & 0xFF);
        bytes[3] = (byte) (intValue & 0xFF);
        bytes[4] = (byte) 0x00; // Placeholder for sign
        out.write(bytes);
    }
}