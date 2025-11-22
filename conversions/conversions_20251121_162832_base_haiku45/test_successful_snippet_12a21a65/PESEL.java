```java
import java.util.Scanner;

public class PESEL {
    private String wsPesel;
    private int wsYear;
    private int wsMonth;
    private int wsDay;
    private int wsSeq;
    private int wsGen;
    private int wsPeselCheckDigit;
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
        done();
    }

    private void done() {
        System.exit(0);
    }

    private void getPesel() {
        System.out.println("Podaj PESEL:");
        Scanner scanner = new Scanner(System.in);
        wsPesel = scanner.nextLine().trim();
        
        if (wsPesel.length() >= 11) {
            wsYear = Integer.parseInt(wsPesel.substring(0, 2));
            wsMonth = Integer.parseInt(wsPesel.substring(2, 4));
            wsDay = Integer.parseInt(wsPesel.substring(4, 6));
            wsSeq = Integer.parseInt(wsPesel.substring(6, 9));
            wsGen = Integer.parseInt(wsPesel.substring(9, 10));
            wsPeselCheckDigit = Integer.parseInt(wsPesel.substring(10, 11));
        }
    }

    private void calcChecksum() {
        wsCheckTotal = 0;
        
        int[] weights = {9, 7, 3, 1, 9, 7, 3, 1, 9, 7};
        
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(wsPesel.charAt(i));
            wsCheckTotal += digit * weights[i];
        }
        
        wsCheckDigit = wsCheckTotal % 10;
    }

    private void validateChecksum() {
        if (wsCheckDigit != wsPeselCheckDigit) {
            System.out.println("Niepoprawny PESEL");
            done();
        }
    }

    private void calcAge() {
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

    private void showType() {
        boolean isWoman = (wsGen == 0 || wsGen == 2 || wsGen == 4 || wsGen == 6 || wsGen == 8);
        boolean isChild = (wsAge >= 0 && wsAge <= 11);
        boolean isTeen = (wsAge >= 12 && wsAge <= 17);
        boolean isAdult = (wsAge >= 18 && wsAge <= 99);
        
        if (isWoman) {
            if (isChild) {
                System.out.println("Dziewczynka");
            } else if (isTeen) {
                System.out.println("Dziewczyna");
            } else if (isAdult) {
                System.out.println("Kobieta");
            }
        } else {
            if (isChild) {
                System.out.println("Chlopiec");
            } else if (isTeen) {
                System.out.println("Chlopak");
            } else if (isAdult) {
                System.out.println("Mezczyzna");
            }
        }
    }

    private void showAge() {
        System.out.println("Lat:" + String.format("%3d", wsAge));
    }
}