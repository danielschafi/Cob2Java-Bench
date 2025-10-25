import java.text.DecimalFormat;

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

    public static void main(String[] args) {
        Harshad harshad = new Harshad();
        harshad.execute();
    }

    public void execute() {
        pass = 1;
        int niven = 1;
        for (firstNum = 1; niven <= 20; firstNum++) {
            calculateHarshad();
            if (harshadMod == 0) {
                if (pass == 1) {
                    harshads[niven - 1] = firstNum;
                    niven++;
                } else {
                    harshadResult = firstNum;
                }
            }
        }

        pass = 2;
        secondNum = firstNum;
        for (firstNum = secondNum; harshadResult <= 1000; firstNum++) {
            calculateHarshad();
            if (harshadMod == 0) {
                if (pass == 1) {
                    harshads[niven - 1] = firstNum;
                    niven++;
                } else {
                    harshadResult = firstNum;
                }
            }
        }

        DecimalFormat df = new DecimalFormat("00000");
        for (i = 0; i < 20; i++) {
            harshadDisp = harshads[i];
            System.out.print(df.format(harshadDisp) + " ");
        }

        System.out.println("... " + df.format(harshadResult));
    }

    public void calculateHarshad() {
        div = firstNum;
        harshadResult = 0;
        calculateSumOfDigits();
        harshadDiv = div / tot;
        harshadMod = div % tot;
    }

    public void calculateSumOfDigits() {
        tot = 0;
        while (div != 0) {
            mod = div % 10;
            tot += mod;
            div /= 10;
        }
    }
}