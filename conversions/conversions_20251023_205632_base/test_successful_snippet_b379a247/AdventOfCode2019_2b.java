import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdventOfCode2019_2b {
    public static void main(String[] args) {
        String inputFilePath = "2.input";
        String inputRec = "";
        int[] ioOp = new int[10000];
        int[] op = new int[10000];
        int sepNumber = 0;
        int ptr = 0;
        int addr = 0;
        int val = 0;
        int res = 0;
        int resFmt = 0;
        int noun = 0;
        int verb = 0;
        int len = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
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

        ptr = 0;
        for (int i = 0; i < sepNumber; i++) {
            int commaIndex = inputRec.indexOf(',', ptr);
            if (commaIndex == -1) {
                commaIndex = inputRec.length();
            }
            val = Integer.parseInt(inputRec.substring(ptr, commaIndex));
            ptr = commaIndex + 1;
            len++;
            ioOp[len] = val;
        }

        for (noun = 0; noun <= 99; noun++) {
            for (verb = 0; verb <= 99; verb++) {
                System.arraycopy(ioOp, 1, op, 1, len);
                op[2] = noun;
                op[3] = verb;

                ptr = 1;
                while (ptr <= len) {
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
                            res = op[1];
                            resFmt = res;
                            ptr = len + 1;
                            break;
                    }
                    ptr++;
                }

                if (res == 19690720) {
                    res = 100 * noun + verb;
                    resFmt = res;
                    System.out.println(resFmt);
                    return;
                }
            }
        }
    }
}