import java.util.Scanner;

public class Submenu {
    private char wrkOpcao;
    private String wrkModulo = "SUB-MENU";
    private char wrkTecla;
    private char wrkOpcaoRelato;

    public static void main(String[] args) {
        Submenu submenu = new Submenu();
        submenu.principal();
    }

    public void principal() {
        iniciar();
        processar();
        finalizar();
    }

    public void iniciar() {
        limpaTela();
        System.out.println("SISTEMA DE CLIENTES");
        System.out.println(wrkModulo);
        System.out.println("1 - EM TELA");
        System.out.println("2 - EM DISCO");
        System.out.print("OPCAO......: ");
        Scanner scanner = new Scanner(System.in);
        wrkOpcaoRelato = scanner.next().charAt(0);
    }

    public void processar() {
        switch (wrkOpcao) {
            case '1':
                relatorioTela();
                break;
            case '2':
                break;
            case '3':
                break;
            case '4':
                break;
            case '5':
                System.out.print("OPCAO......: ");
                Scanner scanner = new Scanner(System.in);
                wrkOpcaoRelato = scanner.next().charAt(0);
                if (wrkOpcaoRelato == '1') {
                    relatorioTela();
                }
                if (wrkOpcaoRelato == '2') {
                    relatorioDisco();
                }
                break;
            default:
                if (wrkOpcao != 'X') {
                    System.out.println("ENTRE COM OPCAO CORRETA");
                }
        }
    }

    public void finalizar() {
    }

    public void relatorioTela() {
    }

    public void relatorioDisco() {
    }

    public void limpaTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}