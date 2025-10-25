import java.util.Scanner;

public class Hailstones {
    private static final int MOST = 1000000;
    private static final int COVERAGE = 100000;
    private static long stones;
    private static long n;
    private static long storm;
    private static int showArg;
    private static final int SHOW_DEFAULT = 27;
    private static long showSequence;
    private static final long[] longest = new long[2];
    private static final long[] hail = new long[MOST];
    private static String show;
    private static long lowRange;
    private static long highRange;
    private static long range;
    private static long remain;
    private static long unused;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (args.length > 0) {
            showArg = Integer.parseInt(args[0]);
        } else {
            showArg = SHOW_DEFAULT;
        }

        if (showArg < 1 || showArg > COVERAGE) {
            showArg = SHOW_DEFAULT;
        }
        showSequence = showArg;

        longest[0] = 1;
        for (storm = 1; storm <= COVERAGE; storm++) {
            hailstone();
        }
        System.out.println("Longest at: " + longest[1] + " with " + longest[0] + " elements");
    }

    private static void hailstone() {
        stones = 0;
        n = storm;
        while (n != 1) {
            if (stones > MOST) {
                System.err.println("too many hailstones");
                System.exit(1);
            }

            stones++;
            hail[(int) stones] = n;
            remain = n % 2;
            if (remain == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
        }
        stones++;
        hail[(int) stones] = n;

        if (stones > longest[0]) {
            longest[0] = stones;
            longest[1] = storm;
        }

        if (storm == showSequence) {
            System.out.print(showSequence + ": ");
            for (range = 1; range <= stones; range++) {
                lowRange = 5;
                highRange = stones - 4;
                if (range < lowRange || range > highRange) {
                    show = String.format("%10d", hail[(int) range]).trim();
                    System.out.print(show);
                    if (range < stones) {
                        System.out.print(", ");
                    }
                }
                if (range == lowRange && stones > 8) {
                    System.out.print("..., ");
                }
            }
            System.out.println(": " + stones + " elements");
        }
    }
}