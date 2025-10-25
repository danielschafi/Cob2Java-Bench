import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021082 {
    private static final int FILE_STATUS = 0;
    private static int WS_RESULT = 0;
    private static final char[][] WS_BUFFER = new char[14][8];
    private static int WS_VAL_DEC = 0;
    private static final int[][] WS_BUFFER_AS_BIN = new int[14][7];
    private static final int[][] WS_DIGITS_AS_BIN = new int[10][7];
    private static final int[][] WS_069 = new int[3][7];
    private static final int[][] WS_235 = new int[3][7];
    private static char C;
    private static int I;
    private static int I5 = 1;
    private static int I6 = 1;
    private static int J;
    private static int K;
    private static int L;
    private static int LEN;
    private static int M;
    private static int IDX_0 = 0;
    private static int IDX_9 = 0;
    private static int IDX_3 = 0;
    private static int IDX_5 = 0;
    private static int STRING_PTR = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d08.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(WS_RESULT);
    }

    private static void processRecord(String inputRecord) {
        IDX_9 = 0;
        IDX_0 = 0;
        IDX_3 = 0;
        IDX_5 = 0;
        I5 = 1;
        I6 = 1;
        STRING_PTR = 0;

        for (I = 0; I < 14; I++) {
            int endIndex = inputRecord.indexOf(" | ", STRING_PTR);
            if (endIndex == -1) {
                endIndex = inputRecord.length();
            }
            inputRecord.getChars(STRING_PTR, endIndex, WS_BUFFER[I], 0);
            STRING_PTR = endIndex + 3;
        }

        for (I = 0; I < 14; I++) {
            M = 0;
            while (M < 8 && WS_BUFFER[I][7 - M - 1] == ' ') {
                M++;
            }
            LEN = 8 - M;

            for (J = 0; J < 7; J++) {
                C = (char) (97 + J);
                M = 0;
                for (int k = 0; k < LEN; k++) {
                    if (WS_BUFFER[I][k] == C) {
                        M++;
                    }
                }
                WS_BUFFER_AS_BIN[I][J] = M;
            }

            switch (LEN) {
                case 2:
                    for (J = 0; J < 7; J++) {
                        WS_DIGITS_AS_BIN[2][J] = WS_BUFFER_AS_BIN[I][J];
                    }
                    break;
                case 3:
                    for (J = 0; J < 7; J++) {
                        WS_DIGITS_AS_BIN[8][J] = WS_BUFFER_AS_BIN[I][J];
                    }
                    break;
                case 4:
                    for (J = 0; J < 7; J++) {
                        WS_DIGITS_AS_BIN[5][J] = WS_BUFFER_AS_BIN[I][J];
                    }
                    break;
                case 7:
                    for (J = 0; J < 7; J++) {
                        WS_DIGITS_AS_BIN[9][J] = WS_BUFFER_AS_BIN[I][J];
                    }
                    break;
                case 5:
                    if (I < 11) {
                        for (J = 0; J < 7; J++) {
                            WS_235[I5][J] = WS_BUFFER_AS_BIN[I][J];
                        }
                        I5++;
                    }
                    break;
                case 6:
                    if (I < 11) {
                        for (J = 0; J < 7; J++) {
                            WS_069[I6][J] = WS_BUFFER_AS_BIN[I][J];
                        }
                        I6++;
                    }
                    break;
            }
        }

        for (I = 0; I < 3; I++) {
            L = 1;
            for (J = 0; J < 7; J++) {
                if (WS_DIGITS_AS_BIN[5][J] == 1) {
                    L *= WS_069[I][J];
                }
            }
            if (L == 1) {
                IDX_9 = I;
                for (J = 0; J < 7; J++) {
                    WS_DIGITS_AS_BIN[10][J] = WS_069[I][J];
                }
            }
        }

        for (I = 0; I < 3; I++) {
            L = 1;
            for (J = 0; J < 7; J++) {
                if (WS_DIGITS_AS_BIN[2][J] == 1) {
                    L *= WS_069[I][J];
                }
            }
            if (I != IDX_9 && L == 1) {
                IDX_0 = I;
                for (J = 0; J < 7; J++) {
                    WS_DIGITS_AS_BIN[1][J] = WS_069[I][J];
                }
            }
        }

        for (I = 0; I < 3; I++) {
            if (I != IDX_9 && I != IDX_0) {
                for (J = 0; J < 7; J++) {
                    WS_DIGITS_AS_BIN[7][J] = WS_069[I][J];
                }
            }
        }

        for (I = 0; I < 3; I++) {
            L = 1;
            for (J = 0; J < 7; J++) {
                if (WS_DIGITS_AS_BIN[2][J] == 1) {
                    L *= WS_235[I][J];
                }
            }
            if (L == 1) {
                IDX_3 = I;
                for (J = 0; J < 7; J++) {
                    WS_DIGITS_AS_BIN[4][J] = WS_235[I][J];
                }
            }
        }

        for (I = 0; I < 3; I++) {
            L = 1;
            for (J = 0; J < 7; J++) {
                if (WS_235[I][J] == 1) {
                    L *= WS_DIGITS_AS_BIN[7][J];
                }
            }
            if (L == 1) {
                IDX_5 = I;
                for (J = 0; J < 7; J++) {
                    WS_DIGITS_AS_BIN[6][J] = WS_235[I][J];
                }
            }
        }

        for (I = 0; I < 3; I++) {
            if (I != IDX_3 && I != IDX_5) {
                for (J = 0; J < 7; J++) {
                    WS_DIGITS_AS_BIN[3][J] = WS_235[I][J];
                }
            }
        }

        WS_VAL_DEC = 0;
        for (I = 10; I < 14; I++) {
            for (K = 0; K < 10; K++) {
                L = 1;
                for (J = 0; J < 7; J++) {
                    if (WS_DIGITS_AS_BIN[K][J] != WS_BUFFER_AS_BIN[I][J]) {
                        L = 0;
                    }
                }
                if (L == 1) {
                    WS_VAL_DEC = WS_VAL_DEC * 10 + K + 1;
                }
            }
        }
        WS_RESULT += WS_VAL_DEC;
    }
}