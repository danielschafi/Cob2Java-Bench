import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class B64demo2 {
    private static final int MAX_INPUT_SIZE = 49152;
    private static final int MAX_OUTPUT_SIZE = 32768;
    
    private String wsInput01Rec = "";
    private boolean input01Eof = false;
    private int inBufferLen = 1;
    private byte[] inBuffer = new byte[MAX_INPUT_SIZE];
    private int outBufferLen = 0;
    private byte[] outBuffer = new byte[MAX_OUTPUT_SIZE];
    private int returnCode = 0;

    public static void main(String[] args) {
        B64demo2 program = new B64demo2();
        program.run();
        System.exit(program.returnCode);
    }

    private void run() {
        processStdin();
    }

    private void processStdin() {
        fillInBuffer();
        
        B64DECOD decoder = new B64DECOD();
        returnCode = decoder.decode(inBufferLen, inBuffer, outBuffer);
        outBufferLen = decoder.getOutBufferLen();
        
        if (returnCode == 0) {
            System.out.write(outBuffer, 0, outBufferLen);
            System.out.flush();
        } else {
            System.out.println("Error in conversion");
        }
    }

    private void fillInBuffer() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            readStdin(reader);
            
            int pointer = 0;
            for (int i = 0; i < wsInput01Rec.length() && wsInput01Rec.charAt(i) != ' '; i++) {
                if (pointer >= MAX_INPUT_SIZE) {
                    abort();
                    return;
                }
                inBuffer[pointer++] = (byte) wsInput01Rec.charAt(i);
            }
            inBufferLen = pointer;
            
        } catch (IOException e) {
            abort();
        }
    }

    private void readStdin(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) {
            input01Eof = true;
            wsInput01Rec = "";
        } else {
            wsInput01Rec = String.format("%-" + MAX_INPUT_SIZE + "s", line);
        }
    }

    private void abort() {
        returnCode = 12;
        System.exit(returnCode);
    }

    static class B64DECOD {
        private static final String SIX_BIT_CHARS = 
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        
        private int inBufferPtr = 0;
        private int outBufferPtr = 0;
        private int inBlockSize = 0;
        private byte[] groupOfFour = new byte[4];
        private long eightByteInt = 0;
        private int sixBitSub = 0;
        private int outBlockSize = 0;
        private int nbGroupsOfFour = 0;
        private int nbGroupsOfFourR = 0;
        private boolean sixBitCharFound = false;
        private int outBufferLen = 0;

        public int decode(int inBufferLen, byte[] inBuffer, byte[] outBuffer) {
            outBufferLen = 0;
            inBufferPtr = 0;
            outBufferPtr = 0;
            
            return convert(inBufferLen, inBuffer, outBuffer);
        }

        public int getOutBufferLen() {
            return outBufferLen;
        }

        private int convert(int inBufferLen, byte[] inBuffer, byte[] outBuffer) {
            nbGroupsOfFour = inBufferLen / 4;
            nbGroupsOfFourR = inBufferLen % 4;
            
            inBlockSize = 4;
            
            for (int i = 0; i < nbGroupsOfFour; i++) {
                convertOneBlock(inBuffer, outBuffer, inBufferLen);
            }
            
            int returnCode;
            if (nbGroupsOfFourR == 0) {
                returnCode = 0;
            } else if (nbGroupsOfFourR == 1) {
                returnCode = 12;
            } else {
                inBlockSize = nbGroupsOfFourR;
                convertOneBlock(inBuffer, outBuffer, inBufferLen);
                returnCode = 0;
            }
            
            return returnCode;
        }

        private void convertOneBlock(byte[] inBuffer, byte[] outBuffer, int inBufferLen) {
            eightByteInt = 0;
            outBlockSize = 0;
            
            for (int i = 0; i < 4; i++) {
                groupOfFour[i] = 0;
            }
            
            for (int i = 0; i < inBlockSize && inBufferPtr < inBufferLen; i++) {
                groupOfFour[i] = inBuffer[inBufferPtr + i];
            }
            
            for (int groupOfFourSub = inBlockSize - 1; groupOfFourSub >= 0 && inBufferPtr < inBufferLen; groupOfFourSub--) {
                if (groupOfFour[groupOfFourSub] == '=') {
                    continue;
                } else {
                    findSixBitChar(groupOfFourSub);
                    if (sixBitCharFound) {
                        int zeroBasedIndex = sixBitSub - 1;
                        int bytePosition = groupOfFourSub + 4;
                        
                        long mask = 0xFFL << ((7 - bytePosition) * 8);
                        eightByteInt = eightByteInt & ~mask;
                        eightByteInt = eightByteInt | (((long) zeroBasedIndex & 0xFF) << ((7 - bytePosition) * 8));
                        
                        outBlockSize++;
                        eightByteInt = eightByteInt * 4;
                    }
                    inBufferPtr++;
                }
            }
            
            outBlockSize--;
            
            for (int i = 0; i < outBlockSize && outBufferPtr < outBuffer.length; i++) {
                int bytePos = 4 + i;
                outBuffer[outBufferPtr++] = (byte) ((eightByteInt >> ((7 - bytePos) * 8)) & 0xFF);
            }
            
            outBufferLen += outBlockSize;
        }

        private void findSixBitChar(int groupOfFourSub) {
            sixBitCharFound = false;
            sixBitSub = 0;
            
            for (int i = 0; i < SIX_BIT_CHARS.length(); i++) {
                sixBitSub = i + 1;
                if (SIX_BIT_CHARS.charAt(i) == (char) groupOfFour[groupOfFourSub]) {
                    sixBitCharFound = true;
                    break;
                }
            }
        }
    }
}