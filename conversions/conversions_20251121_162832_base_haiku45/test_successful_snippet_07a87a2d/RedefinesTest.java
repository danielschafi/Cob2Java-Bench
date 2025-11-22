import java.util.*;

public class RedefinesTest {
    
    static class Customer {
        int customerType;
        String customerFirstName;
        String customerLastName;
        String corpName;
        String streetAddress;
        String state;
        int zipCode;
        
        Customer() {
            customerType = 0;
            customerFirstName = "";
            customerLastName = "";
            corpName = "";
            streetAddress = "";
            state = "";
            zipCode = 0;
        }
    }
    
    static class DataRecord {
        char dataType;
        String dataDispValue;
        double dataCompValue;
        
        DataRecord() {
            dataType = ' ';
            dataDispValue = "";
            dataCompValue = 0.0;
        }
    }
    
    public static void main(String[] args) {
        int wsNumRecords = 3;
        int wsCurRecordIdx;
        
        Customer[] wsCustomer = new Customer[99];
        for (int i = 0; i < 99; i++) {
            wsCustomer[i] = new Customer();
        }
        
        DataRecord[] wsDataRecords = new DataRecord[2];
        for (int i = 0; i < 2; i++) {
            wsDataRecords[i] = new DataRecord();
        }
        
        wsCustomer[0].customerType = 1;
        wsCustomer[0].customerFirstName = "test-first";
        wsCustomer[0].customerLastName = "test-last";
        wsCustomer[0].streetAddress = "123 fake st";
        wsCustomer[0].state = "NV";
        wsCustomer[0].zipCode = 12345;
        
        wsCustomer[1].customerType = 2;
        wsCustomer[1].corpName = "no-name corp";
        wsCustomer[1].streetAddress = "567 real st";
        wsCustomer[1].state = "NY";
        wsCustomer[1].zipCode = 11795;
        
        wsCustomer[2].customerType = 1;
        wsCustomer[2].corpName = "SET CORP VALUE";
        wsCustomer[2].streetAddress = "890 what st";
        wsCustomer[2].state = "MA";
        wsCustomer[2].zipCode = 9345;
        
        for (wsCurRecordIdx = 1; wsCurRecordIdx <= wsNumRecords; wsCurRecordIdx++) {
            int idx = wsCurRecordIdx - 1;
            
            if (wsCustomer[idx].customerType == 1) {
                System.out.println("CUSTOMER TYPE PERSON");
                System.out.println("Customer first name: " + wsCustomer[idx].customerFirstName);
                System.out.println("Customer last name: " + wsCustomer[idx].customerLastName);
            } else {
                System.out.println("CUSTOMER TYPE CORP");
                System.out.println("Company name: " + wsCustomer[idx].corpName);
            }
            
            System.out.println("Address: ");
            System.out.println(wsCustomer[idx].streetAddress);
            System.out.println(wsCustomer[idx].state + ", " + wsCustomer[idx].zipCode);
        }
        
        wsDataRecords[0].dataType = 'D';
        wsDataRecords[0].dataDispValue = "ABC123";
        
        wsDataRecords[1].dataType = 'C';
        wsDataRecords[1].dataCompValue = 12345.63;
        
        System.out.println("DISPLAY VALUE FILLED IN: ");
        System.out.println("disp 1: " + wsDataRecords[0].dataDispValue);
        System.out.println("comp 1: " + wsDataRecords[0].dataCompValue);
        
        System.out.println("COMP-2 VALUE FILLED IN: ");
        System.out.println("disp 2: " + wsDataRecords[1].dataDispValue);
        System.out.println("comp 2: " + wsDataRecords[1].dataCompValue);
    }
}