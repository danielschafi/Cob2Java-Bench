import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AngleDifference {
    private static final String INPUT_FILE = "Angle_diff.txt";
    private static boolean eofSwitch = false;
    private static int recCtr = 0;
    private static String wsAlphaBearing;
    private static String wsAbSign;
    private static String wsAbIntegerPart;
    private static String wsAbDecimalPart;
    private static double wsBearing1;
    private static double wsBearing2;
    private static double wsBearing;
    private static double wsResult;
    private static double wsResultPos;
    private static int wsIntegerPart;
    private static double wsDecimalPart;
    private static double wsResultOut;

    public static void main(String[] args) {
        initialize();
        while (!eofSwitch) {
            processRecord();
        }
        terminate();
    }

    private static void initialize() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            readRecord(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readRecord(BufferedReader br) throws IOException {
        String line = br.readLine();
        if (line == null) {
            eofSwitch = true;
        } else {
            recCtr++;
            String[] parts = line.split(" ");
            wsAlphaBearing = parts[0];
        }
    }

    private static void processRecord() {
        convertData();
        wsBearing1 = wsBearing;

        wsAlphaBearing = readNextBearing();
        convertData();
        wsBearing2 = wsBearing;

        wsResult = wsBearing2 - wsBearing1;
        wsResultPos = wsResult;
        wsIntegerPart = (int) wsResultPos;
        wsDecimalPart = wsResultPos - wsIntegerPart;
        wsIntegerPart = wsIntegerPart % 360;
        if (wsResult > 0) {
            wsResult = wsIntegerPart + wsDecimalPart;
        } else {
            wsResult = (wsIntegerPart + wsDecimalPart) * -1;
        }

        if (wsResult < -180) {
            wsResult += 360;
        }
        if (wsResult > 180) {
            wsResult -= 360;
        }
        wsResultOut = Math.round(wsResult * 10000.0) / 10000.0;

        System.out.printf("%03d %8.4f%n", recCtr, wsResultOut);

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            readRecord(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readNextBearing() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            for (int i = 0; i < recCtr; i++) {
                br.readLine();
            }
            String line = br.readLine();
            if (line == null) {
                eofSwitch = true;
                return "";
            }
            String[] parts = line.split(" ");
            return parts[1];
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void convertData() {
        wsAbIntegerPart = wsAlphaBearing.substring(0, 6);
        wsAbDecimalPart = wsAlphaBearing.substring(7);
        wsBearing = Double.parseDouble(wsAbIntegerPart + "." + wsAbDecimalPart);
        if (wsAlphaBearing.charAt(0) == '-') {
            wsBearing = -wsBearing;
        }
    }

    private static void terminate() {
        System.out.println("RECORDS PROCESSED: " + recCtr);
    }
}