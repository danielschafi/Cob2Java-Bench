import java.io.*;
import java.nio.file.*;

public class example13 {
    static class CustomerData {
        int IDNum;
        String FirstName;
        String LastName;
    }

    static String PageHeading = "Customer List";
    static String Heads = "IDNum    FirstName    LastName";
    static String ReportFooting = "END OF REPORT";
    static int LineCount = 0;
    static int PageCount = 0;
    static boolean WSEOF = false;
    static CustomerData currentCustomer = new CustomerData();
    static BufferedWriter reportWriter;
    static BufferedReader customerReader;

    public static void main(String[] args) {
        try {
            customerReader = new BufferedReader(new FileReader("Customer.dat"));
            reportWriter = new BufferedWriter(new FileWriter("CustReoport.rpt"));

            printPageHeading();
            readCustomerFile();

            if (!WSEOF) {
                printReportBody();
            }

            writeReportLine(ReportFooting, 5);

            customerReader.close();
            reportWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readCustomerFile() {
        try {
            String line = customerReader.readLine();
            if (line == null) {
                WSEOF = true;
            } else {
                parseCustomerLine(line);
            }
        } catch (IOException e) {
            WSEOF = true;
        }
    }

    static void parseCustomerLine(String line) {
        try {
            if (line.length() >= 35) {
                currentCustomer.IDNum = Integer.parseInt(line.substring(0, 5).trim());
                currentCustomer.FirstName = line.substring(5, 20).trim();
                currentCustomer.LastName = line.substring(20, 35).trim();
            }
        } catch (NumberFormatException e) {
            WSEOF = true;
        }
    }

    static void printPageHeading() {
        try {
            reportWriter.write(PageHeading);
            reportWriter.newLine();
            reportWriter.newLine();
            reportWriter.newLine();
            reportWriter.newLine();
            reportWriter.newLine();
            reportWriter.write(Heads);
            reportWriter.newLine();
            LineCount = 3;
            PageCount++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void printReportBody() {
        try {
            while (!WSEOF) {
                if (LineCount >= 40) {
                    String pageFooting = String.format("               Page : %2d", PageCount);
                    writeReportLine(pageFooting, 5);
                    printPageHeading();
                }

                String customerDetailLine = String.format(" %5d    %-15s  %-15s",
                        currentCustomer.IDNum,
                        currentCustomer.FirstName,
                        currentCustomer.LastName);
                writeReportLine(customerDetailLine, 1);
                LineCount++;

                readCustomerFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void writeReportLine(String line, int advanceLines) {
        try {
            reportWriter.write(line);
            for (int i = 0; i < advanceLines; i++) {
                reportWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}