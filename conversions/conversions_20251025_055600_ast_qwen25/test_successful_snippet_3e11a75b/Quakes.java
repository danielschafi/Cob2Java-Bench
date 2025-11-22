import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Quakes {
    private static final String DEFAULT_FILENAME = "data.txt";
    private static String commandFilename;
    private static String quakeFdStatus;
    private static boolean ioError;
    private static String dateTime;
    private static String quake;
    private static double magnitude;

    public static void main(String[] args) {
        showBigOnes(args);
    }

    private static void showBigOnes(String[] args) {
        if (args.length > 0) {
            commandFilename = args[0].trim();
        } else {
            commandFilename = DEFAULT_FILENAME;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(commandFilename))) {
            String dataLine;
            while ((dataLine = reader.readLine()) != null) {
                String[] parts = dataLine.trim().split("\\s+");
                if (parts.length >= 3) {
                    dateTime = parts[0];
                    quake = parts[1];
                    magnitude = Double.parseDouble(parts[2]);

                    if (magnitude > 6) {
                        System.out.println(dateTime + " " + quake + " " + magnitude);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(commandFilename + " not found");
            System.exit(1);
        }
    }
}