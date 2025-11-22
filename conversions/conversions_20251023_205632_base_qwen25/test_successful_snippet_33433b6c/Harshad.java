import java.util.Arrays;

public class Harshad {
    private int[] harshads = new int[20];
    private int firstNum;
    private int secondNum;
    private int i;
    private int div;
    private int mod;
    private int tot;
    private int harshadDiv;
    private int harshadMod;
    private int harshadDisp;
    private int harshadResult;
    private int pass;
    private int niven = 0;

    public static void main(String[] args) {
        Harshad harshad = new Harshad();
        harshad.execute();
    }

    public void execute() {
        pass = 1;
        niven = 1;
        for (firstNum = 1; niven < 21; firstNum++) {
            calculateHarshad();
        }

        pass = 2;
        secondNum = firstNum;
        for (firstNum = secondNum; harshadResult <= 1000; firstNum++) {
            calculateHarshad();
        }

        for (i = 0; i < 20; i++) {
            harshadDisp = harshads[i];
            System.out.print(String.format("%5d", harshadDisp).trim() + " ");
        }

        harshadDisp = harshadResult;
        System.out.println("... " + String.format("%5d", harshadDisp).trim());
    }

    private void calculateHarshad() {
        div = firstNum;
        harshadResult = 0;
        calculateSumOfDigits();
        harshadDiv = firstNum / tot;
        harshadMod = firstNum % tot;
        if (harshadMod == 0) {
            if (pass == 1) {
                harshads[niven++] = firstNum;
            } else {
                harshadResult = firstNum;
            }
        }
    }

    private void calculateSumOfDigits() {
        tot = 0;
        while (div != 0) {
            mod = div % 10;
            div = div / 10;
            tot += mod;
        }
    }
}