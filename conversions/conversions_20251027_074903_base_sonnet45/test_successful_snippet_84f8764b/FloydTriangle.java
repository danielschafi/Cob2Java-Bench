public class FloydTriangle {
    
    private int numLines;
    private int curLine;
    private int curCol;
    private int curNum;
    private int zeroSkip;
    private int linePtr;
    private int maxNum;
    private char[] outLine;
    private int maxColNum;
    
    public static void main(String[] args) {
        FloydTriangle ft = new FloydTriangle();
        ft.begin();
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
        outLine = new char[72];
        for (int i = 0; i < outLine.length; i++) {
            outLine[i] = ' ';
        }
        linePtr = 0;
        for (curCol = 1; curCol <= curLine; curCol++) {
            floydNum();
        }
        System.out.println(new String(outLine).stripTrailing());
    }
    
    private void floydNum() {
        maxColNum = maxNum - numLines + curCol;
        String maxColNumStr = String.format("%03d", maxColNum);
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
    
    private void floydOneDigit() {
        String formatted = String.format(" %d", curNum);
        stringIntoOutLine(formatted);
    }
    
    private void floydTwoDigits() {
        String formatted;
        if (curNum < 10) {
            formatted = String.format("  %d", curNum);
        } else {
            formatted = String.format(" %d", curNum);
        }
        stringIntoOutLine(formatted);
    }
    
    private void floydThreeDigits() {
        String formatted;
        if (curNum < 10) {
            formatted = String.format("   %d", curNum);
        } else if (curNum < 100) {
            formatted = String.format("  %d", curNum);
        } else {
            formatted = String.format(" %d", curNum);
        }
        stringIntoOutLine(formatted);
    }
    
    private void stringIntoOutLine(String str) {
        for (int i = 0; i < str.length() && linePtr < outLine.length; i++) {
            outLine[linePtr++] = str.charAt(i);
        }
    }
}