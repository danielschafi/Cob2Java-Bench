import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SSS {
    private static final int SCALE = 8;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    
    private BigDecimal X;
    private BigDecimal Y;
    private BigDecimal Z;
    private BigDecimal XI;
    private BigDecimal ZETA;
    
    private BigDecimal A;
    private BigDecimal B;
    private BigDecimal C;
    private BigDecimal S;
    
    private BigDecimal ALPHA;
    private BigDecimal BETA;
    private BigDecimal GAMMA;
    
    private Scanner scanner;
    
    public SSS() {
        scanner = new Scanner(System.in);
        X = BigDecimal.ZERO;
        Y = BigDecimal.ZERO;
        Z = BigDecimal.ZERO;
        XI = BigDecimal.ZERO;
        ZETA = BigDecimal.ZERO;
        A = BigDecimal.ZERO;
        B = BigDecimal.ZERO;
        C = BigDecimal.ZERO;
        S = BigDecimal.ZERO;
        ALPHA = BigDecimal.ZERO;
        BETA = BigDecimal.ZERO;
        GAMMA = BigDecimal.ZERO;
    }
    
    public static void main(String[] args) {
        SSS program = new SSS();
        program.main_procedure();
    }
    
    private void main_procedure() {
        String inputPrompt = "Enter the length of side a:";
        getSide(inputPrompt);
        A = X;
        
        inputPrompt = "Enter the length of side b:";
        getSide(inputPrompt);
        B = X;
        
        inputPrompt = "Enter the length of side c:";
        getSide(inputPrompt);
        C = X;
        
        S = A.add(B).add(C).divide(new BigDecimal(2), SCALE, ROUNDING_MODE);
        
        BigDecimal maxSide = max(A, max(B, C));
        if (maxSide.compareTo(S) > 0) {
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
        
        System.out.println("Sides:                  " + formatNumber(A) + "  " + formatNumber(B) + "  " + formatNumber(C));
        
        System.out.println("Angles:                 " + formatNumber(ALPHA) + "  " + formatNumber(BETA) + "  " + formatNumber(GAMMA) + "  radians");
        
        XI = ALPHA;
        toDegrees();
        BigDecimal alphaDegs = ZETA;
        XI = BETA;
        toDegrees();
        BigDecimal betaDegs = ZETA;
        XI = GAMMA;
        toDegrees();
        BigDecimal gammaDegs = ZETA;
        
        System.out.println("                        " + formatNumber(alphaDegs) + "  " + formatNumber(betaDegs) + "  " + formatNumber(gammaDegs) + "  degrees");
    }
    
    private void getSide(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            X = new BigDecimal(input);
            System.out.println("  entered: " + formatNumber(X));
            if (X.compareTo(BigDecimal.ZERO) > 0) {
                break;
            } else {
                System.out.println("  lengths must be positive, try again...");
            }
        }
    }
    
    private void lawOfCosines() {
        BigDecimal numerator = X.multiply(X).add(Y.multiply(Y)).subtract(Z.multiply(Z));
        BigDecimal denominator = new BigDecimal(2).multiply(X).multiply(Y);
        BigDecimal cosValue = numerator.divide(denominator, SCALE, ROUNDING_MODE);
        ZETA = new BigDecimal(Math.acos(cosValue.doubleValue()));
    }
    
    private void toDegrees() {
        BigDecimal pi = new BigDecimal(Math.PI);
        ZETA = new BigDecimal(180).multiply(XI).divide(pi, SCALE, ROUNDING_MODE);
    }
    
    private BigDecimal max(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) > 0 ? a : b;
    }
    
    private String formatNumber(BigDecimal num) {
        return String.format("%11.4f", num);
    }
}