import java.text.DecimalFormat;

public class FloydTriangle {
    public static void main(String[] args) {
        int numLines = 5;
        floyd(numLines);
        System.out.println();
        numLines = 14;
        floyd(numLines);
    }

    public static void floyd(int numLines) {
        int curNum = 1;
        int maxNum = numLines * (numLines + 1) / 2;
        for (int curLine = 1; curLine <= numLines; curLine++) {
            floydLine(curLine, curNum, maxNum);
            curNum += curLine;
        }
    }

    public static void floydLine(int curLine, int curNum, int maxNum) {
        StringBuilder outLine = new StringBuilder(" ".repeat(72));
        int linePtr = 0;
        for (int curCol = 1; curCol <= curLine; curCol++) {
            floydNum(curNum, outLine, linePtr, maxNum - numLines + curCol);
            curNum++;
            linePtr += String.valueOf(maxNum - numLines + curCol).length();
        }
        System.out.println(outLine.toString().trim());
    }

    public static void floydNum(int curNum, StringBuilder outLine, int linePtr, int maxColNum) {
        DecimalFormat df;
        if (String.valueOf(maxColNum).length() == 3) {
            df = new DecimalFormat("000");
        } else if (String.valueOf(maxColNum).length() == 2) {
            df = new DecimalFormat("00");
        } else {
            df = new DecimalFormat("0");
        }
        outLine.replace(linePtr, linePtr + String.valueOf(maxColNum).length(), df.format(curNum));
    }
}