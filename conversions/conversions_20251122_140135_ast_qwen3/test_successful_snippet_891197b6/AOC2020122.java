import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020122 {
    private static int FILE_STATUS = 0;
    private static int REC_LEN = 0;
    private static int WX = -1;
    private static int WY = 10;
    private static int W0 = 0;
    private static int X = 0;
    private static int Y = 0;
    private static int N = 0;
    private static int ARG = 0;
    private static String INPUT_ACTION = "";
    private static String INPUT_ARG = "";

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d12.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 2) {
                    INPUT_ACTION = line.substring(0, 1);
                    INPUT_ARG = line.substring(1).trim();
                    processRecord();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        N = Math.abs(X) + Math.abs(Y);
        System.out.println(N);
    }

    private static void processRecord() {
        ARG = Integer.parseInt(INPUT_ARG);
        navigate();
    }

    private static void navigate() {
        if (INPUT_ACTION.equals("F")) {
            X = X + WX * ARG;
            Y = Y + WY * ARG;
            return;
        }

        N = ARG / 90;

        if (INPUT_ACTION.equals("L")) {
            for (int i = 0; i < N; i++) {
                W0 = WX;
                WX = -1 * WY;
                WY = W0;
            }
            return;
        }

        if (INPUT_ACTION.equals("R")) {
            for (int i = 0; i < N; i++) {
                W0 = WX;
                WX = WY;
                WY = -1 * W0;
            }
            return;
        }

        switch (INPUT_ACTION) {
            case "N":
                WX = WX - ARG;
                break;
            case "W":
                WY = WY - ARG;
                break;
            case "S":
                WX = WX + ARG;
                break;
            case "E":
                WY = WY + ARG;
                break;
        }
    }
}