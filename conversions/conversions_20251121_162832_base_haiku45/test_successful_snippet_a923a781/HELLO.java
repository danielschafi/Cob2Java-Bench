import java.io.*;
import java.util.*;

public class HELLO {
    static class CableSegment {
        int startX, startY, endX, endY, len, direction;
    }

    static List<CableSegment> cableOne = new ArrayList<>();
    static int currentX = 0;
    static int currentY = 0;
    static int newX = 0;
    static int newY = 0;
    static int minX = 0;
    static int maxX = 0;
    static int minY = 0;
    static int maxY = 0;
    static char foundIntersection = 'N';
    static int intersectX = 0;
    static int intersectY = 0;
    static int currSteps1 = 0;
    static int currSteps2 = 0;
    static long minSteps = 9999999999L;
    static int minIntersectX = 0;
    static int minIntersectY = 0;
    static long distTmp1 = 0;
    static long distTmp2 = 0;
    static long distTot = 0;

    public static void main(String[] args) {
        readCable1();
        displayCable1();
        readCable2();
        System.out.println("minX: " + minIntersectX + " minY: " + minIntersectY + " steps: " + minSteps);
    }

    static void readCable1() {
        try (BufferedReader br = new BufferedReader(new FileReader("cable1.txt"))) {
            String line;
            currentX = 0;
            currentY = 0;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                char direction = line.charAt(0);
                int length = Integer.parseInt(line.substring(1));
                char invalidOp = 'N';

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
                    default:
                        invalidOp = 'Y';
                }

                if (invalidOp != 'Y') {
                    CableSegment seg = new CableSegment();
                    seg.startX = Math.min(currentX, newX);
                    seg.startY = Math.min(currentY, newY);
                    seg.endX = Math.max(currentX, newX);
                    seg.endY = Math.max(currentY, newY);
                    seg.len = length;
                    seg.direction = dir;
                    cableOne.add(seg);

                    currentX = newX;
                    currentY = newY;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void displayCable1() {
        for (int i = 0; i < cableOne.size(); i++) {
            CableSegment seg = cableOne.get(i);
            System.out.println("start: x: " + seg.startX + " y: " + seg.startY);
            System.out.println("  end: x: " + seg.endX + " y: " + seg.endY);
            System.out.println("  len: " + seg.len + " dir: " + seg.direction);
        }
    }

    static void readCable2() {
        try (BufferedReader br = new BufferedReader(new FileReader("cable2.txt"))) {
            String line;
            currentX = 0;
            currentY = 0;
            currSteps2 = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                char direction = line.charAt(0);
                int length = Integer.parseInt(line.substring(1));
                char invalidOp = 'N';

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
                    default:
                        invalidOp = 'Y';
                }

                if (invalidOp != 'Y') {
                    currSteps1 = 0;
                    for (int i = 0; i < cableOne.size(); i++) {
                        intersects(i);
                        if (foundIntersection == 'Y') {
                            if (intersectX != 0 || intersectY != 0) {
                                distTmp2 = Math.abs(intersectX - currentX) + Math.abs(intersectY - currentY);
                                CableSegment seg = cableOne.get(i);
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
                        currSteps1 += cableOne.get(i).len;
                    }

                    currSteps2 += length;
                    currentX = newX;
                    currentY = newY;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void intersects(int i) {
        foundIntersection = 'N';
        minX = Math.min(currentX, newX);
        minY = Math.min(currentY, newY);
        maxX = Math.max(currentX, newX);
        maxY = Math.max(currentY, newY);

        CableSegment seg = cableOne.get(i);

        if (minX == maxX) {
            if (seg.startX <= minX && seg.endX >= maxX &&
                seg.startY >= minY && seg.endY <= maxY) {
                foundIntersection = 'Y';
                intersectX = minX;
                intersectY = seg.startY;
            }
        } else {
            if (seg.startY <= minY && seg.endY >= maxY &&
                seg.startX >= minX && seg.endX <= maxX) {
                foundIntersection = 'Y';
                intersectY = minY;
                intersectX = seg.startX;
            }
        }
    }
}