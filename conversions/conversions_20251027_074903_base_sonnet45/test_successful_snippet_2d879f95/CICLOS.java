import java.io.PrintStream;

public class CICLOS {
    private static final PrintStream out = System.out;
    private static int COUNTER;
    private static int COUNTER_2;

    public static void main(String[] args) {
        COUNTER = 0;
        COUNTER_2 = 0;

        for (int i = 0; i < 2; i++) {
            out.println("HOLA");
        }
        out.println("------------------------------------------------");

        for (COUNTER = 1; COUNTER < 4; COUNTER++) {
            out.println("Counter = " + String.format("%02d", COUNTER));
        }
        out.println("------------------------------------------------");

        for (COUNTER = 1; COUNTER < 3; COUNTER++) {
            for (COUNTER_2 = 1; COUNTER_2 < 3; COUNTER_2++) {
                out.println("Counter = " + String.format("%02d", COUNTER));
                out.println("Counter-2 = " + String.format("%02d", COUNTER_2));
                out.println("- - - -  - - - - - - - - - - - ");
            }
        }
        out.println("------------------------------------------------");

        COUNTER = 0;
        while (COUNTER != 5) {
            out.println("Counter = " + String.format("%02d", COUNTER));
            COUNTER = COUNTER + 1;
        }
        out.println("------------------------------------------------");

        COUNTER = 0;
        do {
            out.println("Counter = " + String.format("%02d", COUNTER));
            COUNTER = COUNTER + 1;
        } while (COUNTER != 5);
        out.println("------------------------------------------------");

        parrafo1();
    }

    private static void parrafo1() {
        for (int i = 0; i < 3; i++) {
            parrafo2();
        }
        System.exit(0);
    }

    private static void parrafo2() {
        out.println("soy una instruccion en el parrafo2");
    }

    private static void parrafo3() {
        out.println("soy una instruccion en el parrafo3");
    }
}