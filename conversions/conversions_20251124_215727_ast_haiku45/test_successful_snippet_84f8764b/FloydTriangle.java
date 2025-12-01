```java
public class FloydTriangle {
    private int numLines;
    private int curLine;
    private int curCol;
    private int curNum;
    private int zeroSkip;
    private int linePtr;
    private int maxNum;
    private StringBuilder outLine;
    private int maxColNum;

    public FloydTriangle() {
        outLine = new StringBuilder(72);
    }

    public static void main(String[] args) {
        FloydTriangle program = new FloydTriangle();
        program.begin();
    }

    private void begin() {
        numLines = 5;
        floyd();
        System.out.println();
        numLines = 14;
        floyd();
    }

    private void floyd() {
        curNum = 1;
        maxNum = numLines * (numLines + 1) / 2;
        for (curLine = 1; curLine <= numLines; curLine++) {
            floydLine();
        }
    }

    private void floydLine() {
        outLine = new StringBuilder(72);
        for (int i = 0; i < 72; i++) {
            outLine.append(' ');
        }
        linePtr = 0;
        for (curCol = 1; curCol <= curLine; curCol++) {
            floydNum();
        }
        System.out.println(outLine.toString().trim());
    }

    private void floydNum() {
        maxColNum = maxNum - numLines + curCol;
        String maxColNumStr = String.valueOf(maxColNum);
        zeroSkip = 0;
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
        String formatted = String.format("%1d", curNum);
        insertIntoOutLine(formatted);
    }

    private void floydTwoDigits() {
        String formatted = String.format("%2d", curNum).replace(' ', ' ');
        insertIntoOutLine(formatted);
    }

    private void floydThreeDigits() {
        String formatted = String.format("%3d", curNum).replace(' ', ' ');
        insertIntoOutLine(formatted);
    }

    private void insertIntoOutLine(String value) {
        if (linePtr + value.length() <= 72) {
            for (int i = 0; i < value.length(); i++) {
                outLine.setCharAt(linePtr + i, value.charAt(i));
            }
            linePtr += value.length();
        }
    }
}