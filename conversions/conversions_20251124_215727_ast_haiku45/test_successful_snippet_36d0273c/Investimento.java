import java.util.Scanner;
import java.text.DecimalFormat;

public class Investimento {
    private static double wrkValor = 0.0;
    private static double wrkAcum = 0.0;
    private static int wrkMeses = 1;
    private static double wrkTaxa = 0.0;
    private static String wrkAcumEd = "";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        iniciar0050();
        iniciar0100();
        
        if (wrkValor > 0) {
            while (wrkValor != 0) {
                processar0200();
            }
        } else {
            System.out.println("O VALOR DEVE SER !=0");
        }
        
        finalizar0300();
    }

    private static void iniciar0050() {
        System.out.print("DIGITE UM VALOR DE TAXA MENSAL: ");
        wrkTaxa = scanner.nextDouble();
    }

    private static void iniciar0100() {
        System.out.print("DIGITE UM VALOR DE INVESTIMENTO: ");
        wrkValor = scanner.nextDouble();
    }

    private static void processar0200() {
        wrkMeses++;
        wrkAcum += wrkValor;
        wrkAcum = wrkAcum * (wrkTaxa / 100.0 + 1.0);
        
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        wrkAcumEd = df.format(wrkAcum);
        
        iniciar0100();
    }

    private static void finalizar0300() {
        System.out.println("-------------------");
        wrkMeses--;
        System.out.println("QUANTIDADE DE INVESTIMENTOS: " + wrkMeses);
        
        if (wrkAcum > 0) {
            System.out.println("ACUMULADO: R" + wrkAcumEd);
        } else {
            System.out.println("FINAL DO PROCESSAMENTO");
        }
    }
}