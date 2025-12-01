import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202112 {
    static class Edge {
        String v1;
        String v2;
    }
    
    static class QueueItem {
        int len;
        String[] path;
        
        QueueItem() {
            path = new String[100];
            len = 0;
        }
        
        QueueItem(QueueItem other) {
            this.len = other.len;
            this.path = new String[100];
            for (int i = 0; i < other.len; i++) {
                this.path[i] = other.path[i];
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        List<Edge> edges = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("d12.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    String node1 = parts[0].trim();
                    String node2 = parts[1].trim();
                    
                    Edge e1 = new Edge();
                    e1.v1 = node1;
                    e1.v2 = node2;
                    edges.add(e1);
                    
                    Edge e2 = new Edge();
                    e2.v1 = node2;
                    e2.v2 = node1;
                    edges.add(e2);
                }
            }
        }
        
        int result = traverse(edges);
        System.out.println(result);
    }
    
    static int traverse(List<Edge> edges) {
        List<QueueItem> queue = new ArrayList<>();
        int result = 0;
        
        QueueItem start = new QueueItem();
        start.len = 1;
        start.path[0] = "start";
        queue.add(start);
        
        int q1 = 0;
        
        while (q1 < queue.size()) {
            QueueItem current = queue.get(q1);
            String node1 = current.path[current.len - 1];
            
            if (node1.equals("end")) {
                result++;
            } else {
                for (Edge edge : edges) {
                    if (edge.v1.equals(node1)) {
                        String node2 = edge.v2;
                        boolean visited = false;
                        
                        if (Character.isLowerCase(node2.charAt(0))) {
                            for (int j = 0; j < current.len; j++) {
                                if (current.path[j].equals(node2)) {
                                    visited = true;
                                    break;
                                }
                            }
                        }
                        
                        if (!visited) {
                            QueueItem newItem = new QueueItem(current);
                            newItem.path[newItem.len] = node2;
                            newItem.len++;
                            queue.add(newItem);
                        }
                    }
                }
            }
            
            q1++;
        }
        
        return result;
    }
}