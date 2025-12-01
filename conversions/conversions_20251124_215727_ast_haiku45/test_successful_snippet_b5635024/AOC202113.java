import java.io.*;
import java.util.*;

public class AOC202113 {
    private static final int ARRAY_SIZE = 2000;
    private int[][] wsDot = new int[ARRAY_SIZE][ARRAY_SIZE];
    private int wsResult = 0;
    private int n = 2000;
    private int i = 1;
    private int j = 1;
    private int k = 1;
    private int i1 = 1;
    private int j1 = 1;
    private int x = 0;
    private int y = 0;
    private int fileStatus = 0;
    private BufferedReader reader;

    public static void main(String[] args) {
        AOC202113 program = new AOC202113();
        program.run();
    }

    private void run() {
        try {
            reader = new BufferedReader(new FileReader("d13.input"));
            perform002Read();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void perform002Read() throws IOException {
        String inputRecord;
        while ((inputRecord = reader.readLine()) != null) {
            perform003ProcessRecord(inputRecord);
            if (fileStatus == 1) {
                break;
            }
        }
        fileStatus = 1;
    }

    private void perform003ProcessRecord(String inputRecord) {
        if (inputRecord.length() > 0 && inputRecord.charAt(0) == ' ') {
            x = n;
            y = n;
            return;
        }

        if (inputRecord.length() > 0 && inputRecord.charAt(0) != 'f') {
            perform004PlaceDot(inputRecord);
        } else {
            if (inputRecord.length() > 11 && inputRecord.charAt(11) == 'x') {
                String xStr = inputRecord.substring(13, Math.min(16, inputRecord.length())).trim();
                x = Integer.parseInt(xStr);
                x = x + 1;
                perform005FoldX();
            } else {
                String yStr = inputRecord.substring(13, Math.min(16, inputRecord.length())).trim();
                y = Integer.parseInt(yStr);
                y = y + 1;
                perform006FoldY();
            }
            perform007CountDots();
            System.out.println(wsResult);
            fileStatus = 1;
        }
    }

    private void perform004PlaceDot(String inputRecord) {
        String[] parts = inputRecord.split(",");
        x = Integer.parseInt(parts[0].trim());
        y = Integer.parseInt(parts[1].trim());
        x = x + 1;
        y = y + 1;
        wsDot[x][y] = 1;
    }

    private void perform005FoldX() {
        i1 = x + 1;
        for (j = 1; j <= y; j++) {
            wsDot[x][j] = 0;
            for (i = i1; i <= 2 * x; i++) {
                if (wsDot[i][j] == 1) {
                    k = 2 * x - i;
                    wsDot[k][j] = 1;
                    wsDot[i][j] = 0;
                }
            }
        }
    }

    private void perform006FoldY() {
        j1 = y + 1;
        for (i = 1; i <= x; i++) {
            wsDot[i][y] = 0;
            for (j = j1; j <= 2 * y; j++) {
                if (wsDot[i][j] == 1) {
                    k = 2 * y - j;
                    wsDot[i][k] = 1;
                    wsDot[i][j] = 0;
                }
            }
        }
    }

    private void perform007CountDots() {
        for (i = 1; i <= y - 1; i++) {
            for (j = 1; j <= x - 1; j++) {
                if (wsDot[j][i] == 1) {
                    wsResult = wsResult + 1;
                }
            }
        }
    }
}