import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2020081 {
    static class Instruction {
        String instruction;
        char sign;
        int arg;
        boolean done;
        
        public Instruction(String instruction, char sign, int arg) {
            this.instruction = instruction;
            this.sign = sign;
            this.arg = arg;
            this.done = false;
        }
    }
    
    public static void main(String[] args) {
        List<Instruction> code = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("d08.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String instruction = line.substring(0, 3);
                char sign = line.charAt(4);
                int arg = Integer.parseInt(line.substring(5));
                code.add(new Instruction(instruction, sign, arg));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int acc = 0;
        int codePos = 0;
        
        while (codePos < code.size() && !code.get(codePos).done) {
            Instruction current = code.get(codePos);
            current.done = true;
            
            if ("nop".equals(current.instruction)) {
                codePos++;
            } else if ("acc".equals(current.instruction)) {
                if (current.sign == '+') {
                    acc += current.arg;
                } else {
                    acc -= current.arg;
                }
                codePos++;
            } else if ("jmp".equals(current.instruction)) {
                if (current.sign == '+') {
                    codePos += current.arg;
                } else {
                    codePos -= current.arg;
                }
            }
        }
        
        System.out.println(acc);
    }
}