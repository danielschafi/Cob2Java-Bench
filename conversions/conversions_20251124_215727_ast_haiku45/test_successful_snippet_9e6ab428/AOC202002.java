import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202002 {
    private int fileStatus = 0;
    private int recLen = 0;
    private int wsPos1 = 0;
    private int wsPos2 = 0;
    private char wsChar = ' ';
    private String wsStringEmpty = "";
    private String wsPassword = "";
    private int conditionsMet = 0;
    private int correctRows = 0;

    public static void main(String[] args) {
        AOC202002 program = new AOC202002();
        program.run();
    }

    private void run() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("d2.input"));
            for (String line : lines) {
                processRecord(line);
            }
            System.out.println(correctRows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRecord(String inputRecord) {
        conditionsMet = 0;
        
        String[] parts = inputRecord.split("[ \\-:]");
        List<String> tokens = new ArrayList<>();
        for (String part : parts) {
            if (!part.isEmpty()) {
                tokens.add(part);
            }
        }
        
        if (tokens.size() >= 5) {
            wsPos1 = Integer.parseInt(tokens.get(0));
            wsPos2 = Integer.parseInt(tokens.get(1));
            wsChar = tokens.get(2).charAt(0);
            wsStringEmpty = tokens.get(3);
            wsPassword = tokens.get(4);
            
            if (wsPos1 > 0 && wsPos1 <= wsPassword.length()) {
                if (wsPassword.charAt(wsPos1 - 1) == wsChar) {
                    conditionsMet++;
                }
            }
            
            if (wsPos2 > 0 && wsPos2 <= wsPassword.length()) {
                if (wsPassword.charAt(wsPos2 - 1) == wsChar) {
                    conditionsMet++;
                }
            }
            
            if (conditionsMet == 1) {
                correctRows++;
            }
        }
    }
}