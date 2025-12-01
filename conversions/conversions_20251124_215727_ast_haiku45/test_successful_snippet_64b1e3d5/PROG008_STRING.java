```java
public class PROG008_STRING {
    private static String saux = "";
    private static int cont = 0;
    private static int yesno = 0;
    private static int idx = 0;

    public static void main(String[] args) {
        mainProcedure();
    }

    private static void mainProcedure() {
        saux = "HELLO WORLD 123!!";
        padString(15);

        System.out.println(" ");
        System.out.println("==== EXEMPLO 1 ====");
        System.out.println("SAUX='" + saux + "'");

        cont = 0;
        cont = countCharacters(saux);
        System.out.println("CONT=" + cont + " - FOR CHARACTERS");

        cont = 0;
        cont = countAll(saux, "L");
        System.out.println("CONT=" + cont + " - FOR ALL 'L'");

        cont = 0;
        cont = countCharactersBeforeInitial(saux, " ");
        System.out.println("CONT=" + cont + " - BEFORE INITIAL ' '");

        cont = 0;
        cont = countCharactersAfterInitial(saux, " ");
        System.out.println("CONT=" + cont + " - AFTER INITIAL ' '");

        yesno = 0;
        yesno = countLeading(saux, "H");
        System.out.println("YESNO=" + yesno + " - FOR LEADING 'H' 0=N / 1=Y");

        saux = replaceFirst(saux, "E", "A");
        System.out.println("SAUX='" + saux + "' - REPLACING FIRST 'E' BY 'A'");

        saux = replaceAll(saux, "LLO", "PPY");
        System.out.println("SAUX='" + saux + "' - REPLACING ALL 'LLO' BY 'PPY'");

        saux = " HOLA-DAY";
        padString(15);
        saux = replaceAllBetween(saux, "HOLA", "GOOD", " ", "-");
        System.out.println("SAUX='" + saux + "' - HOLA-DAY TO GOOD-DAY'");

        saux = "HELLO WORLD";
        padString(15);
        saux = converting(saux, "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyz");
        System.out.println("SAUX='" + saux + "' - CONVERTING'");

        saux = replaceAllCharacters(saux, "F");
        System.out.println("SAUX='" + saux + "' - REPLACING ");

        System.exit(0);
    }

    private static void padString(int length) {
        if (saux.length() < length) {
            saux = String.format("%-" + length + "s", saux);
        }
    }

    private static int countCharacters(String str) {
        return str.length();
    }

    private static int countAll(String str, String pattern) {
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(pattern, index)) != -1) {
            count++;
            index += pattern.length();
        }
        return count;
    }

    private static int countCharactersBeforeInitial(String str, String pattern) {
        int index = str.indexOf(pattern);
        if (index == -1) {
            return str.length();
        }
        return index;
    }

    private static int countCharactersAfterInitial(String str, String pattern) {
        int index = str.indexOf(pattern);
        if (index == -1) {
            return 0;
        }
        return str.length() - index - pattern.length();
    }

    private static int countLeading(String str, String pattern) {
        if (str.startsWith(pattern)) {
            return 1;
        }
        return 0;
    }

    private static String replaceFirst(String str, String oldStr, String newStr) {
        int index = str.indexOf(oldStr);
        if (index == -1) {
            return str;
        }
        return str.substring(0, index) + newStr + str.substring(index + oldStr.length());
    }

    private static String replaceAll(String str, String oldStr, String newStr) {
        return str.replace(oldStr, newStr);
    }

    private static String replaceAllBetween(String str, String oldStr, String newStr, String afterPattern, String beforePattern) {
        int afterIndex = str.indexOf(afterPattern);
        int beforeIndex = str.indexOf(beforePattern);

        if (afterIndex == -1 || beforeIndex == -1 || afterIndex >= beforeIndex) {
            return str;
        }

        int searchStart = afterIndex + afterPattern.length();
        int searchEnd = beforeIndex;

        String before = str.substring(0, searchStart);
        String middle = str.substring(searchStart, searchEnd);
        String after = str.substring(searchEnd);

        middle = middle.replace(oldStr, newStr);

        return before + middle + after;
    }

    private static String converting(String str, String fromChars, String toChars) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            int index = fromChars.indexOf(c);
            if (index != -1 && index < toChars.length()) {
                result.append(toChars.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private static String replaceAllCharacters(String str, String replacement) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            result.append(replacement);
        }
        return result.toString();
    }
}