public class LCM {
    public static void main(String[] args) {
        int[] A = new int[10];
        int N = 2;
        int I, J, Q, R;
        int K = 0;
        int B = 0;
        int C = 0;
        int D = 0;
        String P;
        int num = 0;
        int num2 = 0;
        int chickens = 0;
        int dogs = 0;
        int total = 0;
        int result = 0;
        int result1 = 0;
        int result2 = 0;
        int count1 = 0;
        
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        System.out.println("ENTER " + N + " NUMBERS");
        for (I = 1; I <= N; I++) {
            A[I - 1] = scanner.nextInt();
            if (B < A[I - 1]) {
                B = A[I - 1];
            }
        }
        
        for (I = B; C != N; I += B) {
            C = 0;
            D = D + 1;
            for (J = 1; J <= N; J++) {
                K = B * D;
                Q = K / A[J - 1];
                R = K % A[J - 1];
                if (R == 0) {
                    C = C + 1;
                }
            }
        }
        
        P = String.format("%05d", K);
        System.out.println("THE LCM IS " + P);
        
        System.out.println("Enter Number of Head");
        num = scanner.nextInt();
        System.out.println("Enter number of legs");
        num2 = scanner.nextInt();
        
        for (chickens = 0; chickens < num; chickens++) {
            dogs = num - chickens;
            result = 2 * chickens;
            result1 = 4 * dogs;
            result2 = result + result1;
            if (result2 == num2) {
                System.out.println("[" + chickens + "," + dogs + "]");
                count1 = 1;
            } else {
                if (count1 == 1) {
                    count1 = 1;
                } else {
                    count1 = 2;
                }
            }
        }
        
        if (count1 == 2) {
            System.out.println("NONE");
        }
        
        scanner.close();
    }
}