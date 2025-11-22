import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CGPRG004 {
    
    private static String WS_FIM = "N";
    private static int WS_CTEXIB = 0;
    private static long AS_CEP = 0;
    private static BigDecimal AS_FRENTE = BigDecimal.ZERO;
    private static BigDecimal AS_FUNDO = BigDecimal.ZERO;
    private static BigDecimal AS_VAL_METRO = BigDecimal.ZERO;
    private static BigDecimal AS_VAL_VENDA = BigDecimal.ZERO;
    private static BigDecimal AS_COMISSAO = BigDecimal.ZERO;
    private static String AS_DATA = "";
    private static String AS_HORA = "";
    
    private static long WS_CEP = 0;
    private static BigDecimal WS_FRENTE = BigDecimal.ZERO;
    private static BigDecimal WS_FUNDO = BigDecimal.ZERO;
    private static BigDecimal WS_VAL_METRO = BigDecimal.ZERO;
    private static BigDecimal WS_VAL_VENDA = BigDecimal.ZERO;
    private static BigDecimal WS_COMISSAO = BigDecimal.ZERO;
    private static String WS_MENSAGEM = "";
    
    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH.mm.ssss");
        
        AS_DATA = currentDate.format(dateFormatter);
        AS_HORA = currentTime.format(timeFormatter);
        
        iniciar();
        
        while (!WS_FIM.equals("S")) {
            processar();
        }
        
        terminar();
    }
    
    private static void iniciar() {
        System.out.println("** ATIVIDADE 4 **");
        System.out.println("** ANA CAROLINA GOMES DA SILVA **");
        System.out.println("** CALCULO DO PRECO DE VENDA DE UM TERRENO **");
        System.out.println("** RETANGULAR **");
        System.out.println("DATA ATUAL: " + AS_DATA);
        System.out.println("HORA ATUAL: " + AS_HORA);
        System.out.println("---------------------");
        WS_CTEXIB = 0;
    }
    
    private static void processar() {
        System.out.println("** PROCESSAMENTO **");
        
        AS_CEP = 9000400L;
        AS_FRENTE = new BigDecimal("22.50");
        AS_FUNDO = new BigDecimal("80.00");
        AS_VAL_METRO = new BigDecimal("2315.00");
        
        AS_VAL_VENDA = AS_FRENTE.multiply(AS_FUNDO).multiply(AS_VAL_METRO);
        
        if (AS_VAL_VENDA.compareTo(new BigDecimal("1500000")) > 0) {
            AS_COMISSAO = AS_VAL_VENDA.multiply(new BigDecimal("0.04")).setScale(2, RoundingMode.HALF_UP);
            WS_MENSAGEM = "ALTO PADRAO";
        } else {
            AS_COMISSAO = AS_VAL_VENDA.multiply(new BigDecimal("0.06")).setScale(2, RoundingMode.HALF_UP);
            WS_MENSAGEM = "MEDIO PADRAO";
        }
        
        WS_CEP = AS_CEP;
        WS_FRENTE = AS_FRENTE;
        WS_FUNDO = AS_FUNDO;
        WS_VAL_METRO = AS_VAL_METRO;
        WS_VAL_VENDA = AS_VAL_VENDA;
        WS_COMISSAO = AS_COMISSAO;
        
        String output = String.format("%08d %s M %s M %s M2 %s %s %s",
            WS_CEP,
            formatNumber(WS_FRENTE),
            formatNumber(WS_FUNDO),
            formatCurrency(WS_VAL_METRO),
            formatCurrency(WS_VAL_VENDA),
            formatCurrency(WS_COMISSAO),
            WS_MENSAGEM);
        
        System.out.println(output);
        
        WS_CTEXIB++;
        WS_FIM = "S";
    }
    
    private static void terminar() {
        System.out.println("---------------------------");
        System.out.println("** ENCERRANDO A EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + WS_CTEXIB);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG004");
    }
    
    private static String formatNumber(BigDecimal value) {
        return String.format("%,.2f", value).replace(",", "X").replace(".", ",").replace("X", ".");
    }
    
    private static String formatCurrency(BigDecimal value) {
        return "R$ " + String.format("%,.2f", value).replace(",", "X").replace(".", ",").replace("X", ".");
    }
}