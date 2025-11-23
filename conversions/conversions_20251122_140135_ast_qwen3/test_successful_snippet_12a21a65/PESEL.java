import java.util.Scanner;

public class PESEL {
    private static String wsPesel;
    private static int wsYear;
    private static int wsMonth;
    private static int wsDay;
    private static int wsSeq;
    private static int wsGen;
    private static int wsPeselCheckDigit;
    private static int wsCheckPartial;
    private static int wsCheckTotal = 0;
    private static int wsCheckDigit;
    private static int wsAge;
    private static int wsCurYear = 2017;
    private static int wsAgeDisp;

    public static void main(String[] args) {
        getPesel();
        calcChecksum();
        validateChecksum();
        calcAge();
        showType();
        showAge();
    }

    private static void getPesel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj PESEL:");
        wsPesel = scanner.nextLine();
        wsYear = Integer.parseInt(wsPesel.substring(0, 2));
        wsMonth = Integer.parseInt(wsPesel.substring(2, 4));
        wsDay = Integer.parseInt(wsPesel.substring(4, 6));
        wsSeq = Integer.parseInt(wsPesel.substring(6, 9));
        wsGen = Integer.parseInt(wsPesel.substring(9, 10));
        wsPeselCheckDigit = Integer.parseInt(wsPesel.substring(10, 11));
    }

    private static void calcChecksum() {
        wsCheckTotal = 0;
        wsCheckPartial = Character.getNumericValue(wsPesel.charAt(0));
        wsCheckTotal += 9 * wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPesel.charAt(1));
        wsCheckTotal += 7 * wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPesel.charAt(2));
        wsCheckTotal += 3 * wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPesel.charAt(3));
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPesel.charAt(4));
        wsCheckTotal += 9 * wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPesel.charAt(5));
        wsCheckTotal += 7 * wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPesel.charAt(6));
        wsCheckTotal += 3 * wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPesel.charAt(7));
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPesel.charAt(8));
        wsCheckTotal += 9 * wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPesel.charAt(9));
        wsCheckTotal += 7 * wsCheckPartial;

        wsCheckDigit = wsCheckTotal % 10;
    }

    private static void validateChecksum() {
        if (wsCheckDigit != wsPeselCheckDigit) {
            System.out.println("Niepoprawny PESEL");
            System.exit(0);
        }
    }

    private static void calcAge() {
        wsAge = wsCurYear;
        if (wsMonth >= 81 && wsMonth <= 92) {
            wsAge -= 1800;
        } else if (wsMonth >= 1 && wsMonth <= 12) {
            wsAge -= 1900;
        } else if (wsMonth >= 21 && wsMonth <= 32) {
            wsAge -= 2000;
        }
        wsAge -= wsYear;
    }

    private static void showType() {
        if (isWoman()) {
            if (isChild()) {
                System.out.println("Dziewczynka");
            } else if (isTeen()) {
                System.out.println("Dziewczyna");
            } else if (isAdult()) {
                System.out.println("Kobieta");
            }
        } else {
            if (isChild()) {
                System.out.println("Chlopiec");
            } else if (isTeen()) {
                System.out.println("Chlopak");
            } else if (isAdult()) {
                System.out.println("Mezczyzna");
            }
        }
    }

    private static void showAge() {
        wsAgeDisp = wsAge;
        System.out.println("Lat: " + wsAgeDisp);
    }

    private static boolean isWoman() {
        return wsGen == 0 || wsGen == 2 || wsGen == 4 || wsGen == 6 || wsGen == 8;
    }

    private static boolean isMan() {
        return wsGen == 1 || wsGen == 3 || wsGen == 5 || wsGen == 7 || wsGen == 9;
    }

    private static boolean isChild() {
        return wsAge >= 0 && wsAge <= 11;
    }

    private static boolean isTeen() {
        return wsAge >= 12 && wsAge <= 17;
    }

    private static boolean isAdult() {
        return wsAge >= 18 && wsAge <= 99;
    }
}