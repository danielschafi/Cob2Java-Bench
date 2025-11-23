public class Prog008String {
    private static String SAUX = "";
    private static int CONT = 0;
    private static int YESNO = 0;

    public static void main(String[] args) {
        SAUX = "HELLO WORLD 123!!";

        System.out.println(" ");
        System.out.println("==== EXEMPLO 1 ====");
        System.out.println("SAUX='" + SAUX + "'");

        CONT = 0;
        for (int i = 0; i < SAUX.length(); i++) {
            CONT++;
        }
        System.out.println("CONT=" + CONT + " - FOR CHARACTERS");

        CONT = 0;
        for (int i = 0; i < SAUX.length(); i++) {
            if (SAUX.charAt(i) == 'L') {
                CONT++;
            }
        }
        System.out.println("CONT=" + CONT + " - FOR ALL 'L'");

        CONT = 0;
        boolean foundSpace = false;
        for (int i = 0; i < SAUX.length(); i++) {
            if (!foundSpace && SAUX.charAt(i) == ' ') {
                foundSpace = true;
            } else if (foundSpace) {
                CONT++;
            }
        }
        System.out.println("CONT=" + CONT + " - BEFORE INITIAL ' '");

        CONT = 0;
        boolean afterSpace = false;
        for (int i = 0; i < SAUX.length(); i++) {
            if (SAUX.charAt(i) == ' ') {
                afterSpace = true;
            } else if (afterSpace) {
                CONT++;
            }
        }
        System.out.println("CONT=" + CONT + " - AFTER INITIAL ' '");

        YESNO = 0;
        if (SAUX.startsWith("H")) {
            YESNO = 1;
        }
        System.out.println("YESNO=" + YESNO + " - FOR LEADING 'H' 0=N / 1=Y");

        SAUX = SAUX.replaceFirst("E", "A");
        System.out.println("SAUX='" + SAUX + "' - REPLACING FIRST 'E' BY 'A'");

        SAUX = SAUX.replaceAll("LLO", "PPY");
        System.out.println("SAUX='" + SAUX + "' - REPLACING ALL 'LLO' BY 'PPY'");

        SAUX = " HOLA-DAY";
        int firstSpaceIndex = SAUX.indexOf(' ');
        int firstDashIndex = SAUX.indexOf('-');
        if (firstSpaceIndex != -1 && firstDashIndex != -1 && firstSpaceIndex < firstDashIndex) {
            String beforeSpace = SAUX.substring(0, firstSpaceIndex + 1);
            String afterSpace = SAUX.substring(firstSpaceIndex + 1);
            String afterDash = afterSpace.substring(afterSpace.indexOf('-'));
            SAUX = beforeSpace + "GOOD" + afterDash;
        }
        System.out.println("SAUX='" + SAUX + "' - HOLA-DAY TO GOOD-DAY'");

        SAUX = "HELLO WORLD";
        SAUX = SAUX.toLowerCase();
        System.out.println("SAUX='" + SAUX + "' - CONVERTING'");

        SAUX = "F".repeat(SAUX.length());
        System.out.println("SAUX='" + SAUX + "' - REPLACING ");

        System.exit(0);
    }
}