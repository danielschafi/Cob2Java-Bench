```java
public class TrimFunctionTest {
    public static void main(String[] args) {
        String wsTestString1 = "    hello world       ";
        String wsTestString2;

        System.out.println("--" + wsTestString1 + "--");
        System.out.println("--" + trim(wsTestString1) + "--");
        System.out.println("--" + trimLeading(wsTestString1) + "--");
        System.out.println("--" + trimTrailing(wsTestString1) + "--");

        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = wsTestString1;
        System.out.println(wsTestString2);

        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = trim(wsTestString1);
        System.out.println(wsTestString2);

        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = trimLeading(wsTestString1);
        System.out.println(wsTestString2);

        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = trimTrailing(wsTestString1);
        System.out.println(wsTestString2);

        System.out.println("--" + "    String literal    " + "--");
        System.out.println("--" + trim("   String literal    ") + "--");
        System.out.println("--" + trimLeading("     String literal   ") + "--");
        System.out.println("--" + trimTrailing("   String literal    ") + "--");

        System.exit(0);
    }

    private static String trim(String str) {
        return str.trim();
    }

    private static String trimLeading(String str) {
        int i = 0;
        while (i < str.length() && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        return str.substring(i);
    }

    private static String trimTrailing(String str) {
        int i = str.length() - 1;
        while (i >= 0 && Character.isWhitespace(str.charAt(i))) {
            i--;
        }
        return str.substring(0, i + 1);
    }
}