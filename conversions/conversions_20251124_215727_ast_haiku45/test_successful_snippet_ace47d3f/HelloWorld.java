public class HelloWorld {
    private int counter = 0;
    private double demoA = 99.0;
    private double demoB = 1.25;
    private double demoC = 0.0;
    private String hdr = "OUTPUT";
    private String dvdr = "--------";
    
    private int pointAX = 1;
    private int pointAY = 2;
    private int pointBX = 8;
    private int pointBY = 9;
    
    private String[][] dbtable = new String[10][10];
    private int[][][] dbtableCol = new int[10][10][1];
    
    private boolean acceptable = false;
    private boolean unacceptable = false;
    
    public HelloWorld() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                dbtable[i][j] = "ROW";
                dbtableCol[i][j][0] = 100;
            }
        }
    }
    
    public void display(Object... args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg);
        }
        System.out.println(sb.toString());
    }
    
    public void routineA() {
        display("HELLO ", counter);
        counter += 1;
    }
    
    public void routineB() {
        display("FOR LOOP ", counter);
    }
    
    public void routineC() {
        display(dvdr);
        display("C");
        routineF();
    }
    
    public void routineD() {
        display(dvdr);
        display("D");
        routineF();
    }
    
    public void routineE() {
        display(dvdr);
        display("E");
        routineF();
    }
    
    public void routineF() {
        display("DBTABLE: ", dbtable);
        display("STUDENT 1, GRADE 1: ", dbtableCol[0][0][0]);
    }
    
    public void run() {
        display("Hello, world3");
        display(hdr);
        display("A=", demoA);
        display("B=", demoB);
        display(dvdr);
        display(pointAX);
        display(pointBX);
        display(dvdr);
        pointBX = pointAX;
        display(pointBX);
        display(dvdr);
        demoC = demoA + demoB;
        display(demoC);
        demoA = demoC - pointBY;
        display(demoA);
        demoA = demoB * 3;
        display(demoA);
        display(dvdr);
        display(demoA);
        display(demoB);
        if (demoA > demoB) {
            display("A IS GREATER THAN B");
        } else {
            display("B IS GREATER THAN A");
        }
        display(dvdr);
        demoB = demoB * 12;
        display(demoB);
        acceptable = (demoB >= 1 && demoB <= 10);
        unacceptable = (demoB >= 12 && demoB <= 20);
        if (acceptable) {
            display("ACCEPTABLE: ", demoB);
        }
        if (unacceptable) {
            display("UNACCEPTABLE: ", demoB);
        }
        
        counter = 0;
        while (counter != 5) {
            routineA();
        }
        display(dvdr);
        for (int i = 0; i < 10; i++) {
            routineA();
        }
        display(dvdr);
        counter = 0;
        while (counter != 10) {
            routineB();
            counter += 1;
        }
        counter = 2;
        if (counter == 1) {
            routineC();
        } else if (counter == 2) {
            routineD();
        } else if (counter == 3) {
            routineE();
        }
    }
    
    public static void main(String[] args) {
        HelloWorld program = new HelloWorld();
        program.run();
    }
}