import java.text.DecimalFormat;

public class CGPRG003 {
    private char wsFim = 'N';
    private int wsCtexib = 0;
    private double asN1 = 0.0;
    private double asN2 = 0.0;
    private double asMed = 0.0;

    private String wsAluno = new String(new char[25]);
    private String filler1 = "  ";
    private long wsRa = 0L;
    private String filler2 = "  ";
    private String wsNota1 = new String(new char[5]);
    private String filler3 = "  ";
    private String wsNota2 = new String(new char[5]);
    private String filler4 = new String(new char[24]);
    private String wsMedia = new String(new char[6]);

    public static void main(String[] args) {
        CGPRG003 program = new CGPRG003();
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
        System.out.println("** PROGRAMA 3 **");
        wsCtexib = 0;
    }

    private void processar() {
        System.out.println("** MIGUEL MORAIS - JESSICA HOLANDA **");

        wsAluno = "MIGUEL MORAIS ";
        wsRa = 1680481721001L;
        asN1 = 7.25;
        asN2 = 10.00;
        asMed = (asN1 + asN2) / 2;

        DecimalFormat df = new DecimalFormat("0.00");
        wsNota1 = String.format("%5s", df.format(asN1)).replace(' ', '0');
        wsNota2 = String.format("%5s", df.format(asN2)).replace(' ', '0');
        wsMedia = String.format("%6s", df.format(asMed)).replace(' ', '0');

        System.out.println(wsAluno + filler1 + wsRa + filler2 + wsNota1 + filler3 + wsNota2 + filler4 + wsMedia);
        wsCtexib++;
        wsFim = 'S';
    }

    private void terminar() {
        System.out.println("** FIM DA EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtexib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG003");
    }
}