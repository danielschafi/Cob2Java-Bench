import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202008_1 {
    static class Instruction {
        String instruction;
        char sign;
        int arg;
        int done = 0;
    }

    static int fileStatus = 0;
    static Instruction[] wsCode = new Instruction[625];
    static int i = 1;
    static int arg = 0;
    static int codePos = 1;
    static long wsAcc = 0;

    public static void main(String[] args) {
        for (int j = 0; j < wsCode.length; j++) {
            wsCode[j] = new Instruction();
        }

        readFile();
        runCode();
        System.out.println(wsAcc);
    }

    static void readFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("d08.input"));
            for (String line : lines) {
                if (line.length() >= 5) {
                    wsCode[i].instruction = line.substring(0, 3);
                    wsCode[i].sign = line.charAt(4);
                    wsCode[i].arg = Integer.parseInt(line.substring(5));
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void runCode() {
        while (wsCode[codePos].done != 1) {
            runInstruction();
        }
    }

    static void runInstruction() {
        wsCode[codePos].done = 1;
        String wsI = wsCode[codePos].instruction;
        arg = wsCode[codePos].arg;

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