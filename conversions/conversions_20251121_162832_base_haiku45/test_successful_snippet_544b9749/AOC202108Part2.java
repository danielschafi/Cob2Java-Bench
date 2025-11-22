import java.io.*;
import java.util.*;

public class AOC202108Part2 {
    private static int fileStatus = 0;
    private static int wsResult = 0;
    private static String[] wsBuffer = new String[15];
    private static int wsValDec = 0;
    private static int[][] wsBufferAsBinArr = new int[15][8];
    private static int[][] wsDigitsAsBinArr = new int[11][8];
    private static int[][][] ws069 = new int[4][8];
    private static int[][][] ws235 = new int[4][8];

    public static void main(String[] args) {
        try {
            processFile("d08.input");
            System.out.println(wsResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            processRecord(line);
        }
        reader.close();
    }

    private static void processRecord(String inputRecord) {
        int idx9 = 0, idx0 = 0, idx3 = 0, idx5 = 0;
        int i5 = 1, i6 = 1;

        // Initialize arrays
        for (int i = 0; i < 15; i++) {
            wsBuffer[i] = "";
            for (int j = 0; j < 8; j++) {
                wsBufferAsBinArr[i][j] = 0;
            }
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 8; j++) {
                wsDigitsAsBinArr[i][j] = 0;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                ws069[i][j] = 0;
                ws235[i][j] = 0;
            }
        }

        // Parse input
        String[] parts = inputRecord.split(" \\| ");
        String[] allParts = new String[14];
        int idx = 0;
        for (String part : parts) {
            String[] subParts = part.split(" ");
            for (String subPart : subParts) {
                if (idx < 14) {
                    allParts[idx] = subPart;
                    idx++;
                }
            }
        }

        for (int i = 0; i < 14; i++) {
            wsBuffer[i] = allParts[i];
        }

        // Convert to binary representation
        for (int i = 0; i < 14; i++) {
            String buffer = wsBuffer[i];
            int len = buffer.length();

            for (int j = 0; j < 7; j++) {
                char c = (char) (97 + j);
                int count = 0;
                for (char ch : buffer.toCharArray()) {
                    if (ch == c) count++;
                }
                wsBufferAsBinArr[i][j] = count;
            }

            switch (len) {
                case 2:
                    for (int j = 0; j < 7; j++) {
                        wsDigitsAsBinArr[2][j] = wsBufferAsBinArr[i][j];
                    }
                    break;
                case 3:
                    for (int j = 0; j < 7; j++) {
                        wsDigitsAsBinArr[8][j] = wsBufferAsBinArr[i][j];
                    }
                    break;
                case 4:
                    for (int j = 0; j < 7; j++) {
                        wsDigitsAsBinArr[5][j] = wsBufferAsBinArr[i][j];
                    }
                    break;
                case 7:
                    for (int j = 0; j < 7; j++) {
                        wsDigitsAsBinArr[9][j] = wsBufferAsBinArr[i][j];
                    }
                    break;
                case 5:
                    if (i < 10) {
                        for (int j = 0; j < 7; j++) {
                            ws235[i5][j] = wsBufferAsBinArr[i][j];
                        }
                        i5++;
                    }
                    break;
                case 6:
                    if (i < 10) {
                        for (int j = 0; j < 7; j++) {
                            ws069[i6][j] = wsBufferAsBinArr[i][j];
                        }
                        i6++;
                    }
                    break;
            }
        }

        // Identify 0, 6, 9
        for (int i = 1; i <= 3; i++) {
            int l = 1;
            for (int j = 0; j < 7; j++) {
                if (wsDigitsAsBinArr[5][j] == 1) {
                    l = l * ws069[i][j];
                }
            }
            if (l == 1) {
                idx9 = i;
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBinArr[10][j] = ws069[i][j];
                }
            }
        }

        for (int i = 1; i <= 3; i++) {
            int l = 1;
            for (int j = 0; j < 7; j++) {
                if (wsDigitsAsBinArr[2][j] == 1) {
                    l = l * ws069[i][j];
                }
            }
            if (i != idx9 && l == 1) {
                idx0 = i;
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBinArr[1][j] = ws069[i][j];
                }
            }
        }

        for (int i = 1; i <= 3; i++) {
            if (i != idx9 && i != idx0) {
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBinArr[7][j] = ws069[i][j];
                }
            }
        }

        // Identify 2, 3, 5
        for (int i = 1; i <= 3; i++) {
            int l = 1;
            for (int j = 0; j < 7; j++) {
                if (wsDigitsAsBinArr[2][j] == 1) {
                    l = l * ws235[i][j];
                }
            }
            if (l == 1) {
                idx3 = i;
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBinArr[4][j] = ws235[i][j];
                }
            }
        }

        for (int i = 1; i <= 3; i++) {
            int l = 1;
            for (int j = 0; j < 7; j++) {
                if (ws235[i][j] == 1) {
                    l = l * wsDigitsAsBinArr[7][j];
                }
            }
            if (l == 1) {
                idx5 = i;
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBinArr[6][j] = ws235[i][j];
                }
            }
        }

        for (int i = 1; i <= 3; i++) {
            if (i != idx3 && i != idx5) {
                for (int j = 0; j < 7; j++) {
                    wsDigitsAsBinArr[3][j] = ws235[i][j];