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
        String countryCodeValues = "ADAEAFAGAIALAMAOAQARASATAUAWAXAZBABBBDBEBFBGBHBIBJBLBMBNBOBQBRBSBTBVBWBYBZCACCCDCFCGCHCICKCLCMCNCOCRCUCVCWCXCYCZDEDJDKDMDODZECEE" +
                                 "EGEHERESETFIFJFKFMFOFRGAGBGDGEGFGGGHGIGLGMGNGPGQGRGSGTGUGWGYHKHMHNHRHTHUIDIEILIMINIOIQIRISITJEJMJOJPKEKGKHKIKMKNKPKRKWKYKZLALBLC" +
                                 "LILKLRLSLTLULVLYMAMCMDMEMFMGMHMKMLMMMNMOMPMQMRMSMTMUMVMWMXMYMZNA" +
                                 "NCNENFNGNINLNONPNRNUNZOMPAPEPFPGPHPKPLPMPNPRPSPTPWPYQARERORSRURW" +
                                 "SASBSCSDSESGSHSISJSKSLSMSNSOSRSSSTSVSXSYSZTCTDTFTGTHTJTKTLTMTNTO" +
                                 "TRTTTVTWTZUAUGUMUSUYUZVAVCVEVGVIVNVUWFWSYEYTZAZMZW";
        String[] countryCodes = countryCodeValues.split("(?<=\\G.{2})");
        Arrays.sort(countryCodes);

        String base36Digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int p = 0;
        StringBuilder luhnNumber = new StringBuilder();

        if (testNumber.length() != 12) {
            return -1;
        }

        int index = Arrays.binarySearch(countryCodes, testNumber.substring(0, 2));
        if (index < 0) {
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

        int luhntestResult = luhntest(luhnNumber.toString());
        if (luhntestResult != 0) {
            return -4;
        }

        return 0;
    }

    public static int luhntest(String testNumber) {
        int checkSum = 0;

        for (int i = testNumber.length() - 1; i >= 0; i -= 2) {
            checkSum += Character.getNumericValue(testNumber.charAt(i));
        }

        for (int i = testNumber.length() - 2; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(testNumber.charAt(i));
            checkSum += digit;
            checkSum += digit;
            if (digit >= 5) {
                checkSum -= 9;
            }
        }

        return (checkSum % 10 == 0) ? 0 : -1;
    }
}