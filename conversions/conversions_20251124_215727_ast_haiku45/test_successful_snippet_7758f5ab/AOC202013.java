import java.io.*;
import java.util.*;

public class AOC202013 {
    private static int wsStart = 0;
    private static int[] wsBuses = new int[99];
    private static int n = 0;
    private static int wsQuotient = 0;
    private static int wsMod = 0;
    private static int wsTime = 0;
    private static int wsBusMin = 0;
    private static int wsTimeMin = 99999;
    private static int result = 0;
    private static int stringPtr = 1;
    private static int i = 0;
    private static int j = 1;

    public static void main(String[] args) {
        read();
        findEarliestBus();
        System.out.println(result);
    }

    private static void read() {
        try (BufferedReader br = new BufferedReader(new FileReader("d13.input"))) {
            String line = br.readLine();
            wsStart = Integer.parseInt(line);
            
            line = br.readLine();
            stringPtr = 0;
            j = 0;
            
            String[] parts = line.split(",");
            for (String part : parts) {
                if (!part.equals("x")) {
                    int busNum = Integer.parseInt(part);
                    if (busNum != 0) {
                        wsBuses[j] = busNum;
                        j++;
                    }
                }
            }
            n = j;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findEarliestBus() {
        for (i = 0; i < n; i++) {
            wsQuotient = wsStart / wsBuses[i];
            wsMod = wsStart % wsBuses[i];
            wsTime = wsBuses[i] - wsMod;
            
            if (wsTime < wsTimeMin) {
                wsTimeMin = wsTime;
                wsBusMin = wsBuses[i];
            }
        }
        result = wsTimeMin * wsBusMin;
    }
}