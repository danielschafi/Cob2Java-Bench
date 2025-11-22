import java.io.*;
import java.util.*;

public class AOC202112 {
    static class Edge {
        String v1;
        String v2;
    }
    
    static class QueueItem {
        List<String> path;
        
        QueueItem(List<String> path) {
            this.path = new ArrayList<>(path);
        }
    }
    
    public static void main(String[] args) throws IOException {
        List<Edge> edges = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("d12.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    String node1 = parts[0];
                    String node2 = parts[1];
                    
                    Edge edge1 = new Edge();
                    edge1.v1 = node1;
                    edge1.v2 = node2;
                    edges.add(edge1);
                    
                    Edge edge2 = new Edge();
                    edge2.v1 = node2;
                    edge2.v2 = node1;
                    edges.add(edge2);
                }
            }
        }
        
        int result = traverse(edges);
        System.out.println(result);
    }
    
    static int traverse(List<Edge> edges) {
        Queue<QueueItem> queue = new LinkedList<>();
        List<String> startPath = new ArrayList<>();
        startPath.add("start");
        queue.add(new QueueItem(startPath));
        
        int result = 0;
        
        while (!queue.isEmpty()) {
            QueueItem current = queue.poll();
            List<String> path = current.path;
            String node1 = path.get(path.size() - 1);
            
            if (node1.equals("end")) {
                result++;
            } else {
                for (Edge edge : edges) {
                    if (edge.v1.equals(node1)) {
                        String node2 = edge.v2;
                        boolean visited = false;
                        
                        if (Character.compare(node2.charAt(0), 'a') >= 0 && 
                            Character.compare(node2.charAt(0), 'z') <= 0) {
                            for (String v : path) {
                                if (v.equals(node2)) {
                                    visited = true;
                                    break;
                                }
                            }
                        }
                        
                        if (!visited) {
                            List<String> newPath = new ArrayList<>(path);
                            newPath.add(node2);
                            queue.add(new QueueItem(newPath));
                        }
                    }
                }
            }
        }
        
        return result;
    }
}