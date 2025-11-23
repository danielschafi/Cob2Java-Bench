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
    
    public static void evaluate1() {
        switch (animal) {
            case 1:
            case 2:
                System.out.println("mammifère");
                break;
            default:
                System.out.println("reptile");
                break;
        }
    }
    
    public static void evaluate2() {
        if (animal >= 1 && animal <= 2) {
            System.out.println("mammifère");
        } else if (animal == 3 || animal == 5) {
            System.out.println("poisson");
        } else if (animal >= 1 && animal <= 5) {
            System.out.println("animal");
        } else {
            System.out.println("pas animal");
        }
    }
}