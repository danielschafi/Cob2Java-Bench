import java.math.BigInteger;

public class Middle3 {
    public static void main(String[] args) {
        int[] items = {123, 12345, 1234567, 987654321, 10001, -10001, -123, -100, 100, -12345, 1, 2, -1, -10, 2002, -2002, 0};

        for (int item : items) {
            if (item == 0) {
                break;
            }
            int num = item;
            String numDisp = String.format("%9d", item);
            String result;

            if (num < 100) {
                result = "too small";
            } else {
                result = null;
                for (int power = 9; power >= 1 && result == null; power--) {
                    BigInteger power10 = BigInteger.TEN.pow(power);
                    if (num >= power10.intValue()) {
                        if (power % 2 == 1) {
                            result = "even number digits";
                        } else {
                            int num2 = num / (int) Math.pow(10, (power / 2) - 1);
                            int threeDigits = num2 % 1000;
                            result = String.format("%03d", threeDigits);
                        }
                    }
                }
            }

            System.out.println(numDisp + " --> " + result);
        }
    }
}