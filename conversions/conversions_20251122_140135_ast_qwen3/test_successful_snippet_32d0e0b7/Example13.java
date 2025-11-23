import java.io.*;
import java.util.*;

public class Example13 {
    private static final String CUSTOMER_REPORT_FILE = "CustReoport.rpt";
    private static final String CUSTOMER_FILE = "Customer.dat";
    
    private static class CustomerRecord {
        int idNum;
        String firstName;
        String lastName;
    }
    
    private static class ReportData {
        String pageHeading = "Customer List";
        String pageFooting = "              Page : ";
        String heads = "IDNum    FirstName    LastName";
        String reportFooting = "END OF REPORT";
        int pageCount = 0;
        int lineCount = 0;
        boolean newPageRequired = false;
        boolean wsEof = false;
        
        // Formatted strings for output
        StringBuilder printLine = new StringBuilder(new String(new char[44]));
        StringBuilder prnPageNum = new StringBuilder("  ");
        StringBuilder prnCusID = new StringBuilder("     ");
        StringBuilder prnFirstName = new StringBuilder(new String(new char[15]));
        StringBuilder prnLastName = new StringBuilder(new String(new char[15]));
        StringBuilder customerDetailLine = new StringBuilder();
        
        public ReportData() {
            // Initialize customerDetailLine with proper formatting
            customerDetailLine.append(" ");
            customerDetailLine.append(String.format("%-5s", ""));
            customerDetailLine.append("    ");
            customerDetailLine.append(String.format("%-15s", ""));
            customerDetailLine.append("  ");
            customerDetailLine.append(String.format("%-15s", ""));
        }
    }
    
    public static void main(String[] args) {
        ReportData data = new ReportData();
        List<CustomerRecord> customerRecords = new ArrayList<>();
        
        // Read customer data from file
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 30) { // Ensure we have enough data
                    CustomerRecord record = new CustomerRecord();
                    record.idNum = Integer.parseInt(line.substring(0, 5).trim());
                    record.firstName = line.substring(5, 20).trim();
                    record.lastName = line.substring(20, 35).trim();
                    customerRecords.add(record);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading customer file: " + e.getMessage());
            return;
        }
        
        // Write report to file
        try (PrintWriter writer = new PrintWriter(new FileWriter(CUSTOMER_REPORT_FILE))) {
            // Print initial page heading
            printPageHeading(data, writer);
            
            // Process each customer record
            for (int i = 0; i < customerRecords.size(); i++) {
                CustomerRecord record = customerRecords.get(i);
                
                // Check if we need a new page
                if (data.newPageRequired) {
                    data.pageCount++;
                    data.prnPageNum.setLength(0);
                    data.prnPageNum.append(String.format("%2d", data.pageCount));
                    
                    // Write page footing and advance lines
                    writer.println(data.pageFooting + data.prnPageNum.toString());
                    for (int j = 0; j < 5; j++) {
                        writer.println();
                    }
                    
                    // Print new page heading
                    printPageHeading(data, writer);
                }
                
                // Format and write customer detail line
                data.prnCusID.setLength(0);
                data.prnCusID.append(String.format("%5d", record.idNum));
                
                data.prnFirstName.setLength(0);
                data.prnFirstName.append(String.format("%-15s", record.firstName));
                
                data.prnLastName.setLength(0);
                data.prnLastName.append(String.format("%-15s", record.lastName));
                
                data.customerDetailLine.setLength(0);
                data.customerDetailLine.append(" ");
                data.customerDetailLine.append(data.prnCusID);
                data.customerDetailLine.append("    ");
                data.customerDetailLine.append(data.prnFirstName);
                data.customerDetailLine.append("  ");
                data.customerDetailLine.append(data.prnLastName);
                
                writer.println(data.customerDetailLine.toString());
                data.lineCount++;
                
                // Check if we need a new page
                data.newPageRequired = data.lineCount >= 40;
            }
            
            // Write final report footing
            for (int i = 0; i < 5; i++) {
                writer.println();
            }
            writer.println(data.reportFooting);
            
        } catch (IOException e) {
            System.err.println("Error writing report file: " + e.getMessage());
        }
    }
    
    private static void printPageHeading(ReportData data, PrintWriter writer) {
        // Write page heading
        writer.println(data.pageHeading);
        
        // Write headers
        writer.println(data.heads);
        
        // Reset line count
        data.lineCount = 3;
        
        // Increment page count
        data.pageCount++;
        
        // Set page number for display
        data.prnPageNum.setLength(0);
        data.prnPageNum.append(String.format("%2d", data.pageCount));
    }
}