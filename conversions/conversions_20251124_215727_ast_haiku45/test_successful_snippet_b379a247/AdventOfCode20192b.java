import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AdventOfCode20192b {
    private static final int MAX_SIZE = 9999;
    private int len = 0;
    private long[] iOp = new long[MAX_SIZE];
    private long[] op = new long[MAX_SIZE];
    private int sepNumber = 0;
    private int ptr = 0;
    private int addr = 0;
    private String val = "";
    private long res = 0;
    private int noun = 0;
    private int verb = 0;

    public static void main(String[] args) {
        AdventOfCode20192b program = new AdventOfCode20192b();
        program.run();
    }

    private void run() {
        try {
            String inputRec = new String(Files.readAllBytes(Paths.get("2.input"))).trim();
            
            sepNumber = 0;
            for (char c : inputRec.toCharArray()) {
                if (c == ',') {
                    sepNumber++;
                }
            }
            if (sepNumber < 9999) {
                sepNumber++;
            } else {
                sepNumber = 9999;
            }

            ptr = 0;
            String[] parts = inputRec.split(",");
            for (String part : parts) {
                iOp[len] = Long.parseLong(part.trim());
                len++;
            }

            for (noun = 0; noun <= 99; noun++) {
                for (verb = 0; verb <= 99; verb++) {
                    for (ptr = 0; ptr < len; ptr++) {
                        op[ptr] = iOp[ptr];
                    }

                    op[1] = noun;
                    op[2] = verb;

                    ptr = 0;
                    while (ptr < len) {
                        long opcode = op[ptr];
                        
                        if (opcode == 1) {
                            ptr++;
                            addr = (int)op[ptr] + 1;
                            res = op[addr];
                            ptr++;
                            addr = (int)op[ptr] + 1;
                            res += op[addr];
                            ptr++;
                            addr = (int)op[ptr] + 1;
                            op[addr] = res;
                        } else if (opcode == 2) {
                            ptr++;
                            addr = (int)op[ptr] + 1;
                            res = op[addr];
                            ptr++;
                            addr = (int)op[ptr] + 1;
                            res *= op[addr];
                            ptr++;
                            addr = (int)op[ptr] + 1;
                            op[addr] = res;
                        } else if (opcode == 99) {
                            res = op[0];
                            break;
                        }
                        ptr++;
                    }

                    if (res == 19690720L) {
                        res = 100L * noun + verb;
                        System.out.println(res);
                        return;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}