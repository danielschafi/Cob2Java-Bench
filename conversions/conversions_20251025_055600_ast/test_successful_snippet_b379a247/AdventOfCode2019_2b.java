import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdventOfCode2019_2b {
    public static void main(String[] args) {
        String inputFileName = "2.input";
        String inputRec = new String(new char[9999]);
        int[] iOp = new int[9999];
        int[] op = new int[9999];
        int sepNumber = 0;
        int ptr = 0;
        int addr = 0;
        String val = new String(new char[15]);
        int res = 0;
        int resFmt = 0;
        int noun = 0;
        int verb = 0;
        int len = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            inputRec = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < inputRec.length(); i++) {
            if (inputRec.charAt(i) == ',') {
                sepNumber++;
            }
        }
        if (sepNumber < 9999) {
            sepNumber++;
        } else {
            sepNumber = 9999;
        }

        ptr = 1;
        for (int i = 0; i < sepNumber; i++) {
            int commaIndex = inputRec.indexOf(',', ptr - 1);
            if (commaIndex == -1) {
                val = inputRec.substring(ptr - 1);
            } else {
                val = inputRec.substring(ptr - 1, commaIndex);
                ptr = commaIndex + 1;
            }
            iOp[len] = Integer.parseInt(val);
            len++;
        }

        for (noun = 0; noun <= 99; noun++) {
            for (verb = 0; verb <= 99; verb++) {
                System.arraycopy(iOp, 0, op, 0, len);
                op[1] = noun;
                op[2] = verb;

                ptr = 0;
                while (ptr < len) {
                    switch (op[ptr]) {
                        case 1:
                            ptr++;
                            addr = op[ptr] + 1;
                            res = op[addr];
                            ptr++;
                            addr = op[ptr] + 1;
                            res += op[addr];
                            ptr++;
                            addr = op[ptr] + 1;
                            op[addr] = res;
                            break;
                        case 2:
                            ptr++;
                            addr = op[ptr] + 1;
                            res = op[addr];
                            ptr++;
                            addr = op[ptr] + 1;
                            res *= op[addr];
                            ptr++;
                            addr = op[ptr] + 1;
                            op[addr] = res;
                            break;
                        case 99:
                            res = op[0];
                            resFmt = res;
                            System.out.println(resFmt);
                            System.exit(0);
                        default:
                            break;
                    }
                    ptr++;
                }

                if (res == 19690720) {
                    res = 100 * noun + verb;
                    resFmt = res;
                    System.out.println(resFmt);
                    System.exit(0);
                }
            }
        }
    }
}