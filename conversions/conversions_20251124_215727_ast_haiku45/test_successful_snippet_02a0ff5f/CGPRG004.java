import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CGPRG004 {
    private String wsFim = "N";
    private int wsCtexib = 0;
    private long asCep = 0;
    private BigDecimal asFrente = BigDecimal.ZERO;
    private BigDecimal asFundo = BigDecimal.ZERO;
    private BigDecimal asValMetro = BigDecimal.ZERO;
    private BigDecimal asValVenda = BigDecimal.ZERO;
    private BigDecimal asComissao = BigDecimal.ZERO;
    private String asData = "";
    private String asHora = "";
    
    private long wsCep = 0;
    private BigDecimal wsFrente = BigDecimal.ZERO;
    private BigDecimal wsFundo = BigDecimal.ZERO;
    private BigDecimal wsValMetro = BigDecimal.ZERO;
    private BigDecimal wsValVenda = BigDecimal.ZERO;
    private BigDecimal wsComissao = BigDecimal.ZERO;
    private String wsMensagem = "";

    public static void main(String[] args) {
        CGPRG004 program = new CGPRG004();
        program.run();
    }

    public void run() {
        acceptDate();
        acceptTime();
        iniciar();
        while (!wsFim.equals("S")) {
            processar();
        }
        terminar();
    }

    private void acceptDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        asData = today.format(formatter);
    }

    private void acceptTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm.ssss");
        asHora = now.format(formatter);
    }

    private void iniciar() {
        System.out.println("** ATIVIDADE 4 **");
        System.out.println("** ANA CAROLINA GOMES DA SILVA **");
        System.out.println("** CALCULO DO PRECO DE VENDA DE UM TERRENO **");
        System.out.println("** RETANGULAR **");
        System.out.println("DATA ATUAL: " + asData);
        System.out.println("HORA ATUAL: " + asHora);
        System.out.println("---------------------");
        wsCtexib = 0;
    }

    private void processar() {
        System.out.println("** PROCESSAMENTO **");
        
        asCep = 9000400L;
        asFrente = new BigDecimal("22.50");
        asFundo = new BigDecimal("80.00");
        asValMetro = new BigDecimal("2315.00");
        
        asValVenda = asFrente.multiply(asFundo).multiply(asValMetro);
        
        if (asValVenda.compareTo(new BigDecimal("1500000")) > 0) {
            asComissao = asValVenda.multiply(new BigDecimal("0.04"));
            wsMensagem = "ALTO PADRAO";
        } else {
            asComissao = asValVenda.multiply(new BigDecimal("0.06"));
            wsMensagem = "MEDIO PADRAO";
        }
        
        wsCep = asCep;
        wsFrente = asFrente;
        wsFundo = asFundo;
        wsValMetro = asValMetro;
        wsValVenda = asValVenda;
        wsComissao = asComissao;
        
        displayOutput();
        wsCtexib++;
        wsFim = "S";
    }

    private void displayOutput() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMANY);
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        
        DecimalFormat dfFrente = new DecimalFormat("###,##", symbols);
        DecimalFormat dfValMetro = new DecimalFormat("$$$,##", symbols);
        DecimalFormat dfValVenda = new DecimalFormat("$$$.$$$,##", symbols);
        DecimalFormat dfComissao = new DecimalFormat("$$$,##", symbols);
        
        StringBuilder output = new StringBuilder();
        output.append(String.format("%08d", wsCep)).append(" ");
        output.append(dfFrente.format(wsFrente)).append("M ");
        output.append(dfFrente.format(wsFundo)).append("M ");
        output.append(dfValMetro.format(wsValMetro)).append("M2 ");
        output.append(dfValVenda.format(wsValVenda)).append(" ");
        output.append(dfComissao.format(wsComissao)).append(" ");
        output.append(wsMensagem);
        
        System.out.println(output.toString());
    }

    private void terminar() {
        System.out.println("---------------------------");
        System.out.println("** ENCERRANDO A EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtexib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG004");
    }
}