public class CallISINtest {
    public static void main(String[] args) {
        int result;
        
        System.out.print("should be valid ");
        result = ISINtest("US0378331005");
        displayISINtestResult(result);
        
        System.out.print("should not be valid ");
        result = ISINtest("US0373831005");
        displayISINtestResult(result);
        
        System.out.print("should not be valid ");
        result = ISINtest("U50378331005");
        displayISINtestResult(result);
        
        System.out.print("should not be valid ");
        result = ISINtest("US03378331005");
        displayISINtestResult(result);
        
        System.out.print("should be valid ");
        result = ISINtest("AU0000XVGZA3");
        displayISINtestResult(result);
        
        System.out.print("should be valid ");
        result = ISINtest("AU0000VXGZA3");
        displayISINtestResult(result);
        
        System.out.print("should be valid ");
        result = ISINtest("FR0000988040");
        displayISINtestResult(result);
    }
    
    public static int ISINtest(String testNumber) {
        String countryCodes = "ADAEAFAGAIALAMAOAQARASATAUAWAXAZBABBBDBEBFBGBHBIBJBLBMBNBOBQBRBS" +
                              "BTBVBWBYBZCACCCDCFCGCHCICKCLCMCNCOCRCUCVCWCXCYCZDEDJDKDMDODZECEE" +
                              "EGEHERESETFIFJFKFMFOFRGAGBGDGEGFGGGHGIGLGMGNGPGQGRGSGTGUGWGYHKHM" +
                              "HNHRHTHUIDIEILIMINIOIQIRISITJEJMJOJPKEKGKHKIKMKNKPKRKWKYKZLALBLC" +
                              "LILKLRLSLTLULVLYMAMCMDMEMFMGMHMKMLMMMNMOMPMQMRMSMTMUMVMWMXMYMZNA" +
                              "NCNENFNGNINLNONPNRNUNZOMPAPEPFPGPHPKPLPMPNPRPSPTPWPYQARERORSRURW" +
                              "SASBSCSDSESGSHSISJSKSLSMSNSOSRSSSTSVSXSYSZTCTDTFTGTHTJTKTLTMTNTO" +
                              "TRTTTVTWTZUAUGUMUSUYUZVAVCVEVGVIVNVUWFWSYEYTZAZMZW";
        
        String base36Digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] luhnNumber = new char[20];
        int p = 0;
        int result;
        
        if (testNumber.length() != 12) {
            return -1;
        }
        
        boolean foundCountryCode = false;
        for (int i = 0; i < 249; i++) {
            String countryCode = countryCodes.substring(i * 2, i * 2 + 2);
            if (testNumber.substring(0, 2).equals(countryCode)) {
                foundCountryCode = true;
                break;
            }
        }
        
        if (!foundCountryCode) {
            return -2;
        }
        
        for (int i = 0; i < testNumber.length(); i++) {
            char c = testNumber.charAt(i);
            if (c >= '0' && c <= '9') {
                luhnNumber[p] = c;
                p++;
            } else {
                int b = 9;
                while (b <= 35 && base36Digits.charAt(b) != c) {
                    b++;
                }
                if (b > 35) {
                    return -3;
                }
                luhnNumber[p] = (char) ('0' + b / 10);
                luhnNumber[p + 1] = (char) ('0' + b % 10);
                p += 2;
            }
        }
        
        result = luhntest(new String(luhnNumber, 0, p));
        if (result != 0) {
            return -4;
        }
        
        return 0;
    }
    
    public static int luhntest(String testNumber) {
        int checksum = 0;
        int length = testNumber.length();
        
        for (int i = length - 1; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(testNumber.charAt(i));
            checksum += digit;
        }
        
        for (int i = length - 2; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(testNumber.charAt(i));
            checksum += digit;
            checksum += digit;
            if (digit >= 5) {
                checksum -= 9;
            }
        }
        
        if (checksum % 10 == 0) {
            return 0;
        } else {
            return -1;
        }
    }
    
    public static void displayISINtestResult(int result) {
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
}