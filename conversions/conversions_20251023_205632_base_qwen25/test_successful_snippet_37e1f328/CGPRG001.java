import java.text.DecimalFormat;

public class CGPRG001 {
    private String wsFim = "N";
    private int wsCtexib = 0;

    private String wsNome;
    private String wsDpto;
    private String wsFuncao;
    private double wsSalario;

    private int lkNrDpto;
    private String lkNomeDpto;
    private int lkCodRetorno;

    public static void main(String[] args) {
        CGPRG001 program = new CGPRG001();
        program.execute();
    }

    public void execute() {
        iniciar();
        while (!wsFim.equals("S")) {
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

        DecimalFormat df = new DecimalFormat("0000,00");
        String formattedSalario = df.format(wsSalario);

        System.out.println(wsNome + wsDpto + wsFuncao + formattedSalario + "                        ");
        wsCtexib++;
        wsFim = "S";
    }

    private void terminar() {
        System.out.println("** FIM DA EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtexib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG001");
    }
}