import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AdventOfCode2019_2a {
    private static List<Long> op = new ArrayList<>();
    private static int len = 0;
    private static int ptr = 0;
    private static int addr = 0;
    private static String val = "";
    private static long res = 0;

    public static void main(String[] args) {
        try {
            String inputRec = new String(Files.readAllBytes(Paths.get("2.input"))).trim();
            
            int sepNumber = 0;
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
                op.add(Long.parseLong(part.trim()));
                len++;
            }
            
            op.set(1, 12L);
            op.set(2, 2L);
            
            ptr = 0;
            while (ptr <= len - 1) {
                long opcode = op.get(ptr);
                
                if (opcode == 1) {
                    ptr++;
                    addr = (int)(op.get(ptr) + 1);
                    res = op.get(addr - 1);
                    ptr++;
                    addr = (int)(op.get(ptr) + 1);
                    res += op.get(addr - 1);
                    ptr++;
                    addr = (int)(op.get(ptr) + 1);
                    op.set(addr - 1, res);
                } else if (opcode == 2) {
                    ptr++;
                    addr = (int)(op.get(ptr) + 1);
                    res = op.get(addr - 1);
                    ptr++;
                    addr = (int)(op.get(ptr) + 1);
                    res *= op.get(addr - 1);
                    ptr++;
                    addr = (int)(op.get(ptr) + 1);
                    op.set(addr - 1, res);
                } else if (opcode == 99) {
                    System.out.println(op.get(0));
                    System.exit(0);
                }
                
                ptr++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}