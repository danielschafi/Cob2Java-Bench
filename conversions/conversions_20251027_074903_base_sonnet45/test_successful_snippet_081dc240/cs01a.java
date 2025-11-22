import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class cs01a {
    private static final String MYNAME = "cs01a";
    private int wsCount = 0;
    private int wsRecCount = 0;
    private int depthLen = 0;
    private int holdDepth = 0;
    private int currDepth = 0;
    private String wsInptDepth = "";
    private boolean inptDataEof = false;
    private BufferedReader reader;

    public static void main(String[] args) {
        cs01a program = new cs01a();
        program.run();
    }

    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));

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

            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }

    private void readInptData() {
        wsInptDepth = "";
        try {
            String line = reader.readLine();
            if (line == null) {
                inptDataEof = true;
            } else {
                wsRecCount++;
                wsInptDepth = line.length() >= 8 ? line.substring(0, 8) : line;
                while (wsInptDepth.length() < 8) {
                    wsInptDepth += " ";
                }

                String byte4 = wsInptDepth.substring(3, 4);
                if (isNumeric(byte4)) {
                    depthLen = 4;
                } else {
                    depthLen = 3;
                }

                String depthStr = wsInptDepth.substring(0, depthLen).trim();
                try {
                    currDepth = Integer.parseInt(depthStr);
                } catch (NumberFormatException e) {
                    currDepth = 0;
                }
            }
        } catch (IOException e) {
            inptDataEof = true;
        }
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("[0-9]");
    }
}