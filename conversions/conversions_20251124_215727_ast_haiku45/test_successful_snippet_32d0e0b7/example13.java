import java.io.*;
import java.util.*;

public class example13 {
    private static final String CUSTOMER_FILE = "Customer.dat";
    private static final String REPORT_FILE = "CustReoport.rpt";
    
    private static BufferedReader customerFileReader;
    private static PrintWriter reportWriter;
    
    private static String printLine;
    private static int idNum;
    private static String firstName;
    private static String lastName;
    private static boolean wseof;
    
    private static String pageHeading = "Customer List";
    private static String pageFooting = "                       Page : ";
    private static String heads = "IDNum    FirstName    LastName";
    private static String reportFooting = "END OF REPORT";
    private static int lineCount = 0;
    private static int pageCount = 0;
    
    private static String prnCusID;
    private static String prnFirstName;
    private static String prnLastName;
    private static String prnPageNum;
    
    public static void main(String[] args) {
        try {
            customerFileReader = new BufferedReader(new FileReader(CUSTOMER_FILE));
            reportWriter = new PrintWriter(new FileWriter(REPORT_FILE));
            
            printPageHeading();
            readCustomerFile();
            
            if (!wseof) {
                printReportBody();
            }
            
            writeReportFooting();
            
            customerFileReader.close();
            reportWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void readCustomerFile() {
        try {
            String line = customerFileReader.readLine();
            if (line == null) {
                wseof = true;
            } else {
                parseCustomerData(line);
                wseof = false;
            }
        } catch (IOException e) {
            wseof = true;
        }
    }
    
    private static void parseCustomerData(String line) {
        if (line.length() >= 35) {
            String idStr = line.substring(0, 5).trim();
            firstName = line.substring(5, 20).trim();
            lastName = line.substring(20, 35).trim();
            try {
                idNum = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                idNum = 0;
            }
        }
    }
    
    private static void printPageHeading() {
        reportWriter.println(pageHeading);
        reportWriter.println();
        reportWriter.println();
        reportWriter.println();
        reportWriter.println();
        reportWriter.println(heads);
        lineCount = 3;
        pageCount++;
    }
    
    private static void printReportBody() {
        while (!wseof) {
            if (lineCount >= 40) {
                prnPageNum = String.format("%2d", pageCount);
                reportWriter.println();
                reportWriter.println();
                reportWriter.println();
                reportWriter.println();
                reportWriter.println(pageFooting + prnPageNum);
                printPageHeading();
            }
            
            prnCusID = String.format("%5d", idNum);
            prnFirstName = String.format("%-15s", firstName);
            prnLastName = String.format("%-15s", lastName);
            
            String customerDetailLine = " " + prnCusID + "    " + prnFirstName + "  " + prnLastName;
            reportWriter.println(customerDetailLine);
            lineCount++;
            
            readCustomerFile();
        }
    }
    
    private static void writeReportFooting() {
        reportWriter.println();
        reportWriter.println();
        reportWriter.println();
        reportWriter.println();
        reportWriter.println();
        reportWriter.println(reportFooting);
    }
}