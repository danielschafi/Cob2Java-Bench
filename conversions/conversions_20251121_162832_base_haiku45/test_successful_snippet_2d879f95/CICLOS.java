```java
public class CICLOS {
    public static void main(String[] args) {
        int COUNTER = 0;
        int COUNTER_2 = 0;

        // REPITE 2 VECES UN BLOQUE DE SENTENCIAS
        for (int i = 0; i < 2; i++) {
            System.out.println("HOLA");
        }
        System.out.println("------------------------------------------------");

        // CREA UN FOR AUMENTANDO DE 1 EN 1 EL COUNTER, hasta el 4
        for (COUNTER = 1; COUNTER < 4; COUNTER++) {
            System.out.println("Counter = " + COUNTER);
        }
        System.out.println("------------------------------------------------");

        // CREA UN FOR ANIDADO
        for (COUNTER = 1; COUNTER < 3; COUNTER++) {
            for (COUNTER_2 = 1; COUNTER_2 < 3; COUNTER_2++) {
                System.out.println("Counter = " + COUNTER);
                System.out.println("Counter-2 = " + COUNTER_2);
                System.out.println("- - - -  - - - - - - - - - - - ");
            }
        }
        System.out.println("------------------------------------------------");

        // CREA UN WHILE AUMENTANDO DE 1 EN 1 EL COUNTER
        COUNTER = 0;
        while (COUNTER != 5) {
            System.out.println("Counter = " + COUNTER);
            COUNTER = COUNTER + 1;
        }
        System.out.println("------------------------------------------------");

        // CREA UN DO-WHILE AUMENTANDO DE 1 EN 1 EL COUNTER
        COUNTER = 0;
        do {
            System.out.println("Counter = " + COUNTER);
            COUNTER = COUNTER + 1;
        } while (COUNTER != 5);
        System.out.println("------------------------------------------------");

        // los parrafos cumplen la funcion de "etiquetas", permiten agrupar
        // codigo, para despues ser "llamadas" o "saltar" a estas
        for (int i = 0; i < 3; i++) {
            parrafo2();
        }
    }

    static void parrafo2() {
        System.out.println("soy una instruccion en el parrafo2");
    }

    static void parrafo3() {
        System.out.println("soy una instruccion en el parrafo3");
    }
}