import java.io.*;
import java.nio.file.*;
import java.util.Base64;

public class b64demo1 {
    private static final String MYNAME = "b64demo1";
    private static final String[] SIX_BIT_CHARS = {
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdef",
        "ghijklmnopqrstuvwxyz0123456789+/"
    };

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(MYNAME + " requires a command line argument of FILE or TEST");
            return;
        }

        String processType = args[0].toUpperCase();
        if (processType.equals("FILE")) {
            processFavicon();
        } else if (processType.equals("TEST")) {
            processWikipediaTests();
        } else {
            System.out.println(MYNAME + " requires a command line argument of FILE or TEST");
        }
    }

    private static void processFavicon() {
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get("favicon.ico"));
            String encoded = Base64.getEncoder().encodeToString(fileContent);
            System.out.println(encoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processWikipediaTests() {
        String[] testStrings = {"light work.", "light work", "light wor", "light wo", "light w"};
        for (String testString : testStrings) {
            byte[] inputBuffer = testString.getBytes();
            String encoded = Base64.getEncoder().encodeToString(inputBuffer);
            System.out.println(MYNAME + " IN-BUFFER-LEN = " + inputBuffer.length);
            System.out.println(MYNAME + " IN-BUFFER = " + new String(inputBuffer));
            System.out.println(MYNAME + " OUT-BUFFER-LEN = " + encoded.length());
            System.out.println(MYNAME + " OUT-BUFFER = " + encoded);
        }
    }
}

class B64ENCOD {
    private static final String[] SIX_BIT_CHARS = {
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdef",
        "ghijklmnopqrstuvwxyz0123456789+/"
    };

    public static void encode(int inBufferLen, byte[] inBuffer, int[] outBufferLen, char[] outBuffer) {
        int inBufferPtr = 0;
        int outBufferPtr = 0;
        int nbGroupsOfThree = inBufferLen / 3;
        int nbGroupsOfThreeR = inBufferLen % 3;
        int nbBytes;
        int outBlockSub = 5;
        char[] outBlock = new char[4];

        for (int i = 0; i < nbGroupsOfThree; i++) {
            nbBytes = 3;
            outBlockSub = 5;
            outBlock = new char[4];
            convertOneBlock(inBuffer, inBufferPtr, nbBytes, outBlock, outBlockSub, outBuffer, outBufferPtr);
            inBufferPtr += 3;
            outBufferPtr += 4;
        }

        switch (nbGroupsOfThreeR) {
            case 2:
                nbBytes = 3;
                outBlockSub = 5;
                outBlock = new char[4];
                convertOneBlock(inBuffer, inBufferPtr, nbBytes, outBlock, outBlockSub, outBuffer, outBufferPtr);
                outBuffer[outBufferPtr + 3] = '=';
                outBufferPtr += 4;
                break;
            case 1:
                nbBytes = 2;
                outBlockSub = 5;
                outBlock = new char[4];
                convertOneBlock(inBuffer, inBufferPtr, nbBytes, outBlock, outBlockSub, outBuffer, outBufferPtr);
                outBuffer[outBufferPtr + 2] = '=';
                outBuffer[outBufferPtr + 3] = '=';
                outBufferPtr += 4;
                break;
        }

        outBufferLen[0] = outBufferPtr;
    }

    private static void convertOneBlock(byte[] inBuffer, int inBufferPtr, int nbBytes, char[] outBlock, int outBlockSub, char[] outBuffer, int outBufferPtr) {
        int fourByteInt = 0;
        for (int i = 0; i < nbBytes; i++) {
            fourByteInt = (fourByteInt << 8) | (inBuffer[inBufferPtr + i] & 0xFF);
        }

        for (int i = 0; i < nbBytes; i++) {
            outBlockSub--;
            outBlock[outBlockSub] = SIX_BIT_CHARS[(fourByteInt & 0x3F)];
            fourByteInt >>= 6;
        }

        System.arraycopy(outBlock, outBlockSub, outBuffer, outBufferPtr, 4 - outBlockSub);
    }
}