import java.text.DecimalFormat;

public class RedefinesTest {
    private static final int WS_NUM_RECORDS = 3;
    private static final int WS_CUSTOMER_LENGTH = 99;
    private static final int WS_DIFF_DATA_TYPES_LENGTH = 2;

    private int wsCurRecordIdx;
    private int[] wsCustomerType = new int[WS_CUSTOMER_LENGTH];
    private String[] wsCustomerFirstName = new String[WS_CUSTOMER_LENGTH];
    private String[] wsCustomerLastName = new String[WS_CUSTOMER_LENGTH];
    private String[] wsCorpName = new String[WS_CUSTOMER_LENGTH];
    private String[] wsStreetAddress = new String[WS_CUSTOMER_LENGTH];
    private String[] wsState = new String[WS_CUSTOMER_LENGTH];
    private int[] wsZipCode = new int[WS_CUSTOMER_LENGTH];
    private char[] wsDataType = new char[WS_DIFF_DATA_TYPES_LENGTH];
    private String[] wsDataDispValue = new String[WS_DIFF_DATA_TYPES_LENGTH];
    private short[] wsDataCompValue = new short[WS_DIFF_DATA_TYPES_LENGTH];

    public static void main(String[] args) {
        RedefinesTest program = new RedefinesTest();
        program.mainProcedure();
    }

    private void mainProcedure() {
        wsCustomerType[0] = 1;
        wsCustomerFirstName[0] = "test-first";
        wsCustomerLastName[0] = "test-last";
        wsStreetAddress[0] = "123 fake st";
        wsState[0] = "NV";
        wsZipCode[0] = 12345;

        wsCustomerType[1] = 2;
        wsCorpName[1] = "no-name corp";
        wsStreetAddress[1] = "567 real st";
        wsState[1] = "NY";
        wsZipCode[1] = 11795;

        wsCustomerType[2] = 1;
        wsCorpName[2] = "SET CORP VALUE";
        wsStreetAddress[2] = "890 what st";
        wsState[2] = "MA";
        wsZipCode[2] = 9345;

        for (wsCurRecordIdx = 0; wsCurRecordIdx < WS_NUM_RECORDS; wsCurRecordIdx++) {
            if (wsCustomerType[wsCurRecordIdx] == 1) {
                System.out.println("CUSTOMER TYPE PERSON");
                System.out.println("Customer first name: " + wsCustomerFirstName[wsCurRecordIdx]);
                System.out.println("Customer last name: " + wsCustomerLastName[wsCurRecordIdx]);
            } else {
                System.out.println("CUSTOMER TYPE CORP");
                System.out.println("Company name: " + wsCorpName[wsCurRecordIdx]);
            }

            System.out.println("Address: " + wsStreetAddress[wsCurRecordIdx]);
            System.out.println(wsState[wsCurRecordIdx] + ", " + wsZipCode[wsCurRecordIdx]);
        }

        wsDataType[0] = 'D';
        wsDataDispValue[0] = "ABC123";

        wsDataType[1] = 'C';
        wsDataCompValue[1] = (short) 12345;

        System.out.println("DISPLAY VALUE FILLED IN:");
        System.out.println("disp 1: " + wsDataDispValue[0]);
        System.out.println("comp 1: " + wsDataCompValue[0]);

        System.out.println("COMP-2 VALUE FILLED IN:");
        System.out.println("disp 2: " + wsDataDispValue[1]);
        System.out.println("comp 2: " + new DecimalFormat("0.00").format(wsDataCompValue[1]));
    }
}