import java.io.*;
import java.nio.file.*;

public class example13 {
    private static final String CUSTOMER_FILE = "Customer.dat";
    private static final String CUSTOMER_REPORT = "CustReoport.rpt";
    private static final String PAGE_HEADING = "Customer List";
    private static final String HEADS = "IDNum    FirstName    LastName";
    private static final String REPORT_FOOTING = "END OF REPORT";
    private static final int LINES_PER_PAGE = 40;

    private static int lineCount = 0;
    private static int pageCount = 0;
    private static boolean wseof = false;

    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(CUSTOMER_FILE));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(CUSTOMER_REPORT))) {

            printPageHeading(writer);
            readCustomerFile(reader);
            writer.write(String.format("%13s%n", REPORT_FOOTING));
            for (int i = 0; i < 5; i++) {
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printPageHeading(BufferedWriter writer) throws IOException {
        writer.write(String.format("%13s%n", PAGE_HEADING));
        for (int i = 0; i < 5; i++) {
            writer.newLine();
        }
        writer.write(String.format("%36s%n", HEADS));
        lineCount = 3;
        pageCount++;
    }

    private static void readCustomerFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null && !wseof) {
            printReportBody(writer, line);
        }
        wseof = true;
    }

    private static void printReportBody(BufferedWriter writer, String line) throws IOException {
        if (lineCount >= LINES_PER_PAGE) {
            writer.write(String.format("%15s%7s%n", " ", pageCount));
            for (int i = 0; i < 5; i++) {
                writer.newLine();
            }
            printPageHeading(writer);
        }
        String idNum = line.substring(0, 5).trim();
        String firstName = line.substring(5, 20).trim();
        String lastName = line.substring(20, 35).trim();
        writer.write(String.format(" %5s    %-15s  %-15s%n", idNum, firstName, lastName));
        lineCount++;
    }
}