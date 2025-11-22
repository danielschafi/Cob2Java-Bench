import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC2020101 {
    private static final int MAX_SIZE = 99;
    private static int[] wsArray = new int[MAX_SIZE];
    private static int wsArrLen = 95;
    private static int result = 0;
    private static int i = 1;
    private static int diff1 = 0;
    private static int diff3 = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d10.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Arrays.sort(wsArray, 0, wsArrLen);
        shiftArray();
        wsArray[wsArrLen] = wsArray[wsArrLen - 1] + 3;
        useAdapters();

        System.out.println(diff1 * diff3);
    }

    private static void processRecord(String record) {
        result++;
        wsArray[i] = Integer.parseInt(record.trim());
        i++;
    }

    private static void shiftArray() {
        for (int j = 0; j < wsArrLen - 1; j++) {
            wsArray[j] = wsArray[j + 1];
        }
    }

    private static void useAdapters() {
        for (int j = 0; j < wsArrLen - 1; j++) {
            int diff = wsArray[j + 1] - wsArray[j];
            if (diff == 1) {
                diff1++;
            }
            if (diff == 3) {
                diff3++;
            }
        }
    }
}