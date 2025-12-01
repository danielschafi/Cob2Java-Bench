import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class b64demo2 {
    private static final String BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    
    private static int inBufferLen = 1;
    private static byte[] inBuffer = new byte[49152];
    private static int outBufferLen = 0;
    private static byte[] outBuffer = new byte[32768];
    
    private static int inBufferPtr = 1;
    private static int outBufferPtr = 1;
    private static int inBlockSize = 0;
    private static int groupOfFourSub = 0;
    private static byte[] groupOfFourTbl = new byte[4];
    private static long eightByteInt = 0;
    private static int sixBitSub = 0;
    private static boolean sixBitCharFound = false;
    private static int outBlockSize = 0;
    private static int nbGroupsOfFour = 0;
    private static int nbGroupsOfFourR = 0;
    
    public static void main(String[] args) {
        processStdin();
    }
    
    private static void processStdin() {
        fillInBuffer();
        
        b64Decode();
        
        if (B64DECOD.returnCode == 0) {
            System.out.print(new String(outBuffer, 0, outBufferLen));
        } else {
            System.out.println("Error in conversion");
        }
    }
    
    private static void fillInBuffer() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            if (line != null) {
                int spaceIndex = line.indexOf(' ');
                if (spaceIndex == -1) {
                    inBuffer = line.getBytes();
                    inBufferLen = line.length();
                } else {
                    String trimmed = line.substring(0, spaceIndex);
                    inBuffer = trimmed.getBytes();
                    inBufferLen = trimmed.length();
                }
            }
        } catch (IOException e) {
            B64DECOD.returnCode = 12;
        }
    }
    
    private static void b64Decode() {
        B64DECOD.decode(inBufferLen, inBuffer, outBufferLen, outBuffer);
    }
}

class B64DECOD {
    private static final String BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    
    public static int returnCode = 0;
    
    private static int inBufferPtr = 1;
    private static int outBufferPtr = 1;
    private static int inBlockSize = 0;
    private static int groupOfFourSub = 0;
    private static byte[] groupOfFourTbl = new byte[4];
    private static long eightByteInt = 0;
    private static int sixBitSub = 0;
    private static boolean sixBitCharFound = false;
    private static int outBlockSize = 0;
    private static int nbGroupsOfFour = 0;
    private static int nbGroupsOfFourR = 0;
    
    public static void decode(int inBufferLen, byte[] inBuffer, int outBufferLen, byte[] outBuffer) {
        outBufferLen = 0;
        convert(inBufferLen, inBuffer, outBufferLen, outBuffer);
    }
    
    private static void convert(int inBufferLen, byte[] inBuffer, int outBufferLen, byte[] outBuffer) {
        nbGroupsOfFour = inBufferLen / 4;
        nbGroupsOfFourR = inBufferLen % 4;
        inBlockSize = 4;
        
        for (int i = 0; i < nbGroupsOfFour; i++) {
            convertOneBlock(inBuffer, outBuffer, inBufferLen, outBufferLen);
        }
        
        if (nbGroupsOfFourR == 0) {
            returnCode = 0;
        } else if (nbGroupsOfFourR == 1) {
            returnCode = 12;
        } else {
            inBlockSize = nbGroupsOfFourR;
            convertOneBlock(inBuffer, outBuffer, inBufferLen, outBufferLen);
            returnCode = 0;
        }
    }
    
    private static void convertOneBlock(byte[] inBuffer, byte[] outBuffer, int inBufferLen, int outBufferLen) {
        eightByteInt = 0;
        outBlockSize = 0;
        
        for (int i = 0; i < inBlockSize && inBufferPtr <= inBufferLen; i++) {
            groupOfFourTbl[i] = inBuffer[inBufferPtr - 1];
        }
        
        for (int i = inBlockSize - 1; i >= 0 && inBufferPtr <= inBufferLen; i--) {
            if (groupOfFourTbl[i] == '=') {
                continue;
            } else {
                findSixBitChar(groupOfFourTbl[i]);
                if (sixBitCharFound) {
                    sixBitSub--;
                    byte sixBitByte = (byte) (sixBitSub & 0xFF);
                    eightByteInt = (eightByteInt << 8) | (sixBitByte & 0xFF);
                    outBlockSize++;
                    eightByteInt *= 4;
                }
                inBufferPtr++;
            }
        }
        
        outBlockSize--;
        byte[] bytes = longToBytes(eightByteInt);
        for (int i = 0; i < outBlockSize; i++) {
            outBuffer[outBufferPtr - 1 + i] = bytes[4 + i];
        }
        outBufferPtr += outBlockSize;
        outBufferLen += outBlockSize;
    }
    
    private static void findSixBitChar(byte target) {
        sixBitCharFound = false;
        sixBitSub = 0;
        
        while (sixBitSub < BASE64_CHARS.length() && !sixBitCharFound) {
            sixBitSub++;
            if (BASE64_CHARS.charAt(sixBitSub - 1) == (char) target) {
                sixBitCharFound = true;
            }
        }
    }
    
    private static byte[] longToBytes(long value) {
        byte[] bytes = new byte[8];
        for (int i = 7; i >= 0; i--) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= 8;
        }
        return bytes;
    }
}