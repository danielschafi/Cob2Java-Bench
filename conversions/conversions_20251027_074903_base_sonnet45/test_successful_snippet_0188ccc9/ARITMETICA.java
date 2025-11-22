import java.math.BigDecimal;
import java.math.RoundingMode;

public class ARITMETICA {
    public static void main(String[] args) {
        byte numero1 = 1;
        byte numero2 = 2;
        byte resultado = 1;
        BigDecimal numerogrande = BigDecimal.ZERO;

        System.out.println("NUMERO1 = " + numero1);
        System.out.println("NUMERO2 = " + numero2);

        resultado = (byte)(numero1 + numero2 + 5 + resultado);
        System.out.println("ADD NUMERO1 NUMERO2 5 TO RESULTADO = " + resultado);

        resultado = (byte)(numero1 + numero2 + 5);
        System.out.println("ADD NUMERO1 NUMERO2 5 GIVING RESULTADO = " + resultado);

        resultado = 1;

        resultado = (byte)(resultado - numero1 - numero2 - 5);
        System.out.println("SUBTRACT NUMERO1 NUMERO2 5 FROM RESULTADO:" + resultado);

        resultado = (byte)(numero2 - numero1 - 5);
        System.out.println("SUBTRACT NUMERO1 5 FROM NUMERO2 GIVING RESULTADO =" + resultado);

        resultado = 0;

        resultado = (byte)(5 * resultado);
        System.out.println("MULTIPLY 5 BY RESULTADO = " + resultado);

        resultado = (byte)(5 * numero1);
        System.out.println("MULTIPLY 5 BY NUMERO1 GIVING RESULTADO = " + resultado);

        resultado = 10;
        numero2 = 8;

        resultado = (byte)(resultado / 10);
        System.out.println("DIVIDE 10 INTO RESULTADO = " + resultado);

        resultado = (byte)(numero2 / 2);
        System.out.println("DIVIDE 2 INTO NUMERO2 GIVING RESULTADO = " + resultado);

        resultado = (byte)((2 + 3) * 5);
        System.out.println("RESULTADO = (2 + 3) * 5 = " + resultado);

        resultado = (byte)Math.pow(3, 3);
        System.out.println("RESULTADO = 3 ** 3 = " + resultado);

        numerogrande = new BigDecimal("-9000000000.22");
        System.out.println(numerogrande.setScale(2, RoundingMode.HALF_UP).toPlainString());
    }
}