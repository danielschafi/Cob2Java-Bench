import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CGPRG004 {
    
    private String wsFim = "N";
    private int wsCtexib = 0;
    private int asCep;
    private double asFrente;
    private double asFundo;
    private double asValMetro;
    private double asValVenda;
    private double asComissao;
    private String asData;
    private String asHora;
    private String wsMensagem;
    
    public static void main(String[] args) {
        CGPRG004 program = new CGPRG004();
        program.run();
    }
    
    public void run() {
        acceptDateAndTime();
        iniciar010();
        while (!wsFim.equals("S")) {
            processar030();
        }
        terminar050();
    }
    
    private void acceptDateAndTime() {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH.mm.ssss");
        
        asData = currentDate.format(dateFormatter);
        asHora = currentTime.format(timeFormatter);
    }
    
    private void iniciar010() {
        System.out.println("** ATIVIDADE 4 **");
        System.out.println("** ANA CAROLINA GOMES DA SILVA **");
        System.out.println("** CALCULO DO PRECO DE VENDA DE UM TERRENO **");
        System.out.println("** RETANGULAR **");
        System.out.println("DATA ATUAL: " + asData);
        System.out.println("HORA ATUAL: " + asHora);
        System.out.println("---------------------");
        wsCtexib = 0;
    }
    
    private void processar030() {
        System.out.println("** PROCESSAMENTO **");
        
        asCep = 9000400;
        asFrente = 22.50;
        asFundo = 80.00;
        asValMetro = 2315.00;
        asValVenda = asFrente * asFundo * asValMetro;
        
        if (asValVenda > 1500000) {
            asComissao = asValVenda * 0.04;
            wsMensagem = "ALTO PADRAO";
        } else {
            asComissao = asValVenda * 0.06;
            wsMensagem = "MEDIO PADRAO";
        }
        
        displayOutput();
        wsCtexib += 1;
        wsFim = "S";
    }
    
    private void displayOutput() {
        String output = String.format("%08d %s %s %s %s %s %s",
            asCep,
            formatFrente(asFrente),
            formatFundo(asFundo),
            formatValMetro(asValMetro),
            formatValVenda(asValVenda),
            formatComissao(asComissao),
            wsMensagem);
        System.out.println(output);
    }
    
    private String formatFrente(double value) {
        return String.format("%6.2fM ", value).replace('.', ',');
    }
    
    private String formatFundo(double value) {
        return String.format("%6.2fM ", value).replace('.', ',');
    }
    
    private String formatValMetro(double value) {
        String formatted = String.format("R$ %8.2fM2 ", value);
        formatted = formatted.replace('.', ',');
        return formatted;
    }
    
    private String formatValVenda(double value) {
        String formatted = String.format("R$ %12.2f ", value);
        formatted = formatted.replace('.', ',');
        return formatted;
    }
    
    private String formatComissao(double value) {
        String formatted = String.format("R$ %9.2f ", value);
        formatted = formatted.replace('.', ',');
        return formatted;
    }
    
    private void terminar050() {
        System.out.println("---------------------------");
        System.out.println("** ENCERRANDO A EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtexib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG004");
    }
}