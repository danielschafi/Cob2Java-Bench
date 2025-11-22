public class Middle3 {
    private static final int[] VALUE_ITEMS = {123, 12345, 1234567, 987654321, 10001, -10001, -123, -100, 100, -12345, 1, 2, -1, -10, 2002, -2002, 0};
    
    public static void main(String[] args) {
        for (int i = 0; i < VALUE_ITEMS.length; i++) {
            int num = VALUE_ITEMS[i];
            String result;
            
            if (num < 100) {
                result = "too small";
            } else {
                boolean done = false;
                for (int power = 9; power >= 1 && !done; power--) {
                    long power10 = (long) Math.pow(10, power);
                    if (num >= power10) {
                        if (power % 2 == 1) {
                            result = "even number digits";
                            done = true;
                        } else {
                            int num2 = num / (int) Math.pow(10, (power / 2) - 1);
                            int threeDigits = num2 % 1000;
                            result = String.valueOf(threeDigits);
                            done = true;
                        }
                    }
                }
            }
            
            System.out.println(num + " --> " + result);
        }
    }
}