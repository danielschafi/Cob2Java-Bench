import java.io.*;
import java.util.Arrays;

public class AOC2020101 {
    public static void main(String[] args) {
        String inputFile = "d10.input";
        int[] wsArray = new int[95];
        int fileStatus = 0;
        int recLen = 0;
        int wsArrLen = 95;
        int result = 0;
        int i = 1;
        int diff1 = 0;
        int diff3 = 0;
        int diff = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            wsArray[0] = 0;
            i = 1;
            String line;
            while ((line = reader.readLine()) != null) {
                wsArray[i] = Integer.parseInt(line);
                i++;
            }
        } catch (IOException e) {
            fileStatus = 1;
        }

        Arrays.sort(wsArray, 0, i);
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