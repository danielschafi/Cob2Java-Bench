public class HelloWorld {
    private static int counter = 0;
    private static double demoA = 99.00;
    private static double demoB = 1.25;
    private static boolean acceptable = (demoB >= 1 && demoB <= 10);
    private static boolean unacceptable = (demoB >= 12 && demoB <= 20);
    private static double demoC;
    private static String hdr = "OUTPUT";
    private static String dvdr = "--------";
    
    private static class PointA {
        public int x = 1;
        public int y = 2;
    }
    
    private static class PointB {
        public int x = 8;
        public int y = 9;
    }
    
    private static class DbTable {
        public String[][] dbtableRowVal = new String[10][10];
        public int[][] dbtableColVal = new int[10][10];
        
        public DbTable() {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    dbtableRowVal[i][j] = "ROW";
                    dbtableColVal[i][j] = 100;
                }
            }
        }
    }
    
    private static PointA pointA = new PointA();
    private static PointB pointB = new PointB();
    private static DbTable dbtable = new DbTable();
    
    public static void main(String[] args) {
        System.out.println("Hello, world3");
        System.out.println(hdr);
        System.out.println("A=" + demoA);
        System.out.println("B=" + demoB);
        System.out.println(dvdr);
        System.out.println(pointA.x);
        System.out.println(pointB.x);
        System.out.println(dvdr);
        pointB.x = pointA.x;
        System.out.println(pointB.x);
        System.out.println(dvdr);
        demoC = demoA + demoB;
        System.out.println(demoC);
        demoA = demoC - pointB.y;
        System.out.println(demoA);
        demoA = demoB * 3;
        System.out.println(demoA);
        System.out.println(dvdr);
        System.out.println(demoA);
        System.out.println(demoB);
        if (demoA > demoB) {
            System.out.println("A IS GREATER THAN B");
        } else {
            System.out.println("B IS GREATER THAN A");
        }
        System.out.println(dvdr);
        demoB = demoB * 12;
        System.out.println(demoB);
        if (acceptable) {
            System.out.println("ACCEPTABLE: " + demoB);
        }
        if (unacceptable) {
            System.out.println("UNACCEPTABLE: " + demoB);
        }
        
        while (counter != 5) {
            routineA();
        }
        System.out.println(dvdr);
        for (int i = 0; i < 10; i++) {
            routineA();
        }
        System.out.println(dvdr);
        for (counter = 0; counter < 10; counter++) {
            routineB();
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
    
    private static void routineA() {
        System.out.println("HELLO " + counter);
        counter++;
    }
    
    private static void routineB() {
        System.out.println("FOR LOOP " + counter);
    }
    
    private static void routineC() {
        System.out.println(dvdr);
        System.out.println("C");
        routineF();
    }
    
    private static void routineD() {
        System.out.println(dvdr);
        System.out.println("D");
        routineF();
    }
    
    private static void routineE() {
        System.out.println(dvdr);
        System.out.println("E");
        routineF();
    }
    
    private static void routineF() {
        System.out.println("DBTABLE: " + dbtable);
        System.out.println("STUDENT 1, GRADE 1: " + dbtable.dbtableColVal[0][0]);
    }
}