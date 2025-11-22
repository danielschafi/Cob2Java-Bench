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
    private static String P;
    private static int num = 0;
    private static int num2 = 0;
    private static int chickens = 0;
    private static int dogs = 0;
    private static int total = 0;
    private static int result = 0;
    private static int result1 = 0;
    private static int result2 = 0;
    private static int count1 = 0;

    public static void main(String[] args) {
        System.out.println("ENTER " + N + " NUMBERS");
        for (I = 1; I <= N; I++) {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            A[I-1] = scanner.nextInt();
            if (B < A[I-1]) {
                B = A[I-1];
            }
        }

        while (C != N) {
            C = 0;
            D = D + 1;
            for (J = 1; J <= N; J++) {
                K = B * D;
                Q = K / A[J-1];
                R = K % A[J-1];
                if (R == 0) {
                    C = C + 1;
                }
            }
        }

        K = B * D;
        P = String.valueOf(K);
        System.out.println("THE LCM IS " + P);

        java.util.Scanner scanner2 = new java.util.Scanner(System.in);
        System.out.println("Enter Number of Head");
        num = scanner2.nextInt();
        System.out.println("Enter number of legs");
        num2 = scanner2.nextInt();
        
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
        
        if (count1 == 2) {
            System.out.println("NONE");
        }
    }
}