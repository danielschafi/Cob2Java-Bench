import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class RedefinesTest {
    
    static class Customer {
        int customerType;
        byte[] customerName = new byte[30];
        byte[] streetAddress = new byte[20];
        byte[] state = new byte[2];
        int zipCode;
        
        String getCustomerFirstName() {
            return new String(customerName, 0, 10).trim();
        }
        
        void setCustomerFirstName(String value) {
            byte[] bytes = String.format("%-10s", value).substring(0, 10).getBytes();
            System.arraycopy(bytes, 0, customerName, 0, 10);
        }
        
        String getCustomerLastName() {
            return new String(customerName, 10, 20).trim();
        }
        
        void setCustomerLastName(String value) {
            byte[] bytes = String.format("%-20s", value).substring(0, 20).getBytes();
            System.arraycopy(bytes, 0, customerName, 10, 20);
        }
        
        String getCorpName() {
            return new String(customerName, 0, 30).trim();
        }
        
        void setCorpName(String value) {
            byte[] bytes = String.format("%-30s", value).substring(0, 30).getBytes();
            System.arraycopy(bytes, 0, customerName, 0, 30);
        }
        
        String getStreetAddress() {
            return new String(streetAddress).trim();
        }
        
        void setStreetAddress(String value) {
            byte[] bytes = String.format("%-20s", value).substring(0, 20).getBytes();
            System.arraycopy(bytes, 0, streetAddress, 0, 20);
        }
        
        String getState() {
            return new String(state).trim();
        }
        
        void setState(String value) {
            byte[] bytes = String.format("%-2s", value).substring(0, 2).getBytes();
            System.arraycopy(bytes, 0, state, 0, 2);
        }
    }
    
    static class DiffDataType {
        char dataType;
        byte[] dataStorage = new byte[10];
        
        String getDataDispValue() {
            return new String(dataStorage, 0, 10);
        }
        
        void setDataDispValue(String value) {
            byte[] bytes = String.format("%-10s", value).substring(0, 10).getBytes();
            System.arraycopy(bytes, 0, dataStorage, 0, 10);
        }
        
        double getDataCompValue() {
            ByteBuffer buffer = ByteBuffer.wrap(dataStorage);
            buffer.order(ByteOrder.nativeOrder());
            return buffer.getDouble();
        }
        
        void setDataCompValue(double value) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            buffer.order(ByteOrder.nativeOrder());
            buffer.putDouble(value);
            byte[] bytes = buffer.array();
            System.arraycopy(bytes, 0, dataStorage, 0, Math.min(8, dataStorage.length));
        }
    }
    
    public static void main(String[] args) {
        int wsNumRecords = 3;
        int wsCurRecordIdx;
        Customer[] wsCustomer = new Customer[99];
        for (int i = 0; i < 99; i++) {
            wsCustomer[i] = new Customer();
        }
        
        DiffDataType[] wsDiffDataTypes = new DiffDataType[2];
        for (int i = 0; i < 2; i++) {
            wsDiffDataTypes[i] = new DiffDataType();
        }
        
        wsCustomer[0].customerType = 1;
        wsCustomer[0].setCustomerFirstName("test-first");
        wsCustomer[0].setCustomerLastName("test-last");
        wsCustomer[0].setStreetAddress("123 fake st");
        wsCustomer[0].setState("NV");
        wsCustomer[0].zipCode = 12345;
        
        wsCustomer[1].customerType = 2;
        wsCustomer[1].setCorpName("no-name corp");
        wsCustomer[1].setStreetAddress("567 real st");
        wsCustomer[1].setState("NY");
        wsCustomer[1].zipCode = 11795;
        
        wsCustomer[2].customerType = 1;
        wsCustomer[2].setCorpName("SET CORP VALUE");
        wsCustomer[2].setStreetAddress("890 what st");
        wsCustomer[2].setState("MA");
        wsCustomer[2].zipCode = 9345;
        
        for (wsCurRecordIdx = 1; wsCurRecordIdx <= wsNumRecords; wsCurRecordIdx++) {
            int idx = wsCurRecordIdx - 1;
            
            if (wsCustomer[idx].customerType == 1) {
                System.out.println("CUSTOMER TYPE PERSON");
                System.out.println("Customer first name: " + wsCustomer[idx].getCustomerFirstName());
                System.out.println("Customer last name: " + wsCustomer[idx].getCustomerLastName());
            } else {
                System.out.println("CUSTOMER TYPE CORP");
                System.out.println("Company name: " + wsCustomer[idx].getCorpName());
            }
            
            System.out.println("Address: ");
            System.out.println(wsCustomer[idx].getStreetAddress());
            System.out.println(wsCustomer[idx].getState() + ", " + 
                String.format("%05d", wsCustomer[idx].zipCode));
        }
        
        wsDiffDataTypes[0].dataType = 'D';
        wsDiffDataTypes[0].setDataDispValue("ABC123");
        
        wsDiffDataTypes[1].dataType = 'C';
        wsDiffDataTypes[1].setDataCompValue(12345.63);
        
        System.out.println("DISPLAY VALUE FILLED IN: ");
        System.out.println("disp 1: " + wsDiffDataTypes[0].getDataDispValue());
        System.out.println("comp 1: " + wsDiffDataTypes[0].getDataCompValue());
        
        System.out.println("COMP-2 VALUE FILLED IN: ");
        System.out.println("disp 2: " + wsDiffDataTypes[1].getDataDispValue());
        System.out.println("comp 2: " + wsDiffDataTypes[1].getDataCompValue());
    }
}