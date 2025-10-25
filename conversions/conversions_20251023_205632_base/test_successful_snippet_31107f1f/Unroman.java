import java.util.Scanner;

public class Unroman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inpRoman = new String[20];
        int[] inpRomanDigits = new int[20];
        int i, j, k, l;
        int wsSearchIdx;
        int[][] wsTblTableDef = {
            {1000, 'M'},
            {500, 'D'},
            {100, 'C'},
            {50, 'L'},
            {10, 'X'},
            {5, 'V'},
            {1, 'I'}
        };
        int wsNumber = 0;
        String wsNumberPic;

        while (true) {
            String input = scanner.nextLine();
            if (input.equals(" ")) break;

            for (int m = 0; m < 20; m++) {
                inpRoman[m] = " ";
                inpRomanDigits[m] = 0;
            }

            for (i = 0; i < input.length() && !input.substring(i, i + 1).equals(" "); i++) {
                wsSearchIdx = 0;
                boolean found = false;
                for (int rx = 0; rx < 7; rx++) {
                    if (wsTblTableDef[rx][1] == input.charAt(i)) {
                        inpRomanDigits[i] = wsTblTableDef[rx][0];
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    inpRomanDigits[i] = 0;
                }
            }
            l = i - 1;
            wsNumber = 0;
            for (i = 0; i <= l && inpRomanDigits[i] != 0; i++) {
                j = inpRomanDigits[i];
                k = (i + 1 <= l) ? inpRomanDigits[i + 1] : 0;
                if (k > j) {
                    wsNumber -= j;
                } else {
                    wsNumber += j;
                }
            }
            wsNumberPic = String.format("%05d", wsNumber);
            System.out.println("----------");
            System.out.println("roman=" + input);
            System.out.println("arabic=" + wsNumberPic);
            if (i < l || wsNumber == 0) {
                System.out.println("invalid/incomplete roman numeral at pos " + (i + 1) + " found " + input.charAt(i));
            }
        }
        scanner.close();
    }
}