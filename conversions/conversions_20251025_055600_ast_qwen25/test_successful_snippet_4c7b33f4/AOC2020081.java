import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020081 {
    private static final int MAX_CODE_SIZE = 625;
    private static final String INPUT_FILE = "d08.input";

    private int fileStatus = 0;
    private String[] wsInstruction = new String[MAX_CODE_SIZE];
    private String[] wsSign = new String[MAX_CODE_SIZE];
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
                    wsInstruction[i - 1] = line.substring(0, 3).trim();
                    wsSign[i - 1] = line.substring(4, 5).trim();
                    wsArg[i - 1] = Integer.parseInt(line.substring(6, 9).trim());
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runCode() {
        while (wsDone[codePos - 1] == 0) {
            runInstruction();
        }
    }

    private void runInstruction() {
        wsDone[codePos - 1] = 1;
        wsI = wsInstruction[codePos - 1];
        arg = wsArg[codePos - 1];

        if (wsI.equals("nop")) {
            codePos++;
        } else if (wsI.equals("acc")) {
            if (wsSign[codePos - 1].equals("+")) {
                wsAcc += arg;
            } else {
                wsAcc -= arg;
            }
            codePos++;
        } else if (wsI.equals("jmp")) {
            if (wsSign[codePos - 1].equals("+")) {
                codePos += arg;
            } else {
                codePos -= arg;
            }
        }
    }
}