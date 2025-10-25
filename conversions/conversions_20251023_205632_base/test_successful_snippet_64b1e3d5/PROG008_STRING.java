import java.util.Arrays;

public class PROG008_STRING {
    public static void main(String[] args) {
        char[] SAUX = new char[15];
        int CONT = 0;
        int YESNO = 0;

        Arrays.fill(SAUX, ' ');

        String strSAUX = "HELLO WORLD 123!!";
        System.arraycopy(strSAUX.toCharArray(), 0, SAUX, 0, strSAUX.length());

        System.out.println(" ");
        System.out.println("==== EXEMPLO 1 ====");
        System.out.println("SAUX='" + new String(SAUX) + "'");

        CONT = 0;
        for (char c : SAUX) {
            if (c != ' ') {
                CONT++;
            }
        }
        System.out.println("CONT=" + CONT + " - FOR CHARACTERS");

        CONT = 0;
        for (char c : SAUX) {
            if (c == 'L') {
                CONT++;
            }
        }
        System.out.println("CONT=" + CONT + " - FOR ALL 'L'");

        CONT = 0;
        boolean foundSpace = false;
        for (char c : SAUX) {
            if (c == ' ') {
                foundSpace = true;
                break;
            }
            CONT++;
        }
        System.out.println("CONT=" + CONT + " - BEFORE INITIAL ' '");

        CONT = 0;
        foundSpace = false;
        for (char c : SAUX) {
            if (c == ' ' && !foundSpace) {
                foundSpace = true;
                continue;
            }
            if (foundSpace) {
                CONT++;
            }
        }
        System.out.println("CONT=" + CONT + " - AFTER INITIAL ' '");

        YESNO = 0;
        if (SAUX[0] == 'H') {
            YESNO = 1;
        }
        System.out.println("YESNO=" + YESNO + " - FOR LEADING 'H' 0=N / 1=Y");

        strSAUX = new String(SAUX).replaceFirst("E", "A");
        System.arraycopy(strSAUX.toCharArray(), 0, SAUX, 0, strSAUX.length());
        System.out.println("SAUX='" + new String(SAUX) + "' - REPLACING FIRST 'E' BY 'A'");

        strSAUX = new String(SAUX).replaceAll("LLO", "PPY");
        System.arraycopy(strSAUX.toCharArray(), 0, SAUX, 0, strSAUX.length());
        System.out.println("SAUX='" + new String(SAUX) + "' - REPLACING ALL 'LLO' BY 'PPY'");

        strSAUX = " HOLA-DAY";
        System.arraycopy(strSAUX.toCharArray(), 0, SAUX, 0, strSAUX.length());
        strSAUX = new String(SAUX).replaceAll("(?<= )HOLA(?=-)", "GOOD");
        System.arraycopy(strSAUX.toCharArray(), 0, SAUX, 0, strSAUX.length());
        System.out.println("SAUX='" + new String(SAUX) + "' - HOLA-DAY TO GOOD-DAY'");

        strSAUX = "HELLO WORLD";
        System.arraycopy(strSAUX.toCharArray(), 0, SAUX, 0, strSAUX.length());
        strSAUX = new String(SAUX).replaceAll("[ABCDEFGHIJKLMNOPQRSTUVWXZ]", m -> m.toLowerCase());
        System.arraycopy(strSAUX.toCharArray(), 0, SAUX, 0, strSAUX.length());
        System.out.println("SAUX='" + new String(SAUX) + "' - CONVERTING'");

        strSAUX = new String(SAUX).replaceAll(".", "F");
        System.arraycopy(strSAUX.toCharArray(), 0, SAUX, 0, strSAUX.length());
        System.out.println("SAUX='" + new String(SAUX) + "' - REPLACING ");
    }
}