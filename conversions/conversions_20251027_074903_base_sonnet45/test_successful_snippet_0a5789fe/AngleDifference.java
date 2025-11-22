import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class AngleDifference {
    private static int recCtr = 0;

    public static void main(String[] args) {
        initialize();
    }

    private static void initialize() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Angle_diff.txt"))) {
            String line;
            while ((line = readRecord(reader)) != null) {
                processRecord(line);
            }
            terminate();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static String readRecord(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line != null) {
            recCtr++;
        }
        return line;
    }

    private static void processRecord(String inRecord) {
        String alphaBearing1 = inRecord.substring(0, 20);
        String alphaBearing2 = inRecord.substring(21, 41);

        BigDecimal wsBearing1 = convertData(alphaBearing1);
        BigDecimal wsBearing2 = convertData(alphaBearing2);

        BigDecimal wsResult = wsBearing2.subtract(wsBearing1);
        BigDecimal wsResultPos = wsResult.abs();
        BigDecimal wsIntegerPart = new BigDecimal(wsResultPos.toBigInteger());
        BigDecimal wsDecimalPart = wsResultPos.subtract(wsIntegerPart);
        
        long intPart = wsIntegerPart.longValue();
        intPart = intPart % 360;
        wsIntegerPart = new BigDecimal(intPart);

        if (wsResult.compareTo(BigDecimal.ZERO) > 0) {
            wsResult = wsIntegerPart.add(wsDecimalPart);
        } else {
            wsResult = wsIntegerPart.add(wsDecimalPart).negate();
        }

        BigDecimal minus180 = new BigDecimal("-180");
        BigDecimal plus180 = new BigDecimal("180");
        BigDecimal threeSixty = new BigDecimal("360");

        if (wsResult.compareTo(minus180) < 0) {
            wsResult = wsResult.add(threeSixty);
        }
        if (wsResult.compareTo(plus180) > 0) {
            wsResult = wsResult.subtract(threeSixty);
        }

        BigDecimal wsResultOut = wsResult.setScale(4, RoundingMode.HALF_UP);

        System.out.printf("%03d %11.4f%n", recCtr, wsResultOut);
    }

    private static BigDecimal convertData(String wsAlphaBearing) {
        wsAlphaBearing = wsAlphaBearing.trim();
        
        char wsAbSign = wsAlphaBearing.charAt(0);
        String wsAbIntegerPart = wsAlphaBearing.substring(1, 7);
        String wsAbDecimalPart = wsAlphaBearing.substring(8, 20);

        String bearingStr = wsAbIntegerPart + "." + wsAbDecimalPart;
        BigDecimal wsBearing = new BigDecimal(bearingStr);

        if (wsAbSign == '-') {
            wsBearing = wsBearing.negate();
        }

        return wsBearing;
    }

    private static void terminate() {
        System.out.println("RECORDS PROCESSED: " + recCtr);
    }
}