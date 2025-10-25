import java.io.*;

public class ANDPROG {
    public static void main(String[] args) {
        String STRING1 = "A";
        boolean VALUEA = STRING1.equals("A");

        System.out.println(STRING1);
        if ("A".equals(STRING1) && "A".equals("A") || "B".equals("B")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("A".equals(STRING1) && "A".equals("A") || "B".equals("C")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("B".equals(STRING1) && "A".equals("A") || "B".equals("B")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("B".equals(STRING1) && "A".equals("A") || "B".equals("C")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("B".equals(STRING1) && "A".equals("A") || "B".equals("B") && "C".equals("C")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("B".equals(STRING1) && "A".equals("A") || "B".equals("C")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("B".equals(STRING1) && "A".equals("A") || "B".equals("B") && "C".equals("B")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
    }
}