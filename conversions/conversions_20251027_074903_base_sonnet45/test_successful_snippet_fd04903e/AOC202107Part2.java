import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AOC202107Part2 {
    public static void main(String[] args) {
        List<Integer> wsArray = new ArrayList<>();
        int n = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader("d07.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                wsArray.add(Integer.parseInt(line.trim()));
                n++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }
        
        BigInteger wsSum = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            wsSum = wsSum.add(BigInteger.valueOf(wsArray.get(i)));
        }
        
        long wsMean = wsSum.divide(BigInteger.valueOf(n)).longValue();
        
        BigInteger wsAns1 = BigInteger.ZERO;
        BigInteger wsAns2 = BigInteger.ZERO;
        BigInteger wsAns3 = BigInteger.ZERO;
        
        for (int i = 0; i < n; i++) {
            long x = Math.abs(wsMean - 1 - wsArray.get(i));
            wsAns1 = wsAns1.add(BigInteger.valueOf((x * (x + 1)) / 2));
            
            x = Math.abs(wsMean - wsArray.get(i));
            wsAns2 = wsAns2.add(BigInteger.valueOf((x * (x + 1)) / 2));
            
            x = Math.abs(wsMean + 1 - wsArray.get(i));
            wsAns3 = wsAns3.add(BigInteger.valueOf((x * (x + 1)) / 2));
        }
        
        BigInteger wsResult = wsAns1.min(wsAns2).min(wsAns3);
        System.out.println(wsResult);
    }
}