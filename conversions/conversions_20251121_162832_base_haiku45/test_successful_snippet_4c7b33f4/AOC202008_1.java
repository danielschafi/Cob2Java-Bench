import java.io.*;
import java.util.*;

public class AOC202008_1 {
    static class Instruction {
        String instruction;
        char sign;
        int arg;
        int done;
        
        Instruction() {
            instruction = "";
            sign = ' ';
            arg = 0;
            done = 0;
        }
    }
    
    static Instruction[] wsCode = new Instruction[625];
    static int wsAcc = 0;
    static int i = 1;
    static int codePos = 1;
    
    public static void main(String[] args) {
        for (int j = 0; j < wsCode.length; j++) {
            wsCode[j] = new Instruction();
        }
        
        readInput();
        runCode();
        System.out.println(wsAcc);
    }
    
    static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader("d08.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void processRecord(String line) {
        String[] parts = line.split(" ");
        wsCode[i].instruction = parts[0];
        wsCode[i].sign = parts[1].charAt(0);
        wsCode[i].arg = Integer.parseInt(parts[1].substring(1));
        i++;
    }
    
    static void runCode() {
        while (wsCode[codePos].done == 0) {
            runInstruction();
        }
    }
    
    static void runInstruction() {
        wsCode[codePos].done = 1;
        String wsI = wsCode[codePos].instruction;
        int arg = wsCode[codePos].arg;
        
        if (wsI.equals("nop")) {
            codePos++;
        }
        
        if (wsI.equals("acc")) {
            if (wsCode[codePos].sign == '+') {
                wsAcc = wsAcc + arg;
            } else {
                wsAcc = wsAcc - arg;
            }
            codePos++;
        }
        
        if (wsI.equals("jmp")) {
            if (wsCode[codePos].sign == '+') {
                codePos = codePos + arg;
            } else {
                codePos = codePos - arg;
            }
        }
    }
}