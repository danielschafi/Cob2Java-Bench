import java.util.Scanner;

public class INVESTIMENTO {
    private static double WRK_VALOR;
    private static double WRK_ACUM;
    private static int WRK_MESES;
    private static double WRK_TAXA;
    private static String WRK_ACUM_ED;

    public static void main(String[] args) {
        iniciarPrincipal();
    }

    public static void iniciarPrincipal() {
        iniciar1();
        iniciar2();
        if (WRK_VALOR > 0) {
            processar();
        } else {
            System.out.println("O VALOR DEVE SER !=0");
        }
        finalizar();
    }

    public static void iniciar1() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("DIGITE UM VALOR DE TAXA MENSAL: ");
        WRK_TAXA = scanner.nextDouble();
    }

    public static void iniciar2() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("DIGITE UM VALOR DE INVESTIMENTO: ");
        WRK_VALOR = scanner.nextDouble();
    }

    public static void processar() {
        while (WRK_VALOR > 0) {
            WRK_MESES++;
            WRK_ACUM += WRK_VALOR;
            WRK_ACUM = WRK_ACUM * (WRK_TAXA / 100 + 1);
            WRK_ACUM_ED = String.format("R %,.2f", WRK_ACUM);
            iniciar2();
        }
    }

    public static void finalizar() {
        System.out.println("-------------------");
        WRK_MESES--;
        System.out.println("QUANTIDADE DE INVESTIMENTOS: " + WRK_MESES);
        if (WRK_ACUM > 0) {
            System.out.println("ACUMULADO: " + WRK_ACUM_ED);
        } else {
            System.out.println("FINAL DO PROCESSAMENTO");
        }
    }
}