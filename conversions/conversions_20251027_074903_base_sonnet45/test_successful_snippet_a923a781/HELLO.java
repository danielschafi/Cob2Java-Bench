import java.io.*;
import java.util.*;

public class HELLO {
    static class CableSegment {
        int startX;
        int startY;
        int endX;
        int endY;
        int len;
        int direction;
    }

    static List<CableSegment> cableOneSegments = new ArrayList<>();
    static int currentX = 0;
    static int currentY = 0;
    static int newX = 0;
    static int newY = 0;
    static boolean foundIntersection = false;
    static int intersectX = 0;
    static int intersectY = 0;
    static int currSteps1 = 0;
    static int currSteps2 = 0;
    static long minSteps = 9999999999L;
    static int minIntersectX = 0;
    static int minIntersectY = 0;

    public static void main(String[] args) {
        try {
            BufferedReader cable1Reader = new BufferedReader(new FileReader("cable1.txt"));
            String line;
            
            while ((line = cable1Reader.readLine()) != null) {
                if (line.length() < 2) continue;
                
                char direction = line.charAt(0);
                int length = Integer.parseInt(line.substring(1).trim());
                
                CableSegment segment = new CableSegment();
                boolean invalidOp = false;
                
                switch (direction) {
                    case 'L':
                        newX = currentX - length;
                        segment.direction = -1;
                        break;
                    case 'R':
                        newX = currentX + length;
                        segment.direction = 1;
                        break;
                    case 'U':
                        newY = currentY - length;
                        segment.direction = -1;
                        break;
                    case 'D':
                        newY = currentY + length;
                        segment.direction = 1;
                        break;
                    default:
                        invalidOp = true;
                }
                
                if (!invalidOp) {
                    segment.startX = Math.min(currentX, newX);
                    segment.startY = Math.min(currentY, newY);
                    segment.endX = Math.max(currentX, newX);
                    segment.endY = Math.max(currentY, newY);
                    segment.len = length;
                    
                    cableOneSegments.add(segment);
                    
                    currentX = newX;
                    currentY = newY;
                }
            }
            cable1Reader.close();
            
            for (CableSegment seg : cableOneSegments) {
                System.out.println("start: x: " + seg.startX + " y: " + seg.startY);
                System.out.println("  end: x: " + seg.endX + " y: " + seg.endY);
                System.out.println("  len: " + seg.len + " dir: " + seg.direction);
            }
            
            currentX = 0;
            currentY = 0;
            newX = 0;
            newY = 0;
            
            BufferedReader cable2Reader = new BufferedReader(new FileReader("cable2.txt"));
            
            while ((line = cable2Reader.readLine()) != null) {
                if (line.length() < 2) continue;
                
                char direction = line.charAt(0);
                int length = Integer.parseInt(line.substring(1).trim());
                
                boolean invalidOp = false;
                
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
                        invalidOp = true;
                }
                
                if (!invalidOp) {
                    currSteps1 = 0;
                    
                    for (CableSegment seg : cableOneSegments) {
                        intersects(seg);
                        
                        if (foundIntersection) {
                            if (intersectX != 0 || intersectY != 0) {
                                int distTmp2 = Math.abs(intersectX - currentX) + Math.abs(intersectY - currentY);
                                int distTmp1;
                                
                                if (seg.direction > 0) {
                                    distTmp1 = Math.abs(intersectX - seg.startX) + Math.abs(intersectY - seg.startY);
                                } else {
                                    distTmp1 = Math.abs(intersectX - seg.endX) + Math.abs(intersectY - seg.endY);
                                }
                                
                                long distTot = distTmp2 + distTmp1 + currSteps1 + currSteps2;
                                
                                if (distTot < minSteps) {
                                    minSteps = distTot;
                                    minIntersectX = intersectX;
                                    minIntersectY = intersectY;
                                }
                            }
                        }
                        
                        currSteps1 += seg.len;
                    }
                    
                    currSteps2 += length;
                    
                    currentX = newX;
                    currentY = newY;
                }
            }
            cable2Reader.close();
            
            System.out.println("minX: " + minIntersectX + " minY: " + minIntersectY + " steps: " + minSteps);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void intersects(CableSegment seg) {
        foundIntersection = false;
        int minX = Math.min(currentX, newX);
        int minY = Math.min(currentY, newY);
        int maxX = Math.max(currentX, newX);
        int maxY = Math.max(currentY, newY);
        
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