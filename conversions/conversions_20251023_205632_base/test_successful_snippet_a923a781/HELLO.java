import java.io.*;
import java.util.*;

public class HELLO {
    static class CableSegment {
        int startX, startY, endX, endY, len, direction;
    }

    public static void main(String[] args) {
        CableSegment[] wsCableOneSegment = new CableSegment[400];
        for (int i = 0; i < 400; i++) {
            wsCableOneSegment[i] = new CableSegment();
        }
        int wsCableOneNumber = 0;
        int wsCurrentX = 0, wsCurrentY = 0, wsNewX = 0, wsNewY = 0;
        int wsMinX = 0, wsMaxX = 0, wsMinY = 0, wsMaxY = 0;
        char wsFoundIntersection = 'N';
        int wsIntersectX = 0, wsIntersectY = 0;
        int wsCurrSteps1 = 0, wsCurrSteps2 = 0;
        int wsMinSteps = 999999999;
        int wsMinIntersectX = 0, wsMinIntersectY = 0;
        char wsEOF = 'N';
        char wsInvalidOp = 'N';
        int wsDistTmp1 = 0, wsDistTmp2 = 0, wsDistTot = 0;

        try (BufferedReader cable1 = new BufferedReader(new FileReader("cable1.txt"))) {
            String line;
            int i = 0;
            while ((line = cable1.readLine()) != null) {
                wsInvalidOp = 'N';
                char cable1Direction = line.charAt(0);
                int cable1Length = Integer.parseInt(line.substring(1));

                switch (cable1Direction) {
                    case 'L':
                        wsNewX = wsCurrentX - cable1Length;
                        wsCableOneSegment[i].direction = -1;
                        break;
                    case 'R':
                        wsNewX = wsCurrentX + cable1Length;
                        wsCableOneSegment[i].direction = 1;
                        break;
                    case 'U':
                        wsNewY = wsCurrentY - cable1Length;
                        wsCableOneSegment[i].direction = -1;
                        break;
                    case 'D':
                        wsNewY = wsCurrentY + cable1Length;
                        wsCableOneSegment[i].direction = 1;
                        break;
                    default:
                        wsInvalidOp = 'Y';
                        break;
                }

                if (wsInvalidOp != 'Y') {
                    wsCableOneSegment[i].startX = Math.min(wsCurrentX, wsNewX);
                    wsCableOneSegment[i].startY = Math.min(wsCurrentY, wsNewY);
                    wsCableOneSegment[i].endX = Math.max(wsCurrentX, wsNewX);
                    wsCableOneSegment[i].endY = Math.max(wsCurrentY, wsNewY);
                    wsCableOneSegment[i].len = cable1Length;
                    wsCurrentX = wsNewX;
                    wsCurrentY = wsNewY;
                    i++;
                }
            }
            wsCableOneNumber = i;
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < wsCableOneNumber; i++) {
            System.out.println("start: x: " + wsCableOneSegment[i].startX + " y: " + wsCableOneSegment[i].startY);
            System.out.println("  end: x: " + wsCableOneSegment[i].endX + " y: " + wsCableOneSegment[i].endY);
            System.out.println("  len: " + wsCableOneSegment[i].len + " dir: " + wsCableOneSegment[i].direction);
        }

        wsEOF = 'N';
        wsCurrentX = 0;
        wsCurrentY = 0;
        wsNewX = 0;
        wsNewY = 0;

        try (BufferedReader cable2 = new BufferedReader(new FileReader("cable2.txt"))) {
            String line;
            while ((line = cable2.readLine()) != null) {
                wsInvalidOp = 'N';
                char cable2Direction = line.charAt(0);
                int cable2Length = Integer.parseInt(line.substring(1));

                switch (cable2Direction) {
                    case 'L':
                        wsNewX = wsCurrentX - cable2Length;
                        break;
                    case 'R':
                        wsNewX = wsCurrentX + cable2Length;
                        break;
                    case 'U':
                        wsNewY = wsCurrentY - cable2Length;
                        break;
                    case 'D':
                        wsNewY = wsCurrentY + cable2Length;
                        break;
                    default:
                        wsInvalidOp = 'Y';
                        break;
                }

                if (wsInvalidOp != 'Y') {
                    wsCurrSteps1 = 0;
                    for (int i = 0; i < wsCableOneNumber; i++) {
                        intersects(wsCableOneSegment[i], wsCurrentX, wsNewX, wsCurrentY, wsNewY);
                        if (wsFoundIntersection == 'Y') {
                            if (wsIntersectX != 0 || wsIntersectY != 0) {
                                wsDistTmp2 = Math.abs(wsIntersectX - wsCurrentX) + Math.abs(wsIntersectY - wsCurrentY);
                                if (wsCableOneSegment[i].direction > 0) {
                                    wsDistTmp1 = Math.abs(wsIntersectX - wsCableOneSegment[i].startX) + Math.abs(wsIntersectY - wsCableOneSegment[i].startY);
                                } else {
                                    wsDistTmp1 = Math.abs(wsIntersectX - wsCableOneSegment[i].endX) + Math.abs(wsIntersectY - wsCableOneSegment[i].endY);
                                }
                                wsDistTot = wsDistTmp2 + wsDistTmp1 + wsCurrSteps1 + wsCurrSteps2;
                                if (wsDistTot < wsMinSteps) {
                                    wsMinSteps = wsDistTot;
                                    wsMinIntersectX = wsIntersectX;
                                    wsMinIntersectY = wsIntersectY;
                                }
                            }
                        }
                        wsCurrSteps1 += wsCableOneSegment[i].len;
                    }
                    wsCurrSteps2 += cable2Length;
                    wsCurrentX = wsNewX;
                    wsCurrentY = wsNewY;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("minX: " + wsMinIntersectX + " minY: " + wsMinIntersectY + " steps: " + wsMinSteps);
    }

    static void intersects(CableSegment segment, int currentX, int newX, int currentY, int newY) {
        wsFoundIntersection = 'N';
        wsMinX = Math.min(currentX, newX);
        wsMinY = Math.min(currentY, newY);
        wsMaxX = Math.max(currentX, newX);
        wsMaxY = Math.max(currentY, newY);

        if (wsMinX == wsMaxX) {
            if (segment.startX <= wsMinX && segment.endX >= wsMaxX && segment.startY >= wsMinY && segment.endY <= wsMaxY) {
                wsFoundIntersection = 'Y';
                wsIntersectX = wsMinX;
                wsIntersectY = segment.startY;
            }
        } else {
            if (segment.startY <= wsMinY && segment.endY >= wsMaxY && segment.startX >= wsMinX && segment.endX <= wsMaxX) {
                wsFoundIntersection = 'Y';
                wsIntersectY = wsMinY;
                wsIntersectX = segment.startX;
            }
        }
    }
}