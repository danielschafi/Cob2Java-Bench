import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021032 {
    private static final int N = 12;
    private static final int M = 1000;
    private static final String INPUTFILE = "d03.input";

    private int fileStatus = 0;
    private int wsOxyDec = 0;
    private int wsCo2Dec = 0;
    private String[] wsArray = new String[M];
    private int[] wsOxyFlag = new int[M];
    private int[] wsCo2Flag = new int[M];
    private int[] wsCountsOxy = new int[N];
    private int[] wsCountsCo2 = new int[N];
    private String wsInput;
    private long wsResult;
    private int i;
    private int j;
    private int k;
    private int wsOxyIdx;
    private int wsCo2Idx;
    private int wsOxyRows;
    private int wsCo2Rows;
    private char wsOxyBadBit;
    private char wsCo2BadBit;

    public static void main(String[] args) {
        AOC2021032 program = new AOC2021032();
        program.run();
    }

    private void run() {
        readFile();
        filterNumbers();
        computeDecimals();
        wsResult = (long) wsOxyDec * wsCo2Dec;
        System.out.println(wsResult);
    }

    private void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUTFILE))) {
            while ((wsInput = br.readLine()) != null && j < M) {
                wsArray[j] = wsInput;
                for (i = 0; i < N; i++) {
                    if (wsInput.charAt(i) == '1') {
                        wsCountsOxy[i]++;
                        wsCountsCo2[i]++;
                    }
                }
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileStatus = 1;
    }

    private void filterNumbers() {
        wsOxyRows = M;
        wsCo2Rows = M;
        for (i = 0; i < N; i++) {
            filterByBit();
        }
    }

    private void filterByBit() {
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

        for (j = 0; j < M; j++) {
            if (wsOxyFlag[j] == 1 && wsOxyRows > 1) {
                if (wsArray[j].charAt(i) == wsOxyBadBit) {
                    wsOxyFlag[j] = 0;
                    wsOxyRows--;
                    for (k = i; k < N; k++) {
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
                    for (k = i; k < N; k++) {
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

    private void computeDecimals() {
        for (i = 0; i < N; i++) {
            wsOxyDec *= 2;
            wsCo2Dec *= 2;
            if (wsArray[wsOxyIdx].charAt(i) == '1') {
                wsOxyDec += 1;
            }
            if (wsArray[wsCo2Idx].charAt(i) == '1') {
                wsCo2Dec += 1;
            }
        }
    }
}