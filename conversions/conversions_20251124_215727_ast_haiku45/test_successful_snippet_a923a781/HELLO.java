import java.io.*;
import java.nio.file.*;
import java.util.*;

public class HELLO {
    static class Segment {
        long startX, startY, endX, endY, len;
        int direction;
    }

    static List<Segment> cableOneSegments = new ArrayList<>();
    static long currentX = 0, currentY = 0, newX = 0, newY = 0;
    static long minX = 0, maxX = 0, minY = 0, maxY = 0;
    static boolean foundIntersection = false;
    static long intersectX = 0, intersectY = 0;
    static long currSteps1 = 0, currSteps2 = 0;
    static long minSteps = 9999999999L;
    static long minIntersectX = 0, minIntersectY = 0;
    static long distTmp1 = 0, distTmp2 = 0, distTot = 0;

    public static void main(String[] args) throws IOException {
        readCable1();
        displayCable1();
        readCable2();
        System.out.println("minX: " + minIntersectX + " minY: " + minIntersectY + " steps: " + minSteps);
    }

    static void readCable1() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("cable1.txt"));
        currentX = 0;
        currentY = 0;

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            
            char direction = line.charAt(0);
            long length = Long.parseLong(line.substring(1));
            newX = currentX;
            newY = currentY;
            int dir = 0;

            switch (direction) {
                case 'L':
                    newX = currentX - length;
                    dir = -1;
                    break;
                case 'R':
                    newX = currentX + length;
                    dir = 1;
                    break;
                case 'U':
                    newY = currentY - length;
                    dir = -1;
                    break;
                case 'D':
                    newY = currentY + length;
                    dir = 1;
                    break;
            }

            Segment seg = new Segment();
            seg.startX = Math.min(currentX, newX);
            seg.startY = Math.min(currentY, newY);
            seg.endX = Math.max(currentX, newX);
            seg.endY = Math.max(currentY, newY);
            seg.len = length;
            seg.direction = dir;
            cableOneSegments.add(seg);

            currentX = newX;
            currentY = newY;
        }
    }

    static void displayCable1() {
        for (Segment seg : cableOneSegments) {
            System.out.println("start: x: " + seg.startX + " y: " + seg.startY);
            System.out.println("  end: x: " + seg.endX + " y: " + seg.endY);
            System.out.println("  len: " + seg.len + " dir: " + seg.direction);
        }
    }

    static void readCable2() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("cable2.txt"));
        currentX = 0;
        currentY = 0;
        currSteps2 = 0;

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            char direction = line.charAt(0);
            long length = Long.parseLong(line.substring(1));
            newX = currentX;
            newY = currentY;

            switch (direction) {
                case 'L':
                    newX = currentX - length;
                    break;
                case 'R':
                    newX = currentX + length;
                    break;
                case 'U':
                    newY = currentY - length;
                    break;
                case 'D':
                    newY = currentY + length;
                    break;
            }

            currSteps1 = 0;
            for (int i = 0; i < cableOneSegments.size(); i++) {
                intersects(i);
                if (foundIntersection) {
                    if (intersectX != 0 || intersectY != 0) {
                        distTmp2 = Math.abs(intersectX - currentX) + Math.abs(intersectY - currentY);
                        Segment seg = cableOneSegments.get(i);
                        if (seg.direction > 0) {
                            distTmp1 = Math.abs(intersectX - seg.startX) + Math.abs(intersectY - seg.startY);
                        } else {
                            distTmp1 = Math.abs(intersectX - seg.endX) + Math.abs(intersectY - seg.endY);
                        }
                        distTot = distTmp2 + distTmp1 + currSteps1 + currSteps2;
                        if (distTot < minSteps) {
                            minSteps = distTot;
                            minIntersectX = intersectX;
                            minIntersectY = intersectY;
                        }
                    }
                }
                currSteps1 += cableOneSegments.get(i).len;
            }

            currSteps2 += length;
            currentX = newX;
            currentY = newY;
        }
    }

    static void intersects(int i) {
        foundIntersection = false;
        minX = Math.min(currentX, newX);
        minY = Math.min(currentY, newY);
        maxX = Math.max(currentX, newX);
        maxY = Math.max(currentY, newY);

        Segment seg = cableOneSegments.get(i);

        if (minX == maxX) {
            if (seg.startX <= minX && seg.endX >= maxX && seg.startY >= minY && seg.endY <= maxY) {
                foundIntersection = true;
                intersectX = minX;
                intersectY = seg.startY;
            }
        } else {
            if (seg.startY <= minY && seg.endY >= maxY && seg.startX >= minX && seg.endX <= maxX) {
                foundIntersection = true;
                intersectY = minY;
                intersectX = seg.startX;
            }
        }
    }
}