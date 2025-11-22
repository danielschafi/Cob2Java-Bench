import java.io.*;
import java.util.*;

public class AdventOfCode2019_2a {
    private static List<Long> opTable = new ArrayList<>();
    private static int len = 0;

    public static void main(String[] args) {
        try {
            readInputFile();
            
            opTable.set(1, 12L);
            opTable.set(2, 2L);
            
            int ptr = 0;
            while (ptr <= len - 1) {
                long opcode = opTable.get(ptr);
                
                if (opcode == 1) {
                    ptr++;
                    int addr = (int)(opTable.get(ptr) + 1);
                    long res = opTable.get(addr);
                    ptr++;
                    addr = (int)(opTable.get(ptr) + 1);
                    res += opTable.get(addr);
                    ptr++;
                    addr = (int)(opTable.get(ptr) + 1);
                    opTable.set(addr, res);
                } else if (opcode == 2) {
                    ptr++;
                    int addr = (int)(opTable.get(ptr) + 1);
                    long res = opTable.get(addr);
                    ptr++;
                    addr = (int)(opTable.get(ptr) + 1);
                    res *= opTable.get(addr);
                    ptr++;
                    addr = (int)(opTable.get(ptr) + 1);
                    opTable.set(addr, res);
                } else if (opcode == 99) {
                    System.out.println(opTable.get(0));
                    System.exit(0);
                }
                
                ptr++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readInputFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("2.input"));
        String line = reader.readLine();
        reader.close();
        
        if (line != null) {
            String[] parts = line.split(",");
            len = parts.length;
            
            for (String part : parts) {
                opTable.add(Long.parseLong(part.trim()));
            }
        }
    }
}