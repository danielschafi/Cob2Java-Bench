import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC20210802 {
    private int wsResult = 0;
    private String[] wsBuffer = new String[14];
    private int wsValDec = 0;
    private int[][] wsBufferAsBin = new int[14][7];
    private int[][] wsDigitsAsBin = new int[10][7];
    private int[][] ws069 = new int[3][7];
    private int[][] ws235 = new int[3][7];
    private int i5 = 0;
    private int i6 = 0;
    private int idx0 = 0;
    private int idx9 = 0;
    private int idx3 = 0;
    private int idx5 = 0;

    public static void main(String[] args) {
        AOC20210802 program = new AOC20210802();
        program.run();
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d08.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
            System.out.println(wsResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRecord(String inputRecord) {
        idx9 = 0;
        idx0 = 0;
        idx3 = 0;
        idx5 = 0;
        i5 = 0;
        i6 = 0;

        for (int i = 0; i < 14; i++) {
            wsBuffer[i] = "";
            for (int j = 0; j < 7; j++) {
                wsBufferAsBin[i][j] = 0;
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                wsDigitsAsBin[i][j] = 0;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                ws069[i][j] = 0;
                ws235[i][j] = 0;
            }
        }

        String[] tokens = inputRecord.split("\\s+\\|\\s+|\\s+");
        for (int i = 0; i < tokens.length && i < 14; i++) {
            wsBuffer[i] = tokens[i];
        }

        for (int i = 0; i < 14; i++) {
            int len = wsBuffer[i].length();

            for (int j = 0; j < 7; j++) {
                char c = (char) ('a' + j);
                int count = 0;
                for (int k = 0; k < wsBuffer[i].length(); k++) {
                    if (wsBuffer[i].charAt(k) == c) {
                        count++;
                    }
                }
                wsBufferAsBin[i][j] = count;
            }

            switch (len) {
                case 2:
                    for (int j = 0; j < 7; j++) {
                        wsDigitsAsBin[1][j] = wsBufferAsBin[i][j];
                    }
                    break;
                case 3:
                    for (int j = 0; j < 7; j++) {
                        wsDigitsAsBin[7][j] = wsBufferAsBin[i][j];
                    }
                    break;
                case 4:
                    for (int j = 0; j < 7; j++) {
                        wsDigitsAsBin[4][j] = wsBufferAsBin[i][j];
                    }
                    break;
                case 7:
                    for (int j = 0; j < 7; j++) {
                        wsDigitsAsBin[8][j] = wsBufferAsBin[i][j];
                    }
                    break;
                case 5:
                    if (i < 10) {
                        for (int j = 0; j < 7; j++) {
                            ws235[i5][j] = wsBufferAsBin[i][j];
                        }
                        i5++;
                    }
                    break;
                case 6:
                    if (i < 10) {
                        for (int j = 0; j < 7; j++) {
                            ws069[i6][j] = wsBufferAsBin[i][j];
                        }
                        i6++;
                    }
                    break;
            }
        }

        for (int i = 0; i < 3; i++) {
            int l = 1;
            for (int j = 0; j < 7; j++) {
                if (wsDigitsAsBin[4][j] == 1) {
                    l = l * ws069[i][j];
                }
            }
            if (l == 1) {
                idx9 = i;
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBin[9][j] = ws069[i][j];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            int l = 1;
            for (int j = 0; j < 7; j++) {
                if (wsDigitsAsBin[1][j] == 1) {
                    l = l * ws069[i][j];
                }
            }
            if (i != idx9 && l == 1) {
                idx0 = i;
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBin[0][j] = ws069[i][j];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (i != idx9 && i != idx0) {
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBin[6][j] = ws069[i][j];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            int l = 1;
            for (int j = 0; j < 7; j++) {
                if (wsDigitsAsBin[1][j] == 1) {
                    l = l * ws235[i][j];
                }
            }
            if (l == 1) {
                idx3 = i;
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBin[3][j] = ws235[i][j];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            int l = 1;
            for (int j = 0; j < 7; j++) {
                if (ws235[i][j] == 1) {
                    l = l * wsDigitsAsBin[6][j];
                }
            }
            if (l == 1) {
                idx5 = i;
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBin[5][j] = ws235[i][j];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (i != idx3 && i != idx5) {
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBin[2][j] = ws235[i][j];
                }
            }
        }

        wsValDec = 0;
        for (int i = 10; i < 14; i++) {
            for (int k = 0; k < 10; k++) {
                int l = 1;