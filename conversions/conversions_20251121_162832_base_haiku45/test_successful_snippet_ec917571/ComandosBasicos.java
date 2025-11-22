```java
public class ComandosBasicos {
    private static String WRK_NOMES = "            ";
    private static long WRK_TELEFONE = 975591454L;
    private static long WRK_COPIA_TELEFONE = 0;
    private static String WRK_SENHA = "27455518";
    private static String WS_IMSILIN = "                  ";
    private static String CH_CODIMSILIN = "               ";
    private static long CH_CCCDES = 0;
    private static long CH_ERBDES = 0;
    private static String WS_DATA = "  ";
    private static long WS_HORA = 0;
    private static long TNI_DTAHORINI = 0;

    public static void main(String[] args) {
        CH_CCCDES = 4568;
        CH_ERBDES = 0;

        if (CH_ERBDES == 0) {
            System.out.println("AQUI");
            CH_ERBDES = 0;
        }
        System.out.println("CH-ERBDES: " + CH_ERBDES);

        if (isNumeric(String.valueOf(CH_CCCDES)) && CH_CCCDES > 0) {
            if (isNumeric(String.valueOf(CH_ERBDES)) && CH_ERBDES > 0) {
                System.out.println("CH-CCCDES: " + CH_CCCDES);
                System.out.println("CH-ERBDES: " + CH_ERBDES);
            }
        }
        WS_DATA = "09";
        WS_HORA = 11;
        TNI_DTAHORINI = 1;

        if (WS_DATA.compareTo(String.valueOf(WS_HORA)) > 0) {
            System.out.println("BELLZEBOSS");
        }

        System.exit(0);
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}