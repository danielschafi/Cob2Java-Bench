import java.io.*;
import java.util.*;

public class B64demo2 {
    private static final String SIX_BIT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    
    public static void main(String[] args) {
        try {
            StringBuilder inputBuffer = new StringBuilder();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            while ((line = reader.readLine()) != null) {
                inputBuffer.append(line);
            }
            
            byte[] inBuffer = inputBuffer.toString().getBytes();
            int inBufferLen = inBuffer.length;
            byte[] outBuffer = new byte[32768];
            int outBufferLen = 0;
            
            int returnCode = b64decod(inBufferLen, inBuffer, outBuffer);
            
            if (returnCode == 0) {
                System.out.print(new String(outBuffer, 0, outBufferLen));
            } else {
                System.err.println("Error in conversion");
            }
        } catch (Exception e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }
    
    private static int b64decod(int inBufferLen, byte[] inBuffer, byte[] outBuffer) {
        int outBufferLen = 0;
        int inBufferPtr = 0;
        int outBufferPtr = 0;
        int nbGroupsOfFour = inBufferLen / 4;
        int nbGroupsOfFourR = inBufferLen % 4;
        int inBlockSize = 4;
        
        // Process full groups of four
        for (int i = 0; i < nbGroupsOfFour; i++) {
            outBufferLen = convertOneBlock(inBuffer, inBufferLen, inBufferPtr, inBlockSize, outBuffer, outBufferPtr, outBufferLen);
            inBufferPtr += 4;
            outBufferPtr = outBufferLen;
        }
        
        int returnCode;
        switch (nbGroupsOfFourR) {
            case 0:
                returnCode = 0;
                break;
            case 1:
                returnCode = 12;
                break;
            default:
                inBlockSize = nbGroupsOfFourR;
                outBufferLen = convertOneBlock(inBuffer, inBufferLen, inBufferPtr, inBlockSize, outBuffer, outBufferPtr, outBufferLen);
                returnCode = 0;
                break;
        }
        
        return returnCode;
    }
    
    private static int convertOneBlock(byte[] inBuffer, int inBufferLen, int inBufferPtr, int inBlockSize, byte[] outBuffer, int outBufferPtr, int outBufferLen) {
        byte[] groupOfFour = new byte[4];
        long eightByteInt = 0;
        int outBlockSize = 0;
        
        // Copy group of four characters
        for (int i = 0; i < inBlockSize && (inBufferPtr + i) < inBufferLen; i++) {
            groupOfFour[i] = inBuffer[inBufferPtr + i];
        }
        
        // Process each character in reverse order
        for (int i = inBlockSize - 1; i >= 0; i--) {
            if (groupOfFour[i] == '=') {
                continue;
            } else {
                int sixBitValue = findSixBitChar(groupOfFour[i]);
                if (sixBitValue != -1) {
                    // Convert to zero-based index
                    sixBitValue--;
                    // Place in appropriate byte position
                    int bytePosition = i + 4;
                    if (bytePosition < 8) {
                        eightByteInt |= ((long) sixBitValue & 0xFFL) << (bytePosition * 8);
                        outBlockSize++;
                    }
                    // Shift left logical 2 bits (multiply by 4)
                    eightByteInt *= 4;
                }
            }
        }
        
        // Adjust output block size
        outBlockSize--;
        
        // Copy result to output buffer
        int bytesToCopy = Math.min(outBlockSize, 8);
        for (int i = 0; i < bytesToCopy; i++) {
            outBuffer[outBufferLen + i] = (byte) ((eightByteInt >> ((bytesToCopy - 1 - i) * 8)) & 0xFF);
        }
        
        outBufferLen += bytesToCopy;
        return outBufferLen;
    }
    
    private static int findSixBitChar(byte ch) {
        int index = SIX_BIT_CHARS.indexOf(ch);
        if (index == -1) {
            return -1;
        }
        return index + 1; // 1-based indexing
    }
}