import java.util.Arrays;

public class IbanMain {
    private static final String[] COUNTRY_LENGTHS_AREA = {
        "AD24", "AE23", "AL28", "AT20", "AZ28", "BA20", "BE16", "BG22", "BH22", "BR29", "CH21", "CR21", "CY28", "CZ24", "DE22",
        "DK18", "DO28", "EE20", "ES24", "FI18", "FO18", "FR27", "GB22", "GE22", "GI23", "GL18", "GR27", "GT28", "HR21", "HU28",
        "IE22", "IL23", "IS26", "IT27", "KW30", "KZ20", "LB28", "LI21", "LT20", "LU20", "LV21", "MC27", "MD24", "ME22", "MK19",
        "MR27", "MT31", "MU30", "NL18", "NO15", "PK24", "PL28", "PS29", "PT25", "RO24", "RS22", "SA24", "SE24", "SI19", "SK24",
        "SM27", "TN24", "TR26", "VG24"
    };

    public static void main(String[] args) {
        String iban = "GB82 WEST 1234 5698 7654 32";
        boolean isValid = validateIban(iban);
        System.out.println(iban.trim() + (isValid ? " is valid." : " is not valid."));

        iban = "GB82 TEST 1234 5698 7654 32";
        isValid = validateIban(iban);
        System.out.println(iban.trim() + (isValid ? " is valid." : " is not valid."));
    }

    private static boolean validateIban(String iban) {
        iban = iban.toUpperCase().replaceAll("\\s", "");
        int len = iban.length();

        for (String countryLength : COUNTRY_LENGTHS_AREA) {
            if (iban.startsWith(countryLength.substring(0, 2)) && len == Integer.parseInt(countryLength.substring(2))) {
                iban = createIbanNumber(len, iban);
                return Integer.parseInt(iban) % 97 == 1;
            }
        }
        return false;
    }

    private static String createIbanNumber(int len, String iban) {
        String firstFour = iban.substring(0, 4);
        iban = iban.substring(4) + firstFour;
        StringBuilder ibanNum = new StringBuilder();

        for (char c : iban.toCharArray()) {
            if (Character.isDigit(c)) {
                ibanNum.append(c);
            } else {
                ibanNum.append((int) c - 55);
            }
        }

        return ibanNum.toString();
    }
}