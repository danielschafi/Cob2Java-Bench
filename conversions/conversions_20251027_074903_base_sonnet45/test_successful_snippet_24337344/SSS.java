import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SSS {
    private static final int SCALE = 8;
    private static final RoundingMode ROUNDING = RoundingMode.HALF_UP;

    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal z;
    private BigDecimal xi;
    private BigDecimal zeta;
    private BigDecimal a;
    private BigDecimal b;
    private BigDecimal c;
    private BigDecimal s;
    private BigDecimal alpha;
    private BigDecimal beta;
    private BigDecimal gamma;

    public static void main(String[] args) {
        SSS program = new SSS();
        program.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);

        a = getSide(scanner, "Enter the length of side a:");
        b = getSide(scanner, "Enter the length of side b:");
        c = getSide(scanner, "Enter the length of side c:");

        try {
            s = a.add(b).add(c).divide(new BigDecimal("2"), SCALE, ROUNDING);
        } catch (ArithmeticException e) {
            System.out.println("Semiperimeter: Arithmetic overflow");
            System.exit(0);
        }

        BigDecimal maxSide = a.max(b).max(c);
        if (maxSide.compareTo(s) > 0) {
            System.out.println("The triangle inequality is violated...");
            System.out.println("The triangle has no real solution.");
            System.exit(0);
        }

        x = a;
        y = b;
        z = c;
        lawOfCosines();
        gamma = zeta;

        x = b;
        y = c;
        z = a;
        lawOfCosines();
        alpha = zeta;

        x = c;
        y = a;
        z = b;
        lawOfCosines();
        beta = zeta;

        System.out.println("Solution:");

        System.out.printf("%15s%15.4f%15.4f%15.4f%15s%n", 
            "Sides:", a.doubleValue(), b.doubleValue(), c.doubleValue(), "");

        System.out.printf("%15s%15.4f%15.4f%15.4f%15s%n", 
            "Angles:", alpha.doubleValue(), beta.doubleValue(), gamma.doubleValue(), "radians");

        xi = alpha;
        toDegrees();
        BigDecimal alphaDeg = zeta;

        xi = beta;
        toDegrees();
        BigDecimal betaDeg = zeta;

        xi = gamma;
        toDegrees();
        BigDecimal gammaDeg = zeta;

        System.out.printf("%15s%15.4f%15.4f%15.4f%15s%n", 
            "", alphaDeg.doubleValue(), betaDeg.doubleValue(), gammaDeg.doubleValue(), "degrees");

        scanner.close();
    }

    private BigDecimal getSide(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                BigDecimal value = new BigDecimal(input);
                System.out.printf("  entered: %10.4f%n", value.doubleValue());
                if (value.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("  lengths must be positive, try again...");
                    continue;
                }
                return value.setScale(SCALE, ROUNDING);
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input, try again...");
            }
        }
    }

    private void lawOfCosines() {
        BigDecimal xSquared = x.multiply(x);
        BigDecimal ySquared = y.multiply(y);
        BigDecimal zSquared = z.multiply(z);
        BigDecimal numerator = xSquared.add(ySquared).subtract(zSquared);
        BigDecimal denominator = new BigDecimal("2").multiply(x).multiply(y);
        BigDecimal cosValue = numerator.divide(denominator, SCALE, ROUNDING);
        zeta = new BigDecimal(Math.acos(cosValue.doubleValue())).setScale(SCALE, ROUNDING);
    }

    private void toDegrees() {
        BigDecimal pi = new BigDecimal(Math.PI);
        zeta = new BigDecimal("180").multiply(xi).divide(pi, SCALE, ROUNDING);
    }
}