import java.text.DecimalFormat;

public class CGPRG001 {
    
    private String wsFim = "N";
    private int wsCtexib = 0;
    
    private String wsNome;
    private String wsDpto;
    private String wsFuncao;
    private double wsSalario;
    
    private int lkNrDpto;
    private String lkNomeDpto;
    private int lkCodRetorno;
    
    public static void main(String[] args) {
        CGPRG001 program = new CGPRG001();
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
        System.out.println("** INICIO DA EXECUCAO **");
        wsCtexib = 0;
    }
    
    private void processar030() {
        System.out.println("** MIGUEL MORAIS **");
        
        wsNome = "CELSO D. GALLAO          ";
        wsDpto = "A.D.S     ";
        wsFuncao = "PROFESSOR      ";
        wsSalario = 1200.00;
        
        displayRegSysout();
        wsCtexib += 1;
        wsFim = "S";
    }
    
    private void terminar050() {
        System.out.println("** FIM DA EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + String.format("%02d", wsCtexib));
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG001");
    }
    
    private void displayRegSysout() {
        DecimalFormat df = new DecimalFormat("0000.00");
        String salarioFormatted = df.format(wsSalario);
        
        String output = wsNome + wsDpto + wsFuncao + salarioFormatted;
        System.out.println(output);
    }
}