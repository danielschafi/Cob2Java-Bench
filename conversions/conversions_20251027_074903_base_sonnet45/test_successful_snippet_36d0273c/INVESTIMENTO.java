import java.util.Scanner;
import java.text.DecimalFormat;

public class INVESTIMENTO {
    
    private double wrkValor = 0.0;
    private double wrkAcum = 0.0;
    private int wrkMeses = 1;
    private double wrkTaxa = 0.0;
    private DecimalFormat wrkAcumEd = new DecimalFormat("$#,##0.00");
    
    private Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        INVESTIMENTO program = new INVESTIMENTO();
        program.principal();
    }
    
    private void principal() {
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
    
    private void iniciar0050() {
        System.out.println("DIGITE UM VALOR DE TAXA MENSAL: ");
        wrkTaxa = scanner.nextDouble();
    }
    
    private void iniciar0100() {
        System.out.println("DIGITE UM VALOR DE INVESTIMENTO: ");
        wrkValor = scanner.nextDouble();
    }
    
    private void processar0200() {
        wrkMeses = wrkMeses + 1;
        wrkAcum = wrkAcum + wrkValor;
        wrkAcum = wrkAcum * (wrkTaxa / 100 + 1);
        iniciar0100();
    }
    
    private void finalizar0300() {
        System.out.println("-------------------");
        wrkMeses = wrkMeses - 1;
        System.out.println("QUANTIDADE DE INVESTIMENTOS: " + wrkMeses);
        if (wrkAcum > 0) {
            System.out.println("ACUMULADO: R" + wrkAcumEd.format(wrkAcum));
        } else {
            System.out.println("FINAL DO PROCESSAMENTO");
        }
        scanner.close();
        System.exit(0);
    }
}