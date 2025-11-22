import java.math.BigDecimal;
import java.math.RoundingMode;

public class HelloWorld {
    private static int COUNTER = 0;
    private static BigDecimal DEMO_A = new BigDecimal("99.00");
    private static BigDecimal DEMO_B = new BigDecimal("1.250");
    private static BigDecimal DEMO_C = BigDecimal.ZERO;
    private static String HDR = "OUTPUT  ";
    private static String DVDR = "--------";
    
    private static class Point {
        int X;
        int Y;
    }
    
    private static Point POINT_A = new Point();
    private static Point POINT_B = new Point();
    
    private static class DBTableRow {
        String DBTABLE_ROWVAL;
        int[] DBTABLE_COL;
        
        DBTableRow() {
            DBTABLE_ROWVAL = "ROW       ";
            DBTABLE_COL = new int[10];
            for (int i = 0; i < 10; i++) {
                DBTABLE_COL[i] = 100;
            }
        }
    }
    
    private static DBTableRow[] DBTABLE = new DBTableRow[10];
    
    private static boolean isACCEPTABLE() {
        BigDecimal val = DEMO_B.setScale(0, RoundingMode.DOWN);
        int intVal = val.intValue();
        return intVal >= 1 && intVal <= 10;
    }
    
    private static boolean isUNACCEPTABLE() {
        BigDecimal val = DEMO_B.setScale(0, RoundingMode.DOWN);
        int intVal = val.intValue();
        return intVal >= 12 && intVal <= 20;
    }
    
    private static void ROUTINE_A() {
        System.out.println("HELLO " + String.format("%03d", COUNTER));
        COUNTER = COUNTER + 1;
    }
    
    private static void ROUTINE_B() {
        System.out.println("FOR LOOP " + String.format("%03d", COUNTER));
    }
    
    private static void ROUTINE_C() {
        System.out.println(DVDR);
        System.out.println("C");
        ROUTINE_F();
    }
    
    private static void ROUTINE_D() {
        System.out.println(DVDR);
        System.out.println("D");
        ROUTINE_F();
    }
    
    private static void ROUTINE_E() {
        System.out.println(DVDR);
        System.out.println("E");
        ROUTINE_F();
    }
    
    private static void ROUTINE_F() {
        StringBuilder dbTableStr = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            dbTableStr.append(DBTABLE[i].DBTABLE_ROWVAL);
            for (int j = 0; j < 10; j++) {
                dbTableStr.append(String.format("%03d", DBTABLE[i].DBTABLE_COL[j]));
            }
        }
        System.out.println("DBTABLE: " + dbTableStr.toString());
        System.out.println("STUDENT 1, GRADE 1: " + String.format("%03d", DBTABLE[0].DBTABLE_COL[0]));
    }
    
    public static void main(String[] args) {
        POINT_A.X = 1;
        POINT_A.Y = 2;
        POINT_B.X = 8;
        POINT_B.Y = 9;
        
        for (int i = 0; i < 10; i++) {
            DBTABLE[i] = new DBTableRow();
        }
        
        System.out.println("Hello, world3");
        System.out.println(HDR);
        System.out.println("A=" + DEMO_A.setScale(2, RoundingMode.HALF_UP).toPlainString());
        System.out.println("B=" + DEMO_B.setScale(3, RoundingMode.HALF_UP).toPlainString());
        System.out.println(DVDR);
        System.out.println(String.format("%02d", POINT_A.X));
        System.out.println(String.format("%02d", POINT_B.X));
        System.out.println(DVDR);
        POINT_B.X = POINT_A.X;
        System.out.println(String.format("%02d", POINT_B.X));
        System.out.println(DVDR);
        DEMO_C = DEMO_A.add(DEMO_B);
        System.out.println(DEMO_C.setScale(9, RoundingMode.HALF_UP).toPlainString());
        DEMO_A = DEMO_C.subtract(new BigDecimal(POINT_B.Y));
        System.out.println(DEMO_A.setScale(2, RoundingMode.HALF_UP).toPlainString());
        DEMO_A = DEMO_B.multiply(new BigDecimal("3"));
        System.out.println(DEMO_A.setScale(2, RoundingMode.HALF_UP).toPlainString());
        System.out.println(DVDR);
        System.out.println(DEMO_A.setScale(2, RoundingMode.HALF_UP).toPlainString());
        System.out.println(DEMO_B.setScale(3, RoundingMode.HALF_UP).toPlainString());
        
        if (DEMO_A.compareTo(DEMO_B) > 0) {
            System.out.println("A IS GREATER THAN B");
        } else {
            System.out.println("B IS GREATER THAN A");
        }
        
        System.out.println(DVDR);
        DEMO_B = DEMO_B.multiply(new BigDecimal("12"));
        System.out.println(DEMO_B.setScale(3, RoundingMode.HALF_UP).toPlainString());
        
        if (isACCEPTABLE()) {
            System.out.println("ACCEPTABLE: " + DEMO_B.setScale(3, RoundingMode.HALF_UP).toPlainString());
        }
        if (isUNACCEPTABLE()) {
            System.out.println("UNACCEPTABLE: " + DEMO_B.setScale(3, RoundingMode.HALF_UP).toPlainString());
        }
        
        while (COUNTER != 5) {
            ROUTINE_A();
        }
        
        System.out.println(DVDR);
        
        for (int i = 0; i < 10; i++) {
            ROUTINE_A();
        }
        
        System.out.println(DVDR);
        
        for (COUNTER = 0; COUNTER != 10; COUNTER = COUNTER + 1) {
            ROUTINE_B();
        }
        
        COUNTER = 2;
        
        switch (COUNTER) {
            case 1:
                ROUTINE_C();
                break;
            case 2:
                ROUTINE_D();
                break;
            case 3:
                ROUTINE_E();
                break;
        }
    }
}