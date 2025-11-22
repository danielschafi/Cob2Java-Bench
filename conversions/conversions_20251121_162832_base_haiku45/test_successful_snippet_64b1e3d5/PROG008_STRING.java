```java
public class PROG008_STRING {
    public static void main(String[] args) {
        String SAUX = "HELLO WORLD 123!!";
        int CONT = 0;
        int YESNO = 0;
        
        System.out.println(" ");
        System.out.println("==== EXEMPLO 1 ====");
        System.out.println("SAUX='" + SAUX + "'");
        
        CONT = 0;
        CONT = SAUX.length();
        System.out.println("CONT=" + CONT + " - FOR CHARACTERS");
        
        CONT = 0;
        for (char c : SAUX.toCharArray()) {
            if (c == 'L') CONT++;
        }
        System.out.println("CONT=" + CONT + " - FOR ALL 'L'");
        
        CONT = 0;
        int spaceIndex = SAUX.indexOf(" ");
        if (spaceIndex >= 0) {
            CONT = spaceIndex;
        }
        System.out.println("CONT=" + CONT + " - BEFORE INITIAL ' '");
        
        CONT = 0;
        int spaceIndex2 = SAUX.indexOf(" ");
        if (spaceIndex2 >= 0) {
            CONT = SAUX.length() - spaceIndex2 - 1;
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
        int startIdx = SAUX.indexOf(" ") + 1;
        int endIdx = SAUX.indexOf("-");
        if (startIdx >= 0 && endIdx > startIdx) {
            String before = SAUX.substring(0, startIdx);
            String middle = SAUX.substring(startIdx, endIdx).replaceAll("HOLA", "GOOD");
            String after = SAUX.substring(endIdx);
            SAUX = before + middle + after;
        }
        System.out.println("SAUX='" + SAUX + "' - HOLA-DAY TO GOOD-DAY'");
        
        SAUX = "HELLO WORLD";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        for (char c : SAUX.toCharArray()) {
            int idx = uppercase.indexOf(c);
            if (idx >= 0) {
                result.append(lowercase.charAt(idx));
            } else {
                result.append(c);
            }
        }
        SAUX = result.toString();
        System.out.println("SAUX='" + SAUX + "' - CONVERTING'");
        
        StringBuilder result2 = new StringBuilder();
        for (char c : SAUX.toCharArray()) {
            result2.append("F");
        }
        SAUX = result2.toString();
        System.out.println("SAUX='" + SAUX + "' - REPLACING ");
        
        System.exit(0);
    }
}