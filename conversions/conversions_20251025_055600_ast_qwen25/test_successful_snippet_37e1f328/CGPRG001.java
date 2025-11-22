import java.text.DecimalFormat;

public class CGPRG001 {
    private char wsFim = 'N';
    private int wsCtexib = 0;
    private String wsNome = new String(new char[25]).replace('\0', ' ');
    private String wsDpto = new String(new char[10]).replace('\0', ' ');
    private String wsFuncao = new String(new char[15]).replace('\0', ' ');
    private double wsSalario = 0.0;

    public static void main(String[] args) {
        CGPRG001 program = new CGPRG001();
        program.execute();
    }

    private void execute() {
        iniciar();
        while (wsFim != 'S') {
            processar();
        }
        terminar();
    }

    private void iniciar() {
        System.out.println("** INICIO DA EXECUCAO **");
        wsCtexib = 0;
    }

    private void processar() {
        System.out.println("** MIGUEL MORAIS **");

        wsNome = "CELSO D. GALLAO ";
        wsDpto = "A.D.S     ";
        wsFuncao = "PROFESSOR";
        wsSalario = 1200.00;

        DecimalFormat df = new DecimalFormat("0000.00");
        String formattedSalario = df.format(wsSalario);

        System.out.println(wsNome.trim() + wsDpto.trim() + wsFuncao.trim() + formattedSalario);
        wsCtexib++;
        wsFim = 'S';
    }

    private void terminar() {
        System.out.println("** FIM DA EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtexib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG001");
    }
}