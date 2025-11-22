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
    private static double wsMedia = 0.0;
    
    public static void main(String[] args) {
        iniciar();
        do {
            processar();
        } while (!wsFim.equals("S"));
        terminar();
    }
    
    private static void iniciar() {
        System.out.println("** PROGRAMA 3 **");
        wsCtexib = 0;
    }
    
    private static void processar() {
        System.out.println("** MIGUEL MORAIS - JESSICA HOLANDA **");
        
        wsAluno = "MIGUEL MORAIS ";
        wsRa = 1680481721001L;
        asN1 = 7.25;
        asN2 = 10.00;
        asMed = (asN1 + asN2) / 2;
        wsNota1 = asN1;
        wsNota2 = asN2;
        wsMedia = asMed;
        
        System.out.printf("%-25s %13d %8.2f %8.2f %30s %6.2f%n", 
                         wsAluno, wsRa, wsNota1, wsNota2, "", wsMedia);
        wsCtexib++;
        wsFim = "S";
    }
    
    private static void terminar() {
        System.out.println("** FIM DA EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtexib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG003");
    }
}