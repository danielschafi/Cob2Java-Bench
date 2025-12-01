import java.util.Scanner;

public class LCM {
    private static int[] A = new int[10];
    private static int N = 2;
    private static int I;
    private static int J;
    private static int Q;
    private static int R;
    private static int K;
    private static int B = 0;
    private static int C = 0;
    private static int D = 0;
    private static int num = 0;
    private static int num2 = 0;
    private static int chickens = 0;
    private static int dogs = 0;
    private static int total = 0;
    private static int result = 0;
    private static int result1 = 0;
    private static int result2 = 0;
    private static int count1 = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mainPara();
    }

    private static void mainPara() {
        System.out.println("ENTER " + N + " NUMBERS");
        for (I = 1; I <= N; I++) {
            xPara();
        }
        for (I = B; C != N; I++) {
            yPara();
        }
        System.out.println("THE LCM IS " + K);

        System.out.println("Enter Number of Head");
        num = scanner.nextInt();
        System.out.println("Enter number of legs");
        num2 = scanner.nextInt();
        headlegPara();
        if (count1 == 2) {
            System.out.println("NONE");
        }
    }

    private static void xPara() {
        A[I] = scanner.nextInt();
        if (B < A[I]) {
            B = A[I];
        }
    }

    private static void yPara() {
        C = 0;
        D = D + 1;
        for (J = 1; J <= N; J++) {
            zPara();
        }
    }

    private static void zPara() {
        K = B * D;
        Q = K / A[J];
        R = K % A[J];
        if (R == 0) {
            C = C + 1;
        }
    }

    private static void headlegPara() {
        for (chickens = 0; chickens < num; chickens++) {
            dogs = num - chickens;
            result = 2 * chickens;
            result1 = 4 * dogs;
            result2 = result + result1;
            if (result2 == num2) {
                System.out.println("[" + chickens + "," + dogs + "]");
                count1 = 1;
            } else if (count1 == 1) {
                count1 = 1;
            } else {
                count1 = 2;
            }
        }
    }
}