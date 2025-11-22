public class Harshad {
    private static int[] harshads = new int[20];
    private static int firstNum = 0;
    private static int secondNum = 0;
    private static int i = 0;
    private static int div = 0;
    private static int mod = 0;
    private static int tot = 0;
    private static int harshadDiv = 0;
    private static int harshadMod = 0;
    private static boolean evenlyDivisible = false;
    private static String harshadDisp = "";
    private static int harshadResult = 0;
    private static int pass = 0;
    private static boolean firstPass = false;
    private static boolean secondPass = false;

    public static void main(String[] args) {
        pass = 1;
        int niven = 1;
        for (firstNum = 1; niven <= 20; firstNum++) {
            calculateHarshad();
            if (pass == 1) {
                harshads[niven - 1] = firstNum;
                niven++;
            }
        }

        pass = 2;
        firstNum = secondNum;
        while (harshadResult <= 1000) {
            calculateHarshad();
            if (pass == 2 && evenlyDivisible) {
                harshadResult = firstNum;
            }
        }

        for (i = 0; i < 20; i++) {
            harshadDisp = String.format("%5d", harshads[i]).trim();
            System.out.print(harshadDisp + " ");
        }

        harshadDisp = String.format("%5d", harshadResult).trim();
        System.out.println("... " + harshadDisp);
    }

    private static void calculateHarshad() {
        div = firstNum;
        tot = 0;
        calculateSumOfDigits();
        harshadDiv = firstNum / tot;
        harshadMod = firstNum % tot;
        evenlyDivisible = (harshadMod == 0);
        if (evenlyDivisible) {
            if (pass == 1) {
                harshads[niven - 1] = firstNum;
                niven++;
            } else {
                harshadResult = firstNum;
            }
        }
    }

    private static void calculateSumOfDigits() {
        tot = 0;
        while (div != 0) {
            div = div / 10;
            mod = div % 10;
            tot += mod;
        }
    }
}