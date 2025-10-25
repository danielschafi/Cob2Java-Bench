import java.util.Scanner;

public class PESEL {
    private String wsPESEL;
    private int wsYear;
    private int wsMonth;
    private int wsDay;
    private int wsSeq;
    private int wsGen;
    private int wsPESELCheckDigit;
    private int wsCheckPartial;
    private int wsCheckTotal;
    private int wsCheckDigit;
    private int wsAge;
    private int wsCurYear = 2017;

    public static void main(String[] args) {
        PESEL pesel = new PESEL();
        pesel.mainProcedure();
    }

    public void mainProcedure() {
        getPESEL();
        calcChecksum();
        validateChecksum();
        calcAge();
        showType();
        showAge();
    }

    public void getPESEL() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj PESEL:");
        wsPESEL = scanner.nextLine();
    }

    public void calcChecksum() {
        wsCheckTotal = 0;

        wsCheckPartial = Character.getNumericValue(wsPESEL.charAt(0));
        wsCheckPartial *= 9;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPESEL.charAt(1));
        wsCheckPartial *= 7;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPESEL.charAt(2));
        wsCheckPartial *= 3;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPESEL.charAt(3));
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPESEL.charAt(4));
        wsCheckPartial *= 9;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPESEL.charAt(5));
        wsCheckPartial *= 7;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPESEL.charAt(6));
        wsCheckPartial *= 3;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPESEL.charAt(7));
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPESEL.charAt(8));
        wsCheckPartial *= 9;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Character.getNumericValue(wsPESEL.charAt(9));
        wsCheckPartial *= 7;
        wsCheckTotal += wsCheckPartial;

        wsCheckDigit = wsCheckTotal % 10;
    }

    public void validateChecksum() {
        wsPESELCheckDigit = Character.getNumericValue(wsPESEL.charAt(10));
        if (wsCheckDigit != wsPESELCheckDigit) {
            System.out.println("Niepoprawny PESEL");
            System.exit(0);
        }
    }

    public void calcAge() {
        wsYear = Integer.parseInt(wsPESEL.substring(0, 2));
        wsMonth = Integer.parseInt(wsPESEL.substring(2, 4));
        wsDay = Integer.parseInt(wsPESEL.substring(4, 6));
        wsSeq = Integer.parseInt(wsPESEL.substring(6, 9));
        wsGen = Integer.parseInt(wsPESEL.substring(9, 10));

        wsAge = wsCurYear;

        if (wsMonth >= 81 && wsMonth <= 92) {
            wsAge -= 1800;
            wsMonth -= 80;
        } else if (wsMonth >= 1 && wsMonth <= 12) {
            wsAge -= 1900;
        } else if (wsMonth >= 21 && wsMonth <= 32) {
            wsAge -= 2000;
            wsMonth -= 20;
        }

        wsAge -= wsYear;
    }

    public void showType() {
        if (wsGen % 2 == 0) {
            if (wsAge >= 0 && wsAge <= 11) {
                System.out.println("Dziewczynka");
            } else if (wsAge >= 12 && wsAge <= 17) {
                System.out.println("Dziewczyna");
            } else if (wsAge >= 18 && wsAge <= 99) {
                System.out.println("Kobieta");
            }
        } else {
            if (wsAge >= 0 && wsAge <= 11) {
                System.out.println("Chlopiec");
            } else if (wsAge >= 12 && wsAge <= 17) {
                System.out.println("Chlopak");
            } else if (wsAge >= 18 && wsAge <= 99) {
                System.out.println("Mezczyzna");
            }
        }
    }

    public void showAge() {
        System.out.printf("Lat: %03d%n", wsAge);
    }
}