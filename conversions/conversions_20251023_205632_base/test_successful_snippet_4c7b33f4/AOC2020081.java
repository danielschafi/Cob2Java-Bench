import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020081 {
    private static final int MAX_CODE_SIZE = 625;
    private static final String INPUT_FILE = "d08.input";

    private int fileStatus = 0;
    private String[] wsInstruction = new String[MAX_CODE_SIZE];
    private char[] wsSign = new char[MAX_CODE_SIZE];
    private int[] wsArg = new int[MAX_CODE_SIZE];
    private int[] wsDone = new int[MAX_CODE_SIZE];
    private String wsI;
    private int wsAcc = 0;

    private int i = 1;
    private int arg = 0;
    private int codePos = 1;

    public static void main(String[] args) {
        AOC2020081 program = new AOC2020081();
        program.run();
    }

    private void run() {
        readFile();
        runCode();
        System.out.println(wsAcc);
    }

    private void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 8) {
                    wsInstruction[i] = line.substring(0, 3);
                    wsSign[i] = line.charAt(4);
                    wsArg[i] = Integer.parseInt(line.substring(5, 8));
                    i++;
                }
            }
        } catch (IOException e) {
            fileStatus = 1;
        }
    }

    private void runCode() {
        while (wsDone[codePos] == 0) {
            runInstruction();
        }
    }

    private void runInstruction() {
        wsDone[codePos] = 1;
        wsI = wsInstruction[codePos];
        arg = wsArg[codePos];

        if (wsI.equals("nop")) {
            codePos++;
        } else if (wsI.equals("acc")) {
            if (wsSign[codePos] == '+') {
                wsAcc += arg;
            } else {
                wsAcc -= arg;
            }
            codePos++;
        } else if (wsI.equals("jmp")) {
            if (wsSign[codePos] == '+') {
                codePos += arg;
            } else {
                codePos -= arg;
            }
        }
    }
}