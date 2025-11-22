public class Harshad {
    private static final int[] harshads = new int[20];
    private static int nivenIndex = 0;
    private static int firstNum;
    private static int secondNum;
    private static int i;
    private static int div;
    private static int mod;
    private static int tot;
    private static int harshadDiv;
    private static int harshadMod;
    private static int harshadResult;
    private static int pass;

    public static void main(String[] args) {
        pass = 1;
        nivenIndex = 0;
        
        for (firstNum = 1; nivenIndex < 20; firstNum++) {
            calculateHarshad();
        }

        pass = 2;
        secondNum = firstNum;
        
        for (firstNum = secondNum; harshadResult <= 1000; firstNum++) {
            calculateHarshad();
        }

        for (i = 0; i < 20; i++) {
            System.out.print(String.format("%5d", harshads[i]).trim() + " ");
        }

        System.out.println("... " + String.format("%5d", harshadResult).trim());
    }

    private static void calculateHarshad() {
        div = firstNum;
        harshadResult = 0;
        calculateSumOfDigits();
        harshadDiv = firstNum / tot;
        harshadMod = firstNum % tot;
        
        if (harshadMod == 0) {
            if (pass == 1) {
                harshads[nivenIndex] = firstNum;
                nivenIndex++;
            } else {
                harshadResult = firstNum;
            }
        }
    }

    private static void calculateSumOfDigits() {
        tot = 0;
        do {
            mod = div % 10;
            div = div / 10;
            tot += mod;
        } while (div != 0);
    }
}