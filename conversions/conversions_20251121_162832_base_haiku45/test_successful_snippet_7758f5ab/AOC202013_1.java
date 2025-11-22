import java.io.*;
import java.util.*;

public class AOC202013_1 {
    private static int recLen;
    private static int wsStart;
    private static int[] wsBuses = new int[100];
    private static int wsBuffer;
    private static int n = 99;
    private static int wsQuotient;
    private static int wsMod;
    private static int wsTime;
    private static int wsBusMin;
    private static int wsTimeMin = 99999;
    
    private static int result = 0;
    private static int stringPtr = 1;
    private static int i = 0;
    private static int j = 1;
    
    public static void main(String[] args) {
        try {
            read();
            findEarliestBus();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("d13.input"));
        
        String line1 = br.readLine();
        wsStart = Integer.parseInt(line1);
        
        String line2 = br.readLine();
        br.close();
        
        String[] parts = line2.split(",");
        j = 1;
        
        for (String part : parts) {
            wsBuffer = 0;
            try {
                wsBuffer = Integer.parseInt(part);
            } catch (NumberFormatException e) {
                wsBuffer = 0;
            }
            
            if (wsBuffer != 0) {
                wsBuses[j - 1] = wsBuffer;
                j++;
            }
        }
        
        n = j - 1;
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