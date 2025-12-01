import java.math.BigInteger;

public class IbanValidator {
    static class CountryLength {
        String code;
        int length;
        
        CountryLength(String code, int length) {
            this.code = code;
            this.length = length;
        }
    }
    
    static CountryLength[] countryLengths = {
        new CountryLength("AD", 24), new CountryLength("AE", 23), new CountryLength("AL", 28),
        new CountryLength("AT", 20), new CountryLength("AZ", 28), new CountryLength("BA", 20),
        new CountryLength("BE", 16), new CountryLength("BG", 22), new CountryLength("BH", 22),
        new CountryLength("BR", 29), new CountryLength("CH", 21), new CountryLength("CR", 21),
        new CountryLength("CY", 28), new CountryLength("CZ", 24), new CountryLength("DE", 22),
        new CountryLength("DK", 18), new CountryLength("DO", 28), new CountryLength("EE", 20),
        new CountryLength("ES", 24), new CountryLength("FI", 18), new CountryLength("FO", 18),
        new CountryLength("FR", 27), new CountryLength("GB", 22), new CountryLength("GE", 22),
        new CountryLength("GI", 23), new CountryLength("GL", 18), new CountryLength("GR", 27),
        new CountryLength("GT", 28), new CountryLength("HR", 21), new CountryLength("HU", 28),
        new CountryLength("IE", 22), new CountryLength("IL", 23), new CountryLength("IS", 26),
        new CountryLength("IT", 27), new CountryLength("KW", 30), new CountryLength("KZ", 20),
        new CountryLength("LB", 28), new CountryLength("LI", 21), new CountryLength("LT", 20),
        new CountryLength("LU", 20), new CountryLength("LV", 21), new CountryLength("MC", 27),
        new CountryLength("MD", 24), new CountryLength("ME", 22), new CountryLength("MK", 19),
        new CountryLength("MR", 27), new CountryLength("MT", 31), new CountryLength("MU", 30),
        new CountryLength("NL", 18), new CountryLength("NO", 15), new CountryLength("PK", 24),
        new CountryLength("PL", 28), new CountryLength("PS", 29), new CountryLength("PT", 25),
        new CountryLength("RO", 24), new CountryLength("RS", 22), new CountryLength("SA", 24),
        new CountryLength("SE", 24), new CountryLength("SI", 19), new CountryLength("SK", 24),
        new CountryLength("SM", 27), new CountryLength("TN", 24), new CountryLength("TR", 26),
        new CountryLength("VG", 24)
    };
    
    public static void main(String[] args) {
        String iban1 = "GB82 WEST 1234 5698 7654 32";
        boolean valid1 = validateIban(iban1);
        System.out.println(iban1.trim() + (valid1 ? " is valid." : " is not valid."));
        
        String iban2 = "GB82 TEST 1234 5698 7654 32";
        boolean valid2 = validateIban(iban2);
        System.out.println(iban2.trim() + (valid2 ? " is valid." : " is not valid."));
    }
    
    static boolean validateIban(String iban) {
        iban = iban.toUpperCase();
        iban = removeSpaces(iban);
        
        int len = 0;
        for (int i = 0; i < iban.length(); i++) {
            if (iban.charAt(i) == ' ') break;
            len++;
        }
        
        String countryCode = iban.substring(0, 2);
        int expectedLen = -1;
        
        for (CountryLength cl : countryLengths) {
            if (cl.code.equals(countryCode)) {
                expectedLen = cl.length;
                break;
            }
        }
        
        if (expectedLen == -1 || expectedLen != len) {
            return false;
        }
        
        iban = createIbanNumber(len, iban);
        
        BigInteger ibanNum = new BigInteger(iban);
        return ibanNum.mod(BigInteger.valueOf(97)).equals(BigInteger.ONE);
    }
    
    static String removeSpaces(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }
    
    static String createIbanNumber(int len, String iban) {
        String firstFour = iban.substring(0, 4);
        String rest = iban.substring(4);
        iban = rest + firstFour;
        
        StringBuilder ibanNum = new StringBuilder();
        
        for (int i = 0; i < len; i++) {
            if (i >= iban.length() || iban.charAt(i) == ' ') break;
            
            char c = iban.charAt(i);
            if (Character.isDigit(c)) {
                ibanNum.append(c);
            } else {
                int letterNum = c - 'A' + 10;
                ibanNum.append(letterNum);
            }
        }
        
        return ibanNum.toString();
    }
}