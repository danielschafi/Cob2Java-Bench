import java.util.Scanner;

public class UNROMAN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inpRoman = new String[20];
        int[] inpRomanDigits = new int[20];
        int[][] wsTblRoman = {
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

            for (int i = 0; i < 20; i++) {
                inpRomanDigits[i] = 0;
            }

            for (int i = 0; i < input.length(); i++) {
                inpRoman[i] = String.valueOf(input.charAt(i));
                boolean found = false;
                for (int rx = 0; rx < 7; rx++) {
                    if (wsTblRoman[rx][1] == inpRoman[i].charAt(0)) {
                        inpRomanDigits[i] = rx + 1;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    inpRomanDigits[i] = 0;
                }
            }

            int l = input.length() - 1;
            wsNumber = 0;

            for (int i = 0; i <= l; i++) {
                if (inpRomanDigits[i] == 0) break;
                int j = inpRomanDigits[i];
                int k = (i + 1 <= l) ? inpRomanDigits[i + 1] : 0;

                if (k > j) {
                    wsNumber -= wsTblRoman[j - 1][0];
                } else {
                    wsNumber += wsTblRoman[j - 1][0];
                }
            }

            wsNumberPic = String.format("%05d", wsNumber);
            if (wsNumber < 0) {
                wsNumberPic = "-" + wsNumberPic.substring(1);
            }

            System.out.println("----------");
            System.out.println("roman=" + input);
            System.out.println("arabic=" + wsNumberPic);

            if (l > 0 && wsNumber == 0) {
                System.out.println("invalid/incomplete roman numeral at pos " + (l + 1) + " found " + inpRoman[l]);
            }
        }
        scanner.close();
    }
}