import java.text.DecimalFormat;

public class HelloWorld {
    public static void main(String[] args) {
        int counter = 0;
        double demoA = 99.00;
        double demoB = 1.25;
        boolean acceptable = demoB >= 1 && demoB <= 10;
        boolean unacceptable = demoB >= 12 && demoB <= 20;
        double demoC = 0.0;
        String hdr = "OUTPUT";
        String dvdr = "--------";
        int pointAX = 1;
        int pointAY = 2;
        int pointBX = 8;
        int pointBY = 9;
        String[][] dbtable = new String[10][11];
        for (int i = 0; i < 10; i++) {
            dbtable[i][0] = "ROW";
            for (int j = 1; j < 11; j++) {
                dbtable[i][j] = "100";
            }
        }

        System.out.println("Hello, world3");
        System.out.println(hdr);
        System.out.println("A=" + new DecimalFormat("0.00").format(demoA));
        System.out.println("B=" + new DecimalFormat("0.000").format(demoB));
        System.out.println(dvdr);
        System.out.println(pointAX);
        System.out.println(pointBX);
        System.out.println(dvdr);
        pointBX = pointAX;
        System.out.println(pointBX);
        System.out.println(dvdr);
        demoC = demoA + demoB;
        System.out.println(new DecimalFormat("0.000000000").format(demoC));
        demoA = demoC - pointBY;
        System.out.println(new DecimalFormat("0.00").format(demoA));
        demoA = demoB * 3;
        System.out.println(new DecimalFormat("0.00").format(demoA));
        System.out.println(dvdr);
        System.out.println(new DecimalFormat("0.00").format(demoA));
        System.out.println(new DecimalFormat("0.000").format(demoB));
        if (demoA > demoB) {
            System.out.println("A IS GREATER THAN B");
        } else {
            System.out.println("B IS GREATER THAN A");
        }
        System.out.println(dvdr);
        demoB = demoB * 12;
        System.out.println(new DecimalFormat("0.000").format(demoB));
        if (acceptable) {
            System.out.println("ACCEPTABLE: " + new DecimalFormat("0.000").format(demoB));
        }
        if (unacceptable) {
            System.out.println("UNACCEPTABLE: " + new DecimalFormat("0.000").format(demoB));
        }

        while (counter != 5) {
            routineA(counter);
            counter++;
        }
        System.out.println(dvdr);
        for (int i = 0; i < 10; i++) {
            routineA(counter);
            counter++;
        }
        System.out.println(dvdr);
        for (counter = 0; counter < 10; counter++) {
            routineB(counter);
        }
        counter = 2;
        switch (counter) {
            case 0:
                routineC();
                break;
            case 1:
                routineD();
                break;
            case 2:
                routineE();
                break;
        }
    }

    public static void routineA(int counter) {
        System.out.println("HELLO " + counter);
    }

    public static void routineB(int counter) {
        System.out.println("FOR LOOP " + counter);
    }

    public static void routineC() {
        System.out.println("--------");
        System.out.println("C");
        routineF();
    }

    public static void routineD() {
        System.out.println("--------");
        System.out.println("D");
        routineF();
    }

    public static void routineE() {
        System.out.println("--------");
        System.out.println("E");
        routineF();
    }

    public static void routineF() {
        System.out.println("DBTABLE: ");
        System.out.println("STUDENT 1, GRADE 1: 100");
    }
}