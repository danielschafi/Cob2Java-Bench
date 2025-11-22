import java.util.Scanner;

public class b64demo2 {
    private static final String MYNAME = "b64demo2";
    private static final int MAX_INPUT_SIZE = 49152;
    private static final int MAX_OUTPUT_SIZE = 32768;
    
    private static String wsInput01Rec = "";
    private static boolean input01Eof = false;
    private static int inBufferLen = 1;
    private static String inBuffer = "";
    private static int outBufferLen = 0;
    private static String outBuffer = "";
    private static int returnCode = 0;

    public static void main(String[] args) {
        processStdin();
        System.exit(returnCode);
    }

    private static void processStdin() {
        fillInBuffer();
        
        B64Decod decoder = new B64Decod();
        int[] outLen = {0};
        String[] outBuf = {""};
        
        decoder.decode(inBufferLen, inBuffer, outLen, outBuf);
        
        if (decoder.getReturnCode() == 0) {
            System.out.print(outBuf[0]);
        } else {
            System.out.println("Error in conversion");
        }
        
        returnCode = decoder.getReturnCode();
    }

    private static void fillInBuffer() {
        readStdin();
        
        inBuffer = wsInput01Rec.trim();
        inBufferLen = inBuffer.length();
    }

    private static void readStdin() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            wsInput01Rec = scanner.nextLine();
        }
        scanner.close();
    }
}

class B64Decod {
    private static final String SIX_BIT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final int MAX_INPUT_SIZE = 49152;
    private static final int MAX_OUTPUT_SIZE = 32768;
    
    private int inBufferPtr = 0;
    private int outBufferPtr = 0;
    private int inBlockSize = 0;
    private int groupOfFourSub = 0;
    private String[] groupOfFour = new String[4];
    private long eightByteInt = 0;
    private int sixBitSub = 0;
    private int outBlockSize = 0;
    private String outBlock = "";
    private int nbGroupsOfFour = 0;
    private int nbGroupsOfFourR = 0;
    private boolean sixBitCharFound = false;
    private int returnCode = 0;
    
    public void decode(int inBufferLen, String inBuffer, int[] outLen, String[] outBuf) {
        outLen[0] = 0;
        outBuf[0] = "";
        
        convert(inBufferLen, inBuffer, outLen, outBuf);
    }
    
    private void convert(int inBufferLen, String inBuffer, int[] outLen, String[] outBuf) {
        nbGroupsOfFour = inBufferLen / 4;
        nbGroupsOfFourR = inBufferLen % 4;
        
        inBlockSize = 4;
        outBufferPtr = 0;
        
        for (int i = 0; i < nbGroupsOfFour; i++) {
            convertOneBlock(inBuffer, inBufferLen, outLen, outBuf);
        }
        
        if (nbGroupsOfFourR == 0) {
            returnCode = 0;
        } else if (nbGroupsOfFourR == 1) {
            returnCode = 12;
        } else {
            inBlockSize = nbGroupsOfFourR;
            convertOneBlock(inBuffer, inBufferLen, outLen, outBuf);
            returnCode = 0;
        }
    }
    
    private void convertOneBlock(String inBuffer, int inBufferLen, int[] outLen, String[] outBuf) {
        eightByteInt = 0;
        outBlockSize = 0;
        
        for (int i = 0; i < inBlockSize; i++) {
            if (inBufferPtr + i < inBuffer.length()) {
                groupOfFour[i] = String.valueOf(inBuffer.charAt(inBufferPtr + i));
            } else {
                groupOfFour[i] = "";
            }
        }
        
        for (int i = inBlockSize - 1; i >= 0; i--) {
            if (groupOfFour[i].equals("=")) {
                continue;
            } else {
                findSixBitChar(groupOfFour[i]);
                if (sixBitCharFound) {
                    sixBitSub--;
                    byte[] bytes = longToBytes(eightByteInt);
                    bytes[4 + i] = (byte)(sixBitSub & 0xFF);
                    eightByteInt = bytesToLong(bytes);
                    outBlockSize++;
                    eightByteInt = eightByteInt * 4;
                }
                inBufferPtr++;
            }
        }
        
        outBlockSize--;
        byte[] bytes = longToBytes(eightByteInt);
        StringBuilder sb = new StringBuilder(outBuf[0]);
        for (int i = 0; i < outBlockSize; i++) {
            sb.append((char)bytes[4 + i]);
        }
        outBuf[0] = sb.toString();
        outLen[0] += outBlockSize;
    }
    
    private void findSixBitChar(String ch) {
        sixBitCharFound = false;
        sixBitSub = 0;
        
        for (int i = 0; i < SIX_BIT_CHARS.length(); i++) {
            sixBitSub++;
            if (SIX_BIT_CHARS.charAt(i) == ch.charAt(0)) {
                sixBitCharFound = true;
                break;
            }
        }
    }
    
    private byte[] longToBytes(long value) {
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte)((value >> (56 - i * 8)) & 0xFF);
        }
        return bytes;
    }
    
    private long bytesToLong(byte[] bytes) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value = (value << 8) | (bytes[i] & 0xFF);
        }
        return value;
    }
    
    public int getReturnCode() {
        return returnCode;
    }
}