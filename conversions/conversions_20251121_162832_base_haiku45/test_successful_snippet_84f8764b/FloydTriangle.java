public class FloydTriangle {
    private static int numLines;
    private static int curLine;
    private static int curCol;
    private static int curNum;
    private static int zeroSkip;
    private static int linePtr;
    private static int maxNum;
    private static int maxColNum;
    private static StringBuilder outLine;

    public static void main(String[] args) {
        numLines = 5;
        floyd();
        System.out.println();
        numLines = 14;
        floyd();
    }

    private static void floyd() {
        curNum = 1;
        maxNum = numLines * (numLines + 1) / 2;
        for (curLine = 1; curLine <= numLines; curLine++) {
            floydLine();
        }
    }

    private static void floydLine() {
        outLine = new StringBuilder();
        linePtr = 0;
        for (curCol = 1; curCol <= curLine; curCol++) {
            floydNum();
        }
        System.out.println(outLine.toString());
    }

    private static void floydNum() {
        maxColNum = maxNum - numLines + curCol;
        String maxColNumStr = String.valueOf(maxColNum);
        zeroSkip = 0;
        for (int i = 0; i < maxColNumStr.length(); i++) {
            if (maxColNumStr.charAt(i) == '0') {
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

    private static void floydOneDigit() {
        outLine.append(String.format("%1d", curNum));
    }

    private static void floydTwoDigits() {
        outLine.append(String.format("%2d", curNum));
    }

    private static void floydThreeDigits() {
        outLine.append(String.format("%3d", curNum));
    }
}