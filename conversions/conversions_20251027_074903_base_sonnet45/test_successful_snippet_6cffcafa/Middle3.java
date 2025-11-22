import java.util.ArrayList;
import java.util.List;

public class Middle3 {
    private int num;
    private int div;
    private int mod;
    private int digitCounter;
    private int digitDiv;
    private int digitMod;
    private int multiplier;
    private String result;

    private static final int[] VALUES = {
        123, 12345, 1234567, 987654321, 10001, -10001, -123, -100, 100,
        -12345, 1, 2, -1, -10, 2002, -2002, 0
    };

    public static void main(String[] args) {
        Middle3 program = new Middle3();
        program.run();
    }

    public void run() {
        for (int value : VALUES) {
            num = value;
            check();
            System.out.printf("%10d --> %s%n", value, result);
            if (value == 0) break;
        }
    }

    private void check() {
        if (isNumTooSmall()) {
            result = "Number too small";
            return;
        }

        countDigits();
        int quotient = digitCounter / 2;
        int remainder = digitCounter % 2;

        if (remainder == 0) {
            result = "Even number of digits";
            return;
        }

        if (digitCounter > 3) {
            int exponent = ((digitCounter - 5) / 2) + 1;
            multiplier = (int) Math.pow(10, exponent);
            num = num / multiplier;
            div = num / 1000;
            mod = num % 1000;
            result = String.format("%03d", mod);
        } else {
            result = String.format("%d", num);
        }
    }

    private boolean isNumTooSmall() {
        return num >= -99 && num <= 99;
    }

    private void countDigits() {
        digitCounter = 0;
        digitDiv = Math.abs(num);
        while (digitDiv != 0) {
            digitMod = digitDiv % 10;
            digitDiv = digitDiv / 10;
            digitCounter++;
        }
    }
}