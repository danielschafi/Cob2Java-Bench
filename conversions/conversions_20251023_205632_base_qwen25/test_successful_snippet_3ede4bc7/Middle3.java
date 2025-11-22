import java.text.DecimalFormat;

public class Middle3 {
    public static void main(String[] args) {
        int[] valueItems = {123, 12345, 1234567, 987654321, 10001, -10001, -123, -100, 100, -12345, 1, 2, -1, -10, 2002, -2002, 0};
        int num, numDisp, num2, power, power10, threeDigits, flag;
        String result;
        DecimalFormat df = new DecimalFormat("---------0");

        for (int item = 0; item < valueItems.length; item++) {
            num = valueItems[item];
            numDisp = valueItems[item];
            result = "";

            if (num < 100) {
                result = "too small";
            } else {
                for (power = 9; power >= 1; power--) {
                    flag = 0;
                    power10 = (int) Math.pow(10, power);
                    if (num >= power10) {
                        flag = 1;
                        if (power % 2 == 1) {
                            result = "even number digits";
                        } else {
                            num2 = num;
                            num2 /= Math.pow(10, (power / 2) - 1);
                            threeDigits = num2 % 1000;
                            result = String.format("%03d", threeDigits);
                        }
                    }
                    if (flag == 1) break;
                }
            }
            System.out.println(df.format(numDisp) + " --> " + result);
        }
    }
}