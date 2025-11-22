import java.io.*;
import java.nio.file.*;

public class example13 {
    private static final int HIGH_VALUE = 255;
    
    private static class CustomerData {
        int IDNum;
        String FirstName;
        String LastName;
        boolean WSEOF;
        
        CustomerData() {
            IDNum = 0;
            FirstName = "";
            LastName = "";
            WSEOF = false;
        }
    }
    
    private static BufferedReader customerFile;
    private static BufferedWriter customerReport;
    private static CustomerData currentCustomer = new CustomerData();
    private static int LineCount = 0;
    private static int PageCount = 0;
    
    public static void main(String[] args) {
        try {
            customerFile = new BufferedReader(new FileReader("Customer.dat"));
            customerReport = new BufferedWriter(new FileWriter("CustReoport.rpt"));
            
            PrintPageHeading();
            ReadCustomerFile();
            
            while (!currentCustomer.WSEOF) {
                PrintReportBody();
            }
            
            writeLineAfterAdvancing("END OF REPORT", 5);
            
            customerFile.close();
            customerReport.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void PrintPageHeading() throws IOException {
        writeLineAfterAdvancingPage("Customer List");
        writeLineAfterAdvancing("IDNum    FirstName    LastName", 5);
        LineCount = 3;
        PageCount++;
    }
    
    private static void PrintReportBody() throws IOException {
        if (NewPageRequired()) {
            String pageFooting = String.format("%15s%s%2d", "", "Page : ", PageCount);
            writeLineAfterAdvancing(pageFooting, 5);
            PrintPageHeading();
        }
        
        String detailLine = String.format(" %05d    %-15s  %-15s", 
            currentCustomer.IDNum, 
            currentCustomer.FirstName, 
            currentCustomer.LastName);
        
        writeLineAfterAdvancing(detailLine, 1);
        LineCount++;
        ReadCustomerFile();
    }
    
    private static void ReadCustomerFile() throws IOException {
        String line = customerFile.readLine();
        if (line == null) {
            currentCustomer.WSEOF = true;
        } else {
            if (line.length() >= 35) {
                currentCustomer.IDNum = Integer.parseInt(line.substring(0, 5).trim());
                currentCustomer.FirstName = line.substring(5, 20);
                currentCustomer.LastName = line.substring(20, 35);
            } else {
                currentCustomer.WSEOF = true;
            }
        }
    }
    
    private static boolean NewPageRequired() {
        return LineCount >= 40;
    }
    
    private static void writeLineAfterAdvancingPage(String text) throws IOException {
        customerReport.write("\f");
        customerReport.write(text);
        customerReport.newLine();
    }
    
    private static void writeLineAfterAdvancing(String text, int lines) throws IOException {
        for (int i = 0; i < lines; i++) {
            customerReport.newLine();
        }
        customerReport.write(text);
        customerReport.newLine();
    }
}