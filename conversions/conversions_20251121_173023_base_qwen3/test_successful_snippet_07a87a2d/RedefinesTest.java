import java.util.Arrays;

public class RedefinesTest {
    private static final int MAX_RECORDS = 99;
    private static final int MAX_DATA_TYPES = 2;

    private int wsNumRecords = 3;
    private int wsCurRecordIdx;

    // Customer array
    private Customer[] wsCustomer = new Customer[MAX_RECORDS];
    
    // Data types array
    private DataType[] wsDiffDataTypes = new DataType[MAX_DATA_TYPES];

    public static void main(String[] args) {
        new RedefinesTest().mainProcedure();
    }

    private void mainProcedure() {
        // Initialize customer records
        for (int i = 0; i < MAX_RECORDS; i++) {
            wsCustomer[i] = new Customer();
        }
        
        // Initialize data types
        for (int i = 0; i < MAX_DATA_TYPES; i++) {
            wsDiffDataTypes[i] = new DataType();
        }

        // Record 1 - Person
        wsCustomer[0].setCustomerType(1);
        wsCustomer[0].setCustomerFirstName("test-first");
        wsCustomer[0].setCustomerLastName("test-last");
        wsCustomer[0].setStreetAddress("123 fake st");
        wsCustomer[0].setState("NV");
        wsCustomer[0].setZipCode(12345);

        // Record 2 - Corporation
        wsCustomer[1].setCustomerType(2);
        wsCustomer[1].setCorpName("no-name corp");
        wsCustomer[1].setStreetAddress("567 real st");
        wsCustomer[1].setState("NY");
        wsCustomer[1].setZipCode(11795);

        // Record 3 - Person with corporation name
        wsCustomer[2].setCustomerType(1);
        wsCustomer[2].setCorpName("SET CORP VALUE");
        wsCustomer[2].setStreetAddress("890 what st");
        wsCustomer[2].setState("MA");
        wsCustomer[2].setZipCode(9345);

        // Process records
        for (wsCurRecordIdx = 1; wsCurRecordIdx <= wsNumRecords; wsCurRecordIdx++) {
            Customer currentCustomer = wsCustomer[wsCurRecordIdx - 1];
            
            if (currentCustomer.isCustomerTypePerson()) {
                System.out.println("CUSTOMER TYPE PERSON");
                System.out.println("Customer first name: " + currentCustomer.getCustomerFirstName());
                System.out.println("Customer last name: " + currentCustomer.getCustomerLastName());
            } else {
                System.out.println("CUSTOMER TYPE CORP");
                System.out.println("Company name: " + currentCustomer.getCorpName());
            }

            System.out.println("Address: ");
            System.out.println(currentCustomer.getStreetAddress());
            System.out.println(currentCustomer.getState() + ", " + currentCustomer.getZipCode());
        }

        // Data type examples
        wsDiffDataTypes[0].setDisplayType(true);
        wsDiffDataTypes[0].setDataDispValue("ABC123");

        wsDiffDataTypes[1].setCompType(true);
        wsDiffDataTypes[1].setDataCompValue(12345.63);

        System.out.println("DISPLAY VALUE FILLED IN: ");
        System.out.println("disp 1: " + wsDiffDataTypes[0].getDataDispValue());
        System.out.println("comp 1: " + wsDiffDataTypes[0].getDataCompValue());

        System.out.println("COMP-2 VALUE FILLED IN: ");
        System.out.println("disp 2: " + wsDiffDataTypes[1].getDataDispValue());
        System.out.println("comp 2: " + wsDiffDataTypes[1].getDataCompValue());
    }

    private static class Customer {
        private int customerType;
        private String customerFirstName;
        private String customerLastName;
        private String corpName;
        private String streetAddress;
        private String state;
        private int zipCode;

        public boolean isCustomerTypePerson() {
            return customerType == 1;
        }

        public int getCustomerType() {
            return customerType;
        }

        public void setCustomerType(int customerType) {
            this.customerType = customerType;
        }

        public String getCustomerFirstName() {
            return customerFirstName;
        }

        public void setCustomerFirstName(String customerFirstName) {
            this.customerFirstName = customerFirstName;
        }

        public String getCustomerLastName() {
            return customerLastName;
        }

        public void setCustomerLastName(String customerLastName) {
            this.customerLastName = customerLastName;
        }

        public String getCorpName() {
            return corpName != null ? corpName : 
                   (customerFirstName != null ? customerFirstName : "") +
                   (customerLastName != null ? " " + customerLastName : "");
        }

        public void setCorpName(String corpName) {
            this.corpName = corpName;
        }

        public String getStreetAddress() {
            return streetAddress;
        }

        public void setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getZipCode() {
            return zipCode;
        }

        public void setZipCode(int zipCode) {
            this.zipCode = zipCode;
        }
    }

    private static class DataType {
        private char dataType;
        private String dataDispValue;
        private double dataCompValue;

        public boolean isDisplayType() {
            return dataType == 'D';
        }

        public void setDisplayType(boolean displayType) {
            this.dataType = displayType ? 'D' : ' ';
        }

        public boolean isCompType() {
            return dataType == 'C';
        }

        public void setCompType(boolean compType) {
            this.dataType = compType ? 'C' : ' ';
        }

        public String getDataDispValue() {
            return dataDispValue;
        }

        public void setDataDispValue(String dataDispValue) {
            this.dataDispValue = dataDispValue;
        }

        public double getDataCompValue() {
            return dataCompValue;
        }

        public void setDataCompValue(double dataCompValue) {
            this.dataCompValue = dataCompValue;
        }
    }
}