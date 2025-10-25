import java.util.regex.*;

public class PROG008_STRING {
    public static void main(String[] args) {
        String SAUX = "HELLO WORLD 123!!";
        int CONT = 0;
        int YESNO = 0;

        System.out.println(" ");
        System.out.println("==== EXEMPLO 1 ====");
        System.out.println("SAUX='" + SAUX + "'");

        CONT = SAUX.length();
        System.out.println("CONT=" + CONT + " - FOR CHARACTERS");

        CONT = 0;
        for (char c : SAUX.toCharArray()) {
            if (c == 'L') {
                CONT++;
            }
        }
        System.out.println("CONT=" + CONT + " - FOR ALL 'L'");

        CONT = 0;
        for (int i = 0; i < SAUX.length(); i++) {
            if (SAUX.charAt(i) == ' ') {
                break;
            }
            CONT++;
        }
        System.out.println("CONT=" + CONT + " - BEFORE INITIAL ' '");

        CONT = 0;
        boolean foundSpace = false;
        for (int i = 0; i < SAUX.length(); i++) {
            if (SAUX.charAt(i) == ' ') {
                foundSpace = true;
                continue;
            }
            if (foundSpace) {
                CONT++;
            }
        }
        System.out.println("CONT=" + CONT + " - AFTER INITIAL ' '");

        YESNO = SAUX.startsWith("H") ? 1 : 0;
        System.out.println("YESNO=" + YESNO + " - FOR LEADING 'H' 0=N / 1=Y");

        SAUX = SAUX.replaceFirst("E", "A");
        System.out.println("SAUX='" + SAUX + "' - REPLACING FIRST 'E' BY 'A'");

        SAUX = SAUX.replaceAll("LLO", "PPY");
        System.out.println("SAUX='" + SAUX + "' - REPLACING ALL 'LLO' BY 'PPY'");

        SAUX = " HOLA-DAY";
        SAUX = SAUX.replaceAll("(?<= )HOLA(?= -)", "GOOD");
        System.out.println("SAUX='" + SAUX + "' - HOLA-DAY TO GOOD-DAY'");

        SAUX = "HELLO WORLD";
        SAUX = SAUX.toLowerCase();
        System.out.println("SAUX='" + SAUX + "' - CONVERTING'");

        StringBuilder sb = new StringBuilder();
        for (char c : SAUX.toCharArray()) {
            sb.append('F');
        }
        SAUX = sb.toString();
        System.out.println("SAUX='" + SAUX + "' - REPLACING ");
    }
}