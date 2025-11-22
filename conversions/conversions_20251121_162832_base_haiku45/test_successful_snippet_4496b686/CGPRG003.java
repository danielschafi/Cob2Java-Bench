public class CGPRG003 {
    private static String WS_FIM = "N";
    private static int WS_CTEXIB = 0;
    private static double AS_N1 = 0.0;
    private static double AS_N2 = 0.0;
    private static double AS_MED = 0.0;
    
    private static String WS_ALUNO = "";
    private static long WS_RA = 0;
    private static double WS_NOTA1 = 0.0;
    private static double WS_NOTA2 = 0.0;
    private static double WS_MEDIA = 0.0;
    
    private static int LK_NR_DPTO = 0;
    private static String LK_NOME_DPTO = "";
    private static int LK_COD_RETORNO = 0;
    
    public static void main(String[] args) {
        iniciar();
        while (!WS_FIM.equals("S")) {
            processar();
        }
        terminar();
    }
    
    private static void iniciar() {
        System.out.println("** PROGRAMA 3 **");
        WS_CTEXIB = 0;
    }
    
    private static void processar() {
        System.out.println("** MIGUEL MORAIS - JESSICA HOLANDA **");
        
        WS_ALUNO = "MIGUEL MORAIS ";
        WS_RA = 1680481721001L;
        WS_NOTA1 = 7.25;
        WS_NOTA2 = 10.00;
        AS_N1 = WS_NOTA1;
        AS_N2 = WS_NOTA2;
        AS_MED = (AS_N1 + AS_N2) / 2.0;
        WS_NOTA1 = AS_N1;
        WS_NOTA2 = AS_N2;
        WS_MEDIA = AS_MED;
        
        String output = String.format("%-25s  %13d  %5.2f  %5.2f  %24s%6.2f",
                WS_ALUNO, WS_RA, WS_NOTA1, WS_NOTA2, "", WS_MEDIA);
        System.out.println(output);
        
        WS_CTEXIB++;
        WS_FIM = "S";
    }
    
    private static void terminar() {
        System.out.println("** FIM DA EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + WS_CTEXIB);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG003");
    }
}