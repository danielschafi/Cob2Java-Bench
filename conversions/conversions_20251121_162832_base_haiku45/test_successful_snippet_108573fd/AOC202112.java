import java.io.*;
import java.util.*;

public class AOC202112 {
    static class Edge {
        String v1;
        String v2;
    }
    
    static class QueueItem {
        int len;
        String doubleVisit;
        String[] path;
        
        QueueItem() {
            path = new String[100];
            len = 0;
            doubleVisit = " ";
        }
    }
    
    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("d12.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    String node1 = parts[0];
                    String node2 = parts[1];
                    
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int result = traverse(edges);
        System.out.println(result);
    }
    
    static int traverse(List<Edge> edges) {
        Queue<QueueItem> queue = new LinkedList<>();
        int result = 0;
        
        QueueItem start = new QueueItem();
        start.len = 1;
        start.doubleVisit = " ";
        start.path[0] = "start";
        queue.add(start);
        
        while (!queue.isEmpty()) {
            QueueItem current = queue.poll();
            int len = current.len;
            String node1 = current.path[len - 1];
            
            for (Edge edge : edges) {
                if (edge.v1.equals(node1)) {
                    String node2 = edge.v2;
                    
                    if (node2.equals("end")) {
                        result++;
                    } else {
                        int visited = 0;
                        int lowercase = 0;
                        
                        if (Character.isLowerCase(node2.charAt(0))) {
                            lowercase = 1;
                            for (int j = 0; j < len; j++) {
                                if (current.path[j].equals(node2)) {
                                    visited = 1;
                                    break;
                                }
                            }
                        }
                        
                        if (lowercase == 0) {
                            QueueItem newItem = new QueueItem();
                            newItem.len = len + 1;
                            newItem.doubleVisit = current.doubleVisit;
                            for (int j = 0; j < len; j++) {
                                newItem.path[j] = current.path[j];
                            }
                            newItem.path[len] = node2;
                            queue.add(newItem);
                        } else {
                            if (visited == 0) {
                                QueueItem newItem = new QueueItem();
                                newItem.len = len + 1;
                                newItem.doubleVisit = current.doubleVisit;
                                for (int j = 0; j < len; j++) {
                                    newItem.path[j] = current.path[j];
                                }
                                newItem.path[len] = node2;
                                queue.add(newItem);
                            }
                            if (visited == 1 && current.doubleVisit.equals(" ") && !node2.equals("start")) {
                                QueueItem newItem = new QueueItem();
                                newItem.len = len + 1;
                                newItem.doubleVisit = node2;
                                for (int j = 0; j < len; j++) {
                                    newItem.path[j] = current.path[j];
                                }
                                newItem.path[len] = node2;
                                queue.add(newItem);
                            }
                        }
                    }
                }
            }
        }
        
        return result;
    }
}