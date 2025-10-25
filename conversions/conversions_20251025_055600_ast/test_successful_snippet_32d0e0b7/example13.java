import java.io.*;
import java.nio.file.*;
import java.util.*;

public class example13 {
    private static final String CUSTOMER_FILE = "Customer.dat";
    private static final String REPORT_FILE = "CustReoport.rpt";
    private static final String PAGE_HEADING = "Customer List";
    private static final String HEADS = "IDNum    FirstName    LastName";
    private static final String REPORT_FOOTING = "END OF REPORT";
    private static final String PAGE_FOOTER_FORMAT = "Page : %1d";
    private static final int PAGE_SIZE = 40;

    private int lineCount = 0;
    private int pageCount = 0;

    public static void main(String[] args) {
        example13 program = new example13();
        program.run();
    }

    public void run() {
        try (BufferedReader customerFile = Files.newBufferedReader(Paths.get(CUSTOMER_FILE));
             BufferedWriter customerReport = Files.newBufferedWriter(Paths.get(REPORT_FILE))) {

            printPageHeading(customerReport);
            String line = customerFile.readLine();
            while (line != null) {
                printReportBody(line, customerReport);
                line = customerFile.readLine();
            }
            customerReport.write(String.format("%n%n%n%n%n" + REPORT_FOOTING));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printPageHeading(BufferedWriter customerReport) throws IOException {
        customerReport.write(String.format("%13s%n", PAGE_HEADING));
        customerReport.write(String.format("%n%n%n%n%n" + HEADS));
        lineCount = 3;
        pageCount++;
    }

    private void printReportBody(String line, BufferedWriter customerReport) throws IOException {
        if (lineCount >= PAGE_SIZE) {
            customerReport.write(String.format("%n%n%n%n%n" + String.format(PAGE_FOOTER_FORMAT, pageCount)));
            printPageHeading(customerReport);
        }
        String[] parts = line.split("(?<=\\G.{15})");
        String idNum = parts[0].trim();
        String firstName = parts[1].trim();
        String lastName = parts[2].trim();
        customerReport.write(String.format("%n %5s    %-15s  %-15s", idNum, firstName, lastName));
        lineCount++;
    }
}