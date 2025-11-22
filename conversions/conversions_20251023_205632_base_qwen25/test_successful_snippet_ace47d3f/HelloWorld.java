import java.text.DecimalFormat;

public class HelloWorld {
    public static void main(String[] args) {
        int COUNTER = 0;
        double DEMO_A = 99.00;
        double DEMO_B = 1.25;
        double DEMO_C = 0.0;
        String HDR = "OUTPUT";
        String DVDR = "--------";
        int POINT_A_X = 1;
        int POINT_A_Y = 2;
        int POINT_B_X = 8;
        int POINT_B_Y = 9;
        String[][] DBTABLE = new String[10][11];
        for (int i = 0; i < 10; i++) {
            DBTABLE[i][0] = "ROW";
            for (int j = 1; j < 11; j++) {
                DBTABLE[i][j] = "100";
            }
        }

        System.out.println("Hello, world3");
        System.out.println(HDR);
        System.out.println("A=" + new DecimalFormat("0.00").format(DEMO_A));
        System.out.println("B=" + new DecimalFormat("0.000").format(DEMO_B));
        System.out.println(DVDR);
        System.out.println(POINT_A_X);
        System.out.println(POINT_B_X);
        System.out.println(DVDR);
        POINT_B_X = POINT_A_X;
        System.out.println(POINT_B_X);
        System.out.println(DVDR);
        DEMO_C = DEMO_A + DEMO_B;
        System.out.println(new DecimalFormat("0.000000000").format(DEMO_C));
        DEMO_A = DEMO_C - POINT_B_Y;
        System.out.println(new DecimalFormat("0.00").format(DEMO_A));
        DEMO_A = DEMO_B * 3;
        System.out.println(new DecimalFormat("0.00").format(DEMO_A));
        System.out.println(DVDR);
        System.out.println(new DecimalFormat("0.00").format(DEMO_A));
        System.out.println(new DecimalFormat("0.000").format(DEMO_B));
        if (DEMO_A > DEMO_B) {
            System.out.println("A IS GREATER THAN B");
        } else {
            System.out.println("B IS GREATER THAN A");
        }
        System.out.println(DVDR);
        DEMO_B = DEMO_B * 12;
        System.out.println(new DecimalFormat("0.000").format(DEMO_B));
        if (DEMO_B >= 1 && DEMO_B <= 10) {
            System.out.println("ACCEPTABLE: " + new DecimalFormat("0.000").format(DEMO_B));
        }
        if (DEMO_B >= 12 && DEMO_B <= 20) {
            System.out.println("UNACCEPTABLE: " + new DecimalFormat("0.000").format(DEMO_B));
        }

        while (COUNTER != 5) {
            ROUTINE_A(COUNTER);
            COUNTER++;
        }
        System.out.println(DVDR);
        for (int i = 0; i < 10; i++) {
            ROUTINE_A(COUNTER);
            COUNTER++;
        }
        System.out.println(DVDR);
        for (COUNTER = 0; COUNTER < 10; COUNTER++) {
            ROUTINE_B(COUNTER);
        }
        COUNTER = 2;
        switch (COUNTER) {
            case 0:
                ROUTINE_C();
                break;
            case 1:
                ROUTINE_D();
                break;
            case 2:
                ROUTINE_E();
                break;
        }

        ROUTINE_F(DBTABLE);
    }

    public static void ROUTINE_A(int COUNTER) {
        System.out.println("HELLO " + COUNTER);
    }

    public static void ROUTINE_B(int COUNTER) {
        System.out.println("FOR LOOP " + COUNTER);
    }

    public static void ROUTINE_C() {
        System.out.println("--------");
        System.out.println("C");
        ROUTINE_F(null);
    }

    public static void ROUTINE_D() {
        System.out.println("--------");
        System.out.println("D");
        ROUTINE_F(null);
    }

    public static void ROUTINE_E() {
        System.out.println("--------");
        System.out.println("E");
        ROUTINE_F(null);
    }

    public static void ROUTINE_F(String[][] DBTABLE) {
        if (DBTABLE != null) {
            System.out.print("DBTABLE: ");
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 11; j++) {
                    System.out.print(DBTABLE[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("STUDENT 1, GRADE 1: " + DBTABLE[0][1]);
        } else {
            System.out.println("DBTABLE: null");
            System.out.println("STUDENT 1, GRADE 1: null");
        }
    }
}