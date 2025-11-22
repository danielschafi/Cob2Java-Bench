public class PROG008_STRING {
    public static void main(String[] args) {
        String SAUX = "HELLO WORLD 123!!";
        int CONT = 0;
        int YESNO = 0;

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
        boolean afterInitialSpace = false;
        for (int i = 0; i < SAUX.length(); i++) {
            if (afterInitialSpace) {
                CONT++;
            } else if (SAUX.charAt(i) == ' ') {
                afterInitialSpace = true;
            }
        }
        System.out.println("CONT=" + CONT + " - AFTER INITIAL ' '");

        YESNO = 0;
        if (SAUX.length() > 0 && SAUX.charAt(0) == 'H') {
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
            String betweenSpaceAndDash = SAUX.substring(firstSpaceIndex + 1, firstDashIndex);
            String afterDash = SAUX.substring(firstDashIndex);
            if (betweenSpaceAndDash.equals("HOLA")) {
                SAUX = beforeSpace + "GOOD" + afterDash;
            }
        }
        System.out.println("SAUX='" + SAUX + "' - HOLA-DAY TO GOOD-DAY'");

        SAUX = "HELLO WORLD";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SAUX.length(); i++) {
            char c = SAUX.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c = (char)(c + 32);
            }
            sb.append(c);
        }
        SAUX = sb.toString();
        System.out.println("SAUX='" + SAUX + "' - CONVERTING'");

        SAUX = "FFFFFFFFFFFFFFF";
        System.out.println("SAUX='" + SAUX + "' - REPLACING ");

        System.out.println(" ");
    }
}