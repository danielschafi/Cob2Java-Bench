import java.util.ArrayList;
import java.util.List;

public class Middle3 {
    private static final int[] VALUE_ITEMS = {
        123, 12345, 1234567, 987654321, 10001, -10001, -123, -100, 100, -12345, 1, 2, -1, -10, 2002, -2002, 0
    };

    public static void main(String[] args) {
        for (int item : VALUE_ITEMS) {
            if (item == 0) {
                break;
            }
            processItem(item);
        }
    }

    private static void processItem(int num) {
        String numDisp = formatNumber(num);
        String result;
        
        int absNum = Math.abs(num);
        
        if (absNum < 100) {
            result = "too small";
        } else {
            result = findMiddleDigits(absNum);
        }
        
        System.out.println(numDisp + " --> " + result);
    }

    private static String findMiddleDigits(int num) {
        String result = "";
        boolean done = false;
        
        for (int power = 9; power >= 1 && !done; power--) {
            long power10 = (long) Math.pow(10, power);
            
            if (num >= power10) {
                done = true;
                
                if (power % 2 == 1) {
                    result = "even number digits";
                } else {
                    int divisor = (int) Math.pow(10, (power / 2) - 1);
                    int num2 = num / divisor;
                    int threeDigits = num2 % 1000;
                    result = String.valueOf(threeDigits);
                }
            }
        }
        
        return result;
    }

    private static String formatNumber(int num) {
        String formatted = String.format("%10d", num);
        return formatted;
    }
}