import java.util.Arrays;

public class TrimFunctionTest {
    public static void main(String[] args) {
        String wsTestString1 = "    hello world       ";
        String wsTestString2;

        System.out.println("--" + wsTestString1 + "--");
        System.out.println("--" + wsTestString1.trim() + "--");
        System.out.println("--" + wsTestString1.stripLeading() + "--");
        System.out.println("--" + wsTestString1.stripTrailing() + "--");

        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = wsTestString1;
        System.out.println(wsTestString2);

        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = wsTestString1.trim();
        System.out.println(wsTestString2);

        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = wsTestString1.stripLeading();
        System.out.println(wsTestString2);

        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = wsTestString1.stripTrailing();
        System.out.println(wsTestString2);

        System.out.println("--" + "    String literal    " + "--");
        System.out.println("--" + "   String literal    ".trim() + "--");
        System.out.println("--" + "     String literal   ".stripLeading() + "--");
        System.out.println("--" + "   String literal    ".stripTrailing() + "--");
    }
}