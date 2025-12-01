import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class AngleDifference {
    private String eofSwitch = "N";
    private int recCtr = 0;
    private String wsAlphaBearing;
    private String wsAbSign;
    private String wsAbIntegerPart;
    private String wsAbDecPoint;
    private String wsAbDecimalPart;
    private BigDecimal wsBearing1;
    private BigDecimal wsBearing2;
    private BigDecimal wsBearing;
    private BigDecimal wsResult;
    private BigDecimal wsResultPos;
    private long wsIntegerPart;
    private BigDecimal wsDecimalPart;
    private BigDecimal wsResultOut;

    public static void main(String[] args) {
        AngleDifference program = new AngleDifference();
        program.run();
    }

    public void run() {
        initialize();
        while (!eofSwitch.equals("Y")) {
            processRecord();
        }
        terminate();
    }

    private void initialize() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Angle_diff.txt"))) {
            readRecord(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readRecord(BufferedReader reader) {
        try {
            String line = reader.readLine();
            if (line == null) {
                eofSwitch = "Y";
            } else {
                recCtr++;
            }
        } catch (IOException e) {
            eofSwitch = "Y";
        }
    }

    private void processRecord() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Angle_diff.txt"))) {
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                if (lineNum <= recCtr) continue;

                String[] parts = line.split(" +");
                if (parts.length < 2) {
                    eofSwitch = "Y";
                    break;
                }

                String alphaBearing1 = parts[0];
                String alphaBearing2 = parts[1];

                wsAlphaBearing = alphaBearing1;
                convertData();
                wsBearing1 = wsBearing;

                wsAlphaBearing = alphaBearing2;
                convertData();
                wsBearing2 = wsBearing;

                wsResult = wsBearing2.subtract(wsBearing1);
                wsResultPos = wsResult.abs();
                wsIntegerPart = wsResultPos.longValue();
                wsDecimalPart = wsResultPos.subtract(new BigDecimal(wsIntegerPart));

                wsIntegerPart = wsIntegerPart % 360;

                if (wsResult.compareTo(BigDecimal.ZERO) > 0) {
                    wsResult = new BigDecimal(wsIntegerPart).add(wsDecimalPart);
                } else {
                    wsResult = new BigDecimal(wsIntegerPart).add(wsDecimalPart).multiply(new BigDecimal(-1));
                }

                if (wsResult.compareTo(new BigDecimal(-180)) < 0) {
                    wsResult = wsResult.add(new BigDecimal(360));
                }
                if (wsResult.compareTo(new BigDecimal(180)) > 0) {
                    wsResult = wsResult.subtract(new BigDecimal(360));
                }

                wsResultOut = wsResult.setScale(4, RoundingMode.HALF_UP);

                System.out.println(String.format("%03d %s", recCtr, formatOutput(wsResultOut)));

                recCtr++;
                break;
            }
            if (lineNum <= recCtr) {
                eofSwitch = "Y";
            }
        } catch (IOException e) {
            eofSwitch = "Y";
        }
    }

    private void convertData() {
        if (wsAlphaBearing == null || wsAlphaBearing.length() < 20) {
            wsBearing = BigDecimal.ZERO;
            return;
        }

        wsAbSign = wsAlphaBearing.substring(0, 1);
        wsAbIntegerPart = wsAlphaBearing.substring(1, 7);
        wsAbDecPoint = wsAlphaBearing.substring(7, 8);
        wsAbDecimalPart = wsAlphaBearing.substring(8, 20);

        String intPart = wsAbIntegerPart.trim();
        String decPart = wsAbDecimalPart.trim();

        if (intPart.isEmpty()) intPart = "0";
        if (decPart.isEmpty()) decPart = "0";

        wsBearing = new BigDecimal(intPart + "." + decPart);

        if (wsAbSign.equals("-")) {
            wsBearing = wsBearing.negate();
        }
    }

    private String formatOutput(BigDecimal value) {
        String formatted = String.format("%8.4f", value);
        return formatted;
    }

    private void terminate() {
        System.out.println("RECORDS PROCESSED: " + recCtr);
    }
}