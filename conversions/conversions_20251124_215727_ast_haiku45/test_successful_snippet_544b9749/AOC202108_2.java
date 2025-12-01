import java.io.*;
import java.util.*;

public class AOC202108_2 {
    private int fileStatus = 0;
    private long wsResult = 0;
    private String[] wsBuffer = new String[14];
    private int wsValDec = 0;
    private int[][] wsBufferAsBin = new int[14][7];
    private int[][] wsDigitsAsBin = new int[10][7];
    private int[][][] ws069 = new int[3][7];
    private int[][][] ws235 = new int[3][7];
    
    private char c;
    private int i, i5, i6, j, k, l, len, m;
    private int idx0, idx9, idx3, idx5;
    private int stringPtr;

    public static void main(String[] args) {
        AOC202108_2 program = new AOC202108_2();
        program.run();
    }

    private void run() {
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(wsResult);
    }

    private void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("d08.input"));
        String line;
        while ((line = reader.readLine()) != null) {
            processRecord(line);
        }
        reader.close();
    }

    private void processRecord(String inputRecord) {
        idx9 = 0;
        idx0 = 0;
        idx3 = 0;
        idx5 = 0;
        i5 = 1;
        i6 = 1;

        String[] parts = inputRecord.split(" \\| | ");
        for (i = 0; i < Math.min(14, parts.length); i++) {
            wsBuffer[i] = parts[i];
        }

        for (i = 0; i < 14; i++) {
            if (wsBuffer[i] == null) continue;
            
            m = 0;
            for (int x = wsBuffer[i].length() - 1; x >= 0 && wsBuffer[i].charAt(x) == ' '; x--) {
                m++;
            }
            len = 8 - m;

            for (j = 0; j < 7; j++) {
                c = (char) (97 + j);
                m = 0;
                for (char ch : wsBuffer[i].toCharArray()) {
                    if (ch == c) m++;
                }
                wsBufferAsBin[i][j] = m;
            }

            switch (len) {
                case 2:
                    for (j = 0; j < 7; j++) {
                        wsDigitsAsBin[1][j] = wsBufferAsBin[i][j];
                    }
                    break;
                case 3:
                    for (j = 0; j < 7; j++) {
                        wsDigitsAsBin[7][j] = wsBufferAsBin[i][j];
                    }
                    break;
                case 4:
                    for (j = 0; j < 7; j++) {
                        wsDigitsAsBin[4][j] = wsBufferAsBin[i][j];
                    }
                    break;
                case 7:
                    for (j = 0; j < 7; j++) {
                        wsDigitsAsBin[8][j] = wsBufferAsBin[i][j];
                    }
                    break;
                case 5:
                    if (i < 10) {
                        for (j = 0; j < 7; j++) {
                            ws235[i5 - 1][j] = wsBufferAsBin[i][j];
                        }
                        i5++;
                    }
                    break;
                case 6:
                    if (i < 10) {
                        for (j = 0; j < 7; j++) {
                            ws069[i6 - 1][j] = wsBufferAsBin[i][j];
                        }
                        i6++;
                    }
                    break;
            }
        }

        for (i = 0; i < 3; i++) {
            l = 1;
            for (j = 0; j < 7; j++) {
                if (wsDigitsAsBin[4][j] == 1) {
                    l = l * ws069[i][j];
                }
            }
            if (l == 1) {
                idx9 = i;
                for (j = 0; j < 7; j++) {
                    wsDigitsAsBin[9][j] = ws069[i][j];
                }
            }
        }

        for (i = 0; i < 3; i++) {
            l = 1;
            for (j = 0; j < 7; j++) {
                if (wsDigitsAsBin[1][j] == 1) {
                    l = l * ws069[i][j];
                }
            }
            if (i != idx9 && l == 1) {
                idx0 = i;
                for (j = 0; j < 7; j++) {
                    wsDigitsAsBin[0][j] = ws069[i][j];
                }
            }
        }

        for (i = 0; i < 3; i++) {
            if (i != idx9 && i != idx0) {
                for (j = 0; j < 7; j++) {
                    wsDigitsAsBin[6][j] = ws069[i][j];
                }
            }
        }

        for (i = 0; i < 3; i++) {
            l = 1;
            for (j = 0; j < 7; j++) {
                if (wsDigitsAsBin[1][j] == 1) {
                    l = l * ws235[i][j];
                }
            }
            if (l == 1) {
                idx3 = i;
                for (j = 0; j < 7; j++) {
                    wsDigitsAsBin[3][j] = ws235[i][j];
                }
            }
        }

        for (i = 0; i < 3; i++) {
            l = 1;
            for (j = 0; j < 7; j++) {
                if (ws235[i][j] == 1) {
                    l = l * wsDigitsAsBin[6][j];
                }
            }
            if (l == 1) {
                idx5 = i;
                for (j = 0; j < 7; j++) {
                    wsDigitsAsBin[5][j] = ws235[i][j];
                }
            }
        }

        for (i = 0; i < 3; i++) {
            if (i != idx3 && i != idx5) {
                for (j = 0; j < 7; j++) {
                    wsDigitsAsBin[2][j] = ws235[i][j];
                }
            }
        }

        wsValDec = 0;
        for (i = 10; i < 14; i++) {
            if (wsBuffer[i] == null) continue;
            
            for (k = 0; k < 10; k++) {
                l = 1;
                for (j = 0; j < 7; j++) {
                    if (wsDigitsAsBin[k][j] != wsBufferAsBin[i][j]) {
                        l = 0;
                    }
                }
                if (l == 1) {
                    wsValDec = wsValDec * 10 + k;
                }
            }
        }
        wsResult += wsValDec;
    }
}