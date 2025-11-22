import java.util.HashMap;
import java.util.Map;

public class IbanMain {
    public static void main(String[] args) {
        String iban = "GB82 WEST 1234 5698 7654 32";
        boolean isValid = validateIban(iban);
        displayValidity(iban, isValid);

        iban = "GB82 TEST 1234 5698 7654 32";
        isValid = validateIban(iban);
        displayValidity(iban, isValid);
    }

    private static void displayValidity(String iban, boolean isValid) {
        if (isValid) {
            System.out.println(iban.trim() + " is valid.");
        } else {
            System.out.println(iban.trim() + " is not valid.");
        }
    }

    private static boolean validateIban(String iban) {
        iban = iban.toUpperCase();
        iban = removeSpaces(iban);

        int len = iban.length();
        Map<String, Integer> countryLengths = new HashMap<>();
        String countryLengthsArea = "AD24AE23AL28AT20AZ28BA20BE16BG22BH22BR29CH21CR21CY28CZ24DE22DK18DO28EE20ES24FI18FO18FR27GB22GE22GI23GL18GR27GT28HR21HU28IE22IL23IS26IT27KW30KZ20LB28LI21LT20LU20LV21MC27MD24ME22MK19MR27MT31MU30NL18NO15PK24PL28PS29PT25RO24RS22SA24SE24SI19SK24SM27TN24TR26VG24";
        for (int i = 0; i < countryLengthsArea.length(); i += 4) {
            String countryCode = countryLengthsArea.substring(i, i + 2);
            int countryLen = Integer.parseInt(countryLengthsArea.substring(i + 2, i + 4));
            countryLengths.put(countryCode, countryLen);
        }

        if (!countryLengths.containsKey(iban.substring(0, 2)) || countryLengths.get(iban.substring(0, 2)) != len) {
            return false;
        }

        iban = createIbanNumber(len, iban);

        return mod97(iban) == 1;
    }

    private static String removeSpaces(String str) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c != ' ') {
                result.append(c);
            }
        }
        return result.toString();
    }

    private static String createIbanNumber(int len, String iban) {
        String firstFour = iban.substring(0, 4);
        iban = iban.substring(4);
        iban = iban + firstFour;

        StringBuilder ibanNum = new StringBuilder();
        for (char c : iban.toCharArray()) {
            if (Character.isDigit(c)) {
                ibanNum.append(c);
            } else {
                int letterNum = c - 'A' + 10;
                ibanNum.append(letterNum);
            }
        }
        return ibanNum.toString();
    }

    private static int mod97(String number) {
        long remainder = 0;
        for (int i = 0; i < number.length(); i++) {
            remainder = (remainder * 10 + (number.charAt(i) - '0')) % 97;
        }
        return (int) remainder;
    }
}