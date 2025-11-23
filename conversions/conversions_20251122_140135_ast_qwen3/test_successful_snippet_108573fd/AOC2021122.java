import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC2021122 {
    private static final int MAX_EDGES = 64;
    private static final int MAX_QUEUE_SIZE = 999999;
    private static final int MAX_PATH_LENGTH = 100;
    
    private static String[] v1 = new String[MAX_EDGES];
    private static String[] v2 = new String[MAX_EDGES];
    private static int n = 0;
    
    private static int[] qLen = new int[MAX_QUEUE_SIZE];
    private static String[] qDouble = new String[MAX_QUEUE_SIZE];
    private static String[][] qV = new String[MAX_QUEUE_SIZE][MAX_PATH_LENGTH];
    private static int q1 = 1;
    private static int q2 = 0;
    private static long result = 0;
    
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
        q2++;
        qLen[q2] = 1;
        qDouble[q2] = " ";
        qV[q2][1] = "start";
        
        while (q1 <= q2) {
            int len = qLen[q1];
            String node1 = qV[q1][len];
            
            for (int i = 1; i <= n; i++) {
                if (v1[i].equals(node1)) {
                    String node2 = v2[i];
                    if (node2.equals("end")) {
                        result++;
                    } else {
                        int visited = 0;
                        int lowercase = 0;
                        
                        if (node2.charAt(0) >= 'a') {
                            lowercase = 1;
                            for (int j = 1; j <= len; j++) {
                                if (qV[q1][j].equals(node2)) {
                                    visited = 1;
                                }
                            }
                        }
                        
                        if (lowercase == 0) {
                            q2++;
                            qDouble[q2] = qDouble[q1];
                            qLen[q2] = len + 1;
                            for (int j = 1; j <= len; j++) {
                                qV[q2][j] = qV[q1][j];
                            }
                            qV[q2][len + 1] = node2;
                        } else {
                            if (visited == 0) {
                                q2++;
                                qDouble[q2] = qDouble[q1];
                                qLen[q2] = len + 1;
                                for (int j = 1; j <= len; j++) {
                                    qV[q2][j] = qV[q1][j];
                                }
                                qV[q2][len + 1] = node2;
                            }
                            if (visited == 1 && qDouble[q1].equals(" ") && !node2.equals("start")) {
                                q2++;
                                qDouble[q2] = node2;
                                qLen[q2] = len + 1;
                                for (int j = 1; j <= len; j++) {
                                    qV[q2][j] = qV[q1][j];
                                }
                                qV[q2][len + 1] = node2;
                            }
                        }
                    }
                }
            }
            q1++;
        }
    }
}