import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class AOC2021121 {
    private static final int MAX_EDGES = 64;
    private static final int MAX_QUEUE_SIZE = 99999;
    private static final int MAX_PATH_LENGTH = 100;

    private static String[] v1 = new String[MAX_EDGES];
    private static String[] v2 = new String[MAX_EDGES];
    private static int n = 0;

    private static int[][] qLen = new int[MAX_QUEUE_SIZE][MAX_PATH_LENGTH];
    private static String[][] qV = new String[MAX_QUEUE_SIZE][MAX_PATH_LENGTH];
    private static int q1 = 1;
    private static int q2 = 0;
    private static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("d12.input"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("-");
            v1[n] = parts[0];
            v2[n] = parts[1];
            n++;
            v1[n] = parts[1];
            v2[n] = parts[0];
            n++;
        }
        reader.close();

        q2 = 1;
        qLen[q2][0] = 1;
        qV[q2][0] = "start";

        while (q1 <= q2) {
            int len = qLen[q1][0];
            String node1 = qV[q1][len - 1];
            if (node1.equals("end")) {
                result++;
            } else {
                for (int i = 0; i < n; i++) {
                    if (v1[i].equals(node1)) {
                        String node2 = v2[i];
                        int visited = 0;
                        if (node2.charAt(0) >= 'a') {
                            for (int j = 0; j < len; j++) {
                                if (qV[q1][j].equals(node2)) {
                                    visited = 1;
                                    break;
                                }
                            }
                        }
                        if (visited == 0) {
                            q2++;
                            qLen[q2][0] = len + 1;
                            for (int j = 0; j < len; j++) {
                                qV[q2][j] = qV[q1][j];
                            }
                            qV[q2][len] = node2;
                        }
                    }
                }
            }
            q1++;
        }

        System.out.println(result);
    }
}