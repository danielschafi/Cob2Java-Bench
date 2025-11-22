public class EthiopianMultiplication {
    public static void main(String[] args) {
        int l = 17;
        int r = 34;
        long ethiopianMultiply = ethiopianMultiply(l, r);
        System.out.println(ethiopianMultiply);
        long product = l * r;
        System.out.println(product);
    }

    public static long ethiopianMultiply(int l, int r) {
        long product = 0;
        while (l != 0) {
            if (!isEven(l)) {
                product += r;
            }
            l = halve(l);
            r = twice(r);
        }
        return product;
    }

    public static boolean isEven(int n) {
        int remainder = n % 2;
        return remainder == 0;
    }

    public static int halve(int n) {
        return n / 2;
    }

    public static int twice(int n) {
        return n * 2;
    }
}