public class Harshad {
    private static final int[] harshads = new int[20];
    private static int firstNum;
    private static int secondNum;
    private static int i;
    private static int div;
    private static int mod;
    private static int tot;
    private static int harshadDiv;
    private static int harshadMod;
    private static int harshadDisp;
    private static int harshadResult;
    private static int pass;

    public static void main(String[] args) {
        pass = 1;
        int niven = 1;
        for (firstNum = 1; niven <= 20; firstNum++) {
            calculateHarshad();
        }

        pass = 2;
        secondNum = firstNum;
        while (harshadResult <= 1000) {
            firstNum = secondNum;
            calculateHarshad();
            secondNum++;
        }

        for (i = 0; i < 20; i++) {
            harshadDisp = harshads[i];
            System.out.print(trim(Integer.toString(harshadDisp)) + " ");
        }
        harshadDisp = harshadResult;
        System.out.println("... " + trim(Integer.toString(harshadDisp)));
    }

    private static void calculateHarshad() {
        div = firstNum;
        harshadResult = 0;
        calculateSumOfDigits();
        harshadDiv = firstNum / tot;
        harshadMod = firstNum % tot;
        if (harshadMod == 0) {
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

    private static String trim(String s) {
        return s.replaceAll("^0+", "").isEmpty() ? "0" : s.replaceAll("^0+", "");
    }
}