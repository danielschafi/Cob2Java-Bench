import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RunStddev {
    
    private static class TbData {
        int tbSize;
        BigDecimal[] tbTable;
        
        public TbData() {
            tbSize = 0;
            tbTable = new BigDecimal[100];
        }
    }
    
    public static void main(String[] args) {
        TbData tbData = new TbData();
        boolean noMoreInput = false;
        
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            
            if (line == null) {
                noMoreInput = true;
            }
            
            do {
                if (!noMoreInput) {
                    int inpFld = Integer.parseInt(line.trim());
                    tbData.tbSize++;
                    tbData.tbTable[tbData.tbSize - 1] = new BigDecimal(inpFld);
                    
                    BigDecimal stddev = stddev(tbData);
                    
                    System.out.println("inp=" + String.format("%03d", inpFld) + 
                                     " stddev=" + formatStddev(stddev));
                    
                    line = reader.readLine();
                    if (line == null) {
                        noMoreInput = true;
                    }
                }
            } while (!noMoreInput);
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    private static BigDecimal stddev(TbData tbData) {
        BigDecimal sum = BigDecimal.ZERO;
        
        for (int i = 0; i < tbData.tbSize; i++) {
            sum = sum.add(tbData.tbTable[i]);
        }
        
        BigDecimal avg = sum.divide(new BigDecimal(tbData.tbSize), 4, RoundingMode.HALF_UP);
        
        BigDecimal sumsq = BigDecimal.ZERO;
        
        for (int i = 0; i < tbData.tbSize; i++) {
            BigDecimal diff = tbData.tbTable[i].subtract(avg);
            BigDecimal diffSquared = diff.multiply(diff);
            sumsq = sumsq.add(diffSquared);
        }
        
        BigDecimal variance = sumsq.divide(new BigDecimal(tbData.tbSize), 4, RoundingMode.HALF_UP);
        BigDecimal stddev = sqrt(variance, 4);
        
        return stddev;
    }
    
    private static BigDecimal sqrt(BigDecimal value, int scale) {
        if (value.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal x1 = new BigDecimal(Math.sqrt(value.doubleValue()));
        
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = value.divide(x0, scale + 2, RoundingMode.HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(new BigDecimal("2"), scale + 2, RoundingMode.HALF_UP);
        }
        
        return x1.setScale(scale, RoundingMode.HALF_UP);
    }
    
    private static String formatStddev(BigDecimal stddev) {
        String str = stddev.setScale(4, RoundingMode.HALF_UP).toPlainString();
        
        if (stddev.compareTo(BigDecimal.ZERO) >= 0) {
            str = "+" + str;
        }
        
        String[] parts = str.split("\\.");
        String intPart = parts[0];
        String decPart = parts.length > 1 ? parts[1] : "0000";
        
        while (decPart.length() < 4) {
            decPart += "0";
        }
        
        return intPart + "." + decPart;
    }
}