import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class B64Demo2 {
    private static final String[] SIX_BIT_CHARS_VAL = {
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdef",
        "ghijklmnopqrstuvwxyz0123456789+/"
    };
    private static final char[] SIX_BIT_CHARS_TBL = new char[64];

    static {
        int index = 0;
        for (String part : SIX_BIT_CHARS_VAL) {
            for (char c : part.toCharArray()) {
                SIX_BIT_CHARS_TBL[index++] = c;
            }
        }
    }

    public static void main(String[] args) {
        try {
            StringBuilder inputBuffer = new StringBuilder();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while ((line = reader.readLine()) != null) {
                inputBuffer.append(line);
            }
            reader.close();

            String inputStr = inputBuffer.toString();
            byte[] inBuffer = inputStr.getBytes(StandardCharsets.US_ASCII);
            int inBufferLen = inBuffer.length;

            byte[] outBuffer = new byte[32768];
            int outBufferLen = 0;

            int returnCode = convert(inBuffer, inBufferLen, outBuffer, outBufferLen);

            if (returnCode == 0) {
                System.out.write(outBuffer, 0, outBufferLen);
                System.out.write('\n');
            } else {
                System.err.println("Error in conversion");
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }

    private static int convert(byte[] inBuffer, int inBufferLen, byte[] outBuffer, int outBufferLen) {
        int nbGroupsOfFour = inBufferLen / 4;
        int nbGroupsOfFourR = inBufferLen % 4;

        int inBlockSize = 4;

        for (int i = 0; i < nbGroupsOfFour; i++) {
            convertOneBlock(inBuffer, inBufferLen, outBuffer, outBufferLen, inBlockSize);
        }

        switch (nbGroupsOfFourR) {
            case 0:
                return 0;
            case 1:
                return 12;
            default:
                inBlockSize = nbGroupsOfFourR;
                convertOneBlock(inBuffer, inBufferLen, outBuffer, outBufferLen, inBlockSize);
                return 0;
        }
    }

    private static void convertOneBlock(byte[] inBuffer, int inBufferLen, byte[] outBuffer, int outBufferLen, int inBlockSize) {
        long eightByteInt = 0L;
        int outBlockSize = 0;

        for (int groupOfFourSub = inBlockSize; groupOfFourSub >= 1; groupOfFourSub--) {
            int bufferIndex = 0;
            for (int i = 0; i < groupOfFourSub; i++) {
                bufferIndex = (groupOfFourSub - i - 1) * 4 + i;
                if (bufferIndex >= inBufferLen) break;
                if (inBuffer[bufferIndex] == '=') continue;

                int sixBitSub = findSixBitChar((char) inBuffer[bufferIndex]);
                if (sixBitSub >= 0) {
                    sixBitSub--;
                    eightByteInt |= ((long) (sixBitSub & 0xFF)) << (bufferIndex * 8);
                    outBlockSize++;
                    eightByteInt *= 4;
                }
            }
        }

        outBlockSize--;
        if (outBlockSize > 0) {
            System.arraycopy(
                Arrays.copyOfRange(
                    ByteBuffer.allocate(8).putLong(eightByteInt).array(),
                    4,
                    4 + outBlockSize
                ),
                0,
                outBuffer,
                outBufferLen,
                outBlockSize
            );
        }
        outBufferLen += outBlockSize;
    }

    private static int findSixBitChar(char ch) {
        for (int i = 0; i < SIX_BIT_CHARS_TBL.length; i++) {
            if (SIX_BIT_CHARS_TBL[i] == ch) {
                return i + 1;
            }
        }
        return -1;
    }
}