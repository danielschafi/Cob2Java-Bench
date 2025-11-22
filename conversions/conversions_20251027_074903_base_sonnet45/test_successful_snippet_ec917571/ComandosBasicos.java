import java.math.BigDecimal;

public class ComandosBasicos {
    
    private String WRK_NOMES = "            ";
    private int WRK_TELEFONE = 975591454;
    private int WRK_COPIA_TELEFONE = 0;
    private String WRK_SENHA = "27455518";
    private String WS_IMSILIN = "                  ";
    private String CH_CODIMSILIN = "               ";
    private int CH_CCCDES = 0;
    private int CH_ERBDES = 0;
    private String WS_DATA = "  ";
    private int WS_HORA = 0;
    private long TNI_DTAHORINI = 0;
    
    public static void main(String[] args) {
        ComandosBasicos program = new ComandosBasicos();
        program.execute();
    }
    
    private void execute() {
        CH_CCCDES = 4568;
        CH_ERBDES = 0;
        
        if (CH_ERBDES == 0) {
            System.out.println("AQUI");
            CH_ERBDES = 0;
        }
        System.out.println("CH-ERBDES: " + String.format("%04d", CH_ERBDES));
        
        if (isNumeric(CH_CCCDES) && CH_CCCDES > 0) {
            if (isNumeric(CH_ERBDES) && CH_ERBDES > 0) {
                System.out.println("CH-CCCDES: " + String.format("%04d", CH_CCCDES));
                System.out.println("CH-ERBDES: " + String.format("%04d", CH_ERBDES));
            }
        }
        
        WS_DATA = "09";
        WS_HORA = 11;
        TNI_DTAHORINI = 1;
        
        if (WS_DATA.compareTo(String.format("%02d", WS_HORA)) > 0) {
            System.out.println("BELLZEBOSS");
        }
    }
    
    private boolean isNumeric(int value) {
        return true;
    }
}