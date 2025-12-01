import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CGPRG003 {
    private String wsFim = "N";
    private int wsCtexib = 0;
    private BigDecimal asN1 = BigDecimal.ZERO;
    private BigDecimal asN2 = BigDecimal.ZERO;
    private BigDecimal asMed = BigDecimal.ZERO;
    
    private String wsAluno = "";
    private long wsRa = 0;
    private BigDecimal wsNota1 = BigDecimal.ZERO;
    private BigDecimal wsNota2 = BigDecimal.ZERO;
    private BigDecimal wsMedia = BigDecimal.ZERO;
    
    private int lkNrDpto = 0;
    private String lkNomeDpto = "";
    private int lkCodRetorno = 0;
    
    public static void main(String[] args) {
        CGPRG003 program = new CGPRG003();
        program.run();
    }
    
    public void run() {
        iniciar();
        while (!wsFim.equals("S")) {
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
        wsNota1 = new BigDecimal("7.25");
        wsNota2 = new BigDecimal("10.00");
        
        asMed = (asN1.add(asN2)).divide(new BigDecimal("2"), 2, java.math.RoundingMode.HALF_UP);
        wsNota1 = asN1;
        wsNota2 = asN2;
        wsMedia = asMed;
        
        exibirRegistro();
        wsCtexib++;
        wsFim = "S";
    }
    
    private void exibirRegistro() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        
        DecimalFormat df = new DecimalFormat("0.00", symbols);
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-25s", wsAluno));
        sb.append("  ");
        sb.append(String.format("%13d", wsRa));
        sb.append("  ");
        sb.append(String.format("%5s", df.format(wsNota1)));
        sb.append("  ");
        sb.append(String.format("%5s", df.format(wsNota2)));
        sb.append("                        ");
        sb.append(String.format("%5s", df.format(wsMedia)));
        
        System.out.println(sb.toString());
    }
    
    private void terminar() {
        System.out.println("** FIM DA EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtexib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG003");
    }
}