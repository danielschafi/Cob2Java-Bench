import java.util.HashMap;
import java.util.Map;

public class IbanMain {
    private static String iban;
    private static String ibanFlag;

    public static void main(String[] args) {
        iban = "GB82 WEST 1234 5698 7654 32";
        displayValidity();

        iban = "GB82 TEST 1234 5698 7654 32";
        displayValidity();
    }

    private static void displayValidity() {
        ibanFlag = validateIban(iban);
        if ("Y".equals(ibanFlag)) {
            System.out.println(iban.trim() + " is valid.");
        } else {
            System.out.println(iban.trim() + " is not valid.");
        }
    }

    private static String validateIban(String ibanInput) {
        String countryLengthsStr = "AD24AE23AL28AT20AZ28BA20BE16" +
            "BG22BH22BR29CH21CR21CY28CZ24DE22DK18DO28EE20ES24FI18FO18F" +
            "R27GB22GE22GI23GL18GR27GT28HR21HU28IE22IL23IS26IT27KW30KZ" +
            "20LB28LI21LT20LU20LV21MC27MD24ME22MK19MR27MT31MU30NL18NO1" +
            "5PK24PL28PS29PT25RO24RS22SA24SE24SI19SK24SM27TN24TR26VG24";

        Map<String, Integer> countryLengths = new HashMap<>();
        for (int i = 0; i < countryLengthsStr.length(); i += 4) {
            String country = countryLengthsStr.substring(i, i + 2);
            int length = Integer.parseInt(countryLengthsStr.substring(i + 2, i + 4));
            countryLengths.put(country, length);
        }

        ibanInput = ibanInput.toUpperCase();
        ibanInput = removeSpaces(ibanInput);

        int len = ibanInput.length();
        String countryCode = ibanInput.substring(0, 2);

        if (!countryLengths.containsKey(countryCode)) {
            return "N";
        }

        if (countryLengths.get(countryCode) != len) {
            return "N";
        }

        String ibanNumber = createIbanNumber(len, ibanInput);

        if (mod97(ibanNumber) == 1) {
            return "Y";
        } else {
            return "N";
        }
    }

    private static String removeSpaces(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    private static String createIbanNumber(int len, String ibanInput) {
        String firstFour = ibanInput.substring(0, 4);
        String rest = ibanInput.substring(4);
        String rearranged = rest + firstFour;

        StringBuilder ibanNum = new StringBuilder();
        for (int i = 0; i < rearranged.length() && i < len; i++) {
            char c = rearranged.charAt(i);
            if (Character.isDigit(c)) {
                ibanNum.append(c);
            } else if (Character.isLetter(c)) {
                int letterNum = c - 'A' + 10;
                ibanNum.append(letterNum);
            }
        }

        return ibanNum.toString();
    }

    private static int mod97(String numStr) {
        int remainder = 0;
        for (int i = 0; i < numStr.length(); i++) {
            char c = numStr.charAt(i);
            if (Character.isDigit(c)) {
                remainder = (remainder * 10 + (c - '0')) % 97;
            }
        }
        return remainder;
    }
}