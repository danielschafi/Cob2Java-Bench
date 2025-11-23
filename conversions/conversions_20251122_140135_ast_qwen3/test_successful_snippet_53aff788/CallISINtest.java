public class CallISINtest {
    public static void main(String[] args) {
        int ISINtestResult;
        
        System.out.print("should be valid ");
        ISINtestResult = ISINtest("US0378331005");
        displayISINtestResult(ISINtestResult);
        
        System.out.print("should not be valid ");
        ISINtestResult = ISINtest("US0373831005");
        displayISINtestResult(ISINtestResult);
        
        System.out.print("should not be valid ");
        ISINtestResult = ISINtest("U50378331005");
        displayISINtestResult(ISINtestResult);
        
        System.out.print("should not be valid ");
        ISINtestResult = ISINtest("US03378331005");
        displayISINtestResult(ISINtestResult);
        
        System.out.print("should be valid ");
        ISINtestResult = ISINtest("AU0000XVGZA3");
        displayISINtestResult(ISINtestResult);
        
        System.out.print("should be valid ");
        ISINtestResult = ISINtest("AU0000VXGZA3");
        displayISINtestResult(ISINtestResult);
        
        System.out.print("should be valid ");
        ISINtestResult = ISINtest("FR0000988040");
        displayISINtestResult(ISINtestResult);
    }
    
    public static int ISINtest(String testNumber) {
        String countryCodeValues = "ADAEAFAGAIALAMAOAQARASATAUAWAXAZBABBBDBEBFBGBHBIBJBLBMBNBOBQBRBS" +
                                  "BTBVBWBYBZCACCCDCFCGCHCICKCLCMCNCOCRCUCVCWCXCYCZDEDJDKDMDODZECEE" +
                                  "EGEHERESETFIFJFKFMFOFRGAGBGDGEGFGGGHGIGLGMGNGPGQGRGSGTGUGWGYHKHM" +
                                  "HNHRHTHUIDIEILIMINIOIQIRISITJEJMJOJPKEKGKHKIKMKNKPKRKWKYKZLALBLC" +
                                  "LILKLRLSLTLULVLYMAMCMDMEMFMGMHMKMLMMMNMOMPMQMRMSMTMUMVMWMXMYMZNA" +
                                  "NCNENFNGNINLNONPNRNUNZOMPAPEPFPGPHPKPLPMPNPRPSPTPWPYQARERORSRURW" +
                                  "SASBSCSDSESGSHSISJSKSLSMSNSOSRSSSTSVSXSYSZTCTDTFTGTHTJTKTLTMTNTO" +
                                  "TRTTTVTWTZUAUGUMUSUYUZVAVCVEVGVIVNVUWFWSYEYTZAZMZW";
        
        String[] countryCodes = new String[249];
        for (int i = 0; i < 249; i++) {
            countryCodes[i] = countryCodeValues.substring(i * 2, i * 2 + 2);
        }
        
        if (testNumber.length() != 12) {
            return -1;
        }
        
        boolean foundCountryCode = false;
        for (String countryCode : countryCodes) {
            if (testNumber.substring(0, 2).equals(countryCode)) {
                foundCountryCode = true;
                break;
            }
        }
        
        if (!foundCountryCode) {
            return -2;
        }
        
        StringBuilder luhnNumber = new StringBuilder();
        int p = 0;
        
        for (int i = 0; i < testNumber.length(); i++) {
            char c = testNumber.charAt(i);
            if (c >= '0' && c <= '9') {
                luhnNumber.append(c);
                p++;
            } else {
                String base36Digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                int b = 9;
                while (b <= 35) {
                    if (base36Digits.charAt(b) == c) {
                        break;
                    }
                    b++;
                }
                if (b > 35) {
                    return -3;
                }
                luhnNumber.append(String.format("%02d", b));
                p += 2;
            }
        }
        
        String luhnNumberStr = luhnNumber.toString().substring(0, p);
        int luhntestResult = luhntest(luhnNumberStr);
        if (luhntestResult != 0) {
            return -4;
        }
        
        return 0;
    }
    
    public static int luhntest(String testNumber) {
        int checkSum = 0;
        int length = testNumber.length();
        
        // First pass: add every other digit starting from the rightmost
        for (int i = length; i >= 1; i -= 2) {
            int digit = Character.getNumericValue(testNumber.charAt(i - 1));
            checkSum += digit;
        }
        
        // Second pass: add every other digit starting from second-to-last,
        // doubling them and subtracting 9 if they're greater than 4
        for (int i = length - 1; i >= 1; i -= 2) {
            int digit = Character.getNumericValue(testNumber.charAt(i - 1));
            checkSum += digit;
            checkSum += digit;
            if (digit >= 5) {
                checkSum -= 9;
            }
        }
        
        return (checkSum % 10 == 0) ? 0 : -1;
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
                break;
        }
    }
}