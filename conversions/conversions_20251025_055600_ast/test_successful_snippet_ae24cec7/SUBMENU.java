import java.util.Scanner;

public class SUBMENU {
    private char wrkOpcao;
    private String wrkModulo = "Modulo";
    private char wrkTecla;
    private char wrkOpcaoRelato;

    public static void main(String[] args) {
        SUBMENU submenu = new SUBMENU();
        submenu.principal();
    }

    public void principal() {
        iniciar();
        processar();
        finalizar();
    }

    public void iniciar() {
        tela();
        aceitarMenuRelato();
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
                aceitarMenuRelato();
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

    public void tela() {
        System.out.println("SISTEMA DE CLIENTES");
        System.out.println(wrkModulo);
        System.out.println("1 - EM TELA");
        System.out.println("2 - EM DISCO");
        System.out.print("OPCAO......: ");
    }

    public void aceitarMenuRelato() {
        Scanner scanner = new Scanner(System.in);
        wrkOpcao = scanner.next().charAt(0);
        wrkOpcaoRelato = scanner.next().charAt(0);
    }
}