public class ComandosAritmeticos {
    private static int wrkContador = 0;
    private static int wrkReplace = 10;
    private static int wrkCompute = 100;
    private static int wrkRecebeCompute = 0;
    private static int wrkSaldo = 0;
    private static int wrkBoleto = 0;
    private static int wrkRestante = 0;
    private static int wrkDividendo = 1001;
    private static int wrkResultadoDivisao = 0;
    private static int wrkRestoDivisao = 0;
    private static int wrkResultadoMultiplicacao = 0;

    public static void main(String[] args) {
        wrkContador += 10;
        wrkContador += 90;
        System.out.println(wrkContador);

        try {
            wrkContador += 900;
        } catch (ArithmeticException e) {
            System.out.println("VALOR DE 3 BYTES EXCEDIDO!");
        }

        wrkReplace = 90 + 10;
        System.out.println("GIVING " + wrkReplace);

        wrkCompute = wrkCompute * 2;
        System.out.println(wrkCompute);

        wrkRecebeCompute = wrkCompute;
        System.out.println(wrkRecebeCompute);

        try {
            wrkCompute = (wrkCompute + 2) * 10;
        } catch (ArithmeticException e) {
            System.out.println("ERRO NA FORMULA, BYTES EXCEDIDO");
        }

        wrkSaldo = 1260;
        wrkBoleto = 1000;
        wrkRestante = wrkSaldo - wrkBoleto;
        System.out.println("Saldo restante: " + wrkRestante);

        wrkResultadoDivisao = wrkDividendo / 2;
        wrkRestoDivisao = wrkDividendo % 2;
        System.out.println("RESULTADO DA DIVISAO " + wrkResultadoDivisao);
        System.out.println("RESTO DA DIVISAO " + wrkRestoDivisao);

        wrkResultadoMultiplicacao = 3 * wrkSaldo;
        System.out.println("RESULTADO DA MULTIPLICACAO: " + wrkResultadoMultiplicacao);

        System.exit(0);
    }
}