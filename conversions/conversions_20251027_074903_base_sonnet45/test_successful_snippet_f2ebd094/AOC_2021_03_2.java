import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC_2021_03_2 {
    private static final int N = 12;
    private static final int M = 1000;
    private int wsOxyDec = 0;
    private int wsCo2Dec = 0;
    private String[] wsArray = new String[M];
    private int[] wsOxyFlag = new int[M];
    private int[] wsCo2Flag = new int[M];
    private int[] wsCountsOxy = new int[N];
    private int[] wsCountsCo2 = new int[N];
    private int j = 0;
    private int wsOxyIdx = 0;
    private int wsCo2Idx = 0;
    private int wsOxyRows = 0;
    private int wsCo2Rows = 0;

    public static void main(String[] args) {
        AOC_2021_03_2 program = new AOC_2021_03_2();
        program.run();
    }

    private void run() {
        readFile();
        filterNumbers();
        computeDecimals();
        long wsResult = (long) wsOxyDec * wsCo2Dec;
        System.out.println(wsResult);
    }

    private void readFile() {
        for (int i = 0; i < M; i++) {
            wsOxyFlag[i] = 1;
            wsCo2Flag[i] = 1;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("d03.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRecord(String inputRecord) {
        wsArray[j] = inputRecord;
        j++;
        for (int i = 0; i < N; i++) {
            if (inputRecord.charAt(i) == '1') {
                wsCountsOxy[i]++;
                wsCountsCo2[i]++;
            }
        }
    }

    private void filterNumbers() {
        wsOxyRows = j;
        wsCo2Rows = j;
        for (int i = 0; i < N; i++) {
            filterByBit(i);
        }
    }

    private void filterByBit(int i) {
        char wsOxyBadBit;
        char wsCo2BadBit;

        if (wsCountsOxy[i] < wsOxyRows / 2.0) {
            wsOxyBadBit = '1';
        } else {
            wsOxyBadBit = '0';
        }

        if (wsCountsCo2[i] >= wsCo2Rows / 2.0) {
            wsCo2BadBit = '1';
        } else {
            wsCo2BadBit = '0';
        }

        for (int jIdx = 0; jIdx < j; jIdx++) {
            if (wsOxyFlag[jIdx] == 1 && wsOxyRows > 1) {
                if (wsArray[jIdx].charAt(i) == wsOxyBadBit) {
                    wsOxyFlag[jIdx] = 0;
                    wsOxyRows--;
                    for (int k = i; k < N; k++) {
                        if (wsArray[jIdx].charAt(k) == '1') {
                            wsCountsOxy[k]--;
                        }
                    }
                }
            }

            if (wsCo2Flag[jIdx] == 1 && wsCo2Rows > 1) {
                if (wsArray[jIdx].charAt(i) == wsCo2BadBit) {
                    wsCo2Flag[jIdx] = 0;
                    wsCo2Rows--;
                    for (int k = i; k < N; k++) {
                        if (wsArray[jIdx].charAt(k) == '1') {
                            wsCountsCo2[k]--;
                        }
                    }
                }
            }

            if (wsOxyFlag[jIdx] == 1 && wsOxyRows == 1) {
                wsOxyIdx = jIdx;
            }

            if (wsCo2Flag[jIdx] == 1 && wsCo2Rows == 1) {
                wsCo2Idx = jIdx;
            }
        }
    }

    private void computeDecimals() {
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