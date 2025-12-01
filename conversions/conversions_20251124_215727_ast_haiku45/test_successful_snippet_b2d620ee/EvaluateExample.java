public class EvaluateExample {
    private static int animal;
    
    private static final int CHIEN = 1;
    private static final int CHAT = 2;
    private static final int POISSON = 3;
    private static final int SERPENT = 4;
    private static final int REQUIN = 5;
    
    public static void main(String[] args) {
        animal = 2;
        evaluate1();
        
        animal = 5;
        evaluate1();
        
        animal = 2;
        evaluate2();
        
        animal = 5;
        evaluate2();
    }
    
    private static void evaluate1() {
        if (animal >= 1 && animal <= 2) {
            System.out.println("mammifère");
        } else {
            System.out.println("reptile");
        }
    }
    
    private static void evaluate2() {
        if (animal == CHIEN || animal == CHAT) {
            System.out.println("mammifère");
        } else if (animal == POISSON || animal == REQUIN) {
            System.out.println("poisson");
        } else {
            System.out.println("animal");
        }
    }
}