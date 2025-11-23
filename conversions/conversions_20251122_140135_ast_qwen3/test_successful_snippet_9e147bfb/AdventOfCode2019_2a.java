import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode2019_2a {
    private static final String INPUT_FILE = "2.input";
    
    public static void main(String[] args) {
        List<Integer> opTable = new ArrayList<>();
        int sepNumber = 0;
        int ptr = 1;
        int len = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String inputRec = reader.readLine();
            
            // Count separators
            sepNumber = (int) inputRec.chars().filter(ch -> ch == ',').count();
            if (sepNumber < 9999) {
                sepNumber++;
            } else {
                sepNumber = 9999;
            }
            
            // Parse numbers
            String[] parts = inputRec.split(",");
            for (String part : parts) {
                opTable.add(Integer.parseInt(part.trim()));
            }
            len = opTable.size();
            
            // Set initial values
            opTable.set(1, 12);
            opTable.set(2, 2);
            
            // Execute program
            ptr = 0;
            while (ptr < len) {
                int opcode = opTable.get(ptr);
                
                switch (opcode) {
                    case 1: // Addition
                        int addr1 = opTable.get(ptr + 1);
                        int addr2 = opTable.get(ptr + 2);
                        int resultAddr = opTable.get(ptr + 3);
                        
                        int value1 = opTable.get(addr1);
                        int value2 = opTable.get(addr2);
                        int sum = value1 + value2;
                        
                        opTable.set(resultAddr, sum);
                        ptr += 4;
                        break;
                        
                    case 2: // Multiplication
                        addr1 = opTable.get(ptr + 1);
                        addr2 = opTable.get(ptr + 2);
                        resultAddr = opTable.get(ptr + 3);
                        
                        value1 = opTable.get(addr1);
                        value2 = opTable.get(addr2);
                        int product = value1 * value2;
                        
                        opTable.set(resultAddr, product);
                        ptr += 4;
                        break;
                        
                    case 99: // Halt
                        System.out.println(opTable.get(0));
                        return;
                        
                    default:
                        throw new RuntimeException("Unknown opcode: " + opcode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}