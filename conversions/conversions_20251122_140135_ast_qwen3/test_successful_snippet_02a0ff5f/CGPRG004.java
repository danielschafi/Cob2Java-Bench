public class CGPRG004 {
    private static String wsFim = "N";
    private static int wsCtexib = 0;
    private static long asCep = 9000400L;
    private static double asFrente = 22.50;
    private static double asFundo = 80.00;
    private static double asValMetro = 2315.00;
    private static double asValVenda;
    private static double asComissao;
    private static String asData;
    private static String asHora;
    private static long wsCep;
    private static double wsFrente;
    private static double wsFundo;
    private static double wsValMetro;
    private static double wsValVenda;
    private static double wsComissao;
    private static String wsMensagem;

    public static void main(String[] args) {
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyyMMdd");
        java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("HHmmssSS");
        asData = dateFormat.format(date);
        asHora = timeFormat.format(date);
        
        iniciar();
        processar();
        terminar();
    }

    private static void iniciar() {
        System.out.println("** ATIVIDADE 4 **");
        System.out.println("** ANA CAROLINA GOMES DA SILVA **");
        System.out.println("** CALCULO DO PRECO DE VENDA DE UM TERRENO **");
        System.out.println("** RETANGULAR **");
        System.out.println("DATA ATUAL: " + asData);
        System.out.println("HORA ATUAL: " + asHora);
        System.out.println("---------------------");
        wsCtexib = 0;
    }

    private static void processar() {
        System.out.println("** PROCESSAMENTO **");
        
        asValVenda = asFrente * asFundo * asValMetro;
        
        if (asValVenda > 1500000) {
            asComissao = asValVenda * 0.04;
            wsMensagem = "ALTO PADRAO";
        } else {
            asComissao = asValVenda * 0.06;
            wsMensagem = "MEDIO PADRAO";
        }
        
        wsCep = asCep;
        wsFrente = asFrente;
        wsFundo = asFundo;
        wsValMetro = asValMetro;
        wsValVenda = asValVenda;
        wsComissao = asComissao;
        
        System.out.printf("%08d %,.2fM %,.2fM %,.2fM2 %,.2f R$ %,.2f%n", 
                         wsCep, wsFrente, wsFundo, wsValMetro, wsValVenda, wsComissao);
        System.out.println(wsMensagem);
        
        wsCtexib++;
        wsFim = "S";
    }

    private static void terminar() {
        System.out.println("---------------------------");
        System.out.println("** ENCERRANDO A EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtexib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG004");
    }
}