public class CGPRG001 {
    private static String wsFim = "N";
    private static int wsCtexib = 0;
    private static String wsNome = "";
    private static String wsDpto = "";
    private static String wsFuncao = "";
    private static double wsSalario = 0.0;
    
    public static void main(String[] args) {
        iniciar();
        do {
            processar();
        } while (!wsFim.equals("S"));
        terminar();
    }
    
    private static void iniciar() {
        System.out.println("** INICIO DA EXECUCAO **");
        wsCtexib = 0;
    }
    
    private static void processar() {
        System.out.println("** MIGUEL MORAIS **");
        
        wsNome = "CELSO D. GALLAO ";
        wsDpto = "A.D.S     ";
        wsFuncao = "PROFESSOR";
        wsSalario = 1200.00;
        
        System.out.printf("%-25s%-10s%-15s%8.2f%-24s%n", 
                         wsNome, wsDpto, wsFuncao, wsSalario, "");
        
        wsCtexib++;
        wsFim = "S";
    }
    
    private static void terminar() {
        System.out.println("** FIM DA EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtexib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG001");
    }
}