public class FloydTriangle {
    private static int numLines;
    private static int curLine;
    private static int curCol;
    private static int curNum;
    private static int zeroSkip;
    private static int linePtr;
    private static int maxNum;
    private static String outLine;
    private static int oneDigit;
    private static int twoDigits;
    private static int threeDigits;
    private static int maxColNum;

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
        outLine = " ".repeat(72);
        linePtr = 1;
        for (curCol = 1; curCol <= curLine; curCol++) {
            floydNum();
        }
        System.out.println(outLine.trim());
    }

    private static void floydNum() {
        maxColNum = maxNum - numLines + curCol;
        zeroSkip = 0;
        String maxColNumStr = String.valueOf(maxColNum);
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
        oneDigit = curNum;
        String oneDigitStr = String.format("%d", oneDigit);
        outLine = outLine.substring(0, linePtr - 1) + oneDigitStr + outLine.substring(linePtr - 1 + oneDigitStr.length());
        linePtr += oneDigitStr.length();
    }

    private static void floydTwoDigits() {
        twoDigits = curNum;
        String twoDigitsStr = String.format("%02d", twoDigits);
        outLine = outLine.substring(0, linePtr - 1) + twoDigitsStr + outLine.substring(linePtr - 1 + twoDigitsStr.length());
        linePtr += twoDigitsStr.length();
    }

    private static void floydThreeDigits() {
        threeDigits = curNum;
        String threeDigitsStr = String.format("%03d", threeDigits);
        outLine = outLine.substring(0, linePtr - 1) + threeDigitsStr + outLine.substring(linePtr - 1 + threeDigitsStr.length());
        linePtr += threeDigitsStr.length();
    }
}