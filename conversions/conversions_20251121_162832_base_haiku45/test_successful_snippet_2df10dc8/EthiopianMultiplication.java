public class EthiopianMultiplication {
    
    public static void main(String[] args) {
        long l = 17;
        long r = 34;
        long ethiopianMultiply = ethiopianMultiply(l, r);
        System.out.println(ethiopianMultiply);
        long product = l * r;
        System.out.println(product);
    }
    
    public static long ethiopianMultiply(long l, long r) {
        long product = 0;
        while (l != 0) {
            if (evenp(l) == 0) {
                product += r;
            }
            l = halve(l);
            r = twice(r);
        }
        return product;
    }
    
    public static long halve(long n) {
        return n / 2;
    }
    
    public static long twice(long n) {
        return n * 2;
    }
    
    public static long evenp(long n) {
        long remainder = n % 2;
        return 1 - remainder;
    }
}