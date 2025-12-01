import java.io.*;
import java.util.*;

public class AOC202013_2 {
    private static final int MAX_BUSES = 99;
    private long[] wsBuses = new long[MAX_BUSES];
    private long[] wsRemainders = new long[MAX_BUSES];
    private int len = 99;
    private long result = 0;

    public static void main(String[] args) {
        AOC202013_2 program = new AOC202013_2();
        program.run();
    }

    private void run() {
        read();
        findTimestamp();
        System.out.println(result);
    }

    private void read() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d13.input"))) {
            String line1 = reader.readLine();
            String line2 = reader.readLine();

            int j = 1;
            String[] parts = line2.split(",");

            for (int i = 0; i < parts.length; i++) {
                String buffer = parts[i].trim();
                if (!buffer.isEmpty()) {
                    long wsI = Long.parseLong(buffer);
                    if (wsI != 0) {
                        wsBuses[j - 1] = wsI;
                        long wsM = wsI - i - 1;
                        long quotient = wsM / wsI;
                        wsM = wsM % wsI;
                        if (wsM < 0) {
                            wsM += wsI;
                        }
                        wsRemainders[j - 1] = wsM;
                        j++;
                    }
                }
            }
            len = j - 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findTimestamp() {
        long n = wsBuses[0];
        long a = wsRemainders[0];

        for (int i = 1; i < len; i++) {
            long n1 = wsBuses[i];
            long a1 = wsRemainders[i];
            long wsMod = 0;
            long wsQuotient = 1;

            while (wsMod != a1) {
                a = a + n;
                wsQuotient = a / n1;
                wsMod = a % n1;
            }

            n = n * n1;
        }

        result = a;
    }
}