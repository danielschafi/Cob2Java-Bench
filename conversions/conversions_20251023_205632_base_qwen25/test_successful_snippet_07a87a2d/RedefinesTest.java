import java.text.DecimalFormat;

public class RedefinesTest {
    public static void main(String[] args) {
        int wsNumRecords = 3;
        int wsCurRecordIdx;

        Customer[] wsCustomer = new Customer[100];
        for (int i = 0; i < wsCustomer.length; i++) {
            wsCustomer[i] = new Customer();
        }

        DataType[] wsDiffDataTypes = new DataType[2];
        for (int i = 0; i < wsDiffDataTypes.length; i++) {
            wsDiffDataTypes[i] = new DataType();
        }

        wsCustomer[0].wsCustomerTypePerson = true;
        wsCustomer[0].wsCustomerFirstName = "test-first";
        wsCustomer[0].wsCustomerLastName = "test-last";
        wsCustomer[0].wsStreetAddress = "123 fake st";
        wsCustomer[0].wsState = "NV";
        wsCustomer[0].wsZipCode = 12345;

        wsCustomer[1].wsCustomerTypeCorp = true;
        wsCustomer[1].wsCorpName = "no-name corp";
        wsCustomer[1].wsStreetAddress = "567 real st";
        wsCustomer[1].wsState = "NY";
        wsCustomer[1].wsZipCode = 11795;

        wsCustomer[2].wsCustomerTypePerson = true;
        wsCustomer[2].wsCorpName = "SET CORP VALUE";
        wsCustomer[2].wsStreetAddress = "890 what st";
        wsCustomer[2].wsState = "MA";
        wsCustomer[2].wsZipCode = 9345;

        for (wsCurRecordIdx = 0; wsCurRecordIdx < wsNumRecords; wsCurRecordIdx++) {
            if (wsCustomer[wsCurRecordIdx].wsCustomerTypePerson) {
                System.out.println("CUSTOMER TYPE PERSON");
                System.out.println("Customer first name: " + wsCustomer[wsCurRecordIdx].wsCustomerFirstName);
                System.out.println("Customer last name: " + wsCustomer[wsCurRecordIdx].wsCustomerLastName);
            } else {
                System.out.println("CUSTOMER TYPE CORP");
                System.out.println("Company name: " + wsCustomer[wsCurRecordIdx].wsCorpName);
            }

            System.out.println("Address: " + wsCustomer[wsCurRecordIdx].wsStreetAddress);
            System.out.println(wsCustomer[wsCurRecordIdx].wsState + ", " + wsCustomer[wsCurRecordIdx].wsZipCode);
        }

        wsDiffDataTypes[0].wsDisplayType = true;
        wsDiffDataTypes[0].wsDataDispValue = "ABC123";

        wsDiffDataTypes[1].wsCompType = true;
        wsDiffDataTypes[1].wsDataCompValue = 12345.63f;

        System.out.println("DISPLAY VALUE FILLED IN:");
        System.out.println("disp 1: " + wsDiffDataTypes[0].wsDataDispValue);
        System.out.println("comp 1: " + wsDiffDataTypes[0].wsDataCompValue);

        System.out.println("COMP-2 VALUE FILLED IN:");
        System.out.println("disp 2: " + wsDiffDataTypes[1].wsDataDispValue);
        System.out.println("comp 2: " + new DecimalFormat("0.00").format(wsDiffDataTypes[1].wsDataCompValue));
    }
}

class Customer {
    boolean wsCustomerTypePerson;
    boolean wsCustomerTypeCorp;
    String wsCustomerFirstName;
    String wsCustomerLastName;
    String wsCorpName;
    String wsStreetAddress;
    String wsState;
    int wsZipCode;
}

class DataType {
    boolean wsDisplayType;
    boolean wsCompType;
    String wsDataDispValue;
    float wsDataCompValue;
}