public class EvaluateExample {
    
    private static int animal;
    
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
        if ((animal >= 1 && animal <= 2)) {
            System.out.println("mammifère");
        } else if (animal == 3 || animal == 5) {
            System.out.println("poisson");
        } else {
            System.out.println("animal");
        }
    }
}