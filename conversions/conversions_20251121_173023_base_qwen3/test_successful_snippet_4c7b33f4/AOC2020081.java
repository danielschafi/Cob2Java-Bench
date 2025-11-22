import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2020081 {
    static class Instruction {
        String operation;
        char sign;
        int argument;
        boolean done;
        
        public Instruction(String operation, char sign, int argument) {
            this.operation = operation;
            this.sign = sign;
            this.argument = argument;
            this.done = false;
        }
    }
    
    static List<Instruction> code = new ArrayList<>();
    static int accumulator = 0;
    static int codePos = 0;
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d08.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String operation = line.substring(0, 3);
                char sign = line.charAt(4);
                int argument = Integer.parseInt(line.substring(5));
                code.add(new Instruction(operation, sign, argument));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        runCode();
        System.out.println(accumulator);
    }
    
    static void runCode() {
        while (!code.get(codePos).done) {
            executeInstruction();
        }
    }
    
    static void executeInstruction() {
        Instruction current = code.get(codePos);
        current.done = true;
        String operation = current.operation;
        char sign = current.sign;
        int argument = current.argument;
        
        if (operation.equals("nop")) {
            codePos++;
        } else if (operation.equals("acc")) {
            if (sign == '+') {
                accumulator += argument;
            } else {
                accumulator -= argument;
            }
            codePos++;
        } else if (operation.equals("jmp")) {
            if (sign == '+') {
                codePos += argument;
            } else {
                codePos -= argument;
            }
        }
    }
}