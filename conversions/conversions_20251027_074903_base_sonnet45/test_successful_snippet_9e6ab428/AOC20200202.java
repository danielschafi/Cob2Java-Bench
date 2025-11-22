import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC20200202 {
    private int correctRows = 0;

    public static void main(String[] args) {
        AOC20200202 program = new AOC20200202();
        program.run();
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d2.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
            System.out.println(correctRows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRecord(String inputRecord) {
        int conditionsMet = 0;
        
        String[] parts = inputRecord.split("[ :\\-]+");
        
        if (parts.length < 4) {
            return;
        }
        
        int wsPos1 = Integer.parseInt(parts[0]);
        int wsPos2 = Integer.parseInt(parts[1]);
        char wsChar = parts[2].charAt(0);
        String wsPassword = parts.length > 4 ? parts[4] : "";
        
        if (wsPassword.length() >= wsPos1 && wsPassword.charAt(wsPos1 - 1) == wsChar) {
            conditionsMet++;
        }
        
        if (wsPassword.length() >= wsPos2 && wsPassword.charAt(wsPos2 - 1) == wsChar) {
            conditionsMet++;
        }
        
        if (conditionsMet == 1) {
            correctRows++;
        }
    }
}