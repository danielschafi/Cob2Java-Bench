public class TrimFunctionTest {
    
    private static String wsTestString1 = "    hello world       ";
    private static String wsTestString2 = "";
    
    public static void main(String[] args) {
        mainProcedure();
    }
    
    private static void mainProcedure() {
        System.out.println("--" + wsTestString1 + "--");
        System.out.println("--" + trim(wsTestString1) + "--");
        System.out.println("--" + trimLeading(wsTestString1) + "--");
        System.out.println("--" + trimTrailing(wsTestString1) + "--");
        
        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = padRight(wsTestString1, 30);
        System.out.println(wsTestString2);
        
        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = padRight(trim(wsTestString1), 30);
        System.out.println(wsTestString2);
        
        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = padRight(trimLeading(wsTestString1), 30);
        System.out.println(wsTestString2);
        
        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = padRight(trimTrailing(wsTestString1), 30);
        System.out.println(wsTestString2);
        
        System.out.println("--" + "    String literal    " + "--");
        System.out.println("--" + trim("   String literal    ") + "--");
        System.out.println("--" + trimLeading("     String literal   ") + "--");
        System.out.println("--" + trimTrailing("   String literal    ") + "--");
    }
    
    private static String trim(String str) {
        if (str == null) return "";
        return str.trim();
    }
    
    private static String trimLeading(String str) {
        if (str == null) return "";
        int i = 0;
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }
        return str.substring(i);
    }
    
    private static String trimTrailing(String str) {
        if (str == null) return "";
        int i = str.length() - 1;
        while (i >= 0 && str.charAt(i) == ' ') {
            i--;
        }
        return str.substring(0, i + 1);
    }
    
    private static String padRight(String str, int length) {
        if (str == null) str = "";
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }
}