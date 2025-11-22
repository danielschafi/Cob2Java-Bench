public class IbanMain {
    public static void main(String[] args) {
        String iban = "";
        char ibanFlag = 'N';

        iban = "GB82 WEST 1234 5698 7654 32";
        displayValidity(iban, ibanFlag);

        iban = "GB82 TEST 1234 5698 7654 32";
        displayValidity(iban, ibanFlag);
    }

    public static void displayValidity(String iban, char ibanFlag) {
        ibanFlag = validateIban(iban);
        if (ibanFlag == 'Y') {
            System.out.println(trim(iban) + " is valid.");
        } else {
            System.out.println(trim(iban) + " is not valid.");
        }
    }

    public static char validateIban(String iban) {
        iban = upperCase(iban);
        iban = removeSpaces(iban);

        int len = iban.length();
        String countryLengthsArea = "AD24AE23AL28AT20AZ28BA20BE16BG22BH22BR29CH21CR21CY28CZ24DE22DK18DO28EE20ES24FI18FO18FR27GB22GE22GI23GL18GR27GT28HR21HU28IE22IL23IS26IT27KW30KZ20LB28LI21LT20LU20LV21MC27MD24ME22MK19MR27MT31MU30NL18NO15PK24PL28PS29PT25RO24RS22SA24SE24SI19SK24SM27TN24TR26VG24";

        int countryLengthsIdx = 1;
        boolean found = false;
        while (countryLengthsIdx <= 64 && !found) {
            String countryCode = countryLengthsArea.substring((countryLengthsIdx - 1) * 4, (countryLengthsIdx - 1) * 4 + 2);
            int countryLen = Integer.parseInt(countryLengthsArea.substring((countryLengthsIdx - 1) * 4 + 2, (countryLengthsIdx - 1) * 4 + 4));
            if (countryCode.equals(iban.substring(0, 2))) {
                if (countryLen != len) {
                    return 'N';
                }
                found = true;
            }
            countryLengthsIdx++;
        }

        if (!found) {
            return 'N';
        }

        iban = createIbanNumber(len, iban);

        long ibanNum = Long.parseLong(iban);
        if (ibanNum % 97 == 1) {
            return 'Y';
        } else {
            return 'N';
        }
    }

    public static String removeSpaces(String str) {
        StringBuilder result = new StringBuilder(str);
        int offset = 0;

        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == ' ') {
                offset++;
            } else if (offset != 0) {
                result.setCharAt(i - offset, result.charAt(i));
            }
        }

        for (int i = result.length() - offset; i < result.length(); i++) {
            result.setCharAt(i, ' ');
        }

        return result.toString();
    }

    public static String createIbanNumber(int len, String iban) {
        String firstFour = iban.substring(0, 4);
        String remaining = iban.substring(4);
        String newIban = remaining + firstFour;

        StringBuilder ibanNum = new StringBuilder();
        int digitNum = 1;

        for (int i = 0; i < len; i++) {
            char c = newIban.charAt(i);
            if (Character.isDigit(c)) {
                ibanNum.append(c);
                digitNum++;
            } else {
                int letterNum = (int) c - (int) 'A' + 10;
                ibanNum.append(letterNum);
                digitNum += 2;
            }
        }

        return ibanNum.toString();
    }

    public static String trim(String str) {
        return str.replaceAll("^\\s+|\\s+$", "");
    }

    public static String upperCase(String str) {
        return str.toUpperCase();
    }
}