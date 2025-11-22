import java.util.Scanner;

public class EvaluateExample {
    public static void main(String[] args) {
        int animal;

        animal = 2;
        evaluate1(animal);

        animal = 5;
        evaluate1(animal);

        animal = 2;
        evaluate2(animal);

        animal = 5;
        evaluate2(animal);
    }

    public static void evaluate1(int animal) {
        if (animal >= 1 && animal <= 2) {
            System.out.println("mammifère");
        } else {
            System.out.println("reptile");
        }
    }

    public static void evaluate2(int animal) {
        if (animal == 1 || animal == 2) {
            System.out.println("mammifère");
        } else if (animal == 3 || animal == 5) {
            System.out.println("poisson");
        } else {
            System.out.println("animal");
        }
    }
}