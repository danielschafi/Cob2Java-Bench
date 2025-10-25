import java.util.Scanner;

public class CICLOS {
    public static void main(String[] args) {
        int COUNTER = 0;
        int COUNTER_2 = 0;

        for (int i = 0; i < 2; i++) {
            System.out.println("HOLA");
        }
        System.out.println("------------------------------------------------");

        for (COUNTER = 1; COUNTER < 4; COUNTER++) {
            System.out.println("Counter = " + COUNTER);
        }
        System.out.println("------------------------------------------------");

        for (COUNTER = 1; COUNTER <= 3; COUNTER++) {
            for (COUNTER_2 = 1; COUNTER_2 <= 3; COUNTER_2++) {
                System.out.println("Counter = " + COUNTER);
                System.out.println("Counter-2 = " + COUNTER_2);
            }
            System.out.println("- - - -  - - - - - - - - - - - ");
        }
        System.out.println("------------------------------------------------");

        COUNTER = 0;
        while (COUNTER < 5) {
            System.out.println("Counter = " + COUNTER);
            COUNTER++;
        }
        System.out.println("------------------------------------------------");

        COUNTER = 0;
        do {
            System.out.println("Counter = " + COUNTER);
            COUNTER++;
        } while (COUNTER < 5);
        System.out.println("------------------------------------------------");

        parrafo1();
    }

    public static void parrafo1() {
        for (int i = 0; i < 3; i++) {
            parrafo2();
        }
        System.exit(0);
    }

    public static void parrafo2() {
        System.out.println("soy una instruccion en el parrafo2");
    }

    public static void parrafo3() {
        System.out.println("soy una instruccion en el parrafo3");
    }
}