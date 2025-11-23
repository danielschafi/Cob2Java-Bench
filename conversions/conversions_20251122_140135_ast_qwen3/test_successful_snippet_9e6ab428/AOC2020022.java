import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020022 {
    private static int correctRows = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d2.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(correctRows);
    }

    private static void processRecord(String inputRecord) {
        int conditionsMet = 0;
        
        String[] parts = inputRecord.split("[\\s:-]+");
        int pos1 = Integer.parseInt(parts[0]);
        int pos2 = Integer.parseInt(parts[1]);
        char character = parts[2].charAt(0);
        String password = parts[4];
        
        if (password.charAt(pos1 - 1) == character) {
            conditionsMet++;
        }
        if (password.charAt(pos2 - 1) == character) {
            conditionsMet++;
        }
        if (conditionsMet == 1) {
            correctRows++;
        }
    }
}