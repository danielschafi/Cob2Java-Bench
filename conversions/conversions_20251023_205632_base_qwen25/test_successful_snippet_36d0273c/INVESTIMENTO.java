import java.util.Scanner;

public class INVESTIMENTO {
    private double wrkValor = 0.0;
    private double wrkAcum = 0.0;
    private int wrkMeses = 1;
    private double wrkTaxa = 0.0;
    private String wrkAcumEd = "0,00";

    public static void main(String[] args) {
        INVESTIMENTO investimento = new INVESTIMENTO();
        investimento.principal();
    }

    public void principal() {
        iniciar();
        iniciarInvestimento();
        if (wrkValor > 0) {
            while (wrkValor != 0) {
                processar();
            }
        } else {
            System.out.println("O VALOR DEVE SER !=0");
        }
        finalizar();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("DIGITE UM VALOR DE TAXA MENSAL: ");
        wrkTaxa = scanner.nextDouble();
    }

    public void iniciarInvestimento() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("DIGITE UM VALOR DE INVESTIMENTO: ");
        wrkValor = scanner.nextDouble();
    }

    public void processar() {
        wrkMeses++;
        wrkAcum += wrkValor;
        wrkAcum = wrkAcum * (wrkTaxa / 100 + 1);
        wrkAcumEd = String.format("%.2f", wrkAcum).replace('.', ',');
        iniciarInvestimento();
    }

    public void finalizar() {
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