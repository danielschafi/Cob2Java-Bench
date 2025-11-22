import java.math.BigInteger;

public class ArbitraryPrecisionIntegers {
    
    static class GMPNumber {
        BigInteger value;
        
        GMPNumber() {
            this.value = BigInteger.ZERO;
        }
    }
    
    static GMPNumber gmpNumber;
    static GMPNumber gmpBuild;
    
    static long theInt;
    static long theExponent;
    static boolean validExponent = true;
    static final int WINDOW_WIDTH = 20;
    
    public static void main(String[] args) {
        // First calculation: 10 ** 19
        initializeIntegers();
        System.out.print("10 ** 19        : ");
        theInt = 10;
        theExponent = 19;
        raisePowAccreteExponent();
        showAllOrPortion();
        cleanUp();
        
        // Second calculation: 12345 ** 9
        initializeIntegers();
        System.out.print("12345 ** 9      : ");
        theInt = 12345;
        theExponent = 9;
        raisePowAccreteExponent();
        showAllOrPortion();
        cleanUp();
        
        // Third calculation: 5 ** 4 ** 3 ** 2
        initializeIntegers();
        System.out.print("5 ** 4 ** 3 ** 2: ");
        theInt = 3;
        theExponent = 2;
        raisePowAccreteExponent();
        theInt = 4;
        raisePowAccreteExponent();
        theInt = 5;
        raisePowAccreteExponent();
        showAllOrPortion();
        cleanUp();
    }
    
    static void initializeIntegers() {
        gmpNumber = new GMPNumber();
        gmpBuild = new GMPNumber();
    }
    
    static void raisePowAccreteExponent() {
        if (!validExponent) {
            System.err.println("Error: intermediate overflow occured at " + theExponent);
            System.exit(1);
        }
        
        gmpNumber.value = BigInteger.ZERO;
        gmpBuild.value = BigInteger.valueOf(theInt);
        gmpNumber.value = gmpBuild.value.pow((int) theExponent);
        gmpBuild.value = BigInteger.ZERO;
        
        try {
            theExponent = gmpNumber.value.longValueExact();
            validExponent = true;
        } catch (ArithmeticException e) {
            validExponent = false;
        }
    }
    
    static void showAllOrPortion() {
        String numberString = gmpNumber.value.toString();
        int numberLength = numberString.length();
        
        System.out.print("GMP length: " + numberLength + ", ");
        System.out.println("strlen: " + numberLength);
        
        int limitWidth = WINDOW_WIDTH;
        if (numberLength <= WINDOW_WIDTH) {
            limitWidth = numberLength;
            System.out.println(numberString.substring(0, limitWidth));
        } else {
            System.out.print(numberString.substring(0, WINDOW_WIDTH));
            int remaining = numberLength - WINDOW_WIDTH;
            if (remaining <= WINDOW_WIDTH) {
                limitWidth = remaining;
            } else {
                System.out.print("...");
            }
            System.out.println(numberString.substring(numberLength - limitWidth));
        }
    }
    
    static void cleanUp() {
        gmpNumber = null;
        gmpBuild = null;
        validExponent = true;
    }
}