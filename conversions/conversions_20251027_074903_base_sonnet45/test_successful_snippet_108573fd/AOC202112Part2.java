import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC202112Part2 {
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
        String doubleVisit;
        String[] path;
        
        QueueItem(int len, String doubleVisit, String[] path) {
            this.len = len;
            this.doubleVisit = doubleVisit;
            this.path = path;
        }
    }
    
    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("d12.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    String node1 = parts[0].trim();
                    String node2 = parts[1].trim();
                    edges.add(new Edge(node1, node2));
                    edges.add(new Edge(node2, node1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        int result = traverse(edges);
        System.out.println(result);
    }
    
    static int traverse(List<Edge> edges) {
        List<QueueItem> queue = new ArrayList<>();
        int result = 0;
        
        String[] initialPath = new String[100];
        initialPath[0] = "start";
        queue.add(new QueueItem(1, " ", initialPath));
        
        int q1 = 0;
        
        while (q1 < queue.size()) {
            QueueItem current = queue.get(q1);
            int len = current.len;
            String node1 = current.path[len - 1];
            
            for (Edge edge : edges) {
                if (edge.v1.equals(node1)) {
                    String node2 = edge.v2;
                    
                    if (node2.equals("end")) {
                        result++;
                    } else {
                        int visited = 0;
                        boolean lowercase = false;
                        
                        if (node2.length() > 0 && node2.charAt(0) >= 'a' && node2.charAt(0) <= 'z') {
                            lowercase = true;
                            for (int j = 0; j < len; j++) {
                                if (current.path[j].equals(node2)) {
                                    visited++;
                                }
                            }
                        }
                        
                        if (!lowercase) {
                            String[] newPath = new String[100];
                            for (int j = 0; j < len; j++) {
                                newPath[j] = current.path[j];
                            }
                            newPath[len] = node2;
                            queue.add(new QueueItem(len + 1, current.doubleVisit, newPath));
                        } else {
                            if (visited == 0) {
                                String[] newPath = new String[100];
                                for (int j = 0; j < len; j++) {
                                    newPath[j] = current.path[j];
                                }
                                newPath[len] = node2;
                                queue.add(new QueueItem(len + 1, current.doubleVisit, newPath));
                            }
                            if (visited == 1 && current.doubleVisit.equals(" ") && !node2.equals("start")) {
                                String[] newPath = new String[100];
                                for (int j = 0; j < len; j++) {
                                    newPath[j] = current.path[j];
                                }
                                newPath[len] = node2;
                                queue.add(new QueueItem(len + 1, node2, newPath));
                            }
                        }
                    }
                }
            }
            q1++;
        }
        
        return result;
    }
}