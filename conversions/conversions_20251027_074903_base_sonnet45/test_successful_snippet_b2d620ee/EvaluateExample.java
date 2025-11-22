public class EvaluateExample {
    
    private int animal;
    
    public EvaluateExample() {
        animal = 0;
    }
    
    public static void main(String[] args) {
        EvaluateExample program = new EvaluateExample();
        program.run();
    }
    
    public void run() {
        animal = 2;
        evaluate1();
        
        animal = 5;
        evaluate1();
        
        animal = 2;
        evaluate2();
        
        animal = 5;
        evaluate2();
    }
    
    private void evaluate1() {
        if (animal >= 1 && animal <= 2) {
            System.out.println("mammifère");
        } else {
            System.out.println("reptile");
        }
    }
    
    private void evaluate2() {
        if (animal >= 1 && animal <= 2) {
            System.out.println("mammifère");
        } else if (animal == 3 || animal == 5) {
            System.out.println("poisson");
        } else {
            System.out.println("animal");
        }
    }
}