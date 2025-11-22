import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AOC2021122 {
    private static final int MAX_EDGES = 64;
    private static final int MAX_QUEUE_SIZE = 999999;
    private static final int MAX_PATH_LENGTH = 100;
    
    private static String[] v1 = new String[MAX_EDGES];
    private static String[] v2 = new String[MAX_EDGES];
    private static int n = 0;
    
    private static int[][] qV = new int[MAX_QUEUE_SIZE][MAX_PATH_LENGTH];
    private static int[] qLen = new int[MAX_QUEUE_SIZE];
    private static String[] qDouble = new String[MAX_QUEUE_SIZE];
    
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
        
        traverse();
        System.out.println(result);
    }
    
    private static void traverse() {
        q2++;
        qLen[q2] = 1;
        qDouble[q2] = " ";
        qV[q2][0] = 1; // 'start'
        
        while (q1 <= q2) {
            int len = qLen[q1];
            int node1 = qV[q1][len - 1];
            
            for (int i = 0; i < n; i++) {
                if (v1[i].equals(getString(node1))) {
                    String node2 = v2[i];
                    
                    if (node2.equals("end")) {
                        result++;
                    } else {
                        int visited = 0;
                        int lowercase = 0;
                        
                        if (Character.isLowerCase(node2.charAt(0))) {
                            lowercase = 1;
                            
                            for (int j = 0; j < len; j++) {
                                if (getString(qV[q1][j]).equals(node2)) {
                                    visited = 1;
                                }
                            }
                        }
                        
                        if (lowercase == 0) {
                            q2++;
                            qDouble[q2] = qDouble[q1];
                            qLen[q2] = len + 1;
                            
                            for (int j = 0; j < len; j++) {
                                qV[q2][j] = qV[q1][j];
                            }
                            qV[q2][len] = getNodeValue(node2);
                        } else {
                            if (visited == 0) {
                                q2++;
                                qDouble[q2] = qDouble[q1];
                                qLen[q2] = len + 1;
                                
                                for (int j = 0; j < len; j++) {
                                    qV[q2][j] = qV[q1][j];
                                }
                                qV[q2][len] = getNodeValue(node2);
                            }
                            
                            if (visited == 1 && qDouble[q1].equals(" ") && !node2.equals("start")) {
                                q2++;
                                qDouble[q2] = node2;
                                qLen[q2] = len + 1;
                                
                                for (int j = 0; j < len; j++) {
                                    qV[q2][j] = qV[q1][j];
                                }
                                qV[q2][len] = getNodeValue(node2);
                            }
                        }
                    }
                }
            }
            q1++;
        }
    }
    
    private static String getString(int value) {
        switch (value) {
            case 1: return "start";
            case 2: return "end";
            case 3: return "a";
            case 4: return "b";
            default: return "unknown";
        }
    }
    
    private static int getNodeValue(String node) {
        switch (node) {
            case "start": return 1;
            case "end": return 2;
            case "a": return 3;
            case "b": return 4;
            default: return 0;
        }
    }
}