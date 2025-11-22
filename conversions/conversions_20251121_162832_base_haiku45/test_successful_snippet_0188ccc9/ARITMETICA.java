public class ARITMETICA {
    public static void main(String[] args) {
        int NUMERO1 = 1;
        int NUMERO2 = 2;
        int RESULTADO = 1;
        long numerogrande = 0;

        System.out.println("NUMERO1 = " + NUMERO1);
        System.out.println("NUMERO2 = " + NUMERO2);

        RESULTADO = NUMERO1 + NUMERO2 + 5 + RESULTADO;
        System.out.println("ADD NUMERO1 NUMERO2 5 TO RESULTADO = " + RESULTADO);

        RESULTADO = NUMERO1 + NUMERO2 + 5;
        System.out.println("ADD NUMERO1 NUMERO2 5 GIVING RESULTADO = " + RESULTADO);

        RESULTADO = 1;
        RESULTADO = RESULTADO - NUMERO1 - NUMERO2 - 5;
        System.out.println("SUBTRACT NUMERO1 NUMERO2 5 FROM RESULTADO:" + RESULTADO);

        RESULTADO = NUMERO2 - NUMERO1 - 5;
        System.out.println("SUBTRACT NUMERO1 5 FROM NUMERO2 GIVING RESULTADO =" + RESULTADO);

        RESULTADO = 0;
        RESULTADO = 5 * RESULTADO;
        System.out.println("MULTIPLY 5 BY RESULTADO = " + RESULTADO);

        RESULTADO = 5 * NUMERO1;
        System.out.println("MULTIPLY 5 BY NUMERO1 GIVING RESULTADO = " + RESULTADO);

        RESULTADO = 10;
        NUMERO2 = 8;
        RESULTADO = RESULTADO / 10;
        System.out.println("DIVIDE 10 INTO RESULTADO = " + RESULTADO);

        RESULTADO = NUMERO2 / 2;
        System.out.println("DIVIDE 2 INTO NUMERO2 GIVING RESULTADO = " + RESULTADO);

        RESULTADO = (2 + 3) * 5;
        System.out.println("RESULTADO = (2 + 3) * 5 = " + RESULTADO);

        RESULTADO = (int) Math.pow(3, 3);
        System.out.println("RESULTADO = 3 ** 3 = " + RESULTADO);

        numerogrande = -9000000000L;
        System.out.println(numerogrande);

        System.exit(0);
    }
}