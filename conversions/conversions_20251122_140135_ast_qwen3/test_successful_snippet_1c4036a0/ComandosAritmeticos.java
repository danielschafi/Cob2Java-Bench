public class ComandosAritmeticos {
    private static int WRK_CONTADOR = 0;
    private static int WRK_REPLACE = 10;
    private static int WRK_COMPUTE = 100;
    private static int WRK_RECEBE_COMPUTE = 0;
    private static int WRK_SALDO = 0;
    private static int WRK_BOLETO = 0;
    private static int WRK_RESTANTE = 0;
    private static int WRK_DIVIDENDO = 1001;
    private static int WRK_RESULTADO_DIVISAO = 0;
    private static int WRK_RESTO_DIVISAO = 0;
    private static int WRK_RESULTADO_MULTIPLICACAO = 0;

    public static void main(String[] args) {
        WRK_CONTADOR += 10;
        WRK_CONTADOR += 90;
        System.out.println(WRK_CONTADOR);

        try {
            WRK_CONTADOR += 900;
        } catch (Exception e) {
            System.out.println("VALOR DE 3 BYTES EXCEDIDO!");
        }

        WRK_REPLACE = 50 + WRK_REPLACE;
        System.out.println("GIVING " + WRK_REPLACE);

        WRK_COMPUTE = WRK_COMPUTE * 2;
        System.out.println(WRK_COMPUTE);

        WRK_RECEBE_COMPUTE = WRK_COMPUTE;
        System.out.println(WRK_RECEBE_COMPUTE);

        try {
            WRK_COMPUTE = (WRK_COMPUTE + 2) * 10;
        } catch (Exception e) {
            System.out.println("ERRO NA FORMULA, BYTES EXCEDIDO");
        }

        WRK_SALDO = 1260;
        WRK_BOLETO = 1000;
        WRK_RESTANTE = WRK_SALDO - WRK_BOLETO;
        System.out.println("Saldo restante: " + WRK_RESTANTE);

        WRK_RESULTADO_DIVISAO = WRK_DIVIDENDO / 2;
        WRK_RESTO_DIVISAO = WRK_DIVIDENDO % 2;
        System.out.println("RESULTADO DA DIVISAO " + WRK_RESULTADO_DIVISAO);
        System.out.println("RESTO DA DIVISAO " + WRK_RESTO_DIVISAO);

        WRK_RESULTADO_MULTIPLICACAO = 3 * WRK_SALDO;
        System.out.println("RESULTADO DA MULTIPLICACAO: " + WRK_RESULTADO_MULTIPLICACAO);

    }
}