import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;

public class SQRT {
    public static void main(String[] args) {
        String inputFilePath = "sqrtFIXED.dat";
        String outputFilePath = "sqrtFIXED.out";

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFilePath));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {

            DecimalFormat df = new DecimalFormat("00000000000.000000");
            DecimalFormat df2 = new DecimalFormat("00000000000.000000");

            writer.write("         SQUARE ROOT APPROXIMATIONS\n");
            writer.write("--------------------------------------------\n");
            writer.write("      NUMBER         SQUARE ROOT\n");
            writer.write(" ------------------- ------------------\n");

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() < 16) continue;

                String inZStr = line.substring(0, 16).trim();
                String inDiffStr = line.substring(16, 21).trim();

                double inZ;
                double inDiff;

                try {
                    inZ = Double.parseDouble(inZStr);
                    inDiff = Double.parseDouble(inDiffStr);
                } catch (NumberFormatException e) {
                    writer.write(String.format(" %-11s        INVALID INPUT\n", inZStr));
                    continue;
                }

                if (inZ <= 0) {
                    writer.write(String.format(" %-11s        INVALID INPUT\n", df.format(inZ)));
                    continue;
                }

                double z = inZ;
                double x = z / 2;
                double y;
                double temp;

                for (int k = 1; k <= 1000; k++) {
                    y = 0.5 * (x + z / x);
                    temp = y - x;
                    if (temp < 0) temp = -temp;
                    if (temp / (y + x) > inDiff) {
                        x = y;
                        continue;
                    }
                    writer.write(String.format(" %-11s %-11s\n", df.format(inZ), df2.format(y)));
                    break;
                }

                if (x != y) {
                    writer.write(String.format(" %-11s  ATTEMPT ABORTED,TOO MANY ITERATIONS\n", df.format(inZ)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}