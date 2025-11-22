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
        System.out.println(formatNumber(WRK_CONTADOR, 3));
        
        int tempContador = WRK_CONTADOR + 900;
        if (tempContador > 999) {
            System.out.println("VALOR DE 3 BYTES EXCEDIDO!");
        } else {
            WRK_CONTADOR = tempContador;
        }
        
        WRK_REPLACE = 50;
        System.out.println("GIVING " + formatNumber(WRK_REPLACE, 2));
        
        WRK_COMPUTE = WRK_COMPUTE * 2;
        System.out.println(formatNumber(WRK_COMPUTE, 3));
        
        WRK_RECEBE_COMPUTE = WRK_COMPUTE;
        System.out.println(formatNumber(WRK_RECEBE_COMPUTE, 3));
        
        int tempCompute = (WRK_COMPUTE + 2) * 10;
        if (tempCompute > 999) {
            System.out.println("ERRO NA FORMULA, BYTES EXCEDIDO");
        } else {
            WRK_COMPUTE = tempCompute;
        }
        
        WRK_SALDO = 1260;
        WRK_BOLETO = 1000;
        WRK_RESTANTE = WRK_SALDO - WRK_BOLETO;
        System.out.println("Saldo restante: " + formatNumber(WRK_RESTANTE, 4));
        
        WRK_RESULTADO_DIVISAO = WRK_DIVIDENDO / 2;
        WRK_RESTO_DIVISAO = WRK_DIVIDENDO % 2;
        System.out.println("RESULTADO DA DIVISAO " + formatNumber(WRK_RESULTADO_DIVISAO, 4));
        System.out.println("RESTO DA DIVISAO " + formatNumber(WRK_RESTO_DIVISAO, 4));
        
        WRK_RESULTADO_MULTIPLICACAO = 3 * WRK_SALDO;
        System.out.println("RESULTADO DA MULTIPLICACAO: " + formatNumber(WRK_RESULTADO_MULTIPLICACAO, 4));
    }
    
    private static String formatNumber(int number, int digits) {
        String format = "%0" + digits + "d";
        String formatted = String.format(format, number);
        if (formatted.length() > digits) {
            return formatted.substring(formatted.length() - digits);
        }
        return formatted;
    }
}