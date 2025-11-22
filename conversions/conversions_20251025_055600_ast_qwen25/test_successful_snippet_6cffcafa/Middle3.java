import java.text.DecimalFormat;

public class Middle3 {
    private int num;
    private String numDisp;
    private int div;
    private int mod;
    private String modDisp;
    private int digitCounter;
    private int digitDiv;
    private int digitMod;
    private int multiplier;
    private String result;
    private int[] items = {123, 12345, 1234567, 987654321, 10001, -10001, -123, -100, 100, -12345, 1, 2, -1, -10, 2002, -2002, 0};

    public static void main(String[] args) {
        Middle3 middle3 = new Middle3();
        middle3.execute();
    }

    public void execute() {
        for (int item : items) {
            if (item == 0) break;
            num = item;
            numDisp = String.format("%9d", num);
            check();
            System.out.println(numDisp + " --> " + result);
        }
    }

    public void check() {
        if (num >= -99 && num <= 99) {
            result = "Number too small";
            return;
        }

        countDigits();
        digitDiv = digitCounter / 2;
        digitMod = digitCounter % 2;
        if (digitMod == 0) {
            result = "Even number of digits";
            return;
        }

        if (digitCounter > 3) {
            multiplier = (int) Math.pow(10, ((digitCounter - 5) / 2) + 1);
            num /= multiplier;
            div = num / 1000;
            mod = num % 1000;
            modDisp = String.format("%03d", mod);
        } else {
            modDisp = String.format("%03d", num);
        }
        result = modDisp;
    }

    public void countDigits() {
        digitCounter = 0;
        digitDiv = Math.abs(num);
        while (digitDiv != 0) {
            digitDiv /= 10;
            digitCounter++;
        }
    }
}