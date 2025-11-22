import java.util.Scanner;

public class PESEL {
    private String wsPesel;
    private int wsYear;
    private int wsMonth;
    private int wsDay;
    private int wsSeq;
    private int wsGen;
    private int wsPeselCheckDigit;
    private int wsCheckPartial;
    private int wsCheckTotal;
    private int wsCheckDigit;
    private int wsAge;
    private int wsCurYear = 2017;

    public static void main(String[] args) {
        PESEL program = new PESEL();
        program.mainProcedure();
    }

    private void mainProcedure() {
        getPesel();
        calcChecksum();
        validateChecksum();
        calcAge();
        showType();
        showAge();
    }

    private void getPesel() {
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

    private void calcChecksum() {
        wsCheckTotal = 0;
        
        wsCheckPartial = Integer.parseInt(wsPesel.substring(0, 1));
        wsCheckPartial *= 9;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Integer.parseInt(wsPesel.substring(1, 2));
        wsCheckPartial *= 7;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Integer.parseInt(wsPesel.substring(2, 3));
        wsCheckPartial *= 3;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Integer.parseInt(wsPesel.substring(3, 4));
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Integer.parseInt(wsPesel.substring(4, 5));
        wsCheckPartial *= 9;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Integer.parseInt(wsPesel.substring(5, 6));
        wsCheckPartial *= 7;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Integer.parseInt(wsPesel.substring(6, 7));
        wsCheckPartial *= 3;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Integer.parseInt(wsPesel.substring(7, 8));
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Integer.parseInt(wsPesel.substring(8, 9));
        wsCheckPartial *= 9;
        wsCheckTotal += wsCheckPartial;

        wsCheckPartial = Integer.parseInt(wsPesel.substring(9, 10));
        wsCheckPartial *= 7;
        wsCheckTotal += wsCheckPartial;

        wsCheckDigit = wsCheckTotal % 10;
    }

    private void validateChecksum() {
        if (wsCheckDigit != wsPeselCheckDigit) {
            System.out.println("Niepoprawny PESEL");
            System.exit(0);
        }
    }

    private void calcAge() {
        wsAge = wsCurYear;
        if (is1800()) {
            wsAge -= 1800;
        } else if (is1900()) {
            wsAge -= 1900;
        } else if (is2000()) {
            wsAge -= 2000;
        }
        wsAge -= wsYear;
    }

    private void showType() {
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

    private void showAge() {
        System.out.println("Lat:" + String.format("%3d", wsAge).trim());
    }

    private boolean is1800() {
        return wsMonth >= 81 && wsMonth <= 92;
    }

    private boolean is1900() {
        return wsMonth >= 1 && wsMonth <= 12;
    }

    private boolean is2000() {
        return wsMonth >= 21 && wsMonth <= 32;
    }

    private boolean isWoman() {
        return wsGen == 0 || wsGen == 2 || wsGen == 4 || wsGen == 6 || wsGen == 8;
    }

    private boolean isMan() {
        return wsGen == 1 || wsGen == 3 || wsGen == 5 || wsGen == 7 || wsGen == 9;
    }

    private boolean isChild() {
        return wsAge >= 0 && wsAge <= 11;
    }

    private boolean isTeen() {
        return wsAge >= 12 && wsAge <= 17;
    }

    private boolean isAdult() {
        return wsAge >= 18 && wsAge <= 99;
    }
}