public class Middle3 {
    private static final int[] items = {123, 12345, 1234567, 987654321, 10001, -10001, -123, -100, 100, -12345, 1, 2, -1, -10, 2002, -2002, 0};
    
    public static void main(String[] args) {
        for (int i = 0; i < items.length; i++) {
            processNumber(items[i]);
        }
    }
    
    private static void processNumber(int num) {
        String numDisp = String.format("%-9d", num);
        if (num < 100) {
            System.out.println(numDisp + " --> too small");
        } else {
            int power = 9;
            boolean done = false;
            while (power >= 1 && !done) {
                long power10 = (long) Math.pow(10, power);
                if (num >= power10) {
                    done = true;
                    if (power % 2 == 1) {
                        System.out.println(numDisp + " --> even number digits");
                    } else {
                        int num2 = num;
                        int divisor = (int) Math.pow(10, (power / 2) - 1);
                        num2 /= divisor;
                        int threeDigits = num2 % 1000;
                        System.out.println(numDisp + " --> " + threeDigits);
                    }
                }
                power--;
            }
        }
    }
}