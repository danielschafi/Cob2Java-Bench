import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdventOfCode2019_2a {
    private static final int MAX_LENGTH = 9999;
    private int[] op = new int[MAX_LENGTH];
    private int len;
    private int sepNumber;
    private int ptr;
    private int addr;
    private int val;
    private int res;

    public static void main(String[] args) {
        AdventOfCode2019_2a program = new AdventOfCode2019_2a();
        program.run();
    }

    private void run() {
        try (BufferedReader br = new BufferedReader(new FileReader("2.input"))) {
            String inputRec = br.readLine();

            sepNumber = 0;
            for (char c : inputRec.toCharArray()) {
                if (c == ',') {
                    sepNumber++;
                }
            }
            if (sepNumber < MAX_LENGTH) {
                sepNumber++;
            } else {
                sepNumber = MAX_LENGTH;
            }

            ptr = 0;
            String[] values = inputRec.split(",");
            for (String value : values) {
                op[len++] = Integer.parseInt(value);
            }

            op[1] = 12;
            op[2] = 2;

            ptr = 0;
            while (ptr <= len) {
                switch (op[ptr]) {
                    case 1:
                        ptr++;
                        addr = op[ptr++] + 1;
                        res = op[addr];
                        ptr++;
                        addr = op[ptr++] + 1;
                        res += op[addr];
                        ptr++;
                        addr = op[ptr++] + 1;
                        op[addr] = res;
                        break;
                    case 2:
                        ptr++;
                        addr = op[ptr++] + 1;
                        res = op[addr];
                        ptr++;
                        addr = op[ptr++] + 1;
                        res *= op[addr];
                        ptr++;
                        addr = op[ptr++] + 1;
                        op[addr] = res;
                        break;
                    case 99:
                        System.out.printf("%015d%n", op[0]);
                        return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}