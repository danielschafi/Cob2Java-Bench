import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021032 {
    private static final int N = 12;
    private static final int M = 1000;
    private static int fileStatus = 0;
    private static int wsOxyDec = 0;
    private static int wsCo2Dec = 0;
    private static final String[] wsArray = new String[M];
    private static final int[] wsOxyFlag = new int[M];
    private static final int[] wsCo2Flag = new int[M];
    private static final int[] wsCountsOxy = new int[N];
    private static final int[] wsCountsCo2 = new int[N];
    private static int j = 1;
    private static int wsOxyIdx = 0;
    private static int wsCo2Idx = 0;
    private static int wsOxyRows = 0;
    private static int wsCo2Rows = 0;
    private static char wsOxyBadBit;
    private static char wsCo2BadBit;

    public static void main(String[] args) {
        openInput();
        while (fileStatus != 1) {
            read();
        }
        closeInput();
        filterNumbers();
        computeDecimals();
        long wsResult = (long) wsOxyDec * wsCo2Dec;
        System.out.println(wsResult);
    }

    private static void openInput() {
        for (int i = 0; i < M; i++) {
            wsOxyFlag[i] = 1;
            wsCo2Flag[i] = 1;
        }
    }

    private static void read() {
        try (BufferedReader br = new BufferedReader(new FileReader("d03.input"))) {
            String line;
            while ((line = br.readLine()) != null && j <= M) {
                wsArray[j - 1] = line;
                processRecord(line);
                j++;
            }
            fileStatus = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecord(String inputRecord) {
        for (int i = 0; i < N; i++) {
            if (inputRecord.charAt(i) == '1') {
                wsCountsOxy[i]++;
                wsCountsCo2[i]++;
            }
        }
    }

    private static void closeInput() {
        j = 1;
    }

    private static void filterNumbers() {
        wsOxyRows = M;
        wsCo2Rows = M;
        for (int i = 0; i < N; i++) {
            filterByBit(i);
        }
    }

    private static void filterByBit(int i) {
        if (wsCountsOxy[i] < wsOxyRows / 2) {
            wsOxyBadBit = '1';
        } else {
            wsOxyBadBit = '0';
        }

        if (wsCountsCo2[i] >= wsCo2Rows / 2) {
            wsCo2BadBit = '1';
        } else {
            wsCo2BadBit = '0';
        }

        for (int j = 0; j < M; j++) {
            if (wsOxyFlag[j] == 1 && wsOxyRows > 1) {
                if (wsArray[j].charAt(i) == wsOxyBadBit) {
                    wsOxyFlag[j] = 0;
                    wsOxyRows--;
                    for (int k = i; k < N; k++) {
                        if (wsArray[j].charAt(k) == '1') {
                            wsCountsOxy[k]--;
                        }
                    }
                }
            }

            if (wsCo2Flag[j] == 1 && wsCo2Rows > 1) {
                if (wsArray[j].charAt(i) == wsCo2BadBit) {
                    wsCo2Flag[j] = 0;
                    wsCo2Rows--;
                    for (int k = i; k < N; k++) {
                        if (wsArray[j].charAt(k) == '1') {
                            wsCountsCo2[k]--;
                        }
                    }
                }
            }

            if (wsOxyFlag[j] == 1 && wsOxyRows == 1) {
                wsOxyIdx = j;
            }

            if (wsCo2Flag[j] == 1 && wsCo2Rows == 1) {
                wsCo2Idx = j;
            }
        }
    }

    private static void computeDecimals() {
        for (int i = 0; i < N; i++) {
            wsOxyDec = wsOxyDec * 2;
            wsCo2Dec = wsCo2Dec * 2;
            if (wsArray[wsOxyIdx].charAt(i) == '1') {
                wsOxyDec = wsOxyDec + 1;
            }
            if (wsArray[wsCo2Idx].charAt(i) == '1') {
                wsCo2Dec = wsCo2Dec + 1;
            }
        }
    }
}