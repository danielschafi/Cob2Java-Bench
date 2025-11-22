import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CGPRG003 {
    
    private String wsFim = "N";
    private int wsCtexib = 0;
    private double asN1 = 0.0;
    private double asN2 = 0.0;
    private double asMed = 0.0;
    
    private String wsAluno = "";
    private long wsRa = 0;
    private double wsNota1 = 0.0;
    private double wsNota2 = 0.0;
    private double wsMedia = 0.0;
    
    private DecimalFormat formatNota;
    private DecimalFormat formatMedia;
    
    public CGPRG003() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        
        formatNota = new DecimalFormat(" 0.00", symbols);
        formatMedia = new DecimalFormat(" 0,00", symbols);
    }
    
    public static void main(String[] args) {
        CGPRG003 program = new CGPRG003();
        program.run();
    }
    
    public void run() {
        iniciar010();
        while (!wsFim.equals("S")) {
            processar030();
        }
        terminar050();
    }
    
    private void iniciar010() {
        System.out.println("** PROGRAMA 3 **");
        wsCtexib = 0;
    }
    
    private void processar030() {
        System.out.println("** MIGUEL MORAIS - JESSICA HOLANDA **");
        
        wsAluno = "MIGUEL MORAIS            ";
        wsRa = 1680481721001L;
        asN1 = 7.25;
        asN2 = 10.00;
        asMed = (asN1 + asN2) / 2;
        wsNota1 = asN1;
        wsNota2 = asN2;
        wsMedia = asMed;
        
        displayRegSysout();
        wsCtexib += 1;
        wsFim = "S";
    }
    
    private void displayRegSysout() {
        StringBuilder output = new StringBuilder();
        output.append(wsAluno);
        output.append("  ");
        output.append(String.format("%013d", wsRa));
        output.append("  ");
        output.append(formatNota.format(wsNota1));
        output.append("  ");
        output.append(formatNota.format(wsNota2));
        output.append("                        ");
        output.append(formatMedia.format(wsMedia));
        
        System.out.println(output.toString());
    }
    
    private void terminar050() {
        System.out.println("** FIM DA EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + String.format("%02d", wsCtexib));
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG003");
    }
}