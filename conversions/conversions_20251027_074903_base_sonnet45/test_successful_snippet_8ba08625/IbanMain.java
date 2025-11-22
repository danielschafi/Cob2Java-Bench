import java.math.BigInteger;

public class IbanMain {
    private String iban;
    private char ibanFlag;

    public static void main(String[] args) {
        IbanMain program = new IbanMain();
        program.mainLine();
    }

    private void mainLine() {
        iban = "GB82 WEST 1234 5698 7654 32";
        displayValidity();

        iban = "GB82 TEST 1234 5698 7654 32";
        displayValidity();
    }

    private void displayValidity() {
        ValidateIban validator = new ValidateIban();
        ibanFlag = validator.validate(iban);
        if (ibanFlag == 'Y') {
            System.out.println(iban.trim() + " is valid.");
        } else {
            System.out.println(iban.trim() + " is not valid.");
        }
    }
}

class ValidateIban {
    private static final String COUNTRY_LENGTHS_DATA = 
        "AD24AE23AL28AT20AZ28BA20BE16" +
        "BG22BH22BR29CH21CR21CY28CZ24DE22DK18DO28EE20ES24FI18FO18F" +
        "R27GB22GE22GI23GL18GR27GT28HR21HU28IE22IL23IS26IT27KW30KZ" +
        "20LB28LI21LT20LU20LV21MC27MD24ME22MK19MR27MT31MU30NL18NO1" +
        "5PK24PL28PS29PT25RO24RS22SA24SE24SI19SK24SM27TN24TR26VG24";

    private static class CountryLength {
        String countryCode;
        int countryLen;

        CountryLength(String code, int len) {
            this.countryCode = code;
            this.countryLen = len;
        }
    }

    private CountryLength[] countryLengths;

    public ValidateIban() {
        initializeCountryLengths();
    }

    private void initializeCountryLengths() {
        countryLengths = new CountryLength[64];
        for (int i = 0; i < 64; i++) {
            int pos = i * 4;
            String code = COUNTRY_LENGTHS_DATA.substring(pos, pos + 2);
            int len = Integer.parseInt(COUNTRY_LENGTHS_DATA.substring(pos + 2, pos + 4));
            countryLengths[i] = new CountryLength(code, len);
        }
    }

    public char validate(String iban) {
        iban = iban.toUpperCase();
        iban = removeSpaces(iban);

        int len = iban.indexOf(' ') >= 0 ? iban.indexOf(' ') : iban.length();
        
        String countryCode = iban.substring(0, 2);
        boolean found = false;
        int expectedLen = 0;

        for (CountryLength cl : countryLengths) {
            if (cl.countryCode.equals(countryCode)) {
                found = true;
                expectedLen = cl.countryLen;
                break;
            }
        }

        if (!found) {
            return 'N';
        }

        if (expectedLen != len) {
            return 'N';
        }

        iban = createIbanNumber(len, iban);

        BigInteger ibanNumber = new BigInteger(iban.trim());
        BigInteger remainder = ibanNumber.mod(BigInteger.valueOf(97));

        if (remainder.equals(BigInteger.ONE)) {
            return 'Y';
        } else {
            return 'N';
        }
    }

    private String removeSpaces(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                result.append(str.charAt(i));
            }
        }
        while (result.length() < 50) {
            result.append(' ');
        }
        return result.toString();
    }

    private String createIbanNumber(int len, String iban) {
        String firstFour = iban.substring(0, 4);
        String rest = iban.substring(4, len);
        iban = rest + firstFour;

        StringBuilder ibanNum = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char c = iban.charAt(i);
            if (c == ' ') {
                break;
            }
            if (Character.isDigit(c)) {
                ibanNum.append(c);
            } else {
                int letterNum = c - 'A' + 10;
                ibanNum.append(letterNum);
            }
        }

        while (ibanNum.length() < 50) {
            ibanNum.append(' ');
        }

        return ibanNum.toString();
    }
}