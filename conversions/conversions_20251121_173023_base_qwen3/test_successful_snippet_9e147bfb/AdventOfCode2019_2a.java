import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode2019_2a {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("2.input"));
            String inputLine = reader.readLine();
            reader.close();

            String[] parts = inputLine.split(",");
            List<Long> opTable = new ArrayList<>();
            for (String part : parts) {
                opTable.add(Long.parseLong(part.trim()));
            }

            opTable.set(1, 12L);
            opTable.set(2, 2L);

            int ptr = 0;
            while (ptr < opTable.size()) {
                long opcode = opTable.get(ptr);
                if (opcode == 1) {
                    long addr1 = opTable.get(ptr + 1);
                    long val1 = opTable.get((int) addr1);
                    long addr2 = opTable.get(ptr + 2);
                    long val2 = opTable.get((int) addr2);
                    long addr3 = opTable.get(ptr + 3);
                    opTable.set((int) addr3, val1 + val2);
                    ptr += 4;
                } else if (opcode == 2) {
                    long addr1 = opTable.get(ptr + 1);
                    long val1 = opTable.get((int) addr1);
                    long addr2 = opTable.get(ptr + 2);
                    long val2 = opTable.get((int) addr2);
                    long addr3 = opTable.get(ptr + 3);
                    opTable.set((int) addr3, val1 * val2);
                    ptr += 4;
                } else if (opcode == 99) {
                    System.out.println(opTable.get(0));
                    break;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}