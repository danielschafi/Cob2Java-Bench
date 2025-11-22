import java.math.BigInteger;

public class HAMMING {
    private static class PopcountVariables {
        BigInteger popcountIn;
        int popcountOut;
    }

    private static class StateVariables {
        BigInteger curPower3;
        int curEvilNum;
        int curOdiousNum;
        int lineIndex;

        StateVariables() {
            curPower3 = BigInteger.ONE;
            curEvilNum = 0;
            curOdiousNum = 0;
            lineIndex = 1;
        }
    }

    private static PopcountVariables popcountVars = new PopcountVariables();
    private static StateVariables stateVars = new StateVariables();

    public static void main(String[] args) {
        begin();
    }

    private static void begin() {
        System.out.println("     3^   EVIL   ODD");
        for (int i = 0; i < 30; i++) {
            makeLine();
        }
    }

    private static void makeLine() {
        int lineno = stateVars.lineIndex;
        popcountVars.popcountIn = stateVars.curPower3;
        findPopcount();
        int outPow3 = popcountVars.popcountOut;
        findEvil();
        int outEvil = stateVars.curEvilNum;
        findOdious();
        int outOdious = stateVars.curOdiousNum;
        
        System.out.printf("%2d.  %2d    %2d    %2d%n", lineno, outPow3, outEvil, outOdious);
        
        stateVars.curPower3 = stateVars.curPower3.multiply(BigInteger.valueOf(3));
        stateVars.curEvilNum++;
        stateVars.curOdiousNum++;
        stateVars.lineIndex++;
    }

    private static void findEvil() {
        while (true) {
            popcountVars.popcountIn = BigInteger.valueOf(stateVars.curEvilNum);
            findPopcount();
            if (isEvil(popcountVars.popcountOut)) {
                break;
            }
            stateVars.curEvilNum++;
        }
    }

    private static void findOdious() {
        while (true) {
            popcountVars.popcountIn = BigInteger.valueOf(stateVars.curOdiousNum);
            findPopcount();
            if (isOdious(popcountVars.popcountOut)) {
                break;
            }
            stateVars.curOdiousNum++;
        }
    }

    private static void findPopcount() {
        popcountVars.popcountOut = 0;
        BigInteger temp = popcountVars.popcountIn;
        while (!temp.equals(BigInteger.ZERO)) {
            processBit(temp);
            temp = popcountVars.popcountIn;
        }
    }

    private static void processBit(BigInteger value) {
        BigInteger[] divResult = value.divideAndRemainder(BigInteger.TWO);
        BigInteger quotient = divResult[0];
        BigInteger remainder = divResult[1];
        
        if (remainder.equals(BigInteger.ONE)) {
            popcountVars.popcountOut++;
        }
        popcountVars.popcountIn = quotient;
    }

    private static boolean isEvil(int popcount) {
        int lastDigit = popcount % 10;
        return lastDigit == 0 || lastDigit == 2 || lastDigit == 4 || lastDigit == 6 || lastDigit == 8;
    }

    private static boolean isOdious(int popcount) {
        int lastDigit = popcount % 10;
        return lastDigit == 1 || lastDigit == 3 || lastDigit == 5 || lastDigit == 7 || lastDigit == 9;
    }
}