import java.util.Scanner;
import java.text.DecimalFormat;

public class INVESTIMENTO {
    private static double WRK_VALOR = 0;
    private static double WRK_ACUM = 0;
    private static int WRK_MESES = 1;
    private static double WRK_TAXA = 0;
    private static String WRK_ACUM_ED = "0,00";
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        iniciar_0050();
        iniciar_0100();
        
        if (WRK_VALOR > 0) {
            while (WRK_VALOR != 0) {
                processar_0200();
            }
        } else {
            System.out.println("O VALOR DEVE SER !=0");
        }
        
        finalizar_0300();
    }
    
    private static void iniciar_0050() {
        System.out.print("DIGITE UM VALOR DE TAXA MENSAL: ");
        WRK_TAXA = scanner.nextDouble();
    }
    
    private static void iniciar_0100() {
        System.out.print("DIGITE UM VALOR DE INVESTIMENTO: ");
        WRK_VALOR = scanner.nextDouble();
    }
    
    private static void processar_0200() {
        WRK_MESES = WRK_MESES + 1;
        WRK_ACUM = WRK_ACUM + WRK_VALOR;
        WRK_ACUM = WRK_ACUM * (WRK_TAXA / 100 + 1);
        
        DecimalFormat df = new DecimalFormat("0.00");
        String formatted = df.format(WRK_ACUM);
        WRK_ACUM_ED = formatted.replace(".", ",");
        
        iniciar_0100();
    }
    
    private static void finalizar_0300() {
        System.out.println("-------------------");
        WRK_MESES = WRK_MESES - 1;
        System.out.println("QUANTIDADE DE INVESTIMENTOS: " + WRK_MESES);
        
        if (WRK_ACUM > 0) {
            System.out.println("ACUMULADO: R" + WRK_ACUM_ED);
        } else {
            System.out.println("FINAL DO PROCESSAMENTO");
        }
    }
}