import java.text.DecimalFormat;

public class FloydTriangle {
    private int numLines;
    private int curLine;
    private int curCol;
    private int curNum;
    private int zeroSkip;
    private int linePtr;
    private int maxNum;
    private char[] outLine = new char[72];
    private DecimalFormat oneDigit = new DecimalFormat("0");
    private DecimalFormat twoDigits = new DecimalFormat("00");
    private DecimalFormat threeDigits = new DecimalFormat("000");
    private int maxColNum;

    public static void main(String[] args) {
        FloydTriangle floydTriangle = new FloydTriangle();
        floydTriangle.numLines = 5;
        floydTriangle.floyd();
        System.out.println();
        floydTriangle.numLines = 14;
        floydTriangle.floyd();
    }

    private void floyd() {
        curNum = 1;
        maxNum = numLines * (numLines + 1) / 2;
        for (curLine = 1; curLine <= numLines; curLine++) {
            floydLine();
        }
    }

    private void floydLine() {
        for (int i = 0; i < outLine.length; i++) {
            outLine[i] = ' ';
        }
        linePtr = 0;
        for (curCol = 1; curCol <= curLine; curCol++) {
            floydNum();
        }
        System.out.println(new String(outLine));
    }

    private void floydNum() {
        maxColNum = maxNum - numLines + curCol;
        zeroSkip = 0;
        String maxColNumStr = Integer.toString(maxColNum);
        for (char c : maxColNumStr.toCharArray()) {
            if (c == '0') {
                zeroSkip++;
            } else {
                break;
            }
        }
        if (zeroSkip == 0) {
            floydThreeDigits();
        } else if (zeroSkip == 1) {
            floydTwoDigits();
        } else if (zeroSkip == 2) {
            floydOneDigit();
        }
        curNum++;
    }

    private void floydOneDigit() {
        String numStr = oneDigit.format(curNum);
        for (char c : numStr.toCharArray()) {
            outLine[linePtr++] = c;
        }
    }

    private void floydTwoDigits() {
        String numStr = twoDigits.format(curNum);
        for (char c : numStr.toCharArray()) {
            outLine[linePtr++] = c;
        }
    }

    private void floydThreeDigits() {
        String numStr = threeDigits.format(curNum);
        for (char c : numStr.toCharArray()) {
            outLine[linePtr++] = c;
        }
    }
}