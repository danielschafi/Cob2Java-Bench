public class TrimFunctionTest {
    public static void main(String[] args) {
        String wsTestString1 = "    hello world       ";
        String wsTestString2 = new String(new char[30]);

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
    }

    private static String trim(String str) {
        return str.trim();
    }

    private static String trimLeading(String str) {
        int start = 0;
        while (start < str.length() && str.charAt(start) == ' ') {
            start++;
        }
        return str.substring(start);
    }

    private static String trimTrailing(String str) {
        int end = str.length();
        while (end > 0 && str.charAt(end - 1) == ' ') {
            end--;
        }
        return str.substring(0, end);
    }
}