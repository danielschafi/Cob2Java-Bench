import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Quakes {
    public static void main(String[] args) {
        String commandFilename = args.length > 0 ? args[0] : "data.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(commandFilename));
            String dataLine;
            while ((dataLine = reader.readLine()) != null) {
                String[] parts = dataLine.trim().split("\\s+");
                if (parts.length >= 3) {
                    String dateTime = parts[0];
                    String quake = parts[1];
                    double magnitude = Double.parseDouble(parts[2]);
                    if (magnitude > 6) {
                        System.out.println(dateTime + " " + quake + " " + magnitude);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(commandFilename + " not found");
            System.exit(1);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("io error: " + e.getMessage());
                    System.exit(1);
                }
            }
        }
    }
}