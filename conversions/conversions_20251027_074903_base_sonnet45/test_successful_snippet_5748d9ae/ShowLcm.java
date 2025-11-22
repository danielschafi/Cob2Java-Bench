public class ShowLcm {
    public static void main(String[] args) {
        System.out.println("lcm(35, 21) = " + lcm(35, 21));
    }

    public static int lcm(int m, int n) {
        return Math.abs(m * n) / gcd(m, n);
    }

    public static int gcd(int m, int n) {
        int x = m;
        int y = n;
        int temp;

        while (y != 0) {
            temp = x;
            x = y;
            y = temp % y;
        }

        return Math.abs(x);
    }
}