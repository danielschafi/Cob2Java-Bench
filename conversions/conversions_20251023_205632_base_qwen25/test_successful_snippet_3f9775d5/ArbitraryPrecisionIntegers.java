import java.math.BigInteger;

public class ArbitraryPrecisionIntegers {
    private static final int WINDOW_WIDTH = 20;

    public static void main(String[] args) {
        BigInteger gmpNumber = BigInteger.ZERO;
        BigInteger gmpBuild = BigInteger.ZERO;
        long theInt;
        long theExponent;
        boolean validExponent = true;

        theInt = 10;
        theExponent = 19;
        gmpNumber = initializeIntegers();
        gmpBuild = initializeIntegers();
        gmpNumber = raisePowAccreteExponent(gmpNumber, gmpBuild, theInt, theExponent, validExponent);
        showAllOrPortion(gmpNumber);
        cleanUp(gmpNumber, gmpBuild);

        theInt = 12345;
        theExponent = 9;
        gmpNumber = initializeIntegers();
        gmpBuild = initializeIntegers();
        gmpNumber = raisePowAccreteExponent(gmpNumber, gmpBuild, theInt, theExponent, validExponent);
        showAllOrPortion(gmpNumber);
        cleanUp(gmpNumber, gmpBuild);

        theInt = 3;
        theExponent = 2;
        gmpNumber = initializeIntegers();
        gmpBuild = initializeIntegers();
        gmpNumber = raisePowAccreteExponent(gmpNumber, gmpBuild, theInt, theExponent, validExponent);

        theInt = 4;
        theExponent = gmpNumber.longValue();
        gmpNumber = raisePowAccreteExponent(gmpNumber, gmpBuild, theInt, theExponent, validExponent);

        theInt = 5;
        theExponent = gmpNumber.longValue();
        gmpNumber = raisePowAccreteExponent(gmpNumber, gmpBuild, theInt, theExponent, validExponent);
        showAllOrPortion(gmpNumber);
        cleanUp(gmpNumber, gmpBuild);
    }

    private static BigInteger initializeIntegers() {
        return BigInteger.ZERO;
    }

    private static BigInteger raisePowAccreteExponent(BigInteger gmpNumber, BigInteger gmpBuild, long theInt, long theExponent, boolean validExponent) {
        if (!validExponent) {
            System.err.println("Error: intermediate overflow occurred at " + theExponent);
            System.exit(1);
        }
        gmpNumber = BigInteger.ZERO;
        gmpBuild = BigInteger.valueOf(theInt);
        gmpNumber = gmpBuild.pow((int) theExponent);
        theExponent = gmpNumber.longValue();
        validExponent = gmpNumber.bitLength() <= Long.SIZE - 1;
        return gmpNumber;
    }

    private static void showAllOrPortion(BigInteger gmpNumber) {
        String numberString = gmpNumber.toString();
        int numberLength = numberString.length();
        System.out.print("GMP length: " + numberLength + ", strlen: " + numberLength);

        int limitWidth = WINDOW_WIDTH;
        if (numberLength <= WINDOW_WIDTH) {
            limitWidth = numberLength;
            System.out.println(numberString.substring(0, limitWidth));
        } else {
            System.out.print(numberString);
            numberLength -= WINDOW_WIDTH;
            numberLength = Math.max(0, numberLength);
            if (numberLength <= WINDOW_WIDTH) {
                limitWidth = numberLength;
            } else {
                System.out.print("...");
            }
            System.out.println(numberString.substring(numberString.length() - limitWidth));
        }
    }

    private static void cleanUp(BigInteger gmpNumber, BigInteger gmpBuild) {
        gmpNumber = BigInteger.ZERO;
        gmpBuild = BigInteger.ZERO;
    }
}