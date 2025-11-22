import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class b64demo1 {
    private static final String MYNAME = "b64demo1";
    private static final String SIX_BIT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(MYNAME + " requires a command line argument of FILE or TEST");
            return;
        }

        String processType = args[0].toUpperCase();
        if ("FILE".equals(processType)) {
            processFavicon();
        } else if ("TEST".equals(processType)) {
            processWikipediaTests();
        } else {
            System.out.println(MYNAME + " requires a command line argument of FILE or TEST");
        }
    }

    private static void processFavicon() {
        byte[] inBuffer = new byte[3638];
        try (FileInputStream fis = new FileInputStream("favicon.ico")) {
            fis.read(inBuffer);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int inBufferLen = inBuffer.length;
        String outBuffer = Base64.getEncoder().encodeToString(inBuffer);
        int outBufferLen = outBuffer.length();

        System.out.println(outBuffer);
    }

    private static void processWikipediaTests() {
        String[] testStrings = {"light work.", "light work", "light wor", "light wo", "light w"};
        for (String testString : testStrings) {
            byte[] inBuffer = testString.getBytes();
            int inBufferLen = inBuffer.length;
            String outBuffer = Base64.getEncoder().encodeToString(inBuffer);
            int outBufferLen = outBuffer.length();

            System.out.println(MYNAME + " IN-BUFFER-LEN = " + inBufferLen);
            System.out.println(MYNAME + " IN-BUFFER = " + new String(inBuffer));
            System.out.println(MYNAME + " OUT-BUFFER-LEN = " + outBufferLen);
            System.out.println(MYNAME + " OUT-BUFFER = " + outBuffer);
        }
    }
}