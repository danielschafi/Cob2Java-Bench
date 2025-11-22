import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode2019_2b {
    public static void main(String[] args) {
        List<Long> inputTable = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("2.input"))) {
            String inputRec = br.readLine();
            if (inputRec != null) {
                String[] values = inputRec.split(",");
                for (String val : values) {
                    inputTable.add(Long.parseLong(val.trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        int len = inputTable.size();
        
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                List<Long> opTable = new ArrayList<>(inputTable);
                
                opTable.set(1, (long) noun);
                opTable.set(2, (long) verb);
                
                int ptr = 0;
                long res = 0;
                
                while (ptr < len) {
                    int opcode = opTable.get(ptr).intValue();
                    
                    if (opcode == 1) {
                        ptr++;
                        int addr1 = opTable.get(ptr).intValue();
                        res = opTable.get(addr1);
                        ptr++;
                        int addr2 = opTable.get(ptr).intValue();
                        res += opTable.get(addr2);
                        ptr++;
                        int addr3 = opTable.get(ptr).intValue();
                        opTable.set(addr3, res);
                    } else if (opcode == 2) {
                        ptr++;
                        int addr1 = opTable.get(ptr).intValue();
                        res = opTable.get(addr1);
                        ptr++;
                        int addr2 = opTable.get(ptr).intValue();
                        res *= opTable.get(addr2);
                        ptr++;
                        int addr3 = opTable.get(ptr).intValue();
                        opTable.set(addr3, res);
                    } else if (opcode == 99) {
                        res = opTable.get(0);
                        break;
                    }
                    ptr++;
                }
                
                if (res == 19690720) {
                    long result = 100 * noun + verb;
                    System.out.println(result);
                    return;
                }
            }
        }
    }
}