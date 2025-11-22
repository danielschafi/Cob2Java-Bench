public class PROG008_STRING {
    
    private String SAUX;
    private int CONT;
    private int YESNO;
    private int IDX;
    
    public PROG008_STRING() {
        SAUX = "";
        CONT = 0;
        YESNO = 0;
        IDX = 0;
    }
    
    public void mainProcedure() {
        SAUX = padRight("HELLO WORLD 123!!", 15);
        
        System.out.println(" ");
        System.out.println("==== EXEMPLO 1 ====");
        System.out.println("SAUX='" + SAUX + "'");
        
        CONT = 0;
        CONT = SAUX.length();
        System.out.println("CONT=" + String.format("%02d", CONT) + " - FOR CHARACTERS");
        
        CONT = 0;
        CONT = countOccurrences(SAUX, "L");
        System.out.println("CONT=" + String.format("%02d", CONT) + " - FOR ALL 'L'");
        
        CONT = 0;
        int spaceIndex = SAUX.indexOf(" ");
        if (spaceIndex != -1) {
            CONT = spaceIndex;
        } else {
            CONT = SAUX.length();
        }
        System.out.println("CONT=" + String.format("%02d", CONT) + " - BEFORE INITIAL ' '");
        
        CONT = 0;
        spaceIndex = SAUX.indexOf(" ");
        if (spaceIndex != -1) {
            CONT = SAUX.length() - spaceIndex - 1;
        }
        System.out.println("CONT=" + String.format("%02d", CONT) + " - AFTER INITIAL ' '");
        
        YESNO = 0;
        if (SAUX.startsWith("H")) {
            YESNO = 1;
        }
        System.out.println("YESNO=" + YESNO + " - FOR LEADING 'H' 0=N / 1=Y");
        
        SAUX = replaceFirst(SAUX, "E", "A");
        System.out.println("SAUX='" + SAUX + "' - REPLACING FIRST 'E' BY 'A'");
        
        SAUX = SAUX.replace("LLO", "PPY");
        System.out.println("SAUX='" + SAUX + "' - REPLACING ALL 'LLO' BY 'PPY'");
        
        SAUX = padRight(" HOLA-DAY", 15);
        SAUX = replaceInRange(SAUX, "HOLA", "GOOD", " ", "-");
        System.out.println("SAUX='" + SAUX + "' - HOLA-DAY TO GOOD-DAY'");
        
        SAUX = padRight("HELLO WORLD", 15);
        SAUX = convertCase(SAUX, "ABCDEFGHIJKLMNOPQRSTUVWXZ", "abcdefghijklmnopqrstuvwxz");
        System.out.println("SAUX='" + SAUX + "' - CONVERTING'");
        
        SAUX = replaceAllChars(SAUX, "F");
        System.out.println("SAUX='" + SAUX + "' - REPLACING ");
    }
    
    private String padRight(String str, int length) {
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }
    
    private int countOccurrences(String str, String target) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i + 1).equals(target)) {
                count++;
            }
        }
        return count;
    }
    
    private String replaceFirst(String str, String target, String replacement) {
        int index = str.indexOf(target);
        if (index != -1) {
            return str.substring(0, index) + replacement + str.substring(index + target.length());
        }
        return str;
    }
    
    private String replaceInRange(String str, String target, String replacement, String after, String before) {
        int afterIndex = str.indexOf(after);
        int beforeIndex = str.indexOf(before);
        
        if (afterIndex == -1) afterIndex = -1;
        if (beforeIndex == -1) beforeIndex = str.length();
        
        if (afterIndex != -1) {
            String prefix = str.substring(0, afterIndex + 1);
            String middle = str.substring(afterIndex + 1, beforeIndex);
            String suffix = str.substring(beforeIndex);
            
            middle = middle.replace(target, replacement);
            return prefix + middle + suffix;
        }
        return str;
    }
    
    private String convertCase(String str, String from, String to) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int index = from.indexOf(c);
            if (index != -1 && index < to.length()) {
                result.append(to.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    
    private String replaceAllChars(String str, String replacement) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            result.append(replacement);
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
        PROG008_STRING program = new PROG008_STRING();
        program.mainProcedure();
    }
}