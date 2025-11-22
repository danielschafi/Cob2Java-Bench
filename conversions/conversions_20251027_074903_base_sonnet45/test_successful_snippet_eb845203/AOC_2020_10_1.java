import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AOC_2020_10_1 {
    public static void main(String[] args) {
        int fileStatus = 0;
        int wsArrLen = 95;
        int[] wsArray = new int[99];
        int result = 0;
        int i = 1;
        int diff1 = 0;
        int diff3 = 0;
        int diff = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("d10.input"))) {
            wsArray[0] = 0;
            i = 1;
            String line;
            while ((line = br.readLine()) != null) {
                result++;
                wsArray[i] = Integer.parseInt(line.trim());
                i++;
            }
            fileStatus = 1;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        wsArrLen = i;
        Arrays.sort(wsArray, 0, wsArrLen);

        for (i = 1; i < wsArrLen; i++) {
            wsArray[i - 1] = wsArray[i];
        }
        wsArrLen--;

        wsArray[wsArrLen - 1] = wsArray[wsArrLen - 2] + 3;

        for (i = 1; i < wsArrLen; i++) {
            diff = wsArray[i] - wsArray[i - 1];
            if (diff == 1) {
                diff1++;
            }
            if (diff == 3) {
                diff3++;
            }
        }

        result = diff1 * diff3;
        System.out.println(result);
    }
}