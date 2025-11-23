import java.util.Scanner;

public class INVESTIMENTO {
    private static double WRK_VALOR = 0.0;
    private static double WRK_ACUM = 0.0;
    private static int WRK_MESES = 1;
    private static double WRK_TAXA = 0.0;
    private static String WRK_ACUM_ED = "0,00";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 0050-INICIAR
        System.out.print("DIGITE UM VALOR DE TAXA MENSAL: ");
        WRK_TAXA = scanner.nextDouble();
        
        // 0100-INICIAR
        System.out.print("DIGITE UM VALOR DE INVESTIMENTO: ");
        WRK_VALOR = scanner.nextDouble();
        
        if (WRK_VALOR > 0) {
            // 0200-PROCESSAR
            while (WRK_VALOR != 0) {
                WRK_MESES++;
                WRK_ACUM += WRK_VALOR;
                WRK_ACUM = WRK_ACUM * (WRK_TAXA / 100 + 1);
                WRK_ACUM_ED = String.format("%,.2f", WRK_ACUM).replace('.', ',');
                
                // 0100-INICIAR (again)
                System.out.print("DIGITE UM VALOR DE INVESTIMENTO: ");
                WRK_VALOR = scanner.nextDouble();
            }
        } else {
            System.out.println("O VALOR DEVE SER !=0");
        }
        
        // 0300-FINALIZAR
        System.out.println("-------------------");
        WRK_MESES--;
        System.out.println("QUANTIDADE DE INVESTIMENTOS: " + WRK_MESES);
        if (WRK_ACUM > 0) {
            System.out.println("ACUMULADO: R" + WRK_ACUM_ED);
        } else {
            System.out.println("FINAL DO PROCESSAMENTO");
        }
        
        scanner.close();
    }
}