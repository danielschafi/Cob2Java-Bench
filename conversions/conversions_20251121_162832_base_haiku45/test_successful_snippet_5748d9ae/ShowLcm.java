public class ShowLcm {
    
    public static void main(String[] args) {
        System.out.println("lcm(35, 21) = " + lcm(35, 21));
    }
    
    public static long lcm(long m, long n) {
        return Math.abs(m * n) / gcd(m, n);
    }
    
    public static long gcd(long m, long n) {
        long x = m;
        long y = n;
        long temp;
        
        while (y != 0) {
            temp = x;
            x = y;
            y = temp % y;
        }
        
        return Math.abs(x);
    }
}