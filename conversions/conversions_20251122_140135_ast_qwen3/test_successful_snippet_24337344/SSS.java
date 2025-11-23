import java.util.Scanner;
import java.lang.Math;

public class SSS {
    private static double X, Y, Z, XI, ZETA, A, B, C, S, ALPHA, BETA, GAMMA, XVAL;
    private static String INPUT_PROMPT;
    private static String OLABEL, AVAL, BVAL, CVAL, OUNITS;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        INPUT_PROMPT = "Enter the length of side a:";
        getSide();
        A = X;
        
        INPUT_PROMPT = "Enter the length of side b:";
        getSide();
        B = X;
        
        INPUT_PROMPT = "Enter the length of side c:";
        getSide();
        C = X;
        
        S = Math.round((A + B + C) / 2 * 100000000.0) / 100000000.0;
        if (Math.max(Math.max(A, B), C) > S) {
            System.out.println("The triangle inequality is violated...");
            System.out.println("The triangle has no real solution.");
            System.exit(0);
        }
        
        X = A;
        Y = B;
        Z = C;
        lawOfCosines();
        GAMMA = ZETA;
        
        X = B;
        Y = C;
        Z = A;
        lawOfCosines();
        ALPHA = ZETA;
        
        X = C;
        Y = A;
        Z = B;
        lawOfCosines();
        BETA = ZETA;
        
        System.out.println("Solution:");
        
        OLABEL = "Sides:";
        AVAL = String.format("%10.4f", A);
        BVAL = String.format("%10.4f", B);
        CVAL = String.format("%10.4f", C);
        OUNITS = "";
        System.out.println(OLABEL + AVAL + BVAL + CVAL + OUNITS);
        
        OLABEL = "Angles:";
        AVAL = String.format("%10.4f", ALPHA);
        BVAL = String.format("%10.4f", BETA);
        CVAL = String.format("%10.4f", GAMMA);
        OUNITS = "radians";
        System.out.println(OLABEL + AVAL + BVAL + CVAL + OUNITS);
        
        OLABEL = "";
        XI = ALPHA;
        toDegrees();
        AVAL = String.format("%10.4f", ZETA);
        XI = BETA;
        toDegrees();
        BVAL = String.format("%10.4f", ZETA);
        XI = GAMMA;
        toDegrees();
        CVAL = String.format("%10.4f", ZETA);
        OUNITS = "degrees";
        System.out.println(OLABEL + AVAL + BVAL + CVAL + OUNITS);
        
        scanner.close();
    }
    
    private static void getSide() {
        System.out.println(INPUT_PROMPT);
        X = scanner.nextDouble();
        XVAL = X;
        System.out.println("  entered: " + XVAL);
        if (!(X > 0.0)) {
            System.out.println("  lengths must be positive, try again...");
            getSide();
        }
    }
    
    private static void lawOfCosines() {
        ZETA = Math.round(Math.acos((X * X + Y * Y - Z * Z) / (2 * X * Y)) * 100000000.0) / 100000000.0;
    }
    
    private static void toDegrees() {
        ZETA = Math.round(180 * XI / Math.PI * 100000000.0) / 100000000.0;
    }
}