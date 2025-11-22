import java.util.Scanner;

public class PESEL {
    private String pesel;
    private int checkPartial;
    private int checkTotal;
    private int checkDigit;
    private int age;
    private int curYear = 2017;

    public static void main(String[] args) {
        PESEL peselValidator = new PESEL();
        peselValidator.mainProcedure();
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
        pesel = scanner.nextLine();
    }

    public void calcChecksum() {
        checkTotal = 0;

        checkPartial = Character.getNumericValue(pesel.charAt(0));
        checkTotal += 9 * checkPartial;

        checkPartial = Character.getNumericValue(pesel.charAt(1));
        checkTotal += 7 * checkPartial;

        checkPartial = Character.getNumericValue(pesel.charAt(2));
        checkTotal += 3 * checkPartial;

        checkPartial = Character.getNumericValue(pesel.charAt(3));
        checkTotal += checkPartial;

        checkPartial = Character.getNumericValue(pesel.charAt(4));
        checkTotal += 9 * checkPartial;

        checkPartial = Character.getNumericValue(pesel.charAt(5));
        checkTotal += 7 * checkPartial;

        checkPartial = Character.getNumericValue(pesel.charAt(6));
        checkTotal += 3 * checkPartial;

        checkPartial = Character.getNumericValue(pesel.charAt(7));
        checkTotal += checkPartial;

        checkPartial = Character.getNumericValue(pesel.charAt(8));
        checkTotal += 9 * checkPartial;

        checkPartial = Character.getNumericValue(pesel.charAt(9));
        checkTotal += 7 * checkPartial;

        checkDigit = checkTotal % 10;
    }

    public void validateChecksum() {
        int peselCheckDigit = Character.getNumericValue(pesel.charAt(10));
        if (checkDigit != peselCheckDigit) {
            System.out.println("Niepoprawny PESEL");
            System.exit(0);
        }
    }

    public void calcAge() {
        age = curYear;
        int year = Integer.parseInt(pesel.substring(0, 2));
        int month = Integer.parseInt(pesel.substring(2, 4));

        if (month >= 81 && month <= 92) {
            age -= 1800;
        } else if (month >= 1 && month <= 12) {
            age -= 1900;
        } else if (month >= 21 && month <= 32) {
            age -= 2000;
        }

        age -= year;
    }

    public void showType() {
        int gender = Character.getNumericValue(pesel.charAt(9));
        if (gender % 2 == 0) {
            if (age <= 11) {
                System.out.println("Dziewczynka");
            } else if (age <= 17) {
                System.out.println("Dziewczyna");
            } else {
                System.out.println("Kobieta");
            }
        } else {
            if (age <= 11) {
                System.out.println("Chlopiec");
            } else if (age <= 17) {
                System.out.println("Chlopak");
            } else {
                System.out.println("Mezczyzna");
            }
        }
    }

    public void showAge() {
        System.out.printf("Lat: %02d%n", age);
    }
}