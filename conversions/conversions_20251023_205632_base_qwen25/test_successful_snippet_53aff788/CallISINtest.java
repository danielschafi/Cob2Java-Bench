import java.util.Arrays;

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

    public static void displayISINtestResult(int ISINtestResult) {
        switch (ISINtestResult) {
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
                System.out.println(" invalid return code " + ISINtestResult);
                break;
        }
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
        int p = 0;
        StringBuilder luhnNumber = new StringBuilder();
        int luhntestResult;

        System.out.print(" " + testNumber);

        if (testNumber.length() != 12) {
            return -1;
        }

        if (!Arrays.asList(countryCodes.split("(?<=\\G.{2})")).contains(testNumber.substring(0, 2))) {
            return -2;
        }

        for (int i = 0; i < 12; i++) {
            char c = testNumber.charAt(i);
            if (c >= '0' && c <= '9') {
                luhnNumber.append(c);
                p++;
            } else {
                int b = base36Digits.indexOf(c);
                if (b == -1) {
                    return -3;
                }
                luhnNumber.append(String.format("%02d", b));
                p += 2;
            }
        }

        luhntestResult = luhntest(luhnNumber.toString().substring(0, p));
        if (luhntestResult != 0) {
            return -4;
        }

        return 0;
    }

    public static int luhntest(String testNumber) {
        int i;
        int checkSum = 0;

        System.out.print(" " + testNumber);

        i = testNumber.length();
        while (i > 0) {
            checkSum += Character.getNumericValue(testNumber.charAt(i - 1));
            i -= 2;
        }

        System.out.print(" " + checkSum);

        i = testNumber.length() - 1;
        while (i > 0) {
            int digit = Character.getNumericValue(testNumber.charAt(i - 1));
            checkSum += digit;
            checkSum += digit;
            if (digit >= 5) {
                checkSum -= 9;
            }
            i -= 2;
        }

        System.out.print(" " + checkSum);

        return (checkSum % 10 == 0) ? 0 : -1;
    }
}