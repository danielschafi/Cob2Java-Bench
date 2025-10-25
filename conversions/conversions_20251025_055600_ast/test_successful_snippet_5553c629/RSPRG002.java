import java.util.Scanner;

public class RSPRG002 {
    private char wsFim;
    private int wsCtlido;
    private double wsMedia;
    private int wsNumeroIn;
    private String wsNomeIn;
    private char wsSexoIn;
    private int wsIdadeIn;
    private String wsCursoIn;
    private double wsNota1In;
    private double wsNota2In;

    public static void main(String[] args) {
        RSPRG002 program = new RSPRG002();
        program.rsprg002();
    }

    private void rsprg002() {
        iniciar();
        while (wsFim != 'S') {
            processar();
        }
        terminar();
    }

    private void iniciar() {
        lerSysin();
    }

    private void lerSysin() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            wsNumeroIn = scanner.nextInt();
            wsNomeIn = scanner.next();
            wsSexoIn = scanner.next().charAt(0);
            wsIdadeIn = scanner.nextInt();
            wsCursoIn = scanner.next();
            wsNota1In = scanner.nextDouble();
            wsNota2In = scanner.nextDouble();
        } else {
            wsFim = 'S';
        }
    }

    private void processar() {
        wsMedia = (wsNota1In + wsNota2In) / 2;
        System.out.println(wsNumeroIn + " " + wsNomeIn + " " + wsSexoIn + " " + wsIdadeIn + " " + wsCursoIn + " " + wsNota1In + " " + wsNota2In);
        System.out.println(wsMedia);
        lerSysin();
    }

    private void terminar() {
        System.out.println(" *========================================*");
        System.out.println(" *   TOTAIS DE CONTROLE - RSPRG002        *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * REGISTROS LIDOS    - SYSIN  = " + wsCtlido);
        System.out.println(" *========================================*");
        System.out.println(" *----------------------------------------*");
        System.out.println(" *      TERMINO NORMAL DO RSPRG002        *");
        System.out.println(" *----------------------------------------*");
    }
}