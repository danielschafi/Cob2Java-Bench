```java
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.*;
import java.util.*;

public class SQRT {
    private static final int MAX_ITERATIONS = 1000;
    
    static class InCard {
        BigDecimal inZ;
        BigDecimal inDiff;
    }
    
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("sqrtFIXED.dat"));
            
            PrintWriter out = new PrintWriter(System.out, true);
            
            out.println("         SQUARE ROOT APPROXIMATIONS");
            out.println("--------------------------------------------");
            out.println("        NUMBER               SQUARE ROOT");
            out.println(" -------------------        ------------------");
            
            for (String line : lines) {
                if (line.length() < 22) continue;
                
                InCard inCard = parseInputLine(line);
                if (inCard == null) continue;
                
                if (inCard.inZ.compareTo(BigDecimal.ZERO) <= 0) {
                    String formattedZ = formatNegative(inCard.inZ);
                    out.println(" " + formattedZ + "        INVALID INPUT");
                    continue;
                }
                
                BigDecimal z = inCard.inZ;
                BigDecimal diff = inCard.inDiff;
                BigDecimal x = z.divide(BigDecimal.valueOf(2), 6, RoundingMode.HALF_UP);
                
                boolean found = false;
                for (int k = 1; k <= MAX_ITERATIONS; k++) {
                    BigDecimal y = BigDecimal.valueOf(0.5)
                        .multiply(x.add(z.divide(x, 6, RoundingMode.HALF_UP)))
                        .setScale(6, RoundingMode.HALF_UP);
                    
                    BigDecimal temp = y.subtract(x).abs();
                    BigDecimal divisor = y.add(x);
                    
                    if (divisor.compareTo(BigDecimal.ZERO) != 0) {
                        BigDecimal ratio = temp.divide(divisor, 6, RoundingMode.HALF_UP);
                        if (ratio.compareTo(diff) <= 0) {
                            String formattedZ = formatNumber(z);
                            String formattedY = formatNumber(y);
                            out.println(" " + formattedZ + "     " + formattedY);
                            found = true;
                            break;
                        }
                    }
                    
                    x = y;
                }
                
                if (!found) {
                    String formattedZ = formatNumber(z);
                    out.println(" " + formattedZ + "  ATTEMPT ABORTED,TOO MANY ITERATIONS");
                }
            }
            
            out.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static InCard parseInputLine(String line) {
        try {
            if (line.length() < 22) return null;
            
            String zStr = line.substring(0, 17).trim();
            String diffStr = line.substring(17, 22).trim();
            
            if (zStr.isEmpty() || diffStr.isEmpty()) return null;
            
            InCard card = new InCard();
            card.inZ = new BigDecimal(zStr);
            card.inDiff = new BigDecimal("0." + diffStr);
            
            return card;
        } catch (Exception e) {
            return null;
        }
    }
    
    private static String formatNumber(BigDecimal num) {
        String str = String.format("%17.6f", num);
        return str;
    }
    
    private static String formatNegative(BigDecimal num) {
        String str = String.format("%17.6f", num);
        return str;
    }
}