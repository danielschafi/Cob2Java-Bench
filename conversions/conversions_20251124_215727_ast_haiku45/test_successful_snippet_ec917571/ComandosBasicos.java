public class ComandosBasicos {
    private String wrkNomes = "            ";
    private long wrkTelefone = 975591454L;
    private long wrkCopiaTelefone = 0L;
    private String wrkSenha = "27455518";
    private String wsImsilin = "                  ";
    private String chCodimsilin = "               ";
    private int chCccdes = 0;
    private int chErbdes = 0;
    private String wsData = "  ";
    private int wsHora = 0;
    private long tniDtahorini = 0L;

    public static void main(String[] args) {
        ComandosBasicos program = new ComandosBasicos();
        program.run();
    }

    public void run() {
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
        tniDtahorini = 1L;

        if (wsData.compareTo(String.valueOf(wsHora)) > 0) {
            System.out.println("BELLZEBOSS");
        }

        System.exit(0);
    }

    private boolean isNumeric(String str) {
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