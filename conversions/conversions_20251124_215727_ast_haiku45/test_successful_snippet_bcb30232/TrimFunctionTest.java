public class TrimFunctionTest {
    private static String wsTestString1 = "    hello world       ";
    private static String wsTestString2;

    public static void main(String[] args) {
        wsTestString1 = "    hello world       ";
        wsTestString2 = "";

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
        int start = 0;
        while (start < str.length() && Character.isWhitespace(str.charAt(start))) {
            start++;
        }
        return str.substring(start);
    }

    private static String trimTrailing(String str) {
        int end = str.length() - 1;
        while (end >= 0 && Character.isWhitespace(str.charAt(end))) {
            end--;
        }
        return str.substring(0, end + 1);
    }
}