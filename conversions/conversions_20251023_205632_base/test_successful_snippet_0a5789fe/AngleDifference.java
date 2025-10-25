import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AngleDifference {
    public static void main(String[] args) {
        String fileName = "Angle_diff.txt";
        String line;
        int recCtr = 0;
        boolean eofSwitch = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (!eofSwitch) {
                line = br.readLine();
                if (line == null) {
                    eofSwitch = true;
                } else {
                    recCtr++;
                    String[] parts = line.split(" ");
                    String alphaBearing1 = parts[0];
                    String alphaBearing2 = parts[1];

                    double bearing1 = convertData(alphaBearing1);
                    double bearing2 = convertData(alphaBearing2);

                    double result = bearing2 - bearing1;
                    double resultPos = result;
                    int integerPart = (int) resultPos;
                    double decimalPart = resultPos - integerPart;
                    integerPart = Math.floorMod(integerPart, 360);
                    if (result > 0) {
                        result = integerPart + decimalPart;
                    } else {
                        result = (integerPart + decimalPart) * -1;
                    }

                    if (result < -180) {
                        result = result + 360;
                    }
                    if (result > 180) {
                        result = result - 360;
                    }
                    double resultOut = Math.round(result * 10000.0) / 10000.0;

                    System.out.printf("%03d %8.4f%n", recCtr, resultOut);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("RECORDS PROCESSED: " + recCtr);
    }

    private static double convertData(String alphaBearing) {
        char sign = alphaBearing.charAt(0);
        String integerPart = alphaBearing.substring(1, 7);
        String decimalPart = alphaBearing.substring(8);

        double bearing = Double.parseDouble(integerPart + "." + decimalPart);
        if (sign == '-') {
            bearing = -bearing;
        }
        return bearing;
    }
}