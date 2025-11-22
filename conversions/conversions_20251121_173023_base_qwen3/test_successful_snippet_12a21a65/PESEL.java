import java.util.Scanner;

public class PESEL {
    private static String ws_pesel;
    private static int ws_year;
    private static int ws_month;
    private static int ws_day;
    private static int ws_seq;
    private static int ws_gen;
    private static int ws_pesel_check_digit;
    private static int ws_check_partial;
    private static int ws_check_total = 0;
    private static int ws_check_digit;
    private static int ws_age;
    private static int ws_cur_year = 2017;
    private static int ws_age_disp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Podaj PESEL:");
        ws_pesel = scanner.nextLine();
        
        // Extract parts from PESEL string
        ws_year = Integer.parseInt(ws_pesel.substring(0, 2));
        ws_month = Integer.parseInt(ws_pesel.substring(2, 4));
        ws_day = Integer.parseInt(ws_pesel.substring(4, 6));
        ws_seq = Integer.parseInt(ws_pesel.substring(6, 9));
        ws_gen = Integer.parseInt(ws_pesel.substring(9, 10));
        ws_pesel_check_digit = Integer.parseInt(ws_pesel.substring(10, 11));
        
        calcChecksum();
        validateChecksum();
        calcAge();
        showType();
        showAge();
    }
    
    private static void calcChecksum() {
        // PESEL: [ABCDEFGHIJK]
        // K=(9*A + 7*B + 3*C + D + 9*E + 7*F + 3*G + H + 9*I + 7*J) MOD 10
        
        ws_check_partial = Character.getNumericValue(ws_pesel.charAt(0));
        ws_check_partial *= 9;
        ws_check_total += ws_check_partial;
        
        ws_check_partial = Character.getNumericValue(ws_pesel.charAt(1));
        ws_check_partial *= 7;
        ws_check_total += ws_check_partial;
        
        ws_check_partial = Character.getNumericValue(ws_pesel.charAt(2));
        ws_check_partial *= 3;
        ws_check_total += ws_check_partial;
        
        ws_check_partial = Character.getNumericValue(ws_pesel.charAt(3));
        ws_check_total += ws_check_partial;
        
        ws_check_partial = Character.getNumericValue(ws_pesel.charAt(4));
        ws_check_partial *= 9;
        ws_check_total += ws_check_partial;
        
        ws_check_partial = Character.getNumericValue(ws_pesel.charAt(5));
        ws_check_partial *= 7;
        ws_check_total += ws_check_partial;
        
        ws_check_partial = Character.getNumericValue(ws_pesel.charAt(6));
        ws_check_partial *= 3;
        ws_check_total += ws_check_partial;
        
        ws_check_partial = Character.getNumericValue(ws_pesel.charAt(7));
        ws_check_total += ws_check_partial;
        
        ws_check_partial = Character.getNumericValue(ws_pesel.charAt(8));
        ws_check_partial *= 9;
        ws_check_total += ws_check_partial;
        
        ws_check_partial = Character.getNumericValue(ws_pesel.charAt(9));
        ws_check_partial *= 7;
        ws_check_total += ws_check_partial;
        
        ws_check_digit = ws_check_total % 10;
    }
    
    private static void validateChecksum() {
        if (ws_check_digit != ws_pesel_check_digit) {
            System.out.println("Niepoprawny PESEL");
            return;
        }
    }
    
    private static void calcAge() {
        ws_age = ws_cur_year;
        
        if (ws_month >= 81 && ws_month <= 92) {
            ws_age -= 1800;
        } else if (ws_month >= 1 && ws_month <= 12) {
            ws_age -= 1900;
        } else if (ws_month >= 21 && ws_month <= 32) {
            ws_age -= 2000;
        }
        
        ws_age -= ws_year;
    }
    
    private static void showType() {
        if (ws_gen % 2 == 0) { // Woman
            if (ws_age >= 0 && ws_age <= 11) {
                System.out.println("Dziewczynka");
            } else if (ws_age >= 12 && ws_age <= 17) {
                System.out.println("Dziewczyna");
            } else if (ws_age >= 18 && ws_age <= 99) {
                System.out.println("Kobieta");
            }
        } else { // Man
            if (ws_age >= 0 && ws_age <= 11) {
                System.out.println("Chlopiec");
            } else if (ws_age >= 12 && ws_age <= 17) {
                System.out.println("Chlopak");
            } else if (ws_age >= 18 && ws_age <= 99) {
                System.out.println("Mezczyzna");
            }
        }
    }
    
    private static void showAge() {
        ws_age_disp = ws_age;
        System.out.println("Lat: " + String.format("%03d", ws_age_disp));
    }
}