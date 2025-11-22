import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC2020071 {
    private static final int MAX_BAGS = 594;
    private static final int MAX_QUEUE = 9999;
    private static final int MAX_BUFFER = 32;
    
    private static String[] wsBuffer = new String[MAX_BUFFER];
    private static String[] wsBagColor = new String[MAX_BAGS];
    private static int[] wsBagDone = new int[MAX_BAGS];
    private static int[] wsBagBagsNumber = new int[MAX_BAGS];
    private static String[][] wsBagBags = new String[MAX_BAGS][MAX_BUFFER];
    private static String[] wsBagsQueue = new String[MAX_QUEUE];
    
    private static int n = 0;
    private static int result = 0;
    private static int bagIdx = 1;
    private static int i = 1;
    private static int j = 1;
    private static int k = 1;
    private static int stringPtr = 1;
    private static int q1 = 1;
    private static int q2 = 1;

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("d07.input"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                parseRecord(line);
            }
            reader.close();
            
            walkGraph();
            countResult();
            
            System.out.println(q2);
            System.out.println(result);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void parseRecord(String inputRecord) {
        n++;
        stringPtr = 1;
        
        // Split the record by spaces
        String[] parts = inputRecord.trim().split("\\s+");
        for (int idx = 0; idx < Math.min(parts.length, MAX_BUFFER); idx++) {
            wsBuffer[idx] = parts[idx];
        }
        
        // Combine first two words as the bag color
        StringBuilder colorBuilder = new StringBuilder();
        colorBuilder.append(wsBuffer[0]).append(" ").append(wsBuffer[1]);
        wsBagColor[i] = colorBuilder.toString();
        
        // Check if there are sub-bags
        if (!wsBuffer[4].equals("no")) {
            parseSubBags();
        }
        
        i++;
    }
    
    private static void parseSubBags() {
        k = 1;
        for (j = 4; j < MAX_BUFFER; j += 4) {
            if (wsBuffer[j] != null && !wsBuffer[j].trim().isEmpty()) {
                StringBuilder subBagBuilder = new StringBuilder();
                subBagBuilder.append(wsBuffer[j + 1]).append(" ").append(wsBuffer[j + 2]);
                wsBagBags[i][k] = subBagBuilder.toString();
                k++;
            }
        }
        wsBagBagsNumber[i] = k - 1;
    }
    
    private static void walkGraph() {
        wsBagsQueue[1] = "shiny gold";
        q2 = 1;
        
        while (q1 <= q2) {
            walkGraphLoop();
        }
    }
    
    private static void walkGraphLoop() {
        wsBag = wsBagsQueue[q1];
        q1++;
        
        findBagIndex();
        wsBagDone[bagIdx] = 1;
        
        for (int idx = 1; idx <= n; idx++) {
            if (wsBagDone[idx] == 0) {
                for (j = 1; j <= wsBagBagsNumber[idx]; j++) {
                    if (wsBag.equals(wsBagBags[idx][j])) {
                        q2++;
                        wsBagsQueue[q2] = wsBagColor[idx];
                        break;
                    }
                }
            }
        }
    }
    
    private static void findBagIndex() {
        for (k = 1; k <= n; k++) {
            if (wsBag.equals(wsBagColor[k])) {
                bagIdx = k;
                break;
            }
        }
    }
    
    private static void countResult() {
        for (i = 1; i <= n; i++) {
            if (wsBagDone[i] == 1) {
                result++;
            }
        }
        result--;
    }
    
    // Temporary variable used in walkGraphLoop
    private static String wsBag;
}