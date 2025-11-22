public class HelloWorld {
    static class Point {
        int x;
        int y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static class DBTableRow {
        String rowval;
        int[][] col;
        
        DBTableRow() {
            this.rowval = "ROW";
            this.col = new int[10][1];
            for (int i = 0; i < 10; i++) {
                this.col[i][0] = 100;
            }
        }
    }
    
    static int counter = 0;
    static double demoA = 99.00;
    static double demoB = 1.25;
    static double demoC = 0;
    static String hdr = "OUTPUT";
    static String dvdr = "--------";
    static Point pointA = new Point(1, 2);
    static Point pointB = new Point(8, 9);
    static DBTableRow[] dbtable = new DBTableRow[10];
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            dbtable[i] = new DBTableRow();
        }
        
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
        if (demoB >= 1 && demoB <= 10) {
            System.out.println("ACCEPTABLE: " + demoB);
        }
        if (demoB >= 12 && demoB <= 20) {
            System.out.println("UNACCEPTABLE: " + demoB);
        }
        
        counter = 0;
        while (counter != 5) {
            routineA();
        }
        System.out.println(dvdr);
        for (int i = 0; i < 10; i++) {
            routineA();
        }
        System.out.println(dvdr);
        for (counter = 0; counter != 10; counter++) {
            routineB();
        }
        counter = 2;
        switch (counter) {
            case 1:
                routineC();
                break;
            case 2:
                routineD();
                break;
            case 3:
                routineE();
                break;
        }
    }
    
    static void routineA() {
        System.out.println("HELLO " + counter);
        counter++;
    }
    
    static void routineB() {
        System.out.println("FOR LOOP " + counter);
    }
    
    static void routineC() {
        System.out.println(dvdr);
        System.out.println("C");
        routineF();
    }
    
    static void routineD() {
        System.out.println(dvdr);
        System.out.println("D");
        routineF();
    }
    
    static void routineE() {
        System.out.println(dvdr);
        System.out.println("E");
        routineF();
    }
    
    static void routineF() {
        System.out.println("DBTABLE: " + dbtable);
        System.out.println("STUDENT 1, GRADE 1: " + dbtable[0].col[0][0]);
    }
}