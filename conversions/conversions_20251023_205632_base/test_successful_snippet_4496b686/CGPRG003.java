import java.text.DecimalFormat;

public class CGPRG003 {
    private String WS_FIM = "N";
    private int WS_CTEXIB = 0;
    private double AS_N1 = 0.0;
    private double AS_N2 = 0.0;
    private double AS_MED = 0.0;
    private String WS_ALUNO = new String(new char[25]);
    private long WS_RA;
    private double WS_NOTA1;
    private double WS_NOTA2;
    private double WS_MEDIA;
    private int LK_NR_DPTO;
    private String LK_NOME_DPTO = new String(new char[15]);
    private int LK_COD_RETORNO;

    public static void main(String[] args) {
        CGPRG003 program = new CGPRG003();
        program.run();
    }

    public void run() {
        procedure010Iniciar();
        while (!WS_FIM.equals("S")) {
            procedure030Processar();
        }
        procedure050Terminar();
    }

    private void procedure010Iniciar() {
        System.out.println("** PROGRAMA 3 **");
        WS_CTEXIB = 0;
    }

    private void procedure030Processar() {
        System.out.println("** MIGUEL MORAIS - JESSICA HOLANDA **");

        WS_ALUNO = "MIGUEL MORAIS ";
        WS_RA = 1680481721001L;
        AS_N1 = 7.25;
        AS_N2 = 10.00;
        AS_MED = (AS_N1 + AS_N2) / 2;
        WS_NOTA1 = AS_N1;
        WS_NOTA2 = AS_N2;
        WS_MEDIA = AS_MED;

        DecimalFormat df = new DecimalFormat("0.00");
        System.out.printf("%-25s  %013d  %5s  %5s  %7s%n", WS_ALUNO, WS_RA, df.format(WS_NOTA1), df.format(WS_NOTA2), df.format(WS_MEDIA));
        WS_CTEXIB++;
        WS_FIM = "S";
    }

    private void procedure050Terminar() {
        System.out.println("** FIM DA EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + WS_CTEXIB);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG003");
    }
}