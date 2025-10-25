import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021082 {
    private static final int FILE_STATUS = 0;
    private static int wsResult = 0;
    private static final String[] wsBuffer = new String[14];
    private static final int[][] wsBufferAsBinArr = new int[14][7];
    private static final int[][] wsDigitsAsBinArr = new int[10][7];
    private static final int[][] ws069 = new int[3][7];
    private static final int[][] ws235 = new int[3][7];
    private static char c;
    private static int i;
    private static int i5 = 1;
    private static int i6 = 1;
    private static int j;
    private static int k;
    private static int l;
    private static int len;
    private static int m;
    private static int idx9 = 0;
    private static int idx0 = 0;
    private static int idx3 = 0;
    private static int idx5 = 0;
    private static int stringPtr;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("d08.input"))) {
            String inputRecord;
            while ((inputRecord = reader.readLine()) != null) {
                processRecord(inputRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(wsResult);
    }

    private static void processRecord(String inputRecord) {
        idx9 = 0;
        idx0 = 0;
        idx3 = 0;
        idx5 = 0;
        i5 = 1;
        i6 = 1;

        stringPtr = 0;
        for (i = 0; i < 14; i++) {
            int start = stringPtr;
            while (stringPtr < inputRecord.length() && inputRecord.charAt(stringPtr) != ' ' && inputRecord.charAt(stringPtr) != '|') {
                stringPtr++;
            }
            wsBuffer[i] = inputRecord.substring(start, stringPtr).trim();
            stringPtr++;
        }

        for (i = 0; i < 14; i++) {
            m = 0;
            while (m < wsBuffer[i].length() && wsBuffer[i].charAt(wsBuffer[i].length() - 1 - m) == ' ') {
                m++;
            }
            len = 8 - m;

            for (j = 0; j < 7; j++) {
                c = (char) (97 + j);
                m = 0;
                for (int x = 0; x < wsBuffer[i].length(); x++) {
                    if (wsBuffer[i].charAt(x) == c) {
                        m++;
                    }
                }
                wsBufferAsBinArr[i][j] = m;
            }

            switch (len) {
                case 2:
                    for (j = 0; j < 7; j++) {
                        wsDigitsAsBinArr[2][j] = wsBufferAsBinArr[i][j];
                    }
                    break;
                case 3:
                    for (j = 0; j < 7; j++) {
                        wsDigitsAsBinArr[8][j] = wsBufferAsBinArr[i][j];
                    }
                    break;
                case 4:
                    for (j = 0; j < 7; j++) {
                        wsDigitsAsBinArr[5][j] = wsBufferAsBinArr[i][j];
                    }
                    break;
                case 7:
                    for (j = 0; j < 7; j++) {
                        wsDigitsAsBinArr[9][j] = wsBufferAsBinArr[i][j];
                    }
                    break;
                case 5:
                    if (i < 11) {
                        for (j = 0; j < 7; j++) {
                            ws235[i5 - 1][j] = wsBufferAsBinArr[i][j];
                        }
                        i5++;
                    }
                    break;
                case 6:
                    if (i < 11) {
                        for (j = 0; j < 7; j++) {
                            ws069[i6 - 1][j] = wsBufferAsBinArr[i][j];
                        }
                        i6++;
                    }
                    break;
            }
        }

        for (i = 0; i < 3; i++) {
            l = 1;
            for (j = 0; j < 7; j++) {
                if (wsDigitsAsBinArr[5][j] == 1) {
                    l *= ws069[i][j];
                }
            }
            if (l == 1) {
                idx9 = i + 1;
                for (j = 0; j < 7; j++) {
                    wsDigitsAsBinArr[10][j] = ws069[i][j];
                }
            }
        }

        for (i = 0; i < 3; i++) {
            l = 1;
            for (j = 0; j < 7; j++) {
                if (wsDigitsAsBinArr[2][j] == 1) {
                    l *= ws069[i][j];
                }
            }
            if (i != idx9 - 1 && l == 1) {
                idx0 = i + 1;
                for (j = 0; j < 7; j++) {
                    wsDigitsAsBinArr[1][j] = ws069[i][j];
                }
            }
        }

        for (i = 0; i < 3; i++) {
            if (i != idx9 - 1 && i != idx0 - 1) {
                for (j = 0; j < 7; j++) {
                    wsDigitsAsBinArr[7][j] = ws069[i][j];
                }
            }
        }

        for (i = 0; i < 3; i++) {
            l = 1;
            for (j = 0; j < 7; j++) {
                if (wsDigitsAsBinArr[2][j] == 1) {
                    l *= ws235[i][j];
                }
            }
            if (l == 1) {
                idx3 = i + 1;
                for (j = 0; j < 7; j++) {
                    wsDigitsAsBinArr[4][j] = ws235[i][j];
                }
            }
        }

        for (i = 0; i < 3; i++) {
            l = 1;
            for (j = 0; j < 7; j++) {
                if (ws235[i][j] == 1) {
                    l *= wsDigitsAsBinArr[7][j];
                }
            }
            if (l == 1) {
                idx5 = i + 1;
                for (j = 0; j < 7; j++) {
                    wsDigitsAsBinArr[6][j] = ws235[i][j];
                }
            }
        }

        for (i = 0; i < 3; i++) {
            if (i != idx3 - 1 && i != idx5 - 1) {
                for (j = 0; j < 7; j++) {
                    wsDigitsAsBinArr[3][j] = ws235[i][j];
                }
            }
        }

        int wsValDec = 0;
        for (i = 11; i < 15; i++) {
            for (k = 1; k < 11; k++) {
                l = 1;
                for (j = 0; j < 7; j++) {
                    if (wsDigitsAsBinArr[k - 1][j] != wsBufferAsBinArr[i][j]) {
                        l = 0;
                    }
                }
                if (l == 1) {
                    wsValDec = wsValDec * 10 + k - 1;
                }
            }
        }
        wsResult += wsValDec;
    }
}