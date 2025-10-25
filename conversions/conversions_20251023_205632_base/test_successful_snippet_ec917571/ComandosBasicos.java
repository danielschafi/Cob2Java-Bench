import java.text.DecimalFormat;

public class ComandosBasicos {
    public static void main(String[] args) {
        String wrkNomes = "            ";
        int wrkTelefone = 975591454;
        int wrkCopiaTelefone = 0;
        String wrkSenha = "27455518";
        String wsImsilin = "                  ";
        String chCodImsilin = "               ";
        int chCccdes = 0;
        int chErbdes = 0;
        String wsData = "  ";
        int wsHora = 0;
        long tniDtaHorIni = 0;

        chCccdes = 4568;
        chErbdes = 0;

        if (chErbdes == 0) {
            System.out.println("AQUI");
            chErbdes = 0;
        }
        System.out.println("CH-ERBDES: " + chErbdes);

        if (isNumeric(String.valueOf(chCccdes)) && chCccdes > 0) {
            if (isNumeric(String.valueOf(chErbdes)) && chErbdes > 0) {
                System.out.println("CH-CCCDES: " + chCccdes);
                System.out.println("CH-ERBDES: " + chErbdes);
            }
        }
        wsData = "09";
        wsHora = 11;
        tniDtaHorIni = 1;

        if (wsData.compareTo(String.valueOf(wsHora)) > 0) {
            System.out.println("BELLZEBOSS");
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}