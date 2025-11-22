import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SalesSum {
    private static final int MAX_SALES_ID_ENTRIES = 99;
    private static final int MAX_STATE_ENTRIES = 50;

    private String[] tableSalesId = new String[MAX_SALES_ID_ENTRIES];
    private double[] tableSalesIdAmount = new double[MAX_SALES_ID_ENTRIES];
    private String[] tableState = new String[MAX_STATE_ENTRIES];
    private double[] tableStateAmount = new double[MAX_STATE_ENTRIES];
    private double wsSalesTotal = 0.0;
    private double wsStateTotal = 0.0;
    private int wsIdSub = 0;
    private int wsStateSub = 0;
    private boolean eof = false;

    public static void main(String[] args) {
        SalesSum salesSum = new SalesSum();
        salesSum.run();
    }

    public void run() {
        System.out.println("SALESSUM PROGRAM START");
        try (BufferedReader salesFileIn = Files.newBufferedReader(Paths.get("SALESIDAMT.DAT"));
             BufferedWriter salesFileOut = Files.newBufferedWriter(Paths.get("SALESIDOUT.DAT"));
             BufferedWriter stateFileOut = Files.newBufferedWriter(Paths.get("STATESOUT.DAT"))) {

            initializeTable();
            String line;
            while ((line = salesFileIn.readLine()) != null && !eof) {
                populateTables(line);
            }
            writeSummaryFiles(salesFileOut, stateFileOut);
            writeTrailers(salesFileOut, stateFileOut);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeTable() {
        for (wsIdSub = 0; wsIdSub < MAX_SALES_ID_ENTRIES; wsIdSub++) {
            tableSalesId[wsIdSub] = "   ";
            tableSalesIdAmount[wsIdSub] = 0.0;
        }
        for (wsStateSub = 0; wsStateSub < MAX_STATE_ENTRIES; wsStateSub++) {
            tableState[wsStateSub] = "  ";
            tableStateAmount[wsStateSub] = 0.0;
        }
    }

    private void populateTables(String line) {
        String salesStateIn = line.substring(0, 2).trim();
        String salesIdIn = line.substring(2, 5).trim();
        double salesAmountIn = Double.parseDouble(line.substring(5, 11)) + Double.parseDouble(line.substring(11, 13)) / 100;

        for (wsIdSub = 0; wsIdSub < MAX_SALES_ID_ENTRIES; wsIdSub++) {
            if (tableSalesId[wsIdSub] == null || tableSalesId[wsIdSub].trim().isEmpty() || tableSalesId[wsIdSub].equals(salesIdIn)) {
                break;
            }
        }

        if (wsIdSub < MAX_SALES_ID_ENTRIES) {
            if (tableSalesId[wsIdSub] == null || tableSalesId[wsIdSub].trim().isEmpty() || tableSalesId[wsIdSub].equals(salesIdIn)) {
                tableSalesIdAmount[wsIdSub] += salesAmountIn;
                if (tableSalesId[wsIdSub] == null || tableSalesId[wsIdSub].trim().isEmpty()) {
                    tableSalesId[wsIdSub] = salesIdIn;
                }
            }
        }

        for (wsStateSub = 0; wsStateSub < MAX_STATE_ENTRIES; wsStateSub++) {
            if (tableState[wsStateSub] == null || tableState[wsStateSub].trim().isEmpty() || tableState[wsStateSub].equals(salesStateIn)) {
                break;
            }
        }

        if (wsStateSub < MAX_STATE_ENTRIES) {
            if (tableState[wsStateSub] == null || tableState[wsStateSub].trim().isEmpty() || tableState[wsStateSub].equals(salesStateIn)) {
                tableStateAmount[wsStateSub] += salesAmountIn;
                if (tableState[wsStateSub] == null || tableState[wsStateSub].trim().isEmpty()) {
                    tableState[wsStateSub] = salesStateIn;
                }
            }
        }
    }

    private void writeSummaryFiles(BufferedWriter salesFileOut, BufferedWriter stateFileOut) throws IOException {
        for (wsIdSub = 0; wsIdSub < MAX_SALES_ID_ENTRIES; wsIdSub++) {
            if (tableSalesId[wsIdSub] == null || tableSalesId[wsIdSub].trim().isEmpty()) {
                break;
            }
            salesFileOut.write(String.format("%-3s%08.2f%n", tableSalesId[wsIdSub], tableSalesIdAmount[wsIdSub]));
            wsSalesTotal += tableSalesIdAmount[wsIdSub];
        }

        for (wsStateSub = 0; wsStateSub < MAX_STATE_ENTRIES; wsStateSub++) {
            if (tableState[wsStateSub] == null || tableState[wsStateSub].trim().isEmpty()) {
                break;
            }
            stateFileOut.write(String.format("%-2s%08.2f%n", tableState[wsStateSub], tableStateAmount[wsStateSub]));
            wsStateTotal += tableStateAmount[wsStateSub];
        }
    }

    private void writeTrailers(BufferedWriter salesFileOut, BufferedWriter stateFileOut) throws IOException {
        // Add trailer record writing logic here if needed
    }
}