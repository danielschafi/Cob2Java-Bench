public class middle3 {
    public static void main(String[] args) {
        int[] items = {123, 12345, 1234567, 987654321, 10001, -10001, -123, -100, 100, -12345, 1, 2, -1, -10, 2002, -2002, 0};
        
        for (int i = 0; i < items.length; i++) {
            int num = items[i];
            String numDisp = formatNumber(num);
            String result = check(num);
            System.out.println(numDisp + " --> " + result);
        }
    }
    
    private static String formatNumber(int num) {
        return String.format("%10d", num);
    }
    
    private static String check(int num) {
        if (num >= -99 && num <= 99) {
            return "Number too small";
        }
        
        int digitCounter = countDigits(num);
        
        if (digitCounter % 2 == 0) {
            return "Even number of digits";
        }
        
        int mod;
        if (digitCounter > 3) {
            int multiplier = (int) Math.pow(10, (((digitCounter - 5) / 2) + 1));
            int numTruncated = num / multiplier;
            mod = numTruncated % 1000;
        } else {
            mod = num;
        }
        
        return String.format("%3d", mod);
    }
    
    private static int countDigits(int num) {
        int count = 0;
        int temp = num;
        while (temp != 0) {
            temp = temp / 10;
            count++;
        }
        return count;
    }
}