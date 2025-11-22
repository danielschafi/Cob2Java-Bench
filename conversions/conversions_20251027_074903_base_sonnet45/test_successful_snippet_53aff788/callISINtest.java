import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class callISINtest {
    
    public static void main(String[] args) {
        ISINtest isinTest = new ISINtest();
        int result;
        
        System.out.print("should be valid ");
        result = isinTest.validate("US0378331005");
        displayISINtestResult(result);
        
        System.out.print("should not be valid ");
        result = isinTest.validate("US0373831005");
        displayISINtestResult(result);
        
        System.out.print("should not be valid ");
        result = isinTest.validate("U50378331005");
        displayISINtestResult(result);
        
        System.out.print("should not be valid ");
        result = isinTest.validate("US03378331005");
        displayISINtestResult(result);
        
        System.out.print("should be valid ");
        result = isinTest.validate("AU0000XVGZA3");
        displayISINtestResult(result);
        
        System.out.print("should be valid ");
        result = isinTest.validate("AU0000VXGZA3");
        displayISINtestResult(result);
        
        System.out.print("should be valid ");
        result = isinTest.validate("FR0000988040");
        displayISINtestResult(result);
    }
    
    private static void displayISINtestResult(int result) {
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

class ISINtest {
    private static final Set<String> COUNTRY_CODES = new HashSet<>(Arrays.asList(
        "AD","AE","AF","AG","AI","AL","AM","AO","AQ","AR","AS","AT","AU","AW","AX","AZ","BA","BB","BD","BE","BF","BG","BH","BI","BJ","BL","BM","BN","BO","BQ","BR","BS",
        "BT","BV","BW","BY","BZ","CA","CC","CD","CF","CG","CH","CI","CK","CL","CM","CN","CO","CR","CU","CV","CW","CX","CY","CZ","DE","DJ","DK","DM","DO","DZ","EC","EE",
        "EG","EH","ER","ES","ET","FI","FJ","FK","FM","FO","FR","GA","GB","GD","GE","GF","GG","GH","GI","GL","GM","GN","GP","GQ","GR","GS","GT","GU","GW","GY","HK","HM",
        "HN","HR","HT","HU","ID","IE","IL","IM","IN","IO","IQ","IR","IS","IT","JE","JM","JO","JP","KE","KG","KH","KI","KM","KN","KP","KR","KW","KY","KZ","LA","LB","LC",
        "LI","LK","LR","LS","LT","LU","LV","LY","MA","MC","MD","ME","MF","MG","MH","MK","ML","MM","MN","MO","MP","MQ","MR","MS","MT","MU","MV","MW","MX","MY","MZ","NA",
        "NC","NE","NF","NG","NI","NL","NO","NP","NR","NU","NZ","OM","PA","PE","PF","PG","PH","PK","PL","PM","PN","PR","PS","PT","PW","PY","QA","RE","RO","RS","RU","RW",
        "SA","SB","SC","SD","SE","SG","SH","SI","SJ","SK","SL","SM","SN","SO","SR","SS","ST","SV","SX","SY","SZ","TC","TD","TF","TG","TH","TJ","TK","TL","TM","TN","TO",
        "TR","TT","TV","TW","TZ","UA","UG","UM","US","UY","UZ","VA","VC","VE","VG","VI","VN","VU","WF","WS","YE","YT","ZA","ZM","ZW"
    ));
    
    private static final String BASE36_DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public int validate(String testNumber) {
        System.out.print(" " + testNumber);
        
        if (testNumber.length() != 12) {
            return -1;
        }
        
        String countryCode = testNumber.substring(0, 2);
        if (!COUNTRY_CODES.contains(countryCode)) {
            return -2;
        }
        
        StringBuilder luhnNumber = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            char c = testNumber.charAt(i);
            if (c >= '0' && c <= '9') {
                luhnNumber.append(c);
            } else {
                int b = BASE36_DIGITS.indexOf(c);
                if (b == -1) {
                    return -3;
                }
                luhnNumber.append(String.format("%02d", b));
            }
        }
        
        Luhntest luhntest = new Luhntest();
        int luhnResult = luhntest.validate(luhnNumber.toString());
        if (luhnResult != 0) {
            return -4;
        }
        
        return 0;
    }
}

class Luhntest {
    public int validate(String testNumber) {
        System.out.print(" " + testNumber);
        int checkSum = 0;
        
        for (int i = testNumber.length() - 1; i >= 0; i -= 2) {
            checkSum += Character.getNumericValue(testNumber.charAt(i));
        }
        System.out.print(" " + checkSum);
        
        for (int i = testNumber.length() - 2; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(testNumber.charAt(i));
            checkSum += digit;
            checkSum += digit;
            if (testNumber.charAt(i) >= '5') {
                checkSum -= 9;
            }
        }
        System.out.print(" " + checkSum);
        
        if (checkSum % 10 == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}