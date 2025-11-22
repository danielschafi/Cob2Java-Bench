import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class AdventOfCode2019_2a {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("2.input"));
            String inputRec = reader.readLine();
            reader.close();

            if (inputRec == null || inputRec.isEmpty()) {
                return;
            }

            String[] tokens = inputRec.split(",");
            int len = tokens.length;
            
            BigInteger[] op = new BigInteger[len + 1];
            for (int i = 0; i < len; i++) {
                op[i + 1] = new BigInteger(tokens[i].trim());
            }

            op[2] = BigInteger.valueOf(12);
            op[3] = BigInteger.valueOf(2);

            int ptr = 1;
            while (ptr <= len) {
                int opcode = op[ptr].intValue();
                
                if (opcode == 1) {
                    ptr++;
                    int addr1 = op[ptr].intValue() + 1;
                    BigInteger res = op[addr1];
                    ptr++;
                    int addr2 = op[ptr].intValue() + 1;
                    res = res.add(op[addr2]);
                    ptr++;
                    int addr3 = op[ptr].intValue() + 1;
                    op[addr3] = res;
                } else if (opcode == 2) {
                    ptr++;
                    int addr1 = op[ptr].intValue() + 1;
                    BigInteger res = op[addr1];
                    ptr++;
                    int addr2 = op[ptr].intValue() + 1;
                    res = res.multiply(op[addr2]);
                    ptr++;
                    int addr3 = op[ptr].intValue() + 1;
                    op[addr3] = res;
                } else if (opcode == 99) {
                    System.out.println(op[1].toString().trim());
                    return;
                }
                ptr++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}