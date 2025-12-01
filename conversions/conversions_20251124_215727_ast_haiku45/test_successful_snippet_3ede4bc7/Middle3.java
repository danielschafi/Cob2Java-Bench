public class Middle3 {
    private static int[] items = {
        123, 12345, 1234567, 987654321, 10001, -10001, -123, -100, 100, -12345, 1, 2, -1, -10, 2002, -2002, 0
    };
    
    private static int num;
    private static int num2;
    private static int power;
    private static long power10;
    private static int threeDigits;
    private static String result;
    private static int flag;
    private static boolean done;
    
    public static void main(String[] args) {
        for (int item = 0; item < items.length; item++) {
            if (items[item] == 0) break;
            outerProcedure(item);
        }
    }
    
    private static void outerProcedure(int item) {
        num = items[item];
        String numDisp = String.format("%9d", num);
        
        if (num < 100) {
            result = "too small";
        } else {
            done = false;
            for (power = 9; power >= 1; power--) {
                innerProcedure();
                if (done) break;
            }
        }
        
        System.out.println(numDisp + " --> " + result);
    }
    
    private static void innerProcedure() {
        flag = 0;
        power10 = (long) Math.pow(10, power);
        
        if (num >= power10) {
            flag = 1;
            if (power % 2 == 1) {
                result = "even number digits";
            } else {
                num2 = num;
                num2 = (int) (num2 / Math.pow(10, (power / 2) - 1));
                threeDigits = num2 % 1000;
                result = String.format("%3d", threeDigits).replace(' ', '0');
            }
        }
    }
}