import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class cs01a {
    private static final String MYNAME = "cs01a";
    private static int wsCount = 0;
    private static int wsRecCount = 0;
    private static int depthLen = 0;
    private static int holdDepth = 0;
    private static int currDepth = 0;
    private static boolean inputDataEof = false;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try {
            perform8010ReadInptData();
            holdDepth = currDepth;

            while (!inputDataEof) {
                if (currDepth > holdDepth) {
                    wsCount++;
                }
                holdDepth = currDepth;
                perform8010ReadInptData();
            }

            System.out.println(MYNAME + " measurements larger than the previous measurement " + wsCount);
            System.out.println(MYNAME + " records read " + wsRecCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void perform8010ReadInptData() throws IOException {
        String inputLine = reader.readLine();
        if (inputLine == null) {
            inputDataEof = true;
            return;
        }

        wsRecCount++;
        String wsInptDepth = inputLine.length() > 8 ? inputLine.substring(0, 8) : inputLine;

        if (wsInptDepth.length() >= 4 && Character.isDigit(wsInptDepth.charAt(3))) {
            depthLen = 4;
        } else {
            depthLen = 3;
        }

        try {
            currDepth = Integer.parseInt(wsInptDepth.substring(0, depthLen));
        } catch (NumberFormatException e) {
            currDepth = 0;
        }
    }
}