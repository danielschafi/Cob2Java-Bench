public class ComandosAritmeticos {
    public static void main(String[] args) {
        int wrkContador = 0;
        int wrkReplace = 10;
        int wrkCompute = 100;
        int wrkRecebeCompute = 0;
        int wrkSaldo = 0;
        int wrkBoleto = 0;
        int wrkRestante = 0;
        int wrkDividendo = 1001;
        int wrkResultadoDivisao = 0;
        int wrkRestoDivisao = 0;
        int wrkResultadoMultiplicacao = 0;

        wrkContador += 10;
        wrkContador += 90;
        System.out.println(wrkContador);

        wrkContador += 900;
        if (wrkContador > 999) {
            System.out.println("VALOR DE 3 BYTES EXCEDIDO!");
        }

        wrkReplace = 50;
        System.out.println("GIVING " + wrkReplace);

        wrkCompute = wrkCompute * 2;
        System.out.println(wrkCompute);

        wrkRecebeCompute = wrkCompute;
        System.out.println(wrkRecebeCompute);

        wrkCompute = (wrkCompute + 2) * 10;
        if (wrkCompute > 9999) {
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