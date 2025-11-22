import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;

public class SQRT {
    public static void main(String[] args) {
        String inputFilePath = "sqrtFIXED.dat";
        String outputFilePath = "output.txt";
        DecimalFormat df = new DecimalFormat("00000000000.000000");
        DecimalFormat df2 = new DecimalFormat("00000000000.000000;-00000000000.000000");

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFilePath));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {

            writer.write(String.format("%26s%n", "SQUARE ROOT APPROXIMATIONS"));
            writer.write(String.format("%44s%n", "--------------------------------------------"));
            writer.write(String.format("%8s%6s%15s%11s%n", " ", "NUMBER", " ", "SQUARE ROOT"));
            writer.write(String.format("%20s%5s%19s%n", " -------------------", " ", "------------------"));

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
                    continue;
                }

                if (inZ <= 0) {
                    writer.write(String.format(" %s        INVALID INPUT%n", df2.format(inZ)));
                    continue;
                }

                double diff = inDiff;
                double z = inZ;
                double x = z / 2;
                double y;
                double temp;

                for (int k = 1; k <= 1000; k++) {
                    y = 0.5 * (x + z / x);
                    temp = y - x;
                    if (temp < 0) temp = -temp;
                    if (temp / (y + x) <= diff) {
                        writer.write(String.format(" %s %s%n", df.format(inZ), df.format(y)));
                        break;
                    }
                    if (k == 1000) {
                        writer.write(String.format(" %s  ATTEMPT ABORTED,TOO MANY ITERATIONS%n", df.format(inZ)));
                    }
                    x = y;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}