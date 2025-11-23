public class CGPRG001 {
    
    // Working Storage Section
    private static String wsFim = "N";
    private static int wsCtexib = 0;
    
    // WS-REG-SYSOUT structure
    private static String wsNome = "";
    private static String wsDpto = "";
    private static String wsFuncao = "";
    private static double wsSalario = 0.0;
    
    public static void main(String[] args) {
        perform010Iniciar();
        perform030Processar();
        perform050Terminar();
    }
    
    private static void perform010Iniciar() {
        System.out.println("** INICIO DA EXECUCAO **");
        wsCtexib = 0;
    }
    
    private static void perform030Processar() {
        System.out.println("** MIGUEL MORAIS **");
        
        wsNome = "CELSO D. GALLAO ";
        wsDpto = "A.D.S     ";
        wsFuncao = "PROFESSOR";
        wsSalario = 1200.00;
        
        System.out.println(wsNome + wsDpto + wsFuncao + String.format("%1$,.2f", wsSalario) + "                       ");
        wsCtexib++;
        wsFim = "S";
    }
    
    private static void perform050Terminar() {
        System.out.println("** FIM DA EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtexib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG001");
    }
}