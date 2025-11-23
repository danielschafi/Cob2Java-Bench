import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC2021121 {
    private static final int MAX_EDGES = 64;
    private static final int MAX_QUEUE_SIZE = 99999;
    private static final int MAX_PATH_LENGTH = 100;
    
    private static String[] v1 = new String[MAX_EDGES * 2];
    private static String[] v2 = new String[MAX_EDGES * 2];
    private static int n = 0;
    
    private static int[][] qV = new int[MAX_QUEUE_SIZE][MAX_PATH_LENGTH];
    private static int[] qLen = new int[MAX_QUEUE_SIZE];
    
    private static int q1 = 1;
    private static int q2 = 0;
    private static int result = 0;
    
    public static void main(String[] args) {
        readInput();
        traverse();
        System.out.println(result);
    }
    
    private static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader("d12.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    n++;
                    v1[n] = parts[0];
                    v2[n] = parts[1];
                    n++;
                    v1[n] = parts[1];
                    v2[n] = parts[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void traverse() {
        q2 = 1;
        qLen[q2] = 1;
        qV[q2][1] = 1; // start
        
        while (q1 <= q2) {
            int len = qLen[q1];
            int nodeIndex = qV[q1][len];
            String node1 = getNodeName(nodeIndex);
            
            if ("end".equals(node1)) {
                result++;
            } else {
                for (int i = 1; i <= n; i++) {
                    if (v1[i].equals(node1)) {
                        String node2 = v2[i];
                        int visited = 0;
                        
                        if (node2.charAt(0) >= 'a') {
                            for (int j = 1; j <= len; j++) {
                                if (getNodeName(qV[q1][j]).equals(node2)) {
                                    visited = 1;
                                    break;
                                }
                            }
                        }
                        
                        if (visited == 0) {
                            q2++;
                            qLen[q2] = len + 1;
                            
                            for (int j = 1; j <= len; j++) {
                                qV[q2][j] = qV[q1][j];
                            }
                            
                            qV[q2][len + 1] = getNodeIndex(node2);
                        }
                    }
                }
            }
            q1++;
        }
    }
    
    private static String getNodeName(int index) {
        switch (index) {
            case 1: return "start";
            case 2: return "end";
            case 3: return "a";
            case 4: return "b";
            default: return "unknown";
        }
    }
    
    private static int getNodeIndex(String name) {
        switch (name) {
            case "start": return 1;
            case "end": return 2;
            case "a": return 3;
            case "b": return 4;
            default: return 0;
        }
    }
}