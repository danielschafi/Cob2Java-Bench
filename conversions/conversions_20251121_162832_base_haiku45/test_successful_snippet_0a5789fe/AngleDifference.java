import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class AngleDifference {
    private static final String INPUT_FILE = "Angle_diff.txt";
    private static int recCtr = 0;

    public static void main(String[] args) {
        initialize();
        processRecords();
        terminate();
    }

    private static void initialize() {
        recCtr = 0;
    }

    private static void processRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recCtr++;
                processRecord(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void processRecord(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length < 2) {
            return;
        }

        String alphaBearing1 = parts[0];
        String alphaBearing2 = parts[1];

        BigDecimal bearing1 = convertData(alphaBearing1);
        BigDecimal bearing2 = convertData(alphaBearing2);

        BigDecimal result = bearing2.subtract(bearing1);

        BigDecimal integerPart = new BigDecimal(result.toBigInteger());
        BigDecimal decimalPart = result.subtract(integerPart);

        long intPart = integerPart.longValue();
        intPart = intPart % 360;
        if (intPart < 0) {
            intPart += 360;
        }

        if (result.compareTo(BigDecimal.ZERO) > 0) {
            result = new BigDecimal(intPart).add(decimalPart);
        } else {
            result = new BigDecimal(intPart).add(decimalPart).multiply(new BigDecimal(-1));
        }

        if (result.compareTo(new BigDecimal(-180)) < 0) {
            result = result.add(new BigDecimal(360));
        }
        if (result.compareTo(new BigDecimal(180)) > 0) {
            result = result.subtract(new BigDecimal(360));
        }

        result = result.setScale(4, RoundingMode.HALF_UP);

        System.out.printf("%03d %10.4f%n", recCtr, result);
    }

    private static BigDecimal convertData(String alphaBearing) {
        alphaBearing = alphaBearing.trim();
        boolean isNegative = alphaBearing.startsWith("-");

        String numericPart = alphaBearing.substring(1);
        BigDecimal bearing = new BigDecimal(numericPart);

        if (isNegative) {
            bearing = bearing.negate();
        }

        return bearing;
    }

    private static void terminate() {
        System.out.println("RECORDS PROCESSED: " + recCtr);
    }
}