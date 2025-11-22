import java.io.*;
import java.util.Arrays;

public class AOC2020101 {
    private static final int MAX_ARRAY_SIZE = 95;
    private static final String INPUT_FILE = "d10.input";

    public static void main(String[] args) {
        int[] wsArray = new int[MAX_ARRAY_SIZE];
        int fileStatus = 0;
        int recLen = 0;
        int wsArrLen = 95;
        int i = 1;
        int diff1 = 0;
        int diff3 = 0;
        int diff = 0;
        int result = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            wsArray[0] = 0;
            i = 1;
            String line;
            while ((line = reader.readLine()) != null) {
                wsArray[i] = Integer.parseInt(line.trim());
                i++;
            }
        } catch (IOException e) {
            fileStatus = 1;
        }

        Arrays.sort(wsArray, 0, i - 1);
        for (int j = 0; j < i - 1; j++) {
            wsArray[j] = wsArray[j + 1];
        }
        wsArray[i - 1] = wsArray[i - 2] + 3;

        for (int j = 0; j < i - 1; j++) {
            diff = wsArray[j + 1] - wsArray[j];
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