import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC2020041 {
    private static int correctPassports = 0;
    private static int foundFields = 0;
    private static List<String> rows = new ArrayList<>();
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("d4.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    nextPassport();
                } else {
                    processRow(line);
                }
            }
            nextPassport();
            System.out.println(correctPassports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void processRow(String record) {
        String[] fields = record.split("\\s+");
        for (String field : fields) {
            if (!field.startsWith("cid")) {
                foundFields++;
            }
        }
    }
    
    private static void nextPassport() {
        if (foundFields == 7) {
            correctPassports++;
        }
        foundFields = 0;
    }
}