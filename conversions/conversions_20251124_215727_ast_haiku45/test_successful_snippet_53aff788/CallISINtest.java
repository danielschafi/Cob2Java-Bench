public class CallISINtest {
    static int isinTestResult;

    public static void main(String[] args) {
        testISIN("US0378331005", "should be valid ");
        testISIN("US0373831005", "should not be valid ");
        testISIN("U50378331005", "should not be valid ");
        testISIN("US03378331005", "should not be valid ");
        testISIN("AU0000XVGZA3", "should be valid ");
        testISIN("AU0000VXGZA3", "should be valid ");
        testISIN("FR0000988040", "should be valid ");
    }

    static void testISIN(String testNumber, String message) {
        System.out.print(message);
        isinTestResult = ISINtest.isinTest(testNumber);
        displayISINtestResult();
    }

    static void displayISINtestResult() {
        switch (isinTestResult) {
            case 0:
                System.out.println(" is valid");
                break;
            case -1:
                System.out.println(" invalid length ");
                break;
            case -2:
                System.out.println(" invalid countrycode ");
                break;
            case -3:
                System.out.println(" invalid base36 digit ");
                break;
            case -4:
                System.out.println(" luhn test failed");
                break;
            default:
                System.out.println(" invalid return code " + isinTestResult);
        }
    }
}

class ISINtest {
    private static final String COUNTRY_CODES = 
        "ADAEAFAGAIALAMAOAQARASATAUAWAXAZBABBBDBEBFBGBHBIBJBLBMBNBOBQBRBS" +
        "BTBVBWBYBZCACCCDCFCGCHCICKCLCMCNCOCRCUCVCWCXCYCZDEDJDKDMDODZECEE" +
        "EGEHERESETFIFJFKFMFOFRGAGBGDGEGFGGGHGIGLGMGNGPGQGRGSGTGUGWGYHKHM" +
        "HNHRHTHUIDIEILIMINIOIQIRISITJEJMJOJPKEKGKHKIKMKNKPKRKWKYKZLALBLC" +
        "LILKLRLSLTLULVLYMAMCMDMEMFMGMHMKMLMMMNMOMPMQMRMSMTMUMVMWMXMYMZNA" +
        "NCNENFNGNINLNONPNRNUNZOMPAPEPFPGPHPKPLPMPNPRPSPTPWPYQARERORSRURW" +
        "SASBSCSDSESGSHSISJSKSLSMSNSOSRSSSTSVSXSYSZTCTDTFTGTHTJTKTLTMTNTO" +
        "TRTTTVTWTZUAUGUMUSUYUZVAVCVEVGVIVNVUWFWSYEYTZAZMZW";
    
    private static final String BASE36_DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static int isinTest(String testNumber) {
        System.out.print(" " + testNumber);

        if (testNumber.length() != 12) {
            return -1;
        }

        String countryCode = testNumber.substring(0, 2);
        boolean found = false;
        for (int i = 0; i < COUNTRY_CODES.length(); i += 2) {
            if (COUNTRY_CODES.substring(i, i + 2).equals(countryCode)) {
                found = true;
                break;
            }
        }
        if (!found) {
            return -2;
        }

        StringBuilder luhnNumber = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            char c = testNumber.charAt(i);
            if (c >= '0' && c <= '9') {
                luhnNumber.append(c);
            } else {
                int b = BASE36_DIGITS.indexOf(c);
                if (b < 0) {
                    return -3;
                }
                luhnNumber.append(b);
            }
        }

        int luhnTestResult = Luhntest.luhnTest(luhnNumber.toString());
        if (luhnTestResult != 0) {
            return -4;
        }

        return 0;
    }
}

class Luhntest {
    static int luhnTest(String testNumber) {
        System.out.print(" " + testNumber);
        int checkSum = 0;

        int len = testNumber.length();
        for (int i = len - 1; i >= 0; i -= 2) {
            checkSum += Character.getNumericValue(testNumber.charAt(i));
        }
        System.out.print(" " + checkSum);

        for (int i = len - 2; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(testNumber.charAt(i));
            checkSum += digit + digit;
            if (testNumber.charAt(i) >= '5') {
                checkSum -= 9;
            }
        }
        System.out.print(" " + checkSum);

        if (checkSum % 10 == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}