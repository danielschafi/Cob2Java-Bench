import java.io.*;
import java.util.*;

public class AOC202112 {
    static class Edge {
        String v1;
        String v2;
        
        Edge(String v1, String v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }
    
    static class QueueItem {
        int len;
        List<String> path;
        
        QueueItem(int len, List<String> path) {
            this.len = len;
            this.path = new ArrayList<>(path);
        }
    }
    
    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        int n = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader("d12.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    String node1 = parts[0].trim();
                    String node2 = parts[1].trim();
                    
                    edges.add(new Edge(node1, node2));
                    n++;
                    edges.add(new Edge(node2, node1));
                    n++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        int result = traverse(edges, n);
        System.out.println(result);
    }
    
    static int traverse(List<Edge> edges, int n) {
        Queue<QueueItem> queue = new LinkedList<>();
        List<String> initialPath = new ArrayList<>();
        initialPath.add("start");
        queue.add(new QueueItem(1, initialPath));
        
        int result = 0;
        
        while (!queue.isEmpty()) {
            QueueItem current = queue.poll();
            int len = current.len;
            String node1 = current.path.get(len - 1);
            
            if (node1.equals("end")) {
                result++;
            } else {
                for (int i = 0; i < n; i++) {
                    if (edges.get(i).v1.equals(node1)) {
                        String node2 = edges.get(i).v2;
                        boolean visited = false;
                        
                        if (!node2.isEmpty() && Character.isLowerCase(node2.charAt(0))) {
                            for (int j = 0; j < len; j++) {
                                if (current.path.get(j).equals(node2)) {
                                    visited = true;
                                    break;
                                }
                            }
                        }
                        
                        if (!visited) {
                            List<String> newPath = new ArrayList<>(current.path);
                            newPath.add(node2);
                            queue.add(new QueueItem(len + 1, newPath));
                        }
                    }
                }
            }
        }
        
        return result;
    }
}