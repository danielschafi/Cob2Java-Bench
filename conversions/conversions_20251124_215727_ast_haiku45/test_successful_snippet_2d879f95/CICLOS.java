```java
public class CICLOS {
    private int COUNTER = 0;
    private int COUNTER_2 = 0;

    public static void main(String[] args) {
        CICLOS program = new CICLOS();
        program.run();
    }

    public void run() {
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
        parrafo1();
    }

    public void parrafo1() {
        // PERFORM al saltar al parrafo2 una vez se ha ejecutado retorna a la linea
        // donde fue invocado, a diferencia de GO TO que continua la ejecucion del
        // programa en el punto donde se quedo
        for (int i = 0; i < 3; i++) {
            parrafo2();
        }
    }

    public void parrafo2() {
        System.out.println("soy una instruccion en el parrafo2");
    }

    public void parrafo3() {
        System.out.println("soy una instruccion en el parrafo3");
    }
}