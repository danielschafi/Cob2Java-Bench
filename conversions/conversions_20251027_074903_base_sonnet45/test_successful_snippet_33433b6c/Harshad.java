public class Harshad {
    private static int[] harshads = new int[20];
    private static int niven;
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
        niven = 0;
        firstNum = 1;
        
        while (niven < 20) {
            calculateHarshad();
            firstNum++;
        }

        pass = 2;
        secondNum = firstNum;
        firstNum = secondNum;
        
        do {
            calculateHarshad();
            firstNum++;
        } while (harshadResult <= 1000);

        i = 0;
        do {
            System.out.print(harshads[i] + " ");
            i++;
        } while (i < 20);

        System.out.println("... " + harshadResult);
    }

    private static void calculateHarshad() {
        div = firstNum;
        harshadResult = 0;
        calculateSumOfDigits();
        harshadDiv = firstNum / tot;
        harshadMod = firstNum % tot;
        
        if (harshadMod == 0) {
            if (pass == 1) {
                harshads[niven] = firstNum;
                niven++;
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