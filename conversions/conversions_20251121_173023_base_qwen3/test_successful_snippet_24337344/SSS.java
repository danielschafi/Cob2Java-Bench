import java.util.Scanner;

public class SSS {
    private static double x, y, z, xi, zeta, a, b, c, s, alpha, beta, gamma, xval;
    private static String inputPrompt;
    private static String olabel,ounits;
    private static double aval,bval,cval;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        inputPrompt = "Enter the length of side a:";
        getSide(scanner);
        a = x;

        inputPrompt = "Enter the length of side b:";
        getSide(scanner);
        b = x;

        inputPrompt = "Enter the length of side c:";
        getSide(scanner);
        c = x;

        s = (a + b + c) / 2;
        if (Math.max(Math.max(a, b), c) > s) {
            System.out.println("The triangle inequality is violated...");
            System.out.println("The triangle has no real solution.");
            return;
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

        olabel = "Sides:";
        aval = a;
        bval = b;
        cval = c;
        ounits = "";
        displayOutput();

        olabel = "Angles:";
        aval = alpha;
        bval = beta;
        cval = gamma;
        ounits = "radians";
        displayOutput();

        olabel = "";
        xi = alpha;
        toDegrees();
        aval = zeta;
        xi = beta;
        toDegrees();
        bval = zeta;
        xi = gamma;
        toDegrees();
        cval = zeta;
        ounits = "degrees";
        displayOutput();
    }

    private static void getSide(Scanner scanner) {
        System.out.println(inputPrompt);
        x = scanner.nextDouble();
        xval = x;
        System.out.println("  entered: " + String.format("%.4f", xval));
        if (!(x > 0.0)) {
            System.out.println("  lengths must be positive, try again...");
            getSide(scanner);
        }
    }

    private static void lawOfCosines() {
        zeta = Math.acos((x * x + y * y - z * z) / (2 * x * y));
    }

    private static void toDegrees() {
        zeta = 180 * xi / Math.PI;
    }
    
    private static void displayOutput() {
        System.out.printf("%-15s%10.4f%10.4f%10.4f%-15s%n", olabel, aval, bval, cval, ounits);
    }
}