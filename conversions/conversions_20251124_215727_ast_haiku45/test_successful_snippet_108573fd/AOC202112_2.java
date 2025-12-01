import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202112_2 {
    static class QueueEntry {
        int len;
        String doubleNode;
        String[] path;
        
        QueueEntry() {
            len = 0;
            doubleNode = " ";
            path = new String[100];
            for (int i = 0; i < 100; i++) {
                path[i] = "";
            }
        }
    }
    
    static class Edge {
        String v1;
        String v2;
        
        Edge(String v1, String v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }
    
    static List<Edge> edges = new ArrayList<>();
    static Queue<QueueEntry> queue = new LinkedList<>();
    static int result = 0;
    
    public static void main(String[] args) throws IOException {
        readInput();
        traverse();
        System.out.println(result);
    }
    
    static void readInput() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("d12.input"));
        for (String line : lines) {
            String[] parts = line.split("-");
            if (parts.length == 2) {
                String node1 = parts[0];
                String node2 = parts[1];
                edges.add(new Edge(node1, node2));
                edges.add(new Edge(node2, node1));
            }
        }
    }
    
    static void traverse() {
        QueueEntry start = new QueueEntry();
        start.len = 1;
        start.doubleNode = " ";
        start.path[0] = "start";
        queue.add(start);
        
        while (!queue.isEmpty()) {
            QueueEntry current = queue.poll();
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
                            QueueEntry newEntry = new QueueEntry();
                            newEntry.len = len + 1;
                            newEntry.doubleNode = current.doubleNode;
                            for (int j = 0; j < len; j++) {
                                newEntry.path[j] = current.path[j];
                            }
                            newEntry.path[len] = node2;
                            queue.add(newEntry);
                        } else {
                            if (visited == 0) {
                                QueueEntry newEntry = new QueueEntry();
                                newEntry.len = len + 1;
                                newEntry.doubleNode = current.doubleNode;
                                for (int j = 0; j < len; j++) {
                                    newEntry.path[j] = current.path[j];
                                }
                                newEntry.path[len] = node2;
                                queue.add(newEntry);
                            }
                            if (visited == 1 && current.doubleNode.equals(" ") && !node2.equals("start")) {
                                QueueEntry newEntry = new QueueEntry();
                                newEntry.len = len + 1;
                                newEntry.doubleNode = node2;
                                for (int j = 0; j < len; j++) {
                                    newEntry.path[j] = current.path[j];
                                }
                                newEntry.path[len] = node2;
                                queue.add(newEntry);
                            }
                        }
                    }
                }
            }
        }
    }
}