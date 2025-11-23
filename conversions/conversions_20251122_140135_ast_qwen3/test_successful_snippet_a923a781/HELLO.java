import java.io.*;
import java.util.*;

public class HELLO {
    static class CableSegment {
        long startX;
        long startY;
        long endX;
        long endY;
        long len;
        long direction;
    }

    static final int MAX_SEGMENTS = 400;
    static CableSegment[] cableOneSegments = new CableSegment[MAX_SEGMENTS];
    static {
        for (int i = 0; i < MAX_SEGMENTS; i++) {
            cableOneSegments[i] = new CableSegment();
        }
    }
    
    static int cableOneNumber = 0;
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
    static char EOF = 'N';
    static char invalidOp = 'N';
    static long distTmp1 = 0;
    static long distTmp2 = 0;
    static long distTot = 0;

    public static void main(String[] args) throws IOException {
        // Initialize file readers
        BufferedReader reader1 = new BufferedReader(new FileReader("cable1.txt"));
        BufferedReader reader2 = new BufferedReader(new FileReader("cable2.txt"));

        // Process first cable file
        String line1;
        EOF = 'N';
        int i = 1;
        while ((line1 = reader1.readLine()) != null) {
            if (line1.trim().isEmpty()) continue;
            
            String[] parts1 = line1.split("\\s+");
            char direction = parts1[0].charAt(0);
            long length = Long.parseLong(parts1[1]);
            invalidOp = 'N';

            switch (direction) {
                case 'L':
                    newX = currentX - length;
                    cableOneSegments[i-1].direction = -1;
                    break;
                case 'R':
                    newX = currentX + length;
                    cableOneSegments[i-1].direction = 1;
                    break;
                case 'U':
                    newY = currentY - length;
                    cableOneSegments[i-1].direction = -1;
                    break;
                case 'D':
                    newY = currentY + length;
                    cableOneSegments[i-1].direction = 1;
                    break;
                default:
                    invalidOp = 'Y';
                    break;
            }

            if (invalidOp != 'Y') {
                minX = Math.min(currentX, newX);
                minY = Math.min(currentY, newY);
                maxX = Math.max(currentX, newX);
                maxY = Math.max(currentY, newY);

                cableOneSegments[i-1].startX = minX;
                cableOneSegments[i-1].startY = minY;
                cableOneSegments[i-1].endX = maxX;
                cableOneSegments[i-1].endY = maxY;
                cableOneSegments[i-1].len = length;

                currentX = newX;
                currentY = newY;
                cableOneNumber = i;
                i++;
            }
        }
        reader1.close();

        // Display segments for debugging
        i = 1;
        while (i <= cableOneNumber) {
            System.out.println("start: x: " + cableOneSegments[i-1].startX + " y: " + cableOneSegments[i-1].startY);
            System.out.println("  end: x: " + cableOneSegments[i-1].endX + " y: " + cableOneSegments[i-1].endY);
            System.out.println("  len: " + cableOneSegments[i-1].len + " dir: " + cableOneSegments[i-1].direction);
            i++;
        }

        // Reset variables for second cable processing
        EOF = 'N';
        currentX = 0;
        currentY = 0;
        newX = 0;
        newY = 0;

        // Process second cable file
        String line2;
        while ((line2 = reader2.readLine()) != null) {
            if (line2.trim().isEmpty()) continue;
            
            String[] parts2 = line2.split("\\s+");
            char direction = parts2[0].charAt(0);
            long length = Long.parseLong(parts2[1]);
            invalidOp = 'N';

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
                default:
                    invalidOp = 'Y';
                    break;
            }

            if (invalidOp != 'Y') {
                i = 1;
                currSteps1 = 0;
                
                while (i <= cableOneNumber) {
                    intersects();
                    if (foundIntersection == 'Y') {
                        if (intersectX != 0 || intersectY != 0) {
                            distTmp2 = Math.abs(intersectX - currentX) + Math.abs(intersectY - currentY);
                            if (cableOneSegments[i-1].direction > 0) {
                                distTmp1 = Math.abs(intersectX - cableOneSegments[i-1].startX) + Math.abs(intersectY - cableOneSegments[i-1].startY);
                            } else {
                                distTmp1 = Math.abs(intersectX - cableOneSegments[i-1].endX) + Math.abs(intersectY - cableOneSegments[i-1].endY);
                            }
                            distTot = distTmp2 + distTmp1 + currSteps1 + currSteps2;
                            if (distTot < minSteps) {
                                minSteps = distTot;
                                minIntersectX = intersectX;
                                minIntersectY = intersectY;
                            }
                        }
                    }
                    currSteps1 += cableOneSegments[i-1].len;
                    i++;
                }
                currSteps2 += length;
                currentX = newX;
                currentY = newY;
            }
        }
        reader2.close();

        // Output result
        System.out.println("minX: " + minIntersectX + " minY: " + minIntersectY + " steps: " + minSteps);
    }

    static void intersects() {
        foundIntersection = 'N';
        minX = Math.min(currentX, newX);
        minY = Math.min(currentY, newY);
        maxX = Math.max(currentX, newX);
        maxY = Math.max(currentY, newY);

        if (minX == maxX) {
            if (cableOneSegments[i-1].startX <= minX && cableOneSegments[i-1].endX >= maxX &&
                cableOneSegments[i-1].startY >= minY && cableOneSegments[i-1].endY <= maxY) {
                foundIntersection = 'Y';
                intersectX = minX;
                intersectY = cableOneSegments[i-1].startY;
            }
        } else {
            if (cableOneSegments[i-1].startY <= minY && cableOneSegments[i-1].endY >= maxY &&
                cableOneSegments[i-1].startX >= minX && cableOneSegments[i-1].endX <= maxX) {
                foundIntersection = 'Y';
                intersectY = minY;
                intersectX = cableOneSegments[i-1].startX;
            }
        }
    }
}