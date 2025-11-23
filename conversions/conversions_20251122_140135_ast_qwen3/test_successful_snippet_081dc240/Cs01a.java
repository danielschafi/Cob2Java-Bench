import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Cs01a {
    private static final String MYNAME = "cs01a";
    private static int wsCount = 0;
    private static int wsRecCount = 0;
    private static int depthLen = 0;
    private static int holdDepth = 0;
    private static int currDepth = 0;
    private static boolean inptDataEof = false;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try {
            readInptData();
            holdDepth = currDepth;

            while (!inptDataEof) {
                if (currDepth > holdDepth) {
                    wsCount++;
                }
                holdDepth = currDepth;
                readInptData();
            }

            System.out.println(MYNAME + " measurements larger than the previous measurement " + wsCount);
            System.out.println(MYNAME + " records read " + wsRecCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readInptData() throws IOException {
        String inputLine = reader.readLine();
        if (inputLine == null) {
            inptDataEof = true;
            return;
        }
        
        wsRecCount++;
        
        String wsInptDepth = inputLine.length() > 8 ? inputLine.substring(0, 8) : inputLine;
        String wsInptByte4 = wsInptDepth.length() >= 4 ? wsInptDepth.substring(3, 4) : "";
        
        if (isNumeric(wsInptByte4)) {
            depthLen = 4;
        } else {
            depthLen = 3;
        }
        
        String depthStr = wsInptDepth.substring(0, depthLen);
        currDepth = Integer.parseInt(depthStr);
    }
    
    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}