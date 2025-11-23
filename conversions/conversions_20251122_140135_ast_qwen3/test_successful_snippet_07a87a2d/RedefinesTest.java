public class RedefinesTest {
    private static final int WS_NUM_RECORDS = 3;
    private static int wsCurRecordIdx;
    
    private static class CustomerRecord {
        private int wsCustomerType;
        private String wsCustomerFirstName;
        private String wsCustomerLastName;
        private String wsCorpName;
        private String wsStreetAddress;
        private String wsState;
        private int wsZipCode;
        
        public CustomerRecord() {
            this.wsCustomerType = 0;
            this.wsCustomerFirstName = "";
            this.wsCustomerLastName = "";
            this.wsCorpName = "";
            this.wsStreetAddress = "";
            this.wsState = "";
            this.wsZipCode = 0;
        }
    }
    
    private static CustomerRecord[] wsCustomer = new CustomerRecord[100];
    
    private static class DiffDataTypesRecord {
        private char wsDataType;
        private String wsDataDispValue;
        private float wsDataCompValue;
        
        public DiffDataTypesRecord() {
            this.wsDataType = '\0';
            this.wsDataDispValue = "";
            this.wsDataCompValue = 0.0f;
        }
    }
    
    private static DiffDataTypesRecord[] wsDiffDataTypes = new DiffDataTypesRecord[2];
    
    static {
        for (int i = 0; i < 100; i++) {
            wsCustomer[i] = new CustomerRecord();
        }
        for (int i = 0; i < 2; i++) {
            wsDiffDataTypes[i] = new DiffDataTypesRecord();
        }
    }
    
    public static void main(String[] args) {
        // Initialize customer records
        wsCustomer[0].wsCustomerType = 1;
        wsCustomer[0].wsCustomerFirstName = "test-first";
        wsCustomer[0].wsCustomerLastName = "test-last";
        wsCustomer[0].wsStreetAddress = "123 fake st";
        wsCustomer[0].wsState = "NV";
        wsCustomer[0].wsZipCode = 12345;
        
        wsCustomer[1].wsCustomerType = 2;
        wsCustomer[1].wsCorpName = "no-name corp";
        wsCustomer[1].wsStreetAddress = "567 real st";
        wsCustomer[1].wsState = "NY";
        wsCustomer[1].wsZipCode = 11795;
        
        wsCustomer[2].wsCustomerType = 1;
        wsCustomer[2].wsCorpName = "SET CORP VALUE";
        wsCustomer[2].wsStreetAddress = "890 what st";
        wsCustomer[2].wsState = "MA";
        wsCustomer[2].wsZipCode = 9345;
        
        // Process customer records
        for (wsCurRecordIdx = 1; wsCurRecordIdx <= WS_NUM_RECORDS; wsCurRecordIdx++) {
            if (wsCustomer[wsCurRecordIdx - 1].wsCustomerType == 1) {
                System.out.println("CUSTOMER TYPE PERSON");
                System.out.println("Customer first name: " + wsCustomer[wsCurRecordIdx - 1].wsCustomerFirstName);
                System.out.println("Customer last name: " + wsCustomer[wsCurRecordIdx - 1].wsCustomerLastName);
            } else {
                System.out.println("CUSTOMER TYPE CORP");
                System.out.println("Company name: " + wsCustomer[wsCurRecordIdx - 1].wsCorpName);
            }
            
            System.out.println("Address: ");
            System.out.println(wsCustomer[wsCurRecordIdx - 1].wsStreetAddress);
            System.out.println(wsCustomer[wsCurRecordIdx - 1].wsState + ", " + wsCustomer[wsCurRecordIdx - 1].wsZipCode);
        }
        
        // Test redefines with different data types
        wsDiffDataTypes[0].wsDataType = 'D';
        wsDiffDataTypes[0].wsDataDispValue = "ABC123";
        
        wsDiffDataTypes[1].wsDataType = 'C';
        wsDiffDataTypes[1].wsDataCompValue = 12345.63f;
        
        System.out.println("DISPLAY VALUE FILLED IN: ");
        System.out.println("disp 1: " + wsDiffDataTypes[0].wsDataDispValue);
        System.out.println("comp 1: " + wsDiffDataTypes[0].wsDataCompValue);
        
        System.out.println("COMP-2 VALUE FILLED IN: ");
        System.out.println("disp 2: " + wsDiffDataTypes[1].wsDataDispValue);
        System.out.println("comp 2: " + wsDiffDataTypes[1].wsDataCompValue);
    }
}