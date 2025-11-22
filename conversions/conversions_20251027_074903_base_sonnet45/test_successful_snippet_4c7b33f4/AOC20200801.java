import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC20200801 {
    
    static class Instruction {
        String instruction;
        String sign;
        int arg;
        int done;
        
        Instruction() {
            this.instruction = "";
            this.sign = "";
            this.arg = 0;
            this.done = 0;
        }
    }
    
    static Instruction[] wsCode = new Instruction[625];
    static int wsAcc = 0;
    static int codePos = 1;
    
    public static void main(String[] args) {
        for (int i = 0; i < 625; i++) {
            wsCode[i] = new Instruction();
        }
        
        readInput();
        runCode();
        System.out.println(wsAcc);
    }
    
    static void readInput() {
        int i = 1;
        try (BufferedReader br = new BufferedReader(new FileReader("d08.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line, i);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void processRecord(String line, int index) {
        String[] parts = line.split(" ");
        wsCode[index].instruction = parts[0];
        wsCode[index].sign = parts[1].substring(0, 1);
        wsCode[index].arg = Integer.parseInt(parts[1].substring(1));
    }
    
    static void runCode() {
        while (wsCode[codePos].done != 1) {
            runInstruction();
        }
    }
    
    static void runInstruction() {
        wsCode[codePos].done = 1;
        String instruction = wsCode[codePos].instruction;
        int arg = wsCode[codePos].arg;
        String sign = wsCode[codePos].sign;
        
        if (instruction.equals("nop")) {
            codePos++;
        } else if (instruction.equals("acc")) {
            if (sign.equals("+")) {
                wsAcc = wsAcc + arg;
            } else {
                wsAcc = wsAcc - arg;
            }
            codePos++;
        } else if (instruction.equals("jmp")) {
            if (sign.equals("+")) {
                codePos = codePos + arg;
            } else {
                codePos = codePos - arg;
            }
        }
    }
}