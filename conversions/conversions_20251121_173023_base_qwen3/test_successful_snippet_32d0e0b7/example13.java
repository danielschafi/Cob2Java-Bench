import java.io.*;
import java.nio.file.*;

public class example13 {
    private static final String CUSTOMER_REPORT_FILE = "CustReoport.rpt";
    private static final String CUSTOMER_FILE = "Customer.dat";
    
    private static PrintWriter reportWriter;
    private static BufferedReader customerReader;
    
    private static int page = 1;
    private static int pageCount = 0;
    private static int lineCount = 0;
    
    public static void main(String[] args) {
        try {
            reportWriter = new PrintWriter(new FileWriter(CUSTOMER_REPORT_FILE));
            customerReader = Files.newBufferedReader(Paths.get(CUSTOMER_FILE));
            
            printPageHeading();
            
            String line;
            while ((line = customerReader.readLine()) != null) {
                if (line.length() < 30) continue; // Skip invalid lines
                
                String idNum = line.substring(0, 5).trim();
                String firstName = line.substring(5, 20).trim();
                String lastName = line.substring(20, 35).trim();
                
                if (idNum.isEmpty()) break;
                
                printReportBody(idNum, firstName, lastName);
            }
            
            reportWriter.println("END OF REPORT");
            reportWriter.println();
            reportWriter.println();
            reportWriter.println();
            reportWriter.println();
            reportWriter.println();
            
            reportWriter.close();
            customerReader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void printPageHeading() {
        reportWriter.println("Customer List");
        reportWriter.println();
        reportWriter.println();
        reportWriter.println();
        reportWriter.println();
        reportWriter.println("IDNum    FirstName    LastName");
        reportWriter.println();
        reportWriter.println();
        
        lineCount = 3;
        pageCount++;
    }
    
    private static void printReportBody(String idNum, String firstName, String lastName) {
        if (lineCount >= 40) {
            reportWriter.println("              Page : " + pageCount);
            reportWriter.println();
            reportWriter.println();
            reportWriter.println();
            reportWriter.println();
            reportWriter.println();
            printPageHeading();
        }
        
        String detailLine = " " + 
                           String.format("%-5s", idNum) + "    " +
                           String.format("%-15s", firstName) + "  " +
                           String.format("%-15s", lastName);
        
        reportWriter.println(detailLine);
        lineCount++;
    }
}