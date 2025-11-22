import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode2019_2b {
    public static void main(String[] args) {
        List<Long> inputTable = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("2.input"))) {
            String inputRec = reader.readLine();
            
            String[] parts = inputRec.split(",");
            for (String part : parts) {
                inputTable.add(Long.parseLong(part.trim()));
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
                boolean found = false;
                
                while (ptr < len) {
                    long opcode = opTable.get(ptr);
                    
                    if (opcode == 1) {
                        ptr++;
                        int addr1 = (int) (long) opTable.get(ptr);
                        ptr++;
                        int addr2 = (int) (long) opTable.get(ptr);
                        ptr++;
                        int addr3 = (int) (long) opTable.get(ptr);
                        
                        long res = opTable.get(addr1) + opTable.get(addr2);
                        opTable.set(addr3, res);
                    } else if (opcode == 2) {
                        ptr++;
                        int addr1 = (int) (long) opTable.get(ptr);
                        ptr++;
                        int addr2 = (int) (long) opTable.get(ptr);
                        ptr++;
                        int addr3 = (int) (long) opTable.get(ptr);
                        
                        long res = opTable.get(addr1) * opTable.get(addr2);
                        opTable.set(addr3, res);
                    } else if (opcode == 99) {
                        break;
                    }
                    
                    ptr++;
                }
                
                if (opTable.get(0) == 19690720L) {
                    long result = 100L * noun + verb;
                    System.out.println(result);
                    return;
                }
            }
        }
    }
}