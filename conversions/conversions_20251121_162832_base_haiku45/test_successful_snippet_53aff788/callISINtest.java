```java
import java.util.Arrays;

public class callISINtest {
    public static void main(String[] args) {
        int[] ISINtest_result = new int[1];
        
        System.out.print("should be valid ");
        ISINtest("US0378331005", ISINtest_result);
        displayISINtestResult(ISINtest_result[0]);
        
        System.out.print("should not be valid ");
        ISINtest("US0373831005", ISINtest_result);
        displayISINtestResult(ISINtest_result[0]);
        
        System.out.print("should not be valid ");
        ISINtest("U50378331005", ISINtest_result);
        displayISINtestResult(ISINtest_result[0]);
        
        System.out.print("should not be valid ");
        ISINtest("US03378331005", ISINtest_result);
        displayISINtestResult(ISINtest_result[0]);
        
        System.out.print("should be valid ");
        ISINtest("AU0000XVGZA3", ISINtest_result);
        displayISINtestResult(ISINtest_result[0]);
        
        System.out.print("should be valid ");
        ISINtest("AU0000VXGZA3", ISINtest_result);
        displayISINtestResult(ISINtest_result[0]);
        
        System.out.print("should be valid ");
        ISINtest("FR0000988040", ISINtest_result);
        displayISINtestResult(ISINtest_result[0]);
    }
    
    private static void displayISINtestResult(int result) {
        switch (result) {
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
                System.out.println(" invalid return code " + result);
        }
    }
    
    private static void ISINtest(String testNumber, int[] ISINtest_result) {
        String countryCodeValues = "ADAEAFAGAIALAMAOAQARASATAUAWAXAZBABBBDBEBFBGBHBIBJBLBMBNBOBQBRBS" +
            "BTBVBWBYBZCACCCDCFCGCHCICKCLCMCNCOCRCUCVCWCXCYCZDEDJDKDMDODZECEE" +
            "EGEHERESETFIFJFKFMFOFRGAGBGDGEGFGGGHGIGLGMGNGPGQGRGSGTGUGWGYHKHM" +
            "HNHRHTHUIDIEILIMINIOIQIRISITJEJMJOJPKEKGKHKIKMKNKPKRKWKYKZLALBLC" +
            "LILKLRLSLTLULVLYMAMCMDMEMFMGMHMKMLMMMNMOMPMQMRMSMTMUMVMWMXMYMZNA" +
            "NCNENFNGNINLNONPNRNUNZOMPAPEPFPGPHPKPLPMPNPRPSPTPWPYQARERORSRURW" +
            "SASBSCSDSESGSHSISJSKSLSMSNSOSRSSSTSVSXSYSZTCTDTFTGTHTJTKTLTMTNTO" +
            "TRTTTVTWTZUAUGUMUSUYUZVAVCVEVGVGVIVNVUWFWSYEYTZAZMZW";
        
        String[] countryCodes = new String[249];
        for (int i = 0; i < 249; i++) {
            countryCodes[i] = countryCodeValues.substring(i * 2, i * 2 + 2);
        }
        Arrays.sort(countryCodes);
        
        String base36Digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        System.out.print(" " + testNumber);
        
        if (testNumber.length() != 12) {
            ISINtest_result[0] = -1;
            return;
        }
        
        String countryCode = testNumber.substring(0, 2);
        boolean found = false;
        for (String cc : countryCodes) {
            if (cc.equals(countryCode)) {
                found = true;
                break;
            }
        }
        
        if (!found) {
            ISINtest_result[0] = -2;
            return;
        }
        
        StringBuilder luhnNumber = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            char c = testNumber.charAt(i);
            if (c >= '0' && c <= '9') {
                luhnNumber.append(c);
            } else {
                int b = base36Digits.indexOf(c);
                if (b < 0) {
                    ISINtest_result[0] = -3;
                    return;
                }
                luhnNumber.append(b);
            }
        }
        
        int[] luhntest_result = new int[1];
        luhntest(luhnNumber.toString(), luhntest_result);
        if (luhntest_result[0] != 0) {
            ISINtest_result[0] = -4;
            return;
        }
        
        ISINtest_result[0] = 0;
    }
    
    private static void luhntest(String testNumber, int[] luhntest_result) {
        System.out.print(" " + testNumber);
        int checkSum = 0;
        
        int length = testNumber.length();
        for (int i = length - 1; i >= 0; i -= 2) {
            checkSum += Character.getNumericValue(testNumber.charAt(i));
        }
        System.out.print(" " + checkSum);
        
        for (int i = length - 2; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(testNumber.charAt(i));
            checkSum += digit;
            checkSum += digit;
            
            if (testNumber.charAt(i) >= '5') {
                checkSum -= 9;
            }
        }
        System.out.print(" " + checkSum);
        
        if (checkSum % 10 == 0) {
            luhntest_result[0] = 0;
        } else {
            luhntest_result[0] = -1;
        }
    }
}