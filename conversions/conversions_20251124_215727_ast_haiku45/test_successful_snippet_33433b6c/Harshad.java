```java
public class Harshad {
    private static int[] harshads = new int[20];
    private static int nivenIndex = 0;
    private static int firstNum = 0;
    private static int secondNum = 0;
    private static int i = 0;
    private static int div = 0;
    private static int mod = 0;
    private static int tot = 0;
    private static int harshadDiv = 0;
    private static int harshadMod = 0;
    private static int harshadResult = 0;
    private static int pass = 0;

    public static void main(String[] args) {
        pass = 1;
        nivenIndex = 0;
        
        for (firstNum = 1; nivenIndex < 20; firstNum++) {
            calculateHarshad();
        }

        pass = 2;
        secondNum = firstNum;
        do {
            calculateHarshad();
            firstNum++;
        } while (harshadResult <= 1000);

        for (i = 0; i < 20; i++) {
            System.out.print(String.valueOf(harshads[i]).trim() + " ");
        }

        System.out.println("... " + String.valueOf(harshadResult).trim());
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