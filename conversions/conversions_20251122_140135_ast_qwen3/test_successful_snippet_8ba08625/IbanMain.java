public class IbanMain {
    public static void main(String[] args) {
        String iban = "GB82 WEST 1234 5698 7654 32";
        boolean isValid = validateIban(iban);
        System.out.println(trim(iban) + " is " + (isValid ? "" : "not ") + "valid.");

        iban = "GB82 TEST 1234 5698 7654 32";
        isValid = validateIban(iban);
        System.out.println(trim(iban) + " is " + (isValid ? "" : "not ") + "valid.");
    }

    private static boolean validateIban(String iban) {
        iban = iban.toUpperCase();
        iban = removeSpaces(iban);

        int len = iban.length();
        String countryCode = iban.substring(0, 2);
        int expectedLength = getCountryLength(countryCode);
        if (expectedLength != len) {
            return false;
        }

        iban = createIbanNumber(len, iban);

        long number = Long.parseLong(iban);
        return (number % 97) == 1;
    }

    private static String removeSpaces(String str) {
        StringBuilder result = new StringBuilder();
        int offset = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                offset++;
            } else if (offset != 0) {
                result.append(c);
            } else {
                result.append(c);
            }
        }

        while (result.length() < str.length()) {
            result.append(' ');
        }

        return result.toString();
    }

    private static String createIbanNumber(int len, String iban) {
        String firstFour = iban.substring(0, 4);
        iban = iban.substring(4);
        iban = iban + firstFour;

        StringBuilder ibanNum = new StringBuilder();
        int digitNum = 1;

        for (int i = 0; i < len; i++) {
            char c = iban.charAt(i);
            if (Character.isDigit(c)) {
                ibanNum.append(c);
                digitNum++;
            } else {
                int letterNum = (int) c - (int) 'A' + 10;
                ibanNum.append(String.format("%02d", letterNum));
                digitNum += 2;
            }
        }

        return ibanNum.toString();
    }

    private static int getCountryLength(String countryCode) {
        String countryLengthsArea = "AD24AE23AL28AT20AZ28BA20BE16BG22BH22BR29CH21CR21CY28CZ24DE22DK18DO28EE20ES24FI18FO18FR27GB22GE22GI23GL18GR27GT28HR21HU28IE22IL23IS26IT27KW30KZ20LB28LI21LT20LU20LV21MC27MD24ME22MK19MR27MT31MU30NL18NO15PK24PL28PS29PT25RO24RS22SA24SE24SI19SK24SM27TN24TR26VG24";
        
        for (int i = 0; i < countryLengthsArea.length(); i += 4) {
            String code = countryLengthsArea.substring(i, i + 2);
            int length = Integer.parseInt(countryLengthsArea.substring(i + 2, i + 4));
            if (code.equals(countryCode)) {
                return length;
            }
        }
        
        return -1;
    }

    private static String trim(String str) {
        return str.trim();
    }
}