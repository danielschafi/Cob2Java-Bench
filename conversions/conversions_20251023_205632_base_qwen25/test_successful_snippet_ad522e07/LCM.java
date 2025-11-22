import java.util.Scanner;

public class LCM {
    public static void main(String[] args) {
        int[] A = new int[10];
        int N = 2;
        int I, J, Q, R, K = 1, B = 0, C = 0, D = 0;
        String P = "";
        int num = 0, num2 = 0, chickens, dogs, total, result, result1, result2, count1 = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("ENTER " + N + " NUMBERS");
        for (I = 0; I < N; I++) {
            A[I] = scanner.nextInt();
            if (B < A[I]) {
                B = A[I];
            }
        }

        while (C != N) {
            C = 0;
            D++;
            for (J = 0; J < N; J++) {
                K = B * D;
                Q = K / A[J];
                R = K % A[J];
                if (R == 0) {
                    C++;
                }
            }
        }
        P = String.format("%05d", K);
        System.out.println("THE LCM IS " + P);

        System.out.println("Enter Number of Head");
        num = scanner.nextInt();
        System.out.println("Enter number of legs");
        num2 = scanner.nextInt();

        for (chickens = 0; chickens <= num; chickens++) {
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

        if (count1 == 2) {
            System.out.println("NONE");
        }
    }
}