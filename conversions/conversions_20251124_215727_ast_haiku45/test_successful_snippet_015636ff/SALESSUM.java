```java
import java.io.*;
import java.util.*;

public class SALESSUM {
    static class SalesRecord {
        String state;
        String id;
        double amount;
    }

    static class SalesIdEntry {
        String id = "";
        double amount = 0.0;
    }

    static class StateEntry {
        String state = "";
        double amount = 0.0;
    }

    static SalesIdEntry[] tableSalesIdEntry = new SalesIdEntry[99];
    static StateEntry[] tableStateEntry = new StateEntry[50];
    static double wsSalesTotal = 0.0;
    static double wsStateTotal = 0.0;
    static int wsIdSub = 0;
    static int wsStateSub = 0;
    static int wsDataSwitch = 1;
    static String salesFileStatus = "";

    static BufferedReader salesFileIn;
    static PrintWriter salesFileOut;
    static PrintWriter stateFileOut;

    public static void main(String[] args) {
        try {
            System.out.println("SALESSUM PROGRAM START");

            salesFileIn = new BufferedReader(new FileReader("SALESIDAMT.DAT"));
            salesFileOut = new PrintWriter(new FileWriter("SALESIDOUT.DAT"));
            stateFileOut = new PrintWriter(new FileWriter("STATESOUT.DAT"));

            initializeTable();

            String line = salesFileIn.readLine();
            if (line != null) {
                populateTables(line);
                while (salesFileStatus.compareTo("02") < 0 && wsDataSwitch == 1) {
                    line = salesFileIn.readLine();
                    if (line == null) {
                        wsDataSwitch = 0;
                        salesFileStatus = "10";
                    } else {
                        populateTables(line);
                    }
                }
            }

            writeSummaryFiles();
            writeTrailers();

            salesFileIn.close();
            salesFileOut.close();
            stateFileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void initializeTable() {
        for (int i = 0; i < 99; i++) {
            tableSalesIdEntry[i] = new SalesIdEntry();
            tableSalesIdEntry[i].id = "";
            tableSalesIdEntry[i].amount = 0.0;
        }

        for (int i = 0; i < 50; i++) {
            tableStateEntry[i] = new StateEntry();
            tableStateEntry[i].state = "";
            tableStateEntry[i].amount = 0.0;
        }
    }

    static void populateTables(String line) {
        if (line.length() < 9) {
            return;
        }

        String state = line.substring(0, 2).trim();
        String id = line.substring(2, 5).trim();
        String amountStr = line.substring(5, 9);
        double amount = 0.0;

        try {
            amount = Double.parseDouble(amountStr) / 100.0;
        } catch (NumberFormatException e) {
            amount = 0.0;
        }

        wsIdSub = 0;
        for (int i = 0; i < 98; i++) {
            if (tableSalesIdEntry[i].id.isEmpty() || tableSalesIdEntry[i].id.equals(id)) {
                wsIdSub = i;
                break;
            }
        }

        if (tableSalesIdEntry[wsIdSub].id.equals(id)) {
            tableSalesIdEntry[wsIdSub].amount += amount;
        } else {
            tableSalesIdEntry[wsIdSub].amount += amount;
            tableSalesIdEntry[wsIdSub].id = id;
        }

        wsStateSub = 0;
        for (int i = 0; i < 49; i++) {
            if (tableStateEntry[i].state.isEmpty() || tableStateEntry[i].state.equals(state)) {
                wsStateSub = i;
                break;
            }
        }

        if (tableStateEntry[wsStateSub].state.equals(state)) {
            tableStateEntry[wsStateSub].amount += amount;
        } else {
            tableStateEntry[wsStateSub].amount += amount;
            tableStateEntry[wsStateSub].state = state;
        }
    }

    static void writeSummaryFiles() {
        for (int i = 0; i < 98; i++) {
            if (tableSalesIdEntry[i].id.isEmpty()) {
                break;
            }
            String line = String.format("%-3s%08.2f", tableSalesIdEntry[i].id, tableSalesIdEntry[i].amount);
            salesFileOut.println(line);
            wsSalesTotal += tableSalesIdEntry[i].amount;
        }

        for (int i = 0; i < 49; i++) {
            if (tableStateEntry[i].state.isEmpty()) {
                break;
            }
            String line = String.format("%-2s%08.2f", tableStateEntry[i].state, tableStateEntry[i].amount);
            stateFileOut.println(line);
            wsStateTotal += tableStateEntry[i].amount;
        }
    }

    static void writeTrailers() {
    }
}