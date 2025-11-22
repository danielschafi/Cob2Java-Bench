import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AOC202013Part2 {
    private List<BigInteger> wsBuses = new ArrayList<>();
    private List<BigInteger> wsRemainders = new ArrayList<>();
    private int len = 0;

    public static void main(String[] args) {
        AOC202013Part2 program = new AOC202013Part2();
        program.run();
    }

    public void run() {
        read();
        BigInteger result = findTimestamp();
        System.out.println(result);
    }

    private void read() {
        try (BufferedReader br = new BufferedReader(new FileReader("d13.input"))) {
            br.readLine();
            String inputRecord = br.readLine();
            
            if (inputRecord == null) {
                return;
            }

            String[] tokens = inputRecord.split(",", -1);
            int j = 0;
            
            for (int i = 0; i < tokens.length && i < 99; i++) {
                String token = tokens[i].trim();
                
                if (!token.equals("x") && !token.isEmpty()) {
                    try {
                        int wsI = Integer.parseInt(token);
                        
                        if (wsI != 0) {
                            wsBuses.add(BigInteger.valueOf(wsI));
                            
                            int wsM = wsI - i;
                            int quotient = wsM / wsI;
                            wsM = wsM % wsI;
                            
                            if (wsM < 0) {
                                wsM += wsI;
                            }
                            
                            wsRemainders.add(BigInteger.valueOf(wsM));
                            j++;
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
            
            len = j;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BigInteger findTimestamp() {
        if (len == 0) {
            return BigInteger.ZERO;
        }

        BigInteger n = wsBuses.get(0);
        BigInteger a = wsRemainders.get(0);

        for (int i = 1; i < len; i++) {
            BigInteger n1 = wsBuses.get(i);
            BigInteger a1 = wsRemainders.get(i);
            BigInteger wsMod = BigInteger.ZERO;

            while (!wsMod.equals(a1)) {
                a = a.add(n);
                wsMod = a.mod(n1);
            }

            n = n.multiply(n1);
        }

        return a;
    }
}