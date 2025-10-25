import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;

public class SalesSum {
    private static final int MAX_SALES_ID_ENTRIES = 99;
    private static final int MAX_STATE_ENTRIES = 50;
    private static final String SALES_FILE_IN = "SALESIDAMT.DAT";
    private static final String SALES_FILE_OUT = "SALESIDOUT.DAT";
    private static final String STATE_FILE_OUT = "STATESOUT.DAT";

    private String[] tableSalesId = new String[MAX_SALES_ID_ENTRIES];
    private double[] tableSalesIdAmount = new double[MAX_SALES_ID_ENTRIES];
    private String[] tableState = new String[MAX_STATE_ENTRIES];
    private double[] tableStateAmount = new double[MAX_STATE_ENTRIES];
    private int wsSalesTotal = 0;
    private int wsStateTotal = 0;
    private int wsIdSub = 0;
    private int wsStateSub = 0;
    private boolean eof = false;

    public static void main(String[] args) {
        SalesSum salesSum = new SalesSum();
        salesSum.salesSumProgram();
    }

    private void salesSumProgram() {
        System.out.println("SALESSUM PROGRAM START");
        try (BufferedReader salesFileIn = Files.newBufferedReader(Paths.get(SALES_FILE_IN));
             BufferedWriter salesFileOut = Files.newBufferedWriter(Paths.get(SALES_FILE_OUT));
             BufferedWriter stateFileOut = Files.newBufferedWriter(Paths.get(STATE_FILE_OUT))) {

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
        for (wsIdSub = 0; wsIdSub < MAX_SALES_ID_ENTRIES - 1; wsIdSub++) {
            tableSalesId[wsIdSub] = "   ";
            tableSalesIdAmount[wsIdSub] = 0.0;
        }
        for (wsStateSub = 0; wsStateSub < MAX_STATE_ENTRIES - 1; wsStateSub++) {
            tableState[wsStateSub] = "  ";
            tableStateAmount[wsStateSub] = 0.0;
        }
    }

    private void populateTables(String line) {
        String salesStateIn = line.substring(0, 2).trim();
        String salesIdIn = line.substring(2, 5).trim();
        double salesAmountIn = Double.parseDouble(line.substring(5).trim());

        for (wsIdSub = 0; wsIdSub < MAX_SALES_ID_ENTRIES - 1; wsIdSub++) {
            if (tableSalesId[wsIdSub] == null || tableSalesId[wsIdSub].trim().isEmpty() || tableSalesId[wsIdSub].equals(salesIdIn)) {
                break;
            }
        }

        if (tableSalesId[wsIdSub] != null && tableSalesId[wsIdSub].equals(salesIdIn)) {
            tableSalesIdAmount[wsIdSub] += salesAmountIn;
        } else {
            tableSalesIdAmount[wsIdSub] += salesAmountIn;
            tableSalesId[wsIdSub] = salesIdIn;
        }

        for (wsStateSub = 0; wsStateSub < MAX_STATE_ENTRIES - 1; wsStateSub++) {
            if (tableState[wsStateSub] == null || tableState[wsStateSub].trim().isEmpty() || tableState[wsStateSub].equals(salesStateIn)) {
                break;
            }
        }

        if (tableState[wsStateSub] != null && tableState[wsStateSub].equals(salesStateIn)) {
            tableStateAmount[wsStateSub] += salesAmountIn;
        } else {
            tableStateAmount[wsStateSub] += salesAmountIn;
            tableState[wsStateSub] = salesStateIn;
        }
    }

    private void writeSummaryFiles(BufferedWriter salesFileOut, BufferedWriter stateFileOut) throws IOException {
        DecimalFormat df = new DecimalFormat("000000.00");
        for (wsIdSub = 0; wsIdSub < MAX_SALES_ID_ENTRIES - 1; wsIdSub++) {
            if (tableSalesId[wsIdSub] == null || tableSalesId[wsIdSub].trim().isEmpty()) {
                break;
            }
            salesFileOut.write(String.format("%-3s%-8s", tableSalesId[wsIdSub], df.format(tableSalesIdAmount[wsIdSub])));
            salesFileOut.newLine();
            wsSalesTotal += tableSalesIdAmount[wsIdSub];
        }

        for (wsStateSub = 0; wsStateSub < MAX_STATE_ENTRIES - 1; wsStateSub++) {
            if (tableState[wsStateSub] == null || tableState[wsStateSub].trim().isEmpty()) {
                break;
            }
            stateFileOut.write(String.format("%-2s%-8s", tableState[wsStateSub], df.format(tableStateAmount[wsStateSub])));
            stateFileOut.newLine();
            wsStateTotal += tableStateAmount[wsStateSub];
        }
    }

    private void writeTrailers(BufferedWriter salesFileOut, BufferedWriter stateFileOut) throws IOException {
        DecimalFormat df = new DecimalFormat("000000.00");
        salesFileOut.write(String.format("TOTAL %-8s", df.format(wsSalesTotal)));
        salesFileOut.newLine();
        stateFileOut.write(String.format("TOTAL %-8s", df.format(wsStateTotal)));
        stateFileOut.newLine();
    }
}