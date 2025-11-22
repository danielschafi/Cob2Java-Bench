import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SQRT {
    private static final int SCALE = 6;
    private static final int MAX_ITERATIONS = 1000;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("sqrtFIXED.dat"));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out))) {
            
            writer.println("         SQUARE ROOT APPROXIMATIONS");
            writer.println("--------------------------------------------");
            writer.println("        NUMBER               SQUARE ROOT");
            writer.println(" -------------------     ------------------");

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() < 23) {
                    continue;
                }

                String zStr = line.substring(0, 17).trim();
                String diffStr = line.substring(17, 23).trim();

                BigDecimal z;
                BigDecimal diff;

                try {
                    z = new BigDecimal(zStr);
                    diff = new BigDecimal("0." + diffStr);
                } catch (NumberFormatException e) {
                    continue;
                }

                if (z.compareTo(BigDecimal.ZERO) <= 0) {
                    writer.printf(" %18.6f        INVALID INPUT%n", z);
                    continue;
                }

                BigDecimal x = z.divide(BigDecimal.valueOf(2), SCALE + 5, RoundingMode.HALF_UP);
                BigDecimal y = BigDecimal.ZERO;
                boolean converged = false;

                for (int k = 1; k <= MAX_ITERATIONS; k++) {
                    y = z.divide(x, SCALE + 5, RoundingMode.HALF_UP)
                         .add(x)
                         .multiply(BigDecimal.valueOf(0.5))
                         .setScale(SCALE + 5, RoundingMode.HALF_UP);

                    BigDecimal temp = y.subtract(x).abs();
                    BigDecimal denominator = y.add(x);

                    if (denominator.compareTo(BigDecimal.ZERO) != 0) {
                        BigDecimal ratio = temp.divide(denominator, SCALE + 5, RoundingMode.HALF_UP);
                        if (ratio.compareTo(diff) <= 0) {
                            converged = true;
                            break;
                        }
                    }

                    x = y;
                }

                if (converged) {
                    writer.printf(" %18.6f     %18.6f%n", z, y);
                } else {
                    writer.printf(" %18.6f  ATTEMPT ABORTED,TOO MANY ITERATIONS%n", z);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}