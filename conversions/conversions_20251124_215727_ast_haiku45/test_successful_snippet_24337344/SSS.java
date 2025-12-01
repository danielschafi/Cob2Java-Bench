import java.util.Scanner;
import java.text.DecimalFormat;

public class SSS {
    private double x;
    private double y;
    private double z;
    private double xi;
    private double zeta;
    private double a;
    private double b;
    private double c;
    private double s;
    private double alpha;
    private double beta;
    private double gamma;
    private String inputPrompt;
    private Scanner scanner;

    public SSS() {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        SSS program = new SSS();
        program.main_procedure();
    }

    private void main_procedure() {
        inputPrompt = "Enter the length of side a:";
        getSide();
        a = x;

        inputPrompt = "Enter the length of side b:";
        getSide();
        b = x;

        inputPrompt = "Enter the length of side c:";
        getSide();
        c = x;

        s = Math.round((a + b + c) / 2.0 * 100000000.0) / 100000000.0;

        if (Math.max(Math.max(a, b), c) > s) {
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

        System.out.println(String.format("%15s%15.4f%15.4f%15.4f%15s", "Sides:", a, b, c, ""));

        System.out.println(String.format("%15s%15.4f%15.4f%15.4f%15s", "Angles:", alpha, beta, gamma, "radians"));

        xi = alpha;
        toDegrees();
        double alphaDeg = zeta;
        xi = beta;
        toDegrees();
        double betaDeg = zeta;
        xi = gamma;
        toDegrees();
        double gammaDeg = zeta;

        System.out.println(String.format("%15s%15.4f%15.4f%15.4f%15s", "", alphaDeg, betaDeg, gammaDeg, "degrees"));
    }

    private void getSide() {
        while (true) {
            System.out.println(inputPrompt);
            x = scanner.nextDouble();
            DecimalFormat df = new DecimalFormat("0.0000");
            System.out.println("  entered: " + df.format(x));
            if (x > 0.0) {
                break;
            }
            System.out.println("  lengths must be positive, try again...");
        }
    }

    private void lawOfCosines() {
        zeta = Math.acos((x * x + y * y - z * z) / (2.0 * x * y));
        zeta = Math.round(zeta * 100000000.0) / 100000000.0;
    }

    private void toDegrees() {
        zeta = 180.0 * xi / Math.PI;
        zeta = Math.round(zeta * 100000000.0) / 100000000.0;
    }
}