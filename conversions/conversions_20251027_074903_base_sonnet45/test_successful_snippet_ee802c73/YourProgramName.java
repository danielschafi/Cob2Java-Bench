import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class YourProgramName {
    private static final String FILE_NAME = "your-file.txt";
    private static int fsFileo = 0;
    private static int wsCount = 0;
    private static int wsI = 0;
    private static long[] wsMyVar = new long[11];
    private static FileOutputStream fileOutputStream = null;

    public static void main(String[] args) {
        int returnCode = 0;

        wsCount = 4;
        wsMyVar[1] = 123456789L;
        wsMyVar[2] = 123456789L;
        wsMyVar[3] = 0L;
        wsMyVar[4] = -123456789L;

        try {
            fileOutputStream = new FileOutputStream(FILE_NAME);
            fsFileo = 0;
        } catch (IOException e) {
            System.out.println("OPEN");
            fsFileo = 35;
            System.out.println(String.format("%02d", fsFileo));
            returnCode = fsFileo;
            System.out.println("-");
        }

        if (fsFileo == 0) {
            try {
                writeString("-NXT-");

                for (wsI = 1; wsI <= wsCount; wsI++) {
                    writeComp3(wsMyVar[wsI], 10);
                    if (fsFileo != 0) {
                        System.out.println("WRITE");
                        System.out.println(String.format("%02d", fsFileo));
                        returnCode = fsFileo;
                        System.out.println("-");
                    }
                }

                writeString("-NXT-");

                for (wsI = 1; wsI <= wsCount; wsI++) {
                    writeComp3(wsMyVar[wsI], 9);
                    if (fsFileo != 0) {
                        System.out.println("WRITE");
                        System.out.println(String.format("%02d", fsFileo));
                        returnCode = fsFileo;
                        System.out.println("-");
                    }
                }

                writeString("-NXT-");

                for (wsI = 1; wsI <= wsCount; wsI++) {
                    writeComp3Unsigned(wsMyVar[wsI], 9);
                    if (fsFileo != 0) {
                        System.out.println("WRITE");
                        System.out.println(String.format("%02d", fsFileo));
                        returnCode = fsFileo;
                        System.out.println("-");
                    }
                }

                writeString("-NXT-");

                for (wsI = 1; wsI <= wsCount; wsI++) {
                    writeComp3Unsigned(wsMyVar[wsI], 10);
                    if (fsFileo != 0) {
                        System.out.println("WRITE");
                        System.out.println(String.format("%02d", fsFileo));
                        returnCode = fsFileo;
                        System.out.println("-");
                    }
                }

            } catch (IOException e) {
                System.out.println("WRITE");
                fsFileo = 30;
                System.out.println(String.format("%02d", fsFileo));
                returnCode = fsFileo;
                System.out.println("-");
            }

            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                fsFileo = 0;
            } catch (IOException e) {
                System.out.println("CLOSE");
                fsFileo = 49;
                System.out.println(String.format("%02d", fsFileo));
                returnCode = fsFileo;
                System.out.println("-");
            }
        }

        System.exit(returnCode);
    }

    private static void writeString(String str) throws IOException {
        byte[] bytes = new byte[5];
        for (int i = 0; i < 5; i++) {
            if (i < str.length()) {
                bytes[i] = (byte) str.charAt(i);
            } else {
                bytes[i] = (byte) ' ';
            }
        }
        fileOutputStream.write(bytes);
    }

    private static void writeComp3(long value, int digits) throws IOException {
        int byteLength = (digits + 2) / 2;
        byte[] packed = new byte[byteLength];
        
        boolean isNegative = value < 0;
        long absValue = Math.abs(value);
        
        String numStr = String.format("%0" + digits + "d", absValue);
        if (numStr.length() > digits) {
            numStr = numStr.substring(numStr.length() - digits);
        }
        
        int nibbleIndex = 0;
        for (int i = 0; i < byteLength; i++) {
            int highNibble = 0;
            int lowNibble = 0;
            
            if (nibbleIndex < numStr.length()) {
                highNibble = numStr.charAt(nibbleIndex) - '0';
                nibbleIndex++;
            }
            
            if (i == byteLength - 1) {
                lowNibble = isNegative ? 0x0D : 0x0C;
            } else {
                if (nibbleIndex < numStr.length()) {
                    lowNibble = numStr.charAt(nibbleIndex) - '0';
                    nibbleIndex++;
                }
            }
            
            packed[i] = (byte) ((highNibble << 4) | lowNibble);
        }
        
        fileOutputStream.write(packed);
    }

    private static void writeComp3Unsigned(long value, int digits) throws IOException {
        int byteLength = (digits + 2) / 2;
        byte[] packed = new byte[byteLength];
        
        long absValue = Math.abs(value);
        
        String numStr = String.format("%0" + digits + "d", absValue);
        if (numStr.length() > digits) {
            numStr = numStr.substring(numStr.length() - digits);
        }
        
        int nibbleIndex = 0;
        for (int i = 0; i < byteLength; i++) {
            int highNibble = 0;
            int lowNibble = 0;
            
            if (nibbleIndex < numStr.length()) {
                highNibble = numStr.charAt(nibbleIndex) - '0';
                nibbleIndex++;
            }
            
            if (i == byteLength - 1) {
                lowNibble = 0x0C;
            } else {
                if (nibbleIndex < numStr.length()) {
                    lowNibble = numStr.charAt(nibbleIndex) - '0';
                    nibbleIndex++;
                }
            }
            
            packed[i] = (byte) ((highNibble << 4) | lowNibble);
        }
        
        fileOutputStream.write(packed);
    }
}