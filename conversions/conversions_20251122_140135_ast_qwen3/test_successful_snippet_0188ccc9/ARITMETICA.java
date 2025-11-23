public class ARITMETICA {
    private static int NUMERO1 = 1;
    private static int NUMERO2 = 2;
    private static int RESULTADO = 1;
    private static double numerogrande = 0.0;

    public static void main(String[] args) {
        System.out.println("NUMERO1 = " + NUMERO1);
        System.out.println("NUMERO2 = " + NUMERO2);
        
        // SUMA
        RESULTADO = NUMERO1 + NUMERO2 + 5 + RESULTADO;
        System.out.println("ADD NUMERO1 NUMERO2 5 TO RESULTADO = " + RESULTADO);
        
        RESULTADO = NUMERO1 + NUMERO2 + 5;
        System.out.println("ADD NUMERO1 NUMERO2 5 GIVING RESULTADO = " + RESULTADO);

        RESULTADO = 1;
        
        // RESTA
        RESULTADO = RESULTADO - NUMERO1 - NUMERO2 - 5;
        System.out.println("SUBTRACT NUMERO1 NUMERO2 5 FROM RESULTADO:" + RESULTADO);
        
        RESULTADO = NUMERO2 - NUMERO1 - 5;
        System.out.println("SUBTRACT NUMERO1 5 FROM NUMERO2 GIVING RESULTADO = " + RESULTADO);

        RESULTADO = 0;
        
        // MULTIPLICACION
        RESULTADO = 5 * RESULTADO;
        System.out.println("MULTIPLY 5 BY RESULTADO = " + RESULTADO);
        
        RESULTADO = 5 * NUMERO1;
        System.out.println("MULTIPLY 5 BY NUMERO1 GIVING RESULTADO = " + RESULTADO);

        RESULTADO = 10;
        NUMERO2 = 8;
        
        // DIVISION
        RESULTADO = RESULTADO / 10;
        System.out.println("DIVIDE 10 INTO RESULTADO = " + RESULTADO);
        
        RESULTADO = NUMERO2 / 2;
        System.out.println("DIVIDE 2 INTO NUMERO2 GIVING RESULTADO = " + RESULTADO);

        // MIX
        RESULTADO = (2 + 3) * 5;
        System.out.println("RESULTADO = (2 + 3) * 5 = " + RESULTADO);

        RESULTADO = (int) Math.pow(3, 3);
        System.out.println("RESULTADO = 3 ** 3 = " + RESULTADO);

        numerogrande = -9000000000.22;
        System.out.println(numerogrande);

        System.exit(0);
    }
}