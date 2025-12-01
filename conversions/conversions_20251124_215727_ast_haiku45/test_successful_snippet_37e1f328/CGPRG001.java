public class CGPRG001 {
    private static String wsFim = "N";
    private static int wsCtexib = 0;
    
    private static class WsRegSysout {
        String wsNome = "";
        String wsDpto = "";
        String wsFuncao = "";
        double wsSalario = 0.0;
        String filler = "";
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-25s", wsNome));
            sb.append(String.format("%-10s", wsDpto));
            sb.append(String.format("%-15s", wsFuncao));
            sb.append(String.format("%08.2f", wsSalario));
            sb.append(String.format("%-24s", filler));
            return sb.toString();
        }
    }
    
    private static WsRegSysout wsRegSysout = new WsRegSysout();
    
    public static void main(String[] args) {
        iniciar();
        while (!wsFim.equals("S")) {
            processar();
        }
        terminar();
    }
    
    private static void iniciar() {
        System.out.println("** INICIO DA EXECUCAO **");
        wsCtexib = 0;
    }
    
    private static void processar() {
        System.out.println("** MIGUEL MORAIS **");
        
        wsRegSysout.wsNome = "CELSO D. GALLAO ";
        wsRegSysout.wsDpto = "A.D.S     ";
        wsRegSysout.wsFuncao = "PROFESSOR";
        wsRegSysout.wsSalario = 1200.00;
        
        System.out.println(wsRegSysout.toString());
        wsCtexib++;
        wsFim = "S";
    }
    
    private static void terminar() {
        System.out.println("** FIM DA EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtexib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG001");
    }
}