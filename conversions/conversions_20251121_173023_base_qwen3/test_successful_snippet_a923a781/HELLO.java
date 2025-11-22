import java.io.*;
import java.util.*;

public class HELLO {
    static class CableSegment {
        long startX, startY, endX, endY, len, direction;
        CableSegment(long startX, long startY, long endX, long endY, long len, long direction) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.len = len;
            this.direction = direction;
        }
    }

    static List<CableSegment> cableOneSegments = new ArrayList<>();
    static long currentX = 0;
    static long currentY = 0;
    static long newX = 0;
    static long newY = 0;
    static long minX = 0;
    static long maxX = 0;
    static long minY = 0;
    static long maxY = 0;
    static char foundIntersection = 'N';
    static long intersectX = 0;
    static long intersectY = 0;
    static long currSteps1 = 0;
    static long currSteps2 = 0;
    static long minSteps = 9999999999L;
    static long minIntersectX = 0;
    static long minIntersectY = 0;
    static boolean eof = false;
    static char invalidOp = 'N';
    static long distTmp1 = 0;
    static long distTmp2 = 0;
    static long distTot = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader("cable1.txt"));
        String line;
        int i = 0;
        while ((line = reader1.readLine()) != null) {
            String[] parts = line.split(" ");
            String direction = parts[0];
            long length = Long.parseLong(parts[1]);
            
            switch (direction.charAt(0)) {
                case 'L':
                    newX = currentX - length;
                    cableOneSegments.add(new CableSegment(
                        Math.min(currentX, newX),
                        currentY,
                        Math.max(currentX, newX),
                        currentY,
                        length,
                        -1
                    ));
                    currentX = newX;
                    break;
                case 'R':
                    newX = currentX + length;
                    cableOneSegments.add(new CableSegment(
                        Math.min(currentX, newX),
                        currentY,
                        Math.max(currentX, newX),
                        currentY,
                        length,
                        1
                    ));
                    currentX = newX;
                    break;
                case 'U':
                    newY = currentY - length;
                    cableOneSegments.add(new CableSegment(
                        currentX,
                        Math.min(currentY, newY),
                        currentX,
                        Math.max(currentY, newY),
                        length,
                        -1
                    ));
                    currentY = newY;
                    break;
                case 'D':
                    newY = currentY + length;
                    cableOneSegments.add(new CableSegment(
                        currentX,
                        Math.min(currentY, newY),
                        currentX,
                        Math.max(currentY, newY),
                        length,
                        1
                    ));
                    currentY = newY;
                    break;
                default:
                    System.out.println("Invalid operation");
                    return;
            }
        }
        reader1.close();

        // Print segments for debugging
        for (int j = 0; j < cableOneSegments.size(); j++) {
            CableSegment seg = cableOneSegments.get(j);
            System.out.println("start: x: " + seg.startX + " y: " + seg.startY);
            System.out.println("  end: x: " + seg.endX + " y: " + seg.endY);
            System.out.println("  len: " + seg.len + " dir: " + seg.direction);
        }

        // Reset variables
        currentX = 0;
        currentY = 0;
        newX = 0;
        newY = 0;

        BufferedReader reader2 = new BufferedReader(new FileReader("cable2.txt"));
        currSteps2 = 0;
        while ((line = reader2.readLine()) != null) {
            String[] parts = line.split(" ");
            String direction = parts[0];
            long length = Long.parseLong(parts[1]);

            switch (direction.charAt(0)) {
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
                default:
                    System.out.println("Invalid operation");
                    return;
            }

            // Check intersections
            currSteps1 = 0;
            for (int j = 0; j < cableOneSegments.size(); j++) {
                intersects(cableOneSegments.get(j), newX, newY);
                if (foundIntersection == 'Y') {
                    if (intersectX != 0 || intersectY != 0) {
                        distTmp2 = Math.abs(intersectX - currentX) + Math.abs(intersectY - currentY);
                        if (cableOneSegments.get(j).direction > 0) {
                            distTmp1 = Math.abs(intersectX - cableOneSegments.get(j).startX) + 
                                      Math.abs(intersectY - cableOneSegments.get(j).startY);
                        } else {
                            distTmp1 = Math.abs(intersectX - cableOneSegments.get(j).endX) + 
                                      Math.abs(intersectY - cableOneSegments.get(j).endY);
                        }
                        distTot = distTmp2 + distTmp1 + currSteps1 + currSteps2;
                        if (distTot < minSteps) {
                            minSteps = distTot;
                            minIntersectX = intersectX;
                            minIntersectY = intersectY;
                        }
                    }
                }
                currSteps1 += cableOneSegments.get(j).len;
            }

            currSteps2 += length;
            currentX = newX;
            currentY = newY;
        }
        reader2.close();

        System.out.println("minX: " + minIntersectX + " minY: " + minIntersectY + " steps: " + minSteps);
    }

    static void intersects(CableSegment segment, long newX, long newY) {
        foundIntersection = 'N';
        minX = Math.min(currentX, newX);
        minY = Math.min(currentY, newY);
        maxX = Math.max(currentX, newX);
        maxY = Math.max(currentY, newY);

        if (minX == maxX) {
            if (segment.startX <= minX && segment.endX >= maxX &&
                segment.startY >= minY && segment.endY <= maxY) {
                foundIntersection = 'Y';
                intersectX = minX;
                intersectY = segment.startY;
            }
        } else {
            if (segment.startY <= minY && segment.endY >= maxY &&
                segment.startX >= minX && segment.endX <= maxX) {
                foundIntersection = 'Y';
                intersectY = minY;
                intersectX = segment.startX;
            }
        }
    }
}