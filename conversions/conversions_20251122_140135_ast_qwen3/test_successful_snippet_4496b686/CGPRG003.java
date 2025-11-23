public class CGPRG003 {
    private static String wsFim = "N";
    private static int wsCtexib = 0;
    private static double asN1 = 0.0;
    private static double asN2 = 0.0;
    private static double asMed = 0.0;
    private static String wsAluno = "";
    private static long wsRa = 0L;
    private static double wsNota1 = 0.0;
    private static double wsNota2 = 0.0;
    private static String wsMedia = "";

    public static void main(String[] args) {
        perform010Iniciar();
        do {
            perform030Processar();
        } while (!wsFim.equals("S"));
        perform050Terminar();
    }

    private static void perform010Iniciar() {
        System.out.println("** PROGRAMA 3 **");
        wsCtexib = 0;
    }

    private static void perform030Processar() {
        System.out.println("** MIGUEL MORAIS - JESSICA HOLANDA **");

        wsAluno = "MIGUEL MORAIS ";
        wsRa = 1680481721001L;
        asN1 = 7.25;
        asN2 = 10.00;
        asMed = (asN1 + asN2) / 2;
        wsNota1 = asN1;
        wsNota2 = asN2;
        wsMedia = String.format("%1.2f", asMed).replace('.', ',');

        StringBuilder wsRegSysout = new StringBuilder();
        wsRegSysout.append(String.format("%-25s", wsAluno));
        wsRegSysout.append("  ");
        wsRegSysout.append(String.format("%013d", wsRa));
        wsRegSysout.append("  ");
        wsRegSysout.append(String.format("%5.2f", wsNota1).replace('.', ','));
        wsRegSysout.append("  ");
        wsRegSysout.append(String.format("%5.2f", wsNota2).replace('.', ','));
        wsRegSysout.append("                       ");
        wsRegSysout.append(wsMedia);

        System.out.println(wsRegSysout.toString());
        wsCtexib++;
        wsFim = "S";
    }

    private static void perform050Terminar() {
        System.out.println("** FIM DA EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtexib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG003");
    }
}